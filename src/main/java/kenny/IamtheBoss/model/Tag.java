package kenny.IamtheBoss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag extends AbstractAuditableEntity<Tag, Long> {
    @Column(name = "tag_name")
    private String tagName;

}
