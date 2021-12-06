package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter //@Setter
public class Photo extends BaseAuditingEntity{
    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    @Setter
    private String name;

    @Column(name = "photo_memo")
    @Setter
    private String memo;

//    @Column(name = "photo_regdate")
//    @CreatedDate
//    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    @Setter
    private Folder path;

}
