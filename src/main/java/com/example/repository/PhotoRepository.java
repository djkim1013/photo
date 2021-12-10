package com.example.repository;

import com.example.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    PhotoEntity findByName(String name);
}
