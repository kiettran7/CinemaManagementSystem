package com.ttk.cinema.configurations;

import com.ttk.cinema.POJOs.*;
import com.ttk.cinema.repositories.*;
import com.ttk.cinema.services.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    GenreRepository genreRepository;
    TagRepository tagRepository;
    ShowRoomRepository showRoomRepository;
    SeatRepository seatRepository;
    PromotionRepository promotionRepository;
    ItemRepository itemRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!databaseExists()) {
                initializeGenres();
                initializeTags();
                initializeShowRooms_SeatsOfRoom();
                initializePromotions();
                initializeItems();

                Role adminRole = Role.builder().name("ADMIN").description("admin role").build();
                Role staffRole = Role.builder().name("STAFF").description("staff role").build();
                Role customerRole = Role.builder().name("CUSTOMER").description("customer role").build();
                roleRepository.saveAll(List.of(adminRole, staffRole, customerRole));

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it!");
            } else {
                log.info("Database already exists, initialization skipped.");
            }
        };
    }

    private boolean databaseExists() {
        try {
            // Kiểm tra xem có ít nhất một Role trong cơ sở dữ liệu hay không
            return roleRepository.count() > 0;
        } catch (DataAccessException e) {
            log.error("Database not found, initializing...", e);
            return false; // Nếu không tìm thấy, trả về false
        }
    }

    private void initializeGenres() {
        Genre action = Genre.builder().genreName("Hành động").build();
        Genre sciFi = Genre.builder().genreName("Khoa học viễn tưởng").build();
        Genre comedy = Genre.builder().genreName("Hài hước").build();
        Genre psychology = Genre.builder().genreName("Tâm lý").build();
        Genre horror = Genre.builder().genreName("Kinh dị").build();
        Genre mythology = Genre.builder().genreName("Thần thoại").build();
        Genre comedyGenre = Genre.builder().genreName("Hài").build();

        List<Genre> genres = List.of(action, sciFi, comedy, psychology, horror, mythology, comedyGenre);
        genreRepository.saveAll(genres);
    }

    private void initializeTags() {
        Tag interesting = Tag.builder().tagName("Thú vị").build();
        Tag outstanding = Tag.builder().tagName("Nổi bật").build();
        Tag mustSee = Tag.builder().tagName("Đáng xem").build();
        Tag goodMovie = Tag.builder().tagName("Phim hay").build();
        Tag entertainment = Tag.builder().tagName("Giải trí").build();

        List<Tag> tags = List.of(interesting, outstanding, mustSee, goodMovie, entertainment);
        tagRepository.saveAll(tags);
    }

    private void initializeShowRooms_SeatsOfRoom() {
        ShowRoom roomA = ShowRoom.builder().showRoomName("Phòng A").build();
        ShowRoom roomB = ShowRoom.builder().showRoomName("Phòng B").build();
        ShowRoom roomC = ShowRoom.builder().showRoomName("Phòng C").build();
        ShowRoom roomD = ShowRoom.builder().showRoomName("Phòng D").build();

        List<ShowRoom> showRooms = List.of(roomA, roomB, roomC, roomD);
        showRoomRepository.saveAll(showRooms);

        List<Seat> seats = new ArrayList<>();

        // Ghế trong các phòng
        for (ShowRoom room : showRooms) {
            for (int i = 1; i <= 20; i++) {
                seats.add(Seat.builder().seatName(room.getShowRoomName() + i).showRoom(room).build());
            }
        }

        seatRepository.saveAll(seats);
    }

    private void initializePromotions() {
        Promotion promotion1 = Promotion.builder().promotionName("Giảm 10%").discountValue(10).build();
        Promotion promotion2 = Promotion.builder().promotionName("Giảm 20%").discountValue(20).build();
        Promotion promotion3 = Promotion.builder().promotionName("Giảm 30%").discountValue(30).build();

        List<Promotion> promotions = List.of(promotion1, promotion2, promotion3);
        promotionRepository.saveAll(promotions);
    }

    private void initializeItems() {
        Item drink = Item.builder().itemName("Nước ngọt").itemType("DRINK").itemPrice(20000).build();
        Item popcorn = Item.builder().itemName("Bỏng ngô").itemType("FOOD").itemPrice(30000).build();
        Item candy = Item.builder().itemName("Kẹo").itemType("FOOD").itemPrice(15000).build();

        List<Item> items = List.of(drink, popcorn, candy);
        itemRepository.saveAll(items);
    }
}