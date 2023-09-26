package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.model.RatingStatistics;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.repository.RatingStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RatingStatisticsService extends AbstractBaseService<RatingStatistics, Long> {
    private final RatingStatisticsRepository repository;

    public RatingStatisticsService(RatingStatisticsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public RatingStatistics findBySystemUser(SystemUser toUser) {
        return repository.ToUser(toUser);
    }
}
