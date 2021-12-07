package com.example.api;

import com.example.domain.Folder;
import com.example.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("folder")
@RequiredArgsConstructor
public class FolderApiController {

    private final FolderService folderService;

    //get
    //폴더 모두 조회
    @GetMapping
    public List<Folder> getAllFolders(){
        return folderService.findAllFolders();
    }

    //폴더 이름으로 조회
    @GetMapping("/{folderName}")
    public Folder getFolderByName(@PathVariable String folderName){
        return folderService.findFolderByName(folderName);
    }

    //post
    //폴더 생성
    @PostMapping
    public Long createFolder(@RequestBody Folder folder){
        return folderService.createFolder(folder);
    }

    //put
    //폴더 이름 수정
    @PutMapping("/{bookId}")
    public Long updateFolder(@PathVariable Long bookId,
                             @RequestBody Folder folder){
        folderService.updateFolder(bookId,folder);
        return bookId;
    }

    //del
    //폴더 삭제
    @DeleteMapping("/{bookId}")
    public Long deleteFolder(@PathVariable Long bookId){
        folderService.deleteFolder(bookId);
        return bookId;
    }
}
