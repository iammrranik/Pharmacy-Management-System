# 🏥 Pharmacy Management System

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:00B4D8,50:0077B6,100:03045E&height=200&section=header&text=Pharmacy%20Management%20System&fontSize=35&fontColor=ffffff&animation=fadeIn"/>
</p>

<p align="center">
  <a href="https://github.com/iammrranik">
    <img src="https://img.shields.io/badge/_Click_Here_to_Explore_My_GitHub_Profile-181717?style=for-the-badge&logo=github&logoColor=white"/>
  </a>
</p>

---

## ✨ Status

🚧 **Completed**  
☕ Built with Java 25 & Spring Boot 4.0.6  
🗄️ Uses MySQL with Spring Data JDBC  
🔐 JWT-based Authentication & Role-Based Access Control  
🎯 Designed for Pharmacy Inventory & Sales Management

---

## 🎯 System Overview

A full-featured **Pharmacy Management REST API** built with Spring Boot. The system manages medicines, suppliers, orders, and users with secure JWT authentication and granular role-based access control. It supports complete **buy** and **return** workflows with time-based tiered refunds, low-stock alerts, expiry tracking, and CSV export capabilities.

---

## 🔥 Features

- 🧩 **Clean Modular Architecture** — Layered design (Controller → Service → Repository → DB) with interface-based programming
- 🔐 **JWT Authentication & RBAC** — Secure login with ADMIN and CUSTOMER roles, granular endpoint-level access control
- 💊 **Medicine Inventory** — Full CRUD, search by name/category/supplier/batch, low-stock alerts, expiry tracking
- 🚚 **Supplier Management** — Supplier CRUD with phone/name search and CSV export
- 🛒 **Order Management** — Complete **buy workflow** with automatic stock deduction on purchase
- 🔄 **Return Workflow** — Time-based tiered refund system (100% within 3hrs down to 10% within 72hrs) with automatic stock restoration
- 📄 **CSV Export** — One-click supplier data export
- ⚡ **Optimized Performance** — Paginated endpoints, `NamedParameterJdbcTemplate` for efficient queries
- 🛡️ **Self-Service Security** — CUSTOMER users can only access their own data; ADMIN has full control

---

## 📌 Tech Stack

| Technology | Version |
|---|---|
| **Java** | 25 |
| **Spring Boot** | 4.0.6 |
| **Spring Security** | 6.x |
| **Spring Data JDBC** | 3.x |
| **MySQL** | 8.x |
| **JWT (jjwt)** | 0.12.6 |
| **Maven** | 3.x |
| **BCrypt** | Password hashing |

---

## 🗂️ Project Structure

```
src/main/java/com/pharmacy/management/system/
├── Application.java                        # Spring Boot entry point
├── api/                                    # REST controllers
│   ├── AuthApi.java                        # Login & Registration
│   ├── MedicineApi.java                    # Medicine CRUD + search
│   ├── OrderApi.java                       # Order CRUD + return
│   ├── OrderDetailsApi.java                # Order line items
│   ├── SupplierApi.java                    # Supplier CRUD + CSV export
│   └── UserApi.java                        # User management
├── config/                                 # Security & validation
│   ├── JwtAuthFilter.java                  # Bearer token filter
│   ├── JwtUtil.java                        # Token generation/validation
│   ├── SecurityBeansConfig.java            # Bean definitions
│   ├── SecurityConfig.java                 # RBAC rules
│   └── ValidationExceptionHandler.java     # Global error handler
├── domain/                                 # Model/entity classes
│   ├── Medicine.java                       # Medicine model
│   ├── Order.java                          # Order model (with refund fields)
│   ├── OrderDetails.java                   # Order line-item model
│   ├── Person.java                         # Abstract base class
│   ├── Supplier.java                       # Supplier model
│   ├── User.java                           # User model
│   └── enums/
│       ├── OrderStatus.java                # PENDING, COMPLETED, CANCELLED, REFUNDED
│       └── UserRole.java                   # ADMIN, CUSTOMER
├── repository/                             # Data access layer
│   ├── IMedicineRepository.java
│   ├── IOrderDetailsRepository.java
│   ├── IOrderRepository.java
│   ├── ISupplierRepository.java
│   ├── IUserRepository.java
│   ├── implementation/                     # JDBC implementations
│   │   ├── MedicineRepository.java
│   │   ├── OrderDetailsRepository.java
│   │   ├── OrderRepository.java
│   │   ├── SupplierRepository.java
│   │   └── UserRepository.java
│   └── mapper/                             # RowMapper implementations
│       ├── MedicineMapper.java
│       ├── OrderDetailsMapper.java
│       ├── OrderMapper.java
│       ├── SupplierMapper.java
│       └── UserMapper.java
└── service/                                # Business logic layer
    ├── IMedicineService.java
    ├── IOrderDetailsService.java
    ├── IOrderService.java
    ├── ISupplierService.java
    ├── IUserService.java
    └── implementation/
        ├── MedicineService.java
        ├── OrderDetailsService.java
        ├── OrderService.java
        ├── SupplierService.java
        └── UserService.java

src/main/resources/
├── application.properties                  # DB, JWT, logging config
├── db.sql                                  # Schema + seed data
├── api.txt                                 # API endpoint reference
├── api2.txt                                # Postman/JWT auth guide
├── api3.txt                                # RBAC scenario testing guide
└── plan.txt                                # Implementation plan
```

