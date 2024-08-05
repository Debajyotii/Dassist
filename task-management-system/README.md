# Task Management System Backend

The Task Management System backend is a standalone application designed to handle the logic and data management for
managing tasks. It provides a set of APIs to perform CRUD operations on tasks and includes authentication,
authorization, validation, and other features.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **JAVAX Validation**
- **H2 Database**
- **Maven**
- **Swagger**

## TASK API Endpoints

- `GET /taskmanager/task` :**Description:** Fetch all tasks with **pagination**.
- `POST /taskmanager/task`:**Description:** Create a new task.
- `GET /taskmanager/task/{id}` :**Description:** Fetch a task by ID.
- `PUT /taskmanager/task/{id}` :**Description:** Update a task by ID.
- `DELETE /taskmanager/task/{id}` :**Description:** Delete a task by ID.
- `DELETE /taskmanager/task/deleteAll` :**Description:** Delete all tasks (**Admin** only).
- `GET /taskmanager/task/search` :**Description:** Search tasks with specified filters.

## Auth API Endpoints

- `GET /taskmanager/auth/login` :**Description:** To authenticate.
- `POST /taskmanager/auth/token/refresh`:**Description:** To generate refresh token.

## Auth API Endpoints

- `GET /taskmanager/user/register` :**Description:** Register new user.

## Global Exception Handling

These are handled as of now:

- **MethodArgumentNotValidException**: Validation errors (400 BAD REQUEST).
- **HttpMessageNotReadableException**: Incorrect message format (400 BAD REQUEST).
- **FailedLoginException**: Login failures (403 FORBIDDEN).
- **AuthorizationDeniedException**: Unauthorized access (403 FORBIDDEN).
- **NoResourceFoundException**: Resource not found (400 BAD REQUEST).
- **CustomException**: Custom application errors.
- **HttpRequestMethodNotSupportedException**: Incorrect HTTP method (405 METHOD NOT ALLOWED).
- **HttpMediaTypeNotSupportedException**: Unsupported media type (415 UNSUPPORTED MEDIA TYPE).
- **General Exception**: Other unhandled exceptions (500 INTERNAL SERVER ERROR).

## Email Scheduler

A Spring scheduler integrated with a demo cron that runs at 8 AM every day. It checks tasks and sends emails to the
respective user. Configure the following in `application.properties`:

```properties
spring.mail.username=email@example.com
spring.mail.password=xxx
spring.mail.host=smtp domain
spring.mail.port=port number
```

### Build and Deployment

```
mvn clean install
mvn spring-boot:run
```

### Screenshots

```
Sample screenshots provided. Present in screenshots folder inside resource directory.
```

### Swagger UI

Swagger Integrated for API documentation.
[Swagger UI](http://localhost:8080/swagger-ui/index.html)
. Task endpoints needs to be authorised with generated token in Swagger UI.

### SQL Details

- Refer schema.sql for H2 table schemas.
- Application would be available with seeded data. Please Refer data.sql. 
