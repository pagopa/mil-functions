/*
 * Error.java
 *
 * 26 nov 2022
 */
package it.gov.pagopa.swclient.mil.services.util;

/**
 * 
 * @author Antonio Tarricone
 */
public final class Error {
	public static final String MODULE_ID = "001";
	public static final String SERVICES_NOT_FOUND = MODULE_ID + "0000";
	public static final String DUPLICATE_KEY = MODULE_ID + "0001";
	public static final String ERROR_WHILE_PERSISTING = MODULE_ID + "0002";

	private Error() {
	}
}
