package com.example.service;

import com.example.domain.Folder;
import com.example.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    @Transactional
    public Long makeNew(Folder folder){
        validateDuplicateFolder(folder);
        folderRepository.save(folder);
        return folder.getId();
    }

    private void validateDuplicateFolder(Folder folder){
        List<Folder> findFolders = folderRepository.findByName(folder.getName());
        if(!findFolders.isEmpty()){
            throw new IllegalStateException("이미 존재하는 폴더입니다.");
        }
    }

    public List<Folder> findFolders(){
        return folderRepository.findAll();
    }
}
