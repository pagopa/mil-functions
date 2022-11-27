package it.gov.pagopa.swclient.mil.services.dto;

import java.util.List;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Services {
	@NotNull(message = "services must not be null")
	@Size(max = 64, message = "services size must be at most {max}")
	private List<Service> services;

	public Services() {
	}

	public Services(@NotNull(message = "services must not be null") @Size(max = 64, message = "services size must be at most {max}") List<Service> services) {
		this.services = services;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Services.class.getSimpleName() + "[", "]")
			.add("services=" + services)
			.toString();
	}
}
