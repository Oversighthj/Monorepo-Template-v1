import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../core/network/api_client.dart';

class LoginPage extends StatefulWidget {
  final ApiClient? apiOverride;
  const LoginPage({super.key, this.apiOverride});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  late final ApiClient _client;
  String _email = '';
  String _password = '';
  String? _error;

  @override
  void initState() {
    super.initState();
    _client = widget.apiOverride ?? ApiClient();
  }

  Future<void> _submit() async {
    final token = await _client.login(_email, _password);
    if (token != null) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('auth_token', token);
      _client.setToken(token);
      if (mounted) Navigator.of(context).pop(true);
    } else {
      setState(() => _error = 'Invalid credentials');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Login')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                onChanged: (v) => _email = v,
                decoration: const InputDecoration(labelText: 'Email'),
              ),
              TextFormField(
                onChanged: (v) => _password = v,
                obscureText: true,
                decoration: const InputDecoration(labelText: 'Password'),
              ),
              if (_error != null)
                Text(_error!, style: const TextStyle(color: Colors.red)),
              ElevatedButton(onPressed: _submit, child: const Text('Login')),
            ],
          ),
        ),
      ),
    );
  }
}