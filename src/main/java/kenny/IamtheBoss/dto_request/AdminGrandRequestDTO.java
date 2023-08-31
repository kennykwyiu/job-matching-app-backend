package kenny.IamtheBoss.dto_request;

import jakarta.validation.constraints.NotBlank;
import kenny.IamtheBoss.model.UserClaim;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminGrandRequestDTO {

    @NotBlank
    private String uid;

    private List<UserClaim> permissions;
}
