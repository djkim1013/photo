package com.example.repository;

import com.example.entity.FolderEntity;
import com.example.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

    public PhotoEntity findByName(String name);
    public PhotoEntity findByPath(FolderEntity folder);
    public List<PhotoEntity> findAllByRegDateBetween(LocalDateTime start, LocalDateTime end);
}
