/**
 * 
 */
package com.self.crud.dto;

import java.io.Serializable;

/**
 * @author Arjun
 *
 */
public class ErrorResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6925993956959773626L;

	int status;
	
	String message;
	
	String name;
	
	/**
	 * @param status
	 * @param message
	 * @param name
	 */
	public ErrorResponse(int status, String message, String name) {
		super();
		this.status = status;
		this.message = message;
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
