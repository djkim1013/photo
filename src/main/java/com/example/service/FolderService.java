package com.example.service;

import com.example.domain.Folder;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {

    //컴파일 시점에 생성 보장을 위해 final 선언
    private final FolderRepository folderRepository;
    
    /**
    * 회원 가입
    */
    @Transactional
    public Long makeNew(Folder folder){
        folderRepository.save(folder);
        return folder.getId();
    }

    /**
     * 모든 폴더 조회
     */
    public List<Folder> findFolders(){
        return folderRepository.findAll();
    }

    /**
     * 폴더명으로 조회
     */
    public List<Folder> findFoldersByName(String name){
        return folderRepository.findByName(name);
    }
}
