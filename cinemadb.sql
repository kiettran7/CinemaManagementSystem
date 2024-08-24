CREATE DATABASE IF NOT EXISTS cinemadb 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE cinemadb;

CREATE TABLE tag (
    tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tag_name NVARCHAR(255) NOT NULL
);

CREATE TABLE genre (
    genre_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    genre_name NVARCHAR(255) NOT NULL
);

CREATE TABLE movie (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_name NVARCHAR(255) NOT NULL,
    movie_image NVARCHAR(500),
    movie_price FLOAT,
    status ENUM('NOW_SHOWING', 'UPCOMING') DEFAULT 'NOW_SHOWING'
);

CREATE TABLE tag_movie (
    tag_movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tag_id BIGINT,
    movie_id BIGINT,
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

CREATE TABLE genre_movie (
    genre_movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    genre_id BIGINT,
    movie_id BIGINT,
    FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username NVARCHAR(100) NOT NULL,
    password NVARCHAR(100) NOT NULL,
    email NVARCHAR(255) unique,
    phone NVARCHAR(100) unique,
    full_name NVARCHAR(255),
    birthday DATETIME,
    joined_date DATETIME,
    avatar NVARCHAR(500),
    role ENUM('ROLE_ADMIN', 'ROLE_STAFF' , 'ROLE_CUSTOMER') DEFAULT 'ROLE_CUSTOMER'
);

CREATE TABLE show_schedule (
    show_schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT,
    show_date DATE,
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id) ON DELETE CASCADE
);

CREATE TABLE showtime (
    showtime_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_time TIME,
    end_time TIME
);

CREATE TABLE show_room (
    show_room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    show_room_name VARCHAR(100) NOT NULL
);

CREATE TABLE `show` (
    show_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    showtime_id BIGINT,
    show_room_id BIGINT,
    FOREIGN KEY (showtime_id) REFERENCES showtime(showtime_id) ON DELETE CASCADE,
    FOREIGN KEY (show_room_id) REFERENCES show_room(show_room_id) ON DELETE CASCADE
);

CREATE TABLE seat (
    seat_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    seat_name NVARCHAR(255) NOT NULL,
    show_room_id BIGINT,
    FOREIGN KEY (show_room_id) REFERENCES show_room(show_room_id) ON DELETE CASCADE
);

