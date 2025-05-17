# دليل المطور: إضافة ميزة جديدة من 0 إلى 100%

هذا الدليل يوضح بالتفصيل كامل الخطوات والملفات التي يجب إنشاؤها أو تعديلها عند إضافة ميزة (Feature) جديدة في المشروع، سواء في **الـ Backend** (Spring Boot) أو **الFrontend** (Flutter). ستجد مثالاً عامًا باسم `feature` يمكنك استبداله باسم الميزة الفعلي.

---

## 1. Backend (Spring Boot)

### 1.1 تحديث مواصفة OpenAPI

- **المسار**: `demo/openapi.yaml`
- **ماذا تفعل**: أضف قسمًا جديدًا تحت `paths` و`components.schemas` لتعريف endpoints وDTOs الخاصة بالميزة.

```yaml
paths:
  /feature:
    get:
      operationId: getFeatures
      tags:
        - feature
      responses:
        '200':
          description: قائمة الميزات
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/FeatureDTO'
components:
  schemas:
    FeatureDTO:
      type: object
      properties:
        id:
          type: integer
        name:
          type: string
        description:
          type: string
```

### 1.2 تكوين OpenAPI Generator في `pom.xml`

- **المسار**: `demo/pom.xml`
- تحت `<build><plugins>...</plugins></build>`، أضف:

```xml
<plugin>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-generator-maven-plugin</artifactId>
  <version>6.6.0</version>
  <executions>
    <execution>
      <goals>
        <goal>generate</goal>
      </goals>
      <configuration>
        <inputSpec>${project.basedir}/openapi.yaml</inputSpec>
        <generatorName>spring</generatorName>
        <apiPackage>com.example.demo.api</apiPackage>
        <modelPackage>com.example.demo.model</modelPackage>
        <invokerPackage>com.example.demo.invoker</invokerPackage>
        <configOptions>
          <useSpringBoot3>true</useSpringBoot3>
          <useJakartaEe>true</useJakartaEe>
        </configOptions>
      </configuration>
    </execution>
  </executions>
</plugin>
```

### 1.3 توليد الكود والتحقق

```bash
cd demo
./mvnw clean generate-sources compile
```
- **التحقق**: تأكد من وجود الملفات المولدة في `target/generated-sources/openapi` مثل:
  - `model/FeatureDTO.java`
  - `api/FeatureApi.java`
  - `api/FeatureApiController.java`

### 1.4 إضافة كيان Entity

- **المسار**: `demo/src/main/java/com/example/demo/feature/Feature.java`
- مثال:

```java
package com.example.demo.feature;

import jakarta.persistence.*;

@Entity
@Table(name = "FEATURES")
public class Feature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;

  // Constructors, getters, setters
}
```

### 1.5 إضافة Repository

- **المسار**: `demo/src/main/java/com/example/demo/feature/FeatureRepository.java`

```java
package com.example.demo.feature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
```

### 1.6 إضافة Mapper (MapStruct)

- **المسار**: `demo/src/main/java/com/example/demo/feature/FeatureMapper.java`

```java
package com.example.demo.feature;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
  FeatureDTO toDto(Feature entity);
  Feature toEntity(FeatureDTO dto);
}
```

### 1.7 إضافة Service

- **المسار**: `demo/src/main/java/com/example/demo/feature/FeatureService.java`

```java
package com.example.demo.feature;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeatureService {
  private final FeatureRepository repo;
  private final FeatureMapper mapper;

  public FeatureService(FeatureRepository repo, FeatureMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  public List<FeatureDTO> getFeatures() {
    return repo.findAll().stream()
      .map(mapper::toDto)
      .toList();
  }

  public FeatureDTO createFeature(FeatureDTO dto) {
    Feature saved = repo.save(mapper.toEntity(dto));
    return mapper.toDto(saved);
  }
}
```

### 1.8 تخصيص Controller (اختياري)

- إن أردت تخصيص سلوكك بدل المولد، انسخ `FeatureApiController.java` من `target/generated-sources` إلى:
  `demo/src/main/java/com/example/demo/api/FeatureApiController.java` وعدله.

### 1.9 تحديث الأمان (SecurityConfig)

- **المسار**: `demo/src/main/java/com/example/demo/security/SecurityConfig.java`
- مثال إضافة endpoint جديد للصلاحيات:

```java
http
  .authorizeHttpRequests()
    .requestMatchers("/api/feature/**").hasRole("ADMIN")
    .anyRequest().authenticated();
```

### 1.10 إضافة اختبارات الوحدة

- **المسار**: `demo/src/test/java/com/example/demo/feature/FeatureServiceTest.java`
- مثال:

```java
@SpringBootTest
public class FeatureServiceTest {
  @Autowired
  FeatureService service;

  @Test
  void testGetFeatures() {
    List<FeatureDTO> list = service.getFeatures();
    assertNotNull(list);
  }
}
```

---

## 2. Frontend (Flutter)

### 2.1 تعريف نموذج البيانات (Model)

- **المسار**: `pilot_app/lib/features/feature/models/feature_model.dart`

```dart
import 'package:json_annotation/json_annotation.dart';

part 'feature_model.g.dart';

@JsonSerializable()
class FeatureModel {
  final int id;
  final String name;
  final String description;

  FeatureModel({required this.id, required this.name, required this.description});

  factory FeatureModel.fromJson(Map<String, dynamic> json) => _$FeatureModelFromJson(json);
  Map<String, dynamic> toJson() => _$FeatureModelToJson(this);
}
```

