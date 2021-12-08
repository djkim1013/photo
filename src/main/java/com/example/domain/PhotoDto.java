package com.example.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDto {

    private Long id;

    private String name;

    private FolderDto path;


}
