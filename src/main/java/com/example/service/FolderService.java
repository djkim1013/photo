package com.example.service;

import com.example.entity.Folder;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor    //setter 지양 -> 생성자 사용 ... bean에 추가가능한 타입의 final 필드 변수는 자동으로 의존성이 주입된다.)
public class FolderService {

    private final FolderRepository folderRepository;    //컴파일 시점에서 생성됨을 보장받기 위해 final 선언
    
    //폴더 생성
    @Transactional
    public Long createFolder(Folder folder){
        folderRepository.save(folder);
        return folder.getId();
    }

    //모든 폴더 조회
    public List<Folder> findAllFolders(){
        return folderRepository.findAll();
    }

    //폴더 이름으로 조회
    public Folder findFolderByName(String name){
        Folder byName = folderRepository.findByName(name);
        return byName;
    }

    //폴더 이름 변경
    @Transactional
    public void updateFolder(Long id, Folder folderNew){
        Folder folder = folderRepository.findById(id).get();
        folder.reName(folderNew.getName());
    }

    //폴더 삭제
    @Transactional
    public void deleteFolder(Long id){
        folderRepository.deleteById(id);
    }
}
