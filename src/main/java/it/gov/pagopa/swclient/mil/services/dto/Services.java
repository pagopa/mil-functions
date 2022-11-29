/*
 * Services.java
 *
 * 29 nov 2022
 */
package it.gov.pagopa.swclient.mil.services.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.gov.pagopa.swclient.mil.services.util.ErrorCode;

/**
 * 
 * @author Antonio Tarricone
 */
public class Services {
	/*
	 * 
	 */
	@NotNull(message = "[" + ErrorCode.SERVICE_LIST_MUST_NOT_BE_NULL + "] service list must not be null")
	@Size(max = 64, message = "[" + ErrorCode.SERVICE_LIST_SIZE_MUST_BE_AT_MOST_MAX + "] service list size must be at most {max}")
	private List<Service> services;

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Services [services=").append(services).append("]");
		return builder.toString();
	}
}
