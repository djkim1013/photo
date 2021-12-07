package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Folder extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    private String name;

//    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "path", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public void addPhoto(Photo photo){
        photos.add(photo);
//        photo.setPath(this);
    }

    public void reName(String name){
        this.name = name;
    }
}
