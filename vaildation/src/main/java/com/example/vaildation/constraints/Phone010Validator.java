package com.example.vaildation.constraints;

import com.example.vaildation.constraints.annotations.Phone010;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

// '010' 또는 '010-' 으로 시작
public class Phone010Validator implements ConstraintValidator<Phone010, String> {
    private final List<String> phoneWhitelist;

    public Phone010Validator() {
        this.phoneWhitelist = new ArrayList<>();
        phoneWhitelist.add("010");
        phoneWhitelist.add("010-");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("010-") || value.startsWith("010");
    }
}
