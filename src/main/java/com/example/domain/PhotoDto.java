package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class PhotoDto{
    @Getter
    @AllArgsConstructor
    public class Request{
        private String name;
        private String memo;
        private Long folder;
    }

    @Getter
    @AllArgsConstructor
    public class ResponseGet {
        private Long id;
        private String name;
        private LocalDateTime regDate;
        private String memo;
        private Long folder;
    }

    @Getter
    @AllArgsConstructor
    public class ResponseInFolder {
        private Long id;
        private String name;
        private LocalDateTime regDate;
        private String memo;
    }
}
