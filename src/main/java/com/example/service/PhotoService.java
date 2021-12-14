package com.example.service;

import com.example.domain.PhotoDto;
import com.example.entity.Photo;
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
    public Photo createPhoto(PhotoDto requestCreate){
        //이름 중복 체크
        if(photoRepository.findByName(requestCreate.getName())!=null) throw new IllegalArgumentException();

        Photo photoCreate = new Photo(
                requestCreate.getName(),
                requestCreate.getMemo(),
                folderRepository.findById(requestCreate.getFolder()).orElseThrow(IllegalArgumentException::new)
        );
        return photoRepository.save(photoCreate);
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
    public List<Photo> findPhotosInPeriod(LocalDateTime start, LocalDateTime end){
        return photoRepository.findByRegDateBetween(start, end);
    }

    //사진 정보 수정
    @Transactional
    public Photo updatePhoto(Long id, PhotoDto requestUpdate){
        Photo photoUpdate = photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        //이름 중복 체크
        Photo photoByName = photoRepository.findByName(requestUpdate.getName());
        if(photoByName != null && photoByName != photoUpdate) throw new IllegalArgumentException();

        photoUpdate.updatePhoto(
                requestUpdate.getName(),
                requestUpdate.getMemo(),
                folderRepository.findById(requestUpdate.getFolder()).orElseThrow(IllegalArgumentException::new)
        );
        return photoUpdate;
    }

    //사진 삭제
    @Transactional
    public void deletePhoto(Long id){
        photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        photoRepository.deleteById(id);
    }
}
