package com.ttk.cinema.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long movieId;

    @Lob
    String movieImage;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    float moviePrice;
    int duration; // Thời lượng phim
    String status; // NOW_SHOWING, UPCOMING, ARCHIVED

    @ManyToMany
    @JoinTable(
            name = "tag_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<Tag> tags;

    @ManyToMany
    @JoinTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    Set<Genre> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    Set<ShowSchedule> showSchedules;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    Set<Ticket> tickets;
}
