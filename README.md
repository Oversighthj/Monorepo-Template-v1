# Monorepo Template · Spring Boot + Flutter + OpenAPI

[![CI Build](https://github.com/Oversighthj/Monorepo-Template/actions/workflows/ci.yaml/badge.svg?branch=main)](https://github.com/Oversighthj/Monorepo-Template/actions)

**Monorepo Template** is a starter project combining a **Spring Boot 3** backend and a **Flutter 3** frontend, unified by a shared **OpenAPI** contract. This template provides a fully working example of a monorepo architecture where the API specification is the single source of truth for both backend and frontend. By using OpenAPI-driven development, it ensures that your client and server are always in sync. It comes with a CI pipeline, code generation scripts, and a sample feature implementation to illustrate best practices. **Developers can fork this repository as a base** for their own projects, saving time on setup and focusing on features.

&#x20;**Figure:** The Flutter frontend (`pilot_app`) running on desktop, showing a simple list of features (here just one sample entry). This UI is powered by data from the Spring Boot backend via the generated API client. For demonstration, the template includes minimal example modules (e.g. a sample user and booking domain) to use as a starting point. You can replace or extend these with your own modules and endpoints while retaining the provided project structure and integrations.

## Tech Stack

* **Backend:** Spring Boot 3 (Java 17) with Spring Web MVC, Spring Validation, and Actuator. Uses Lombok and MapStruct for boilerplate reduction. The backend includes Springdoc OpenAPI for interactive API docs (Swagger UI), and is pre-configured with Flyway (for database migrations), Testcontainers (for integration tests), Jacoco (for test coverage), and Spotless (code formatting).
* **API Contract:** OpenAPI 3 specification (YAML) defines all REST endpoints and data models. The spec is used to auto-generate server interface stubs and a TypeScript-like Dart API client library, ensuring both sides adhere to the same contract. OpenAPI Generator v6.6.0 is used under the hood for code generation.
* **Frontend:** Flutter 3.x (Dart SDK ≥ 3.3) for cross-platform UI. Follows a feature-driven folder structure with `Riverpod` for state management and `GoRouter` for navigation. Uses `Dio` for HTTP requests and a generated Dart client (built on `dio` and `built_value` models) to interact with the backend. Employs `get_it` for dependency injection and `Freezed`/`json_serializable` for data classes and serialization. The Flutter app is set up with unit tests and follows Flutter’s recommended best practices.

## Project Architecture

**Monorepo Structure:** The repository is organized into distinct parts for backend, frontend, and shared code, all in one repo for convenience:

* **Backend – Spring Boot Service** (`services/backend`): A self-contained Spring Boot project (Maven-based) that exposes RESTful APIs defined in the OpenAPI spec. The OpenAPI YAML file resides here at `services/backend/src/main/resources/openapi.yaml`. During the build, the OpenAPI Generator plugin auto-generates Java interfaces and models for the API (into `target/generated-sources/openapi`). The developer implements the API business logic by implementing these interfaces (or by writing controllers that fulfill the contract). For example, if the spec defines a `GET /feature` endpoint, the build will generate an interface (e.g. `FeatureApi`) with a method like `getFeatures()`, and a corresponding model class `FeatureDTO`. You then create a concrete Spring component (or use the provided stub) to handle the request (e.g. via a `@RestController` or service class). The backend is a typical Spring Boot app: it can be run standalone (it listens on port 8080 by default) and it’s configured for profile-based settings (e.g. see `SPRING_PROFILES_ACTIVE=dev` in Docker setup).

* **Frontend – Flutter App** (`pilot_app`): A Flutter project that provides the user interface. It’s organized by feature modules (for example, you might have a `user` feature or a `booking` feature as in the sample). The Flutter app does not hardcode REST calls; instead, it uses a **generated Dart API client** to communicate with the backend. This client is provided by the shared package `template_api`. The app obtains an instance of an `ApiClient` (wrapping Dio) pointing to the backend’s base URL (configured as `http://localhost:8080` by default in `service_locator.dart`). All network calls go through this client, e.g., calling `client.get('/feature')` to fetch a list of features. The UI layer uses Riverpod state notifiers and providers to manage asynchronous data (loading, success, error states) from these API calls. The Flutter app included in the template is set up for web, mobile, or desktop deployment, and includes some example pages (e.g., a feature list page and a form page) to demonstrate usage of the API client and state management. (The screenshot above shows one of these sample pages.)

* **Shared API Client – Dart Package** (`packages/template_api`): A Dart library generated from the OpenAPI spec, which the Flutter app depends on. This package (`template_api`) is produced by OpenAPI Generator using the **`dart-dio`** client generator. It contains Dart models (using built\_value or JSON serialization) and API classes that correspond to the backend endpoints. For instance, if the OpenAPI spec defines a `FeatureDTO` schema and a `/feature` endpoint, the `template_api` package will have a Dart class `FeatureDto` and a `DefaultApi` (or similarly named) class with methods like `getFeatures()` to call the API. The Flutter app adds `template_api` as a dependency (via a path reference in `pubspec.yaml`), so you can simply import and use these generated types and methods in your Flutter code. This ensures type-safe, OpenAPI-consistent calls from Dart to the Java backend. Whenever the OpenAPI spec changes, this package should be regenerated (see **Usage Guide** below) so that the frontend automatically adapts to the new API contract.

* **Infrastructure & CI:** The repository also includes supportive infrastructure:

  * Docker configuration: a `docker-compose.yml` that can build and run the backend service container (and optional services like a database). The backend’s Dockerfile is configured to run the Spring Boot app on port 8080. This allows easy deployment or testing of the backend without installing Java locally.
  * GitHub Actions CI: a workflow (`.github/workflows/ci.yaml`) runs on each push, which **builds and tests both the backend and frontend**. The CI pipeline will execute the OpenAPI generation script and verify that both projects compile and all tests pass. This ensures that any changes to the API spec or code are validated. The status badge at the top of this README reflects the CI build status.

By structuring the project in this way, **the OpenAPI spec becomes the central contract** that ties the backend and frontend together. You define an API once, and both sides get updated code to interact with it. This reduces errors and duplication, and makes it easier to extend the application with new features.

## Getting Started

Below are instructions for running the template application. You can choose to run everything via Docker (containerized backend) or run the backend and frontend manually on your development machine. In both cases, ensure you have the required tools installed (see **Prerequisites**).

### Prerequisites

* **Docker & Docker Compose** (if you plan to use the Docker-based run). Ensure you have Docker installed and the `docker-compose` command available.
* **JDK 17+** (if running the backend manually). The Spring Boot service requires Java 17 or higher. If you don’t have Java installed, you can use the included Maven Wrapper which will download Maven, but a JDK is still needed to run the app.
* **Flutter SDK 3.10+** (if running the frontend). Install Flutter from [flutter.dev](https://docs.flutter.dev/get-started/install) and set it up for your target platform (mobile or desktop). The Flutter project has been tested with Flutter 3 and Dart 3.
* **Dev Tools:** Additionally, if you plan to modify or extend the code, having an IDE (IntelliJ, VS Code, Android Studio) with the Flutter and Lombok plugins will be helpful.

### Running with Docker Compose (Quick Start)

Using Docker Compose is the fastest way to spin up the backend without touching Java or Maven directly:

1. **Build and Start Backend Container:** From the root of the repository, run:

   ```bash
   docker-compose up --build
   ```

   This will build the Docker image for the Spring Boot service (using the Dockerfile in `services/backend`) and start a container. The backend service will listen on port **8080** (exposed to your host machine). The Spring profile "dev" is activated inside the container by default. You should see the Spring Boot startup logs in the console after a short while.

2. **Run the Flutter App:** Docker Compose does not containerize the Flutter UI (since Flutter is typically run on your local device/emulator for development). To launch the Flutter app, open a new terminal on your host machine and run:

   ```bash
   cd pilot_app
   flutter pub get
   flutter run
   ```

   This will compile and launch the Flutter application. You might need to specify a device with `-d` if you have multiple targets (for example, use `-d chrome` for Flutter Web, `-d windows`/`-d linux` for desktop, or pick an attached mobile device). By default, the app tries to connect to `http://localhost:8080` (the backend). If you’re running the Flutter app on a different host or an Android emulator, see the note below. When the app starts, you should see the sample UI (like the **Features** list in the screenshot above) and be able to trigger API calls to the backend.

3. *(Optional)* **Swagger UI:** With the backend running, you can access the auto-generated API docs at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) (or a similar URL, depending on Springdoc configuration) to explore the available endpoints. The template’s OpenAPI spec is loaded there for interactive testing.

> **Note:** If you run the Flutter app on an Android emulator, the hostname **localhost** in the app’s API base URL will not refer to your host machine. In that case, set the base URL to **[http://10.0.2.2:8080](http://10.0.2.2:8080)** (10.0.2.2 is a special IP that points to host PC from Android emulator). You can change this in `pilot_app/lib/core/di/service_locator.dart` when registering the `ApiClient`. For a real device connected via USB, use your PC’s IP address on the network. This ensures the Flutter app can reach the backend container.

### Running Manually (Local Backend & Frontend)

If you prefer not to use Docker, you can run both the backend and frontend directly on your machine:

**1. Start the Backend (Spring Boot):**
Open a terminal and navigate to the backend directory:

```bash
cd services/backend
./mvnw clean spring-boot:run
```

This uses the Maven Wrapper to compile and launch the Spring Boot application. It will run on port 8080 by default. Maven will also regenerate any API interfaces from the OpenAPI spec during the build phase (thanks to the configured plugin). Once you see “Started TemplateApplication” in the console, the backend is up. (Alternatively, you can run `./mvnw package` to build the JAR, then run the JAR with `java -jar` in `services/backend/target`.)

**2. Start the Frontend (Flutter):**
In a second terminal, navigate to the Flutter app directory:

```bash
cd pilot_app
flutter pub get            # install Flutter dependencies, including template_api
flutter pub run build_runner build --delete-conflicting-outputs   # generate any missing code (if needed)
flutter run
```

Ensure you have a simulator or device ready (or use `-d` to specify one). The Flutter app will launch and connect to the backend at `localhost:8080`. As with the Docker approach, if you want to use a mobile emulator, adjust the base URL to 10.0.2.2 as described above. The `build_runner` step will generate code for any `*.g.dart` or other build-runner tasks (for example, the `template_api` package uses built\_value, so running this ensures all serializer code is up to date). Now you can interact with the app and it will talk to the local Spring Boot server.

**3. Testing:** Both backend and frontend come with tests. You can run backend tests with `./mvnw test` (in `services/backend`) which will also produce a test coverage report (see `services/backend/target/site/jacoco` after running). For the Flutter app, run `flutter test` in `pilot_app` to execute the Dart unit/widget tests. The CI pipeline runs these tests automatically on each commit.

---