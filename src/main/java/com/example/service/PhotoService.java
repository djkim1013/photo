package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.PhotoEntity;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FolderRepository folderRepository;

    //사진 저장
    @Transactional
    public Long savePhoto(PhotoDto photoDto){
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.reName(photoDto.getName());
        photoEntity.mvPath(folderRepository.findByName(photoDto.getPath()));
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
        if(photoEntity == null) return null;
        PhotoDto photoDto = new PhotoDto();
        photoDto.setName(photoEntity.getName());
        photoDto.setRegDate(photoEntity.getRegDate());
        return photoDto;
    }

    //사진 경로로 조회
    public List<PhotoDto> findPhotosByPath(String path){
        List<PhotoEntity> photoEntityList = folderRepository.findByName(path).getPhotos();
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

    private List<PhotoDto> getPhotoDtoList(List<PhotoEntity> photoEntityList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        for(PhotoEntity e : photoEntityList){
            PhotoDto d = new PhotoDto();
            d.setName(e.getName());
            d.setPath(e.getPath().getName());
            d.setRegDate(e.getRegDate());
            photoDtoList.add(d);
        }
        return photoDtoList;
    }
}
