package com.example.entity;

import com.example.domain.FolderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class FolderEntity extends BaseAuditingEntity {

    @Id @GeneratedValue
    @Column(name = "folder_id")
    private Long id;

    @Column(name = "folder_name")
    private String name;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "path", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhotoEntity> photos = new ArrayList<>();

    public void reName(String name){
        this.name = name;
    }

//    public void addPhoto(Photo photo){
//        photos.add(photo);
//        photo.setPath(this);
//    }

}
