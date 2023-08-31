package kenny.IamtheBoss.controller;

import kenny.IamtheBoss.dto_factory.PostDTOFactory;
import kenny.IamtheBoss.dto_response.PostResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/post")
public class AdminPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostDTOFactory postDTOFactory;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PostResponseDTO> getAllOrByUserId(@RequestParam(name = "q", required = false) Long systemUserId) throws ResourceNotFoundException {
        if (Objects.isNull(systemUserId)) {
            return postService.findAll().stream()
                    .map(postDTOFactory::toResponseDTO)
                    .collect(Collectors.toList());
        }
        return postService.findAllById(systemUserId).stream()
                .map(postDTOFactory::toResponseDTO)
                .collect(Collectors.toList());
    }


}
