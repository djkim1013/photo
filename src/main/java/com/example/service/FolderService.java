package com.example.service;

import com.example.entity.Folder;
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
    @Transactional
    public Folder createFolder(String name){
        if(folderRepository.findByName(name) != null) throw new IllegalArgumentException();
        return folderRepository.save(new Folder(name));
    }

    //모든 폴더 조회
    public List<Folder> findAllFolders(){
        return folderRepository.findAll();
    }

    //폴더 조건으로 조회
    public Folder findFolderByName(String name){
        return folderRepository.findByName(name);
    }

    //폴더 이름 변경
    @Transactional
    public Folder updateFolder(Long id, String name){
        Folder folderEntity = folderRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(folderRepository.findByName(name) != folderEntity) throw new IllegalArgumentException();
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
