package com.isite.todotest.config.validate;

public interface Validateable<This> {
    public InfoValidator<This> validator();
}
