package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.model.JobOrderReview;
import kenny.IamtheBoss.repository.AbstractBaseRepository;
import kenny.IamtheBoss.repository.JobOrderRepository;
import kenny.IamtheBoss.repository.JobOrderReviewRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JobOrderReviewService extends AbstractBaseService<JobOrderReview, Long> {

    private final JobOrderReviewRepository repository;
    public JobOrderReviewService(JobOrderReviewRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
