//package com.example.repository;
//
//import com.example.entity.FolderEntity;
//import com.example.service.FolderService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class FolderRepositoryTest {
//    @Autowired FolderService folderService;
//    @Autowired FolderRepository folderRepository;
//    @Autowired EntityManager em;
//
//    @Test
//    @Rollback(false)
//    public void addFolder() throws Exception{
//        FolderEntity folder = new FolderEntity();
//        folder.reName("folder1");
//
//        Long saveId = folderService.createFolder(folder);
//
//        assertNotEquals(folderRepository.findById(saveId).get(),null);
//    }
//
//    @Test
//    @Rollback(false)
//    public void findAllFolders() throws Exception{
//        FolderEntity folder1 = new FolderEntity();
//        folder1.reName("folder1");
//        folderService.createFolder(folder1);
//        FolderEntity folder2 = new FolderEntity();
//        folder2.reName("folder2");
//        folderService.createFolder(folder2);
//        FolderEntity folder3 = new FolderEntity();
//        folder3.reName("folder3");
//        folderService.createFolder(folder3);
//
//        List<FolderEntity> folderList = folderRepository.findAll();
//
//        assertEquals(folderList.size(),3);
//
//    }
//
//    @Test
//    @Rollback(false)
//    public void findFolderByName() throws Exception{
//        FolderEntity folder = new FolderEntity();
//        folder.reName("folder1");
//
//        Long saveId = folderService.createFolder(folder);
//
//        assertEquals(folderRepository.findByName("folder1").getId(),saveId);
//    }
//
//    @Test
//    @Rollback(false)
//    public void updateFolderByName() throws Exception{
//        FolderEntity folder = new FolderEntity();
//        folder.reName("folder1");
//        folderRepository.save(folder);
//
//        folder.reName("folder2");
//        folderService.updateFolder(1l,folder);
//
//        assertEquals(folder,folderRepository.findByName("folder2"));
//    }
//
//    @Test
//    @Rollback(false)
//    public void createdDate() throws Exception{
//        FolderEntity folder = new FolderEntity();
//        folder.reName("folder1");
//        folderRepository.save(folder);
//
//        assertNotEquals(folderRepository.findByName("folder1").getRegDate(),null);
//    }
//
//}