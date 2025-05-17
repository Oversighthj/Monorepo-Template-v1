# Usage Guide

This guide covers advanced usage of the Monorepo Template, including how to update the API contract, generate client code, and extend the project with new endpoints and features. It assumes you have the project up and running (see the README for initial setup). Developers looking to customize this template for their own needs should follow these practices to ensure the backend and frontend remain in sync.

## Regenerating the API Client (OpenAPI Workflow)

One of the key benefits of this template is the **single OpenAPI spec** that defines the contract. Whenever you modify the API (e.g., add a new endpoint or change a data model), you should update the OpenAPI spec and then regenerate the code to reflect those changes.

**Location of OpenAPI Spec:**
The file [`openapi.yaml`](services/backend/src/main/resources/openapi.yaml) in the backend’s resources directory is the source of truth for the API. It contains definitions for paths, request/response schemas (DTOs), and any components like security schemes. For example, it might define a path `/feature` and a schema `FeatureDTO` for feature data.

**How the Backend uses the Spec:**
On the Java side, the Maven build is configured to use the OpenAPI Generator Maven plugin. At build time (generate-sources phase), it reads `openapi.yaml` and generates Java interfaces and models under `target/generated-sources/openapi`. This means if you add a new path or model, running a Maven compile will update the generated interfaces. You can then implement the new interface or controller. (If you prefer, you can also run the generator plugin manually via `./mvnw openapi-generator:generate`.) The generated code typically includes:

* **API Interfaces** (in package `com.example.app.api`): e.g., `FeatureApi` with method signatures like `ResponseEntity<List<FeatureDTO>> getFeatures()` corresponding to the spec.
* **Model Classes** (in package `com.example.app.model`): e.g., `FeatureDTO` with fields `id`, `name`, `description` matching the spec schema.
* These are used by the Spring Boot app at runtime (for example, Spring will auto-wire an implementation of `FeatureApi` if you have one, to handle the `/feature` route).

**How the Frontend uses the Spec:**
The Dart client (`template_api` package) must also be regenerated when the spec changes. We provide a convenience script to do this:

```bash
./scripts/generate_api_client.sh
```

This script runs the OpenAPI Generator CLI (via Docker) to generate a Dart client with the `dart-dio` generator. It reads the same `openapi.yaml` from the backend and outputs the Dart code into `packages/template_api`. **You should run this script whenever you change the spec.** After running it, do the following to integrate the changes:

* Open `packages/template_api/pubspec.yaml` and verify the version/name if needed (by default it’s a local package named “template\_api”). Run `flutter pub get` in the `packages/template_api` directory to fetch any new dependencies that the generated code might require.
* Run the Dart build runner to generate any serialization code. The OpenAPI generator may produce classes that use `built_value` (with `.g.dart` parts) or `json_serializable`. To handle these, run:

  ```bash
  cd packages/template_api
  flutter pub run build_runner build --delete-conflicting-outputs
  ```

  This will generate any missing part files (e.g., serializers or built\_value generated code).
* Finally, run `flutter pub get` in `apps/guest_app` as well, to ensure the app picks up the updated `template_api` package (if the package version or content changed, the path dependency will be refreshed).

After these steps, the Flutter app’s code can immediately use the new API calls or models defined in the updated spec. For example, if you added a `/feature` endpoint, the `template_api` might now have a new method `DefaultApi().getFeatures()` and a model class `FeatureDto` to use in your Dart code.

## Adding a New Endpoint or Feature

To illustrate how you would extend this template, let’s walk through adding a hypothetical new feature called “Feature” (as an example). The same general steps apply for any new endpoint or data model you want to add:

