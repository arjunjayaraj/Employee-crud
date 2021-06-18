/**
 * 
 */
package com.self.crud.exception;

import java.io.Serializable;

import io.swagger.annotations.Api;

/**
 * @author Arjun
 *
 */

@Api(value = "BadRequestException")
public class BadRequestException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092853727999173595L;
	
	String code = "400";

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
