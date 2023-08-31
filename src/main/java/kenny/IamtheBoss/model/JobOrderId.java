package kenny.IamtheBoss.model;

import jakarta.persistence.*;
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
//@Embeddable
public class JobOrderId implements Serializable {

    private static final long serialVersionUID = -324958274623904039L;

    private Long postId;
    private Long systemUserId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOrderId that = (JobOrderId) o;
        return Objects.equals(postId, that.postId) && Objects.equals(systemUserId, that.systemUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, systemUserId);
    }
}
