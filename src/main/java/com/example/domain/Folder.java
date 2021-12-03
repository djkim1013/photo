package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Folder {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    private String name;

    @Column(name = "folder_regdate")
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    protected Folder(){
    }

    public Folder(String name){
        this.name = name;
    }

    public void addPhoto(Photo photo){
        photos.add(photo);
        photo.setPath(this);
    }

}
