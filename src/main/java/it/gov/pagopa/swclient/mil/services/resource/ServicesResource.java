package it.gov.pagopa.swclient.mil.services.resource;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.gov.pagopa.swclient.mil.dto.CommonHeader;
import it.gov.pagopa.swclient.mil.services.dao.ServicesEntity;
import it.gov.pagopa.swclient.mil.services.dto.Services;
import it.gov.pagopa.swclient.mil.services.dao.ServicesRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/services")
public class ServicesResource {
    @Inject
    ServicesRepository servicesRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Services> getServices(@Valid @BeanParam CommonHeader commonHeader) {
        Log.debugf("Input parameters: %s", commonHeader);
        Uni<Optional<ServicesEntity>> servicesOptional = servicesRepository.findByIdOptional(commonHeader.getChannel());
        Uni<Services> services = servicesOptional
                .map(o -> o.orElseThrow(() -> new NotFoundException()))
                .map(e -> e.services);
        return services;
    }
}