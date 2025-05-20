import 'package:test/test.dart';
import 'package:template_api/template_api.dart';


/// tests for BookingApi
void main() {
  final instance = TemplateApi().getBookingApi();

  group(BookingApi, () {
    // Get booking list
    //
    //Future<BuiltList<BookingDTO>> bookingsGet({ int propertyId, int userId }) async
    test('test bookingsGet', () async {
      // TODO
    });

    // Delete booking
    //
    //Future bookingsIdDelete(int id) async
    test('test bookingsIdDelete', () async {
      // TODO
    });

    // Get booking
    //
    //Future<BookingDTO> bookingsIdGet(int id) async
    test('test bookingsIdGet', () async {
      // TODO
    });

    // Update booking
    //
    //Future<BookingDTO> bookingsIdPut(int id, BookingDTO bookingDTO) async
    test('test bookingsIdPut', () async {
      // TODO
    });

    // Create booking
    //
    //Future bookingsPost(BookingDTO bookingDTO) async
    test('test bookingsPost', () async {
      // TODO
    });

  });
}
