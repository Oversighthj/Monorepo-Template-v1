import 'package:flutter/material.dart';
import 'package:built_collection/built_collection.dart';
import 'package:template_api/template_api.dart' as gen;

import '../../../../core/network/api_client.dart';
import '../widgets/booking_card.dart';

class BookingListPage extends StatefulWidget {
  final gen.BookingApi? apiOverride;
  const BookingListPage({super.key, this.apiOverride});

  @override
  State<BookingListPage> createState() => _BookingListPageState();
}

class _BookingListPageState extends State<BookingListPage> {
  late final gen.BookingApi _api =
      widget.apiOverride ?? ApiClient.instance.bookingApi;

  BuiltList<gen.BookingDTO>? _data;
  bool _loading = true;
  String? _error;

  @override
  void initState() {
    super.initState();
    _load();
  }

  Future<void> _load() async {
    try {
      final res = await _api.bookingsGet();
      setState(() {
        _data = res.data;
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
        appBar: AppBar(title: const Text('Bookings')),
        body: Center(child: Text(_error!, style: const TextStyle(color: Colors.red))),
      );
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Bookings')),
      body: ListView.builder(
        itemCount: _data?.length ?? 0,
        itemBuilder: (_, i) => BookingCard(booking: _data![i]),
      ),
    );
  }
}
