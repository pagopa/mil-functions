/*
 * ServicesResource.java
 *
 * 29 nov 2022
 */
package it.pagopa.swclient.mil.services.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.mongodb.MongoWriteException;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.bean.CommonHeader;
import it.pagopa.swclient.mil.bean.Errors;
import it.pagopa.swclient.mil.services.ErrorCode;
import it.pagopa.swclient.mil.services.bean.Services;
import it.pagopa.swclient.mil.services.dao.ServicesEntity;
import it.pagopa.swclient.mil.services.dao.ServicesRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * 
 * @author Antonio Tarricone
 */
@Path("/services")
public class ServicesResource {
	/*
	 * 
	 */
	@Inject
	ServicesRepository servicesRepository;

	/**
	 * 
	 * @param commonHeader
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Services> getServices(@Valid @BeanParam CommonHeader commonHeader) {
		Log.debugf("getServices - Input parameters: %s", commonHeader);

		Uni<Optional<ServicesEntity>> servicesOptional = servicesRepository.findByIdOptional(commonHeader.getChannel());

		Uni<Services> services = servicesOptional
			.onFailure().transform(t -> {
				/*
				 * DB error
				 */
				Log.errorf(t, "[%s] DB error while finding", ErrorCode.DB_ERROR_WHILE_FINDING);
				return new InternalServerErrorException(Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new Errors(List.of(ErrorCode.DB_ERROR_WHILE_FINDING)))
					.build());
			})
			.onItem().transform(o -> o.orElseThrow(() -> {
				/*
				 * If 'optional' item is present return it, otherwise throw NotFoundException.
				 */
				Log.warnf("[%s] Services not found for channel %s", ErrorCode.SERVICES_NOT_FOUND, commonHeader.getChannel());
				return new NotFoundException(Response
					.status(Status.NOT_FOUND)
					.entity(new Errors(List.of(ErrorCode.SERVICES_NOT_FOUND)))
					.build());
			}))
			.map(e -> {
				/*
				 * Get services from entity
				 */
				Log.debugf("getServices - Output parameters: %s", e.services);
				return e.services;
			});

		return services;
	}

	/**
	 * 
	 * @param commonHeader
	 * @param services
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Uni<Response> postServices(@Valid @BeanParam CommonHeader commonHeader, @Valid Services services) {
		Log.debugf("postServices - Input parameters: %s, %s", commonHeader, services);

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
				if (t instanceof MongoWriteException m) {
					if (m.getCode() == 11000) {
						/*
						 * Duplicate key
						 */
						Log.warnf(m, "[%s] Duplicate key for %s", ErrorCode.DUPLICATE_KEY, commonHeader.getChannel());
						return new WebApplicationException(Response
							.status(Status.CONFLICT)
							.entity(new Errors(List.of(ErrorCode.DUPLICATE_KEY)))
							.build());
					} else {
						/*
						 * Other error
						 */
						Log.errorf(t, "[%s] DB error while persisting", ErrorCode.DB_ERROR_WHILE_PERSISTING);
						return new InternalServerErrorException(Response
							.status(Status.INTERNAL_SERVER_ERROR)
							.entity(new Errors(List.of(ErrorCode.DB_ERROR_WHILE_PERSISTING)))
							.build());
					}
				} else {
					/*
					 * DB error
					 */
					Log.errorf(t, "[%s] DB error while persisting", ErrorCode.DB_ERROR_WHILE_PERSISTING);
					return new InternalServerErrorException(Response
						.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new Errors(List.of(ErrorCode.DB_ERROR_WHILE_PERSISTING)))
						.build());
				}
			});

		return response;
	}
}
