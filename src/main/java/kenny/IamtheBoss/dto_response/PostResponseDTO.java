package kenny.IamtheBoss.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Long postId;
    private String title;
    private String jobDescription;
    private CategoryResponseDTO categoryResponseDTO;
    private SystemUserResponseDTO systemUserResponseDTO;
    private String status;
}
