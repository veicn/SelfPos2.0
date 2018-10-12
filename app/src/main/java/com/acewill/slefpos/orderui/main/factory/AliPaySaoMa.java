//package com.acewill.slefpos.orderui.main.factory;
//
//import com.acewill.slefpos.api.Api;
//import com.acewill.slefpos.base.BaseApplication;
//import com.acewill.slefpos.bean.paybean.AliPayResult;
//import com.acewill.slefpos.bean.paybean.AliPayResult2;
//import com.acewill.slefpos.bean.paybean.PayModel;
//import com.acewill.slefpos.configure.BaseConfigure;
//import com.acewill.slefpos.configure.TerminalConfigure;
//import com.acewill.slefpos.emuee.HostType;
//import com.jaydenxiao.common.baserx.RxSchedulers;
//import com.jaydenxiao.common.baserx.RxSubscriber;
//import com.jaydenxiao.common.commonutils.ToastUitl;
//
//import rx.Observable;
//import rx.functions.Func1;
//
///**
// * Author：Anch
// * Date：2018/5/4 11:34
// * Desc：
// */
//public class AliPaySaoMa implements PayMethodInterface {
//	@Override
//	public Observable<AliPayResult> pay(PayModel payModel) {
//		return Api
//				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
//				.aliPaySaoMa(Api.getCacheControl(),
//						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
//								.getStoreid(), payModel.getOutTradeNo(),
//						payModel.getAuthCode(), payModel.getStore_name(), payModel
//								.getTotalAmount(), String.valueOf(1), TerminalConfigure
//								.getTerminalmac(), "")
//				.map(new Func1<AliPayResult2, AliPayResult2>() {
//					@Override
//					public AliPayResult2 call(AliPayResult2 result2) {
//						return result2;
//					}
//				}).compose(RxSchedulers.<AliPayResult2>io_main());
//		mRxManage
//				.add(observable.subscribe(new RxSubscriber<AliPayResult2>(BaseApplication
//						.getContext(), false) {
//					@Override
//					protected void _onNext(AliPayResult2 result) {
//						try {
//							AliPayResult2.AlipayTradePrecreateResponseBean bean = result
//									.getAlipay_trade_precreate_response();
//							if (bean != null) {
//								if (bean.getCode().equals("10000")) {
//									String qr_code = bean.getQr_code();
//									//									showPayDialog(qr_code);
//									//									handler.postDelayed(excuteAliQueryRunnable, 5000);
//								} else {
//									//									handler.sendEmptyMessage(Contants.ONlINE_PAYFAIL);
//								}
//							} else {
//								//								handler.sendEmptyMessage(Contants.ONlINE_PAYFAIL);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//
//					@Override
//					protected void _onError(String message) {
//						ToastUitl.showLong(BaseApplication.getContext(), message);
//					}
//				}));
//		return null;
//	}
//}
