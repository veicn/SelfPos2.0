package com.acewill.slefpos.okhttp;


import com.acewill.slefpos.okhttp.builder.GetBuilder;
import com.acewill.slefpos.okhttp.builder.HeadBuilder;
import com.acewill.slefpos.okhttp.builder.OtherRequestBuilder;
import com.acewill.slefpos.okhttp.builder.PostFileBuilder;
import com.acewill.slefpos.okhttp.builder.PostFormBuilder;
import com.acewill.slefpos.okhttp.builder.PostStringBuilder;
import com.acewill.slefpos.okhttp.callback.Callback;
import com.acewill.slefpos.okhttp.request.RequestCall;
import com.acewill.slefpos.okhttp.utils.Platform;
import com.acewill.slefpos.print.Common;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
	public static final long DEFAULT_MILLISECONDS = 10000;
	private volatile static OkHttpUtils  mInstance;
	private                 OkHttpClient mOkHttpClient;
	private                 Platform     mPlatform;

	public OkHttpUtils(OkHttpClient okHttpClient) {
		if (okHttpClient == null) {
			mOkHttpClient = new OkHttpClient();
		} else {
			mOkHttpClient = okHttpClient;
		}

		mPlatform = Platform.get();
	}


	public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
		if (mInstance == null) {
			synchronized (OkHttpUtils.class) {
				if (mInstance == null) {
					mInstance = new OkHttpUtils(okHttpClient);
				}
			}
		}
		return mInstance;
	}

	public static OkHttpUtils getInstance() {
		return initClient(null);
	}


	public Executor getDelivery() {
		return mPlatform.defaultCallbackExecutor();
	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	public static GetBuilder get() {
		return new GetBuilder();
	}

	public static PostStringBuilder postString() {
		return new PostStringBuilder();
	}

	public static PostFileBuilder postFile() {
		return new PostFileBuilder();
	}

	public static PostFormBuilder post() {
		return new PostFormBuilder();
	}

	public static OtherRequestBuilder put() {
		return new OtherRequestBuilder(METHOD.PUT);
	}

	public static HeadBuilder head() {
		return new HeadBuilder();
	}

	public static OtherRequestBuilder delete() {
		return new OtherRequestBuilder(METHOD.DELETE);
	}

	public static OtherRequestBuilder patch() {
		return new OtherRequestBuilder(METHOD.PATCH);
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void execute(final RequestCall requestCall, Callback callback) {
		if (callback == null)
			callback = Callback.CALLBACK_DEFAULT;
		final Callback finalCallback = callback;
		final int      id            = requestCall.getOkHttpRequest().getId();

		final String requestParams = requestCall.getOkHttpRequest().getRequestParams();
		final String url           = requestCall.getOkHttpRequest().geturl();

		requestCall.getCall().enqueue(new okhttp3.Callback() {

			@Override
			public void onFailure(okhttp3.Call call, IOException e) {
				sendFailResultCallback(call, e, finalCallback, id);
				errorLog(url, requestParams, e.getMessage());
			}

			@Override
			public void onResponse(okhttp3.Call call, Response response) throws IOException {

				try {
					if (call.isCanceled()) {
						sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
						errorLog(url, requestParams, "Canceled!");
						return;
					}

					if (!finalCallback.validateReponse(response, id)) {
						sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response
								.code()), finalCallback, id);
						errorLog(url, requestParams, "request failed , reponse's code is : " + response
								.code());
						return;
					}

					Object o = finalCallback.parseNetworkResponse(response, id, url, requestParams);
					sendSuccessResultCallback(o, finalCallback, id);
				} catch (Exception e) {
					sendFailResultCallback(call, e, finalCallback, id);
					errorLog(url, requestParams, e.getMessage());
				} finally {
					if (response.body() != null)
						response.body().close();
				}
			}
		});
	}

	private void errorLog(String url, String requestParams, String reason) {
		FileLog
				.log(Common.Error, "", "onError", "", format
						.format(new Date()) + "/url>" + url + "\n" + format
						.format(new Date()) + "/requestParams>" + requestParams + "\n" + format
						.format(new Date()) + "/errmsg>" + reason);
	}

	public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
		if (callback == null)
			return;

		mPlatform.execute(new Runnable() {
			@Override
			public void run() {
				callback.onError(call, e, id);
				callback.onAfter(id);
			}
		});
	}

	public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {

		if (callback == null)
			return;
		mPlatform.execute(new Runnable() {
			@Override
			public void run() {
				callback.onResponse(object, id);
				callback.onAfter(id);
			}
		});
	}

	public void cancelTag(Object tag) {
		for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
		for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
	}

	public static class METHOD {
		public static final String HEAD   = "HEAD";
		public static final String DELETE = "DELETE";
		public static final String PUT    = "PUT";
		public static final String PATCH  = "PATCH";
	}
}

