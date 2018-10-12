package com.acewill.slefpos.orderui.main.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.smarantstorebean.ImageEntity;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KdsEntity;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallEntity;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallsData;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.bean.smarantstorebean.Market;
import com.acewill.slefpos.bean.smarantstorebean.MarketData;
import com.acewill.slefpos.bean.smarantstorebean.PayTypeData;
import com.acewill.slefpos.bean.smarantstorebean.PaymentTypesEntity;
import com.acewill.slefpos.bean.smarantstorebean.PrinterData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterEntity;
import com.acewill.slefpos.bean.smarantstorebean.PrinterTemplatesData;
import com.acewill.slefpos.bean.smarantstorebean.SelfPosConfigurationData;
import com.acewill.slefpos.bean.smarantstorebean.SelfposConfigurationEntity;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitRequestData;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.orderui.main.contract.LoadDataContract;
import com.acewill.slefpos.orderui.main.uidataprovider.CXJDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.utils.RegisterUtil;
import com.acewill.slefpos.utils.fileutil.ZIPUtils;
import com.google.gson.Gson;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author：Anch
 * Date：2018/1/25 11:55
 * Desc：
 */
public class LoadDataPresenter extends LoadDataContract.Presenter {
	private static final String TAG = "LoadDataPresenter";

	@Override
	public void onStart() {
		super.onStart();
	}


