package com.framework.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class BaseHelper {
	public static String formatJSON(JSONObject json) throws JSONException {
		return json.toString(2);
	}
}
