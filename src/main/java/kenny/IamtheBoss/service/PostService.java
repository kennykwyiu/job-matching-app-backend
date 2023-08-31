package kenny.IamtheBoss.service;

import jakarta.transaction.Transactional;
import kenny.IamtheBoss.dto_request.PostRequestDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.*;
import kenny.IamtheBoss.repository.JobOrderRepository;
import kenny.IamtheBoss.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService extends AbstractBaseService<Post, Long> {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private JobOrderRepository jobOrderRepository;

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Post> findAllById(Long systemUserId) throws ResourceNotFoundException {
        SystemUser systemUser = systemUserService.findById(systemUserId);
        return repository.findAllBySystemUser(systemUser);
    }

    public Post updatePost(Post post, PostRequestDTO requestDTO) throws ResourceNotFoundException {
        post.setTitle(requestDTO.getTitle());
        post.setJobDescription(requestDTO.getJobDescription());
        post.setCategory(categoryService.findById(requestDTO.getCategoryId()));
        repository.save(post);
        return post;
    }

    public void deleteProcessing(Post post) {
        delete(post);
        post.setStatus(PostStatus.CANCELLED);
    }

    public List<Post> findAllByCategory(Long categoryId) throws ResourceNotFoundException {
        Category category = categoryService.findById(categoryId);
        return repository.findAllByCategory(category);
    }


    public Post approveEmployeeApplication(Post post, JobOrder jobOrder) throws ResourceNotFoundException {

        post.setStatus(PostStatus.ASSIGNED);
        jobOrder.setStatus(JobOrderStatus.IN_PROGRESS);
        repository.save(post);
        jobOrderRepository.save(jobOrder);
        return post;
    }

    public Post reopenPost(Long postId) throws ResourceNotFoundException {
        Post post = findById(postId);
        post.setStatus(PostStatus.OPEN);
        repository.save(post);
        return post;
    }
}
