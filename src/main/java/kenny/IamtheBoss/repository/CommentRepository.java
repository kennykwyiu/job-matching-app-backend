package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.Comment;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.SystemUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends AbstractBaseRepository<Comment, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Comment c " +
            "WHERE c.fromUser = :fromUser " +
            "AND c.jobOrder = :jobOrder")
    boolean existsBySystemUserAndComment(@Param("fromUser") SystemUser fromUser,
                                         @Param("jobOrder") JobOrder jobOrder);
}
