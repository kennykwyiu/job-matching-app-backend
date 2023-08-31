package kenny.IamtheBoss.repository;

import kenny.IamtheBoss.model.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends AbstractBaseRepository<SystemUser, Long> {
    Optional<SystemUser> findByExternalUserId(String externalUserId);
}
