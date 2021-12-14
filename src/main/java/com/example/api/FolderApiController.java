package com.example.api;

import com.example.domain.FolderDto;
import com.example.domain.PhotoDto;
import com.example.entity.FolderEntity;
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
    public Long createFolder(@RequestBody FolderDto.Request requestCreate){
        return folderService.createFolder(requestCreate.getName()).getId();
    }

    //폴더 모두 조회
    @GetMapping
    public List<FolderDto.ResponseGetAll> getAllFolders(){
        return folderService.findAllFolders().stream()
                .map(e -> new FolderDto().new ResponseGetAll(
                        e.getId(),
                        e.getName(),
                        e.getRegDate(),
                        e.getPhotoList().size()))
                .collect(Collectors.toList());
    }

    //폴더 이름으로 조회
    @GetMapping(params = {"name"})
    public FolderDto.ResponseGetOne getFolderByName(@RequestParam String name){
        FolderEntity folderByName = folderService.findFolderByName(name);
        return new FolderDto().new ResponseGetOne(
                folderByName.getId(),
                folderByName.getName(),
                folderByName.getRegDate(),
                folderByName.getPhotoList().stream()
                        .map(e -> new PhotoDto().new ResponseInFolder(
                                e.getId(),
                                e.getName(),
                                e.getRegDate(),
                                e.getMemo()
                        ))
                        .collect(Collectors.toList())
        );
    }

    //폴더 이름 수정
    @PutMapping("/{folderId}")
    public FolderDto.ResponseGetOne updateFolder(@PathVariable Long folderId,
                             @RequestBody FolderDto.Request requestUpdate){
        FolderEntity folderUpdated = folderService.updateFolder(folderId, requestUpdate.getName());
        return new FolderDto().new ResponseGetOne(
                folderUpdated.getId(),
                folderUpdated.getName(),
                folderUpdated.getRegDate(),
                folderUpdated.getPhotoList().stream().map(
                        e -> new PhotoDto().new ResponseInFolder(
                                e.getId(),
                                e.getName(),
                                e.getRegDate(),
                                e.getMemo()
                        ))
                        .collect(Collectors.toList())

        );
    }

    //폴더 삭제
    @DeleteMapping("/{folderId}")
    public Long deleteFolder(@PathVariable Long folderId){
        folderService.deleteFolder(folderId);
        return folderId;
    }
}