CREATE TABLE ticket (
    ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_price FLOAT,
    created_date DATETIME,
    `status` ENUM('PENDING', 'PAID', 'CANCELLED') DEFAULT 'PENDING',
    booking_type ENUM('PRE-BOOKED', 'WALK-IN') DEFAULT 'WALK-IN',
    show_id BIGINT,
    seat_id BIGINT,
    customer_id BIGINT, 
    staff_id  BIGINT,
    movie_id BIGINT,
    FOREIGN KEY (show_id) REFERENCES `show`(show_id),
    FOREIGN KEY (seat_id) REFERENCES seat(seat_id),
    FOREIGN KEY (customer_id) REFERENCES user(user_id),
    FOREIGN KEY (staff_id) REFERENCES user(user_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

CREATE TABLE promotion (
	promotion_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    promotion_name NVARCHAR(100),
    discount_value FLOAT
);

CREATE TABLE bill (
    bill_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_amount FLOAT,
    customer_paid FLOAT,
    promotion_id BIGINT,
    ticket_id BIGINT,
    FOREIGN KEY (ticket_id) REFERENCES ticket(ticket_id),
    FOREIGN KEY (promotion_id) REFERENCES promotion(promotion_id)
);

CREATE TABLE item (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    item_type ENUM('POPCORN', 'DRINK') NOT NULL,
    item_price FLOAT NOT NULL
);

CREATE TABLE bill_item (
	bill_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bill_id BIGINT,
    item_id BIGINT,
    quantity BIGINT DEFAULT 0,
    FOREIGN KEY (bill_id) REFERENCES bill(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(item_id) ON DELETE CASCADE
);

CREATE INDEX idx_movie_id ON tag_movie(movie_id);
CREATE INDEX idx_movie_id ON genre_movie(movie_id);
CREATE INDEX idx_movie_id ON show_schedule(movie_id);
CREATE INDEX idx_movie_id ON ticket(movie_id);
CREATE INDEX idx_movie_id ON bill(ticket_id);

-- Chèn dữ liệu mẫu vào bảng tag (thể loại phim)
INSERT INTO tag (tag_name) VALUES
  ('Hành động'),
  ('Kịch tính'),
  ('Hài hước'),
  ('Hoạt hình'),
  ('Tình cảm'),
  ('Khoa học viễn tưởng');

-- Chèn dữ liệu mẫu vào bảng genre (thể loại)
INSERT INTO genre (genre_name) VALUES
  ('Phim chiếu rạp'),
  ('Phim độc lập'),
  ('Phim hoạt hình');

-- Chèn dữ liệu mẫu vào bảng movie (phim)
INSERT INTO movie (movie_name, movie_image, movie_price, status) VALUES
  ('Người Nhện: Không Còn Nhà', 'https://image.tmdb.org/t/p/original/1vquFBTQ2N9AxOTd6LUmi5iSBLN.jpg', 150000, 'NOW_SHOWING'),
  ('Cướp biển vùng Caribbean', 'https://image.tmdb.org/t/p/original/tGcq9sA3UIvF0IvFgXVJj6SC0Tg.jpg', 120000, 'UPCOMING'),
  ('Vua Sư Tử', 'https://ss-images.saostar.vn/wp700/2019/07/15/5620661/34.jpeg', 100000, 'NOW_SHOWING');
  
-- Chèn dữ liệu mẫu vào bảng tag_movie (phim theo thể loại)
INSERT INTO tag_movie (tag_id, movie_id) VALUES
  (1, 1),  -- Hành động - Người Nhện
  (2, 1),  -- Kịch tính - Người Nhện
  (3, 3),  -- Hài hước - Vua Sư Tử
  (4, 3);  -- Hoạt hình - Vua Sư Tử 

-- Chèn dữ liệu mẫu vào bảng genre_movie (phim theo thể loại)
INSERT INTO genre_movie (genre_id, movie_id) VALUES
  (1, 1),  -- Phim chiếu rạp - Người Nhện
  (2, 2),  -- Phim độc lập - Cướp biển vùng Caribbean
  (3, 3);  -- Phim hoạt hình - Vua Sư Tử

-- Chèn dữ liệu mẫu vào bảng user (người dùng)
INSERT INTO user (username, password, email, phone, full_name, birthday, joined_date, role) VALUES
  ('admin', '123456', 'admin@cinema.com', null, 'Quản trị viên Hệ thống', NOW(), NOW(), 'ROLE_ADMIN'),
  ('staff001', '123456', 'staff@cinema.com', '0869311727', 'Nhân viên Bán vé', NOW(), NOW(), 'ROLE_STAFF'),
  ('customer001', '123456', 'customer@gmail.com', '0869372216', 'Khách hàng 1', NOW(), NOW(), 'ROLE_CUSTOMER');

-- Chèn dữ liệu mẫu vào bảng show_schedule (lịch chiếu phim)
INSERT INTO show_schedule (movie_id, show_date) VALUES
  (1, CURDATE()),  -- Người Nhện - hôm nay
  (1, DATE_ADD(CURDATE(), INTERVAL 1 DAY)), -- Người Nhện - ngày mai
  (2, DATE_ADD(CURDATE(), INTERVAL 7 DAY)); -- Cướp biển vùng Caribbean - 1 tuần tới

-- Chèn dữ liệu mẫu vào bảng showtime (giờ chiếu)
INSERT INTO showtime (start_time, end_time) VALUES
  ('10:00:00', '12:00:00'),
  ('14:00:00', '16:00:00'),
  ('18:00:00', '20:00:00');

-- Chèn dữ liệu mẫu vào bảng show_room (phòng chiếu)
INSERT INTO show_room (show_room_name) VALUES
  ('Phòng 1'),
  ('Phòng 2');

-- Chèn dữ liệu mẫu vào bảng show (suất chiếu)
INSERT INTO `show` (showtime_id, show_room_id) VALUES
  (1, 1), -- Suất 1 - Phòng 1
  (2, 1), -- Suất 2 - Phòng 1
  (2, 2), -- Suất 2 - Phòng 2
  (3, 2), -- Suất 3 - Phòng 2
  (1, 2); -- Suất 1 - Phòng 2
  
  -- Chèn dữ liệu mẫu vào bảng seat (ghế ngồi)
INSERT INTO seat (seat_name, show_room_id) VALUES
  ('A1', 1),
  ('A2', 1),
  ('A3', 1),
  ('B1', 1),
  ('B2', 1),
  ('B3', 1),
  ('C1', 2),
  ('C2', 2),
  ('C3', 2);

-- Chèn dữ liệu mẫu vào bảng ticket (vé xem phim)
INSERT INTO ticket (ticket_price, created_date, status, booking_type, show_id, seat_id, customer_id) VALUES
  (150000, NOW(), 'PENDING', 'WALK-IN', 1, 1, 2),
  (150000, NOW(), 'PENDING', 'PRE-BOOKED', 1, 2, 1),
  (100000, NOW(), 'PENDING', 'WALK-IN', 3, 1, 3);

-- Chèn dữ liệu mẫu vào bảng promotion (khuyến mãi)
INSERT INTO promotion (promotion_name, discount_value) VALUES
  ('10%', 0.1),  -- Giảm giá 10%
  ('20%', 0.2);  -- Giảm giá 20%

-- Chèn dữ liệu mẫu vào bảng bill (hóa đơn)
INSERT INTO bill (total_amount, customer_paid, promotion_id, ticket_id) VALUES
  (170000, 153000, 1, 1),  -- Áp dụng khuyến mãi 1 (10% giảm giá) cho vé 1
  (120000, 120000, NULL, 3);  -- Không áp dụng khuyến mãi cho vé 3

-- Chèn dữ liệu mẫu vào bảng item (món hàng)
INSERT INTO item (item_name, item_type, item_price) VALUES
  ('Bắp rang bơ', 'POPCORN', 50000),
  ('Bắp rang lắc phô mai', 'POPCORN', 80000),
  ('Cocacola', 'DRINK', 20000),
  ('Pepsi', 'DRINK', 30000),
  ('Lavie', 'DRINK', 10000);

-- Chèn dữ liệu mẫu vào bảng bill_item (chi tiết hóa đơn)
INSERT INTO bill_item (bill_id, item_id, quantity) VALUES
  (1, 1, 1),  -- 1 phần Bắp rang bơ cho hóa đơn 1
  (1, 3, 1),  -- 1 phần Cocacola rang bơ cho hóa đơn 1
  (2, 2, 2);  -- 2 Nước ngọt cho hóa đơn 2