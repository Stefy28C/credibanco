package com.nexos.credibanco.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class CustomValidator implements ConstraintValidator<Validate, Object> {
    private String methodName;
 
    public void initialize(Validate constraintAnnotation) {
        this.methodName = constraintAnnotation.method();
    }
 
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            Method method = object.getClass().getMethod(methodName);
            return (Boolean) method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}