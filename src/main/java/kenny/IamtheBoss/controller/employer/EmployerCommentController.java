package kenny.IamtheBoss.controller.employer;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.CommentFactory;
import kenny.IamtheBoss.dto_request.CommentRequestDTO;
import kenny.IamtheBoss.dto_response.CommentResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Comment;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.CommentService;
import kenny.IamtheBoss.service.RepeatedRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer/comment")
public class EmployerCommentController {

    @Autowired
    private CommentFactory commentFactory;

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDTO addComment(@Valid @RequestBody CommentRequestDTO requestDTO,
                                         SystemUser systemUser) throws ResourceNotFoundException, RepeatedRatingException {
        Comment comment = commentFactory.toEntity(requestDTO, systemUser);
        commentService.addComment(comment);
        return commentFactory.toResponseDTO(comment);

    }


}
