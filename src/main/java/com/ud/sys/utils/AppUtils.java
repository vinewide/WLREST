package com.ud.sys.utils;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ud.sys.literals.AppLiterals;

public class AppUtils {

	private static final Logger logger = Logger.getLogger(AppUtils.class);

	public String getSuccessJSON(String successMessage, String methodName) {
		JSONObject outputJSONObject = new JSONObject();
		JSONArray jSONArray = new JSONArray();
		JSONObject jSONObject = new JSONObject();
		jSONObject.put(AppLiterals.USER_MESSAGE, methodName + " : " + successMessage);
		jSONObject.put(AppLiterals.ERROR_MESSAGE, "None");
		jSONArray.put(jSONObject);
		outputJSONObject.put(AppLiterals.STATUS, true);
		outputJSONObject.put(AppLiterals.RS, jSONArray);
		return outputJSONObject.toString();
	}

	public String getErrorJSON(String errorMessage, String methodName) {
		JSONObject outputJSONObject = new JSONObject();
		JSONArray jSONArray = new JSONArray();
		JSONObject jSONObject = new JSONObject();
		jSONObject.put(AppLiterals.USER_MESSAGE, DefaultErrorMessageEnum.STANDARD_ERROR_MESSAGE.getText());
		jSONObject.put(AppLiterals.ERROR_MESSAGE, "Exception :" + errorMessage);
		jSONArray.put(jSONObject);
		outputJSONObject.put(AppLiterals.STATUS, false);
		outputJSONObject.put(AppLiterals.RS, jSONArray);
		return outputJSONObject.toString();
	}
}
