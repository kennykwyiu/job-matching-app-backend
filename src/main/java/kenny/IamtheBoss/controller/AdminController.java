package kenny.IamtheBoss.controller;

import com.google.firebase.auth.FirebaseAuthException;
import jakarta.validation.Valid;
import kenny.IamtheBoss.dto_factory.SystemUserFactory;
import kenny.IamtheBoss.dto_request.AdminGrandRequestDTO;
import kenny.IamtheBoss.dto_response.SystemUserResponseDTO;
import kenny.IamtheBoss.exception.ResourceNotFoundException;
import kenny.IamtheBoss.firebase.FirebaseAuthService;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.model.UserClaim;
import kenny.IamtheBoss.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserFactory systemUserFactory;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SystemUserResponseDTO> getAll(SystemUser systemUser) {
        return systemUserService.findAll().stream()
                .map(systemUserFactory::toResponseDTO)
                .toList();
    }

    @PostMapping("/grant") // OK
    public ResponseEntity<Void> grantAdminPermission(@Valid @RequestBody AdminGrandRequestDTO adminGrandRequestDTO) throws FirebaseAuthException {
        String uid = adminGrandRequestDTO.getUid();
        List<UserClaim> userClaims = adminGrandRequestDTO.getPermissions();
        firebaseAuthService.setUserClaims(uid, userClaims);
    return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/delete/{systemUserId}") // OK
    public ResponseEntity<Void> deleteUser(@PathVariable("systemUserId") Long systemUserId) throws FirebaseAuthException, ResourceNotFoundException {
        SystemUser systemUser = systemUserService.findById(systemUserId);
        String externalUserId = systemUser.getExternalUserId();
        firebaseAuthService.deleteUser(externalUserId);
        systemUserService.delete(systemUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/test") // admin OK, employer 403 Forbidden

    public ResponseEntity<String> test(Principal principal) {
        String principalName = principal.getName();
        return ResponseEntity.ok(principalName);
    }
}
