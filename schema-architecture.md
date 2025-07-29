This Spring Boot application follows a hybrid architecture, combining both MVC and RESTful design patterns to cater to different user needs. 
The Admin and Doctor interfaces are built using Thymeleaf templates, enabling server-side rendered views and interactions for managing appointments, viewing patient data, and generating reports.
For other modules such as patient registration, login, and prescription retrieval, the application exposes REST APIs to allow client-side or third-party access.

The backend interacts with two databases: MySQL and MongoDB. MySQL stores structured relational data such as patient records, doctor profiles, appointment schedules, and admin details, and is accessed through JPA-based repositories using entity models.
MongoDB, on the other hand, is used to store unstructured or semi-structured data like prescription documents, which are managed via Spring Data MongoDB using document-oriented models. All controller requests, whether MVC or REST, are routed through a unified service layer that encapsulates the business logic and ensures separation of concerns. 
This layered architecture ensures maintainability, scalability, and a clear division between presentation, logic, and data access.

STEPS INVOLVED:

1. Users interact with the system via either web dashboards (Thymeleaf) or REST API clients (like mobile apps).

2. Controllers receive user requests and route them appropriately—Thymeleaf controllers for HTML views and REST controllers for JSON responses.

3. Controllers delegate processing to the Service Layer, which applies business logic like validations and workflow coordination.

4. The Service Layer communicates with the Repository Layer to fetch or store data.

5. Repositories use Spring Data JPA for MySQL (structured data) and Spring Data MongoDB for MongoDB (flexible document data).

6. Data is retrieved and mapped into Java model classes—JPA entities for MySQL and documents for MongoDB.

7. The processed data is sent back as a dynamic web page (via Thymeleaf) or JSON response (via REST API), completing the request-response cycle.
