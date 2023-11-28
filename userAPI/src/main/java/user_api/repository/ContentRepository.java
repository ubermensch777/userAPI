package user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user_api.model.Content;

import java.util.List;

@Repository
@Transactional
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByAuthorId(Long id);
}
