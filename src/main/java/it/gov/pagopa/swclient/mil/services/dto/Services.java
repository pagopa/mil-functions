package it.gov.pagopa.swclient.mil.services.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.StringJoiner;

public class Services {
    @NotNull(message = "services must not be null")
    @Size(max = 64, message = "services size must be at most {max}")
    private Service[] services;

    public Services() {
    }

    public Services(@NotNull(message = "services must not be null") @Size(max = 64, message = "services size must be at most {max}") Service[] services) {
        this.services = services;
    }

    public Service[] getServices() {
        return services;
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Services.class.getSimpleName() + "[", "]")
                .add("services=" + Arrays.toString(services))
                .toString();
    }
}
