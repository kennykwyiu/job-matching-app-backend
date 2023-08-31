package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post extends AbstractAuditableEntity<Post, Long> {
    @Column(name = "title")
    private String title;

    @Column(name = "job_description")
    private String jobDescription;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

}
