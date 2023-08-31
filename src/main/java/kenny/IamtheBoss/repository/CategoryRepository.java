package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends AbstractBaseRepository<Category, Long> {
    @Query(value = "select * from category " +
            "where is_delete = 1", nativeQuery = true)
    List<Category> findAllDeletedCategory();

}
