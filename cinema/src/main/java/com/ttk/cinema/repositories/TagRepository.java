package com.ttk.cinema.repositories;

import com.ttk.cinema.POJOs.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    @Override
    List<Tag> findAllById(Iterable<String> strings);
}
