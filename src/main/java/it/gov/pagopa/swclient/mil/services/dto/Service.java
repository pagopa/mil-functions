package it.gov.pagopa.swclient.mil.services.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class Service {
    @NotNull(message = "labels must not be null")
    private Labels labels;

    @NotNull(message = "serviceId must not be null")
    @Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", message="serviceId must match {regexp}")
    private String serviceId;

    public Service() {
    }

    public Service(Labels labels, String serviceId) {
        this.labels = labels;
        this.serviceId = serviceId.toString();
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Service.class.getSimpleName() + "[", "]")
                .add("labels=" + labels)
                .add("serviceId=" + serviceId)
                .toString();
    }
}
