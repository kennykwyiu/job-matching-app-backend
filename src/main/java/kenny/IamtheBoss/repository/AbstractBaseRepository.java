package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.AbstractAuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractAuditableEntity<T,ID>, ID extends Serializable >
        extends JpaRepository<T, ID> {
}
