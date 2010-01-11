package com.model.mvc.web.core.constant;

public enum JSONResultCode {
	SUCCESS (0),
	FAIL (1),
	;

	private int resultCode;

	private JSONResultCode(int resultCode){
		this.resultCode = resultCode;
	}
	public int getValue() {
		return resultCode;
	}
}
