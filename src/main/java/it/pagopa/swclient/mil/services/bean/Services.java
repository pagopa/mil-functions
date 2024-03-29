/*
 * Services.java
 *
 * 29 nov 2022
 */
package it.pagopa.swclient.mil.services.bean;

import java.util.List;

import it.pagopa.swclient.mil.services.ErrorCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
