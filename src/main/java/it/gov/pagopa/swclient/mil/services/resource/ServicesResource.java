package it.gov.pagopa.swclient.mil.services.resource;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mongodb.MongoWriteException;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.gov.pagopa.swclient.mil.dto.CommonHeader;
import it.gov.pagopa.swclient.mil.services.dao.ServicesEntity;
import it.gov.pagopa.swclient.mil.services.dao.ServicesRepository;
import it.gov.pagopa.swclient.mil.services.dto.Services;
import it.gov.pagopa.swclient.mil.services.util.Error;

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
			.map(o -> o.orElseThrow(() -> {
				/*
				 * If 'optional' item is present return it, otherwise throw NotFoundException.
				 */
				Log.warnf("[%s] Services not found for channel %s.", Error.SERVICES_NOT_FOUND, commonHeader.getChannel());
				return new NotFoundException(Error.SERVICES_NOT_FOUND);
			}))
			.map(e -> {
				/*
				 * Get services from entity
				 */
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
			.onItem().transform(e -> {
				/*
				 * If an item is returned, return to the caller the URL to retrieve it.
				 */
				URI location = URI.create("/services/" + e.channel);
				Log.debugf("Services saved: %s", location);
				return Response.created(location).build();
			})
			.onFailure().transform(t -> {
				/*
				 * If a failure is returned, throw the right exception.
				 */
				Log.fatal(t);
				if (t instanceof MongoWriteException m) {
					if (m.getCode() == 11000) {
						/*
						 * Duplicate key
						 */
						Log.warnf("[%s] Duplicate key for %s.", Error.DUPLICATE_KEY, commonHeader.getChannel());
						return new WebApplicationException(Error.DUPLICATE_KEY, Status.CONFLICT);
					} else {
						/*
						 * Other error
						 */
						return new InternalServerErrorException(Error.ERROR_WHILE_PERSISTING);
					}
				} else {
					/*
					 * DB error
					 */
					return new InternalServerErrorException(Error.ERROR_WHILE_PERSISTING);
				}
			});

		return response;
	}
}