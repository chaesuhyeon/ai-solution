package sweetk.solution.test.global.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public record ResponseDto<T> (
        T data,
        Status status,
        String message
) {

    public static <T> ResponseEntity<ResponseDto<T>> createSuccess(T data) {
        return ResponseEntity
                .ok(new ResponseDto<>(data, Status.SUCCESS, Status.SUCCESS.getMessage()));
    }

    public static ResponseEntity<ResponseDto<Void>> createSuccessWithNoContent() {
        return ResponseEntity
                .ok(new ResponseDto<>(null, Status.SUCCESS, Status.SUCCESS.getMessage()));
    }

    public static ResponseEntity<ResponseDto<Map<String, String>>> createValidationFail(Map<String, String> errors) {
        return ResponseEntity.badRequest()
                .body(new ResponseDto<>(errors, Status.FAIL, Status.FAIL.getMessage()));
    }

    public static ResponseEntity<ResponseDto<Map<String, String>>> createValidationFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError fieldError) {
                // get source of error
                boolean isTypeMismatch = Arrays.stream(fieldError.getCodes()).anyMatch(s -> "typeMismatch".equals(s));
                if (isTypeMismatch) {
                    errors.put(fieldError.getField(), "잘못된 데이터 타입 입니다.");
                } else {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        }

        for (Map.Entry<String, String> entry : errors.entrySet()) {
            if (!entry.getKey().equals("password")) {
                log.error("ApiResult createFail key = {}, value = {}", entry.getKey(), entry.getValue());
            }
        }

        return ResponseEntity.badRequest()
                .body(new ResponseDto<>(errors, Status.FAIL, Status.FAIL.getMessage()));
    }

    public static ResponseEntity<ResponseDto<Void>> createFail() {
        return ResponseEntity.badRequest()
                .body(new ResponseDto<>(null, Status.FAIL, Status.FAIL.getMessage()));
    }

    public static ResponseEntity<ResponseDto<Void>> createFail(String message) {
        return ResponseEntity.badRequest()
                .body(new ResponseDto<>(null, Status.FAIL, message));
    }

    public static ResponseEntity<ResponseDto<Void>> createFail(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ResponseDto<>(null, Status.FAIL, e.getMessage()));
    }

    public static ResponseEntity<ResponseDto<?>> createServerError(String message) {
        return ResponseEntity.internalServerError()
                .body(new ResponseDto<>(null, Status.ERROR, message));
    }

    @Getter
    public enum Status {
        SUCCESS("정상적으로 처리되었습니다.")
        , FAIL("잘못된 요청입니다.")
        , ERROR("");

        private final String message;

        Status(String message) {
            this.message = message;
        }
    }
}


