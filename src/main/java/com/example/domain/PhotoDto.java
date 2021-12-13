package com.example.domain;


import com.example.entity.PhotoEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Builder
public class PhotoDto extends Dto{
//    private Long id;
//
//    private String name;
//
//    private LocalDateTime regDate;

    private String memo;

    private Long folder;

    //entity 정보값을 가진 dto 생성
    public static PhotoDto entityToDto(PhotoEntity photoEntity){
        PhotoDto photoDto = new PhotoDto();
        photoDto.id = photoEntity.getId();
        photoDto.name = photoEntity.getName();
        photoDto.regDate = photoEntity.getRegDate();
        photoDto.memo = photoEntity.getMemo();
        photoDto.folder = photoEntity.getFolder().getId();
        return photoDto;

//        return PhotoDto.builder()
//                .id(photoEntity.getId())
//                .name(photoEntity.getName())
//                .memo(photoEntity.getMemo())
//                .folder(photoEntity.getFolder().getId())
//                .regDate(photoEntity.getRegDate())
//                .build();
    }

    public

    //entity 리스트의 정보값을 가진 dto 리스트 생성
    public static List<PhotoDto> getPhotoDtoList(List<PhotoEntity> photoEntityList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        for(PhotoEntity photoEntity : photoEntityList){
            photoDtoList.add(PhotoDto.entityToDto(photoEntity));
        }
        return photoDtoList;
    }


}
