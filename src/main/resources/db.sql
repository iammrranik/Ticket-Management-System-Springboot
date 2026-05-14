-- ==========================================
-- 1. DATABASE INITIALIZATION
-- ==========================================

CREATE DATABASE IF NOT EXISTS railwaydb;

USE railwaydb;

-- ==========================================
-- 2. TABLE CREATION (DDL)
-- ==========================================

CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS trains (
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    train_registration_number  VARCHAR(255) NOT NULL,
    train_name                 VARCHAR(255) NOT NULL,
    total_coaches              INT          NOT NULL,
    total_ac_coaches           INT          NOT NULL,
    total_non_ac_coaches       INT          NOT NULL,
    total_compartment_coaches  INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS coaches (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    train_id   INT          NOT NULL,
    coach_type VARCHAR(100) NOT NULL,
    capacity   INT          NOT NULL,
    base_fare  FLOAT        NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains(id)
);

CREATE TABLE IF NOT EXISTS stations (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    station_code VARCHAR(50)  NOT NULL,
    station_name VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS schedules (
    id                   INT AUTO_INCREMENT PRIMARY KEY,
    train_id             INT      NOT NULL,
    source_station_id    INT      NOT NULL,
    destination_station_id INT    NOT NULL,
    departure_time       DATETIME NOT NULL,
    arrival_time         DATETIME NOT NULL,
    FOREIGN KEY (train_id)              REFERENCES trains(id),
    FOREIGN KEY (source_station_id)     REFERENCES stations(id),
    FOREIGN KEY (destination_station_id) REFERENCES stations(id)
);

CREATE TABLE IF NOT EXISTS tickets (
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    user_id                 INT          NOT NULL,
    schedule_id             INT          NOT NULL,
    coach_id                INT          NOT NULL,
    seat_number             VARCHAR(50)  NOT NULL,
    booking_time            DATETIME     NOT NULL,
    total_amount            FLOAT        NOT NULL,
    status                  VARCHAR(50)  NOT NULL,
    actual_return_timestamp DATETIME     DEFAULT NULL,
    refund_amount           FLOAT        DEFAULT 0.0,
    FOREIGN KEY (user_id)     REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id),
    FOREIGN KEY (coach_id)    REFERENCES coaches(id)
);

CREATE TABLE IF NOT EXISTS return_policies (
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    policy_name           VARCHAR(255) NOT NULL,
    hours_before_departure INT         NOT NULL,
    deduction_percentage   FLOAT       NOT NULL
);

CREATE TABLE IF NOT EXISTS food_items (
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    item_name          VARCHAR(255) NOT NULL,
    category           VARCHAR(255) NOT NULL,
    price              FLOAT        NOT NULL,
    available_quantity INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS food_orders (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id       INT          NOT NULL,
    total_amount    FLOAT        NOT NULL,
    order_timestamp DATETIME     NOT NULL,
    status          VARCHAR(100) NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);

CREATE TABLE IF NOT EXISTS food_order_details (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_id     INT   NOT NULL,
    food_item_id INT   NOT NULL,
    quantity     INT   NOT NULL,
    sub_total    FLOAT NOT NULL,
    FOREIGN KEY (order_id)     REFERENCES food_orders(id),
    FOREIGN KEY (food_item_id) REFERENCES food_items(id)
);

CREATE TABLE IF NOT EXISTS train_status (
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    train_id           INT      NOT NULL,
    current_station_id INT      NOT NULL,
    next_station_id    INT      NOT NULL,
    status             VARCHAR(255) NOT NULL,
    last_updated       DATETIME NOT NULL,
    FOREIGN KEY (train_id)            REFERENCES trains(id),
    FOREIGN KEY (current_station_id)  REFERENCES stations(id),
    FOREIGN KEY (next_station_id)     REFERENCES stations(id)
);

-- ==========================================
-- 3. DATA POPULATION (DML)
-- ==========================================

INSERT INTO users (username, password, email, fullname, role) VALUES
('admin', '$2a$10$dummyhashedpassword', 'admin@railway.com', 'System Admin', 'ADMIN'),
('rahim', '$2a$10$dummyhashedpassword', 'rahim@example.com', 'Rahim Ahmed', 'USER'),
('karim', '$2a$10$dummyhashedpassword', 'karim@example.com', 'Karim Ullah', 'USER');

INSERT INTO trains (train_registration_number, train_name, total_coaches, total_ac_coaches, total_non_ac_coaches, total_compartment_coaches) VALUES
('TRAIN-001', 'Subarna Express', 12, 4, 6, 2),
('TRAIN-002', 'Tista Express', 10, 2, 6, 2);

INSERT INTO stations (station_code, station_name, city) VALUES
('DHK', 'Dhaka Railway Station', 'Dhaka'),
('CTG', 'Chittagong Railway Station', 'Chittagong'),
('SYL', 'Sylhet Railway Station', 'Sylhet'),
('RAJ', 'Rajshahi Railway Station', 'Rajshahi');

INSERT INTO schedules (train_id, source_station_id, destination_station_id, departure_time, arrival_time) VALUES
(1, 1, 2, '2026-05-20 06:00:00', '2026-05-20 12:00:00'),
(1, 2, 1, '2026-05-20 14:00:00', '2026-05-20 20:00:00'),
(2, 1, 3, '2026-05-20 07:00:00', '2026-05-20 11:30:00');

INSERT INTO coaches (train_id, coach_type, capacity, base_fare) VALUES
(1, 'AC_SLEEPER', 20, 1200.0),
(1, 'AC_CHAIR', 30, 800.0),
(1, 'NON_AC_CHAIR', 50, 450.0),
(2, 'AC_CHAIR', 30, 750.0),
(2, 'SHOVAN_CHAIR', 60, 350.0);

INSERT INTO food_items (item_name, category, price, available_quantity) VALUES
('Chicken Biryani', 'Main Course', 250.0, 50),
('Vegetable Rice', 'Main Course', 180.0, 40),
('Water Bottle', 'Beverage', 20.0, 200),
('Tea', 'Beverage', 30.0, 100),
('Cake Slice', 'Snack', 60.0, 30);

INSERT INTO return_policies (policy_name, hours_before_departure, deduction_percentage) VALUES
('Standard Return', 24, 10.0),
('Late Return', 6, 25.0);

-- ==========================================
-- 4. UTILITY & MAINTENANCE COMMANDS
-- ==========================================

-- Check all data
SELECT * FROM users;
SELECT * FROM trains;
SELECT * FROM coaches;
SELECT * FROM stations;
SELECT * FROM schedules;
SELECT * FROM tickets;
SELECT * FROM return_policies;
SELECT * FROM food_items;
SELECT * FROM food_orders;
SELECT * FROM food_order_details;
SELECT * FROM train_status;
