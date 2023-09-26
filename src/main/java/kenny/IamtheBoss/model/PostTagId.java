package kenny.IamtheBoss.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PostTagId implements Serializable {

    private static final long serialVersionUID = -3287715633608041039L;

    private Long postId;
    private Long tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagId postTagId = (PostTagId) o;
        return Objects.equals(postId, postTagId.postId) && Objects.equals(tagId, postTagId.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, tagId);
    }
}
