🚖 Uber Backend System

A scalable and secure backend system inspired by Uber, built using Spring Boot, JWT Authentication, and RESTful APIs. This project handles core ride-booking functionalities including user management, driver allocation, ride tracking, and payment simulation.

📌 Features

🔐 Authentication & Authorization

JWT-based secure login/signup

Role-based access (User / Driver / Admin)
🔐 Security

Passwords encrypted using BCrypt

Stateless authentication using JWT

Secure API access via token
👤 User Management

Register & login users

Profile management

🚗 Driver Module

Driver registration

Availability toggle (Online/Offline)

📍 Ride Management

Request ride

Assign nearest driver

Ride status tracking (Requested → Accepted → Completed)

💳 Payment Simulation

Fare calculation based on distance

Mock payment system

📡 REST APIs

Clean and structured API endpoints

🛠️ Tech Stack

Backend: Spring Boot

Security: Spring Security + JWT

Database: MySQL

ORM: Hibernate / JPA

Build Tool: Maven

API Testing: Postman

📂 Project Structure
uber-backend/
│── controller/       # REST Controllers
│── service/          # Business logic
│── repository/       # JPA repositories
│── entity/           # Database models
│── dto/              # Data Transfer Objects
│── config/           # Security & App configs
│── util/             # Utility classes (JWT, etc.)
