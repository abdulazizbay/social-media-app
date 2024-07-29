package socialMediaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import socialMediaApp.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id=?1")
    public List<Post> findByUserId(Integer userId);
    @Query("select p from Post p where p.user.id IN :users order by p.createdAt desc ")
    public List<Post> findAllPostByUserId(@Param("users") List<Integer> userId);
}
