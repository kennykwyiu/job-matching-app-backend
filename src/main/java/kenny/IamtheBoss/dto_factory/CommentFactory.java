package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.CommentRequestDTO;
import kenny.IamtheBoss.dto_response.CommentResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Comment;
import kenny.IamtheBoss.model.RatingStatistics;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.RatingService;
import kenny.IamtheBoss.service.RatingStatisticsService;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFactory {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private JobOrderFactory jobOrderFactory;

    @Autowired
    private RatingFactory ratingFactory;

    @Autowired
    private RatingStatisticsService ratingStatisticsService;

//    public CommentResponseDTO toResponseDTO(CommentRequestDTO requestDTO) throws ResourceNotFoundException {
//        Comment comment = toEntity(requestDTO, systemUser);
//        RatingStatistics ratingStatistics = ratingStatisticsService.findBySystemUser(systemUserService.findById(requestDTO.getToUserId()));
//        return CommentResponseDTO.builder()
//                .id(comment.getId())
//                .title(comment.getTitle())
//                .reviewComment(comment.getReviewComment())
//                .fromUserName(comment.getFromUser().getUserName())
//                .toUserName(comment.getToUser().getUserName())
//                .jobOrder(comment.getJobOrder().getPost().getTitle())
//                .newRating(comment.getRating().getRating())
//                .AvgRating(ratingStatistics.getAverageRating())
//                .build();
//    }

    public CommentResponseDTO toResponseDTO(Comment comment) throws ResourceNotFoundException {
        RatingStatistics ratingStatistics = ratingStatisticsService.findBySystemUser((comment.getToUser()));

        return CommentResponseDTO.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .reviewComment(comment.getReviewComment())
                .fromUserName(comment.getFromUser().getUserName())
                .toUserName(comment.getToUser().getUserName())
                .jobOrder(comment.getJobOrder().getPost().getTitle())
                .newRating(comment.getRating().getRating())
                .AvgRating(ratingStatistics.getAverageRating())
                .build();
    }

    public Comment toEntity(CommentRequestDTO requestDTO, SystemUser fromUser) throws ResourceNotFoundException {


        return Comment.builder()
                .title(requestDTO.getTitle())
                .reviewComment(requestDTO.getReviewComment())
                .fromUser(fromUser)
                .toUser(systemUserService.findById(requestDTO.getToUserId()))
                .jobOrder(jobOrderService.findById(requestDTO.getJobOrderId()))
                .rating(ratingService.findById(requestDTO.getRatingId()))
                .build();
    }


}
