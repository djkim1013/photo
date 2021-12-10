package com.example.service;

import com.example.domain.FolderDto;
import com.example.entity.FolderEntity;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) //이때 insert되면?
@RequiredArgsConstructor    //setter 지양 -> 생성자 사용 ... bean에 추가가능한 타입의 final 필드 변수는 자동으로 의존성이 주입된다.)
public class FolderService {

    private final FolderRepository folderRepository;    //컴파일 시점에서 생성됨을 보장받기 위해 final 선언
    
    //폴더 생성
    @Transactional
    public Long createFolder(FolderDto folderDTO){
        FolderEntity folderEntity = FolderEntity.builder()
                .name(folderDTO.getName())
                .build();
        FolderEntity folderEntityCreated = folderRepository.save(folderEntity);
        return folderEntityCreated.getId();
    }

    //모든 폴더 조회
    public List<FolderDto> findAllFolderList(){
        List<FolderEntity> folderEntityList = folderRepository.findAll();
        return FolderDto.entityListToDtoList(folderEntityList);
    }

    //폴더 조건으로 조회
    public FolderDto findFolderByName(String name){
        FolderEntity folderEntity = folderRepository.findByName(name);
        return FolderDto.entityToDto(folderEntity);
    }

    //폴더 이름 변경
    @Transactional
    public void updateFolder(Long id, FolderDto folderDto){
        //need null check
        FolderEntity folder = folderRepository.findById(id).get();
        folder.updateFolder(folderDto);
    }

    //폴더 삭제
    @Transactional
    public void deleteFolder(Long id){
        folderRepository.deleteById(id);
    }

}
