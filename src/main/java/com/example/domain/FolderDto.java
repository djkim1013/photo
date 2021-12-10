package com.example.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class FolderDto {

    private Long id;

    private String name;

    private LocalDateTime regDate;
}
