package com.acewill.slefpos.api;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.emuee.HostType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * des:retorfit api
 * Created by xsf
 * on 2016.06.15:47
 */
public class Api {

	/**
	 * 小知识
	 * connect timeout 是建立连接的超时时间，read timeout 是传递数据的超时时间
	 */


	//读超时长，单位：毫秒
	public static final int READ_TIME_OUT    = 30000;
	//连接时长，单位：毫秒
	public static final int CONNECT_TIME_OUT = 15000;
	public Retrofit     retrofit;
	public ApiService   apiService;
	public OkHttpClient okHttpClient;
	private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

	/*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

	/**
	 * 设缓存有效期为两天
	 */
	private static final long   CACHE_STALE_SEC     = 60 * 60 * 24 * 2;
	/**
	 * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
	 * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
	 */
	private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
	/**
	 * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
	 * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
	 */
	private static final String CACHE_CONTROL_AGE   = "max-age=0";


	//构造方法私有
	private Api(int hostType) {
		//开启Log
		HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
		logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		//缓存
		File  cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
		Cache cache     = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
		//增加头部信息
		Interceptor headerInterceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request build = chain.request().newBuilder()
						.addHeader("Content-Type", "application/json")
						.build();
				return chain.proceed(build);
			}
		};

		okHttpClient = new OkHttpClient.Builder()
				.readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
				.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
				//				.addInterceptor(mRewriteCacheControlInterceptor)
				//				.addNetworkInterceptor(mRewriteCacheControlInterceptor)
				.addInterceptor(headerInterceptor)
				.addInterceptor(new MyLogInterceptor())
				//				.cache(cache)
				.cookieJar(cookieJar)
				.build();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls()
				.create();
		// 步骤1：创建Retrofit对象
		retrofit = new Retrofit.Builder()
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson))////设置使用Gson解析
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 支持RxJava
				.baseUrl(ApiConstants.getHost(hostType))
				.build();
		// 步骤2：创建 网络请求接口 的实例
		apiService = retrofit.create(ApiService.class);
	}


	private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();
	//初始化Cookie管理器
	CookieJar cookieJar = new CookieJar() {
		//Cookie缓存区

		@Override
		public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
			// TODO Auto-generated method stub
			//移除相同的url的Cookie
			String       host        = arg0.host();
			List<Cookie> cookiesList = cookiesMap.get(host);
			if (cookiesList != null) {
				cookiesMap.remove(host);
			}
			//再重新天添加
			cookiesMap.put(host, arg1);
		}

		@Override
		public List<Cookie> loadForRequest(HttpUrl arg0) {
			// TODO Auto-generated method stub
			List<Cookie> cookiesList = cookiesMap.get(arg0.host());
			//注：这里不能返回null，否则会报NULLException的错误。
			//原因：当Request 连接到网络的时候，OkHttp会调用loadForRequest()
			return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
		}
	};

	private class MyLogInterceptor implements Interceptor {

		@Override
		public Response intercept(Chain chain) throws IOException {
			//获得请求信息，此处如有需要可以添加headers信息
			Request request = chain.request();
			//添加Cookie信息
			request.newBuilder().addHeader("Cookie", "aaaa");
			StringBuilder sb = new StringBuilder();
			//打印请求信息
			Log.d("Api", "url:" + request.url());
			sb.append("url:" + request.url() + "\n");
			Log.d("Api", "method:" + request.method());
			sb.append("method:" + request.method() + "\n");
			Log.d("Api", "request-body:" + request.body());
			sb.append("request-body:" + request.body() + "\n");
			//记录请求耗时
			long             startNs = System.nanoTime();
			okhttp3.Response response;
			try {
				//发送请求，获得相应，
				response = chain.proceed(request);
			} catch (Exception e) {
				throw e;
			}
			long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
			//打印请求耗时
			Log.d("Api", "耗时:" + tookMs + "ms");
			sb.append("耗时:" + tookMs + "ms" + "\n");
			//使用response获得headers(),可以更新本地Cookie。
			Log.d("Api", "headers==========");
			sb.append("headers==========" + "\n");
			Headers headers = response.headers();
			Log.d("Api", headers.toString());
			sb.append(headers.toString() + "\n");
			//获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
			ResponseBody responseBody = response.body();

			//为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
			BufferedSource source = responseBody.source();
			source.request(Long.MAX_VALUE); // Buffer the entire body.
			//获得返回的数据
			Buffer buffer = source.buffer();
			//使用前clone()下，避免直接消耗
			Log.d("Api", "response:" + buffer.clone().readString(Charset.forName("UTF-8")));
			sb.append("response:" + buffer.clone().readString(Charset.forName("UTF-8")) + "\n");

			String sUrl = request.url().toString();
			//			!sUrl.contains("terminal") && !sUrl.contains("getMemberInfo") && !sUrl
			//					.contains("getAllTemplates") && !sUrl.contains("getPrinters") && !sUrl
			//					.contains("getKichenStalls") && !sUrl.contains("getKDSes") && !sUrl
			//					.contains("getStoreConfiguration") &&

			if (sUrl
					.contains("data.action") || sUrl.contains("terminal/dishmenu") || sUrl
					.contains("terminal/dishKind") || sUrl
					.contains("terminal/paytypes") || sUrl
					.contains("getPrinters") || sUrl
					.contains("terminal/getSelfposConfiguration") || sUrl
					.contains("terminal/getOtherfiles") || sUrl
					.contains("terminal/market") || sUrl
					.contains("getKichenStalls")
					|| (SystemConfig.isSmarantSystem && sUrl.contains("getMemberInfo")) || sUrl
					.contains("downloadSqliteFile")) {
				FileLog
						.log("Res", "", "onResponse", "", "url>" + sUrl + ";\n");
			} else {
				FileLog.log("Api", Api.class, "intercept", "log", sb.toString());
			}
			return response;
		}
	}

	public static ApiService getDefault(int hostType) {
		Api retrofitManager = sRetrofitManager.get(hostType);
		if (retrofitManager == null) {
			retrofitManager = new Api(hostType);
			sRetrofitManager.put(hostType, retrofitManager);
		}
		return retrofitManager.apiService;
	}


	//	/**
	//	 * OkHttpClient
	//	 *
	//	 * @return
	//	 */
	//	public static OkHttpClient getOkHttpClient() {
	//		Api retrofitManager = sRetrofitManager.get(HostType.NORMAL_HOST);
	//		if (retrofitManager == null) {
	//			retrofitManager = new Api(HostType.NORMAL_HOST);
	//			sRetrofitManager.put(HostType.NORMAL_HOST, retrofitManager);
	//		}
	//		return retrofitManager.okHttpClient;
	//	}


	/**
	 * 根据网络状况获取缓存的策略
	 */
	@NonNull
	public static String getCacheControl() {
		return NetWorkUtils.isNetConnected(BaseApplication
				.getAppContext()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
	}

	/**
	 * 云端响应头拦截器，用来配置缓存策略
	 * Dangerous interceptor that rewrites the server's cache-control header.
	 */
	private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			Request request      = chain.request();
			String  cacheControl = request.cacheControl().toString();
			if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
				request = request.newBuilder()
						.cacheControl(TextUtils
								.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
						.build();
			}
			Response originalResponse = chain.proceed(request);
			if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
				//有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

				return originalResponse.newBuilder()
						.header("Cache-Control", cacheControl)
						.removeHeader("Pragma")
						.build();
			} else {
				return originalResponse.newBuilder()
						.header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
						.removeHeader("Pragma")
						.build();
			}
		}
	};
}