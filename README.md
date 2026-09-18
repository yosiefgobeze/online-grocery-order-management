Online Grocery Order Management
A RESTful API built with Spring Boot for managing customers, grocery items, and orders in an online grocery system.

Overview
This project provides basic data management for an online grocery ordering system. It supports CRUD operations for customers and grocery items, as well as order creation and management.

The application follows a layered architecture using:

Controllers for handling HTTP requests

Services for business logic

Repositories for database access

DTOs for API requests and responses

Global exception handling for common errors

JPA/Hibernate for persistence

Technologies
Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate

H2 Database

Jakarta Bean Validation

Lombok

Maven

Entities
Customer
Stores customer information:

ID

Name

Email

Address

Phone

A customer can have multiple orders.

Grocery Item
Represents products available in the grocery system:

ID

Name

Category

Price

Quantity

A grocery item can belong to multiple orders.

Order
Tracks orders placed by customers:

ID

Customer

Grocery Items

Order Date

Total Price

An order belongs to one customer and can contain multiple grocery items.

API Endpoints
Customer
Method	Endpoint	Description
GET	/api/v1/customers	Get all customers
GET	/api/v1/customers/{id}	Get customer by ID
POST	/api/v1/customers	Create customer
PUT	/api/v1/customers/{id}	Update customer
DELETE	/api/v1/customers/{id}	Delete customer

Grocery Items
Method	Endpoint	Description
GET	/api/v1/grocery-items	Get all grocery items
GET	/api/v1/grocery-items/{id}	Get grocery item by ID
POST	/api/v1/grocery-items	Create grocery item
PUT	/api/v1/grocery-items/{id}	Update grocery item
DELETE	/api/v1/grocery-items/{id}	Delete grocery item

Orders
Method	Endpoint	Description
GET	/api/v1/orders	Get all orders
GET	/api/v1/orders/{id}	Get order by ID
POST	/api/v1/orders	Create order
DELETE	/api/v1/orders/{id}	Delete order

Example: Create an Order
{
  "customerId": 2,
  "groceryItemIds": [1, 2]
}

The API verifies that the customer and requested grocery items exist before creating the order. The total price is calculated from the selected grocery items.

Example response:

{
  "id": 1,
  "customerId": 2,
  "groceryItemIds": [1, 2],
  "orderDate": "2026-09-18",
  "totalPrice": 17.98
}

Validation and Error Handling
The application uses Jakarta Bean Validation for request validation and a global exception handler for common errors.

Examples include:

400 Bad Request — invalid request data

404 Not Found — customer, grocery item, or order does not exist

409 Conflict — attempting to delete an entity that is referenced by existing records

201 Created — successful resource creation

204 No Content — successful deletion

Project Structure
src/main/java/com/yosiefgobeze/onlinegrocery
│
├── controller
│   ├── CustomerController
│   ├── GroceryItemController
│   └── OrderController
│
├── dto
│   ├── CustomerResponse
│   ├── CustomerUpdateRequest
│   ├── GroceryItemCreateRequest
│   ├── GroceryItemResponse
│   ├── GroceryItemUpdateRequest
│   ├── OrderCreateRequest
│   └── OrderResponse
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── CustomerNotFoundException
│   ├── CustomerHasOrdersException
│   ├── GroceryItemNotFoundException
│   ├── GroceryItemIsOrdered
│   └── OrderNotFoundException
│
├── model
│   ├── Customer
│   ├── GroceryItem
│   └── Order
│
├── repository
│   ├── CustomerRepository
│   ├── GroceryItemRepository
│   └── OrderRepository
│
└── service
    ├── CustomerService
    ├── GroceryItemService
    ├── OrderService
    │
    └── impl
        ├── CustomerServiceImpl
        ├── GroceryItemServiceImpl
        └── OrderServiceImpl

Database
The application uses an H2 file-based database for persistence during development.

The H2 console is available at:

/h2-console

Running the Application
Clone the repository and run the application using Maven:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

The application runs by default on:

http://localhost:8080
