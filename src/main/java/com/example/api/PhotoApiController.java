package com.example.api;

import com.example.domain.PhotoDto;
import com.example.entity.PhotoEntity;
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
    public Long createPhoto(@RequestBody PhotoDto.Request requestCreate){
        return photoService.createPhoto(requestCreate).getId();
    }

    //사진 모두 조회
    @GetMapping
    public List<PhotoDto.ResponseGet> getAllPhotos(){
        return photoService.findAllPhotos().stream()
                .map(e -> new PhotoDto().new ResponseGet(
                        e.getId(),
                        e.getName(),
                        e.getRegDate(),
                        e.getMemo(),
                        e.getFolder().getId()
                ))
                .collect(Collectors.toList());
    }

    //사진 이름으로 조회
    @GetMapping(params = {"name"})
    public PhotoDto.ResponseGet getPhotoByName(@RequestParam String name){
        PhotoEntity photoByName = photoService.findPhotoByName(name);
        return new PhotoDto().new ResponseGet(
                photoByName.getId(),
                photoByName.getName(),
                photoByName.getRegDate(),
                photoByName.getMemo(),
                photoByName.getFolder().getId()
        );
    }

    //사진 경로로 조회
    @GetMapping(params = {"folderId"})
    public List<PhotoDto.ResponseGet> getPhotoByFolder(@RequestParam Long folderId){
        return photoService.findPhotosInFolder(folderId).stream()
                .map(e -> new PhotoDto().new ResponseGet(
                        e.getId(),
                        e.getName(),
                        e.getRegDate(),
                        e.getMemo(),
                        e.getFolder().getId()
                ))
                .collect(Collectors.toList());
    }

//    //사진 날짜로 조회
//    @GetMapping(params = {"start","end"})
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    public List<PhotoDto> getByDateEnd(@RequestParam(name="end") LocalDateTime endDate){
//        return photoService.findPhotosByDateEnd(endDate);
//    }

    //사진 정보 수정
    @PutMapping("/{photoId}")
    public PhotoDto.ResponseGet updatePhoto(@PathVariable Long photoId,
                                            @RequestBody PhotoDto.Request requestUpdate){
        PhotoEntity photoUpdated = photoService.updatePhoto(photoId, requestUpdate);
        return new PhotoDto().new ResponseGet(
                photoUpdated.getId(),
                photoUpdated.getName(),
                photoUpdated.getRegDate(),
                photoUpdated.getMemo(),
                photoUpdated.getFolder().getId()
        );
    }

    //사진 삭제
    @DeleteMapping("/{photoId}")
    public Long deletePhoto(@PathVariable Long photoId){
        photoService.deletePhoto(photoId);
        return photoId;
    }
}