---

## 🚦 How to Run

### Prerequisites

- Java 25+
- MySQL 8.x
- Maven 3.x

### Setup

```bash
# 1. Create the MySQL database
mysql -u root -p < src/main/resources/db.sql

# 2. Configure application.properties (default: root@localhost:3306/pharmacydb)

# 3. Build and run
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 🔗 API Endpoints

### Authentication (`/api/auth`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/auth/register` | Public | Register a new user |
| POST | `/api/auth/login` | Public | Login, returns JWT token |

### Medicines (`/api/medicines`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/medicines` | Public | List all (paginated) |
| GET | `/api/medicines/{id}` | Public | Get by ID |
| POST | `/api/medicines` | ADMIN | Create medicine |
| PUT | `/api/medicines/{id}` | ADMIN | Update medicine |
| DELETE | `/api/medicines/{id}` | ADMIN | Delete medicine |
| GET | `/api/medicines/name/{name}` | Public | Search by name |
| GET | `/api/medicines/category/{category}` | Public | Search by category |
| GET | `/api/medicines/low-stock` | Public | Low stock alerts |
| GET | `/api/medicines/expired` | Public | Expired medicines |
| GET | `/api/medicines/expiring-within/{days}` | Public | Expiring soon |

### Orders (`/api/orders`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/orders` | All | List all (paginated) |
| GET | `/api/orders/{id}` | All | Get by ID |
| POST | `/api/orders` | All | Create order (**buy workflow**) |
| PUT | `/api/orders/{id}` | ADMIN | Update order |
| DELETE | `/api/orders/{id}` | ADMIN | Delete order |
| PUT | `/api/orders/return/{orderId}` | All | Return medicine (**return workflow**) |
| PUT | `/api/orders/status/{orderId}` | ADMIN | Update order status |

### Suppliers (`/api/suppliers`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/suppliers` | Public | List all (paginated) |
| GET | `/api/suppliers/{id}` | Public | Get by ID |
| POST | `/api/suppliers` | ADMIN | Create supplier |
| PUT | `/api/suppliers/{id}` | ADMIN | Update supplier |
| DELETE | `/api/suppliers/{id}` | ADMIN | Delete supplier |
| GET | `/api/suppliers/export/csv` | ADMIN | Export suppliers to CSV |

### Users (`/api/users`)
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/users` | ADMIN | List all (paginated) |
| GET | `/api/users/{id}` | Self/ADMIN | Get user by ID |
| POST | `/api/users` | ADMIN | Create user |
| PUT | `/api/users/{id}` | Self/ADMIN | Update user |
| DELETE | `/api/users/{id}` | Self/ADMIN | Delete user |

---

## 📝 Design

The system follows a **layered architecture** with clearly separated concerns:

- **API Layer** (`api/`): REST controllers handling HTTP requests/responses with validation
- **Service Layer** (`service/`): Business logic — password hashing, stock deduction, time-based refunds, CSV export
- **Repository Layer** (`repository/`): Data access using `NamedParameterJdbcTemplate` with custom `RowMapper` implementations
- **Domain Layer** (`domain/`): Model classes with validation annotations and enums for roles/statuses
- **Config Layer** (`config/`): JWT authentication, RBAC security rules, global exception handling

### Key Workflows

- **Buy Workflow**: Customer places an order → `OrderDetailsService` deducts medicine quantities from stock → Order status set to `COMPLETED`
- **Return Workflow**: Customer initiates return → `OrderService` calculates refund based on time elapsed (100% ≤3hrs → 10% ≤72hrs) → Stock quantities restored → Order status set to `REFUNDED`

### Security Design

- **JWT tokens** with configurable expiration
- **Stateless sessions** — no HTTP session used
- **RBAC** with 50+ endpoint-level rules in `SecurityConfig`
- **Self-check** in `UserApi` prevents CUSTOMER users from accessing other users' data
- **Automatic ADMIN role blocking** during registration

---

## 📦 Requirements

- Java 25
- Spring Boot 4.0.6
- MySQL 8+
- Maven 3.x

---

## 🙏 Credits

Developed by [iammrranik](https://github.com/iammrranik) for a Pharmacy Management System.

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:00B4D8,50:0077B6,100:03045E&height=4" width="80%"/>
</p>
