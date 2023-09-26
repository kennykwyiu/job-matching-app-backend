package kenny.IamtheBoss.service;

import kenny.IamtheBoss.exception.UnauthorizedOperationException;
import kenny.IamtheBoss.model.SystemUser;
import kenny.IamtheBoss.model.UserClaim;

import java.util.List;

public class ServiceUtils {
    public static void validateSystemUserForEntity(SystemUser expectedUser,
                                                   SystemUser actualUser,
                                                   Object entity) throws UnauthorizedOperationException {
        if (!expectedUser.equals(actualUser)) {
            throw new UnauthorizedOperationException(
                    String.format("Unauthorized Operation upon [%s] entity from user [%s]",
                            entity.getClass().getSimpleName(), actualUser.getId())
            );
        }
    }

    public static void validateUserClaimOfEmployer(SystemUser systemUser,
                                                   Object entity) throws UnauthorizedOperationException {
        List<UserClaim> userClaims = systemUser.getUserClaims();
        if (!userClaims.contains(UserClaim.EMPLOYER)) {
            throw new UnauthorizedOperationException(
                    String.format("Unauthorized Operation upon [%s] entity from user [%s]",
                            entity.getClass().getSimpleName(), systemUser.getId())
            );
        }
    }
}