
---

# Project Management System API

This RESTful API allows users to manage projects through CRUD operations. It is built using Java 17 and Spring Boot, with an in-memory H2 database for data persistence.

## Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Prakash1233ruw/Project-Management-System-API.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Project-Management-System-API
   ```

3. **Build the project using Maven:**

   ```bash
   mvn clean package
   ```

4. **Run the application:**

   ```bash
   java -jar target/project-management-system-api.jar
   ```

   The application will start at `http://localhost:8787/ProjectManagementSystem`.

## API Endpoints

### Get All Projects

- **URL:** `/api/projectsList`
- **Method:** `GET`
- **Description:** Retrieve all projects.
- **Response:** List of projects.

### Get Project by ID

- **URL:** `/api/oneProject/{id}`
- **Method:** `GET`
- **Description:** Retrieve a project by its ID.
- **Parameters:**
  - `id` (Path parameter): ID of the project.
- **Response:** Project with the specified ID.

### Create Project

- **URL:** `/api/registerProject`
- **Method:** `POST`
- **Description:** Create a new project.
- **Request Body:** Project object with attributes (name, description,startDate,endDate).
- **Response:** Created project object.

### Update Project

- **URL:** `/api/updateProject/{id}`
- **Method:** `PUT`
- **Description:** Update an existing project.
- **Parameters:**
  - `id` (Path parameter): ID of the project to be updated.
- **Request Body:** Updated project object.
- **Response:** Updated project object.

### Delete Project

- **URL:** `/api/deleteProject/{id}`
- **Method:** `DELETE`
- **Description:** Delete a project by its ID.
- **Parameters:**
  - `id` (Path parameter): ID of the project to be deleted.
- **Response:** HTTP status 200 if successful.


## Data Validation

- Data validation is performed for create and update operations.
- Fields such as name and description are required and must not be blank.

## Dependencies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Web
- H2 Database
- Maven
- Springfox Swagger UI
- SpringDoc OpenAPI Starter WebMVC UI
- Spring Boot Starter Test

## Testing

- Unit tests are included for service layer methods.
- Integration tests are included for API endpoints.

## Documentation

- API endpoints are documented using Swagger  http://localhost:8787/ProjectManagementSystem/swagger-ui/index.html#/

---
