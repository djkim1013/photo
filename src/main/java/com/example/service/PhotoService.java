package com.example.service;

import com.example.domain.Folder;
import com.example.domain.Photo;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FolderRepository folderRepository;

    //사진 저장
    @Transactional
    public Long savePhoto(Photo photo){
        photoRepository.save(photo);
        return photo.getId();
    }

    //모든 사진 조회
    public List<Photo> findAllPhotos(){
        return photoRepository.findAll();
    }

    //사진 이름으로 조회
    public Photo findPhotoByName(String name){
        return photoRepository.findByName(name);
    }

    //사진 경로로 조회
    public List<Photo> findPhotosByPath(String path){
        return folderRepository.findByName(path).getPhotos();
    }
    
    //사진 저장 날짜로 조회
    public List<Photo> findPhotosByDate(LocalDateTime start, LocalDateTime end){
        if(start == null) start = LocalDateTime.MIN;
        if(end == null) end = LocalDateTime.MAX;
        return photoRepository.findAllByRegDateBetween(start,end);
    }

    //사진 삭제
    public void deletePhotoById(Long id){
        photoRepository.deleteById(id);
    }

}
