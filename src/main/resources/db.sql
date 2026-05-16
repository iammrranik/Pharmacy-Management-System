-- ==========================================
-- 1. DATABASE INITIALIZATION
-- ==========================================

CREATE DATABASE IF NOT EXISTS pharmacydb;

USE pharmacydb;

-- ==========================================
-- 2. TABLE CREATION (DDL)
-- ==========================================

-- Create User Table (Roles: ADMIN, CUSTOMER)
CREATE TABLE IF NOT EXISTS users (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(100) NOT NULL,
    email             VARCHAR(100) UNIQUE NOT NULL,
    username          VARCHAR(50) UNIQUE NOT NULL,
    password          VARCHAR(255) NOT NULL,
    phone             VARCHAR(20),
    role              VARCHAR(20) NOT NULL, -- ADMIN, CUSTOMER
    created_date_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Create Supplier Table
CREATE TABLE IF NOT EXISTS suppliers (
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name         VARCHAR(100) NOT NULL,
    contact_person_name   VARCHAR(100),
    email                 VARCHAR(100),
    phone                 VARCHAR(20) NOT NULL,
    address               TEXT,
    created_date          DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Create Medicine Table
CREATE TABLE IF NOT EXISTS medicines (
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    category           VARCHAR(50),
    price              FLOAT NOT NULL,
    available_quantity INT DEFAULT 0,
    batch_no           VARCHAR(50),
    manufacture_date   DATE,
    expiry_date        DATE,
    supplier_id        INT,
    created_date_time  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

-- Create Orders Table (Status: PENDING, COMPLETED, CANCELLED, REFUNDED)
CREATE TABLE IF NOT EXISTS orders (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    customer_id       INT NOT NULL,
    customer_phone    VARCHAR(20),
    order_date_time   DATETIME DEFAULT CURRENT_TIMESTAMP,
    return_date_time  DATETIME,
    total_amount      FLOAT NOT NULL,
    refund_amount     FLOAT DEFAULT 0,
    status            VARCHAR(20) DEFAULT 'PENDING',
    seller_id         INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES users(id),
    FOREIGN KEY (seller_id) REFERENCES users(id)
);

-- Create OrderDetails Table
CREATE TABLE IF NOT EXISTS order_details (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    order_id    INT NOT NULL,
    medicine_id INT NOT NULL,
    quantity    INT NOT NULL,
    unit_price  FLOAT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (medicine_id) REFERENCES medicines(id)
);


-- ==========================================
-- 3. DATA POPULATION (DML)
-- ==========================================

-- Insert 5 sample Users
-- NOTE: After Spring Security + BCrypt implementation, plaintext passwords
-- will NOT work for login. Use the commented BCrypt INSERT below instead,
-- or re-register via POST /api/auth/register after the app starts.
--INSERT INTO users (name, email, username, password, phone, role) VALUES
--('Anik Ahmed', 'anik.admin@email.com', 'anik_admin', 'pass123', '01711000001', 'ADMIN'),
--('Sabbir Hasan', 'sabbir.cust@email.com', 'sabbir_99', 'pass123', '01822000002', 'CUSTOMER'),
--('Rina Akter', 'rina.admin@email.com', 'rina_boss', 'pass123', '01933000003', 'ADMIN'),
--('Tanvir Hossain', 'tanvir.cust@email.com', 'tanvir_h', 'pass123', '01544000004', 'CUSTOMER'),
--('Nusrat Jahan', 'nusrat.cust@email.com', 'nusrat_j', 'pass123', '01655000005', 'CUSTOMER');

-- Same 5 users with BCrypt-hashed "pass123" (ready for Spring Security login)
-- Password hash: $2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i
INSERT INTO users (name, email, username, password, phone, role) VALUES
('Anik Ahmed', 'anik.admin@email.com', 'anik_admin', '$2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i', '01711000001', 'ADMIN'),
('Sabbir Hasan', 'sabbir.cust@email.com', 'sabbir_99', '$2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i', '01822000002', 'CUSTOMER'),
('Rina Akter', 'rina.admin@email.com', 'rina_boss', '$2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i', '01933000003', 'ADMIN'),
('Tanvir Hossain', 'tanvir.cust@email.com', 'tanvir_h', '$2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i', '01544000004', 'CUSTOMER'),
('Nusrat Jahan', 'nusrat.cust@email.com', 'nusrat_j', '$2a$10$uwoMb9AM9oK2tRNOvKGHOe2TPhg903CN6qkug40tK9R0PwPJsOS1i', '01655000005', 'CUSTOMER');

-- Insert 5 sample Suppliers
INSERT INTO suppliers (supplier_name, contact_person_name, email, phone, address) VALUES
('Square Pharmaceuticals', 'Mr. Rahim', 'info@square.com', '02-123456', 'Dhaka'),
('Beximco Pharma', 'Mr. Karim', 'sales@beximco.com', '02-654321', 'Gazipur'),
('Incepta Pharmaceuticals', 'Ms. Selina', 'contact@incepta.com', '02-987654', 'Savar'),
('Renata Limited', 'Mr. Faisal', 'supply@renata.com', '02-112233', 'Mirpur'),
('ACI HealthCare', 'Mr. Jamil', 'aci.hc@aci.com', '02-445566', 'Narayanganj');

-- Insert 5 sample Medicines
INSERT INTO medicines (name, category, price, available_quantity, batch_no, manufacture_date, expiry_date) VALUES
('Napa Extend', 'Tablet', 15.0, 500, 'B-102', '2024-01-10', '2026-12-31'),
('Ace Syrup', 'Syrup', 85.0, 120, 'S-505', '2024-05-15', '2026-05-15'),
('Fexo 120mg', 'Tablet', 10.0, 300, 'F-201', '2023-11-20', '2025-11-20'),
('Seclo 20mg', 'Capsule', 7.0, 1000, 'C-303', '2024-02-05', '2026-02-05'),
('Savlon Liquid', 'Antiseptic', 150.0, 45, 'L-909', '2024-03-12', '2027-03-12');

-- Insert 5 sample Orders (customer_id, seller_id refer to users.id)
INSERT INTO orders (customer_id, customer_phone, total_amount, status, seller_id) VALUES
(2, '01700112233', 100.0, 'COMPLETED', 1),
(4, '01900445566', 255.0, 'PENDING', 3),
(2, '01800778899', 45.0, 'CANCELLED', 1),
(4, '01500223344', 300.0, 'REFUNDED', 3),
(5, '01600556677', 150.0, 'COMPLETED', 1);

-- Insert 5 sample OrderDetails
INSERT INTO order_details (order_id, medicine_id, quantity, unit_price) VALUES
(1, 1, 4, 15.0),
(1, 3, 4, 10.0),
(2, 2, 3, 85.0),
(3, 1, 3, 15.0),
(4, 5, 2, 150.0);


-- ==========================================
-- 4. UTILITY & MAINTENANCE COMMANDS
-- ==========================================

-- Check all data
SELECT * FROM users;
SELECT * FROM medicines;
SELECT * FROM orders;

-- Check inventory levels
SELECT name, available_quantity FROM medicines;

-- To clear sales records but keep medicines and users
-- TRUNCATE TABLE order_details;
-- TRUNCATE TABLE orders;

-- Update medicine stock manually (Example)
UPDATE medicines SET available_quantity = 600 WHERE id = 1;

-- To remove everything (USE WITH CAUTION)
-- DROP TABLE IF EXISTS order_details;
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS medicines;
-- DROP TABLE IF EXISTS suppliers;
-- DROP TABLE IF EXISTS users;
-- DROP DATABASE IF EXISTS pharmacydb;
