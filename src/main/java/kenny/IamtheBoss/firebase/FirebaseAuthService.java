package kenny.IamtheBoss.firebase;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import kenny.IamtheBoss.dto_request.SignUpRequestDTO;
import kenny.IamtheBoss.model.UserClaim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FirebaseAuthService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    public String createEmployer(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();

        createRequest.setDisplayName(requestDTO.getFullName())
                .setEmail(requestDTO.getEmail())
                .setPassword(requestDTO.getPassword());

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        String uid = userRecord.getUid();
        List<UserClaim> requestedPermissions = List.of(UserClaim.EMPLOYER);
        setUserClaims(uid, requestedPermissions);

        log.info("Created user [{}] for [{}]",
                uid,
                userRecord.getEmail());

        return uid;
    }
    public String createEmployee(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();

        createRequest.setDisplayName(requestDTO.getFullName())
                .setEmail(requestDTO.getEmail())
                .setPassword(requestDTO.getPassword());

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        String uid = userRecord.getUid();
        List<UserClaim> requestedPermissions = List.of(UserClaim.EMPLOYEE);
        setUserClaims(uid, requestedPermissions);

        log.info("Created user [{}] for [{}]",
                uid,
                userRecord.getEmail());

        return uid;
    }
    public String createAdmin(SignUpRequestDTO requestDTO) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();

        createRequest.setDisplayName(requestDTO.getFullName())
                .setEmail(requestDTO.getEmail())
                .setPassword(requestDTO.getPassword());

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        String uid = userRecord.getUid();
        List<UserClaim> requestedPermissions = List.of(UserClaim.EMPLOYEE, UserClaim.EMPLOYER, UserClaim.ADMIN);
        setUserClaims(uid, requestedPermissions);

        log.info("Created user [{}] for [{}]",
                uid,
                userRecord.getEmail());

        return uid;
    }

    public void setUserClaims(String uid, List<UserClaim> requestedPermissions) throws FirebaseAuthException {
        List<String> userClaims = requestedPermissions.stream()
                .map(Enum::toString)
                .toList();

        Map<String, Object> claims = Map.of("custom_claims", userClaims);
        firebaseAuth.setCustomUserClaims(uid, claims);
    }

    public void deleteUser(String uid) throws FirebaseAuthException {
        firebaseAuth.deleteUser(uid);
    }

}
