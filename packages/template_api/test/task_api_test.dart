import 'package:test/test.dart';
import 'package:template_api/template_api.dart';


/// tests for TaskApi
void main() {
  final instance = TemplateApi().getTaskApi();

  group(TaskApi, () {
    // Get task list
    //
    //Future<BuiltList<TaskDTO>> tasksGet({ int bookingId, int cleanerId }) async
    test('test tasksGet', () async {
      // TODO
    });

    // Delete task
    //
    //Future tasksIdDelete(int id) async
    test('test tasksIdDelete', () async {
      // TODO
    });

    // Get task
    //
    //Future<TaskDTO> tasksIdGet(int id) async
    test('test tasksIdGet', () async {
      // TODO
    });

    // Update task
    //
    //Future<TaskDTO> tasksIdPut(int id, TaskDTO taskDTO) async
    test('test tasksIdPut', () async {
      // TODO
    });

    // Create task
    //
    //Future tasksPost(TaskDTO taskDTO) async
    test('test tasksPost', () async {
      // TODO
    });

  });
}
