package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
public class Photo extends BaseAuditingEntity{
    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_name")
    private String name;

    @Column(name = "photo_memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder path;

}
