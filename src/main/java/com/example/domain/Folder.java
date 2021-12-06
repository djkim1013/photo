package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter //@Setter
public class Folder extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    @Setter
    private String name;

    @OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public void addPhoto(Photo photo){
        photos.add(photo);
        photo.setPath(this);
    }
}
