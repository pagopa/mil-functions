/*
 * Error.java
 *
 * 26 nov 2022
 */
package it.gov.pagopa.swclient.mil.services;

/**
 * 
 * @author Antonio Tarricone
 */
public final class ErrorCode {
	public static final String MODULE_ID = "001";

	public static final String LABELS_IT_MUST_NOT_BE_NULL = MODULE_ID + "000001";
	public static final String LABELS_IT_MUST_MATCH_REGEXP = MODULE_ID + "000002";
	public static final String LABELS_EN_MUST_MATCH_REGEXP = MODULE_ID + "000003";
	public static final String LABELS_FR_MUST_MATCH_REGEXP = MODULE_ID + "000004";
	public static final String LABELS_DE_MUST_MATCH_REGEXP = MODULE_ID + "000005";
	public static final String LABELS_ES_MUST_MATCH_REGEXP = MODULE_ID + "000006";

	public static final String LABELS_MUST_NOT_BE_NULL = MODULE_ID + "000007";

	public static final String SERVICE_ID_MUST_NOT_BE_NULL = MODULE_ID + "000008";
	public static final String SERVICE_ID_MUST_MATCH_REGEXP = MODULE_ID + "000009";

	public static final String SERVICE_LIST_MUST_NOT_BE_NULL = MODULE_ID + "00000A";
	public static final String SERVICE_LIST_SIZE_MUST_BE_AT_MOST_MAX = MODULE_ID + "00000B";

	public static final String SERVICES_NOT_FOUND = MODULE_ID + "00000B";
	public static final String DUPLICATE_KEY = MODULE_ID + "00000C";
	public static final String DB_ERROR_WHILE_PERSISTING = MODULE_ID + "00000D";
	public static final String DB_ERROR_WHILE_FINDING = MODULE_ID + "00000E";

	private ErrorCode() {
		// This class cannot be instantiated!
	}
}
