package user_api.mapper;

import org.mapstruct.Mapper;
import user_api.dto.ContentDto;
import user_api.model.Content;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    Content toEntity(ContentDto contentDto);
    ContentDto toDto(Content content);
}