	@Override
	public void goLoadData(int type) {
		switch (type) {
			case SystemConfig.System_Smarant:
				Observable<MarketData> marketList = mModel.getMarketList();
				Observable<PrinterData> printerList = mModel.getPrinterList();
				Observable<PayTypeData> payTypeList = mModel.getPayTypeList();
				Observable<ImagesData> imageList = mModel.getImageFiles();
				Observable<KichenStallsData> kichenStalls = mModel.getKichenStalls();
				Observable<KdsData> kdsInfo = mModel.getKDSInfo();
				Observable<SelfPosConfigurationData> selfposConfigurationdata = mModel
						.getSelfposConfiguration();


				//如果其中一个出错了，其他的没有出错会怎么样？？答：被观察者会终止发送事件
				mRxManage.add(Observable
						.merge(marketList, payTypeList, printerList, imageList, selfposConfigurationdata, kichenStalls, kdsInfo)
						.subscribeOn(Schedulers.io())
						.subscribe(new RxSubscriber<Object>(mContext, false) {
							@Override
							protected void _onNext(Object o) {
								Log.e(TAG, "onNext");
								if (o instanceof MarketData) {
									MarketData data = (MarketData) o;
									SmarantDataProvider.setMarketList(data);
									List<Market> content = data.getContent();
									if (content != null && content.size() > 0) {
										Market entity = content.get(0);
										String name   = entity.getMarketName();
										LogUtils.logd("tag", name);
									}
								}
								if (o instanceof PrinterTemplatesData) {
									PrinterTemplatesData data = (PrinterTemplatesData) o;
									SmarantDataProvider.setPrinterTemplatesData(data);
								}
								if (o instanceof PrinterData) {
									PrinterData data = (PrinterData) o;
									SmarantDataProvider.setPrinterList(data);
									List<PrinterEntity> content = data.getContent();
									if (content != null && content.size() > 0) {
										PrinterEntity entity = content.get(0);
										String        vendor = entity.getVendor();
										LogUtils.logd("tag", vendor);
									}
								}

								if (o instanceof PayTypeData) {
									PayTypeData data = (PayTypeData) o;
									SmarantDataProvider.setPayTypeList(data);
									List<PaymentTypesEntity> content = data.getPaymentTypes();
									if (content != null && content.size() > 0) {
										for (PaymentTypesEntity entity : content) {
											if (entity.getId() == PayMethod.MEITUAN) {
												StoreConfigure.setUseMeiTuan(true);
											}
											if (entity.getId() == PayMethod.WECHAT) {
												StoreConfigure.setUseWechat(true);
											}
											if (entity.getId() == PayMethod.ALI) {
												StoreConfigure.setUseAli(true);
											}
											String name = entity.getName();
											LogUtils.logd("tag", name);
										}
									}
								}
								if (o instanceof ImagesData) {
									ImagesData data = (ImagesData) o;
									SmarantDataProvider.setImageList(data);
									List<ImageEntity> content = data.getFiles();
									if (content != null && content.size() > 0) {
										ImageEntity entity   = content.get(0);
										String      fileName = entity.getFilename();
										LogUtils.logd("tag", fileName);
									}
								}
								if (o instanceof KichenStallsData) {
									KichenStallsData data = (KichenStallsData) o;
									SmarantDataProvider.setKichenStalls(data);
									List<KichenStallEntity> content = data.getContent();
									if (content != null && content.size() > 0) {
										KichenStallEntity entity     = content.get(0);
										String            stallsName = entity.getStallsName();
										LogUtils.logd("tag", stallsName);
									}
								}
								if (o instanceof KdsData) {
									KdsData data = (KdsData) o;
									SmarantDataProvider.setKdsInfo(data);
									List<KdsEntity> content = data.getContent();
									if (content != null && content.size() > 0) {
										KdsEntity entity  = content.get(0);
										String    kdsName = entity.getKdsName();
										LogUtils.logd("tag", kdsName);
									}
								}
								if (o instanceof SelfPosConfigurationData) {
									SelfPosConfigurationData data = (SelfPosConfigurationData) o;
									SmarantDataProvider.setSelfposConfigurationdata(data);
									SelfposConfigurationEntity entity = data.getContent();
									if (entity != null) {
										int time = entity.getScreenSaverTime();
										LogUtils.logd("tag", time + "");
									}
								}
							}

							@Override
							public void onCompleted() {
								super.onCompleted();
								Log.e(TAG, "onCompleted");
								mView.returnData(true);
							}

							@Override
							protected void _onError(String message) {
								ToastUitl.showLong(mContext, message);
								mView.returnData(false);
							}

							@Override
							protected void _onTimeOut() {
								mView.returnData(false);
							}
						}));
				break;

			case SystemConfig.System_Sync:
				Observable<SelfPosConfigurationData> selfposConfigurationdata3 = mModel
						.getSelfposConfiguration();
				Observable<ResponseBody> response = mModel
						.downLoadSyncData(mContext, new Gson().toJson(getRequestData()));
				mRxManage.add(Observable
						.merge(selfposConfigurationdata3, response)
						.subscribeOn(Schedulers.io())
						.subscribe(new RxSubscriber<Object>(mContext, false) {

							@Override
							public void onCompleted() {
								super.onCompleted();
								mView.returnData(true);
							}

							@Override
							protected void _onNext(Object object) {
								if (object instanceof ResponseBody) {
									try {
										ResponseBody body = (ResponseBody) object;
										InputStream is = body.byteStream();
										File file = new File(FileUtil
												.getSyncFoldPath(), "syncdata.zip");
										FileOutputStream    fos    = new FileOutputStream(file);
										BufferedInputStream bis    = new BufferedInputStream(is);
										byte[]              buffer = new byte[1024];
										int                 len;
										while ((len = bis.read(buffer)) != -1) {
											fos.write(buffer, 0, len);
											fos.flush();
										}
										fos.close();
										bis.close();
										is.close();
										if (file.exists()) {
											ZIPUtils.UnZipFolder(file.getAbsolutePath(), FileUtil
													.getSyncFoldPath() + "/jsondata");
										} else {
											ToastUitl.showLong(mContext, "数据文件不存在!");
											return;
										}
										//解压数据文件
										File localFile = new File(FileUtil
												.getSyncFoldPath() + "/jsondata");
										if (localFile.exists()) {
											for (File syncfile : localFile.listFiles()) {
												String filename = syncfile.getName();
												SyncDataProvider.initData(filename);
												Log.e("LoadDataPresenter", filename);
											}
										}

										new Thread(new Runnable() {
											@Override
											public void run() {
												//解压图片
												File imageFileOri = new File(FileUtil
														.getSyncFoldPath() + File.separator + "images.zip");
												if (imageFileOri.exists()) {
													try {
														ZIPUtils.UnZipFolder(imageFileOri
																.getAbsolutePath(), FileUtil
																.getSyncFoldPath() + "/imagedata");
													} catch (Exception e) {
														FileLog.log("解压图片异常.." + e.getMessage());
														e.printStackTrace();
													}
												}
											}
										}).start();

									} catch (Exception e) {
										FileLog.log("解压异常.." + e.getMessage());
										e.printStackTrace();
									}
								}

								if (object instanceof SelfPosConfigurationData) {
									SelfPosConfigurationData data = (SelfPosConfigurationData) object;
									SmarantDataProvider.setSelfposConfigurationdata(data);
									SelfposConfigurationEntity entity = data.getContent();
									if (entity != null) {
										int time = entity.getScreenSaverTime();
										LogUtils.logd("tag", time + "");
									}
								}

							}

							@Override
							protected void _onError(String message) {
								ToastUitl.showLong(mContext, message);
								mView.returnData(false);
							}

							@Override
							protected void _onTimeOut() {
								mView.returnData(false);
							}
						}));


				break;
			case SystemConfig.System_CanXingJian:
				if (TextUtils
						.isEmpty(SPUtils.getSharedStringData(mContext, "canxingjian_ip_address"))) {
					ToastUitl.showShort(mContext, "餐行健服务器地址不能为空");
					return;
				}
				ApiConstants.setCanXingJianAdress("http://" + SPUtils
						.getSharedStringData(mContext, "canxingjian_ip_address")
						+ "/");

				Observable<CxjLoginModel> cxjUserLogin = mModel
						.cxjUserLogin("1", "acewill");
				Observable<SelfPosConfigurationData> selfposConfigurationdata2 = mModel
						.getSelfposConfiguration();
				Observable<ImagesData> imageList2 = mModel.getImageFiles();
				Observable<PayTypeData> payTypeList2 = mModel.getPayTypeList();
				Observable<ResponseBody> canxingjian_response = mModel
						.downLoadCanxingjianData();
				mRxManage.add(Observable
						.merge(cxjUserLogin, selfposConfigurationdata2, imageList2, payTypeList2, canxingjian_response)
						.subscribeOn(Schedulers.io())
						.subscribe(new RxSubscriber<Object>(mContext, false) {
							@Override
							protected void _onNext(Object o) {
								if (o instanceof ImagesData) {
									ImagesData data = (ImagesData) o;
									SmarantDataProvider.setImageList(data);
									List<ImageEntity> content = data.getFiles();
									if (content != null && content.size() > 0) {
										ImageEntity entity   = content.get(0);
										String      fileName = entity.getFilename();
										LogUtils.logd("tag", fileName);
									}
								}
								if (o instanceof CxjLoginModel) {
									CxjLoginModel model = (CxjLoginModel) o;
									if (model.getSuccess() == 1) {
										ToastUitl.showLong(mContext, "登录成功");
									} else
										ToastUitl.showLong(mContext, "登录失败");
								}


								if (o instanceof SelfPosConfigurationData) {
									SelfPosConfigurationData data = (SelfPosConfigurationData) o;
									SmarantDataProvider.setSelfposConfigurationdata(data);
									SelfposConfigurationEntity entity = data.getContent();
									if (entity != null) {
										int time = entity.getScreenSaverTime();
										LogUtils.logd("tag", time + "");
									}
								}
								if (o instanceof PayTypeData) {
									PayTypeData data = (PayTypeData) o;
									SmarantDataProvider.setPayTypeList(data);
									List<PaymentTypesEntity> content = data.getPaymentTypes();
									if (content != null && content.size() > 0) {
										for (PaymentTypesEntity entity : content) {
											if (entity.getId() == PayMethod.MEITUAN) {
												StoreConfigure.setUseMeiTuan(true);
											}
											if (entity.getId() == PayMethod.WECHAT) {
												StoreConfigure.setUseWechat(true);
											}
											if (entity.getId() == PayMethod.ALI) {
												StoreConfigure.setUseAli(true);
											}
										}
									}
								}
								if (o instanceof ResponseBody) {
									try {
										ResponseBody body = (ResponseBody) o;
										InputStream  is   = body.byteStream();
										File file = new File(FileUtil
												.getCanXingJianFoldPath(), "menu.db");
										FileOutputStream    fos    = new FileOutputStream(file);
										BufferedInputStream bis    = new BufferedInputStream(is);
										byte[]              buffer = new byte[1024];
										int                 len;
										while ((len = bis.read(buffer)) != -1) {
											fos.write(buffer, 0, len);
											fos.flush();
										}
										fos.close();
										bis.close();
										is.close();
										CXJDataProvider.initMenuResp();
										new Thread(new Runnable() {
											@Override
											public void run() {
												//解压图片
												File imageFileOri = new File(FileUtil
														.getCanXingJianFoldPath() + File.separator + "images.zip");
												if (imageFileOri
														.exists()) {
													try {
														ZIPUtils.UnZipFolder(imageFileOri
																.getAbsolutePath(), FileUtil
																.getCanXingJianFoldPath() + "/imagedata");
													} catch (Exception e) {
														FileLog.log("解压图片异常.." + e
																.getMessage());
														e.printStackTrace();
													}
												}
											}
										}).start();

									} catch (Exception e) {
										FileLog.log("餐行健解压异常.." + e.getMessage());
										e.printStackTrace();
									}
								}
							}

							@Override
							public void onCompleted() {
								super.onCompleted();
								mView.returnData(true);
							}

							@Override
							protected void _onError(String message) {
								mView.returnData(false);
								ToastUitl.showLong(mContext, message);
							}

							@Override
							protected void _onTimeOut() {
								mView.returnData(false);
							}
						}));

				//				mRxManage.add(canxingjian_response
				//						.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
				//							@Override
				//							protected void _onNext(ResponseBody body) {
				//
				//							}
				//
				//							@Override
				//							protected void _onError(String message) {
				//								ToastUitl.showLong(mContext, message);
				//							}
				//
				//							@Override
				//							protected void _onTimeOut() {
				//								ToastUitl.showLong(mContext, "同步数据超时");
				//							}
				//						}));

				break;
		}
	}


