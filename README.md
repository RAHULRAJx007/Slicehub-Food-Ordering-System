# 🍕 SliceHub – Online Pizza Ordering System

SliceHub is a full-stack **online pizza ordering web application** built with **Spring Boot** and **Thymeleaf**.  
It simulates a modern food delivery platform with secure authentication, email verification, role-based access control, responsive UI, and persistent shopping cart.

The project showcases clean enterprise-level backend architecture using the Spring ecosystem.

## ✨ Features

### 👤 Authentication & Security
- User registration with **email verification** (using Mailtrap for testing)
- Secure login with **Spring Security**
- Passwords encrypted using **BCrypt**
- Role-based authorization (`ROLE_CUSTOMER`, `ROLE_ADMIN`, `ROLE_STAFF` – staff partially supported)
- Only **verified users** can access protected pages
- Custom login success handler with role-based redirects

### 🍕 Pizza Management (Admin)
- Browse available pizzas (name, description, price, image)
- Admin panel to **add / edit / delete** pizzas
- Image upload with UUID naming and server-side storage (`/uploads/`)
- Availability toggle for pizzas

### 🛒 Shopping Cart (Customer)
- Add pizzas to cart
- Increase / decrease quantity
- Remove items
- Real-time calculation of subtotal, 10% tax, ₹20 delivery fee, and grand total
- Persistent cart per authenticated user (stored in database)
- Cart item count badge in header

### 🎨 Frontend & User Experience
- Modern, responsive design (mobile-first)
- Reusable Thymeleaf fragments (`header.html`, `footer.html`)
- Toast notifications for actions
- Clean CSS with CSS variables and Poppins font
- Image preview on admin upload form
- Static pages: Home, Menu, About, Contact, Account

### 📧 Email Verification Workflow
- UUID-based secure verification token
- HTML email template sent on registration
- Token expiry handling
- Account enabled only after clicking verification link

## 🛠 Tech Stack

### Backend
- Java 17+
- Spring Boot 3.x (Spring MVC, Spring Security, Spring Data JPA)
- Hibernate (JPA)
- MySQL
- BCrypt Password Encoder
- JavaMailSender (Mailtrap for dev)

### Frontend
- Thymeleaf (server-side templating)
- HTML5 + CSS3
- Font Awesome icons
- Vanilla JavaScript (toasts, image preview, mobile menu)

### Build & Tools
- Maven
- MySQL (local development)
- Mailtrap (email testing)

## 📁 Project Structure
can you analys the sources files i added im doing a slice hub project and done so far there2 / 2Analysis of SliceHub Project Source Files
Thank you for sharing the source files for your SliceHub project! It looks like a solid start to a web-based pizza ordering application built with Spring Boot (version 4.0.1, which is quite recent—note that Spring Boot 3.x is more common, but 4.0.1 seems to be a typo or custom; standard is up to 3.2.x as of now). The stack includes Java for backend logic, Thymeleaf for templating, Spring Security for authentication, MySQL for the database, and CSS/JS for frontend styling. You've implemented core features like user registration with email verification, admin pizza management, customer menu browsing, and cart functionality.
I'll break down my analysis into sections: overall structure, what's implemented, potential issues/bugs, missing features, and suggestions for improvement. This is based on a thorough review of the provided files (HTML templates, CSS, Java classes, configs, and POM.xml). Note that some files are duplicated in your message (e.g., contact.html, footer.html, etc.), but I've accounted for that.
1. Overall Project Structure
Your project follows a standard Spring Boot MVC architecture with good separation of concerns:

