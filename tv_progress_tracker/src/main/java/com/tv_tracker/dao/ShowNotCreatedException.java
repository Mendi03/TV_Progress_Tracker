package com.tv_tracker.dao;

public class ShowNotCreatedException extends Exception {

    private static final long serialVersionUID = 1L;

	public ShowNotCreatedException(Show show) {
		super("Chef with the following values could not be created: " + show);
	}
}
