import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:template_api/template_api.dart' as gen;

/// بطاقة تعرض مهمة تنظيف في تطبيق العامل
class TaskCard extends StatelessWidget {
  final gen.TaskDTO task;
  const TaskCard({super.key, required this.task});

  @override
  Widget build(BuildContext context) {
    final fmt = DateFormat('yyyy-MM-dd HH:mm');

    return Card(
      child: ListTile(
        leading: const Icon(Icons.cleaning_services_outlined),
        title: Text('Task #${task.id} • ${task.status.name}'),
        subtitle: Text(
          'Booking: ${task.bookingId}\n'
          'Cleaner: ${task.cleanerId}\n'
          'Due: ${fmt.format(task.due)}',
        ),
        isThreeLine: true,
        trailing: Text(task.type),
      ),
    );
  }
}
