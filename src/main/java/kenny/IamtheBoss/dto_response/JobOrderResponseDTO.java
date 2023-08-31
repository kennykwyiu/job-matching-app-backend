package kenny.IamtheBoss.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOrderResponseDTO {
    private Long jobOrderId;
    private PostResponseForJobOrderDTO postResponseForJobOrderDTO;
    private SystemUserResponseJobOrderDTO systemUserResponseJobOrderDTO;
    private String status;

}
