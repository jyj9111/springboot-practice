package com.example.vaildation.constraints;

import com.example.vaildation.constraints.annotations.Blacklist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BlacklistValidator implements ConstraintValidator<Blacklist, String> {
    private Set<String> blacklist;

    @Override
    public void initialize(Blacklist constraintAnnotation) {
        this.blacklist = new HashSet<>();
//        for (String target : constraintAnnotation.blacklist()) {
//            blacklist.add(target);
//        }
        Collections.addAll(blacklist, constraintAnnotation.blacklist());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.blacklist.contains(value);
    }
}
