package user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_api.dto.ContentDto;
import user_api.service.contentService.ContentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getContentById(@PathVariable Long id){
        ContentDto contentDto = contentService.getContentById(id);
        if (contentDto != null){
            return ResponseEntity.ok(contentService.getContentById(id));
        }
        return ResponseEntity.badRequest().body("Not Found!");
    }
    @GetMapping("/author/{id}")
    public ResponseEntity<?> getContentByAuthorId(@PathVariable Long id){
        List<ContentDto> contents = contentService.getAllByAuthorId(id);
        if (contents != null){
            return ResponseEntity.ok(contents);
        }
        return ResponseEntity.badRequest().body("Not Found!");
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllContent(){
        List<ContentDto> contents = contentService.getAllContent();
        if (contents != null){
            return ResponseEntity.ok(contents);
        }
        return ResponseEntity.badRequest().body("Not Found!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContentById(@PathVariable Long id){
        if (contentService.deleteContentById(id)){
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        return ResponseEntity.badRequest().body("Failed to delete! Check ID or permissions!");
    }
    @PutMapping("/")
    public ResponseEntity<?> updateContent(@RequestBody ContentDto contentDto){
        ContentDto contentN = contentService.updateContent(contentDto);
        if (contentN != null){
            return ResponseEntity.ok().body(contentN);
        }
        return ResponseEntity.badRequest().body("Failed to update! Check ID or permissions!");
    }
    @PostMapping("/")
    public ResponseEntity<?> createContent(@RequestBody ContentDto contentDto){
        ContentDto contentN = contentService.createContent(contentDto.getBody());
        if (contentN != null){
            return ResponseEntity.ok().body(contentN);
        }
        return ResponseEntity.badRequest().body("Failed to update! Check ID or permissions!");
    }
}
