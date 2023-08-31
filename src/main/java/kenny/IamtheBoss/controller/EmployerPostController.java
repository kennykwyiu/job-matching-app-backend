package kenny.IamtheBoss.controller;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.PostDTOFactory;
import kenny.IamtheBoss.dto_request.PostRequestDTO;
import kenny.IamtheBoss.dto_response.PostResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.PostStatus;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employer/post")
public class EmployerPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private PostDTOFactory postDTOFactory;

    @GetMapping("/{systemUserId}")  // OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PostResponseDTO> getAllByUserId(@PathVariable(name = "systemUserId") Long systemUserId,
                                                SystemUser systemUser) throws ResourceNotFoundException {
        return postService.findAllById(systemUserId).stream()
                .map(postDTOFactory::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{categoryId}/category")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PostResponseDTO> getPostByCategoryId(@PathVariable(name = "categoryId") Long categoryId,
                                                     SystemUser systemUser) throws ResourceNotFoundException {
        return postService.findAllByCategory(categoryId).stream()
                .map(postDTOFactory::toResponseDTO)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // ok
    // TODO: need to check is user a employer before create the job post
    public PostResponseDTO createPostByUserId(@Valid @RequestBody PostRequestDTO requestDTO,
                                              SystemUser systemUser) throws ResourceNotFoundException {
        Post post = postDTOFactory.toEntity(requestDTO);
        postService.save(post);
        return postDTOFactory.toResponseDTO(post);
    }

    @PutMapping("/{postId}") // OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostResponseDTO updatePostByPostId(@PathVariable(name = "postId") Long postId,
                                              @Valid @RequestBody PostRequestDTO requestDTO,
                                              SystemUser systemUser) throws ResourceNotFoundException {
        Post post = postService.findById(postId);
        Post updatedPost = postService.updatePost(post, requestDTO);
        return postDTOFactory.toResponseDTO(updatedPost);
    }

    @PutMapping("/approval/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostResponseDTO approveEmployeeApplication(@PathVariable(name = "postId") Long postId,
                                              SystemUser systemUser) throws ResourceNotFoundException {

        Post post = postService.findById(postId);
        JobOrder jobOrder = jobOrderService.findByPost(post);
        Post updatedPost = postService.approveEmployeeApplication(post, jobOrder);
        return postDTOFactory.toResponseDTO(updatedPost);
    }

    @PutMapping("/reopen/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostResponseDTO reopenPost(@PathVariable(name = "postId") Long postId,
                                              SystemUser systemUser) throws ResourceNotFoundException {
        Post updatedPost = postService.reopenPost(postId);
        return postDTOFactory.toResponseDTO(updatedPost);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(name = "postId") Long postId,
                           SystemUser systemUser) throws ResourceNotFoundException {
        Post post = postService.findById(postId);
        postService.deleteProcessing(post);
    }


}
