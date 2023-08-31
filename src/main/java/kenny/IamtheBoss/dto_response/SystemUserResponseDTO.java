package kenny.IamtheBoss.dto_response;

import kenny.IamtheBoss.model.UserClaim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserResponseDTO {
    private Long id;

    private String userName;

    private String fullName;

    private String email;

    private List<UserClaim> userClaims;


}
