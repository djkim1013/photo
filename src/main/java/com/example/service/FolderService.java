package com.example.service;

import com.example.entity.FolderEntity;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    
    //폴더 생성
//    @Transactional
    public FolderEntity createFolder(String name){
        return folderRepository.save(new FolderEntity(name));
    }

    //모든 폴더 조회
    public List<FolderEntity> findAllFolders(){
        return folderRepository.findAll();
    }

    //폴더 조건으로 조회
    public FolderEntity findFolderByName(String name){
        return folderRepository.findByName(name);
    }

    //폴더 이름 변경
    @Transactional
    public FolderEntity updateFolder(Long id, String name){
        FolderEntity folderEntity = folderRepository.findById(id).orElseThrow(NoSuchElementException::new);
        folderEntity.updateFolder(name);
        return folderEntity;
    }

    //폴더 삭제
    @Transactional
    public void deleteFolder(Long id){
        folderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        folderRepository.deleteById(id);
    }



}
