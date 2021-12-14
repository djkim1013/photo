package com.example.api;

import com.example.domain.FolderDto;
import com.example.entity.Folder;
import com.example.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("folder")
@RequiredArgsConstructor
public class FolderApiController {

    private final FolderService folderService;

    //폴더 생성
    @PostMapping
    public Long createFolder(@RequestBody FolderDto requestCreate){
        return folderService.createFolder(requestCreate.getName()).getId();
    }

    //폴더 모두 조회
    @GetMapping
    public List<FolderDto.WoPhotoList> getAllFolders(){
        return folderService.findAllFolders().stream()
                .map(FolderDto::newDtoWoPhotoList)
                .collect(Collectors.toList());
    }

    //폴더 이름으로 조회
    @GetMapping(params = {"name"})
    public FolderDto getFolderByName(@RequestParam String name){
        return FolderDto.newDto(folderService.findFolderByName(name));
    }

    //폴더 이름 수정
    @PutMapping("/{folderId}")
    public FolderDto updateFolder(@PathVariable Long folderId,
                             @RequestBody FolderDto requestUpdate){
        Folder folderUpdated = folderService.updateFolder(folderId, requestUpdate.getName());
        return FolderDto.newDto(folderUpdated);
    }

    //폴더 삭제
    @DeleteMapping("/{folderId}")
    public Long deleteFolder(@PathVariable Long folderId){
        folderService.deleteFolder(folderId);
        return folderId;
    }
}
