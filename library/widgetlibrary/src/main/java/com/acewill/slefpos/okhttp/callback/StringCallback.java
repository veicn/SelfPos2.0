package com.acewill.slefpos.okhttp.callback;


import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String> {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String parseNetworkResponse(Response response, int id, String url, String requestParams) throws IOException {
		String string = response.body().string();
	if (!url.contains("terminal") && !url.contains("getMemberInfo") && !url
				.contains("getAllTemplates") && !url.contains("getPrinters") && !url
				.contains("getKichenStalls") && !url.contains("getKDSes") && !url
				.contains("getStoreConfiguration")) {
			//			if (!url.contains("orders")){
			FileLog
					.log("Res", "", "onResponse", "", format
							.format(new Date()) + "/url>" + url + ";\n" + format
							.format(new Date()) + "/requestParams>" + requestParams + format
							.format(new Date()) + "/response>" + string);
			//			}else{
			//			FileLog
			//					.log("Res", "", "onResponse", "", format
			//							.format(new Date()) + "/url>" + url + ";\n" + format
			//							.format(new Date()) + "/response>" + string);
			//			}

		} else {
			FileLog
					.log("Res", "", "onResponse", "", format
							.format(new Date()) + "/url>" + url + ";\n");
		}
		return string;
	}
}
