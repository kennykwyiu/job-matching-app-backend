package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.dto_request.JobOrderRequestDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.exception.UnacceptedOrderException;
import kenny.IamtheBoss.exception.UnauthorizedOperationException;
import kenny.IamtheBoss.model.*;
import kenny.IamtheBoss.repository.JobOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JobOrderService extends AbstractBaseService<JobOrder, Long> {

    @Autowired
    private PostService postService;

    private final JobOrderRepository repository;

    public JobOrderService(JobOrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void setDelete(JobOrder jobOrder) {
        jobOrder.setStatus(JobOrderStatus.CANCELLED);
        jobOrder.setIsDelete((byte) 1);
        jobOrder.getPost().setStatus(PostStatus.PENDING);
        repository.save(jobOrder);
    }

    public List<JobOrder> getAllDeletedJobOrder() {
        return repository.findAllByIsDelete();
    }

    public void validateJobOrderRequest(JobOrderRequestDTO requestDTO,
                                        SystemUser systemUser) throws ResourceNotFoundException,
            UnauthorizedOperationException, UnacceptedOrderException {
        Long employeeId = systemUser.getId();

        Post post = postService.findById(requestDTO.getPostId());
        SystemUser employer = post.getSystemUser();
        Long employerId = employer.getId();

        if (employerId.equals(employeeId)) {
            throw new UnauthorizedOperationException(
                    String.format("User#%s Cannot add your own post", employerId)
            );
        }

        if (!post.getStatus().equals(PostStatus.OPEN)) {
            throw new UnacceptedOrderException(
                    String.format("Post#%s: %s not available now", post.getId(), post.getTitle())
            );
        }
    }

    public JobOrder findByPost(Post post) {
        return repository.findByPost(post);

    }

    public void changeJobOrderAndPostStatus(JobOrder jobOrder) {
        jobOrder.setStatus(JobOrderStatus.WAITING_FOR_APPROVAL);
        save(jobOrder);
        Post post = jobOrder.getPost();
        post.setStatus(PostStatus.WAITING_FOR_APPROVAL);
        postService.save(post);
    }
}
