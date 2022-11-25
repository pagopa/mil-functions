package it.gov.pagopa.swclient.mil.services.dto;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;
import java.util.UUID;

public class Service {
    @NotNull(message = "labels must not be null")
    private Labels labels;

    @NotNull(message = "serviceId must not be null")
    private UUID serviceId;

    public Service() {
    }

    public Service(Labels labels, UUID serviceId) {
        this.labels = labels;
        this.serviceId = serviceId;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
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
