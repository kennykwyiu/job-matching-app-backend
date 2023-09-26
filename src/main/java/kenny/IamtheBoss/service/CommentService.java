package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Comment;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
public class CommentService extends AbstractBaseService<Comment, Long> {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void addComment(Comment comment) throws ResourceNotFoundException, RepeatedRatingException {
        if (Objects.isNull(comment.getRating())) {
            throw new ResourceNotFoundException(
                    String.format(
                            "User# %s has not rated User# %s",
                            comment.getFromUser().getUserName(),
                            comment.getToUser().getUserName()
                    ));
        }

        SystemUser fromUser = comment.getFromUser();
        JobOrder jobOrder = comment.getJobOrder();

       if (repository.existsBySystemUserAndComment(fromUser, jobOrder)) {
           throw new RepeatedRatingException(String.format("This job order# %s was already commented", jobOrder.getId()));
       }
        repository.save(comment);
    }
}
