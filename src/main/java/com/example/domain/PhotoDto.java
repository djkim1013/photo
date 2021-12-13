package com.example.domain;


import com.example.entity.PhotoEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PhotoDto{
    private Long id;
    private String name;
    private LocalDateTime regDate;
    private String memo;
    private Long folder;

    //entity 정보값을 가진 dto 생성
    public static PhotoDto requestDto(PhotoEntity photoEntity){
//        PhotoDto photoDto = new PhotoDto();
//        photoDto.id = photoEntity.getId();
//        photoDto.name = photoEntity.getName();
//        photoDto.regDate = photoEntity.getRegDate();
//        photoDto.memo = photoEntity.getMemo();
//        photoDto.folder = photoEntity.getFolder().getId();
//        return photoDto;

        return PhotoDto.builder()
                .id(photoEntity.getId())
                .name(photoEntity.getName())
                .regDate(photoEntity.getRegDate())
                .memo(photoEntity.getMemo())
                .folder(photoEntity.getFolder().getId())
                .build();
    }

    public static PhotoDto responseDtoShort(PhotoEntity photoEntity) {
        return PhotoDto.builder()
                .id(photoEntity.getId())
                .name(photoEntity.getName())
                .regDate(photoEntity.getRegDate())
                .memo(photoEntity.getMemo())
                .build();
    }

    public static PhotoDto responseDtoFull(PhotoEntity photoEntity) {
        return PhotoDto.builder()
                .id(photoEntity.getId())
                .name(photoEntity.getName())
                .regDate(photoEntity.getRegDate())
                .memo(photoEntity.getMemo())
                .folder(photoEntity.getFolder().getId())
                .build();
    }

    //entity 리스트의 정보값을 가진 dto 리스트 생성
    public static List<PhotoDto> getDtoList(List<PhotoEntity> photoEntityList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        for(PhotoEntity photoEntity : photoEntityList){
            photoDtoList.add(PhotoDto.responseDtoShort(photoEntity));
        }
        return photoDtoList;
    }

}
