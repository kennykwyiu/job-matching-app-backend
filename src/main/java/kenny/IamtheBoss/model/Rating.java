package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "total_rating_quantity", precision = 19, scale = 2)
    private BigDecimal totalRatingQuantity;

    @Column(name = "rating", precision = 19, scale = 2)
    private BigDecimal rating;

    @Column(name = "average_rating", precision = 19, scale = 2)
    private BigDecimal averageRating;
}
