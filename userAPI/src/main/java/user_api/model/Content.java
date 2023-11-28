package user_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "content")
public class Content extends BaseEntity{
    private String body;
    @ManyToOne()
    private User author;
}
