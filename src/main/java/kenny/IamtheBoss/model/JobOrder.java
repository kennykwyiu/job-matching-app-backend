package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Where;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "job_order", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "system_user_id"}, name = "jobOrderId")
})
@Where(clause = "is_delete = 0")
public class JobOrder extends AbstractAuditableEntity<JobOrder, Long> {

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Enumerated(EnumType.STRING)
    private JobOrderStatus status;

    @Builder

    public JobOrder(Post post, SystemUser systemUser, JobOrderStatus status) {
        this.post = post;
        this.systemUser = systemUser;
        this.isDelete = 0;
        this.status = status;
    }
}
