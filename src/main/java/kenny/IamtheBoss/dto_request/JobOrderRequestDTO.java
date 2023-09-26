package kenny.IamtheBoss.dto_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOrderRequestDTO {
    @NotNull
    private Long postId;

//    @NotNull
//    private Long systemUserId;

}
