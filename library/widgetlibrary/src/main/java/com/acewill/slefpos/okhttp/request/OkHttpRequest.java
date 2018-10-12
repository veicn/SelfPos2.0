package com.acewill.slefpos.okhttp.request;

import android.text.TextUtils;
import android.util.Log;

import com.acewill.slefpos.okhttp.callback.Callback;
import com.acewill.slefpos.okhttp.utils.Exceptions;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhy on 15/11/6.
 */
public abstract class OkHttpRequest {
	protected String              url;
	protected Object              tag;
	protected Map<String, String> params;
	protected Map<String, String> headers;
	protected int                 id;

	protected Request.Builder builder = new Request.Builder();

	protected OkHttpRequest(String url,
	                        Object tag,
	                        Map<String, String> params,
	                        Map<String, String> headers,
	                        int id) {
		this.url = url;
		this.tag = tag;
		this.params = params;
		this.headers = headers;
		this.id = id;
		Log.e("RequestUrl", "url>>" + url);
		if (url == null) {
			Exceptions.illegalArgument("url can not be null.");
		}

		initBuilder();
	}


	/**
	 * 初始化一些基本参数 url , tag , headers
	 */
	private void initBuilder() {
		builder.url(url)
				.tag(tag);
		appendHeaders();
	}

	protected abstract RequestBody buildRequestBody();

	protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
		return requestBody;
	}

	protected abstract Request buildRequest(RequestBody requestBody);

	public RequestCall build() {
		return new RequestCall(this);
	}


	public Request generateRequest(Callback callback) {
		RequestBody requestBody        = buildRequestBody();
		RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
		Request     request            = buildRequest(wrappedRequestBody);
		return request;
	}


	protected void appendHeaders() {
		Headers.Builder headerBuilder = new Headers.Builder();
		if (headers == null || headers.isEmpty()) {
			return;
		}

		for (String key : headers.keySet()) {
			headerBuilder.add(key, headers.get(key));
		}
		builder.headers(headerBuilder.build());
	}

	public int getId() {
		return id;
	}

	public Object getTag() {
		return tag;
	}

	public String getRequestParams() {
		if (params != null) {
			StringBuilder                       paramsSb = new StringBuilder();
			Iterator<Map.Entry<String, String>> entries  = params.entrySet().iterator();
			paramsSb.append("{");
			while (entries.hasNext()) {
				Map.Entry<String, String> entry = entries.next();
				if (!TextUtils.isEmpty(entry.getValue())&&!entry.getValue().startsWith("{")) {
					paramsSb.append("\"" + entry.getKey() + "\"" + ":" + "\"" + entry
							.getValue() + "\",");
				} else {
					paramsSb.append("\"" + entry.getKey() + "\"" + ":"  + entry
							.getValue() + ",");
				}
			}
			paramsSb.append("}");
			return paramsSb.toString();
		}
		return null;
	}

	public String geturl() {
		return url;
	}
}
