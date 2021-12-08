package com.example.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PhotoDto {

    private Long id;

    private String name;

    private LocalDateTime regDate;

    private String path;
}
