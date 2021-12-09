package com.example.entity;

import lombok.Getter;

import javax.persistence.*;


@Entity(name = "photo")
@Getter
public class PhotoEntity extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name",
            unique = true)
    private String name;

    @Column(name = "photo_memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private FolderEntity path;

    public void reName(String name){
        this.name = name;
    }

    public void modMemo(String memo){
        this.memo = memo;
    }

    public void mvPath(FolderEntity path){
        this.path = path;
    }

}
