package kenny.IamtheBoss.controller.employer;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.PostImagesDTOFactory;
import kenny.IamtheBoss.dto_request.PostImagesRequestDTO;
import kenny.IamtheBoss.dto_response.PostImagesResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.PostImages;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.PostImagesService;
import kenny.IamtheBoss.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer/post-images")
public class PostImagesController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostImagesService postImagesService;

    @Autowired
    private PostImagesDTOFactory postImagesDTOFactory;

    @PostMapping("/{postId}") // OK
    @ResponseStatus(HttpStatus.CREATED)
    public List<PostImagesResponseDTO> createOrReplacePostImages(@PathVariable(name = "postId") Long postId,
                                                                 @Valid @RequestBody PostImagesRequestDTO requestDTO,
                                                                 SystemUser systemUser) throws ResourceNotFoundException {
        Post post = postService.findById(postId);
        List<String> urls = requestDTO.getUrls();

        return postImagesDTOFactory.toResponseDTO(post, urls);
    }

    @DeleteMapping("/{postImagesId}")// OK
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeImageById(@PathVariable(name = "postImagesId") Long postImagesId,
                                SystemUser systemUser) throws ResourceNotFoundException {
        PostImages postImages = postImagesService.findById(postImagesId);
        postImagesService.delete(postImages);
    }


}
