package kenny.IamtheBoss.dto_response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RatingResponseDTO {
    private Long id;
    private SystemUserResponseDTO fromUser;
    private SystemUserResponseDTO toUser;
    private JobOrderResponseDTO jobOrder;
    private BigDecimal rating;
    private BigDecimal averageRating;
}
