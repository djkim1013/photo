package com.example.repository;

import com.example.domain.Folder;
import com.example.domain.PhotoApplication;
import com.example.service.FolderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PhotoApplication.class)
@Transactional
class FolderRepositoryTest {
    @Autowired FolderService folderService;
    @Autowired FolderRepository folderRepository;
    @Autowired EntityManager em;

    @Test
    public void addFolder() throws Exception{
        Folder folder = new Folder();
        folder.setName("folder1");

        Long saveId = folderService.makeNew(folder);
        assertEquals(folder,folderRepository.findOne(saveId));

    }

    @Test
    public void getFolders() throws Exception{
        List<Folder> folderList = folderRepository.findAll();

    }
}