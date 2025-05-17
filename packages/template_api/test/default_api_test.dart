import 'package:test/test.dart';
import 'package:template_api/template_api.dart';


/// tests for DefaultApi
void main() {
  final instance = TemplateApi().getDefaultApi();

  group(DefaultApi, () {
    // Get feature list
    //
    // Returns a list of features.
    //
    //Future<BuiltList<FeatureDTO>> featureGet() async
    test('test featureGet', () async {
      // TODO
    });

    // Status check
    //
    // Returns 'alive' to indicate service is running.
    //
    //Future<String> statusGet() async
    test('test statusGet', () async {
      // TODO
    });

  });
}
