package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.PostRequestDTO;
import kenny.IamtheBoss.dto_response.*;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.Category;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.PostStatus;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.CategoryService;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDTOFactory {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private CategoryFactory categoryFactory;


//    public PostResponseDTO toResponseDTO(PostRequestDTO requestDTO) throws ResourceNotFoundException {
//        Post post = toEntity(requestDTO, systemUser);
//        SystemUser systemUser = post.getSystemUser();
//        SystemUserResponseDTO systemUserResponseDTO = systemUserFactory.toResponseDTO(systemUser);
//        CategoryResponseDTO categoryResponseDTO = categoryFactory.toResponseDTO(post.getCategory());
//        return PostResponseDTO.builder()
//                .postId(post.getId())
//                .title(post.getTitle())
//                .categoryResponseDTO(categoryResponseDTO)
//                .jobDescription(post.getJobDescription())
//                .systemUserResponseDTO(systemUserResponseDTO)
//                .status(post.getStatus().toString())
//                .build();
//
//    }

    public PostResponseDTO toResponseDTO(Post post) {
        SystemUser systemUser = post.getSystemUser();
        SystemUserResponseDTO systemUserResponseDTO = systemUserFactory.toResponseDTO(systemUser);
        CategoryResponseDTO categoryResponseDTO = categoryFactory.toResponseDTO(post.getCategory());

        return PostResponseDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .categoryResponseDTO(categoryResponseDTO)
                .jobDescription(post.getJobDescription())
                .systemUserResponseDTO(systemUserResponseDTO)
                .status(post.getStatus().toString())
                .build();
    }

//    public PostResponseForJobOrderDTO toResponseForJobOrderDTO(PostRequestDTO requestDTO) throws ResourceNotFoundException {
//        Post post = toEntity(requestDTO, systemUser);
//        SystemUser systemUser = post.getSystemUser();
//        SystemUserResponseJobOrderDTO systemUserFactoryResponseJobOrderDTO = systemUserFactory.toResponseJobOrderDTO(systemUser);
//        CategoryResponseDTO categoryResponseDTO = categoryFactory.toResponseDTO(post.getCategory());
//
//        return PostResponseForJobOrderDTO.builder()
//                .postId(post.getId())
//                .title(post.getTitle())
//                .categoryResponseDTO(categoryResponseDTO)
//                .jobDescription(post.getJobDescription())
//                .systemUserResponseJobOrderDTO(systemUserFactoryResponseJobOrderDTO)
//                .status(post.getStatus().toString())
//                .build();
//    }
    public PostResponseForJobOrderDTO toResponseForJobOrderDTO(Post post) {
        SystemUser systemUser = post.getSystemUser();
        SystemUserResponseJobOrderDTO systemUserFactoryResponseJobOrderDTO = systemUserFactory.toResponseJobOrderDTO(systemUser);
        CategoryResponseDTO categoryResponseDTO = categoryFactory.toResponseDTO(post.getCategory());

        return PostResponseForJobOrderDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .categoryResponseDTO(categoryResponseDTO)
                .jobDescription(post.getJobDescription())
                .systemUserResponseJobOrderDTO(systemUserFactoryResponseJobOrderDTO)
                .status(post.getStatus().toString())
                .build();
    }




    public Post toEntity(PostRequestDTO requestDTO, SystemUser systemUser) throws ResourceNotFoundException {

        Long categoryId = requestDTO.getCategoryId();
        Category category = categoryService.findById(categoryId);
        return Post.builder()
                .title(requestDTO.getTitle())
                .jobDescription(requestDTO.getJobDescription())
                .category(category)
                .systemUser(systemUser)
                .status(PostStatus.OPEN)
                .build();
    }

}
