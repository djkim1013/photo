package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.FolderEntity;
import com.example.entity.PhotoEntity;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long createPhoto(PhotoDto photoDto){
        Optional<FolderEntity> folderEntity = folderRepository.findById(photoDto.getFolder());
        if(!folderEntity.isPresent()) return -1L;
        //need null check
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
    public List<PhotoDto> findAllPhotoList(){
        List<PhotoEntity> photoEntityList = photoRepository.findAll();
        return PhotoDto.getPhotoDtoList(photoEntityList);
    }

    //사진 이름으로 조회
    public PhotoDto findPhotoByName(String name){
        PhotoEntity photoEntity = photoRepository.findByName(name);
        return PhotoDto.photoDtoRequest(photoEntity);
    }

    //사진 경로로 조회
    public List<PhotoDto> findPhotoListByPath(Long folderId){
        //need null check
        List<PhotoEntity> photoEntityList = folderRepository.findById(folderId).get().getPhotoList();
        return PhotoDto.getPhotoDtoList(photoEntityList);
    }

//    //사진 저장 날짜로 조회
//    public List<PhotoDto> findPhotosByDateEnd(LocalDateTime end){
//        List<PhotoEntity> photoEntityList = photoRepository.findAllByRegDateLessThan(end);
//        return getPhotoDtoList(photoEntityList);
//    }

    //사진 정보 수정
    @Transactional
    public void updatePhoto(Long id, PhotoDto photoDto){
        //need null check
        FolderEntity folderEntity = folderRepository.findById(photoDto.getFolder()).get();
        //need null check
        photoRepository.findById(id).get().updatePhoto(photoDto,folderEntity);

    }

    //사진 삭제
    @Transactional
    public void deletePhoto(Long id){
        photoRepository.deleteById(id);
    }

}
