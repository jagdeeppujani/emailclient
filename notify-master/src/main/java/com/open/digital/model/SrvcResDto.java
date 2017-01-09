package com.open.digital.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.digital.util.Const;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
public class SrvcResDto {
	// Response Http Code for the result
	// of the ops on the entity
	private HttpStatus httpstatus = HttpStatus.OK;

	// Response Message key
	private String resmsgkey = Const.NO_MSG_KEY;

	// Response Message value
	private String resmsgval = Const.NO_VALUE;

	public SrvcResDto() {

	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}

	public String getResmsgkey() {
		return resmsgkey;
	}

	public void setResmsgkey(String resmsgkey) {
		this.resmsgkey = resmsgkey;
	}

	public String getResmsgval() {
		return resmsgval;
	}

	public void setResmsgval(String resmsgval) {
		this.resmsgval = resmsgval;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}