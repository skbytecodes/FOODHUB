package com.foodhub.helpers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		 System.out.println("session created");
	     HttpSession session = se.getSession();
	     session.setMaxInactiveInterval(24*60*60);
	     
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		 System.out.println("session destroyed");

	}

}
