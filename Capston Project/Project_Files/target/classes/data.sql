INSERT INTO transport (type, source, destination, price, available_seats, departure_time) VALUES ('Bus', 'Bangalore', 'Chennai', 850.00, 45, '10:00 AM');
INSERT INTO transport (type, source, destination, price, available_seats, departure_time) VALUES ('Train', 'Bangalore', 'Hydrabad', 1200.00, 100, '06:30 PM');
INSERT INTO transport (type, source, destination, price, available_seats, departure_time) VALUES ('Flight', 'Mumbai', 'Delhi', 5500.00, 150, '09:00 AM');
INSERT INTO transport (type, source, destination, price, available_seats, departure_time) VALUES ('Bus', 'Pune', 'Goa', 1500.00, 30, '11:00 PM');

INSERT INTO hotel (name, city, rating, room_type, price_per_night, available_rooms) VALUES ('Grand Palace Hotel', 'Bangalore', 4.5, 'Deluxe', 2500.00, 10);
INSERT INTO hotel (name, city, rating, room_type, price_per_night, available_rooms) VALUES ('Sea View Resort', 'Goa', 4.8, 'Suite', 4200.00, 5);
INSERT INTO hotel (name, city, rating, room_type, price_per_night, available_rooms) VALUES ('City Comforts', 'Mumbai', 3.9, 'Standard', 1800.00, 20);
INSERT INTO hotel (name, city, rating, room_type, price_per_night, available_rooms) VALUES ('Hill Top Inn', 'Ooty', 4.2, 'Double', 3000.00, 8);

INSERT INTO food (name, city, type, price, available_quantity) VALUES ('South Indian Meals', 'Bangalore', 'Veg', 180.00, 25);
INSERT INTO food (name, city, type, price, available_quantity) VALUES ('Chicken Biryani', 'Hyderabad', 'Non-Veg', 250.00, 15);
INSERT INTO food (name, city, type, price, available_quantity) VALUES ('Paneer Butter Masala', 'Mumbai', 'Veg', 220.00, 30);
INSERT INTO food (name, city, type, price, available_quantity) VALUES ('Fish Curry', 'Goa', 'Non-Veg', 350.00, 12);

-- Roles
INSERT INTO role (role_id, name) VALUES (1, 'USER');
INSERT INTO role (role_id, name) VALUES (2, 'ADMIN');

-- Sample User
INSERT INTO user (user_id, name, email, password) VALUES (1, 'Sanjay', 'sanjay@example.com', '$2a$10$D.h..'); -- password hash or plain if testing

-- Assign Admin Role to User 1
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

-- Note: Users can register via the UI.
