package kenny.IamtheBoss.dto_request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequestDTO {
    private String title;
    private String reviewComment;
//    private Long fromUserId;
    private Long toUserId;
    private Long jobOrderId;
    private Long ratingId;
}
