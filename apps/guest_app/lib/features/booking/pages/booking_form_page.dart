import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class BookingFormPage extends StatefulWidget {
  final int propertyId;

  const BookingFormPage({super.key, required this.propertyId});

  @override
  State<BookingFormPage> createState() => _BookingFormPageState();
}

class _BookingFormPageState extends State<BookingFormPage> {
  DateTime? _start;
  DateTime? _end;

  final _fmt = DateFormat('yyyy-MM-dd');

  Future<void> _pickStart() async {
    final picked = await showDatePicker(
      context: context,
      firstDate: DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 365)),
      initialDate: _start ?? DateTime.now(),
    );
    if (picked != null) {
      setState(() => _start = picked);
    }
  }

  Future<void> _pickEnd() async {
    final picked = await showDatePicker(
      context: context,
      firstDate: _start ?? DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 365)),
      initialDate: _end ?? (_start ?? DateTime.now()),
    );
    if (picked != null) {
      setState(() => _end = picked);
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
              subtitle: Text(_start != null ? _fmt.format(_start!) : 'Choose…'),
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
                onPressed: (_start != null && _end != null) ? () {} : null,
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
