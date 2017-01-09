package com.open.digital.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
@Component
public class Utils {
	
	public static final String RESKEYDLMTR = ".";
	public static final String LOGDLMTR = "|";
	public final String apiname;

	@Autowired
	public Utils(@Value("${api.name}") String apinamestr) {
		apiname = apinamestr;
	}

	public String getApiname() {
		return apiname;
	}

	public String getlogid(String callerlogid, String classname, String methodname) {
		String logid = "";
		if (null != callerlogid && !(callerlogid.equals("")) && !(callerlogid.equals(" "))){
			logid = callerlogid + Utils.LOGDLMTR + "{" + "\"" + "apiname" + "\":\"" + apiname + "\"," + "\"" + "classname" + "\":\"" + classname + "\"," + "\""
					+ "methodname" + "\":\"" + methodname + "\"" 
					+ "}";
		}else{
			logid = "{" + "\"" + "apiname" + "\":\"" + apiname + "\"," + "\"" + "classname" + "\":\"" + classname + "\"," + "\""
					+ "methodname" + "\":\"" + methodname + "\""
					+ "}";
		}
		return logid;
	}

	public String getjson(String prop, Object value) {
		String jsonstring = "";
		if (value instanceof String) {
			jsonstring = "\"" + prop + "\":\"" + value.toString() + "\"";
		} else {
			jsonstring = "\"" + prop + "\":" + value.toString();
		}
		return "{" + jsonstring + "}";
	}

	public String gethttpstatus(HttpStatus httpstatus) {
		String apiresult = Const.HTTP_2x;
		if (httpstatus.is2xxSuccessful()) {
			apiresult = Const.HTTP_2x;
		} else if (httpstatus.is5xxServerError()) {
			apiresult = Const.HTTP_5x;
		} else if (httpstatus.is4xxClientError()) {
			apiresult = Const.HTTP_4x;
		} else if (httpstatus.is1xxInformational()) {
			apiresult = Const.HTTP_1x;
		} else if (httpstatus.is3xxRedirection()) {
			apiresult = Const.HTTP_3x;
		}
		return apiresult;
	}
}
