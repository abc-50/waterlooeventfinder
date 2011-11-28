/**
 * 
 */
package com.waterlooeventfinder2.client;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

/**
 * @author Mike
 *
 */
public class utils {
	public static void setCookie(String cookieName, String cookieValue) {
		final long DURATION = 1000 * 60 * 60 * 24 * 5; //duration remembering login. 5 days
		Date expires = new Date(System.currentTimeMillis() + DURATION);
		Cookies.setCookie(cookieName, cookieValue, expires, null, "/", false);
	}
	
	public static void removeCookie(String cookieName) {
		Cookies.removeCookie(cookieName);
	}
	
	public static int getIntCookie(String cookieName) {
		String val = Cookies.getCookie(cookieName);
		if (val != null) {
			return Integer.parseInt(val);
		}
		
		return 0;
	}
	
	public static String getStringCookie(String cookieName) {
		return Cookies.getCookie(cookieName);
	}
}
