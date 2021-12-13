package com.example.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
abstract class Dto {
    protected Long id;

    protected String name;

    protected LocalDateTime regDate;

}
