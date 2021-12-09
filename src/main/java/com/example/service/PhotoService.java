package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.FolderEntity;
import com.example.entity.PhotoEntity;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.reName(photoDto.getName());
        photoEntity.modMemo(photoDto.getMemo());
        photoEntity.mvPath(folderRepository.findById(photoDto.getPath()).get());
        PhotoEntity photoEntityCreated = photoRepository.save(photoEntity);
        return photoEntityCreated.getId();
    }

    //모든 사진 조회
    public List<PhotoDto> findAllPhotos(){
        List<PhotoEntity> photoEntityList = photoRepository.findAll();
        return getPhotoDtoList(photoEntityList);
    }

    //사진 이름으로 조회
    public PhotoDto findPhotoByName(String name){
        PhotoEntity photoEntity = photoRepository.findByName(name);
        return entityToDto(photoEntity);
    }

    //사진 경로로 조회
    public List<PhotoDto> findPhotosByPath(Long folderId){
        List<PhotoEntity> photoEntityList = folderRepository.findById(folderId).get().getPhotos();
        //need null check
        return getPhotoDtoList(photoEntityList);
    }

    //사진 저장 날짜로 조회
    public List<PhotoDto> findPhotosByDate(LocalDateTime start, LocalDateTime end){
        if(start == null) start = LocalDateTime.MIN;
        if(end == null) end = LocalDateTime.MAX;
        List<PhotoEntity> photoEntityList = photoRepository.findAllByRegDateBetween(start,end);
        return getPhotoDtoList(photoEntityList);
    }

    //사진 삭제
    @Transactional
    public void deletePhoto(Long id){
        photoRepository.deleteById(id);
    }

    private PhotoDto entityToDto(PhotoEntity photoEntity){
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photoEntity.getId());
        photoDto.setName(photoEntity.getName());
        photoDto.setMemo(photoEntity.getMemo());
        photoDto.setPath(photoEntity.getPath().getId());
        photoDto.setRegDate(photoEntity.getRegDate());
        return photoDto;
    }

    private List<PhotoDto> getPhotoDtoList(List<PhotoEntity> photoEntityList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        for(PhotoEntity photoEntity : photoEntityList){
            photoDtoList.add(entityToDto(photoEntity));
        }
        return photoDtoList;
    }
}
