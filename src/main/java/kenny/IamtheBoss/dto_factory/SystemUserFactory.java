package kenny.IamtheBoss.dto_factory;


import kenny.IamtheBoss.dto_request.SignUpRequestDTO;
import kenny.IamtheBoss.dto_response.SystemUserResponseDTO;
import kenny.IamtheBoss.dto_response.SystemUserResponseJobOrderDTO;
import kenny.IamtheBoss.model.SystemUser;
import org.springframework.stereotype.Component;

@Component
public class SystemUserFactory {


    public SystemUserResponseDTO toResponseDTO(SystemUser systemUser) {
        return SystemUserResponseDTO.builder()
                .id(systemUser.getId())
                .userName(systemUser.getUserName())
                .fullName(systemUser.getFullName())
                .email(systemUser.getEmail())
                .userClaims(systemUser.getUserClaims())
                .build();
    }

    public SystemUserResponseDTO toResponseDTO(SignUpRequestDTO requestDTO) {
        SystemUser systemUser = toEntity(requestDTO);

        return SystemUserResponseDTO.builder()
                .id(systemUser.getId())
                .userName(systemUser.getUserName())
                .fullName(systemUser.getFullName())
                .email(systemUser.getEmail())
                .userClaims(systemUser.getUserClaims())
                .build();
    }
    public SystemUserResponseJobOrderDTO toResponseJobOrderDTO(SystemUser systemUser) {
        return SystemUserResponseJobOrderDTO.builder()
                .id(systemUser.getId())
                .userName(systemUser.getUserName())
                .build();
    }

    public SystemUser toEntity(SignUpRequestDTO requestDTO) {
        return SystemUser.builder()
                .userName(requestDTO.getUserName())
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .build();
    }


}
