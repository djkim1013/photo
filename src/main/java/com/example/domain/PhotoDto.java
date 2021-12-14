package com.example.domain;

import com.example.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PhotoDto{
    private Long id;
    private String name;
    private LocalDateTime regDate;
    private String memo;
    private Long folder;

    public static PhotoDto newDto(Photo photoEntity){
        return new PhotoDto(
                photoEntity.getId(),
                photoEntity.getName(),
                photoEntity.getRegDate(),
                photoEntity.getMemo(),
                photoEntity.getFolder().getId()
        );
    }
}
