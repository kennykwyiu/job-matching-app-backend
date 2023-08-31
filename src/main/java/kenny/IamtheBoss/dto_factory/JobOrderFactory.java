package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_request.JobOrderRequestDTO;
import kenny.IamtheBoss.dto_response.JobOrderResponseDTO;
import kenny.IamtheBoss.dto_response.PostResponseForJobOrderDTO;
import kenny.IamtheBoss.dto_response.SystemUserResponseJobOrderDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.service.PostService;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobOrderFactory {
    @Autowired
    private PostService postService;
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private PostDTOFactory postDTOFactory;

    @Autowired
    private SystemUserFactory systemUserFactory;

    public JobOrderResponseDTO toResponseDTO(JobOrderRequestDTO requestDTO, SystemUser systemUser) throws ResourceNotFoundException {

        JobOrder jobOrder = toEntity(requestDTO, systemUser);

        PostResponseForJobOrderDTO postResponseForJobOrderDTO = postDTOFactory.toResponseForJobOrderDTO (jobOrder.getPost());
        SystemUserResponseJobOrderDTO systemUserResponseJobOrderDTO = systemUserFactory.toResponseJobOrderDTO(jobOrder.getSystemUser());
        return JobOrderResponseDTO.builder()
                .jobOrderId(jobOrder.getId())
                .postResponseForJobOrderDTO(postResponseForJobOrderDTO)
                .systemUserResponseJobOrderDTO(systemUserResponseJobOrderDTO)
                .build();
    }
    public JobOrderResponseDTO toResponseDTO(JobOrder jobOrder) {
        PostResponseForJobOrderDTO postResponseForJobOrderDTO = postDTOFactory.toResponseForJobOrderDTO (jobOrder.getPost());
        SystemUserResponseJobOrderDTO systemUserResponseJobOrderDTO
                = systemUserFactory.toResponseJobOrderDTO(jobOrder.getSystemUser());
        return JobOrderResponseDTO.builder()
                .jobOrderId(jobOrder.getId())
                .postResponseForJobOrderDTO(postResponseForJobOrderDTO)
                .systemUserResponseJobOrderDTO(systemUserResponseJobOrderDTO)
                .status(jobOrder.getStatus().toString())
                .build();
    }

    public JobOrder toEntity(JobOrderRequestDTO requestDTO, SystemUser systemUser) throws ResourceNotFoundException {
        Post post = postService.findById(requestDTO.getPostId());

        return JobOrder.builder()
                .post(post)
                .systemUser(systemUser)
                .build();
    }

}
