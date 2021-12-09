package com.example.api;

import com.example.domain.PhotoDto;
import com.example.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    @GetMapping(params = {"name"})
    public PhotoDto getByName(@RequestParam(name = "name") String photoName){
        return photoService.findPhotoByName(photoName);
    }

    //사진 경로로 조회
    @GetMapping(params = {"path"})
    public List<PhotoDto> getByPath(@RequestParam Long path){
        return photoService.findPhotosByPath(path);
    }

    //사진 날짜로 조회
    @GetMapping(params = {"start","end"})
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public List<PhotoDto> getByDate(@RequestParam(name="start") LocalDateTime startDate,
                                    @RequestParam(name="end") LocalDateTime endDate){
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
