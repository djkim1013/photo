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


@Entity(name = "folder")
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
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

    public void updateFolder(FolderDto folderDto){
        this.name = folderDto.getName();
    }
}
