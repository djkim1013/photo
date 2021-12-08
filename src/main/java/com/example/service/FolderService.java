package com.example.service;

import com.example.domain.FolderDto;
import com.example.entity.FolderEntity;
import com.example.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor    //setter 지양 -> 생성자 사용 ... bean에 추가가능한 타입의 final 필드 변수는 자동으로 의존성이 주입된다.)
public class FolderService {

    private final FolderRepository folderRepository;    //컴파일 시점에서 생성됨을 보장받기 위해 final 선언
    
    //폴더 생성
    @Transactional
    public Long createFolder(FolderDto folderDTO){
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.reName(folderDTO.getName());
        folderEntity = folderRepository.save(folderEntity);
        return folderEntity.getId();
    }

    //모든 폴더 조회
    public List<FolderDto> findAllFolders(){
        List<FolderEntity> resultEntity = folderRepository.findAll();
        List<FolderDto> resultDto = new ArrayList<>();
        for(FolderEntity e : resultEntity){
            FolderDto d = new FolderDto();
            d.setName(e.getName());
            resultDto.add(d);
        }
        return resultDto;
    }

    //폴더 이름으로 조회
    public FolderEntity findFolderByName(String name){
        FolderEntity byName = folderRepository.findByName(name);
        return byName;
    }

    //폴더 이름 변경
    @Transactional
    public void updateFolder(Long id, FolderEntity folderNew){
        FolderEntity folder = folderRepository.findById(id).get();
        folder.reName(folderNew.getName());
    }

    //폴더 삭제
    @Transactional
    public void deleteFolder(Long id){
        folderRepository.deleteById(id);
    }
}
