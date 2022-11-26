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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mongodb.MongoWriteException;

import java.net.URI;
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
			.map(o -> o.orElseThrow(() -> { // if 'optional' item is present return it, otherwise throw NotFoundException
				Log.warnf("Services not found for channel %s.", commonHeader.getChannel());
				return new NotFoundException();
			}))
			.map(e -> { // get services from entity
				Log.debugf("Output parameters: %s", e.services);
				return e.services;
			});

		return services;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Uni<Response> postServices(@Valid @BeanParam CommonHeader commonHeader, @Valid Services services) {
		Log.debugf("Input parameters: %s, %s", commonHeader, services);

		ServicesEntity servicesEntity = new ServicesEntity();
		servicesEntity.channel = commonHeader.getChannel();
		servicesEntity.services = services;

		Uni<Response> response = servicesRepository.persist(servicesEntity)
			.onItem().transform(e -> Response.created(URI.create("/services/" + e.channel)).build()) // if an item is returned, return to the caller the URL to retrieve it
			.onFailure() // if a failure is returned...
			.transform(t -> {
				Log.fatal(t);
				if (t instanceof MongoWriteException && ((MongoWriteException) t).getCode() == 11000) {
					return new WebApplicationException("", Status.CONFLICT);
				} else {
					return new InternalServerErrorException();
				}
			});

		return response;
	}
}