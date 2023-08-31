package kenny.IamtheBoss.dto_request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import kenny.IamtheBoss.model.JobOrder;
import kenny.IamtheBoss.model.SystemUser;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RatingRequestDTO {
    @NotNull
    private Long fromUserId;
    @NotNull
    private Long toUserId;
    @NotNull
    private Long jobOrderId;
    @NotNull
    @Max(10)
    private BigDecimal rating;
}
