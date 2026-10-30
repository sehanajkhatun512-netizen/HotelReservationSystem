CREATE DATABASE IF NOT EXISTS hotel_reservation_db;
USE hotel_reservation_db;
CREATE TABLE `bookings` (
   `booking_id` int NOT NULL AUTO_INCREMENT,
   `customer_name` varchar(100) NOT NULL,
   `customer_email` varchar(100) NOT NULL,
   `room_id` int NOT NULL,
   `check_in` date NOT NULL,
   `check_out` date NOT NULL,
   `total_amount` decimal(10,2) NOT NULL,
   `booking_status` varchar(20) DEFAULT 'CONFIRMED',
   PRIMARY KEY (`booking_id`),
   KEY `fk_bookings_rooms_idx` (`room_id`),
   CONSTRAINT `fk_bookings_rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rooms` (
   `room_id` int NOT NULL AUTO_INCREMENT,
   `room_number` varchar(10) NOT NULL,
   `room_type` varchar(30) NOT NULL,
   `price_per_night` decimal(10,2) NOT NULL,
   `is_available` tinyint(1) DEFAULT '1',
   PRIMARY KEY (`room_id`),
   UNIQUE KEY `room_number_UNIQUE` (`room_number`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;