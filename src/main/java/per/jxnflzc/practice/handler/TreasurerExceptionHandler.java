package per.jxnflzc.practice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.jxnflzc.practice.model.ResponseBodyInfo;
import per.jxnflzc.practice.model.enums.ResponseCode;

import java.util.List;

@RestControllerAdvice
public class TreasurerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreasurerExceptionHandler.class);


    public TreasurerExceptionHandler() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBodyInfo validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                LOGGER.warn("Data check failure : object={}, field={}, errorMessage={}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseBodyInfo.build(ResponseCode.PARAM_ERROR,
                    result.getFieldError() == null ? "请求参数有误" : result.getFieldError().getDefaultMessage());
        }
        //其他错误
        return ResponseBodyInfo.error(ResponseCode.FAIL.getDesc());
    }
}
