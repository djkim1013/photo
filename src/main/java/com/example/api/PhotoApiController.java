package com.example.api;

import com.example.domain.PhotoDto;
import com.example.entity.Photo;
import com.example.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("photo")
@RequiredArgsConstructor
public class PhotoApiController {

    private final PhotoService photoService;

    //사진 저장
    @PostMapping
    public Long createPhoto(@RequestBody PhotoDto requestCreate){
        return photoService.createPhoto(requestCreate).getId();
    }

    //사진 모두 조회
    @GetMapping
    public List<PhotoDto> getAllPhotos(){
        return photoService.findAllPhotos().stream()
                .map(PhotoDto::newDto)
                .collect(Collectors.toList());
    }

    //사진 이름으로 조회
    @GetMapping(params = {"name"})
    public PhotoDto getPhotoByName(@RequestParam String name){
        return PhotoDto.newDto(photoService.findPhotoByName(name));
    }

    //사진 경로로 조회
    @GetMapping(params = {"folderId"})
    public List<PhotoDto> getPhotoByFolder(@RequestParam Long folderId){
        return photoService.findPhotosInFolder(folderId).stream()
                .map(PhotoDto::newDto)
                .collect(Collectors.toList());
    }

    //사진 날짜로 조회

    //사진 정보 수정
    @PutMapping("/{photoId}")
    public PhotoDto updatePhoto(@PathVariable Long photoId,
                                         @RequestBody PhotoDto requestUpdate){
        Photo photoUpdated = photoService.updatePhoto(photoId, requestUpdate);
        return PhotoDto.newDto(photoUpdated);
    }

    //사진 삭제
    @DeleteMapping("/{photoId}")
    public Long deletePhoto(@PathVariable Long photoId){
        photoService.deletePhoto(photoId);
        return photoId;
    }
}
