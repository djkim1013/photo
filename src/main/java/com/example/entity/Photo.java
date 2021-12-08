package com.example.entity;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class Photo extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    private String name;

    @Column(name = "photo_memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder path;

    private String address;

    public void setPath(Folder path){
        this.path = path;
    }

}
