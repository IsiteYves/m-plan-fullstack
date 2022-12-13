package com.isite.todotest.config.validate;

import org.springframework.validation.Errors;

public interface InfoValidator<T> {
    public void validate(T target, Errors errors);
}
