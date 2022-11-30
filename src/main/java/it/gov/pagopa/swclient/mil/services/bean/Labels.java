/*
 * Labels.java
 *
 * 29 nov 2022
 */
package it.gov.pagopa.swclient.mil.services.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import it.gov.pagopa.swclient.mil.services.ErrorCode;

/**
 * 
 * @author Antonio Tarricone
 */
public class Labels {
	/*
	 * 
	 */
	@NotNull(message = "[" + ErrorCode.LABELS_IT_MUST_NOT_BE_NULL + "] labels.it must not be null")
	@Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "[" + ErrorCode.LABELS_IT_MUST_MATCH_REGEXP + "] labels.it must match \"{regexp}\"")
	private String it;

	/*
	 * 
	 */
	@Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "[" + ErrorCode.LABELS_EN_MUST_MATCH_REGEXP + "] labels.en must match \"{regexp}\"")
	private String en;

	/*
	 * 
	 */
	@Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "[" + ErrorCode.LABELS_FR_MUST_MATCH_REGEXP + "] labels.fr must match \"{regexp}\"")
	private String fr;

	/*
	 * 
	 */
	@Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "[" + ErrorCode.LABELS_DE_MUST_MATCH_REGEXP + "] labels.de must match \"{regexp}\"")
	private String de;

	/*
	 * 
	 */
	@Pattern(regexp = "^[\u0001-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]{1,256}$", message = "[" + ErrorCode.LABELS_ES_MUST_MATCH_REGEXP + "] labels.es must match \"{regexp}\"")
	private String es;

	/**
	 * @return the it
	 */
	public String getIt() {
		return it;
	}

	/**
	 * @param it the it to set
	 */
	public void setIt(String it) {
		this.it = it;
	}

	/**
	 * @return the en
	 */
	public String getEn() {
		return en;
	}

	/**
	 * @param en the en to set
	 */
	public void setEn(String en) {
		this.en = en;
	}

	/**
	 * @return the fr
	 */
	public String getFr() {
		return fr;
	}

	/**
	 * @param fr the fr to set
	 */
	public void setFr(String fr) {
		this.fr = fr;
	}

	/**
	 * @return the de
	 */
	public String getDe() {
		return de;
	}

	/**
	 * @param de the de to set
	 */
	public void setDe(String de) {
		this.de = de;
	}

	/**
	 * @return the es
	 */
	public String getEs() {
		return es;
	}

	/**
	 * @param es the es to set
	 */
	public void setEs(String es) {
		this.es = es;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Labels [it=").append(it).append(", en=").append(en).append(", fr=").append(fr).append(", de=").append(de).append(", es=").append(es).append("]");
		return builder.toString();
	}
}
