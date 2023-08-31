package kenny.IamtheBoss.controller;

import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.JobOrderFactory;
import kenny.IamtheBoss.dto_factory.PostDTOFactory;
import kenny.IamtheBoss.dto_request.JobOrderRequestDTO;
import kenny.IamtheBoss.dto_response.JobOrderResponseDTO;
import kenny.IamtheBoss.dto_response.PostResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.exception.UnauthorizedOperationException;
import kenny.IamtheBoss.model.*;
import kenny.IamtheBoss.service.JobOrderService;
import kenny.IamtheBoss.service.PostService;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
