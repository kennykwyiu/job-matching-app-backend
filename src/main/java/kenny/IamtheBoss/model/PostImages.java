package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "post_images")
@NoArgsConstructor
@AllArgsConstructor
public class PostImages extends AbstractAuditableEntity<PostImages, Long> {
    @Column(name = "url")
    private String url;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
