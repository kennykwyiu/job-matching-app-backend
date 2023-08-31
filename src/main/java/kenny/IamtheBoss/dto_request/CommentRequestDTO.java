package kenny.IamtheBoss.dto_request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CommentRequestDTO {
    private String title;
    private String reviewComment;
    private Long fromUserId;
    private Long toUserId;
    private Long jobOrderId;
    private Long ratingId;
}
