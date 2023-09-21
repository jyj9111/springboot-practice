package com.example.vaildation.constraints;

import com.example.vaildation.constraints.annotations.EmailWhitelist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

// 이메일 데이터 유효성 검사기
public class EmailWhitelistValidator
        // 사용자 지정 유효성 검사를 위해 구현해야 하는 인터페이스
        implements ConstraintValidator<EmailWhitelist, String> {
    private final Set<String> whiteList;

    public EmailWhitelistValidator() {
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 유효한 값일 때 true 를 반환
        // 유효하지 않은 값일때 false 를 반환
        String[] split = value.split("@");
        String domain = split[split.length - 1];

        return whiteList.contains(domain);
    }
}
