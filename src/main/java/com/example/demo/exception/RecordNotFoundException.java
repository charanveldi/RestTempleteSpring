package com.example.demo.exception;

public class RecordNotFoundException extends Exception {

	private String errorMsg;

	public RecordNotFoundException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
