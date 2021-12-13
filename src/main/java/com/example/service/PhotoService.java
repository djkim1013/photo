package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.FolderEntity;
import com.example.entity.PhotoEntity;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FolderRepository folderRepository;

    //사진 저장
    @Transactional
    public Long create(PhotoDto photoDto){
        //이름 유효 체크
        if(photoRepository.findByName(photoDto.getName()) != null)
            return -1L;
        //폴더 유효 체크
        Optional<FolderEntity> folderEntity = folderRepository.findById(photoDto.getFolder());
        if(!folderEntity.isPresent())
            return -1L;

//        FolderEntity folderEntity = folderRepository.findById(photoDto.getFolder()).get();
//        PhotoEntity photoEntity = PhotoEntity.builder() //dto cunstructor try
//                .name(photoDto.getName())
//                .memo(photoDto.getMemo())
//                .folder(folderEntity)
//                .build();

        PhotoEntity photoEntity = PhotoEntity.newEntity(photoDto,folderEntity.get());
        PhotoEntity photoEntityCreated = photoRepository.save(photoEntity);
        return photoEntityCreated.getId();
    }

    //모든 사진 조회
    public List<PhotoDto> findAll(){
        List<PhotoEntity> photoEntityList = photoRepository.findAll();
        return PhotoDto.getDtoList(photoEntityList);
    }

    //사진 이름으로 조회
    public PhotoDto findByName(String name){
        PhotoEntity photoEntity = photoRepository.findByName(name);
        return PhotoDto.responseDtoFull(photoEntity);
    }

    //사진 경로로 조회
    public List<PhotoDto> findPhotoListByPath(Long folderId){
        //need null check
        Optional<FolderEntity> folderEntity = folderRepository.findById(folderId);
        if(!folderEntity.isPresent()) return new ArrayList<>();
        List<PhotoEntity> photoEntityList = folderEntity.get().getPhotoList();
        return PhotoDto.getDtoList(photoEntityList);
    }

//    //사진 저장 날짜로 조회
//    public List<PhotoDto> findPhotosByDateEnd(LocalDateTime end){
//        List<PhotoEntity> photoEntityList = photoRepository.findAllByRegDateLessThan(end);
//        return getPhotoDtoList(photoEntityList);
//    }

    //사진 정보 수정
    @Transactional
    public PhotoDto updatePhoto(Long id, PhotoDto photoDto){
        //ID 유효 검사
        Optional<PhotoEntity> photoEntity = photoRepository.findById(id);
        if(!photoEntity.isPresent()) return PhotoDto.builder().build();

        //폴더 유효 검사
        Optional<FolderEntity> folderEntity = folderRepository.findById(photoDto.getFolder());
        if(!folderEntity.isPresent()) return PhotoDto.builder().build();

        photoEntity.get().updatePhoto(photoDto,folderEntity.get());

        return photoDto;
    }

    //사진 삭제
    @Transactional
    public void deletePhoto(Long id){
        //ID 유효 검사
        Optional<PhotoEntity> photoEntity = photoRepository.findById(id);
        if(!photoEntity.isPresent()) return PhotoDto.builder().build();

        photoRepository.deleteById(id);
    }

}
