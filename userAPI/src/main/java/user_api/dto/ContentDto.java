package user_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    private Long id;
    private String body;
    private UserDto author;
}
