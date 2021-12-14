package com.example.domain;

import com.example.entity.Folder;
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
    private List<PhotoDto> photoList;

    public class WoPhotoList{
        private Long id = FolderDto.this.id;
        private String name = FolderDto.this.name;
        private LocalDateTime regDate = FolderDto.this.regDate;
        private Integer PhotoListLength = FolderDto.this.photoList.size();
    }

    public static FolderDto newDto(Folder folderEntity){
        return new FolderDto(
                folderEntity.getId(),
                folderEntity.getName(),
                folderEntity.getRegDate(),
                folderEntity.getPhotoList().stream().map(PhotoDto::newDto).collect(Collectors.toList()));
    }

    public static WoPhotoList newDtoWoPhotoList(Folder folderEntity){
        return new FolderDto(
                folderEntity.getId(),
                folderEntity.getName(),
                folderEntity.getRegDate(),
                null)
                .new WoPhotoList();
    }

}