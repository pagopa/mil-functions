/*
 * ContainerRequestContextRetriever.java
 *
 * 2 feb 2023
 */
package it.gov.pagopa.swclient.mil.filter;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import io.quarkus.logging.Log;

/**
 * This filter gets from ContainerRequestContext some data usefull to verify the signature and puts
 * it as context parameters and it will be used by the interceptor TODO.
 * 
 * @author Antonio Tarricone
 */
@Provider
@PreMatching
public class ContainerRequestContextRetriever implements ContainerRequestFilter {
	/**
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		MultivaluedMap<String, String> headers = ctx.getHeaders();
		headers.forEach((key, value) -> {
			value.forEach(item -> {
				Log.debugf(">>>> Header %s = %s", key, item);
			});
		});

		String method = ctx.getMethod();
		Log.debugf(">>>> Method = %s", method);

		UriInfo uriInfo = ctx.getUriInfo();
		Log.debugf(">>>> Absolute path = %s", uriInfo.getAbsolutePath());
		Log.debugf(">>>> Base URI = %s", uriInfo.getBaseUri());
		List<Object> matchedResources = uriInfo.getMatchedResources();
		matchedResources.forEach(item -> {
			Log.debugf(">>>> Matched resource = %s", item);
		});
		List<String> matchedUris = uriInfo.getMatchedURIs();
		matchedUris.forEach(item -> {
			Log.debugf(">>>> Matched URI = %s", item);
		});
		Log.debugf(">>>> Path = %s", uriInfo.getPath());
		MultivaluedMap<String, String> pathParamters = uriInfo.getPathParameters();
		pathParamters.forEach((key, value) -> {
			value.forEach(item -> {
				Log.debugf(">>>> Path parameters %s = %s", key, item);
			});
		});
		List<PathSegment> pathSegments = uriInfo.getPathSegments();
		pathSegments.forEach(item -> {
			Log.debugf(">>>> Path segment = %s", item.getPath());
		});
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		queryParameters.forEach((key, value) -> {
			value.forEach(item -> {
				Log.debugf(">>>> Query parameters %s = %s", key, item);
			});
		});
		Log.debugf(">>>> Request URI = %s", uriInfo.getRequestUri());
		
		ctx.setProperty("ContainerRequestContext", ctx);
	}
}