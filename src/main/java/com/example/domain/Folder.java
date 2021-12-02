package com.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Folder {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    private String name;

    @Column(name = "folder_regdate")
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "path")
    private List<Photo> photos = new ArrayList<>();
}
