package com.example.domain;

import com.example.entity.FolderEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class FolderDto {

    private Long id;

    private String name;

    private LocalDateTime regDate;

    private List<PhotoDto> photoDtoList;

    //entity 정보값을 가진 dto 생성
    public static FolderDto entityToDto(FolderEntity folderEntity){
        return FolderDto.builder()
                .id(folderEntity.getId())
                .regDate(folderEntity.getRegDate())
                .name(folderEntity.getName())

                //폴더 정보 조회 시 사진 목록 포함여부
//                .photoDtoList(PhotoDto.getPhotoDtoList(folderEntity.getPhotoList()))

                .build();
    }

    //entity 리스트의 정보값을 가진 dto 리스트 생성
    public static List<FolderDto> entityListToDtoList(List<FolderEntity> folderEntityList){
        List<FolderDto> folderDtoList = new ArrayList<>();
        for(FolderEntity folderEntity : folderEntityList){
            folderDtoList.add(FolderDto.entityToDto(folderEntity));
        }
        return folderDtoList;
    }
}
