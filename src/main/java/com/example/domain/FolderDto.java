package com.example.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class FolderDto{

    @Getter
    @AllArgsConstructor
    public class Request {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public class ResponseGetOne {
        private Long id;
        private String name;
        private LocalDateTime regDate;
        private List<PhotoDto> photoList;
    }

    @Getter
    @AllArgsConstructor
    public class ResponseGetAll{
        private Long id;
        private String name;
        private LocalDateTime regDate;
        private Integer numberOfPhotos;
    }

}