package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.Category;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends AbstractBaseRepository<Post, Long> {
    List<Post> findAllBySystemUser(SystemUser systemUser);

    List<Post> findAllByCategory(Category category);
}
