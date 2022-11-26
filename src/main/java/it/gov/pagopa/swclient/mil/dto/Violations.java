package it.gov.pagopa.swclient.mil.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Violations {
    @NotNull(message = "violations must not be null")
    @Size(max = 32, message = "violations size must be at most {max}")
    private List<Violation> violations;

    public Violations() {
    }

    public Violations(@NotNull(message = "violations must not be null") @Size(max = 32, message = "violations size must be at most {max}") List<Violation> violations) {
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Violations.class.getSimpleName() + "[", "]")
                .add("violations=" + violations)
                .toString();
    }
}
