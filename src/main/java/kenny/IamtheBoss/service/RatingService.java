package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.model.RatingStatistics;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RatingService extends AbstractBaseService<Rating, Long> {

    @Autowired
    private RatingStatisticsService ratingStatisticsService;


    private final RatingRepository repository;
    public RatingService(RatingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void createRating(Rating rating) throws RepeatedRatingException {
        SystemUser fromUser = rating.getFromUser();
        SystemUser toUser = rating.getToUser();

        JobOrder jobOrder = rating.getJobOrder();
        Long jobOrderId = jobOrder.getId();
        BigDecimal newRating = rating.getRating();

        if (repository.existsBySystemUserAndJobOrder(fromUser, jobOrder)) {
            throw new RepeatedRatingException(String.format("This job order# %s was already rated", jobOrderId));
        }

        repository.save(rating);

        RatingStatistics toUserRatingStat;
        BigDecimal totalRatingQty;
        BigDecimal toUserAvgRating;

        if (Objects.isNull(toUserRatingStat = ratingStatisticsService.findBySystemUser(toUser))) {
            toUserRatingStat = RatingStatistics.builder()
                    .averageRating(newRating)
                    .totalRatingQuantity(BigDecimal.ONE)
                    .toUser(toUser)
                    .build();

            ratingStatisticsService.save(toUserRatingStat);
            return;
        }

        totalRatingQty = toUserRatingStat.getTotalRatingQuantity();
        toUserAvgRating = toUserRatingStat.getAverageRating();

        toUserRatingStat.setAverageRating(((toUserAvgRating.multiply(totalRatingQty)).add(newRating))
                .divide(totalRatingQty.add(BigDecimal.ONE), 1,
                        RoundingMode.HALF_EVEN));

        toUserRatingStat.setTotalRatingQuantity(totalRatingQty.add(BigDecimal.ONE));
        ratingStatisticsService.save(toUserRatingStat);


    }
}
