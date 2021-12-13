package com.example.service;

import com.example.domain.FolderDto;
import com.example.entity.FolderEntity;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    
    //폴더 생성
    @Transactional
    public Long createFolder(FolderDto folderDTO){
        //이름 유효 검사
        if(folderRepository.findByName(folderDTO.getName())!=null) return -1L;
        FolderEntity folderEntityCreated = folderRepository.save(FolderEntity.newEntity(folderDTO.getName()));
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
        //id 유효 검사
        Optional<FolderEntity> folderEntity = folderRepository.findById(id);
        if(!folderEntity.isPresent()) return;
        folderEntity.get().updateFolder(folderDto);
    }

    //폴더 삭제
    @Transactional
    public void deleteFolder(Long id){
        Optional<FolderEntity> folderEntity = folderRepository.findById(id);
        if(!folderEntity.isPresent()) return;
        folderRepository.deleteById(id);
    }

}
