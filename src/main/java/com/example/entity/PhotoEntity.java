package com.example.entity;

import com.example.domain.PhotoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "photo")
@Getter
@NoArgsConstructor
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
    private FolderEntity folder;

    public PhotoEntity(String name, String memo, FolderEntity folder){
        this.name = name;
        this.memo = memo;
        this.folder = folder;
    }

    public void updatePhoto(String name, String memo, FolderEntity folder){
        this.name = name;
        this.memo = memo;
        this.folder = folder;
    }

}
