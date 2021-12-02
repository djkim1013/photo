package com.example.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PhotoRepository {

    @PersistenceContext
    private EntityManager em;

//    public Long sav
}
