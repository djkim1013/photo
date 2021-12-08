package com.example.repository;

import com.example.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    // JpaRepository : entity를 관리하는 명령어 interface. generic 설정에 따라 JPA가 자동으로 구현체를 생성한다.

    FolderEntity findByName(String name);
}
