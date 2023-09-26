package kenny.IamtheBoss.controller.employee;

import kenny.IamtheBoss.dto_factory.PostDTOFactory;
import kenny.IamtheBoss.dto_response.PostResponseDTO;
import kenny.IamtheBoss.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee/post")
public class EmployeePostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostDTOFactory postDTOFactory;

    @GetMapping
    public List<PostResponseDTO> getAllPost() {
        return postService.findAll().stream()
                .map(postDTOFactory::toResponseDTO)
                .collect(Collectors.toList());
    }


}
