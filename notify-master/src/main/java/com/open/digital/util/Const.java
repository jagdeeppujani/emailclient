package com.open.digital.util;

import org.springframework.stereotype.Component;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
@Component
public class Const {

	public static final String HTTP_HEADER_RES_MSG_KEY = "RES-MSG-KEY";
	public static final String HTTP_HEADER_RES_MSG_VAL = "RES-MSG-VAL";

	public static final String HTTP_1x = "info";
	public static final String HTTP_2x = "ok";
	public static final String HTTP_3x = "redir";
	public static final String HTTP_4x = "clnt.ko";
	public static final String HTTP_5x = "srvr.ko";
	public static final String NO_MSG_KEY = "NOMSGKEY";
	public static final String NO_VALUE = "NOVALUE";
	public static final String NO_MSG_ID = "NOMSGID";
}
