package com.softvan.customannotation;

import com.softvan.enums.StatusEnum;
import com.softvan.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Slf4j
public class StatusEnumValidater  implements ConstraintValidator<StatusEnumTypeConstraint,StatusEnum> {

         private static final List<StatusEnum> statusEnums = Arrays.asList(StatusEnum.ACTIVE,StatusEnum.IN_ACTIVE);


    public boolean isValid(StatusEnum status, ConstraintValidatorContext constraintValidatorContext) {
       log.info("isValid started");
        if(!statusEnums.contains(status)){
             throw new  CustomException("You can use only ACTIVE OR IN_ACTIVE status");
        }

        return true;
    }
}
