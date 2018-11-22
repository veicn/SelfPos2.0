package com.acewill.slefpos.orderui.main.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjWriteTouchTextRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjWshYuJieRes;
import com.acewill.slefpos.bean.meituanbean.ExecuteMeituanCodeResult;
import com.acewill.slefpos.bean.meituanbean.ValidationSetoutResult;
import com.acewill.slefpos.bean.orderbean.CheckCountResp;
import com.acewill.slefpos.bean.orderbean.NewOrderIdRes;
import com.acewill.slefpos.bean.orderbean.NewOrderReq;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.paybean.BaseAliPayResult;
import com.acewill.slefpos.bean.paybean.BaseMeiTuanPayResult;
import com.acewill.slefpos.bean.paybean.BaseWechatPayResult;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.paybean.PayModel;
import com.acewill.slefpos.bean.paybean.SmarantRefundReq;
import com.acewill.slefpos.bean.paybean.SyncPayResult;
import com.acewill.slefpos.bean.paybean.SyncRefundRes;
import com.acewill.slefpos.bean.paybean.SyncRevokeRes;
import com.acewill.slefpos.bean.syncbean.SyncMemberPayRes;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelPointRuleRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncPointPayRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncQureyPayResultRes;
import com.acewill.slefpos.bean.wshbean.CXJWshCreateDeal;
import com.acewill.slefpos.bean.wshbean.CommitWshDealRes;
import com.acewill.slefpos.bean.wshbean.CreateDealRes;
import com.acewill.slefpos.bean.wshbean.WshCreateDeal;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.SyncConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.kds.kdsbean.KDSRes;
import com.acewill.slefpos.kds.kdsbean.KdsOrderBean;
import com.acewill.slefpos.orderui.main.contract.OrderContract;
import com.google.gson.Gson;
import com.jaydenxiao.common.baserx.RxSchedulers;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/5/4 10:08
 * Desc：
 */
public class OrderModel extends OrderContract.Model {
	@Override
	public Observable<BaseAliPayResult> aliShuaKa(int payType, PayModel payModel) {

		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.aliPayShuaKa(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(),
						payModel.getAuthCode(), payModel.getSubject(), payModel
								.getTotalAmount(), String.valueOf(1), payModel.getPaymentStr())
				.map(new Func1<BaseAliPayResult, BaseAliPayResult>() {
					@Override
					public BaseAliPayResult call(BaseAliPayResult result) {
						return result;
					}
				}).compose(RxSchedulers.<BaseAliPayResult>io_main());

	}

	@Override
	public Observable<BaseWechatPayResult> wechatShuaKa(int payType, PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.micropayToWepay(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(),
						Integer.parseInt(payModel.getTotalAmount()), payModel.getBody(), payModel
								.getAuthCode(), payModel.getPaymentStr(), payModel
								.getTimeStart(), payModel.getTimeExpire(),
						payModel.getDeviceInfo(), "192.168.1.101")
				.map(new Func1<BaseWechatPayResult, BaseWechatPayResult>() {
					@Override
					public BaseWechatPayResult call(BaseWechatPayResult result2) {
						return result2;
					}
				}).compose(RxSchedulers.<BaseWechatPayResult>io_main());
	}

	@Override
	public Observable<BaseAliPayResult> aliSaoMa(int payType, PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.aliPaySaoMa(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(),
						payModel.getAuthCode(), payModel.getSubject(), payModel
								.getTotalAmount(), String.valueOf(1), TerminalConfigure
								.getTerminalmac(), payModel.getPaymentStr())
				.map(new Func1<BaseAliPayResult, BaseAliPayResult>() {
					@Override
					public BaseAliPayResult call(BaseAliPayResult result2) {
						return result2;
					}
				}).compose(RxSchedulers.<BaseAliPayResult>io_main());
	}

	@Override
	public Observable<BaseWechatPayResult> wechatSaoMa(int payType, PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.unifiedOrderToWepay(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(),
						Integer.parseInt(payModel.getTotalAmount()), "192.168.1.101", payModel
								.getBody(), payModel.getPaymentStr())
				.map(new Func1<BaseWechatPayResult, BaseWechatPayResult>() {
					@Override
					public BaseWechatPayResult call(BaseWechatPayResult result2) {
						return result2;
					}
				}).compose(RxSchedulers.<BaseWechatPayResult>io_main());
	}

