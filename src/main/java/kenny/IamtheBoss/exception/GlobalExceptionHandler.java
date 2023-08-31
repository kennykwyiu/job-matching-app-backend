package kenny.IamtheBoss.exception;

import kenny.IamtheBoss.dto_response.ApiResponseDTO;
import kenny.IamtheBoss.service.RepeatedRatingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleUnauthorizedOperationException(UnauthorizedOperationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponseDTO.fail(ex.getMessage()));
    }

    @ExceptionHandler(UnacceptedOrderException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleUnacceptedOrderException(UnacceptedOrderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseDTO.fail(ex.getMessage()));
    }

    @ExceptionHandler(RepeatedRatingException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleRepeatedRatingException(RepeatedRatingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseDTO.fail(ex.getMessage()));
    }
}
