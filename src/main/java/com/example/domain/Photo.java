package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
public class Photo {
    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    @Setter
    private String name;

    @Column(name = "photo_memo")
    @Setter
    private String memo;

    @Column(name = "photo_regdate")
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    @Setter
    private Folder path;

}
