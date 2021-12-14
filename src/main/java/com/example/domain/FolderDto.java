package com.example.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FolderDto{
    private Long id;
    private String name;
    private LocalDateTime regDate;
    private List<PhotoDto.ResponseInFolder> photoList;

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
        private List<PhotoDto.ResponseInFolder> photoList;
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