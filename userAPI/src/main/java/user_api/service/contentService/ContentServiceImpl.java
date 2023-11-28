package user_api.service.contentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user_api.dto.ContentDto;
import user_api.mapper.ContentMapper;
import user_api.model.Content;
import user_api.model.User;
import user_api.repository.ContentRepository;
import user_api.service.userService.CurrentUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
    private final ContentMapper contentMapper;
    private final ContentRepository contentRepository;
    private final CurrentUserService currentUserService;

    @Override
    public ContentDto createContent(String body) {
        User author = currentUserService.getCurrentUser();
        return contentMapper.toDto(contentRepository.save(Content.builder().author(author).body(body).build()));
    }

    @Override
    public ContentDto updateContent(ContentDto contentDto) {
        Content oldContent = contentRepository.findById(contentDto.getId()).orElse(null);
        if (oldContent != null && currentUserService.checkAccess(oldContent.getAuthor().getId())){
            oldContent.setBody(contentDto.getBody());
            return contentMapper.toDto(contentRepository.save(oldContent));
        } return null;
    }

    @Override
    public ContentDto getContentById(Long id) {
        return contentMapper.toDto(contentRepository.findById(id).orElse(null));
    }

    @Override
    public List<ContentDto> getAllByAuthorId(Long id) {
        return contentRepository.findAllByAuthorId(id).stream().map(contentMapper::toDto).toList();
    }
    @Override
    public List<ContentDto> getAllContent() {
        return contentRepository.findAll().stream().map(contentMapper::toDto).toList();
    }

    @Override
    public boolean deleteContentById(Long id) {
        Content content = contentRepository.findById(id).orElse(null);
        if (content == null || !currentUserService.checkAccess(content.getAuthor().getId())){
            return false;
        }
            contentRepository.deleteById(id);
            return true;
    }
}
