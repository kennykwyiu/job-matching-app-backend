package kenny.IamtheBoss.service;

import com.google.firebase.auth.FirebaseAuthException;
import jakarta.transaction.Transactional;
import kenny.IamtheBoss.dto_factory.SystemUserFactory;
import kenny.IamtheBoss.dto_request.SignUpRequestDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.firebase.FirebaseAuthService;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.model.UserClaim;
import kenny.IamtheBoss.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SystemUserService extends AbstractBaseService<SystemUser, Long> {

    @Autowired
    private SystemUserFactory systemUserFactory;

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    private final SystemUserRepository repository;

    public SystemUserService(SystemUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public SystemUser signUpEmployer(SignUpRequestDTO requestDTO) throws  FirebaseAuthException {
        String externalUserId = firebaseAuthService.createEmployer(requestDTO);
        SystemUser systemUser = systemUserFactory.toEntity(requestDTO);
        systemUser.setUserClaims(List.of(UserClaim.EMPLOYER));
        systemUser.setExternalUserId(externalUserId);
        return save(systemUser); // due to extends AbstractBaseService<SystemUser, Long>
    }


    public SystemUser signUpEmployee(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        String externalUserId = firebaseAuthService.createEmployee(requestDTO);
        SystemUser systemUser = systemUserFactory.toEntity(requestDTO);
        systemUser.setUserClaims(List.of(UserClaim.EMPLOYEE));
        systemUser.setExternalUserId(externalUserId);
        return save(systemUser); // due to extends AbstractBaseService<SystemUser, Long>
    }
    public SystemUser signUpAdmin(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        String externalUserId = firebaseAuthService.createAdmin(requestDTO);
        SystemUser systemUser = systemUserFactory.toEntity(requestDTO);
        List<UserClaim> requestedPermissions = List.of(UserClaim.EMPLOYEE, UserClaim.EMPLOYER, UserClaim.ADMIN);
        systemUser.setUserClaims(requestedPermissions);
        systemUser.setExternalUserId(externalUserId);
        return save(systemUser); // due to extends AbstractBaseService<SystemUser, Long>
    }

    public SystemUser findByExternalUserId(String externalUserId) throws ResourceNotFoundException {
        return repository.findByExternalUserId(externalUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User not found by external User Id [%s]", externalUserId)
                ));
    }
}
