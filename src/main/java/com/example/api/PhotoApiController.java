package com.example.api;

import com.example.domain.PhotoDto;
import com.example.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("photo")
@RequiredArgsConstructor
public class PhotoApiController {

    private final PhotoService photoService;

    //post
    //사진 저장

    @PostMapping
    public Long create(@RequestBody PhotoDto photo){
        return photoService.create(photo);
    }

    //get
    //사진 모두 조회
    @GetMapping
    public List<PhotoDto> getAll(){
        return photoService.findAllPhotos();
    }

    //사진 이름으로 조회
    @GetMapping("/name")
    public PhotoDto getByName(@RequestParam String photoName){
        return photoService.findPhotoByName(photoName);
    }

    //사진 경로로 조회
    @GetMapping("/path")
    public List<PhotoDto> getByPath(@RequestParam String path){
        return photoService.findPhotosByPath(path);
    }

    //사진 날짜로 조회
    @GetMapping("/between")
    public List<PhotoDto> getByDate(@RequestParam LocalDateTime startDate,
                                    @RequestParam LocalDateTime endDate){
        return photoService.findPhotosByDate(startDate,endDate);
    }

    //put

    //del
    //사진 삭제
    @DeleteMapping("/{photoId}")
    public Long delete(@PathVariable Long photoId){
        photoService.deletePhoto(photoId);
        return photoId;
    }
}
