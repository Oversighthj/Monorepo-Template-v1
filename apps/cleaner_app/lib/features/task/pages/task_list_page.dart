import 'package:flutter/material.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import '../../../../core/network/api_client.dart';
import '../widgets/task_card.dart';

class TaskListPage extends StatefulWidget {
  final gen.TaskApi? apiOverride;
  const TaskListPage({super.key, this.apiOverride});

  @override
  State<TaskListPage> createState() => _TaskListPageState();
}

class _TaskListPageState extends State<TaskListPage> {
  late final gen.TaskApi _api =
      widget.apiOverride ?? ApiClient.instance.taskApi;

  BuiltList<gen.TaskDTO>? _tasks;
  bool _loading = true;
  String? _error;

  @override
  void initState() {
    super.initState();
    _load();
  }

  Future<void> _load() async {
    try {
      final res = await _api.tasksGet();
      setState(() {
        _tasks = res.data;
        _loading = false;
      });
    } catch (e) {
      setState(() {
        _error = e.toString();
        _loading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    if (_loading) {
      return const Scaffold(body: Center(child: CircularProgressIndicator()));
    }

    if (_error != null) {
      return Scaffold(
        appBar: AppBar(title: const Text('Tasks')),
        body: Center(child: Text(_error!, style: const TextStyle(color: Colors.red))),
      );
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Tasks')),
      body: ListView.builder(
        itemCount: _tasks?.length ?? 0,
        itemBuilder: (_, i) => TaskCard(task: _tasks![i]),
      ),
    );
  }
}
