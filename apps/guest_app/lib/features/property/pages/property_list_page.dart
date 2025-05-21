import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:template_api/template_api.dart';   // يصدّر DefaultApi و PropertyDTO و serializers

class PropertyListPage extends StatefulWidget {
  final DefaultApi? apiOverride;
  const PropertyListPage({super.key, this.apiOverride});

  @override
  State<PropertyListPage> createState() => _PropertyListPageState();
}

class _PropertyListPageState extends State<PropertyListPage> {
  late DefaultApi _api;
  List<PropertyDTO> _properties = [];
  bool _loading = true;

  @override
  void initState() {
    super.initState();
    _api = widget.apiOverride ??
        DefaultApi(Dio(BaseOptions(baseUrl: 'http://localhost:8080')), serializers);
    _loadProperties();
  }

  Future<void> _loadProperties() async {
    try {
      final res = await _api.propertiesGet();
      setState(() {
        _properties = res.data?.toList() ?? [];
        _loading = false;
      });
    } catch (e) {
      setState(() => _loading = false);
      rethrow;
    }
  }

  @override
  Widget build(BuildContext context) {
    if (_loading) {
      return const Scaffold(
        body: Center(child: CircularProgressIndicator()),
      );
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Properties')),
      body: ListView.builder(
        itemCount: _properties.length,
        itemBuilder: (context, index) {
          final prop = _properties[index];
          return ListTile(
            title: Text(prop.name ?? 'Unnamed'),
            subtitle: Text(prop.address ?? 'No address'),
            leading: const Icon(Icons.home),
          );
        },
      ),
    );
  }
}
