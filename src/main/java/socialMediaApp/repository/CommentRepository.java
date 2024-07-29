package socialMediaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import socialMediaApp.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
