package com.example.api;

import com.example.domain.FolderDto;
import com.example.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("folder")
@RequiredArgsConstructor
public class FolderApiController {

    private final FolderService folderService;

    //post
    //폴더 생성
    @PostMapping
    public Long createFolder(@RequestBody FolderDto folder){
        return folderService.createFolder(folder);
    }

    //get
    //폴더 모두 조회
    @GetMapping
    public List<FolderDto> getAllFolders(){
        return folderService.findAllFolders();
    }

    //폴더 이름으로 조회
    @GetMapping(params = {"name"})
    public FolderDto getFolderByName(@RequestParam(name= "name") String folderName){
        return folderService.findFolderByName(folderName);
    }

    //put
    //폴더 이름 수정
    @PutMapping("/{folderId}")
    public Long updateFolder(@PathVariable Long folderId,
                             @RequestBody FolderDto folderDto){
        folderService.updateFolder(folderId, folderDto);
        return folderId;
    }

    //del
    //폴더 삭제
    @DeleteMapping("/{bookId}")
    public Long deleteFolder(@PathVariable Long bookId){
        folderService.deleteFolder(bookId);
        return bookId;
    }
}
