package kenny.IamtheBoss.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "system_users")
@Where(clause = "is_delete = 0")
public class SystemUser extends AbstractAuditableEntity<SystemUser, Long>{


    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "external_user_id")
    private String externalUserId;

    @Enumerated(EnumType.STRING)
    private List<UserClaim> userClaims;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Builder
    public SystemUser(String userName, String fullName, String email, String externalUserId, List<UserClaim> userClaims) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.externalUserId = externalUserId;
        this.userClaims = userClaims;
        this.isDelete = 0;
    }

}
