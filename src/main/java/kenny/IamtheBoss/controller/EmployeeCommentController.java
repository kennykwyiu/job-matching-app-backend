package kenny.IamtheBoss.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/comment")
public class EmployeeCommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentFactory commentFactory;

    @PostMapping
    public CommentResponseDTO addComment(@Valid @RequestBody CommentRequestDTO requestDTO,
                                         SystemUser systemUser) throws ResourceNotFoundException, RepeatedRatingException {
        Comment comment = commentFactory.toEntity(requestDTO);
        commentService.addComment(comment);
        return commentFactory.toResponseDTO(comment);
    }


}
