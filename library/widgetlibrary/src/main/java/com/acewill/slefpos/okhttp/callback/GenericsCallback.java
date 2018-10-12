package com.acewill.slefpos.okhttp.callback;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;

import okhttp3.Response;

/**
 * Created by JimGong on 2016/6/23.
 */

public abstract class GenericsCallback<T>
		extends Callback<T> {
	IGenericsSerializator mGenericsSerializator;

	public GenericsCallback(IGenericsSerializator serializator) {
		mGenericsSerializator = serializator;
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public T parseNetworkResponse(Response response, int id, String url, String requestParams)
			throws IOException {
		String string = response.body()
				.string();
		if (url.contains("orders")) {
			Log.e("kjljk", "string>" + string);
		}
		//		else if (url.contains("dishKind")) {
		//			FileLog
		//					.log(Common.Response, "", "onResponse", "", "获取菜品小类成功");
		//		} else {
		//			FileLog
		//					.log(Common.Response, "", "onResponse", "", format
		//							.format(new Date()) + "/url>" + url + ";\n" + format
		//							.format(new Date()) + "/response>" + string);
		//		}
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		if (entityClass == String.class) {
			return (T) string;
		}
		T bean = mGenericsSerializator.transform(string, entityClass);
		return bean;
	}


}
