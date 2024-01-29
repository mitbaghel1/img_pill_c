package com.ia.model;

public class WebResponseJsonBo {
	
	private String status;
	private String message;
	private Object retResponse;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getRetResponse() {
		return retResponse;
	}
	public void setRetResponse(Object retResponse) {
		this.retResponse = retResponse;
	}
}

