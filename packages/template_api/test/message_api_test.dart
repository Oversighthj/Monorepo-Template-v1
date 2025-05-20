import 'package:test/test.dart';
import 'package:template_api/template_api.dart';


/// tests for MessageApi
void main() {
  final instance = TemplateApi().getMessageApi();

  group(MessageApi, () {
    // Get message list
    //
    //Future<BuiltList<MessageDTO>> messagesGet({ int bookingId }) async
    test('test messagesGet', () async {
      // TODO
    });

    // Create message
    //
    //Future messagesPost(MessageDTO messageDTO) async
    test('test messagesPost', () async {
      // TODO
    });

  });
}
