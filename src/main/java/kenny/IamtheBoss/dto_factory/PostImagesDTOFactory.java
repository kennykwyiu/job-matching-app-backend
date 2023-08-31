package kenny.IamtheBoss.dto_factory;

import kenny.IamtheBoss.dto_response.PostImagesResponseDTO;
import kenny.IamtheBoss.model.Post;
import kenny.IamtheBoss.model.PostImages;
import kenny.IamtheBoss.repository.PostImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostImagesDTOFactory {
    @Autowired
    private PostImagesRepository postImagesRepository;

    public List<PostImagesResponseDTO> toResponseDTO(Post post, List<String> urls) {
        return urls.stream()
                .map(url -> {
                            saveToPostImagesRepo(post, url);
                            return getPostImagesResponseDTO(post, url);
                        }
                ).toList();
    }

    private static PostImagesResponseDTO getPostImagesResponseDTO(Post post, String url) {
        return PostImagesResponseDTO.builder()
                .url(url)
                .postId(post.getId())
                .build();
    }

    private PostImages saveToPostImagesRepo(Post post, String url) {
        return postImagesRepository.save(PostImages.builder()
                .url(url)
                .post(post)
                .build());
    }


}
