# 🍕 Food Ordering System — Microservices Architecture

A full-stack **Food Ordering System** built using **Spring Boot Microservices** with service discovery, API gateway, event-driven messaging, and JWT authentication.

---

## 🏗️ Architecture
```
Client (Postman / Frontend)
        |
   API Gateway (Port 8080)
        |
   Eureka Server (Port 8761)
   /         |         \
User      Restaurant   Order
Service   Service      Service
(8081)    (8082)       (8083)
  |          |            |
user_db  restaurant_db  order_db
                          |
                        Kafka
```

## ⚙️ Tech Stack

| Technology | Usage |
|---|---|
| Spring Boot | Microservices Framework |
| Spring Cloud Eureka | Service Discovery |
| Spring Cloud Gateway | API Gateway |
| Spring Security + JWT | Authentication |
| Apache Kafka | Event-Driven Messaging |
| MySQL | Database |
| Maven | Build Tool |

---

## 📦 Services

### 1. Eureka Server (Port: 8761)
### 2. API Gateway (Port: 8080)
### 3. User Service (Port: 8081)
### 4. Restaurant Service (Port: 8082)
### 5. Order Service (Port: 8083)

---

## 🚀 How to Run

### Step 1 — Start Kafka
```bash
cd C:\Kafka\kafka_2.13-4.0.1
bin\windows\kafka-server-start.bat config\server.properties
```

### Step 2 — Start Services (in this order)
1. Eureka Server
2. API Gateway
3. User Service
4. Restaurant Service
5. Order Service

### Step 3 — Verify
Visit `http://localhost:8761` ✅

---

## 📬 API Endpoints

### 👤 User Service
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register user |
| POST | `/api/users/login` | Login & get token |
| GET | `/api/users/all` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| DELETE | `/api/users/{id}` | Delete user |

### 🍽️ Restaurant Service
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/restaurants` | Add restaurant |
| GET | `/api/restaurants` | Get all restaurants |
| GET | `/api/restaurants/{id}` | Get by ID |
| PUT | `/api/restaurants/{id}` | Update restaurant |
| DELETE | `/api/restaurants/{id}` | Delete restaurant |
| GET | `/api/restaurants/search?cuisine=Italian` | Search by cuisine |

### 📦 Order Service
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/orders` | Place order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get by ID |
| GET | `/api/orders/user/{userId}` | Get by user |
| PUT | `/api/orders/{id}/status?status=CONFIRMED` | Update status |
| DELETE | `/api/orders/{id}` | Cancel order |

---

## 🔐 Authentication
Add JWT token in header:
```
Authorization: Bearer <your_token>
```

---

## 🗄️ Database Setup
```sql
CREATE DATABASE user_db;
CREATE DATABASE restaurant_db;
CREATE DATABASE order_db;
```

---

## 👩‍💻 Author
**Pinky Gupta** — [@PinkyGupta30](https://github.com/PinkyGupta30)