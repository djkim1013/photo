package com.example.entity;

import com.example.domain.PhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "photo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public void updatePhoto(PhotoDto photoDto,FolderEntity folder){
        this.name = photoDto.getName();
        this.memo = photoDto.getMemo();
        this.folder = folder;
    }

}
