package kenny.IamtheBoss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Where;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "category")
@Where(clause = "is_delete = 0")
public class Category extends AbstractAuditableEntity<Category, Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Builder
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDelete = 0;
    }
}
