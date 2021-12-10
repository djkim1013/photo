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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final FolderRepository folderRepository;

    //사진 저장
    @Transactional
    public Long createPhoto(PhotoDto photoDto){
        //need null check
        FolderEntity folderEntity = folderRepository.findById(photoDto.getFolder()).get();
        PhotoEntity photoEntity = PhotoEntity.builder()
                .name(photoDto.getName())
                .memo(photoDto.getMemo())
                .folder(folderEntity)
                .build();
        PhotoEntity photoEntityCreated = photoRepository.save(photoEntity);
        return photoEntityCreated.getId();
    }

    //모든 사진 조회
    public List<PhotoDto> findAllPhotoList(){
        List<PhotoEntity> photoEntityList = photoRepository.findAll();
        return getPhotoDtoList(photoEntityList);
    }

    //사진 이름으로 조회
    public PhotoDto findPhotoByName(String name){
        PhotoEntity photoEntity = photoRepository.findByName(name);
        return entityToDto(photoEntity);
    }

    //사진 경로로 조회
    public List<PhotoDto> findPhotoListByPath(Long folderId){
        //need null check
        List<PhotoEntity> photoEntityList = folderRepository.findById(folderId).get().getPhotoList();
        return getPhotoDtoList(photoEntityList);
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

    //entity 정보값을 가진 dto 생성
    private PhotoDto entityToDto(PhotoEntity photoEntity){
        return PhotoDto.builder()
                .id(photoEntity.getId())
                .name(photoEntity.getName())
                .memo(photoEntity.getMemo())
                .folder(photoEntity.getFolder().getId())
                .regDate(photoEntity.getRegDate())
                .build();
    }

    //entity 리스트의 정보값을 가진 dto 리스트 생성
    private List<PhotoDto> getPhotoDtoList(List<PhotoEntity> photoEntityList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        for(PhotoEntity photoEntity : photoEntityList){
            photoDtoList.add(entityToDto(photoEntity));
        }
        return photoDtoList;
    }
}
