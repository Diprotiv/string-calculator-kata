package com.example.incubyte.exceptions;

public class StringCalculatorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StringCalculatorException() {
        super();
    }

    public StringCalculatorException(final String errors) {
        super(errors);
    }
}
