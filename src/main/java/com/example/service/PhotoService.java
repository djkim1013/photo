package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.Photo;
import com.example.repository.FolderRepository;
import com.example.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FolderRepository folderRepository;

    //사진 저장
    @Transactional
    public Photo createPhoto(PhotoDto requestCreate){
        if(photoRepository.findByName(requestCreate.getName())!=null) throw new IllegalArgumentException();
        Photo photoEntity = new Photo(
                requestCreate.getName(),
                requestCreate.getMemo(),
                folderRepository.findById(requestCreate.getFolder()).orElseThrow(IllegalArgumentException::new)
        );
        return photoRepository.save(photoEntity);
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
    public List<Photo> findPhotosInFolder(Long folderId){
        return folderRepository.findById(folderId).orElseThrow(IllegalArgumentException::new).getPhotoList();
    }

    //사진 저장 날짜로 조회

    //사진 정보 수정
    @Transactional
    public Photo updatePhoto(Long id, PhotoDto requestUpdate){
        Photo photoEntity = photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if(photoRepository.findByName(requestUpdate.getName()) != photoEntity)
            throw new IllegalArgumentException();
        photoEntity.updatePhoto(
                requestUpdate.getName(),
                requestUpdate.getMemo(),
                folderRepository.findById(requestUpdate.getFolder()).orElseThrow(IllegalArgumentException::new)
        );
        return photoEntity;
    }

    //사진 삭제
    @Transactional
    public void deletePhoto(Long id){
        photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        photoRepository.deleteById(id);
    }

}
