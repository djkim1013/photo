package com.example.repository;

import com.example.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    public Photo findByName(String name);
    public List<Photo> findAllByRegDateBetween(LocalDateTime start, LocalDateTime end);
}
