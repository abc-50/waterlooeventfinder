package com.waterlooeventfinder2.client;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

/**
 * Helper functions
 * 
 * @author Mike
 * 
 */
public class utils {
	/**
	 * Set cookie to value
	 * 
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(String cookieName, String cookieValue) {
		final long DURATION = 1000 * 60 * 60 * 24 * 5; // duration remembering
														// login. 5 days

		Date expires = new Date(System.currentTimeMillis() + DURATION);
		Cookies.setCookie(cookieName, cookieValue, expires, null, "/", false);
	}

	/**
	 * Remove a cookie
	 * 
	 * @param cookieName
	 */
	public static void removeCookie(String cookieName) {
		Cookies.removeCookie(cookieName);
	}

	/**
	 * Get value of cookie as int
	 * 
	 * @param cookieName
	 * @return integer value of cookie value
	 */
	public static int getIntCookie(String cookieName) {
		String val = Cookies.getCookie(cookieName);
		if (val != null) {
			return Integer.parseInt(val);
		}

		return 0;
	}

	/**
	 * Get string value of cookie
	 * 
	 * @param cookieName
	 * @return string value
	 */
	public static String getStringCookie(String cookieName) {
		return Cookies.getCookie(cookieName);
	}
}
