package com.open.digital.model;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
public class SrvcResModel<M> extends SrvcResDto {

	private M model;

	public SrvcResModel() {
		super();
	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}
}
