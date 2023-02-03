/*
 * RequestBodyHashCalculator.java
 *
 * 2 feb 2023
 */
package it.gov.pagopa.swclient.mil.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import io.quarkus.logging.Log;

/**
 * 
 * @author Antonio Tarricone
 */
@Provider
@Priority(1)
public class RequestBodyHashCalculator implements ReaderInterceptor {
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		/*
		 * Read request body.
		 */
		final InputStream inputStream = context.getInputStream();
		byte[] requestBody = inputStream.readAllBytes();
		
		/*
		 * Put request body again in the input stream.
		 */
		context.setInputStream(new ByteArrayInputStream(requestBody));

		/*
		 * Calculate request body hash.
		 */
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(requestBody);

			StringBuilder builder = new StringBuilder();
			for (byte b : hash) {
				builder.append(String.format("%02X", b));
			}
			Log.debugf("Request body hash: %s", builder.toString());

			/*
			 * Get container request context from the context.
			 */
			ContainerRequestContext containerRequestContext = (ContainerRequestContext)(context.getProperty("ContainerRequestContext"));
			Log.debugf("Headers: %s", containerRequestContext.getHeaders());
			Log.debugf("Method: %s", containerRequestContext.getMethod());
			UriInfo uriInfo = containerRequestContext.getUriInfo();
			Log.debugf("Query parameters: %s", containerRequestContext.getUriInfo().getQueryParameters());
			
			/*
			 * TODO: Verify the signature!
			 */
			//boolean fail = true;
			//if (fail) throw new WebApplicationException(Status.UNAUTHORIZED);

			/*
			 * Proceed.
			 */
			return context.proceed();
		} catch (NoSuchAlgorithmException e) {
			throw new WebApplicationException(e);
		}
	}
}
