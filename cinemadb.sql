CREATE DATABASE IF NOT EXISTS cinemadb 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE cinemadb;

CREATE TABLE invalidated_token
(
    id          VARCHAR(255) NOT NULL,
    expiry_time datetime     NULL,
    CONSTRAINT pk_invalidatedtoken PRIMARY KEY (id)
);

CREATE TABLE permission
(
    name          VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_permission PRIMARY KEY (name)
);

CREATE TABLE `role`
(
    name          VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (name)
);

CREATE TABLE role_permissions
(
    role_name        VARCHAR(255) NOT NULL,
    permissions_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role_permissions PRIMARY KEY (role_name, permissions_name)
);

ALTER TABLE role_permissions
    ADD CONSTRAINT fk_rolper_on_permission FOREIGN KEY (permissions_name) REFERENCES permission (name);

ALTER TABLE role_permissions
    ADD CONSTRAINT fk_rolper_on_role FOREIGN KEY (role_name) REFERENCES `role` (name);

CREATE TABLE tag
(
    tag_id   BIGINT       NOT NULL,
    tag_name VARCHAR(255) NULL,
    CONSTRAINT pk_tag PRIMARY KEY (tag_id)
);

CREATE TABLE genre
(
    genre_id   BIGINT       NOT NULL,
    genre_name VARCHAR(255) NULL,
    CONSTRAINT pk_genre PRIMARY KEY (genre_id)
);

CREATE TABLE genre_movie
(
    genre_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    CONSTRAINT pk_genre_movie PRIMARY KEY (genre_id, movie_id)
);

CREATE TABLE movie
(
    movie_id    BIGINT       NOT NULL,
    movie_image LONGTEXT     NULL,
    movie_price FLOAT        NOT NULL,
    movie_name  VARCHAR(255) NULL,
    duration    INT          NOT NULL,
    status      VARCHAR(255) NULL,
    CONSTRAINT pk_movie PRIMARY KEY (movie_id)
);

CREATE TABLE tag_movie
(
    movie_id BIGINT NOT NULL,
    tag_id   BIGINT NOT NULL,
    CONSTRAINT pk_tag_movie PRIMARY KEY (movie_id, tag_id)
);

ALTER TABLE genre_movie
    ADD CONSTRAINT fk_genmov_on_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id);

ALTER TABLE genre_movie
    ADD CONSTRAINT fk_genmov_on_movie FOREIGN KEY (movie_id) REFERENCES movie (movie_id);

ALTER TABLE tag_movie
    ADD CONSTRAINT fk_tag_movie_on_movie FOREIGN KEY (movie_id) REFERENCES movie (movie_id);

ALTER TABLE tag_movie
    ADD CONSTRAINT fk_tag_movie_on_tag FOREIGN KEY (tag_id) REFERENCES tag (tag_id);

CREATE TABLE user
(
    user_id     BIGINT       NOT NULL,
    username    VARCHAR(255) NULL,
    password    VARCHAR(255) NULL,
    email       VARCHAR(255) NULL,
    phone       VARCHAR(255) NULL,
    full_name   VARCHAR(255) NULL,
    birthday    date         NULL,
    joined_date date         NULL,
    avatar      VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

CREATE TABLE user_roles
(
    user_user_id BIGINT       NOT NULL,
    roles_name   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_user_id, roles_name)
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_name) REFERENCES `role` (name);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_user_id) REFERENCES user (user_id);

CREATE TABLE show_schedule
(
    show_schedule_id BIGINT NOT NULL,
    show_date        date   NULL,
    movie_id         BIGINT NULL,
    CONSTRAINT pk_showschedule PRIMARY KEY (show_schedule_id)
);

ALTER TABLE show_schedule
    ADD CONSTRAINT FK_SHOWSCHEDULE_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (movie_id);

CREATE TABLE showtime
(
    showtime_id BIGINT       NOT NULL,
    start_time  VARCHAR(255) NULL,
    end_time    VARCHAR(255) NULL,
    CONSTRAINT pk_showtime PRIMARY KEY (showtime_id)
);

