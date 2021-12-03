package com.example.service;

import com.example.domain.Folder;
import com.example.domain.Photo;
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

    //컴파일 시점에 생성 보장을 위해 final 선언
    private final PhotoRepository photoRepository;
    
    /**
    * 사진 저장
    */
    @Transactional
    public Long save(Photo photo){
        photoRepository.save(photo);
        return photo.getId();
    }

    /**
     * 모든 사진 조회
     */
    public List<Photo> findPhotos(){
        return photoRepository.findAll();
    }

    /**
     * 사진 이름으로 조회
     */
    public List<Photo> findPhotoByName(String name){
        return photoRepository.findByName(name);
    }
}
