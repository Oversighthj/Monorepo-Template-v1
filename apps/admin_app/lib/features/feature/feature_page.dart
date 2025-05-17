import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:template_api/template_api.dart';

class FeaturePage extends StatefulWidget {
  /// يُمرَّر في الاختبارات لحقن API مزيَّف
  final DefaultApi? apiOverride;

  const FeaturePage({super.key, this.apiOverride});

  @override
  State<FeaturePage> createState() => _FeaturePageState();
}

class _FeaturePageState extends State<FeaturePage> {
  late final DefaultApi _api;
  List<FeatureDTO> _features = [];

  @override
  void initState() {
    super.initState();

    // ▸ إذا جاء apiOverride من الاختبار نستخدمه، وإلا ننشئ الـ API الحقيقي
    _api = widget.apiOverride ??
        DefaultApi(
          Dio(BaseOptions(baseUrl: 'http://localhost:8080')),
          serializers,
        );

    _load();
  }

  Future<void> _load() async {
    try {
      final resp = await _api.featureGet();
      setState(() => _features = resp.data?.toList() ?? []);
    } catch (e, st) {
      debugPrint('❌ ERROR while calling /feature: $e');
      debugPrintStack(stackTrace: st);
      if (mounted) setState(() => _features = []);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Features')),
      body: _features.isEmpty
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: _features.length,
              itemBuilder: (_, i) => ListTile(
                leading: Text('${_features[i].id}'),
                title: Text(_features[i].name ?? ''),
              ),
            ),
    );
  }
}
