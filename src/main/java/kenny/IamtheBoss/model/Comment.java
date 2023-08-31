package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import kenny.IamtheBoss.service.AbstractBaseService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Comment")
@AllArgsConstructor
public class Comment extends AbstractAuditableEntity<Comment, Long> {
    @Column(name = "title")
    private String title;

    @Column(name = "review_comment")
    private String reviewComment;

    @ManyToOne
    @JoinColumn(name = "fromUser_id")
    private SystemUser fromUser;

    @ManyToOne
    @JoinColumn(name = "toUser_id")
    private SystemUser toUser;

    @OneToOne
    @JoinColumn(name = "job_order_id")
    private JobOrder jobOrder;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

}
