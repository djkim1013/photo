package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "folder")
@Getter
@NoArgsConstructor
public class FolderEntity extends BaseAuditingEntity {

    @Id @GeneratedValue //전략 default - auto
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name",
            unique = true)
    private String name;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhotoEntity> photoList = new ArrayList<>();

    public FolderEntity(String name){
        this.name = name;
    }

    public void updateFolder(String name){
        this.name = name;
    }
}
