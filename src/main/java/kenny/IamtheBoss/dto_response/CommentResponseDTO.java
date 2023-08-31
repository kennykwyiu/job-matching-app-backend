package kenny.IamtheBoss.dto_response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CommentResponseDTO {
    private Long id;
    private String title;
    private String reviewComment;
    private String fromUserName;
    private String toUserName;
    private String jobOrder;
    private BigDecimal rating;
}
