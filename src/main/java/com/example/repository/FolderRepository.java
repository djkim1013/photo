package com.example.repository;

import com.example.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    FolderEntity findByName(String name);
}