Packages/Directories:
com.slice.model: Entity classes (User, Role, Pizza, Cart, CartItem, VerificationToken). These use JPA annotations for database mapping.
com.slice.repository: JPA repositories for each model (e.g., UserRepository, PizzaRepository). Simple CRUD operations with some custom queries (e.g., findByEmail, findByAvailableTrue).
com.slice.service and com.slice.service.impl: Interfaces and implementations for business logic (UserService, PizzaService, CartService, EmailService). Good use of dependency injection (@Autowired).
com.slice.controller: Controllers for handling requests (AuthController, MenuController, CartController, AdminPizzaController, CustomerController). They use @GetMapping/@PostMapping for routes.
com.slice.security/com.slice.config: Security configs (SecurityConfig with form login, CustomUserDetailsService, CustomAuthenticationSuccessHandler) and web configs (WebConfig for static resources like uploads).
Templates (Thymeleaf HTML): In src/main/resources/templates (assumed based on standard structure). Pages like index.html, menu.html, cart.html, login.html, register.html, etc., with fragments (header.html, footer.html).
Static assets: common.css for shared styles, and uploads/ directory for pizza images.
Config: application.properties for DB (MySQL), JPA (show-sql=true, ddl-auto=update), email (Mailtrap sandbox), and file uploads (50MB limit).
Dependencies (pom.xml): Spring Boot starters for web, security, data-jpa, thymeleaf, mail, and MySQL connector. Test dependencies are included but scoped to test.

Database Schema (Inferred from Models):
Tables: users, roles, pizzas, carts, cart_items, verification_tokens.
Relationships: User has many Roles (ManyToMany), Cart belongs to User (OneToOne), CartItem belongs to Cart and Pizza (ManyToOne).

