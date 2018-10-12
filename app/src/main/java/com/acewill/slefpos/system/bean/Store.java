package com.acewill.slefpos.system.bean;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author：Anch
 * Date：2017/12/25 14:19
 * Desc：
 */
public class Store {
	public static void setLanguageLocal(Context context, String language){
		SharedPreferences        preferences;
		SharedPreferences.Editor editor;
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		editor = preferences.edit();
		editor.putString("language", language);
		editor.commit();
	}

	public static String getLanguageLocal(Context context){
		SharedPreferences preferences;
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String language = preferences.getString("language", "");
		return language;
	}
}
