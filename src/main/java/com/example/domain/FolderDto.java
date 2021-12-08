package com.example.domain;

import com.example.entity.FolderEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class FolderDto {

    private Long id;

    private String name;

    private List<PhotoDto> photos;

}