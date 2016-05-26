package me.south10.commons;

import lombok.Data;

import java.util.List;

/**
 * Created by south10 on 2016-05-26.
 */
@Data
public class ErrorResponse {
    private String message;

    private String code;

    private List<FieldError> errors;

    // TODO
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
