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
@Table(name = "rating_statistics")
public class RatingStatistics extends AbstractAuditableEntity<RatingStatistics, Long> {

    @Column(name = "average_rating", precision = 19, scale = 2)
    private BigDecimal averageRating;

    @Column(name = "total_rating_quantity", precision = 19, scale = 2)
    private BigDecimal totalRatingQuantity;

    @ManyToOne
    @JoinColumn(name = "toUser_id")
    private SystemUser toUser;



}
