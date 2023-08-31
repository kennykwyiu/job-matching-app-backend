package kenny.IamtheBoss.dto_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostImagesResponseDTO {
    private Long postId;
    private String url;
}
