package com.acewill.slefpos.orderui.main.model;

import android.content.Context;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallsData;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.bean.smarantstorebean.MarketData;
import com.acewill.slefpos.bean.smarantstorebean.PayTypeData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterTemplatesData;
import com.acewill.slefpos.bean.smarantstorebean.SelfPosConfigurationData;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.LoadDataContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.exception.ApiException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/1/25 11:55
 * Desc：
 */
public class LoadDataModel extends LoadDataContract.Model {

	private static final String TAG = "LoadDataModel";

	@Override
	public Observable<PrinterData> getPrinterList() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getPrinters(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<PrinterData, PrinterData>() {
					@Override
					public PrinterData call(PrinterData data) {
						return data;
					}
				}).compose(RxSchedulers.<PrinterData>io_main());
	}

	@Override
	public Observable<MarketData> getMarketList() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.market(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<MarketData, MarketData>() {
					@Override
					public MarketData call(MarketData data) {
						return data;
					}
				}).compose(RxSchedulers.<MarketData>io_main());
	}

	@Override
	public Observable<PayTypeData> getPayTypeList() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.payTypes(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<PayTypeData, PayTypeData>() {
					@Override
					public PayTypeData call(PayTypeData data) {
						return data;
					}
				}).compose(RxSchedulers.<PayTypeData>io_main());
	}

	@Override
	public Observable<ImagesData> getImageFiles() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getOtherfiles(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<ImagesData, ImagesData>() {
					@Override
					public ImagesData call(ImagesData data) {
						return data;
					}
				}).compose(RxSchedulers.<ImagesData>io_main());
	}

	@Override
	public Observable<SelfPosConfigurationData> getSelfposConfiguration() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getSelfposConfiguration(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<SelfPosConfigurationData, SelfPosConfigurationData>() {
					@Override
					public SelfPosConfigurationData call(SelfPosConfigurationData data) {
						return data;
					}
				}).compose(RxSchedulers.<SelfPosConfigurationData>io_main());
	}

	@Override
	public Observable<KichenStallsData> getKichenStalls() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getKichenStalls(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<KichenStallsData, KichenStallsData>() {
					@Override
					public KichenStallsData call(KichenStallsData data) {
						return data;
					}
				}).compose(RxSchedulers.<KichenStallsData>io_main());
	}

	@Override
	public Observable<KdsData> getKDSInfo() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getKDSes(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<KdsData, KdsData>() {
					@Override
					public KdsData call(KdsData data) {
						return data;
					}
				}).compose(RxSchedulers.<KdsData>io_main());
	}

	@Override
	public Observable<ResponseBody> downLoadSyncData(final Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS)
				.downSyncData(body).map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody body) {
						return body;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());

	}

	@Override
	public Observable<ResponseBody> downLoadCanxingjianData() {
		return Api.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.downCanXingJianData().map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody body) {
						return body;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
	}



	@Override
	public Observable<LoginEntity> login(String appid, int brandid, int storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.login(Api
						.getCacheControl(), appid, brandid, storeid, tname, terminalmac, receiveNetOrder, longitute, latitute, description, currentVersion, versionid)
				.map(new Func1<LoginEntity, LoginEntity>() {
					@Override
					public LoginEntity call(LoginEntity entity) {
						if (!"0".equals(entity.getResult()))
							throw new ApiException(entity.getErrmsg());
						return entity;
					}
				}).compose(RxSchedulers.<LoginEntity>io_main());

	}

	@Override
	public Observable<PrinterTemplatesData> getAllTemplates() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.getAllTemplates(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure
						.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<PrinterTemplatesData, PrinterTemplatesData>() {
					@Override
					public PrinterTemplatesData call(PrinterTemplatesData data) {
						return data;
					}
				}).compose(RxSchedulers.<PrinterTemplatesData>io_main());
	}

	@Override
	public Observable<CxjLoginModel> cxjUserLogin(String username, String pwd) {
		return Api.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.cxjUserLogin(username, pwd).map(new Func1<CxjLoginModel, CxjLoginModel>() {
					@Override
					public CxjLoginModel call(CxjLoginModel body) {
						return body;
					}
				}).compose(RxSchedulers.<CxjLoginModel>io_main());
	}

	//	@Override
	//	public void downLoadSyncData(final Context context, String data) {
	//		RequestBody body = RequestBody
	//				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
	//		Api.getDefault().downSyncData(body).subscribeOn(Schedulers.io())
	//				.observeOn(AndroidSchedulers.mainThread())
	//				.subscribe(new Subscriber<ResponseBody>() {
	//					@Override
	//					public void onCompleted() {
	//
	//						Log.e(TAG, "onCompleted");
	//					}
	//
	//					@Override
	//					public void onError(Throwable e) {
	//						Log.e(TAG, "onError");
	//					}
	//
	//					@Override
	//					public void onNext(ResponseBody response) {
	//
	//					}
	//				});
	//
	//	}


	//	@Override
	//	public void downLoadSyncData(final Context context, String data) {
	//		RequestBody body = RequestBody
	//				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
	//		Api.getDefault().data(body).enqueue(new Callback<ResponseBody>() {
	//			@Override
	//			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
	//				try {
	//					if (response.code() == 200) {
	//						InputStream is = response.body().byteStream();
	//						File file = new File(FileUtil
	//								.getSyncFoldPath(), "syncdata.zip");
	//						FileOutputStream    fos    = new FileOutputStream(file);
	//						BufferedInputStream bis    = new BufferedInputStream(is);
	//						byte[]              buffer = new byte[1024];
	//						int                 len;
	//						while ((len = bis.read(buffer)) != -1) {
	//							fos.write(buffer, 0, len);
	//							fos.flush();
	//						}
	//						fos.close();
	//						bis.close();
	//						is.close();
	//						if (file.exists()) {
	//							ZIPUtils.UnZipFolder(file.getAbsolutePath(), FileUtil
	//									.getSyncFoldPath() + "/jsondata");
	//						} else {
	//							ToastUitl.showLong(context, "数据文件不存在!");
	//							return;
	//						}
	//
	//					} else {
	//						ToastUitl.showLong(context, response.code());
	//					}
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//				Log.e(TAG, "success");
	//			}
	//
	//			@Override
	//			public void onFailure(Call<ResponseBody> call, Throwable t) {
	//				Log.e("SyncMainModel", "fail");
	//			}
	//		});
	//	}

}