CREATE TABLE show_room
(
    show_room_id   BIGINT       NOT NULL,
    show_room_name VARCHAR(255) NULL,
    CONSTRAINT pk_showroom PRIMARY KEY (show_room_id)
);

CREATE TABLE show_event
(
    show_id      BIGINT NOT NULL,
    showtime_id  BIGINT NULL,
    show_room_id BIGINT NULL,
    CONSTRAINT pk_showevent PRIMARY KEY (show_id)
);

ALTER TABLE show_event
    ADD CONSTRAINT FK_SHOWEVENT_ON_SHOWTIME FOREIGN KEY (showtime_id) REFERENCES showtime (showtime_id);

ALTER TABLE show_event
    ADD CONSTRAINT FK_SHOWEVENT_ON_SHOW_ROOM FOREIGN KEY (show_room_id) REFERENCES show_room (show_room_id);

CREATE TABLE seat
(
    seat_id      BIGINT       NOT NULL,
    seat_name    VARCHAR(255) NULL,
    show_room_id BIGINT       NULL,
    CONSTRAINT pk_seat PRIMARY KEY (seat_id)
);

ALTER TABLE seat
    ADD CONSTRAINT FK_SEAT_ON_SHOW_ROOM FOREIGN KEY (show_room_id) REFERENCES show_room (show_room_id);

