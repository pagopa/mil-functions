package it.gov.pagopa.swclient.mil.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class Violation {
    @NotNull(message = "message must not be null")
    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "message must match {regexp}")
    private String message;

    public Violation() {
    }

    public Violation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Violation.class.getSimpleName() + "[", "]")
                .add("message='" + message + "'")
                .toString();
    }
}
