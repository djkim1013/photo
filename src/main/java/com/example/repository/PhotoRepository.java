package com.example.repository;

import com.example.entity.FolderEntity;
import com.example.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

    PhotoEntity findByName(String name);
    List<PhotoEntity> findAllByRegDateBetween(LocalDateTime start, LocalDateTime end);
}
