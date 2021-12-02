package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Photo {
    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    private String name;

    @Column(name = "photo_memo")
    private String memo;

    @Column(name = "photo_regdate")
    private LocalDateTime regdate;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @Column(name = "photo_path")
    private Folder path;
}
