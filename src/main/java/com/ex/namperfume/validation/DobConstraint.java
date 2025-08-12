package com.ex.namperfume.validation;

import jakarta.validation.Payload;

public @interface DobConstraint {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
