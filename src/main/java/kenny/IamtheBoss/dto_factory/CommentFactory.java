package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.CommentRequestDTO;
import kenny.IamtheBoss.dto_response.CommentResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Comment;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.RatingService;
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

    public CommentResponseDTO toResponseDTO(CommentRequestDTO requestDTO) throws ResourceNotFoundException {
        Comment comment = toEntity(requestDTO);
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .reviewComment(comment.getReviewComment())
                .fromUserName(comment.getFromUser().getUserName())
                .toUserName(comment.getToUser().getUserName())
                .jobOrder(comment.getJobOrder().getPost().getTitle())
                .rating(comment.getRating().getAverageRating())
                .build();
    }

    public CommentResponseDTO toResponseDTO(Comment comment) throws ResourceNotFoundException {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .reviewComment(comment.getReviewComment())
                .fromUserName(comment.getFromUser().getUserName())
                .toUserName(comment.getToUser().getUserName())
                .jobOrder(comment.getJobOrder().getPost().getTitle())
                .rating(comment.getRating().getAverageRating())
                .build();
    }

    public Comment toEntity(CommentRequestDTO requestDTO) throws ResourceNotFoundException {
        return Comment.builder()
                .title(requestDTO.getTitle())
                .reviewComment(requestDTO.getReviewComment())
                .fromUser(systemUserService.findById(requestDTO.getFromUserId()))
                .toUser(systemUserService.findById(requestDTO.getToUserId()))
                .jobOrder(jobOrderService.findById(requestDTO.getJobOrderId()))
                .rating(ratingService.findById(requestDTO.getRatingId()))
                .build();
    }


}