* **Step 1: Define the API in OpenAPI spec (contract first).**
  Edit the OpenAPI spec file (`services/backend/src/main/resources/openapi.yaml`) to add your new endpoint path and any schemas it uses. For example, to add a `GET /api/feature` endpoint that returns a list of Feature objects, you would add under `paths` something like:

  ```yaml
  /feature:
    get:
      operationId: getFeatures
      tags:
        - feature
      responses:
        '200':
          description: List of features
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/FeatureDTO'
  ```

  And under `components.schemas`, define the `FeatureDTO` schema:

  ```yaml
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

  This formally declares what the request/response will look like. Commit this change or save it.

* **Step 2: Generate server stubs and client SDK.**
  Run a backend build to generate updated stubs: `./mvnw compile` (or simply start the app, since Maven will run the generation plugin). The Maven plugin will detect changes in `openapi.yaml` and produce updated interfaces and models in `target/generated-sources/openapi`. In our example, you’d get a new `FeatureApi.java` interface with a method `getFeatures()`, and a `FeatureDTO.java` model class.
  Next, run the API client generator script: `./scripts/generate_api_client.sh`. This will regenerate the Dart client code under `packages/template_api` for the new endpoint. Don’t forget to run `flutter pub get` and `flutter pub run build_runner build` in that package as described above, to incorporate any new models (e.g., a Dart class for `FeatureDto`). The Flutter app’s pubspec already references this package, so it will now have access to the new API call (e.g., `Api().getFeatures()` or similar).

* **Step 3: Implement the backend logic.**
  With the interface in place, you need to provide an implementation so that the backend actually returns real data. You have a couple of options:

  1. **Implement the interface directly:** The OpenAPI generator may produce an interface (e.g., `FeatureApi`) or an abstract controller. You can create a class like `FeatureApiController` that implements `FeatureApi` and annotate it with `@RestController` (Spring will then route `/feature` to this implementation). For example, you might autowire a `FeatureService` and in `getFeatures()` simply return `ResponseEntity.ok(featureService.getAll())`. The template is configured with `interfaceOnly=true` for generation, so providing your own controller is expected. (If a default stub controller is generated, you can also locate it in `target/generated-sources` and copy it to the main source folder as a starting point.)
  2. **Service and repository layer:** In our example, you might create a JPA entity `Feature` (e.g., in `com.example.app.feature.Feature`), a Spring Data JPA repository `FeatureRepository` for database access, and a service `FeatureService` containing business logic (e.g., to fetch and create features). You can use MapStruct to map between the JPA entity and the OpenAPI-generated `FeatureDTO` class for consistency. The template already includes Lombok and MapStruct, so you can quickly create these classes with minimal code.
     Don’t forget to update the security configuration if your new endpoint needs role-based protection (see `SecurityConfig`, where you can add a rule for your `/feature` path). Also consider adding unit tests for your new service or controller.

* **Step 4: Use the new endpoint in the Flutter app.**
  With the backend providing data and the `template_api` Dart client updated, you can now call the new API from Flutter. Typically, you’d create a new “feature” module in the `apps/guest_app/lib/features` directory (analogous to the existing examples like `user` or `transport`). For instance:

  * Create a Dart model class if needed (though you might use the generated `FeatureDto` from `template_api` directly, or define a simpler app-specific model). The template demonstrates both approaches – in some cases, you might use `template_api` models directly, or use `json_serializable` to define Flutter-side models.
  * Set up a data source or repository class to call the API. For example, a `FeatureRemoteDataSource` that calls `ApiClient.get('/feature')` or uses the generated client method. In our sample, the data source might call `templateApi.getFeatures()` and convert the result to your Flutter `FeatureModel`.
  * Use state management to expose this data to the UI. You can create a `FeatureController` (a Riverpod StateNotifier) that holds an async state (list of features) and fetches data on initialization. The controller would use the repository to load data (and possibly to post new data). The template’s `FeatureController` example (from the developer guide) shows loading data in the constructor and how to handle loading/error states.
  * Build UI screens to display the data. For example, a `FeatureListPage` showing a list of features and a `FeatureFormPage` to add a new feature. You can see a pattern for list/detail pages in the template’s sample pages (like the User list page, etc.). Using Riverpod’s `ref.watch`, the UI can react to the `FeatureController` state. In our example, you’d show a loading spinner while `AsyncValue<List<FeatureModel>>` is loading, display the list when data is available, and handle errors accordingly.
  * Wire up navigation routes for the new pages (update `app_router.dart` with a route for `/feature` and perhaps `/feature-form`), and register any new providers or singletons in the service locator (`service_locator.dart`) so that the new feature’s repository and datasource are available via `get_it`.

After following these steps, your new **Feature** functionality would be fully integrated: you can define a new endpoint, have the backend serve it, and immediately use it in the frontend with minimal manual coding thanks to the OpenAPI-generated client. The key is to always keep the OpenAPI spec as the up-to-date contract and regenerate as needed. The provided example modules (such as the sample “user” feature or the placeholders in the template) can serve as references for how to structure your code.

## Tips for Forking and Customization

* **Renaming the Project:** If you fork this repository for your own project, you’ll likely want to change package names, remove the sample features, and adjust branding. The Java backend’s base package is `com.example.app` (and sometimes `com.example.demo` in placeholders) – you should replace this with your own domain (e.g., `com.mycompany.myapp`). Similarly, rename the Flutter app (currently `apps/guest_app`) to your app’s name and update its `pubspec.yaml` (name, bundle identifier, etc.). Search the codebase for “example” or “template” to find placeholders to replace. Renaming can be done manually or by using the provided `scripts/apply_changes.py` which can batch-apply name changes as per a config (advanced usage).
* **Removing Sample Code:** The template includes some sample domain logic (such as a stubbed booking/payment feature and an auth stub, as seen in the codebase) to illustrate patterns. You can safely delete or replace these with your own features. Just ensure to remove their routes from the OpenAPI spec if you’re not using them, and regenerate the code to avoid leftover references.
* **Adding a Database:** The Docker Compose file has an example PostgreSQL service commented out. If your feature needs a database, you can uncomment and adjust this. The backend is already set up with Spring Data JPA and Flyway, so you can write entities and repositories. Update `application-dev.yaml` (or equivalent config) with the DB connection info and add Flyway migration scripts in `resources/db/migration` if needed.
* **API Versioning and URL Prefix:** By default, the OpenAPI spec and controllers use paths like `/feature`. In a real project, you might want them under a versioned API prefix, e.g., `/api/v1/feature`. You can adjust this in the OpenAPI spec (add a `servers` block with a base path) or via Spring configuration. The template doesn’t enforce a prefix, but it’s a good practice to add before going to production.
* **CI/CD Adjustments:** The included GitHub Actions workflow runs tests and builds. You might want to add steps to deploy your app (for example, build a Docker image and push to a registry, or publish the Flutter app). Also, update the badge URLs if you rename the repository. The status badge in the README is currently pointing to the `main` branch of this template’s Actions workflow. After forking, update the Markdown to use your repository’s URL.

By following this usage guide and the architecture principles outlined, you can confidently develop a full-stack application with a consistent API contract. The **Monorepo Template** is designed to eliminate boilerplate and setup time – you get a ready-made integration between Spring Boot and Flutter, so you can focus on writing features. Happy coding!



```bash
# (optional) create and activate a virtual environment
python -m venv .venv && source .venv/bin/activate

# install requirements
pip install -r requirements.txt

# copy & edit config
cp config.example.yml config.yml
#   ↳ edit include/exclude/backup_retention as needed

# dry-run a patch
python scripts/apply_changes.py patches/foo.diff

# real apply + structure update + tests
python scripts/apply_changes.py patches/foo.diff \
       --run --confirm --update-structure --run-tests --verbose
