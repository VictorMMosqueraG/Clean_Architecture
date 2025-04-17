# Clean architecture
This repository is intended to study clean architecture and learn how to apply it in future projects.

To set up run this project, follow these steps:

## Table of Contents

- [Backend Setup and Dependencies](#backend-setup-and-dependencies)
- [Backend Architecture Breakdown](#backend-architecture-breakdown)

## Backend Setup and Dependencies

1. **Docker Setup**: Ensue that docker is installed on your system. Docker will be used to manage the database container for the development environment. If you don't have Docker installed, you can download and install it from the docker website.

2. **Clone Repository**. Clone this project repository to your local machine using Git. You can do this by running the following command in your terminal:
    ```
    git clone https://github.com/VictorMMosqueraG/Clean_Architecture.git
    ```
3. **Install JDK**: You must install JDK 17, which to date is the one used in this repository.

4. **Install Spring Boot**: You must install Spring boot 3.2.4, witch to date is the one used in this repository.

5. **Database Setup (Development Environment)**: Run database with Docker: In the development environment, the database is manage using Docker (Point 1). To start the database container, run the following command:

    ```
    docker-compose up -d
    ```
This command is a one-time operation dedicate to creating the necessary image and container, When you wish to halt the testing, simply terminate the container suing the provided command. When needed again, restart the container using the same specific command. This approach ensure a streamlined and convenient database management process.

- View Running Containers: To see a list of running Docker containers, use the following command:

    ```
    docker ps
    ```

- Start or Stop Containers: To start a stopped container, use:

    ```
    docker start "container-name"
    ```

- To stop a running container, use:

    ```
    docker stop "container-name"
    ```

6. **Running project**: Then, after following the last steps, can you run this project using the following command:
    
    ```
    ./gradlew bootRun
    ```

## Backend Architecture Breakdown

This project use Clean Architecture. It is structured to ensure scalability,testability, and maintainability.

```
└── cleanarchitecture
    └── application
        └── cases
        └── dto
    └── domain
        └── model
        └── repository
    └── infrastructure
        └── config
        └── controller
        └── entity
        └── mapper
        └── repository
```

**Application Layer**: Contains the business use cases (application logic).

- Cases: Implements application use cases.
- Dto: Data Transfer Object use by use cases.

**Domain Layer**: The heart of the application. Contains the business rules and entities.

- Model: Entity definition 
- Repository: Interface with methods(business logic)

**Infrastructure Layer**: Implements details of external concerns (database, frameworks,etc...).

- Config: Spring configuration files and dependency injection setup.
- Controller: REST API controller.
- Entity: Entities definition with JPA/Hibernate 
- Mapper: Converts to objects
- Repository: Implements repository class and interface with JPA