CREATE TABLE ticket
(
    ticket_id    BIGINT       NOT NULL,
    ticket_price FLOAT        NOT NULL,
    created_date datetime     NULL,
    status       VARCHAR(255) NULL,
    booking_type VARCHAR(255) NULL,
    show_id      BIGINT       NULL,
    seat_id      BIGINT       NULL,
    customer_id  BIGINT       NULL,
    staff_id     BIGINT       NULL,
    movie_id     BIGINT       NULL,
    CONSTRAINT pk_ticket PRIMARY KEY (ticket_id)
);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES user (user_id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (movie_id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seat (seat_id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_SHOW FOREIGN KEY (show_id) REFERENCES show_event (show_id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_STAFF FOREIGN KEY (staff_id) REFERENCES user (user_id);

CREATE TABLE promotion
(
    promotion_id   BIGINT       NOT NULL,
    promotion_name VARCHAR(255) NULL,
    discount_value FLOAT        NOT NULL,
    CONSTRAINT pk_promotion PRIMARY KEY (promotion_id)
);

CREATE TABLE item
(
    item_id    BIGINT       NOT NULL,
    item_name  VARCHAR(255) NULL,
    item_type  VARCHAR(255) NULL,
    item_price FLOAT        NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (item_id)
);

CREATE TABLE bill
(
    bill_id       BIGINT NOT NULL,
    total_amount  FLOAT  NOT NULL,
    customer_paid FLOAT  NOT NULL,
    promotion_id  BIGINT NULL,
    ticket_id     BIGINT NULL,
    CONSTRAINT pk_bill PRIMARY KEY (bill_id)
);

CREATE TABLE bill_item
(
    bill_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    CONSTRAINT pk_bill_item PRIMARY KEY (bill_id, item_id)
);

ALTER TABLE bill
    ADD CONSTRAINT FK_BILL_ON_PROMOTION FOREIGN KEY (promotion_id) REFERENCES promotion (promotion_id);

ALTER TABLE bill
    ADD CONSTRAINT FK_BILL_ON_TICKET FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id);

ALTER TABLE bill_item
    ADD CONSTRAINT fk_bill_item_on_bill FOREIGN KEY (bill_id) REFERENCES bill (bill_id);

ALTER TABLE bill_item
    ADD CONSTRAINT fk_bill_item_on_item FOREIGN KEY (item_id) REFERENCES item (item_id);

CREATE INDEX idx_movie_id ON tag_movie(movie_id);
CREATE INDEX idx_movie_id ON genre_movie(movie_id);
CREATE INDEX idx_movie_id ON show_schedule(movie_id);
CREATE INDEX idx_movie_id ON ticket(movie_id);
CREATE INDEX idx_movie_id ON bill(ticket_id);

-- Thêm dữ liệu vai trò
INSERT INTO role (name, description) VALUES
('STAFF', 'Nhân viên'),
('CUSTOMER', 'Khách hàng'),
('ADMIN', 'Quản trị viên');

-- Thêm người dùng
INSERT INTO user (user_id, username, password, email, phone, full_name, birthday, joined_date) VALUES
(1, 'staff_hoang', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'hoang@example.com', '0912345678', 'Nguyễn Hoàng', '1988-07-15', CURDATE()),
(2, 'staff_hanh', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'hanh@example.com', '0912345679', 'Trần Hạnh', '1990-02-20', CURDATE()),
(3, 'customer_tuan', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'tuan@example.com', '0912345680', 'Lê Tuấn', '1995-01-25', CURDATE()),
(4, 'customer_thao', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'thao@example.com', '0912345681', 'Phạm Thảo', '1997-03-30', CURDATE()),
(5, 'customer_anh', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'anh@example.com', '0912345682', 'Trương Anh', '1999-04-10', CURDATE()),
(6, 'customer_hai', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'hai@example.com', '0912345683', 'Nguyễn Hải', '1998-05-18', CURDATE()),
(7, 'customer_linh', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'linh@example.com', '0912345684', 'Nguyễn Linh', '1996-08-14', CURDATE()),
(8, 'customer_khoa', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'khoa@example.com', '0912345685', 'Nguyễn Khóa', '1994-11-29', CURDATE()),
(9, 'customer_minh', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'minh@example.com', '0912345686', 'Nguyễn Minh', '1993-12-05', CURDATE()),
(10, 'customer_phuong', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'phuong@example.com', '0912345687', 'Nguyễn Phương', '1995-07-20', CURDATE()),
(11, 'admin_kiet', '$2a$10$gpjDXFRMUV8k/29e4X6/aO4ISJMzkGwG3n2KRrR0vS.Z8jQjp91ly', 'kiet@example.com', '0912383020', 'Kiet', '1995-07-20', CURDATE());

-- Phân quyền cho người dùng
INSERT INTO user_roles (user_user_id, roles_name) VALUES
(1, 'STAFF'),
(2, 'STAFF'),
(3, 'CUSTOMER'),
(4, 'CUSTOMER'),
(5, 'CUSTOMER'),
(6, 'CUSTOMER'),
(7, 'CUSTOMER'),
(8, 'CUSTOMER'),
(9, 'CUSTOMER'),
(10, 'CUSTOMER'),
(11, 'ADMIN');

-- Thêm thể loại
INSERT INTO genre (genre_id, genre_name) VALUES
(1, 'Hành động'),
(2, 'Khoa học viễn tưởng'),
(3, 'Hài hước'),
(4, 'Tâm lý'),
(5, 'Kinh dị'),
(6, 'Thần thoại'),
(7, 'Hài');

-- Thêm tag
INSERT INTO tag (tag_id, tag_name) VALUES
(1, 'Thú vị'),
(2, 'Nổi bật'),
(3, 'Đáng xem'),
(4, 'Phim hay'),
(5, 'Giải trí');

--
--
--
--
--
-- CHỈ THÊM TỚI ĐÂY THÔI NHAAAAAAAAAAAAAAAAAAAAAAAAAAA
--
--
--
--
--

-- Thêm phim
INSERT INTO movie (movie_id, movie_image, movie_price, movie_name, duration, status) VALUES
(1, 'https://www.posterhub.com.sg/images/detailed/137/01_1A_1B.jpg', 100000, 'Avengers: Endgame', 181, 'NOW_SHOWING'),
(2, 'https://i.ebayimg.com/images/g/LlUAAOSwm8VUwoRL/s-l400.jpg', 120000, 'Inception', 148, 'NOW_SHOWING'),
(3, 'https://i.etsystatic.com/23402008/r/il/0e5769/2499687838/il_570xN.2499687838_7a06.jpg', 150000, 'Parasite', 132, 'NOW_SHOWING'),
(4, 'https://m.media-amazon.com/images/I/714iHb8BQ3L._AC_SL1500_.jpg', 130000, 'Interstellar', 169, 'NOW_SHOWING'),
(5, 'https://i.ebayimg.com/images/g/JFcAAOSwsuVlvUQO/s-l1200.jpg', 140000, 'Dune', 155, 'UPCOMING'),
(6, 'https://i.ebayimg.com/images/g/OLIAAOSwnTNhZnrr/s-l1200.jpg', 160000, 'No Time to Die', 163, 'UPCOMING'),
(7, 'https://m.media-amazon.com/images/I/81AeYFqjCTL._AC_SL1300_.jpg', 110000, 'Spider-Man: No Way Home', 148, 'NOW_SHOWING'),
(8, 'https://i.ebayimg.com/images/g/QKwAAOSwy5RhlySx/s-l400.jpg', 125000, 'The Matrix Resurrections', 148, 'ARCHIVED'),
(9, 'https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/3/image/c5f0a1eff4c394a251036189ccddaacd/p/o/poster_shangchi.jpg', 135000, 'Shang-Chi and the Legend of the Ten Rings', 132, 'ARCHIVED'),
(10, 'https://image.tmdb.org/t/p/original/89ezV8OreaKji3cw1NnLXpADNvW.jpg', 145000, 'The Suicide Squad', 132, 'ARCHIVED'),
(11, 'https://i0.wp.com/fontlot.com/storage/2020/06/lo-anh-dodge-charger-se-dong-han.jpg', 155000, 'Fast & Furious 9', 143, 'ARCHIVED'),
(12, 'https://image.tmdb.org/t/p/original/hcJiQu002ZNdb9GVTFtT2lzJT1z.jpg', 165000, 'Jungle Cruise', 123, 'ARCHIVED'),
(13, 'https://image.tmdb.org/t/p/original/pDUrJdONXta2EN8W2dKbpVi85Es.jpg', 175000, 'A Quiet Place Part II', 97, 'ARCHIVED'),
(14, 'https://i.ebayimg.com/images/g/uMwAAOSwux5YLgjU/s-l1200.jpg', 185000, 'The Lion King', 118, 'ARCHIVED'),
(15, 'https://hips.hearstapps.com/hmg-prod/images/wonder-woman-1984-poster-1606931235.jpg', 195000, 'Wonder Woman 1984', 151, 'ARCHIVED');

-- Thêm mối quan hệ tag - phim
INSERT INTO tag_movie (tag_id, movie_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 4), (2, 5), (2, 6),
(3, 7), (3, 8), (3, 9),
(4, 10), (4, 2), (4, 3),
(5, 11), (5, 12), (5, 13), (5, 14), (5, 15);

-- Thêm mối quan hệ thể loại - phim
INSERT INTO genre_movie (genre_id, movie_id) VALUES
(1, 1), (2, 1), (1, 2), (3, 2), (1, 3), (4, 3),
(1, 4), (2, 4), (1, 5), (2, 5), (1, 6), (2, 6),
(1, 7), (2, 8), (5, 9), (6, 10), (1, 11), (4, 12), 
(3, 13), (1, 14), (2, 15);

-- Thêm phòng chiếu
INSERT INTO show_room (show_room_id, show_room_name) VALUES
(1, 'Phòng A'), (2, 'Phòng B'), (3, 'Phòng C'), (4, 'Phòng D');

-- Thêm ghế trong các phòng
INSERT INTO seat (seat_id, seat_name, show_room_id) VALUES
(1, 'A1', 1), (2, 'A2', 1), (3, 'A3', 1), (4, 'A4', 1), (5, 'A5', 1),
(6, 'A6', 1), (7, 'A7', 1), (8, 'A8', 1), (9, 'A9', 1), (10, 'A10', 1),
(11, 'A11', 1), (12, 'A12', 1), (13, 'A13', 1), (14, 'A14', 1), (15, 'A15', 1),
(16, 'A16', 1), (17, 'A17', 1), (18, 'A18', 1), (19, 'A19', 1), (20, 'A20', 1);

INSERT INTO seat (seat_id, seat_name, show_room_id) VALUES
(21, 'B1', 2), (22, 'B2', 2), (23, 'B3', 2), (24, 'B4', 2), (25, 'B5', 2),
(26, 'B6', 2), (27, 'B7', 2), (28, 'B8', 2), (29, 'B9', 2), (30, 'B10', 2),
(31, 'B11', 2), (32, 'B12', 2), (33, 'B13', 2), (34, 'B14', 2), (35, 'B15', 2),
(36, 'B16', 2), (37, 'B17', 2), (38, 'B18', 2), (39, 'B19', 2), (40, 'B20', 2);

INSERT INTO seat (seat_id, seat_name, show_room_id) VALUES
(41, 'C1', 3), (42, 'C2', 3), (43, 'C3', 3), (44, 'C4', 3), (45, 'C5', 3),
(46, 'C6', 3), (47, 'C7', 3), (48, 'C8', 3), (49, 'C9', 3), (50, 'C10', 3),
(51, 'C11', 3), (52, 'C12', 3), (53, 'C13', 3), (54, 'C14', 3), (55, 'C15', 3),
(56, 'C16', 3), (57, 'C17', 3), (58, 'C18', 3), (59, 'C19', 3), (60, 'C20', 3);

INSERT INTO seat (seat_id, seat_name, show_room_id) VALUES
(61, 'D1', 4), (62, 'D2', 4), (63, 'D3', 4), (64, 'D4', 4), (65, 'D5', 4),
(66, 'D6', 4), (67, 'D7', 4), (68, 'D8', 4), (69, 'D9', 4), (70, 'D10', 4),
(71, 'D11', 4), (72, 'D12', 4), (73, 'D13', 4), (74, 'D14', 4), (75, 'D15', 4),
(76, 'D16', 4), (77, 'D17', 4), (78, 'D18', 4), (79, 'D19', 4), (80, 'D20', 4);

-- Thêm lịch chiếu
INSERT INTO show_schedule (show_schedule_id, show_date, movie_id) VALUES
(1, '2024-09-25', 1),
(2, '2024-09-25', 2),
(3, '2024-09-25', 3),
(4, '2024-09-26', 4),
(5, '2024-09-26', 5),
(6, '2024-09-27', 6),
(7, '2024-09-27', 7);

-- Thêm thời gian chiếu
INSERT INTO showtime (showtime_id, start_time, end_time) VALUES
(1, '10:00', '12:30'),
(2, '13:00', '15:30'),
(3, '16:00', '18:30'),
(4, '19:00', '21:30');

-- Thêm sự kiện chiếu
INSERT INTO show_event (show_id, showtime_id, show_room_id) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 2),
(4, 1, 3),
(5, 4, 4),
(6, 2, 2),
(7, 3, 3);

-- Thêm vé
INSERT INTO ticket (ticket_id, ticket_price, created_date, status, booking_type, show_id, seat_id, customer_id, staff_id, movie_id) VALUES
(1, 100000, NOW(), 'BOOKED', 'ONLINE', 1, 1, 3, 1, 1),
(2, 120000, NOW(), 'BOOKED', 'IN_PERSON', 2, 21, 4, 1, 2),
(3, 150000, NOW(), 'CANCELLED', 'ONLINE', 3, 41, 5, 2, 3),
(4, 130000, NOW(), 'BOOKED', 'ONLINE', 4, 61, 6, 1, 4);

-- Thêm khuyến mãi
INSERT INTO promotion (promotion_id, promotion_name, discount_value) VALUES
(1, 'Giảm 10%', 10.0),
(2, 'Giảm 20%', 20.0),
(3, 'Giảm 30%', 30.0);

-- Thêm hóa đơn
INSERT INTO bill (bill_id, total_amount, customer_paid, promotion_id, ticket_id) VALUES
(1, 900000, 900000, 1, 1),
(2, 800000, 800000, NULL, 2),
(3, 600000, 600000, 2, 3),
(4, 130000, 130000, 3, 4);

-- Thêm mặt hàng
INSERT INTO item (item_id, item_name, item_type, item_price) VALUES
(1, 'Nước ngọt', 'Đồ uống', 20000),
(2, 'Bỏng ngô', 'Đồ ăn nhẹ', 30000),
(3, 'Kẹo', 'Đồ ăn nhẹ', 15000);

-- Thêm chi tiết hóa đơn
INSERT INTO bill_item (bill_id, item_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(4, 1);