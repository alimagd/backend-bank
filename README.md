# Bank API

This project is a **Spring Boot** application that simulates a simple **Bank API** for managing accounts, withdrawals, and deposits. It includes RESTful endpoints for account operations and demonstrates best practices for exception handling using custom exceptions.

## Features

- **Create an account**: Add new accounts with account details.
- **Retrieve account by ID**: Get account details by its unique ID.
- **Withdraw**: Withdraw a specific amount from an account (with balance validation).
- **Deposit**: Add a specified amount to an account.
- **Retrieve all accounts**: Get all accounts details,
- **Exception handling**: Custom exceptions for `ResourceNotFoundException` and `InsufficientBalanceException`.

## Technologies Used

- **Spring Boot**: Java framework for building the backend.
- **Spring Data JPA**: For data persistence and interaction with the database.
- **MySQL Database**: Database for storing account information. 
- **Lombok**: To reduce boilerplate code (e.g., `@RequiredArgsConstructor`).
- **Maven**: Dependency management and build automation.

## Prerequisites

- **Java 21**
- **Maven** installed
- An IDE like **IntelliJ IDEA** or **Eclipse** (optional but recommended)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/alimagd/backend-bank.git
```

### 2. Build the Project

Navigate to the project directory and build it using Maven:

```bash
cd backend-bank
mvn clean install
```

### 3. Run the Application

You can run the application directly from your IDE or using the following Maven command:

```bash
mvn spring-boot:run
```

### 4. Access the Application

Once the application is running, you can access it on **http://localhost:8080/api/accounts**.

### 5. Setup Mysql Database
CREATE DATABASE bank_app;

Make sure your MySQL username and password in application.properties match your local setup
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bank_app
spring.datasource.username=root
spring.datasource.password=password
# Hibernate properties:
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


## API Endpoints

| Method | Endpoint                  | Description                          |
|--------|----------------------------|--------------------------------------|
| POST   | `/api/accounts`             | Create a new account                 |
| GET    | `/api/accounts/{id}`        | Retrieve account by ID               |
| PUT    | `/api/accounts/{id}/deposit`| Deposit an amount into an account    |
| PUT    | `/api/accounts/{id}/withdraw`| Withdraw an amount from an account   |
| DELETE | `/api/accounts/{id}`        | Delete an account by ID              |
| GET    | `/api/accounts`                | Retrieve all accounts              |

### Example Requests

#### 1. Create an Account (POST)

```bash
POST /api/accounts
```

**Request Body:**
```json
{
    "name": "John Doe",
    "balance": 1000.0
}
```

#### 2. Withdraw from an Account (PUT)

```bash
PUT /api/accounts/{id}/withdraw
```

**Request Body:**
```json
{
    "amount": 200.0
}
```

## Exception Handling

- **ResourceNotFoundException**: Returns a `404 Not Found` if an account does not exist.
- **InsufficientBalanceException**: Returns a `400 Bad Request` if a withdrawal is attempted on an account with insufficient balance.

## Project Structure

```bash
src
└── main
    └── java
        └── com
            └── example
                └── bank
                    ├── controller    # REST controllers
                    ├── exception     # Custom exceptions and global handlers
                    ├── model         # Entity classes (Account, etc.)
                    ├── repository    # JPA Repositories
                    ├── service       # Service layer (business logic)
                    └── mapper        # Mapper for converting between DTOs and entities
```

## Contributing

Feel free to fork the repository and submit pull requests if you'd like to contribute.