	private SyncInitRequestData getRequestData() {
		SyncInitRequestData            data            = new SyncInitRequestData();
		SyncInitRequestData.HeaderBean bean            = null;
		String                         clientReference = null;
		String                         txReference     = null;
		bean = getCommonHeaderBean();
		SyncInitRequestData.DownLoadRequestBean bodyBean4 = new SyncInitRequestData.DownLoadRequestBean();
		clientReference = SPUtils.getSharedStringData(mContext, "clientReference");
		txReference = SPUtils.getSharedStringData(mContext, "txReference");
		bodyBean4.setCompanyOuid(SPUtils.getSharedStringData(mContext, "companyOuid"));
		bodyBean4.setStoreshopOuid(SPUtils.getSharedStringData(mContext, "storeshopOuid"));
		bodyBean4.setVersionSeqNumber("1");
		data.setBody(bodyBean4);
		bean.setClientReference(clientReference);
		bean.setTxReference(txReference);
		data.setHeader(bean);
		Log.e(TAG, "下载同步时数据的参数>" + new Gson().toJson(data));
		return data;
	}

	private SyncInitRequestData.HeaderBean getCommonHeaderBean() {
		SyncInitRequestData.HeaderBean bean     = new SyncInitRequestData.HeaderBean();
		Date                           date     = new Date();
		SimpleDateFormat               sformat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat               sformat2 = new SimpleDateFormat("HH:mm:ss");
		bean.setVersion(SPUtils.getSharedStringData(mContext, "version"));
		bean.setTxTime(sformat1.format(date) + "T" + sformat2.format(date));
		bean.setTimeZone("Asia/Shanghai");
		bean.setLocale("zh_CN");
		bean.setInstanceSid(RegisterUtil
				.buildInstanceSid(SPUtils.getSharedStringData(mContext, "companyOuid"), SPUtils
						.getSharedStringData(mContext, "storeshopOuid"), SPUtils
						.getSharedStringData(mContext, "terminalOuid")));
		return bean;
	}

	@Override
	public void goLogin() {
		mRxManage.add(mModel
				.login(BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(),
						TerminalConfigure.getTname(), TerminalConfigure
								.getTerminalmac(), "0", StoreConfigure
								.getLongitute(), StoreConfigure.getLatitute(), ""
						, TerminalConfigure.getCurrentVersion(), TerminalConfigure.getVersionid())
				.subscribe(new RxSubscriber<LoginEntity>(mContext, false) {
					@Override
					protected void _onNext(LoginEntity entity) {
						mView.returnLoginResult(entity);
					}

					@Override
					protected void _onError(String message) {
						if (message.equals("终端已经被解绑定，请重新绑定终端！")) {
							mView.clearSP();
						}
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						ToastUitl.showLong(mContext, "获取数据超时,请重试!");
					}
				}));
	}


}
