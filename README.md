# POC: Learning API Rest with JAVA

## Descripción
REST API developed in Java using Spring Boot. This application connects to a PostgreSQL database and allows CRUD operations to manage training data.

## Requirements
- Java 17 or higher
- Springboot 3
- Lombok
- Maven 3.6 or higher
- PostgreSQL 13 or higher

## Configuration
### `application.properties` file
In the `application.properties` file located in `src/main/resources/`, make sure to configure the following properties to connect to the PostgreSQL database:

```properties
server.port=8500
spring.application.name=apirest
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
```
Make sure you have the environment variables DB_HOST, DB_PORT, DB_NAME, DB_USER, and DB_PASSWORD set up with the correct values.

# Installation and Execution

### Clone the repository
```bash
git clone https://github.com/usuario/java-apirest-poc.git
```

# Build and run the project
1. Navigate to the root directory of the project.
2. Run the following command to compile and package the project:
```bash
mvn clean install
```
3. Inicia la aplicación:
```bash
mvn spring-boot:run
```
La aplicación estará disponible en http://localhost:8500.

# Project Structure

- `src/main/java/com/api/kimatesting/apirest/controllers`: Contains the REST controllers, like TrainingRestController, to handle HTTP requests.
- `src/main/java/com/api/kimatesting/apirest/models`: Contains the data models, like TrainingModel, which represent the database entities.
- `src/main/java/com/api/kimatesting/apirest/repositories`: Contains repository interfaces, like ITrainingRepository, that extend JpaRepository to perform CRUD operations.
- `src/main/java/com/api/kimatesting/apirest/services`: Contains business logic in TrainingService.
- `src/main/java/com/api/kimatesting/apirest/utils`: Contains utilities like ApiResponse.
- `src/main/java/com/api/kimatesting/apirest/ApirestApplication`: Main class to start the application.

# Usage

## Main Endpoints
- `GET /trainings`: Retrieves all trainings.
- `GET /trainings/{id}`: Retrieves an existing training by id.
- `POST /trainings`: Creates a new training.
- `PUT /trainings/{id}`: Updates an existing training.
- `DELETE /trainings/{id}`: Deletes a training.

Example of a POST request
```bash
curl --location 'http://localhost:8500/api/v1/trainings' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Selenium con Python",
    "icon":"empty"
}'
```
