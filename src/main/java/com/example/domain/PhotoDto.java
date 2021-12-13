package com.example.domain;


import com.example.entity.PhotoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PhotoDto{
    private Long id;
    private String name;
    private LocalDateTime regDate;
    private String memo;
    private Long folder;

    //entity를 dto로 변환
    public static PhotoDto entityToDto(PhotoEntity photoEntity){
        return new PhotoDto(
                photoEntity.getId(),
                photoEntity.getName(),
                photoEntity.getRegDate(),
                photoEntity.getMemo(),
                photoEntity.getFolder().getId()
        );
    }

    //entity 리스트를 dto 리스트로 변환
    public static List<PhotoDto> entityListToDtoList(List<PhotoEntity> photoEntityList){
        return photoEntityList.stream()
                .map(PhotoDto::entityToDto)
                .collect(Collectors.toList());
    }
}
