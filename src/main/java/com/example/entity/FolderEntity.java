package com.example.entity;

import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "folder")
@Getter
public class FolderEntity extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name",
            unique = true)
    private String name;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "path", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "path", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhotoEntity> photos = new ArrayList<>();

    public void reName(String name){
        this.name = name;
    }

//    public void addPhoto(Photo photo){
//        photos.add(photo);
//        photo.setPath(this);
//    }

}
