package user_api.service.contentService;

import user_api.dto.ContentDto;
import user_api.model.Content;

import java.util.List;

public interface ContentService {
    ContentDto createContent (String body);
    ContentDto updateContent (ContentDto contentDto);
    ContentDto getContentById(Long id);
    List<ContentDto> getAllByAuthorId(Long id);
    List<ContentDto> getAllContent();
    boolean deleteContentById(Long id);
}
