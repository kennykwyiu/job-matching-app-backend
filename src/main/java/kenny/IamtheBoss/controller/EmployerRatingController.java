package kenny.IamtheBoss.controller;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.RatingFactory;
import kenny.IamtheBoss.dto_request.RatingRequestDTO;
import kenny.IamtheBoss.dto_response.RatingResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Rating;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.RatingService;
import kenny.IamtheBoss.service.RepeatedRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employer/rating")
public class EmployerRatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingFactory ratingFactory;

    @PostMapping // OK
    public RatingResponseDTO addRating(@Valid @RequestBody RatingRequestDTO requestDTO,
                                       SystemUser systemUser) throws ResourceNotFoundException, RepeatedRatingException {
        Rating rating = ratingFactory.toEntity(requestDTO);
        ratingService.createRating(rating);
        return ratingFactory.toResponseDTO(rating);
    }


}