	@Override
	public Observable<BaseAliPayResult> queryAliPayResult(PayModel payModel) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.aliQueryResult(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(), payModel.getPaymentStr())
				.map(new Func1<BaseAliPayResult, BaseAliPayResult>() {
					@Override
					public BaseAliPayResult call(BaseAliPayResult result3) {
						return result3;
					}
				}).compose(RxSchedulers.<BaseAliPayResult>io_main());
	}

	@Override
	public Observable<BaseWechatPayResult> queryWechatPayResult(PayModel payModel) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.queryWechatPayResult(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), payModel.getOutTradeNo(), payModel.getPaymentStr())
				.map(new Func1<BaseWechatPayResult, BaseWechatPayResult>() {
					@Override
					public BaseWechatPayResult call(BaseWechatPayResult result3) {
						return result3;
					}
				}).compose(RxSchedulers.<BaseWechatPayResult>io_main());
	}

	@Override
	public Observable<SyncPayResult> syncPay(int payMethod, String data) {
		RequestBody
				body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		int hostType = 0;
		if (payMethod == PayMethod.WECHAT) {
			return doSyncPay1(HostType.IS_SYNC_DEBUG ? HostType.SYNC_WECHAT_PAY : HostType.SYNC_WECHAT_PAY_NORMAL, body);
		} else if (payMethod == PayMethod.ALI) {
			return doSyncPay1(HostType.IS_SYNC_DEBUG ? HostType.SYNC_ALI_PAY : HostType.SYNC_ALI_PAY_NORMAL, body);
		} else if (payMethod == PayMethod.SYNCBALANCE) {
			return doSyncPay2(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER_PAY : HostType.SYNC_MEMBER_PAY_NORMAL, body);
		}
		return null;
	}

	private Observable<SyncPayResult> doSyncPay2(int hostType, RequestBody body) {
		return Api.getDefault(hostType)
				.syncBalancePay(body).map(new Func1<SyncPayResult, SyncPayResult>() {
					@Override
					public SyncPayResult call(SyncPayResult result) {
						return result;
					}
				}).compose(RxSchedulers.<SyncPayResult>io_main());
	}

	private Observable<SyncPayResult> doSyncPay1(int hostType, RequestBody body) {
		return Api.getDefault(hostType)
				.syncPay(body).map(new Func1<SyncPayResult, SyncPayResult>() {
					@Override
					public SyncPayResult call(SyncPayResult result) {
						return result;
					}
				}).compose(RxSchedulers.<SyncPayResult>io_main());
	}

	@Override
	public Observable<BaseMeiTuanPayResult> meituanMicroPay(PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.meituanMicroPay(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getStoreid(), payModel
								.getOutTradeNo(), Integer
								.parseInt(payModel.getTotalAmount()), payModel
								.getSubject(), payModel.getBody(), payModel.getAuthCode(), payModel
								.getExpireMinutes(), payModel.getPayType())
				.map(new Func1<BaseMeiTuanPayResult, BaseMeiTuanPayResult>() {
					@Override
					public BaseMeiTuanPayResult call(BaseMeiTuanPayResult result2) {
						return result2;
					}
				}).compose(RxSchedulers.<BaseMeiTuanPayResult>io_main());
	}

	@Override
	public Observable<BaseMeiTuanPayResult> meituanSaomaPay(PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.createCodeUrl(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
						.getStoreid(), payModel.getOutTradeNo(), Integer
						.parseInt(payModel.getTotalAmount()), payModel.getBody(), payModel
						.getPayType(), payModel.getSubject())
				.map(new Func1<BaseMeiTuanPayResult, BaseMeiTuanPayResult>() {
					@Override
					public BaseMeiTuanPayResult call(BaseMeiTuanPayResult result) {
						return result;
					}
				}).compose(RxSchedulers.<BaseMeiTuanPayResult>io_main());
	}

	@Override
	public Observable<BaseMeiTuanPayResult> queryMeiTuanPayResult(PayModel payModel) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.queryMeiTuanPayResult(Api.getCacheControl(), BaseConfigure
						.getAppid(), BaseConfigure.getStoreid(), payModel.getOutTradeNo())
				.map(new Func1<BaseMeiTuanPayResult, BaseMeiTuanPayResult>() {
					@Override
					public BaseMeiTuanPayResult call(BaseMeiTuanPayResult result) {
						return result;
					}
				}).compose(RxSchedulers.<BaseMeiTuanPayResult>io_main());
	}

	@Override
	public Observable<NewOrderIdRes> getNewOrderId() {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.nextOrderId(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), BaseConfigure.getToken())
				.map(new Func1<NewOrderIdRes, NewOrderIdRes>() {
					@Override
					public NewOrderIdRes call(NewOrderIdRes res) {
						return res;
					}
				}).compose(RxSchedulers.<NewOrderIdRes>io_main());
	}

	@Override
	public Observable<NewOrderRes> pushOrder(NewOrderReq req) {

		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson()
						.toJson(req));
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.pushOrder2(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), body)
				.map(new Func1<NewOrderRes, NewOrderRes>() {
					@Override
					public NewOrderRes call(NewOrderRes res) {
						return res;
					}
				}).compose(RxSchedulers.<NewOrderRes>io_main());
	}

	@Override
	public Observable<NewOrderRes> pushOrderToJYJ(NewOrderReq req) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson()
						.toJson(req));
		return Api.getDefault(HostType.PUSH_ORDER_TO_JYJ)
				.pushOrder2(Api.getCacheControl(),
						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(), body)
				.map(new Func1<NewOrderRes, NewOrderRes>() {
					@Override
					public NewOrderRes call(NewOrderRes res) {
						return res;
					}
				}).compose(RxSchedulers.<NewOrderRes>io_main());
	}

	@Override
	public Observable<KDSRes> connectKds() {
		return Api.getDefault(HostType.KDS_HOST)
				.connectKds(Api.getCacheControl())
				.map(new Func1<KDSRes, KDSRes>() {
					@Override
					public KDSRes call(KDSRes res) {
						return res;
					}
				}).compose(RxSchedulers.<KDSRes>io_main());
	}

	@Override
	public Observable<KDSRes> notityKDS(KdsOrderBean req) {

		return Api.getDefault(HostType.KDS_HOST)
				.notiKDS(Api.getCacheControl(), new Gson().toJson(req))
				.map(new Func1<KDSRes, KDSRes>() {
					@Override
					public KDSRes call(KDSRes res) {
						return res;
					}
				}).compose(RxSchedulers.<KDSRes>io_main());
	}

	@Override
	public Observable<CheckCountResp> checkDishCounts(String dishStr) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.checkDishCounts(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), dishStr, BaseConfigure
						.getToken())
				.map(new Func1<CheckCountResp, CheckCountResp>() {
					@Override
					public CheckCountResp call(CheckCountResp res) {
						return res;
					}
				}).compose(RxSchedulers.<CheckCountResp>io_main());
	}

	@Override
	public Observable<SyncAcceptRes> syncAccept(int timestamp, String sign, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);

		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_NEWORDER : HostType.SYNC_NEWORDER_NORMAL)
				.syncAccept(timestamp, SyncConfig.XPARTNER, sign, body)
				.map(new Func1<SyncAcceptRes, SyncAcceptRes>() {
					@Override
					public SyncAcceptRes call(SyncAcceptRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncAcceptRes>io_main());
	}

	@Override
	public Observable<SyncQureyPayResultRes> querySyncPayResult(int payMethod, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		int hostType = 0;
		if (payMethod == PayMethod.WECHAT) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_WECHAT_PAY : HostType.SYNC_WECHAT_PAY_NORMAL;
		} else if (payMethod == PayMethod.ALI) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_ALI_PAY : HostType.SYNC_ALI_PAY_NORMAL;
		} else if (payMethod == PayMethod.SYNCBALANCE) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER_PAY : HostType.SYNC_MEMBER_PAY_NORMAL;
		}
		return Api.getDefault(hostType)
				.syncQueryPayResult(body)
				.map(new Func1<SyncQureyPayResultRes, SyncQureyPayResultRes>() {
					@Override
					public SyncQureyPayResultRes call(SyncQureyPayResultRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncQureyPayResultRes>io_main());
	}

	@Override
	public Observable<NewOrderRes> queryOrderById(int id) {

		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.queryOrderById(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), id)
				.map(new Func1<NewOrderRes, NewOrderRes>() {
					@Override
					public NewOrderRes call(NewOrderRes res) {
						return res;
					}
				}).compose(RxSchedulers.<NewOrderRes>io_main());
	}

	@Override
	public Observable<SyncQureyPayResultRes> backOut(SmarantRefundReq refundReq) {

		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.smarantBackOut(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), refundReq
						.getPaymentNo(), refundReq
						.getPaymentTypeId(), TerminalConfigure.getTname(), refundReq
						.getTotal_fee(), BaseConfigure.getToken())
				.map(new Func1<SyncQureyPayResultRes, SyncQureyPayResultRes>() {
					@Override
					public SyncQureyPayResultRes call(SyncQureyPayResultRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncQureyPayResultRes>io_main());
	}

	@Override
	public Observable<SyncPayResult> syncBalancePay(String syncBalancePay) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), syncBalancePay);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER_PAY : HostType.SYNC_MEMBER_PAY_NORMAL)
				.syncBalancePay(body)
				.map(new Func1<SyncPayResult, SyncPayResult>() {
					@Override
					public SyncPayResult call(SyncPayResult res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncPayResult>io_main());
	}

	@Override
	public Observable<SyncPointPayRes> syncPointPay(String syncPointPay) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), syncPointPay);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncPointPay(body)
				.map(new Func1<SyncPointPayRes, SyncPointPayRes>() {
					@Override
					public SyncPointPayRes call(SyncPointPayRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncPointPayRes>io_main());
	}

	@Override
	public Observable<SyncSingleUseCouponRes> syncSingleCouponPay(String syncSingleCouponPay) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType
						.parse("application/json; charset=utf-8"), syncSingleCouponPay);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncUseCouponSingle(body)
				.map(new Func1<SyncSingleUseCouponRes, SyncSingleUseCouponRes>() {
					@Override
					public SyncSingleUseCouponRes call(SyncSingleUseCouponRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncSingleUseCouponRes>io_main());
	}

	@Override
	public Observable<CreateDealRes> wshCreateDeal(WshCreateDeal.Request request) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.createDeal(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), new Gson()
						.toJson(request), BaseConfigure.getToken())
				.map(new Func1<CreateDealRes, CreateDealRes>() {
					@Override
					public CreateDealRes call(CreateDealRes res) {
						return res;
					}
				}).compose(RxSchedulers.<CreateDealRes>io_main());
	}

	@Override
	public Observable<CxjWshYuJieRes> wshYjset(CXJWshCreateDeal.Request request) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.wshYjset(request.getWid(), request.getOid(), request.getUid(), request
						.getCardid(), request.getType(), request.getActivityid(), request
						.getActivityname(), request.getCouponid(), request.getCno())
				.map(new Func1<CxjWshYuJieRes, CxjWshYuJieRes>() {
					@Override
					public CxjWshYuJieRes call(CxjWshYuJieRes res) {
						return res;
					}
				}).compose(RxSchedulers.<CxjWshYuJieRes>io_main());
	}

	@Override
	public Observable<CommitWshDealRes> commitWshDeal(String bizId, String verifySms) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.commitWshDeal(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), bizId, verifySms, BaseConfigure
						.getToken())
				.map(new Func1<CommitWshDealRes, CommitWshDealRes>() {
					@Override
					public CommitWshDealRes call(CommitWshDealRes res) {
						return res;
					}
				}).compose(RxSchedulers.<CommitWshDealRes>io_main());
	}

	@Override
	public Observable<SyncRefundRes> syncOnlinePayRefund(int payMethod, String revokeParam) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), revokeParam);
		int hostType = -1;
		if (payMethod == PayMethod.WECHAT) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_WECHAT_PAY : HostType.SYNC_WECHAT_PAY_NORMAL;
		} else if (payMethod == PayMethod.ALI) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_ALI_PAY : HostType.SYNC_ALI_PAY_NORMAL;
		}
		return Api
				.getDefault(hostType)
				.syncOnlineRefund(body)
				.map(new Func1<SyncRefundRes, SyncRefundRes>() {
					@Override
					public SyncRefundRes call(SyncRefundRes req) {
						return req;
					}
				}).compose(RxSchedulers.<SyncRefundRes>io_main());
	}

	@Override
	public Observable<SyncRevokeRes> syncOnlinePayRevoke(int payMethod, String revokeParam) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), revokeParam);
		int hostType = -1;
		if (payMethod == PayMethod.WECHAT) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_WECHAT_PAY : HostType.SYNC_WECHAT_PAY_NORMAL;
		} else if (payMethod == PayMethod.ALI) {
			hostType = HostType.IS_SYNC_DEBUG ? HostType.SYNC_ALI_PAY : HostType.SYNC_ALI_PAY_NORMAL;
		}
		return Api
				.getDefault(hostType)
				.syncOnlineRevoke(body)
				.map(new Func1<SyncRevokeRes, SyncRevokeRes>() {
					@Override
					public SyncRevokeRes call(SyncRevokeRes req) {
						return req;
					}
				}).compose(RxSchedulers.<SyncRevokeRes>io_main());
	}

	@Override
	public Observable<SyncRefundRes> syncBalanceRefund(String revokeParam) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), revokeParam);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER_PAY : HostType.SYNC_MEMBER_PAY_NORMAL)
				.syncMemberRefund(body)
				.map(new Func1<SyncRefundRes, SyncRefundRes>() {
					@Override
					public SyncRefundRes call(SyncRefundRes req) {
						return req;
					}
				}).compose(RxSchedulers.<SyncRefundRes>io_main());
	}

	@Override
	public Observable<SyncRevokeRes> syncBalanceRevoke(String revokeParam) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), revokeParam);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER_PAY : HostType.SYNC_MEMBER_PAY_NORMAL)
				.syncMemberRevoke(body)
				.map(new Func1<SyncRevokeRes, SyncRevokeRes>() {
					@Override
					public SyncRevokeRes call(SyncRevokeRes req) {
						return req;
					}
				}).compose(RxSchedulers.<SyncRevokeRes>io_main());
	}


	@Override
	public Observable<SyncCancelUseCouponRes> syncCancelUseCoupon(String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncCancelUseCoupon(body)
				.map(new Func1<SyncCancelUseCouponRes, SyncCancelUseCouponRes>() {
					@Override
					public SyncCancelUseCouponRes call(SyncCancelUseCouponRes req) {
						return req;
					}
				}).compose(RxSchedulers.<SyncCancelUseCouponRes>io_main());
	}

	@Override
	public Observable<SyncCancelPointRuleRes> syncCancelPointRule(String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncCancelPointRule(body)
				.map(new Func1<SyncCancelPointRuleRes, SyncCancelPointRuleRes>() {
					@Override
					public SyncCancelPointRuleRes call(SyncCancelPointRuleRes res) {
						return null;
					}
				}).compose(RxSchedulers.<SyncCancelPointRuleRes>io_main());
	}

	@Override
	public Observable<ValidationSetoutResult> validationSetout(String data) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.validationSetout(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), data)
				.map(new Func1<ValidationSetoutResult, ValidationSetoutResult>() {
					@Override
					public ValidationSetoutResult call(ValidationSetoutResult res) {
						return res;
					}
				}).compose(RxSchedulers.<ValidationSetoutResult>io_main());
	}

	@Override
	public Observable<ExecuteMeituanCodeResult> executeMeituanCode(String id, String codes) {
		return Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.executeMeituanCode(Api.getCacheControl(), BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), codes, TerminalConfigure
						.getTname(), id)
				.map(new Func1<ExecuteMeituanCodeResult, ExecuteMeituanCodeResult>() {
					@Override
					public ExecuteMeituanCodeResult call(ExecuteMeituanCodeResult res) {
						return res;
					}
				}).compose(RxSchedulers.<ExecuteMeituanCodeResult>io_main());
	}

	@Override
	public Observable<CxjWriteTouchTextRes> writeTouchText(String contents) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.writeTouchText(contents)
				.map(new Func1<CxjWriteTouchTextRes, CxjWriteTouchTextRes>() {
					@Override
					public CxjWriteTouchTextRes call(CxjWriteTouchTextRes res) {
						return res;
					}
				}).compose(RxSchedulers.<CxjWriteTouchTextRes>io_main());
	}

	@Override
	public Observable<ResponseBody> cxjNewOrder(String contents) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.cxjNewOrder(contents)
				.map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody res) {
						return res;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
	}

	@Override
	public Observable<ResponseBody> checkOut(String jsondata) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.checkOut(jsondata)
				.map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody res) {
						return res;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
	}

	@Override
	public Observable<ResponseBody> queryCXJPayResult(int oid, String payType, String total, long orderidentify) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.checkOnLinePay(oid, orderidentify, payType, total)
				.map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody res) {
						return res;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
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

	@Override
	public Observable<ResponseBody> closeNewOrder(long orderidentity) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.setCloseOrder(orderidentity)
				.map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody res) {
						return res;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
	}

	@Override
	public Observable<ResponseBody> cxjPrecheck(String jsondata) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.precheck(jsondata)
				.map(new Func1<ResponseBody, ResponseBody>() {
					@Override
					public ResponseBody call(ResponseBody res) {
						return res;
					}
				}).compose(RxSchedulers.<ResponseBody>io_main());
	}

	@Deprecated
	@Override
	public Observable<SyncMemberPayRes> syncMemberPay(String syncBalancePay, String syncPointPay, String syncSingleCouponPay) {
		return null;
	}

	//	@Override
	//	public Observable<Order> pushOrderToJYJ(NewOrderReq req) {
	//		return Api.getDefault(HostType.PUSH_ORDER_TO_JYJ)
	//				.pushOrderToJYJ(Api.getCacheControl(),
	//						BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
	//								.getStoreid(), new Gson().toJson(req), BaseConfigure.getToken())
	//				.map(new Func1<Order, Order>() {
	//					@Override
	//					public Order call(Order res) {
	//						return res;
	//					}
	//				}).compose(RxSchedulers.<Order>io_main());
	//	}


}
