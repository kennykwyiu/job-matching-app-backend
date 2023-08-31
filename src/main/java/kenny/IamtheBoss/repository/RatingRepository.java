package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.model.SystemUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends AbstractBaseRepository<Rating, Long> {
    @Query("select r from Rating r " +
            "where r.toUser = :toUser " +
            "order by r.createdAt desc")
    List<Rating> getAllRating(@Param("toUser") SystemUser toUser);


    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Rating r " +
            "WHERE r.fromUser = :fromUser " +
            "AND r.jobOrder = :jobOrder")
    boolean existsBySystemUserAndJobOrder(@Param("fromUser") SystemUser fromUser,
                                          @Param("jobOrder") JobOrder jobOrder);
}
