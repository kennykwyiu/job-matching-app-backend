package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "job_order_review")
public class JobOrderReview extends AbstractAuditableEntity<JobOrderReview, Long> {

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_comment")
    private String reviewComment;

    @ManyToOne
    @JoinColumn(name = "job_order_id")
    private JobOrder jobOrder;

    @ManyToOne
    @JoinColumn(name = "system_user_from_id")
    private SystemUser systemUserFrom;

    @ManyToOne
    @JoinColumn(name = "system_user_to_id")
    private SystemUser systemUserTo;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Builder
    public JobOrderReview(String reviewTitle,
                          String reviewComment,
                          JobOrder jobOrder,
                          SystemUser systemUserFrom,
                          SystemUser systemUserTo) {
        this.reviewTitle = reviewTitle;
        this.reviewComment = reviewComment;
        this.jobOrder = jobOrder;
        this.systemUserFrom = systemUserFrom;
        this.systemUserTo = systemUserTo;
        this.isDelete = 0;
    }
}
