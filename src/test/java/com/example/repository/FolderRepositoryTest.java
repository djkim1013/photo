package com.example.repository;

import com.example.domain.Folder;
import com.example.PhotoApplication;
import com.example.service.FolderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FolderRepositoryTest {
    @Autowired FolderService folderService;
    @Autowired FolderRepository folderRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    public void addFolder() throws Exception{
        Folder folder = new Folder();
        folder.setName("folder1");

        Long saveId = folderService.makeNew(folder);
        //em.flush();
        assertEquals(folder,folderRepository.findOne(saveId));
    }

}