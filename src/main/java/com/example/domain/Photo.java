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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder path;

    protected Photo(){
    }

    public Photo(String name, String memo){
        this.name = name;
        this.memo = memo;
    }
}