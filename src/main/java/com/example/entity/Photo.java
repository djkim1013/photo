package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "photo")
@Getter
@NoArgsConstructor
public class Photo extends BaseAuditingEntity {

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
    private Folder folder;

    public Photo(String name, String memo, Folder folder){
        this.name = name;
        this.memo = memo;
        this.folder = folder;
    }

    public void updatePhoto(String name, String memo, Folder folder){
        this.name = name;
        this.memo = memo;
        this.folder = folder;
    }

}
