package com.acewill.slefpos.orderui.main.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.acewill.paylibrary.EPayTask;
import com.acewill.paylibrary.MicropayTask;
import com.acewill.paylibrary.PayReqModel;
import com.acewill.paylibrary.epay.EPayResult;
import com.acewill.paylibrary.epay.PayStatus;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjWriteTouchTextRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjWshYuJieRes;
import com.acewill.slefpos.bean.meituanbean.ExecuteMeituanCodeResult;
import com.acewill.slefpos.bean.meituanbean.ValidationSetoutResult;
import com.acewill.slefpos.bean.orderbean.CheckCountResp;
import com.acewill.slefpos.bean.orderbean.NewOrderIdRes;
import com.acewill.slefpos.bean.orderbean.NewOrderReq;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.paybean.AliPayResult;
import com.acewill.slefpos.bean.paybean.AliPayResult2;
import com.acewill.slefpos.bean.paybean.AliPayResult3;
import com.acewill.slefpos.bean.paybean.BaseAliPayResult;
import com.acewill.slefpos.bean.paybean.BaseMeiTuanPayResult;
import com.acewill.slefpos.bean.paybean.BaseWechatPayResult;
import com.acewill.slefpos.bean.paybean.MeiTuanPayResult;
import com.acewill.slefpos.bean.paybean.MeiTuanSaomaRes;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.paybean.PayModel;
import com.acewill.slefpos.bean.paybean.PayResultMsg;
import com.acewill.slefpos.bean.paybean.PayResultType;
import com.acewill.slefpos.bean.paybean.SelfPosPayResult;
import com.acewill.slefpos.bean.paybean.SmarantRefundReq;
import com.acewill.slefpos.bean.paybean.SyncPayResult;
import com.acewill.slefpos.bean.paybean.SyncRefundRes;
import com.acewill.slefpos.bean.paybean.SyncRevokeRes;
import com.acewill.slefpos.bean.paybean.WechatPayResult;
import com.acewill.slefpos.bean.paybean.WechatPayResult2;
import com.acewill.slefpos.bean.paybean.WechatPayResult3;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncBalancePayRes;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelPointRuleRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncPayResultType;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncPointPayRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncQureyPayResultRes;
import com.acewill.slefpos.bean.systembean.ErrorType;
import com.acewill.slefpos.bean.wshbean.CXJWshCreateDeal;
import com.acewill.slefpos.bean.wshbean.CommitWshDealRes;
import com.acewill.slefpos.bean.wshbean.CreateDealRes;
import com.acewill.slefpos.bean.wshbean.WshCreateDeal;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.kds.kdsbean.KDSRes;
import com.acewill.slefpos.kds.kdsbean.KdsOrderBean;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.GenericsCallback;
import com.acewill.slefpos.okhttp.callback.JsonGenericsSerializator;
import com.acewill.slefpos.orderui.main.contract.OrderContract;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.utils.httputils.GsonUtils;
import com.google.gson.Gson;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Author：Anch
 * Date：2018/5/4 10:08
 * Desc：
 */
public class OrderPresenter extends OrderContract.Presenter {
	private static final String TAG = "OrderPresenter";


