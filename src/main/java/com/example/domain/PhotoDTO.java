package com.example.domain;

import com.example.entity.Folder;
import lombok.Builder;

@Builder
public class PhotoDTO {

    private Long id;

    private String name;

    private Folder path;

}
