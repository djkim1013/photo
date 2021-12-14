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
    public PhotoEntity createPhoto(PhotoDto.Request requestCreate){
        PhotoEntity photoEntity = new PhotoEntity(
                requestCreate.getName(),
                requestCreate.getMemo(),
                folderRepository.findById(requestCreate.getFolder()).orElseThrow(IllegalArgumentException::new)
        );
        return photoRepository.save(photoEntity);
    }

    //모든 사진 조회
    public List<PhotoEntity> findAllPhotos(){
        return photoRepository.findAll();
    }

    //사진 이름으로 조회
    public PhotoEntity findPhotoByName(String name){
        return photoRepository.findByName(name);
    }

    //사진 경로로 조회
    public List<PhotoEntity> findPhotosInFolder(Long folderId){
        return folderRepository.findById(folderId).orElseThrow(IllegalArgumentException::new).getPhotoList();
    }

    //사진 저장 날짜로 조회

    //사진 정보 수정
    @Transactional
    public PhotoEntity updatePhoto(Long id, PhotoDto.Request requestUpdate){
        PhotoEntity photoEntity = photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
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
