# 📱 Social Media Application Microservices(say Instagram)

This repository documents the architecture and purpose of each microservice that powers the **Social Media App**, a distributed, scalable application built using **Spring Boot Microservices**.

---

## 🧩 What Is This Application?

The Social Media App is a microservice-based backend platform that mimics the functionality of a modern social network (like Instagram or Facebook). It enables users to register, log in, create posts, like posts, and comment — all through modular, independently deployed services.

## 🚀 Key Features
- JWT-based authentication and authorization
- JWT based routing using Spring Cloud Gateway
- Modular, loosely coupled microservices
- MySQL-based persistent storage
- REST communication between services
- Service discovery using Eureka
- Can be scaled independently

---

## 🛠 Technology Stack
| Layer       | Technology                      |
|------------|----------------------------------|
| Language    | Java 17                         |
| Frameworks  | Spring Boot, Spring Cloud       |
| Gateway     | Spring Cloud Gateway            |
| Auth        | JWT + Gateway filters           |
| Service Reg | Eureka Discovery Server         |
| DB          | MySQL                           |
| HTTP Client | RestTemplate                    |
| Build Tool  | Gradle                          |

---

## 🧱 Microservice Overview

| **Microservice**              | **Role / Responsibilities**                                  | **Base URL**                                                  |
|-------------------------------|--------------------------------------------------------------|---------------------------------------------------------------|
| **UserService**               | User creation, login, profile retrieval and management       | `http://localhost:7070/user-service`                          |
| **PostService**               | Manage posts and categories                                  | `http://localhost:7070/post-service`                          |
| **ReactionService**           | Handles likes and comments on posts                          | `http://localhost:7070/reaction-service`                      |
| **GatewayService**            | Central entry point, handles routing and JWT authentication  | `http://localhost:7070`                                       |
| **SocialMediaAppRegistry**    | Service discovery; monitors registration and availability    | `http://localhost:8761`(UI to see status of services)         |
| **ServiceClient**             | Responsible for inter-commmunication of microservices        |                                                               |

> ⚠️ All internal services (except login and sign up endpoints) are protected via JWT and routed through the API Gateway.

---

## 🗂 Sample Endpoints

### 🧑 User Service

- `POST /user-service/register` — Register a new user  
- `POST /user-service/login` — Authenticate and receive a JWT  
- `GET /user-service/users/{id}` — Get user profile by ID

### 📝 Post Service

- `GET /post-service/posts` — Get all posts  
- `POST /post-service/posts` — Create a new post  
- `GET /post-service/users/{id}/posts` — Posts by user  
- `GET /post-service/category/{id}/posts` — Posts by category

### ❤️ Reaction Service

- `POST /reaction-service/likes` — Like a post  
- `GET /reaction-service/likes/{postId}` — Get likes for post  
- `POST /reaction-service/comments` — Comment on post  
- `GET /reaction-service/comments/{postId}` — Get comments for post


## 🔮 Future Enhancements

- Add Swagger for all services
- Frontend (Angular) to consume these services
- Dockerize all services and use Docker Compose
- Integrate centralized logging (ELK or Loki stack)
- Implement rate-limiting, circuit breakers (Resilience4j)
- Deploy to cloud (AWS) or Kubernetes
- Introduce caching mechanism say redis or ehcache in get request

---

## 👨‍💻 Maintainer

- **Simer(Harsimarpreet singh)** — Java Full Stack Developer | Spring Boot | Angular | Microservices | Devops
