package com.example.entity;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
public class PhotoEntity extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    private String name;

    @Column(name = "photo_memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private FolderEntity path;

    private String address;

    public void setPath(FolderEntity path){
        this.path = path;
    }

}
