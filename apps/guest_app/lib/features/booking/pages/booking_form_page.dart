import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:template_api/template_api.dart' as gen;

import '../../../core/network/api_client.dart';

class BookingFormPage extends StatefulWidget {
  final int propertyId;
  final gen.BookingApi? apiOverride;

  const BookingFormPage({
    super.key,
    required this.propertyId,
    this.apiOverride,
  });

  @override
  State<BookingFormPage> createState() => _BookingFormPageState();
}

class _BookingFormPageState extends State<BookingFormPage> {
  DateTime? _start;
  DateTime? _end;

  final _fmt = DateFormat('yyyy-MM-dd');

  late final gen.BookingApi _api =
      widget.apiOverride ?? ApiClient.instance.bookingApi;

  Future<void> _pickStart() async {
    final picked = await showDatePicker(
      context: context,
      firstDate: DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 365)),
      initialDate: _start ?? DateTime.now(),
    );
    if (picked != null) setState(() => _start = picked);
  }

  Future<void> _pickEnd() async {
    final picked = await showDatePicker(
      context: context,
      firstDate: _start ?? DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 365)),
      initialDate: _end ?? (_start ?? DateTime.now()),
    );
    if (picked != null) setState(() => _end = picked);
  }

  Future<void> _submit() async {
    if (_start == null || _end == null) return;

    final booking = gen.BookingDTO((b) => b
      ..propertyId = widget.propertyId
      ..userId = 1
      ..startAt = _start!.toUtc()
      ..endAt = _end!.toUtc()
      ..status = gen.BookingStatus.PENDING);

    try {
      await _api.bookingsPost(bookingDTO: booking);
      if (!mounted) return;
      ScaffoldMessenger.of(context)
          .showSnackBar(const SnackBar(content: Text('Booking sent')));
      Navigator.pop(context);
    } catch (e) {
      if (!mounted) return;
      ScaffoldMessenger.of(context)
          .showSnackBar(SnackBar(content: Text('Error: $e')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('New Booking')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            ListTile(
              leading: const Icon(Icons.calendar_today),
              title: const Text('Start date'),
              subtitle:
                  Text(_start != null ? _fmt.format(_start!) : 'Choose…'),
              onTap: _pickStart,
            ),
            ListTile(
              leading: const Icon(Icons.calendar_month),
              title: const Text('End date'),
              subtitle: Text(_end != null ? _fmt.format(_end!) : 'Choose…'),
              onTap: _pickEnd,
            ),
            const Spacer(),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton.icon(
                onPressed: (_start != null && _end != null) ? _submit : null,
                icon: const Icon(Icons.check),
                label: const Text('Book'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
