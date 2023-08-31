package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
public interface JobOrderRepository extends AbstractBaseRepository<JobOrder, Long> {
    @Query(value = "select * from job_order jo " +
            "where is_delete = 1", nativeQuery = true)
    List<JobOrder> findAllByIsDelete();
    JobOrder findByPost(Post post);
}
