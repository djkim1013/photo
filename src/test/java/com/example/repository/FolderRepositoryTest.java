package com.example.repository;

import com.example.domain.Folder;
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
        assertEquals(folder,folderRepository.findById(saveId));
    }

    @Test
    @Rollback(false)
    public void selectFolders() throws Exception{
        Folder folder1 = new Folder();
        folder1.setName("folder1");
        folderService.makeNew(folder1);
        Folder folder2 = new Folder();
        folder2.setName("folder2");
        folderService.makeNew(folder2);
        Folder folder3 = new Folder();
        folder3.setName("folder3");
        folderService.makeNew(folder3);

        List<Folder> folderList = folderRepository.findAll();
        assertNotEquals(folderList.size(),0);

    }

}