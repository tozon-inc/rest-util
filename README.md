The project is a Java-based utility for making RESTful API calls using the OkHttp library. It provides a `RestClient` class that simplifies the process of sending HTTP requests and handling responses. The project is built using Maven and includes dependencies for Gson, OkHttp, and JUnit for testing.

### Key Features:
1. **REST Client**:
   - The `RestClient` class supports GET, POST, PUT, and DELETE HTTP methods.
   - It uses OkHttp for making HTTP requests and Gson for JSON serialization and deserialization.
   - The client is configurable with a base URL and custom headers.

2. **Logging**:
   - HTTP request and response logging is enabled using OkHttp's `HttpLoggingInterceptor`.

3. **Testing**:
   - Unit tests for the `RestClient` class are provided using JUnit 5.

### Project Structure:
- **Main Code**:
  - `src/main/java/io/tozon/restutils/RestClient.java`: Contains the `RestClient` class and its builder.
- **Tests**:
  - `src/test/java/io/tozon/restutils/RestClientTest.java`: Contains unit tests for the `RestClient` class.
- **Configuration**:
  - `pom.xml`: Maven configuration file with dependencies and build settings.
- **Git Ignore**:
  - `.gitignore`: Specifies files and directories to be ignored by Git.

### Build and Distribution:
- The project uses Maven for build automation.
- The Maven Assembly Plugin can be configured to create a distributable package (e.g., a ZIP file) containing the compiled JAR, resources, and configuration files.
