package com.example.repository;

import com.example.domain.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PhotoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Photo photo){
        em.persist(photo);
    }

    public Photo findOne(Long id){
        return em.find(Photo.class, id);
    }

    public List<Photo> findAll(){
        return em.createQuery("select p from Photo p", Photo.class)
                .getResultList();
    }

    public List<Photo> findByName(String name){
        return em.createQuery("select p from Photo p where p.name in :name", Photo.class)
                .setParameter("name", name)
                .getResultList();
    }
}
