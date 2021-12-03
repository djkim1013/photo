package com.example.repository;

import com.example.domain.Folder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FolderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Folder folder){
        em.persist(folder);
    }

    public Folder findOne(Long id){
        return em.find(Folder.class, id);
    }

    public List<Folder> findAll(){
        return em.createQuery("select f from Folder f", Folder.class)
                .getResultList();
    }

    public List<Folder> findByName(String name){
        return em.createQuery("select f from Folder f where f.name in :name", Folder.class)
                .setParameter("name", name)
                .getResultList();
    }
}
