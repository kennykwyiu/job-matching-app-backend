package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rating")
public class Rating extends AbstractAuditableEntity<Rating, Long> {
    @ManyToOne
    @JoinColumn(name = "fromUser_id")
    private SystemUser fromUser;

    @ManyToOne
    @JoinColumn(name = "toUser_id")
    private SystemUser toUser;

    @OneToOne
    @JoinColumn(name = "job_order_id")
    private JobOrder jobOrder;

    @Column(name = "rating", precision = 19, scale = 2)
    private BigDecimal rating;

}
