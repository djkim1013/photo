package com.example.domain;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class PhotoDto {

    private Long id;

    private String name;

    private String memo;

    private LocalDateTime regDate;

    private Long folder;
}
