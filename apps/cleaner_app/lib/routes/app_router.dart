// cleaner_app/lib/routes/app_router.dart
import 'package:go_router/go_router.dart';
import '../features/task/pages/task_list_page.dart';

final router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (_, __) => const TaskListPage(),
    ),
  ],
);
