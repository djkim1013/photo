package com.example.repository;

import com.example.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    // JpaRepository : entity를 관리하는 명령어 interface. generic 설정에 따라 JPA가 자동으로 구현체를 생성한다.
    // JpaRepository = CRUD + Pagination + Batch Support etc...

    Folder findByName(String name);


}
