package com.managementsystem.persistence.dao.impl;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DaoException() {
		super();
	}

	public DaoException(String message){
		super(message);
	}
	
	public DaoException(String message, Throwable throwable){
		super(message, throwable);
	}
}
