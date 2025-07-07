package com.tv_tracker.dao;

public class ShowException extends Exception {

    private static final long serialVersionUID = 1L;

	public ShowException(Show show) {
		super("show could not be Found: " + show);
	}

	public ShowException(String alert){
		super(alert);
	}

}
