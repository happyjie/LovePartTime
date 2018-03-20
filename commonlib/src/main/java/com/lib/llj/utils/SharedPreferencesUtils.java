package com.lib.llj.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SharedPreferencesUtils {
	private static SharedPreferencesUtils _instance;
	private static SharedPreferences _settings;

	/**
	 * 初始化
	 * @param context
	 */
	public static void init(Application context) {
		try {
			if (_instance == null)
				_instance = new SharedPreferencesUtils(context,  context.getPackageName()+"_sp");
			return;
		} finally {

		}
	}

	private SharedPreferencesUtils(Application context, String name) {
		_settings = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	/**
	 * SharedPreference是否包含key
	 * @param key
	 * @return
	 */
	public static boolean containkey(String key) {
		synchronized (_settings) {
			boolean bool = _settings.contains(key);
			return bool;
		}
	}

	public static ArrayList<String> getArrayList(String paramString) {
		ArrayList localArrayList = new ArrayList();
		String str = getString(paramString, "{}");
		while (true) {
			JSONArray localJSONArray;
			int i;
			try {
				localJSONArray = new JSONArray(str);
				if (localJSONArray != null) {
					i = 0;
					if (i < localJSONArray.length())
						;
				} else {
					return localArrayList;
				}
			} catch (Exception localException) {
				return localArrayList;
			}
			localArrayList.add(localJSONArray.optString(i));
			i++;
		}
	}

	public static boolean getBoolean(String key) {
		synchronized (_settings) {
			boolean bool = _settings.getBoolean(key, false);
			return bool;
		}
	}

	public static boolean getBoolean(String key, boolean defValue) {
		synchronized (_settings) {
			boolean bool = _settings.getBoolean(key, defValue);
			return bool;
		}
	}

	public static Double getDouble(String key) {
		String str = getString(key, null);
		if (str == null)
			return null;

		try {
			Double vl = Double.valueOf(Double.parseDouble(str));

			return vl;
		} catch (Exception e) {
			Log.e("calculate", "get rate error!");
		}
		return null;
	}

	public static HashMap<String, String> getHashMapByKey(String key) {
		HashMap localHashMap = new HashMap();
		String str1 = getString(key, "{}");
		while (true) {
			JSONObject localJSONObject;
			Iterator localIterator;
			try {
				localJSONObject = new JSONObject(str1);
				if (localJSONObject != null) {
					localIterator = localJSONObject.keys();
					if (localIterator.hasNext())
						;
				} else {
					return localHashMap;
				}
			} catch (Exception e) {
				return localHashMap;
			}
			String str2 = (String) localIterator.next();
			localHashMap.put(str2, localJSONObject.optString(str2));
		}
	}

	public static JSONArray getJsonArray(String key) {
		String str = getString(key);
		try {
			JSONArray localJSONArray = new JSONArray(str);
			return localJSONArray;
		} catch (JSONException e) {
		}
		return null;
	}

	public static void putLong(String key, Long value) {
		synchronized (_settings) {
			_settings.edit().putLong(key, value.longValue()).commit();
			return;
		}
	}

	public static Long getLong(String key) {
		synchronized (_settings) {
			try {
				Long l = Long.valueOf(_settings.getLong(key, 0L));
				return l;
			} catch (Exception e) {
				e.printStackTrace();
				return -1L;
			}

		}
	}

	public static Boolean getOptBoolean(String key) {
		String str = getString(key, null);
		try {
			Boolean localBoolean = Boolean.valueOf(Boolean.parseBoolean(str));
			return localBoolean;
		} catch (Exception e) {
		}
		return null;
	}

	public static Double getOptDouble(String key) {
		String str = getString(key, null);
		try {
			Double localDouble = Double.valueOf(Double.parseDouble(str));
			return localDouble;
		} catch (Exception e) {
		}
		return null;
	}

	public static String getString(String key) {
		synchronized (_settings) {
			String str = _settings.getString(key, "");
			return str;
		}
	}

	public static String getString(String key, String defValue) {
		synchronized (_settings) {
			String str = _settings.getString(key, defValue);
			return str;
		}
	}

	public static void putInt(String key, int value){
		synchronized (_settings) {
			_settings.edit().putInt(key, value).commit();
			return;
		}
	}

	public static int getInt(String key) {
		synchronized (_settings) {
			int ret = _settings.getInt(key, 0);
			return ret;
		}
	}

	public static int getInt(String key, int defaultVal) {
		synchronized (_settings) {
			int ret = _settings.getInt(key, defaultVal);
			return ret;
		}
	}

	public static SharedPreferencesUtils get_instance() {
		return _instance;
	}

	public static void putJsonArray(String key, JSONArray jsonArray) {
		putString(key, jsonArray.toString());
	}

	public static void putString(String key, String value) {
		synchronized (_settings) {
			_settings.edit().putString(key, value).commit();
			return;
		}
	}

	public static void removeByKey(String key) {
		synchronized (_settings) {
			_settings.edit().remove(key).commit();
			return;
		}
	}

	public static void putArrayList(String key, ArrayList<String> list) {
		putString(key, new JSONArray(list).toString());
	}

	public static void putBoolean(String key, boolean value) {
		synchronized (_settings) {
			_settings.edit().putBoolean(key, value).commit();
			return;
		}
	}

	public static void putHashMap(String key, HashMap<String, String> hashMap) {
		JSONObject localJSONObject = new JSONObject(hashMap);
		synchronized (_settings) {
			_settings.edit().putString(key, localJSONObject.toString()).commit();
			return;
		}
	}

}