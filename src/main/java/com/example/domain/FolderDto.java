package com.example.domain;

import com.example.entity.FolderEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FolderDto{

    private Long id;
    private String name;
    private LocalDateTime regDate;
    private List<PhotoDto> photoDtoList;

    public class WoPhotoList{
        private Long id = FolderDto.this.id;
        private String name = FolderDto.this.name;
        private LocalDateTime regDate = FolderDto.this.regDate;
    }

    //entity를 dto로 변환
    public static FolderDto entityToDto(FolderEntity folderEntity){
        return new FolderDto(
                folderEntity.getId(),
                folderEntity.getName(),
                folderEntity.getRegDate(),
                PhotoDto.entityListToDtoList(folderEntity.getPhotoList())
        );
    }

    //entity를 photo-list를 제외하고 dto로 변환
    public static FolderDto.WoPhotoList entityToDtoWoPhotoList(FolderEntity folderEntity){
        return new FolderDto(
                folderEntity.getId(),
                folderEntity.getName(),
                folderEntity.getRegDate(),
                null)
                .new WoPhotoList();
    }

    //entity 리스트를 dto 리스트로 변환
    public static List<FolderDto> entityListToDtoList(List<FolderEntity> folderEntityList){
        return folderEntityList.stream()
                .map(FolderDto::entityToDto)
                .collect(Collectors.toList());
    }

    //entity 리스트를 photo-list를 제외하고 dto 리스트로 변환
    public static List<FolderDto.WoPhotoList> entityListToDtoListWoPhotoList(List<FolderEntity> folderEntityList){
        return folderEntityList.stream()
                .map(FolderDto::entityToDtoWoPhotoList)
                .collect(Collectors.toList());
    }
}
