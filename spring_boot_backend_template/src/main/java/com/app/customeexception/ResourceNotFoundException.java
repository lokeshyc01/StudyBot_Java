package com.app.customeexception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String errMsg)
	{
		super(errMsg);
	}
}
