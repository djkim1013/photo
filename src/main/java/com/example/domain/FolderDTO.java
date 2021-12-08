package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class FolderDTO {
    private Long id;

    private String name;

    @Singular
    private List<PhotoDTO> photos;

}
