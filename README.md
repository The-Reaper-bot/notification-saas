# 🚀 Notification SaaS Platform (Spring Boot)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Build](https://img.shields.io/badge/Build-Passing-success)
![JWT](https://img.shields.io/badge/Auth-JWT-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A **production-ready, multi-tenant SaaS backend** for sending notifications (SMS / Email / WhatsApp-ready) built using **Spring Boot, JWT, and Spring Security**.

This project focuses on **real-world SaaS architecture**, secure multi-tenancy, usage tracking, and cloud deployment.

---

## ✨ Features

- 🔐 **JWT-based Authentication** (Stateless)
- 🏢 **Multi-Tenant Architecture**
- 📩 **Notification APIs** (SMS stub, extensible for Email & WhatsApp)
- 📊 **Usage Tracking & Quota Enforcement**
- 🧩 **Clean Layered Architecture**
- 📄 **Swagger / OpenAPI Documentation**
- ☁️ **Cloud Deployed (Render)**
- 🧪 **H2 for Local Development | PostgreSQL-ready for Production**

---

## 🛠 Tech Stack

| Category | Technology |
|--------|-----------|
| Language | Java 17 (LTS) |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA (Hibernate) |
| Database | H2 (Local), PostgreSQL (Prod) |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |
| Deployment | Docker + Render |

---

## 🧠 Architecture Overview
Controller → Service → Repository
↓
Usage Tracking
↓
Multi-Tenant Context

### 🔹 Multi-Tenancy
- Tenant is created automatically during signup
- Tenant ID is embedded inside JWT
- Tenant resolved per request using `ThreadLocal`
- Prevents cross-tenant data access

### 🔹 Security
- Custom `OncePerRequestFilter` for JWT validation
- Role-based authorization
- CSRF disabled for stateless REST APIs
- Public endpoints explicitly whitelisted

---

## 📦 Project Structure

com.saas.notification
├── auth → Signup / Login, JWT handling
├── config → Spring Security configuration
├── notification → Notification APIs
├── tenant → Tenant management
├── usage → Monthly usage tracking
├── common → TenantContext (ThreadLocal)


---

## 🔑 Authentication Flow

1. Client calls **POST /auth/signup**
2. Tenant + User are created
3. JWT issued with:
   - userId
   - tenantId
   - role
4. JWT required for all secured APIs

---

## 📊 Usage Tracking (SaaS Billing Ready)

- Tracks **SMS usage per tenant per month**
- Enforces plan-based limits:
  - **FREE** → 100 SMS
  - **PRO** → 5000 SMS
- Requests blocked when quota exceeded

---

## 📖 API Documentation (Swagger)

Once the application is running, open:
/swagger-ui.html

/swagger-ui/index.html

---

## ▶️ Run Locally (Without Docker)

### Prerequisites
- Java 17
- Maven 3.9+

### Steps

bash
$ mvn clean compile
$ mvn spring-boot:run
Swagger UI:
http://localhost:8080/swagger-ui.html

🧪 Sample API Usage
🔹 Signup
POST /auth/signup

{
  "company": "TestCorp",
  "email": "admin@test.com",
  "password": "password123"
}

🔹 Create Notification
POST /api/v1/notifications
Authorization: Bearer <JWT>

{
  "type": "SMS",
  "recipient": "9999999999",
  "message": "Hello from SaaS"
}
🐳 Docker Deployment
Dockerfile Included
docker build -t notification-saas .
docker run -p 8080:8080 notification-saas
☁️ Cloud Deployment (Render)

Runtime: Docker

Java Version: 17

Environment Variables:

JWT_SECRET=your-32-character-secret
SPRING_PROFILES_ACTIVE=dev

🌐 Live Demo (Swagger)
https://notification-saas.onrender.com/swagger-ui.html

Designed and deployed a multi-tenant SaaS notification backend using Spring Boot and JWT, featuring secure tenant isolation, usage-based quotas, and cloud deployment.



🧩 Future Enhancements

WhatsApp integration (Gupshup)

Email providers (SES / SendGrid)

Stripe billing integration

Admin dashboard APIs

Async processing (Kafka / RabbitMQ)

Rate limiting & monitoring

👤 Author

Shreya
Backend Engineer | Java | Spring Boot
