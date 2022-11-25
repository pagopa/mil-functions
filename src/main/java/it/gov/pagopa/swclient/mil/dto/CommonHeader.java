package it.gov.pagopa.swclient.mil.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.HeaderParam;
import java.util.StringJoiner;

public class CommonHeader {
    @HeaderParam("Version")
    @Size(max = 64, message = "Version size must be at most {max}")
    @Pattern(regexp = "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$", message = "Version must match {regexp}")
    private String version;

    @HeaderParam("AcquirerId")
    @NotNull(message = "AcquirerId must not be null")
    @Pattern(regexp = "^\\d{1,11}$", message = "AcquirerId must match {regexp}")
    private String acquirerId;

    @HeaderParam("Channel")
    @NotNull(message = "Channel must not be null")
    @Pattern(regexp = "^(ATM|POS|TOTEM|CASH_REGISTER|CSA)$", message = "Channel must match {regexp}")
    private String channel;

    @HeaderParam("TerminalId")
    @NotNull(message = "TerminalId must not be null")
    @Pattern(regexp = "^[0-9a-zA-Z]{8}$", message = "TerminalId must match {regexp}")
    private String terminalId;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    public Channel getChannel() {
        return Channel.valueOf(channel);
    }

    public void setChannel(Channel channel) {
        this.channel = channel.name();
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonHeader.class.getSimpleName() + "[", "]")
                .add("version='" + version + "'")
                .add("acquirerId='" + acquirerId + "'")
                .add("channel=" + channel)
                .add("terminalId='" + terminalId + "'")
                .toString();
    }
}
