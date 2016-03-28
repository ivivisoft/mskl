package com.mskl.common.util;


import com.mskl.common.dto.LoginDto;
import com.mskl.common.dto.RestServiceResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorUtil {

    public static <T> boolean checkBean(T t, RestServiceResult restServiceResult) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations.size() > 0) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                if (t instanceof LoginDto) {
                    msg.append(violation.getMessage());
                    break;
                } else {
                    msg.append(violation.getPropertyPath().toString()).append(violation.getMessage() + ",");
                }
            }
            restServiceResult.setSuccess(false);
            String message = msg.toString();
            if (message.endsWith(",")) {
                message = message.substring(0, message.length() - 1);
            }
            restServiceResult.setMessage(message);
            return false;
        }
        return true;
    }
}
