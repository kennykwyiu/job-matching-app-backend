package kenny.IamtheBoss.controller;

import com.google.firebase.auth.FirebaseAuthException;
import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.SystemUserFactory;
import kenny.IamtheBoss.dto_request.SignUpRequestDTO;
import kenny.IamtheBoss.dto_response.SystemUserResponseDTO;
import kenny.IamtheBoss.firebase.FirebaseAuthService;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.model.UserClaim;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserFactory systemUserFactory;
    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @PostMapping("/signup-employer") // OK
    public ResponseEntity<SystemUserResponseDTO> signUpEmployer(@Valid @RequestBody SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        SystemUser systemUser = systemUserService.signUpEmployer(requestDTO);
        SystemUserResponseDTO systemUserResponseDTO = systemUserFactory.toResponseDTO(systemUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserResponseDTO);
    }

    @PostMapping("/signup-employee")  // TODO: need to test
    public ResponseEntity<SystemUserResponseDTO> signUpEmployee(@Valid @RequestBody SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        SystemUser systemUser = systemUserService.signUpEmployee(requestDTO);
        SystemUserResponseDTO systemUserResponseDTO = systemUserFactory.toResponseDTO(systemUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserResponseDTO);
    }
    @PostMapping("/signup-admin") // OK
    public ResponseEntity<SystemUserResponseDTO> signUpAdmin(@Valid @RequestBody SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        SystemUser systemUser = systemUserService.signUpAdmin(requestDTO);
        SystemUserResponseDTO systemUserResponseDTO = systemUserFactory.toResponseDTO(systemUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserResponseDTO);
    }


    @PostMapping("/grant") // TODO: need to check again
    public ResponseEntity<Void> grantAdmin(Principal principal) throws FirebaseAuthException {
        String principalName = principal.getName();
        List<UserClaim> requestedPermissions = List.of(UserClaim.ADMIN, UserClaim.EMPLOYER, UserClaim.EMPLOYEE);
        firebaseAuthService.setUserClaims(principalName, requestedPermissions);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
