🏨 Hall Reservation & Real-Time Availability
Hotel Management System for Weddings
📌 Project Overview

This project is a web-based hotel management system developed for wedding hall reservations. The system allows users to register, log in, check hall availability in real time, and manage bookings easily. The system also includes catering and menu management features for wedding events.

🚀 Features
👤 User Management
User Registration
User Login
Session Validation
User Dashboard
🏨 Hall Reservation
Add Hall Booking
View Bookings
Update Booking
Delete Booking
Real-Time Availability Checking
Prevent Duplicate Bookings
🍽 Catering & Menu Management
Add Menu Packages
View Menu Packages
Update Menu
Delete Menu
Price Management
✅ Validation Features
Required field validation
Invalid input checking
Guest count validation
Duplicate booking prevention
Price validation
🛠 Technologies Used
Technology	Usage
Spring Boot	Backend Development
Java	Programming Language
MySQL	Database
HTML	Frontend Structure
CSS	UI Design
JavaScript	Frontend Interaction
IntelliJ IDEA	Development IDE
GitHub	Version Control
📂 Project Structure
Hall-Reservation/
│
├── src/main/java/com/example/demo/
│   ├── controller/
│   │   ├── UserController.java
│   │   ├── HallController.java
│   │   └── MenuController.java
│   │
│   ├── model/
│   │   ├── User.java
│   │   ├── HallBooking.java
│   │   └── MenuPackage.java
│   │
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── HallRepository.java
│   │   └── MenuRepository.java
│   │
│   └── HallReservationApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   │
│   └── static/
│       ├── css/
│       │   └── style.css
│       │
│       ├── images/
│       │   ├── wedding1.jpg
│       │   ├── wedding2.jpg
│       │   └── wedding3.jpg
│       │
│       ├── login.html
│       ├── register.html
│       ├── dashboard.html
│       ├── menu.html
│       └── index.html
⚙️ System Workflow
User registers an account
User logs into the system
User checks hall availability
User creates a booking
System validates booking details
System checks duplicate bookings
Booking saved into MySQL database
User can update or delete bookings
🧪 Validation Rules
Customer name cannot be empty
Hall name is required
Booking date is required
Guest count must be greater than 0
Menu price must be greater than 0
Duplicate bookings are not allowed
📊 Main Modules
Module	Description
User Management	Handles login and registration
Hall Reservation	Manages hall bookings
Real-Time Availability	Prevents duplicate bookings
Catering Management	Handles menu packages
▶️ How to Run the Project
1️⃣ Clone Repository
git clone YOUR_GITHUB_LINK
2️⃣ Open Project

Open project using:

IntelliJ IDEA
3️⃣ Configure Database

Create MySQL database:

CREATE DATABASE hall_reservation;

Update:

application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/hall_reservation
spring.datasource.username=root
spring.datasource.password=yourpassword
4️⃣ Run Application

Run:

HallReservationApplication.java
5️⃣ Open Browser
http://localhost:8080/login.html
📌 Future Enhancements
Payment Integration
Email Notifications
Online Deployment
Role-Based Authentication
Mobile Responsive Design



DROP DATABASE IF EXISTS wedding_system;
CREATE DATABASE wedding_system;
USE wedding_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'CLIENT'
);

CREATE TABLE hall_booking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    hall_name VARCHAR(255) NOT NULL,
    booking_date VARCHAR(50) NOT NULL,
    guest_count INT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Pending',
    user_id INT NOT NULL,
    CONSTRAINT uk_hall_date UNIQUE (hall_name, booking_date),
    CONSTRAINT fk_booking_user FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE menu_package (
    id INT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(255) NOT NULL,
    food_items TEXT NOT NULL,
    price_per_person DOUBLE NOT NULL,
    description VARCHAR(500),
    status VARCHAR(50) NOT NULL DEFAULT 'Available'
);




USE wedding_system;

INSERT INTO users (full_name, email, password, role) VALUES
('Admin User', 'admin@gmail.com', '1234', 'ADMIN'),
('Pragas', 'pragas@gmail.com', '1234', 'CLIENT'),
('Raju', 'raju@gmail.com', '1234', 'STAFF');

INSERT INTO hall_booking 
(customer_name, hall_name, booking_date, guest_count, status, user_id) VALUES
('Pragas', 'Grand Hall', '2026-05-10', 250, 'Pending', 2),
('Raju', 'Royal Hall', '2026-05-15', 300, 'Confirmed', 3);

INSERT INTO menu_package 
(menu_name, food_items, price_per_person, description, status) VALUES
('Silver Package', 'Rice, Chicken Curry, Salad, Dessert', 1500, 'Basic wedding menu package', 'Available'),
('Gold Package', 'Rice, Chicken, Fish, Dessert, Juice', 2500, 'Premium wedding menu package', 'Available'),
('Platinum Package', 'Rice, Chicken, Fish, Mutton, Dessert, Juice', 3500, 'Luxury wedding menu package', 'Available');
