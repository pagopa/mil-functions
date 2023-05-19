/*
 * Service.java
 *
 * 29 nov 2022
 */
package it.pagopa.swclient.mil.services.bean;

import it.pagopa.swclient.mil.services.ErrorCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 
 * @author Antonio Tarricone
 */
public class Service {
	/*
	 * 
	 */
	@NotNull(message = "[" + ErrorCode.LABELS_MUST_NOT_BE_NULL + "] labels must not be null")
	private Labels labels;

	/*
	 * 
	 */
	@NotNull(message = "[" + ErrorCode.SERVICE_ID_MUST_NOT_BE_NULL + "] serviceId must not be null")
	@Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", message = "[" + ErrorCode.SERVICE_ID_MUST_MATCH_REGEXP + "] serviceId must match \"{regexp}\"")
	private String serviceId;

	/**
	 * @return the labels
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Service [labels=").append(labels).append(", serviceId=").append(serviceId).append("]");
		return builder.toString();
	}
}
