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
@Builder
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

//hikari - 내장
//jpa 다른 것들
//ddd와 계층 차이
//logging - 운영
//      -설계 통일 / 구체적 이슈 정보 출력 / 동작 의도 / key 값 설정 ... 추적 가능하도록
// properties.yml <-> setting.gradle
// kuber + kafka

//과제/수행 내용 정리