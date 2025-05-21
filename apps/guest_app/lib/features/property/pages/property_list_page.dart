import 'package:flutter/material.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import '../../../core/network/api_client.dart';

/// صفحة تعرض قائمة الملكيّات القادمة من /properties
class PropertyListPage extends StatefulWidget {
  /// يحقن API مخصّص في الاختبارات؛ يظلّ null في التشغيل العادي
  final gen.DefaultApi? apiOverride;

  const PropertyListPage({Key? key, this.apiOverride}) : super(key: key);

  @override
  State<PropertyListPage> createState() => _PropertyListPageState();
}

class _PropertyListPageState extends State<PropertyListPage> {
  /// العميل المولَّد (أو المُحقَن) للتواصل مع الخادم
  late final gen.DefaultApi _api =
      widget.apiOverride ?? ApiClient.instance.defaultApi;

  BuiltList<gen.PropertyDTO>? _properties;
  bool _loading = true;
  String? _error;

  @override
  void initState() {
    super.initState();
    _load();
  }

  Future<void> _load() async {
    try {
      final res = await _api.propertiesGet();
      setState(() {
        _properties = res.data;
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
      return const Scaffold(
        body: Center(child: CircularProgressIndicator()),
      );
    }

    if (_error != null) {
      return Scaffold(
        appBar: AppBar(title: const Text('Properties')),
        body: Center(
          child: Text(
            _error!,
            style: const TextStyle(color: Colors.red),
            textAlign: TextAlign.center,
          ),
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Properties')),
      body: ListView.builder(
        itemCount: _properties?.length ?? 0,
        itemBuilder: (_, index) {
          final prop = _properties![index];
          return ListTile(
            leading: const Icon(Icons.home_outlined),
            title: Text(prop.name ?? 'Unnamed'),
            subtitle: Text(prop.address ?? ''),
          );
        },
      ),
    );
  }
}
