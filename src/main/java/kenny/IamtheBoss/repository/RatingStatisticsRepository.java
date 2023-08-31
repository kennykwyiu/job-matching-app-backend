package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.RatingStatistics;
import kenny.IamtheBoss.model.SystemUser;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingStatisticsRepository extends AbstractBaseRepository<RatingStatistics, Long>  {
    RatingStatistics findBySystemUser(SystemUser toUser);
}
