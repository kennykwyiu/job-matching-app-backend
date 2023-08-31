package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.RatingRequestDTO;
import kenny.IamtheBoss.dto_response.RatingResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.service.JobOrderService;
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

    public RatingResponseDTO toResponseDTO(RatingRequestDTO requestDTO) throws ResourceNotFoundException {
        Rating rating = toEntity(requestDTO);
        return RatingResponseDTO.builder()
                .id(rating.getId())
                .fromUser(systemUserFactory.toResponseDTO(rating.getFromUser()))
                .toUser(systemUserFactory.toResponseDTO(rating.getToUser()))
                .jobOrder(jobOrderFactory.toResponseDTO(rating.getJobOrder()))
                .rating(rating.getRating())
                .averageRating(rating.getAverageRating())
                .build();
    }

    public RatingResponseDTO toResponseDTO(Rating rating) throws ResourceNotFoundException {
        return RatingResponseDTO.builder()
                .id(rating.getId())
                .fromUser(systemUserFactory.toResponseDTO(rating.getFromUser()))
                .toUser(systemUserFactory.toResponseDTO(rating.getToUser()))
                .jobOrder(jobOrderFactory.toResponseDTO(rating.getJobOrder()))
                .rating(rating.getRating())
                .averageRating(rating.getAverageRating())
                .build();
    }

    public Rating toEntity(RatingRequestDTO requestDTO) throws ResourceNotFoundException {
        return Rating.builder()
                .fromUser(systemUserService.findById(requestDTO.getFromUserId()))
                .toUser(systemUserService.findById(requestDTO.getToUserId()))
                .jobOrder(jobOrderService.findById(requestDTO.getJobOrderId()))
                .rating(requestDTO.getRating())
                .build();
    }


}
