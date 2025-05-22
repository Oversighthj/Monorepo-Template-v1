import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:template_api/template_api.dart' as gen;

/// بطاقة لعرض معلومات حجز في تطبيق الإدارة
class BookingCard extends StatelessWidget {
  final gen.BookingDTO booking;
  const BookingCard({super.key, required this.booking});

  @override
  Widget build(BuildContext context) {
    final fmt = DateFormat('yyyy-MM-dd');

    return Card(
      child: ListTile(
        leading: const Icon(Icons.event_note),
        title: Text('Booking #${booking.id}'),
        subtitle: Text(
          '${fmt.format(booking.startAt)} → ${fmt.format(booking.endAt)}\n'
          'Property: ${booking.propertyId} • User: ${booking.userId}',
        ),
        isThreeLine: true,
        trailing: Text(
          booking.status.name,
          style: const TextStyle(fontWeight: FontWeight.bold),
        ),
      ),
    );
  }
}
