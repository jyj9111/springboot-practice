package com.example.vaildation.constraints.annotations;

import com.example.vaildation.constraints.EmailWhitelistValidator;
import com.example.vaildation.constraints.Phone010Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Phone010 가 붙은 필드는
// 유효성 검사 시
// 010 또는 010- 로 시작해야 한다.
@Target(ElementType.FIELD)   // (선택) 어노테이션을 어디에 적용 할 것인지
@Retention(RetentionPolicy.RUNTIME) // 어노테이션이 언제까지 유지될 것인지
@Constraint(validatedBy = Phone010Validator.class)
public @interface Phone010 {
    // Annotation Element
    String message() default "010으로 시작하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
