package com.sukhobskaya.topjava.restaurant_rating_system.util;

import com.sukhobskaya.topjava.restaurant_rating_system.util.exception.NotValidDataException;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@UtilityClass
public class ValidationUtil {

    public static void checkDataValidity(@NotNull Errors errors) {
        if (errors.hasErrors()) {
            var errorMsg = new StringBuilder();

            var errorList = errors.getFieldErrors();
            for (FieldError error : errorList) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new NotValidDataException(errorMsg.toString());
        }
    }
}
