package com.example.domain;

import com.example.entity.FolderEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FolderDto implements Serializable {

    private Long id;
    private String name;
    private LocalDateTime regDate;
    private List<PhotoDto> photoDtoList;

    @AllArgsConstructor
    public class PhotoListHidden{
        private Long id;
        private String name;
        private LocalDateTime regDate;
    }

    //entity 정보값을 가진 dto 생성
    public static FolderDto entityToDto(FolderEntity folderEntity){
        return new FolderDto(
                folderEntity.getId(),
                folderEntity.getName(),
                folderEntity.getRegDate(),
                PhotoDto.entityListToDtoList(folderEntity.getPhotoList())
        );
    }

    //entity 리스트의 정보값을 가진 dto 리스트 생성
    public static List<FolderDto> entityListToDtoList(List<FolderEntity> folderEntityList){
        return folderEntityList.stream()
                .map(FolderDto::entityToDto)
                .collect(Collectors.toList());
    }
}
