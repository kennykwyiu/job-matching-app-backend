package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.RatingRequestDTO;
import kenny.IamtheBoss.dto_response.RatingResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.model.RatingStatistics;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.RatingStatisticsService;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingFactory {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private JobOrderFactory jobOrderFactory;

    @Autowired
    private RatingStatisticsService ratingStatisticsService;

    public RatingResponseDTO toResponseDTO(RatingRequestDTO requestDTO, SystemUser systemUser) throws ResourceNotFoundException {
        Rating rating = toEntity(requestDTO, systemUser);
        RatingStatistics ratingStatistics = ratingStatisticsService.findBySystemUser(systemUserService.findById(requestDTO.getToUserId()));


        return RatingResponseDTO.builder()
                .id(rating.getId())
                .fromUser(systemUserFactory.toResponseDTO(rating.getFromUser()))
                .toUser(systemUserFactory.toResponseDTO(rating.getToUser()))
                .jobOrder(jobOrderFactory.toResponseDTO(rating.getJobOrder()))
                .rating(rating.getRating())
                .averageRating(ratingStatistics.getAverageRating())
                .build();
    }

    public RatingResponseDTO toResponseDTO(Rating rating) throws ResourceNotFoundException {
        RatingStatistics ratingStatistics = ratingStatisticsService.findBySystemUser((rating.getToUser()));

        return RatingResponseDTO.builder()
                .id(rating.getId())
                .fromUser(systemUserFactory.toResponseDTO(rating.getFromUser()))
                .toUser(systemUserFactory.toResponseDTO(rating.getToUser()))
                .jobOrder(jobOrderFactory.toResponseDTO(rating.getJobOrder()))
                .rating(rating.getRating())
                .averageRating(ratingStatistics.getAverageRating())
                .build();
    }

    public Rating toEntity(RatingRequestDTO requestDTO, SystemUser systemUser) throws ResourceNotFoundException {
        return Rating.builder()
                .fromUser(systemUser)
                .toUser(systemUserService.findById(requestDTO.getToUserId()))
                .jobOrder(jobOrderService.findById(requestDTO.getJobOrderId()))
                .rating(requestDTO.getRating())
                .build();
    }


}
