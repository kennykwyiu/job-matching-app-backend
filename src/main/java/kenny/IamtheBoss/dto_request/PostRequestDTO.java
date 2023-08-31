package kenny.IamtheBoss.dto_request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestDTO {
    private String title;
    private String jobDescription;
    private Long categoryId;
    private Long systemUserId;
}