- ثم:

```bash
cd pilot_app
flutter pub run build_runner build --delete-conflicting-outputs
```

### 2.2 إضافة Data Source

- **المسار**: `pilot_app/lib/features/feature/data/remote/feature_remote_data_source.dart`

```dart
import '../../models/feature_model.dart';
import '../../../core/api_client.dart';

class FeatureRemoteDataSource {
  final ApiClient client;
  FeatureRemoteDataSource(this.client);

  Future<List<FeatureModel>> fetchFeatures() async {
    final resp = await client.get('/feature');
    return (resp as List).map((e) => FeatureModel.fromJson(e)).toList();
  }

  Future<FeatureModel> createFeature(FeatureModel f) async {
    final resp = await client.post('/feature', data: f.toJson());
    return FeatureModel.fromJson(resp);
  }
}
```

### 2.3 إضافة Repository

- **المسار**: `pilot_app/lib/features/feature/data/repository/feature_repository_impl.dart`

```dart
import '../remote/feature_remote_data_source.dart';
import '../../models/feature_model.dart';

class FeatureRepositoryImpl implements FeatureRepository {
  final FeatureRemoteDataSource remote;
  FeatureRepositoryImpl(this.remote);

  @override
  Future<List<FeatureModel>> getFeatures() => remote.fetchFeatures();

  @override
  Future<FeatureModel> addFeature(FeatureModel f) => remote.createFeature(f);
}
```

### 2.4 إضافة Controller / StateNotifier

- **المسار**: `pilot_app/lib/features/feature/presentation/controllers/feature_controller.dart`

```dart
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../data/repository/feature_repository_impl.dart';
import '../../models/feature_model.dart';

final featureControllerProvider = StateNotifierProvider<FeatureController, AsyncValue<List<FeatureModel>>>((ref) {
  final repo = ref.read(featureRepositoryProvider);
  return FeatureController(repo);
});

class FeatureController extends StateNotifier<AsyncValue<List<FeatureModel>>> {
  final FeatureRepository repo;
  FeatureController(this.repo) : super(const AsyncValue.loading()) {
    load();
  }

  Future<void> load() async {
    try {
      final data = await repo.getFeatures();
      state = AsyncValue.data(data);
    } catch (e) {
      state = AsyncValue.error(e);
    }
  }

  Future<void> addFeature(String name, String desc) async {
    state = const AsyncValue.loading();
    try {
      final f = await repo.addFeature(FeatureModel(id: 0, name: name, description: desc));
      load();
    } catch (e) {
      state = AsyncValue.error(e);
    }
  }
}
```

### 2.5 إنشاء الصفحات (UI)

- **List Page**: `presentation/pages/feature_list_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../controllers/feature_controller.dart';

class FeatureListPage extends ConsumerWidget {
  const FeatureListPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(featureControllerProvider);
    return Scaffold(
      appBar: AppBar(title: const Text('Features')),
      body: state.when(
        data: (list) => ListView(
          children: list.map((f) => ListTile(title: Text(f.name))).toList(),
        ),
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(child: Text('Error: $e')),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => GoRouter.of(context).push('/feature-form'),
        child: const Icon(Icons.add),
      ),
    );
  }
}
```

- **Form Page**: `presentation/pages/feature_form_page.dart` (شبيه UserFormPage)

### 2.6 تحديث التوجيه (Routing)

- **المسار**: `pilot_app/lib/routes/app_router.dart`

```dart
GoRoute(
  path: '/feature',
  builder: (context, state) => const FeatureListPage(),
),
GoRoute(
  path: '/feature-form',
  builder: (context, state) => const FeatureFormPage(),
),
```

### 2.7 تسجيل DI للميزة

- **المسار**: `pilot_app/lib/core/di/service_locator.dart`

```dart
getIt.registerLazySingleton<FeatureRemoteDataSource>(
  () => FeatureRemoteDataSource(getIt()),
);
getIt.registerLazySingleton<FeatureRepository>(
  () => FeatureRepositoryImpl(getIt()),
);
```

### 2.8 إضافة الاختبارات

- **المسار**: `test/features/feature/controller/feature_controller_test.dart`
- مثال:

```dart
import 'package:flutter_test/flutter_test.dart';
import 'package:mocktail/mocktail.dart';

// Mock Repository
class MockRepo extends Mock implements FeatureRepository {}

void main() {
  test('load features success', () async {
    final repo = MockRepo();
    when(() => repo.getFeatures()).thenAnswer((_) async => []);
    final controller = FeatureController(repo);
    await controller.load();
    expect(controller.state.hasValue, true);
  });
}
```

---

## 3. تحديث CI/CD

- أخيرًا تأكد أنّ `.github/workflows/ci.yaml` يتضمن:

```yaml
jobs:
  backend:
    # … توليد وبناء backend
  flutter:
    needs: backend
    # … تثبيت Flutter، توليد الكود، analyze، test
```

> باتباع هذا الدليل خطوة بخطوة، سيعرف أي مطوّر جديد بالضبط الملفات التي يتعين عليه إنشاؤها وكيفية ربطها معًا لإنشاء ميزة جديدة بنجاح.

