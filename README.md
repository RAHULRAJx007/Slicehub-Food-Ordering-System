# 🍕 SliceHub – Online Pizza Ordering System

SliceHub is a full-stack online pizza ordering web application built using **Spring Boot** and **Thymeleaf**, designed to simulate a real-world food delivery platform. The project focuses on secure authentication, email verification, role-based access, and a clean, responsive user experience.

This application demonstrates enterprise-level backend development practices using the Spring ecosystem.

---

## 🚀 Features

### 👤 Authentication & Security
- User registration with **email verification**
- Secure login using **Spring Security**
- Password encryption with **BCrypt**
- Role-based authorization (`CUSTOMER`, `ADMIN`)
- Only verified users can log in

---

### 🍕 Pizza Management
- Browse available pizzas with price and description
- Admin-only pizza management (add / update / delete)
- Image upload support for pizza items

---

### 🛒 Shopping Cart
- Add pizzas to cart
- Increase / decrease quantity
- Remove items from cart
- Automatic subtotal, tax, and grand total calculation
- Cart linked to authenticated user

---

### 🎨 Frontend & UI
- Responsive design using **HTML, CSS, and Thymeleaf**
- Reusable header and footer fragments
- Clean and modern UI
- Mobile-friendly navigation

---

## 🧰 Tech Stack

### Backend
- Java 17+
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL

### Frontend
- HTML5
- CSS3
- Thymeleaf

### Tools
- Maven
- Mailtrap (email testing)
- BCrypt Password Encoder

---

## 📁 Project Structure

  SliceHub
├── controller
├── service
├── repository
├── model
├── templates
│ ├── fragments
│ ├── index.html
│ ├── login.html
│ ├── register.html
│ └── about.html
├── static
│ └── css
└── application.properties


---

## 🔐 Authentication Flow

Register → Email Verification → Login → Browse Menu → Add to Cart → Checkout


---

## 📧 Email Verification
- Secure verification token generated during registration
- Verification link sent via email
- Token expiration support
- User account enabled only after verification

---

## 🧪 Database
- MySQL with Hibernate auto schema update
- Entity relationships:
  - User ↔ Role
  - User ↔ Cart
  - Cart ↔ CartItem ↔ Pizza

---

## 🌱 Future Enhancements
- Order checkout & payment gateway integration
- Admin dashboard & analytics
- Order history and tracking
- Coupon and discount system
- REST API with React frontend

---

## 🎯 Learning Outcomes
This project demonstrates:
- Real-world Spring Boot application architecture
- Secure authentication and authorization
- Email verification workflow
- Shopping cart logic
- Clean service-repository design patterns

---

## 👨‍💻 Author
**Rahul Raj**

Built with ❤️ as a live project to learn and demonstrate enterprise-level Java web development.
