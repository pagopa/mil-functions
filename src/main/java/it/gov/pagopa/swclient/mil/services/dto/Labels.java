package it.gov.pagopa.swclient.mil.services.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class Labels {
    @NotNull(message = "it must not be null")
    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "it must match {regexp}")
    private String it;

    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "en must match {regexp}")
    private String en;

    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "fr must match {regexp}")
    private String fr;

    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "de must match {regexp}")
    private String de;

    @Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "es must match {regexp}")
    private String es;

    public Labels() {
    }

    public Labels(String it, String en, String fr, String de, String es) {
        this.it = it;
        this.en = en;
        this.fr = fr;
        this.de = de;
        this.es = es;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Labels.class.getSimpleName() + "[", "]")
                .add("it='" + it + "'")
                .add("en='" + en + "'")
                .add("fr='" + fr + "'")
                .add("de='" + de + "'")
                .add("es='" + es + "'")
                .toString();
    }
}
