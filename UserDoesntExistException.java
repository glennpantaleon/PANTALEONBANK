package com.revature.bankexception;

public class UserDoesntExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserDoesntExistException() {
	}
	
	public UserDoesntExistException(String message) {
	}
	
	public String getMessage() {
		return "This User Does not Exist in Bank Database.";
	}
}