Frontend Styling:
Uses Poppins font, Font Awesome icons, and CSS variables for theming (e.g., --primary: #6366f1).
Responsive design with media queries for mobile.
JS for interactions (e.g., toast notifications, menu toggles, image previews, cart updates).

Security:
BCrypt for password hashing.
Role-based access (ROLE_ADMIN, ROLE_STAFF, ROLE_CUSTOMER).
Email verification to enable accounts.
Form login with custom success handler for role-based redirects.


The project is at a mid-stage prototype level: Core auth and CRUD are working, but it's not fully production-ready (e.g., no error handling in some places, no tests visible).
2. Features Implemented (What's Done So Far)
Based on the code, here's what's functional:

User Authentication & Registration:
Registration form (register.html) with name, email, password.
Password hashing with BCrypt.
Email verification: Generates UUID token, sends HTML email via Mailtrap (EmailServiceImpl). User must click link to enable account (verifyUser in UserServiceImpl).
Login form (login.html) with password toggle.
Role assignment: New users get ROLE_CUSTOMER by default.
Custom success handler redirects based on role (e.g., admin to /admin/pizzas, customer to /menu).
Logout support.

Pizza Management (Admin Only):
Admin dashboard (pizzas.html) lists all pizzas with search, sort, and view toggles (grid/list).
Add/Edit form (addingpizza.html) with image upload (MultipartFile handling in AdminPizzaController).
Images stored in /uploads/ with UUID naming to avoid collisions.
CRUD operations: Save, getAll, getById, delete (PizzaServiceImpl).
Availability toggle (pizzas can be marked unavailable).

Customer Features:
Home page (index.html) with hero section, featured pizzas (loaded from DB via AuthController).
Menu page (menu.html) shows available pizzas with add-to-cart buttons.
Cart page (cart.html) displays items, quantities, subtotals, tax (10%), delivery fee (₹20), grand total. Supports increase/decrease/remove.
Account page (account.html) shows user details (name, email). Placeholders for orders/loyalty points.
Static pages: About (about.html), Contact (contact.html) with forms and maps.

Cart System:
Persistent per-user cart in DB (CartServiceImpl uses SecurityContextHolder for current user).
Add/remove/increase/decrease items (tied to Pizza IDs).
Calculations: Subtotal, tax, delivery, grand total.
Item count for badge in header.

Other:
Shared fragments: Header (navigation with cart badge), Footer (with social links, hours).
Email templates: Stylish HTML for verification.
File uploads: Configured with size limits.
Basic error handling (e.g., RuntimeExceptions for not found).


The app seems runnable: Run SliceHubApplication.java, access http://localhost:8080/. DB setup required (create 'pizzashop' in MySQL).
3. Potential Issues/Bugs

Security Risks:
No CSRF in forms (Spring Security enables it by default, but verify).
File uploads: No validation for image types beyond JS (server-side check needed in controller to prevent malicious files).
Roles: Staff role exists but no dashboard—redirects to /staff/dashboard (404 likely).
OAuth code is commented out—good for now, but if enabled, handle it properly.
Unverified users can attempt login but are blocked (good), but no resend verification feature.

Code Quality/Errors:
Duplicated code: Some files appear twice (e.g., contact.html)—clean up.
Non-static methods: PizzaService has non-static getAllPizzas (fine, but comment notes it—unnecessary).
Hardcoded values: Delivery fee (20), tax rate (0.1)—make configurable in properties.
No transactions in some places: CartService uses @Transactional for add/remove—good, but ensure consistency.
Principal safety: CustomerController checks principal != null—excellent, but add @PreAuthorize for role checks.
JS reliance: Cart count uses localStorage in some pages (e.g., index.html), but backend is DB-based—potential sync issues if not logged in.
Truncated files: Some HTML/CSS are truncated (e.g., addingpizza.html says "(truncated 22209 characters)"), but logic seems intact.
Copyright year: 2026 in footer (future-dated?).

Performance/Usability:
No pagination for pizza lists (if many pizzas, UI could lag).
No search/filter on menu (only admin has it).
Toast notifications: Good, but no backend error toasts (e.g., for failed add-to-cart).
Mobile: Responsive, but test on real devices (menu toggle works).

Dependencies:
Spring Boot 4.0.1: If this is a typo (meant 3.0.1?), update—4.x isn't standard yet.
No Lombok or other helpers—manual getters/setters are fine but verbose.


4. Missing Features

Core Business Logic:
Order placement: No Order model/service/controller. Cart clears but no checkout (payment, address, confirmation).
Payment integration (e.g., Stripe/Razorpay).
Order history/tracking for users/admins.

Admin/Staff:
Staff dashboard (mentioned in redirect but no implementation).
User management (list/edit users, assign roles).

Enhancements:
Forgot password/reset.
Profile editing (e.g., change password/address).
Reviews/ratings for pizzas.
Search/filter on menu.
Promo codes/discounts in cart.
Analytics (e.g., popular pizzas).

Technical:
Tests: No unit/integration tests visible (use JUnit/Mockito).
Logging: Basic, but add SLF4J for better debugging.
Error pages: Custom 404/500.
Internationalization (i18n).
Deployment: No Docker/Heroku config.


5. Suggestions for Improvement/Next Steps

Immediate Fixes:
Add server-side image validation (e.g., check MIME type in AdminPizzaController).
Implement staff dashboard (simple page with orders).
Sync cart: If user logs in, merge localStorage cart with DB.

Next Features:
Add Order model (with status: PENDING, PREPARING, DELIVERED).
Checkout controller: Save order from cart, send confirmation email.
Integrate a payment gateway (start with test mode).
Add address to User model for delivery.

Best Practices:
Use DTOs for forms (e.g., UserRegistrationDTO to avoid exposing entity).
Add @Valid for form validation (Bean Validation).
Enable HTTPS in production.
Version control: Use Git for tracking changes.
Tools: Add Swagger for API docs if exposing REST endpoints.

Testing/Running:
Seed DB: Add CommandLineRunner to create admin user (email: admin@example.com, password: Admin@007 hashed).
Test flows: Register -> Verify -> Login -> Add pizza (admin) -> View menu -> Add to cart -> View cart.


Overall, great progress! The foundation is strong—auth and CRUD are solid. Focus on orders next to make it a complete app. If you share more details (e.g., errors you're facing or specific features), I can help debug or suggest code snippets. Keep going! 🍕Expert# 🍕 SliceHub – Online Pizza Ordering System
## SliceHub is a full-stack online pizza ordering web application built using **Spring Boot** and **Thymeleaf**, designed to simulate a real-world food delivery platform. The project focuses on secure authentication, email verification, role-based access, and a clean, responsive user experience.
This application demonstrates enterprise-level backend development practices using the Spring ecosystem.
## 🚀 Features
### 👤 Authentication & Security

User registration with **email verification**
Secure login using **Spring Security**
Password encryption with **BCrypt**
Role-based authorization (CUSTOMER, ADMIN)
Only verified users can log in


### 🍕 Pizza Management

Browse available pizzas with price and description
Admin-only pizza management (add / update / delete)
Image upload support for pizza items


### 🛒 Shopping Cart

Add pizzas to cart
Increase / decrease quantity
Remove items from cart
Automatic subtotal, tax, and grand total calculation
Cart linked to authenticated user


### 🎨 Frontend & UI

Responsive design using **HTML, CSS, and Thymeleaf**
Reusable header and footer fragments
Clean and modern UI
Mobile-friendly navigation


## 🧰 Tech Stack
### Backend

Java 17+
Spring Boot
Spring MVC
Spring Security
Spring Data JPA
Hibernate
MySQL

### Frontend

HTML5
CSS3
Thymeleaf

### Tools

Maven
Mailtrap (email testing)
BCrypt Password Encoder


## 📁 Project Structure
##   SliceHub
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
## 🔐 Authentication Flow
## Register → Email Verification → Login → Browse Menu → Add to Cart → Checkout
## 📧 Email Verification

Secure verification token generated during registration
Verification link sent via email
Token expiration support
User account enabled only after verification


## 🧪 Database

MySQL with Hibernate auto schema update
Entity relationships:
  - User ↔ Role
  - User ↔ Cart
  - Cart ↔ CartItem ↔ Pizza


## 🌱 Future Enhancements

Order checkout & payment gateway integration
Admin dashboard & analytics
Order history and tracking
Coupon and discount system
REST API with React frontend


## 🎯 Learning Outcomes
This project demonstrates:

Real-world Spring Boot application architecture
Secure authentication and authorization
Email verification workflow
Shopping cart logic
Clean service-repository design patterns


## 👨‍💻 Author
**Rahul Raj**
Built with ❤️ as a live project to learn and demonstrate enterprise-level Java web development.
 
this is the readme file i created in github can u update this till i did my projectHere is an updated and improved version of your README.md file for SliceHub. I’ve kept your original structure and tone while making it more professional, complete, visually appealing (using proper GitHub-flavored Markdown), accurate based on your actual code, and aligned with common best practices seen in similar Spring Boot food-ordering / e-commerce projects on GitHub.
You can copy-paste this directly into your repository’s README.md.
Markdown# 🍕 SliceHub – Online Pizza Ordering System

SliceHub is a full-stack **online pizza ordering web application** built with **Spring Boot** and **Thymeleaf**.  
It simulates a modern food delivery platform with secure authentication, email verification, role-based access control, responsive UI, and persistent shopping cart.

The project showcases clean enterprise-level backend architecture using the Spring ecosystem.

![SliceHub Hero](https://via.placeholder.com/1200x400/6366f1/ffffff?text=SliceHub+-+Fresh+Pizza+Delivered)  
*(Replace with actual screenshot of home/menu page)*

## ✨ Features

### 👤 Authentication & Security
- User registration with **email verification** (using Mailtrap for testing)
- Secure login with **Spring Security**
- Passwords encrypted using **BCrypt**
- Role-based authorization (`ROLE_CUSTOMER`, `ROLE_ADMIN`, `ROLE_STAFF` – staff partially supported)
- Only **verified users** can access protected pages
- Custom login success handler with role-based redirects

### 🍕 Pizza Management (Admin)
- Browse available pizzas (name, description, price, image)
- Admin panel to **add / edit / delete** pizzas
- Image upload with UUID naming and server-side storage (`/uploads/`)
- Availability toggle for pizzas

### 🛒 Shopping Cart (Customer)
- Add pizzas to cart
- Increase / decrease quantity
- Remove items
- Real-time calculation of subtotal, 10% tax, ₹20 delivery fee, and grand total
- Persistent cart per authenticated user (stored in database)
- Cart item count badge in header

### 🎨 Frontend & User Experience
- Modern, responsive design (mobile-first)
- Reusable Thymeleaf fragments (`header.html`, `footer.html`)
- Toast notifications for actions
- Clean CSS with CSS variables and Poppins font
- Image preview on admin upload form
- Static pages: Home, Menu, About, Contact, Account

### 📧 Email Verification Workflow
- UUID-based secure verification token
- HTML email template sent on registration
- Token expiry handling
- Account enabled only after clicking verification link

## 🛠 Tech Stack

### Backend
- Java 17+
- Spring Boot 3.x (Spring MVC, Spring Security, Spring Data JPA)
- Hibernate (JPA)
- MySQL
- BCrypt Password Encoder
- JavaMailSender (Mailtrap for dev)

### Frontend
- Thymeleaf (server-side templating)
- HTML5 + CSS3
- Font Awesome icons
- Vanilla JavaScript (toasts, image preview, mobile menu)

### Build & Tools
- Maven
- MySQL (local development)
- Mailtrap (email testing)

## 📁 Project Structure
SliceHub/
├── src/
│   ├── main/
│   │   ├── java/com/slice/
│   │   │   ├── config/               # SecurityConfig, WebConfig, PasswordConfig
│   │   │   ├── controller/           # Auth, Menu, Cart, AdminPizza, Customer
│   │   │   ├── model/                # User, Role, Pizza, Cart, CartItem, VerificationToken
│   │   │   ├── repository/           # JPA Repositories
│   │   │   ├── service/              # Interfaces + Impl (User, Pizza, Cart, Email)
│   │   │   └── security/             # CustomUserDetailsService, SuccessHandler
│   │   ├── resources/
│   │   │   ├── static/css/           # common.css
│   │   │   ├── templates/            # Thymeleaf views
│   │   │   │   ├── fragments/        # header.html, footer.html
│   │   │   │   ├── index.html, menu.html, cart.html, login.html, register.html...
│   │   │   │   └── admin/            # addingpizza.html, pizzas.html
│   │   └── application.properties
│   └── test/
└── pom.xml


## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8+
- Mailtrap account (or any SMTP server)

### Setup Steps
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/SliceHub.git
   cd SliceHub

2. Create MySQL database
   CREATE DATABASE pizzashop;

3. Update application.properties (if needed)

   Database credentials
   Mailtrap username/password

4. Build and run

   mvn clean install
   mvn spring-boot:run

   Or run directly from your IDE (SliceHubApplication.java)
   
5. Access the app
   
   http://localhost:8080/

   Default Admin (seed manually or via CommandLineRunner)

    Email: admin@slicehub.com  
    Password: Admin@007 (change in production!)

🔐 Authentication Flow

    Register → Receive verification email → Click link → Login → Browse Menu → Add to Cart → View Cart

🌱 Current Status & Future Enhancements
Done

Full authentication + email verification
Admin pizza CRUD with image upload
Persistent user cart with calculations
Responsive UI + fragments
Role-based access

Planned / In Progress

Complete checkout & order placement
Order entity + history (customer & admin views)
Payment gateway integration (Razorpay / Stripe sandbox)
Staff dashboard (order management)
Coupon / discount system
REST API layer + potential React/Vue frontend
Order status tracking (Preparing → Out for Delivery → Delivered)
Input validation & better error handling

🎯 Learning Outcomes
This project helped me master:

Spring Security with custom UserDetails & success handler
Email verification workflow in production-like setup
JPA relationships (OneToOne, ManyToMany, OneToMany)
File upload handling & static resource mapping
Thymeleaf fragments & layout reuse
Service-layer business logic separation

👨‍💻 Author
Rahul Raj
Thiruvananthapuram, Kerala
Built with ❤️ and extra cheese as a hands-on enterprise Java web development project.
Feel free to ⭐ the repo if you find it useful!
