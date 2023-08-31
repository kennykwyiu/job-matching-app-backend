package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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

        BigDecimal newRating = rating.getRating();
        BigDecimal totalRatingQuantity; // this one is always null, coz rating is new object
        BigDecimal newTotalRatingQuantity;

        Rating latestRating = null;
        SystemUser fromUser = rating.getFromUser();
        SystemUser toUser = rating.getToUser();

        JobOrder jobOrder = rating.getJobOrder();
        Long jobOrderId = jobOrder.getId();

        if (repository.existsBySystemUserAndJobOrder(fromUser, jobOrder)) {
            throw new RepeatedRatingException(String.format("This job order# %s was already rated", jobOrderId));
        }

        List<Rating> ratingList = repository.getAllRating(toUser);

//        if (Objects.isNull(ratingList)) {
//            totalRatingQuantity = new BigDecimal(BigInteger.ONE);
//            newTotalRatingQuantity = new BigDecimal(BigInteger.ONE);
//        } else {
//            latestRating = ratingList.get(0);
//            totalRatingQuantity = latestRating.getTotalRatingQuantity();
//            newTotalRatingQuantity = totalRatingQuantity.add(BigDecimal.ONE);
//        }

        if (!ratingList.isEmpty()) {
            latestRating = ratingList.get(0);
            totalRatingQuantity = latestRating.getTotalRatingQuantity();
            newTotalRatingQuantity = totalRatingQuantity.add(BigDecimal.ONE);
        } else {
            totalRatingQuantity = BigDecimal.ONE;
            newTotalRatingQuantity = BigDecimal.ONE;
        }

        BigDecimal averageRating;
        BigDecimal totalRating;
//        if (Objects.isNull(latestRating.getAverageRating())) {
//            averageRating = new BigDecimal(String.valueOf(BigDecimal.ONE));
//        } else {
//            averageRating = latestRating.getAverageRating();
//        }

        if (latestRating != null && latestRating.getAverageRating() != null) {
            averageRating = latestRating.getAverageRating();
        } else {
            averageRating = BigDecimal.ZERO; // Initialize with zero
        }

        totalRating = averageRating.multiply(totalRatingQuantity);
        BigDecimal newAverageRating = totalRating.add(newRating).divide(newTotalRatingQuantity, 1,
                RoundingMode.HALF_UP);
        rating.setTotalRatingQuantity(newTotalRatingQuantity);
        rating.setAverageRating(newAverageRating);
        save(rating);
    }
}