	/**
	 * @param payType  0 表示刷卡支付 1表示扫码支付
	 * @param payModel
	 */
	@Override
	public void aliPay(final int payType, final PayModel payModel) {
		if (payType == 0) {
			mRxManage
					.add(mModel.aliShuaKa(payType, payModel)
							.subscribe(new RxSubscriber<BaseAliPayResult>(BaseApplication
									.getContext(), false) {
								@Override
								protected void _onNext(BaseAliPayResult result) {
									try {
										AliPayResult bean1 = GsonUtils.getSingleBean(result
												.getContent(), AliPayResult.class);
										AliPayResult.AlipayTradePayResponseBean bean = bean1
												.getAlipay_trade_pay_response();
										if (bean != null) {
											if (bean.getCode().equals("10000") && "Success"
													.equals(bean.getMsg())) {
												mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
											} else if (bean.getCode()
													.equals("40004") && "ACQ.PAYMENT_AUTH_CODE_INVALID"
													.equals(bean.getSub_code())) {
												mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
														.getSub_msg()));
											} else if (bean.getCode().equals("10003")) {
												mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.USER_PAYING, PayResultMsg.USER_PAYING));
											} else {
												mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW));
											}
										} else {
											//开启定时器查询结果
											mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW));
										}
									} catch (Exception e) {
										mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL));
										e.printStackTrace();
									}
								}

								@Override
								protected void _onError(String message) {
									mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, "服务器异常"));
								}

								@Override
								protected void _onTimeOut() {
									mView.returnAliShuaKaResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
								}
							}));
		} else if (payType == 1) {
			LoadingDialog.setLoadingText("正在生成支付二维码...");
			mRxManage
					.add(mModel.aliSaoMa(payType, payModel)
							.subscribe(new RxSubscriber<BaseAliPayResult>(BaseApplication
									.getContext(), false) {
								@Override
								protected void _onNext(BaseAliPayResult result) {
									try {
										AliPayResult2 bean1 = GsonUtils.getSingleBean(result
												.getContent(), AliPayResult2.class);
										AliPayResult2.AlipayTradePrecreateResponseBean bean = bean1
												.getAlipay_trade_precreate_response();
										if (bean != null) {
											if (bean.getCode().equals("10000")) {
												String qr_code = bean.getQr_code();
												mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, qr_code));
											} else {
												mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL));
											}
										} else {
											mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL));
										}
									} catch (Exception e) {
										mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
										e.printStackTrace();
									}
								}

								@Override
								protected void _onError(String message) {
									mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
								}

								@Override
								protected void _onTimeOut() {
									mView.returnALiSaoMaResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
								}
							}));
		} else {
			ToastUitl.showLong(BaseApplication.getContext(), "不支持的支付类型");
		}
	}

	@Override
	public void wechatPay(int payType, PayModel payModel) {
		Log.e(TAG, "微信支付参数>" + new Gson().toJson(payModel));
		FileLog.log("微信支付参数>" + new Gson().toJson(payModel));
		if (payType == 0) {
			mRxManage.add(mModel.wechatShuaKa(payType, payModel)
					.subscribe(new RxSubscriber<BaseWechatPayResult>(mContext, false) {
						@Override
						protected void _onNext(BaseWechatPayResult result) {
							if (result.getResult() == 0) {
								String content = result.getContent();
								try {
									WechatPayResult bean = GsonUtils
											.getSingleBean(content, WechatPayResult.class);
									if ("SUCCESS".equals(bean.getResult_code())) {
										mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
									} else if ("FAIL".equals(bean.getResult_code()) && "USERPAYING"
											.equals(bean.getErr_code())) {
										mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.USER_PAYING, PayResultMsg.USER_PAYING, bean
												.getErr_code_des()));
									} else if ("FAIL".equals(bean.getResult_code())) {
										mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
												.getErr_code_des()));
									} else {
										mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW));
									}
								} catch (Exception e) {
									mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
									e.printStackTrace();
								}
							}
						}

						@Override
						protected void _onError(String message) {
							//服务器异常
							mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
						}

						@Override
						protected void _onTimeOut() {
							mView.returnWechatShuaKaResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
						}
					}));
		} else if (payType == 1) {
			LoadingDialog.setLoadingText("正在生成支付二维码...");
			mRxManage.add(mModel.wechatSaoMa(payType, payModel)
					.subscribe(new RxSubscriber<BaseWechatPayResult>(mContext, false) {
						@Override
						protected void _onNext(BaseWechatPayResult result) {
							if (result.getResult() == 0) {
								try {
									WechatPayResult2 bean = GsonUtils
											.getSingleBean(result
													.getContent(), WechatPayResult2.class);
									if ("SUCCESS".equals(bean.getResult_code())) {
										String qr_code = bean.getCode_url();
										mView.returnWechatSaoMaResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, qr_code));
									} else {
										mView.returnWechatSaoMaResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
												.getReturn_msg()));
									}
								} catch (Exception e) {
									mView.returnWechatSaoMaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
									e.printStackTrace();
								}
							}
						}

						@Override
						protected void _onError(String message) {
							mView.returnWechatSaoMaResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
						}

						@Override
						protected void _onTimeOut() {
							mView.returnWechatSaoMaResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
						}
					}));
		} else {
			ToastUitl.showLong(BaseApplication.getContext(), "不支持的支付类型");
		}
	}

	@Override
	public void queryAliPayResult(PayModel payModel) {
		FileLog.log("查询支付宝支付结果参数>" + new Gson().toJson(payModel));
		Log.e(TAG, "查询支付宝支付结果参数>" + new Gson().toJson(payModel));
		mRxManage.add(mModel.queryAliPayResult(payModel)
				.subscribe(new RxSubscriber<BaseAliPayResult>(BaseApplication.getContext(), false) {
					@Override
					protected void _onNext(BaseAliPayResult result) {
						try {
							AliPayResult3 bean1 = GsonUtils.getSingleBean(result
									.getContent(), AliPayResult3.class);
							AliPayResult3.AlipayTradeQueryResponseBean bean = bean1
									.getAlipay_trade_query_response();
							if (bean != null) {
								if ("WAIT_BUYER_PAY"
										.equals(bean.getTrade_status())) {
									mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.USER_PAYING, PayResultMsg.USER_PAYING));
								} else if (bean.getCode().equals("10000") && "TRADE_SUCCESS"
										.equals(bean.getTrade_status())) {
									mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
								} else if ("10000".equals(bean.getCode()) && "TRADE_CLOSED"
										.equals(bean.getTrade_status())) {
									mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
											.getTrade_status()));
								} else if ("40004".equals(bean.getCode())) {
									mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS));
								} else {
									mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "未知原因支付失败1000"));
								}
							} else {
								mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, "未知原因支付失败2000"));
							}
						} catch (Exception e) {
							mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
							e.printStackTrace();
						}
					}

					@Override
					protected void _onError(String message) {
						mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnQueryAliPayResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
					}
				}));
	}

	@Override
	public void queryWechatPayResult(PayModel model) {
		FileLog.log("查询微信支付结果参数>" + new Gson().toJson(model));
		Log.e(TAG, "查询微信支付结果参数>" + new Gson().toJson(model));
		mRxManage.add(mModel.queryWechatPayResult(model)
				.subscribe(new RxSubscriber<BaseWechatPayResult>(mContext, false) {
					@Override
					protected void _onNext(BaseWechatPayResult result) {
						if (result.getResult() == 0) {
							try {
								WechatPayResult3 bean = GsonUtils
										.getSingleBean(result.getContent(), WechatPayResult3.class);
								if ("SUCCESS".equals(bean.getResult_code())) {
									if ("NOTPAY".equals(bean.getTrade_state())) {
										mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS));
									} else if ("SUCCESS".equals(bean.getTrade_state())) {
										mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
									} else if ("USERPAYING".equals(bean.getTrade_state())) {
										mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.USER_PAYING, PayResultMsg.USER_PAYING));
									} else {
										mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW));
									}
								} else {
									mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
											.getReturn_msg()));
								}

							} catch (Exception e) {
								mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
								e.printStackTrace();
							}
						}
					}

					@Override
					protected void _onError(String message) {
						mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_ERROR, PayResultMsg.PAY_ERROR));
					}

					@Override
					protected void _onTimeOut() {
						mView.returnQueryWechatPayResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
					}
				}));
	}

	@Override
	public void cxjNewOrder(String contents) {
		Log.e(TAG, "餐行健下单参数2>" + contents);
		FileLog.log("餐行健下单参数2>" + contents);
		mRxManage.add(mModel.cxjNewOrder(contents)
				.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
					@Override
					protected void _onNext(ResponseBody res) {
						mView.returnCXJNewOrderResult(res);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						mView.returnCXJNewOrderResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnCXJNewOrderResult(null);
					}
				}));
	}

	@Override
	public void closeNewOrder(long orderidentity) {
		mRxManage.add(mModel.closeNewOrder(orderidentity)
				.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
					@Override
					protected void _onNext(ResponseBody res) {
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
					}
				}));
	}

	@Override
	public void cxjPrecheck(String jsondata) {
		Log.e(TAG, "餐行健预结参数>>" + jsondata);
		mRxManage.add(mModel.cxjPrecheck(jsondata)
				.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
					@Override
					protected void _onNext(ResponseBody res) {
						mView.returnCXJPrecheckResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnCXJPrecheckResult(null);
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnCXJPrecheckResult(null);
					}
				}));
	}

	@Override
	public void writeTouchText(String contents) {
		Log.e(TAG, "餐行健预结账参数>" + contents);
		FileLog.log("餐行健预结账参数>" + contents);
		mRxManage.add(mModel.writeTouchText(contents)
				.subscribe(new RxSubscriber<CxjWriteTouchTextRes>(mContext, false) {
					@Override
					protected void _onNext(CxjWriteTouchTextRes res) {
						mView.returnWriteTouchTextResult(res, ErrorType.SUCCESS);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						mView.returnWriteTouchTextResult(null, ErrorType.SUCCESS);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnWriteTouchTextResult(null, ErrorType.SUCCESS);
					}
				}));
	}

	@Override
	public void checkOut(String jsondata) {
		Log.e(TAG, "餐行健下单参数>" + jsondata);
		FileLog.log("餐行健下单参数>" + jsondata);
		mRxManage.add(mModel.checkOut(jsondata)
				.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
					@Override
					protected void _onNext(ResponseBody res) {
						mView.returnCheckOutResult(res);
					}

					@Override
					protected void _onError(String message) {
						if (message.contains("JsonSyntaxException")) {

						}
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						ToastUitl.showLong(mContext, "请求超时");
					}
				}));
	}

	@Override
	public void queryCXJPayResult(int oid, String payType, String total, long orderidentify) {
		Log.e(TAG, "oid>" + oid + ",payType>" + payType + ",total>" + total + ",orderidentify>" + orderidentify);
		mRxManage.add(mModel.queryCXJPayResult(oid, payType, total, orderidentify)
				.subscribe(new RxSubscriber<ResponseBody>(mContext, false) {
					@Override
					protected void _onNext(ResponseBody res) {
						mView.returnQueryCXJPayResult(res);
					}

					@Override
					protected void _onError(String message) {
						if (message.contains("JsonSyntaxException")) {

						}
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						ToastUitl.showLong(mContext, "请求超时");
					}
				}));
	}

	@Override
	public void goLogin() {
		mRxManage.add(mModel.cxjUserLogin("1", "acewill")
				.subscribe(new RxSubscriber<CxjLoginModel>(mContext, false) {
					@Override
					protected void _onNext(CxjLoginModel model) {
						mView.returnCXJLoginResult(model);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						mView.returnCXJLoginResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnCXJLoginResult(null);
					}
				}));
	}


	@Override
	public void meituanPay(int payType, PayModel model) {
		if (payType == 0) {
			mRxManage.add(mModel.meituanMicroPay(model)
					.subscribe(new RxSubscriber<BaseMeiTuanPayResult>(mContext, false) {
						@Override
						protected void _onNext(BaseMeiTuanPayResult result) {
							if (result.getResult() != 0) {
								mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW
										, PayResultMsg.PAY_UNKNOW, null));
							} else {
								try {
									MeiTuanPayResult bean = GsonUtils
											.getSingleBean(result
													.getData(), MeiTuanPayResult.class);
									if (bean.getStatus().equals("SUCCESS") && bean.getTradeState()
											.equals("SUCCESS")) {
										mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS, null));
									} else if (bean.getStatus().equals("SUCCESS") && bean
											.getTradeState()
											.equals("USER_PAYING")) {
										mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, null));
									} else if (bean.getStatus().equals("FAIL") && bean.getErrCode()
											.equals("TRADE_PAY_UNKOWN_ERROR")) {
										mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, null));
									} else if (bean.getStatus().equals("FAIL")) {
										mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
												.getErrMsg()));
									} else {
										mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + bean
												.getErrMsg()));
									}
								} catch (Exception e) {
									mView.returnMeiTuanMicroPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + e
											.getMessage()));
									e.printStackTrace();
								}
							}
						}

						@Override
						protected void _onError(String message) {
							ToastUitl.showLong(mContext, message);
						}

						@Override
						protected void _onTimeOut() {

						}
					}));
		} else {
			//扫码支付
			Observable<BaseMeiTuanPayResult> observable = mModel.meituanSaomaPay(model);
			mRxManage.add(observable
					.subscribe(new RxSubscriber<BaseMeiTuanPayResult>(mContext, false) {
						@Override
						protected void _onNext(BaseMeiTuanPayResult bean) {
							if (bean.getResult() != 0) {
								mView.returnMeiTuanSaomaPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + bean
										.getErrmsg()));
							} else {
								try {
									MeiTuanSaomaRes saomaRes = GsonUtils
											.getSingleBean(bean.getData(), MeiTuanSaomaRes.class);
									if ("SUCCESS".equals(saomaRes.getStatus())) {
										mView.returnMeiTuanSaomaPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, saomaRes
												.getQrCode()));
									} else {
										mView.returnMeiTuanSaomaPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + saomaRes
												.getStatus()));
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}

						@Override
						protected void _onError(String message) {
							mView.returnMeiTuanSaomaPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + message));
						}

						@Override
						protected void _onTimeOut() {
							mView.returnMeiTuanSaomaPayResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT, null));
						}
					}));
		}
	}

	@Override
	public void queryMeiTuanPayResult(PayModel model) {
		FileLog.log("查询美团支付结果参数>" + new Gson().toJson(model));
		Log.e(TAG, "查询美团支付结果参数>" + new Gson().toJson(model));
		mRxManage.add(mModel.queryMeiTuanPayResult(model)
				.subscribe(new RxSubscriber<BaseMeiTuanPayResult>(mContext, false) {
					@Override
					protected void _onNext(BaseMeiTuanPayResult result) {
						if (result.getResult() != 0) {
							mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW
									, PayResultMsg.PAY_UNKNOW, null));
						} else {
							try {
								MeiTuanPayResult bean = GsonUtils
										.getSingleBean(result.getData(), MeiTuanPayResult.class);
								if (bean.getStatus().equals("SUCCESS") && bean.getTradeState()
										.equals("SUCCESS")) {
									mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS, null));
								} else if (bean.getStatus().equals("SUCCESS") && bean
										.getTradeState()
										.equals("USER_PAYING")) {
									mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, null));
								} else if (bean.getStatus().equals("FAIL") && bean.getErrCode()
										.equals("TRADE_PAY_UNKOWN_ERROR")) {
									mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, null));
								} else if (bean.getStatus().equals("FAIL")) {
									mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, bean
											.getErrMsg()));
								} else {
									mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + bean
											.getErrMsg()));
								}
							} catch (Exception e) {
								mView.returnQueryMeiTuanPayResult(new SelfPosPayResult(PayResultType.PAY_UNKNOW, PayResultMsg.PAY_UNKNOW, "支付未知异常:" + e
										.getMessage()));
								e.printStackTrace();
							}
						}
					}

					@Override
					protected void _onError(String message) {
						LogUtils.logd("jl", message);
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}

	@Override
	public void getNewOrderId() {
		Order.getInstance().setNewOrderTime(Order.getInstance().getNewOrderTime() + 1);
		mRxManage.add(mModel.getNewOrderId()
				.subscribe(new RxSubscriber<NewOrderIdRes>(mContext, false) {
					@Override
					protected void _onNext(NewOrderIdRes res) {
						if (res.getResult() == 0) {
							mView.returnNewOrderIdResutl(res.getContent());
						}
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnNewOrderIdResutl(-1);
					}
				}));
	}

	@Override
	public void pushOrder(final NewOrderReq req) {
		FileLog.log("智慧快餐下单参数>" + req);
		Log.e(TAG, new Gson().toJson(req));
		mRxManage.add(mModel.pushOrder(req)
				.subscribe(new RxSubscriber<NewOrderRes>(mContext, false) {
					@Override
					protected void _onNext(NewOrderRes res) {
						mRxManage.remove(this);
						if (res.getResult() == 0) {
							mView.returnPushOrderResult(res);
						} else {
							mView.returnPushOrderResult(null);
						}
					}

					@Override
					protected void _onError(String message) {
						mView.returnPushOrderResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnPushOrderResult(null);
					}
				}));
	}

	@Override
	public void notifyKDS(KdsOrderBean kdsOrderBean) {
		mRxManage.add(mModel.notityKDS(kdsOrderBean)
				.subscribe(new RxSubscriber<KDSRes>(mContext, false) {
					@Override
					protected void _onNext(KDSRes respose) {
						mView.returnNotifyKDSResult(respose.isSuccess());

					}

					@Override
					protected void _onError(String message) {
						mView.returnNotifyKDSResult(false);

					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}

	@Override
	public void connectKDS() {
		mRxManage.add(mModel.connectKds().subscribe(new RxSubscriber<KDSRes>(mContext, false) {
			@Override
			protected void _onNext(KDSRes res) {
				mView.kdsConnectOk(res.isSuccess());
			}

			@Override
			protected void _onError(String message) {
				mView.kdsConnectOk(false);
			}

			@Override
			protected void _onTimeOut() {
				mView.kdsConnectOk(false);
			}
		}));
	}

	@Override
	public void checkDishCounts(String dishStr) {
		mRxManage.add(mModel.checkDishCounts(dishStr)
				.subscribe(new RxSubscriber<CheckCountResp>(mContext, false) {
					@Override
					protected void _onNext(CheckCountResp res) {
						//				ToastUitl.showLong(mContext, "");
						mView.returnCheckDishCountResult(res);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}

	@Override
	public void syncPay(int payMethod, String data) {
		Log.e(TAG, "同步时支付参数>" + data);
		FileLog.log("同步时支付参数>" + data);

		mRxManage.add(mModel.syncPay(payMethod, data)
				.subscribe(new RxSubscriber<SyncPayResult>(mContext, false) {
					@Override
					protected void _onNext(SyncPayResult result) {
						if (result.getResponse().getResult().getTradeStatus().equals("2")) {
							Log.e(TAG, "syncPay1");
							FileLog.log("syncPay1");
							SelfPosPayResult result1 = new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, result
									.getResponse().getResult().getFailMsg());
							result1.setSyncPayResult(result);
							mView.returnSyncPayResult(result1);
						} else if (result.getResponse().getResult().getTradeStatus().equals("3")) {
							Log.e(TAG, "syncPay2");
							FileLog.log("syncPay2");
							SelfPosPayResult result1 = new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS,
									result.getResponse().getResult().getBizNo());
							result1.setSyncPayResult(result);
							mView.returnSyncPayResult(result1);
						} else if (result.getResponse().getResult().getTradeStatus().equals("1")) {
							Log.e(TAG, "syncPay3");
							FileLog.log("syncPay3");
							SelfPosPayResult result1 = new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS,
									result.getResponse().getResult().getBizNo());
							result1.setSyncPayResult(result);
							mView.returnSyncPayResult(result1);
						} else {
							Log.e(TAG, "syncPay4");
							FileLog.log("syncPay4");
							mView.returnSyncPayResult(null);
							ToastUitl.showLong(mContext, result.getResponse().getResult()
									.getFailMsg());
						}

					}

					@Override
					protected void _onError(String message) {
						Log.e(TAG, "syncPay5");
						FileLog.log("syncPay5");
						mView.returnSyncPayResult(null);
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						Log.e(TAG, "syncPay6");
						FileLog.log("syncPay6");
						mView.returnSyncPayResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
					}
				}));
	}

	@Override
	public void syncAccept(int timestamp, String sign, String data) {
		Log.e(TAG, "同步时下单参数>" + data);
		FileLog.log("同步时下单参数>" + data);
		//		Order.getInstance().saveAcceptData(mContext);
		mRxManage.add(mModel.syncAccept(timestamp, sign, data)
				.subscribe(new RxSubscriber<SyncAcceptRes>(mContext, false) {
					@Override
					protected void _onNext(SyncAcceptRes res) {
						FileLog.log("syncAccept_onNext!");
						mView.returnSyncAccept(res);
					}

					@Override
					protected void _onError(String message) {
						FileLog.log("syncAccept_onError1!");
						mView.returnSyncAccept(null);
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onError(Throwable exception, String message) {
						FileLog.log("syncAccept_onError2!");
						mView.returnSyncAccept(new SyncAcceptRes(502));
					}

					@Override
					protected void _onTimeOut() {
						FileLog.log("syncAccept_onTimeOut!");
						mView.returnSyncAccept(new SyncAcceptRes(502));
					}
				}));
	}

	@Override
	public void syncQueryPayResult(int payMethod, String data) {
		Log.e(TAG, "同步时查询支付结果参数>" + data);
		FileLog.log("同步时查询支付结果参数>" + data);
		mRxManage.add(mModel.querySyncPayResult(payMethod, data)
				.subscribe(new RxSubscriber<SyncQureyPayResultRes>(mContext, false) {
					@Override
					protected void _onNext(SyncQureyPayResultRes res) {
						if (SyncPayResultType.CREATE.equals(res.getResponse().getResult().getTrade()
								.getTradeStatus()) || SyncPayResultType.UNKNOW
								.equals(res.getResponse().getResult().getTrade()
										.getTradeStatus())) {
							//							mView.returnQuerySyncPayResult(trueres);
							Log.e(TAG, "syncQueryPayResult1");
							FileLog.log("syncQueryPayResult1");
							SelfPosPayResult result = new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS);
							result.setSyncQureyPayResult(res);
							mView.returnQuerySyncPayResult(result);
						} else if (SyncPayResultType.SUCCESS
								.equals(res.getResponse().getResult().getTrade()
										.getTradeStatus())) {
							Log.e(TAG, "syncQueryPayResult2");
							FileLog.log("syncQueryPayResult2");
							SelfPosPayResult result = new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS);
							result.setSyncQureyPayResult(res);
							mView.returnQuerySyncPayResult(result);
						} else if (SyncPayResultType.FAILED
								.equals(res.getResponse().getResult().getTrade()
										.getTradeStatus()) ||
								SyncPayResultType.FINISHED
										.equals(res.getResponse().getResult().getTrade()
												.getTradeStatus()) ||
								SyncPayResultType.CLOSE
										.equals(res.getResponse().getResult().getTrade()
												.getTradeStatus()) ||
								SyncPayResultType.REFUND
										.equals(res.getResponse().getResult().getTrade()
												.getTradeStatus())) {
							Log.e(TAG, "syncQueryPayResult3");
							FileLog.log("syncQueryPayResult3");
							SelfPosPayResult result = new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL);
							result.setSyncQureyPayResult(res);
							mView.returnQuerySyncPayResult(result);
						} else {
							Log.e(TAG, "syncQueryPayResult4");
							FileLog.log("syncQueryPayResult4");
							SelfPosPayResult result = new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL);
							result.setSyncQureyPayResult(res);
							mView.returnQuerySyncPayResult(result);
						}
					}

					@Override
					protected void _onError(String message) {
						FileLog.log("syncQueryPayResult5");
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						FileLog.log("syncQueryPayResult6");
						mView.returnQuerySyncPayResult(new SelfPosPayResult(PayResultType.PAY_TIME_OUT, PayResultMsg.PAY_TIME_OUT));
					}
				}));
	}

	@Override
	public void queryOrderById(int id) {
		mRxManage
				.add(mModel.queryOrderById(id)
						.subscribe(new RxSubscriber<NewOrderRes>(mContext, false) {
							@Override
							protected void _onNext(NewOrderRes res) {
								mView.returnQueryOrderResult(res);
							}

							@Override
							protected void _onError(String message) {
								mView.returnQueryOrderResult(null);
							}

							@Override
							protected void _onTimeOut() {
								mView.returnQueryOrderResult(null);
							}
						}));
	}

	/**
	 * 智慧快餐的退款
	 *
	 * @param refundReq
	 */
	@Override
	public void backOut(SmarantRefundReq refundReq) {
		FileLog.log("智慧快餐退款参数>" + new Gson().toJson(refundReq));
		mRxManage.add(mModel.backOut(refundReq)
				.subscribe(new RxSubscriber<SyncQureyPayResultRes>(mContext, false) {
					@Override
					protected void _onNext(SyncQureyPayResultRes res) {
						mView.returnBackOutResult(true, "已将支付金额原路退款到您的账户，请注意查收!");
					}

					@Override
					protected void _onError(String message) {
						mView.returnBackOutResult(false, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnBackOutResult(false, "连接服务器异常!");
					}
				}));
	}

	@Deprecated
	@Override
	public void wxPay(int type, PayModel model) {
		PayReqModel payReqModel = new PayReqModel();
		payReqModel.payType = PayMethod.WECHAT;
		payReqModel.totalAmount = model.getTotalAmount();
		payReqModel.wxGoodsDetail = model.getWxGoodsDetail();
		payReqModel.isDebug = false;
		payReqModel.authCode = model.getAuthCode();
		payReqModel.orderNo = model.getOutTradeNo();


		if (type == 0) {
			final ShuaKaTask myMicropayTask = new ShuaKaTask();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						myMicropayTask.get(60000, TimeUnit.MILLISECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (TimeoutException e) {
						e.printStackTrace();
					}
				}
			}).start();
			myMicropayTask
					.execute(payReqModel);//将该东西进行各种各样的操作 ，然后再最后的doInBackGround里面去得到结果
		} else {
			LoadingDialog.setLoadingText("正在生成支付二维码...");
			SaoMaTask myEpayTask = new SaoMaTask();
			myEpayTask.execute(payReqModel);
		}
	}

	@Override
	public void pushOrderToJYJ(NewOrderReq req) {
		OkHttpUtils.post()
				.url(StoreConfigure.getJyjAddress() + "/api/orders")
				.addParams("appId", BaseConfigure.getAppid())
				.addParams("brandId", String.valueOf(BaseConfigure.getBrandid()))
				.addParams("storeId", String.valueOf(BaseConfigure.getStoreid()))
				.addParams("order", new Gson().toJson(req)).build()
				.connTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.writeTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS).
				readTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.execute(new GenericsCallback<NewOrderRes>(new JsonGenericsSerializator()) {
					@Override
					public void onError(Call call, Exception e, int id) {
						mView.returnPushOrderToJYJ(null);
					}

					@Override
					public void onResponse(NewOrderRes response, int id) {
						mView.returnPushOrderToJYJ(response);
					}
				});
	}

	@Deprecated
	@Override
	public void syncBalancePay(String syncBalancePay) {
		Log.e(TAG, "同步时使用储值的参数>" + syncBalancePay);
		FileLog.log("同步时使用储值的参数>" + syncBalancePay);
		mRxManage.add(mModel.syncBalancePay(syncBalancePay)
				.subscribe(new RxSubscriber<SyncPayResult>(mContext, false) {
					@Override
					protected void _onNext(SyncPayResult res) {
						mView.returnSyncPayResult(null);
					}

					@Override
					protected void _onError(String message) {
						mView.returnSyncPayResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnSyncPayResult(null);
					}
				}));
	}

	@Override
	public void syncPointPay(String syncPointPay) {
		Log.e(TAG, "同步时使用积分的参数>" + syncPointPay);
		FileLog.log("同步时使用积分的参数>" + syncPointPay);
		mRxManage.add(mModel.syncPointPay(syncPointPay)
				.subscribe(new RxSubscriber<SyncPointPayRes>(mContext, false) {
					@Override
					protected void _onNext(SyncPointPayRes res) {
						mView.returnSyncPointPayResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnSyncPointPayResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnSyncPointPayResult(null);
					}
				}));
	}


	private int total;

	@Deprecated
	@Override
	public void syncMemberPay(String syncBalancePay, String syncPointPay, String syncSingleCouponPay) {
		Observable<SyncPayResult> syncBalancePayResObservable = mModel
				.syncBalancePay(syncBalancePay);
		Observable<SyncPointPayRes> syncPointPayResObservable = mModel
				.syncPointPay(syncPointPay);
		//		Observable<SyncSingleUseCouponRes> syncSingleUseCouponResObservable = mModel
		//				.syncSingleCouponPay(syncPointPay);

		mRxManage.add(Observable
				.merge(syncBalancePayResObservable, syncPointPayResObservable)
				.subscribeOn(Schedulers.io()).subscribe(new RxSubscriber<Object>(mContext, false) {
					@Override
					protected void _onNext(Object o) {
						total++;
						if (o instanceof SyncBalancePayRes) {

						}
						if (o instanceof SyncPointPayRes) {

						}
						//						if (o instanceof SyncSingleUseCouponRes) {
						//
						//						}
						if (total == 2) {
							mView.returnSyncMemberPayResult();
							total = 0;
						}
					}

					@Override
					protected void _onError(String message) {

					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}


	@Override
	public void syncSingleCouponPay(String syncSingleCouponPay) {
		Log.e(TAG, "syncSingleCouponPay>" + syncSingleCouponPay);
		FileLog.log("syncSingleCouponPay>" + syncSingleCouponPay);
		mRxManage.add(mModel.syncSingleCouponPay(syncSingleCouponPay)
				.subscribe(new RxSubscriber<SyncSingleUseCouponRes>(mContext, false) {
					@Override
					protected void _onNext(SyncSingleUseCouponRes res) {
						mView.returnSyncSingleCouponPayResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnSyncSingleCouponPayResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnSyncSingleCouponPayResult(null);
					}
				}));
	}

	@Override
	public void wshCreateDeal(WshCreateDeal.Request request) {
		Log.e(TAG, "微生活会员交易预览的结果>" + new Gson().toJson(request));
		mRxManage.add(mModel.wshCreateDeal(request)
				.subscribe(new RxSubscriber<CreateDealRes>(mContext, false) {
					@Override
					protected void _onNext(CreateDealRes res) {
						mView.returnWshCreateDealResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnWshCreateDealResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnWshCreateDealResult(null);
					}
				}));
	}

	@Override
	public void wshYjset(CXJWshCreateDeal.Request request) {
		mRxManage.add(mModel.wshYjset(request)
				.subscribe(new RxSubscriber<CxjWshYuJieRes>(mContext, false) {
					@Override
					protected void _onNext(CxjWshYuJieRes res) {
						mView.returnWshYjset(res);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						mView.returnWshYjset(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnWshYjset(null);
					}
				}));
	}

	@Override
	public void commitWshDeal(String bizId, String verifySms) {
		mRxManage.add(mModel.commitWshDeal(bizId, verifySms)
				.subscribe(new RxSubscriber<CommitWshDealRes>(mContext, false) {
					@Override
					protected void _onNext(CommitWshDealRes res) {
						mView.returnWshCommitDealResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnWshCommitDealResult(null);
					}

					@Override
					protected void _onTimeOut() {
						//需要查询
						mView.returnWshCommitDealResult(null);
					}
				}));
	}

	@Override
	public void syncRefund(int paymethod, String data) {
		Log.e(TAG, "同步时退款参数>" + data);
		FileLog.log("同步时退款参数>" + data);
		if (paymethod == PayMethod.ALI || paymethod == PayMethod.WECHAT) {
			mRxManage.add(mModel.syncOnlinePayRefund(paymethod, data)
					.subscribe(new RxSubscriber<SyncRefundRes>(mContext, false) {
						@Override
						protected void _onNext(SyncRefundRes req) {
							Log.e(TAG, "syncRevoke1");
							FileLog.log("syncRevoke1");
						}

						@Override
						protected void _onError(String message) {
							Log.e(TAG, "syncRevoke2");
							FileLog.log("syncRevoke2");
						}

						@Override
						protected void _onTimeOut() {
							Log.e(TAG, "syncRevoke3");
							FileLog.log("syncRevoke3");
						}
					}));
		} else {
			mRxManage.add(mModel.syncBalanceRefund(data)
					.subscribe(new RxSubscriber<SyncRefundRes>(mContext, false) {
						@Override
						protected void _onNext(SyncRefundRes req) {
							Log.e(TAG, "syncRevoke4");
							FileLog.log("syncRevoke4");
						}

						@Override
						protected void _onError(String message) {
							Log.e(TAG, "syncRevoke5");
							FileLog.log("syncRevoke5");
						}

						@Override
						protected void _onTimeOut() {
							Log.e(TAG, "syncRevoke6");
							FileLog.log("syncRevoke6");
						}
					}));
		}
	}

	@Override
	public void syncRevoke(int paymethod, String data) {
		Log.e(TAG, "同步时撤銷参数>" + data);
		FileLog.log("同步时撤銷参数>" + data);
		if (paymethod == PayMethod.ALI || paymethod == PayMethod.WECHAT) {
			mRxManage.add(mModel.syncOnlinePayRevoke(paymethod, data)
					.subscribe(new RxSubscriber<SyncRevokeRes>(mContext, false) {
						@Override
						protected void _onNext(SyncRevokeRes req) {
							Log.e(TAG, "syncRevoke1");
							FileLog.log("syncRevoke1");
						}

						@Override
						protected void _onError(String message) {
							Log.e(TAG, "syncRevoke2");
							FileLog.log("syncRevoke2");
						}

						@Override
						protected void _onTimeOut() {
							Log.e(TAG, "syncRevoke3");
							FileLog.log("syncRevoke3");
						}
					}));
		} else {
			mRxManage.add(mModel.syncBalanceRevoke(data)
					.subscribe(new RxSubscriber<SyncRevokeRes>(mContext, false) {
						@Override
						protected void _onNext(SyncRevokeRes req) {
							Log.e(TAG, "syncRevoke4");
							FileLog.log("syncRevoke4");
						}

						@Override
						protected void _onError(String message) {
							Log.e(TAG, "syncRevoke5");
							FileLog.log("syncRevoke5");
						}

						@Override
						protected void _onTimeOut() {
							Log.e(TAG, "syncRevoke6");
							FileLog.log("syncRevoke6");
						}
					}));
		}
	}

	@Override
	public void syncCancelUseCoupon(String data) {
		Log.e(TAG, "同步时取消使用代金券参数>" + data);
		FileLog.log("同步时取消使用代金券参数>" + data);
		mRxManage.add(mModel.syncCancelUseCoupon(data)
				.subscribe(new RxSubscriber<SyncCancelUseCouponRes>(mContext, false) {
					@Override
					protected void _onNext(SyncCancelUseCouponRes res) {
						Log.e(TAG, "syncCancelUseCoupon1");
						FileLog.log("syncCancelUseCoupon1");
					}

					@Override
					protected void _onError(String message) {
						Log.e(TAG, "syncCancelUseCoupon2");
						FileLog.log("syncCancelUseCoupon2");
					}

					@Override
					protected void _onTimeOut() {
						Log.e(TAG, "syncCancelUseCoupon3");
						FileLog.log("syncCancelUseCoupon3");
					}
				}));
	}

	@Override
	public void syncCancelPointRule(String data) {
		Log.e(TAG, "同步时取消使用积分参数>" + data);
		FileLog.log("同步时取消使用积分参数>" + data);
		mRxManage.add(mModel.syncCancelPointRule(data)
				.subscribe(new Subscriber<SyncCancelPointRuleRes>() {
					@Override
					public void onCompleted() {
						Log.e(TAG, "syncCancelPointRule1");
						FileLog.log("syncCancelPointRule1");
					}

					@Override
					public void onError(Throwable e) {
						Log.e(TAG, "syncCancelPointRule2");
						FileLog.log("syncCancelPointRule2");
					}

					@Override
					public void onNext(SyncCancelPointRuleRes res) {
						Log.e(TAG, "syncCancelPointRule3");
						FileLog.log("syncCancelPointRule3");
					}
				}));
	}

	@Override
	public void validationSetout(String meituanquanid) {
		mRxManage.add(mModel.validationSetout(meituanquanid)
				.subscribe(new RxSubscriber<ValidationSetoutResult>(mContext, false) {
					@Override
					protected void _onNext(ValidationSetoutResult result) {
						mView.returnMeituanValidateCodeResult(result);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						mView.returnMeituanValidateCodeResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnMeituanValidateCodeResult(null);
					}
				}));
	}

	@Override
	public void executeMeituanCode(String orderId, String couponCodes) {
		mRxManage.add(mModel.executeMeituanCode(orderId, couponCodes)
				.subscribe(new RxSubscriber<ExecuteMeituanCodeResult>(mContext, false) {
					@Override
					protected void _onNext(ExecuteMeituanCodeResult result) {
						mView.returnExecuteMeituanCodeResult(result);
					}

					@Override
					protected void _onError(String message) {
						mView.returnExecuteMeituanCodeResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnExecuteMeituanCodeResult(null);
					}
				}));
	}


	/**
	 * 刷卡支付
	 *
	 * @author Administrator
	 */
	class ShuaKaTask extends MicropayTask {

		@Override
		protected void onPostExecute(EPayResult result) {//得到支付的结果，然后对该结果进行处理


			if (result == null) {
				FileLog
						.log(Common.Log, OrderPresenter.class, "onPostExecute", "log", "result为空");
				PayStatus status = new PayStatus();
				status.setPayStatu("payFail");
				AppApplication.getRxManager().post(AppConstant.WXPAY, status);
			} else {
				FileLog
						.log(Common.Log, OrderPresenter.class, "onPostExecute", "log", result
								.toString());
				if (result.success) {
					ToastUitl.showLong(mContext, "支付成功");
				} else {
					PayStatus status = new PayStatus();
					status.setPayStatu("payFail");
					AppApplication.getRxManager().post(AppConstant.WXPAY, status);
				}
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}
	}


	/**
	 * 扫码支付
	 */
	class SaoMaTask extends EPayTask {
		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onPostExecute(EPayResult result) {
			if (result == null) {
				AppApplication.getRxManager()
						.post(AppConstant.WXPAY, new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL, PayResultMsg.FAIL_GENERATE_QR_CODE));
			} else {
				if (EPayResult.TRADE_SUCCESS
						.equalsIgnoreCase(result.trade_status)) {
					AppApplication.getRxManager()
							.post(AppConstant.WXPAY, new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
				} else if (result != null && result.success) {

					AppApplication.getRxManager()
							.post(AppConstant.WXPAY, new SelfPosPayResult(PayResultType.PAY_SUCCESS, PayResultMsg.PAY_SUCCESS));
				} else {
					AppApplication.getRxManager()
							.post(AppConstant.WXPAY, new SelfPosPayResult(PayResultType.PAY_FAIL, PayResultMsg.PAY_FAIL));
				}
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			if (!TextUtils.isEmpty(values[0])) {
				AppApplication.getRxManager()
						.post(AppConstant.WXPAY, new SelfPosPayResult(PayResultType.PAY_INPROGRESS, PayResultMsg.PAY_INPROGRESS, values[0]));
			}
		}

	}
}
