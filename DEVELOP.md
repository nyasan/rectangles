## Running the Application Locally

### Prerequisites
Before running this application in dev

1. JDK 11
2. Kotlin support

### Build and Run<a id="build-run"></a>

To build the application from command line, you may run the following command:
```bash
mvn clean install
```

To run the application from command line, you may run the following command after building your application:
```bash
mvn spring-boot:run
```

Run tests
```bash
./mvnw clean verify
```
Open a browser and hit http://localhost:8080/ for service specs.