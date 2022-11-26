package it.gov.pagopa.swclient.mil.util;

import io.quarkus.logging.Log;
import it.gov.pagopa.swclient.mil.dto.Violation;
import it.gov.pagopa.swclient.mil.dto.Violations;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Violation> violationList = e.getConstraintViolations().stream()
                .map(c -> new Violation(c.getMessage()))
                .toList();

        Violations violations = new Violations(violationList);

        Log.error(violations);

        return Response.status(Response.Status.BAD_REQUEST).entity(violations).build();
    }
}
