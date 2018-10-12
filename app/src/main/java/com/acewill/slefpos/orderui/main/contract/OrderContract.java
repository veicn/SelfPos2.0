package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
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
import com.acewill.slefpos.bean.paybean.PayModel;
import com.acewill.slefpos.bean.paybean.SelfPosPayResult;
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
import com.acewill.slefpos.bean.systembean.ErrorType;
import com.acewill.slefpos.bean.wshbean.CXJWshCreateDeal;
import com.acewill.slefpos.bean.wshbean.CommitWshDealRes;
import com.acewill.slefpos.bean.wshbean.CreateDealRes;
import com.acewill.slefpos.bean.wshbean.WshCreateDeal;
import com.acewill.slefpos.kds.kdsbean.KDSRes;
import com.acewill.slefpos.kds.kdsbean.KdsOrderBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Author：Anch
 * Date：2018/5/4 10:08
 * Desc：
 */
public interface OrderContract {
	abstract class Model extends BaseModel {
		public abstract Observable<BaseAliPayResult> aliShuaKa(int payType, PayModel model);

		public abstract Observable<BaseWechatPayResult> wechatShuaKa(int payType, PayModel model);

		public abstract Observable<BaseAliPayResult> aliSaoMa(int payType, PayModel model);

		public abstract Observable<BaseWechatPayResult> wechatSaoMa(int payType, PayModel model);

		public abstract Observable<BaseAliPayResult> queryAliPayResult(PayModel payModel);

		public abstract Observable<BaseWechatPayResult> queryWechatPayResult(PayModel payModel);

		public abstract Observable<SyncPayResult> syncPay(int payMethod, String data);

		public abstract Observable<BaseMeiTuanPayResult> meituanMicroPay(PayModel payModel);

		public abstract Observable<BaseMeiTuanPayResult> meituanSaomaPay(PayModel payModel);

		public abstract Observable<BaseMeiTuanPayResult> queryMeiTuanPayResult(PayModel payModel);

		public abstract Observable<NewOrderIdRes> getNewOrderId();

		public abstract Observable<NewOrderRes> pushOrder(NewOrderReq req);

		public abstract Observable<KDSRes> connectKds();

		public abstract Observable<KDSRes> notityKDS(KdsOrderBean req);

		public abstract Observable<CheckCountResp> checkDishCounts(String dishStr);

		public abstract Observable<SyncAcceptRes> syncAccept(int timestamp, String sign, String dishStr);

		public abstract Observable<SyncQureyPayResultRes> querySyncPayResult(int payMethod, String data);

		public abstract Observable<NewOrderRes> queryOrderById(int id);

		public abstract Observable<SyncQureyPayResultRes> backOut(SmarantRefundReq refundReq);

		public abstract Observable<SyncPayResult> syncBalancePay(String syncBalancePay);

		public abstract Observable<SyncPointPayRes> syncPointPay(String syncPointPay);

		public abstract Observable<SyncMemberPayRes> syncMemberPay(String syncBalancePay, String syncPointPay, String syncSingleCouponPay);

		public abstract Observable<SyncSingleUseCouponRes> syncSingleCouponPay(String syncSingleCouponPay);

		public abstract Observable<CreateDealRes> wshCreateDeal(WshCreateDeal.Request request);

		public abstract Observable<CxjWshYuJieRes> wshYjset(CXJWshCreateDeal.Request request);

		public abstract Observable<CommitWshDealRes> commitWshDeal(String bizId, String verifySms);

		public abstract Observable<SyncRefundRes> syncOnlinePayRefund(int payMethod, String revokeParam);

		public abstract Observable<SyncRevokeRes> syncOnlinePayRevoke(int payMethod, String revokeParam);

		public abstract Observable<SyncRefundRes> syncBalanceRefund(String revokeParam);

		public abstract Observable<SyncRevokeRes> syncBalanceRevoke(String revokeParam);

		public abstract Observable<SyncCancelUseCouponRes> syncCancelUseCoupon(String data);

		public abstract Observable<SyncCancelPointRuleRes> syncCancelPointRule(String data);

		public abstract Observable<ValidationSetoutResult> validationSetout(String data);

		public abstract Observable<ExecuteMeituanCodeResult> executeMeituanCode(String id, String codes);

		public abstract Observable<CxjWriteTouchTextRes> writeTouchText(String contents);

		public abstract Observable<ResponseBody> cxjNewOrder(String contents);

		public abstract Observable<ResponseBody> checkOut(String jsondata);

		public abstract Observable<ResponseBody> queryCXJPayResult(int oid, String payType, String total, long orderidentify);

		public abstract Observable<CxjLoginModel> cxjUserLogin(String username, String pwd);

		public abstract Observable<ResponseBody> closeNewOrder(long orderidentity);

		public abstract  Observable<ResponseBody> cxjPrecheck(String jsondata);
	}

	interface View extends BaseView {
		void returnAliShuaKaResult(SelfPosPayResult result);

		void returnWechatShuaKaResult(SelfPosPayResult result);

		void returnQueryAliPayResult(SelfPosPayResult result);

		void returnALiSaoMaResult(SelfPosPayResult code);

		void returnWechatSaoMaResult(SelfPosPayResult code);

		void returnQueryWechatPayResult(SelfPosPayResult code);

		void returnSyncPayResult(SelfPosPayResult code);

		void returnMeiTuanMicroPayResult(SelfPosPayResult code);

		void returnMeiTuanSaomaPayResult(SelfPosPayResult code);

		void returnQueryMeiTuanPayResult(SelfPosPayResult code);

		void returnNewOrderIdResutl(int orderId);

		void returnPushOrderResult(NewOrderRes orderRes);

		void returnNotifyKDSResult(boolean isOk);

		void kdsConnectOk(boolean isOK);

		void returnCheckDishCountResult(CheckCountResp res);

		void returnSyncAccept(SyncAcceptRes res);

		void returnQuerySyncPayResult(SelfPosPayResult result);

		void returnQueryOrderResult(NewOrderRes orderRes);

		void returnBackOutResult(boolean success, String msg);

		void returnPushOrderToJYJ(NewOrderRes orderRes);


		void returnSyncPointPayResult(SyncPointPayRes orderRes);

		void returnSyncMemberPayResult();

		void returnSyncSingleCouponPayResult(SyncSingleUseCouponRes orderRes);

		void returnWshCreateDealResult(CreateDealRes res);


		void returnWshCommitDealResult(CommitWshDealRes res);

		void returnMeituanValidateCodeResult(ValidationSetoutResult result);

		void returnExecuteMeituanCodeResult(ExecuteMeituanCodeResult result);

		void returnWriteTouchTextResult(CxjWriteTouchTextRes result, ErrorType errorType);

		void returnCheckOutResult(ResponseBody result);


		void returnWshYjset(CxjWshYuJieRes result);

		void returnQueryCXJPayResult(ResponseBody result);

		//		void returnSyncRevoke(SyncRefundRes res);
		//
		//		void returnSyncCancleUseCoupon(SyncRefundRes res);
		//
		//		void returnSyncCanclePointRule(SyncRefundRes res);
		void returnCXJLoginResult(CxjLoginModel cxjLoginModel);

		void returnCXJNewOrderResult(ResponseBody res);

		void returnCXJPrecheckResult(ResponseBody result);

	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void aliPay(int payType, PayModel payModel);

		public abstract void wechatPay(int payType, PayModel payModel);

		public abstract void queryAliPayResult(PayModel payModel);

		public abstract void syncPay(int i, String data);

		public abstract void meituanPay(int i, PayModel model);

		public abstract void queryMeiTuanPayResult(PayModel model);

		public abstract void getNewOrderId();

		public abstract void pushOrder(NewOrderReq req);

		public abstract void notifyKDS(KdsOrderBean kdsOrderBean);

		public abstract void connectKDS();

		public abstract void checkDishCounts(String dishStr);

		public abstract void syncAccept(int timestamp, String sign, String data);

		public abstract void syncQueryPayResult(int payMethod, String req);

		public abstract void queryOrderById(int id);

		public abstract void backOut(SmarantRefundReq payment);

		@Deprecated
		public abstract void wxPay(int type, PayModel model);

		public abstract void pushOrderToJYJ(NewOrderReq req);

		public abstract void syncBalancePay(String syncBalancePay);

		public abstract void syncPointPay(String syncPointPay);

		public abstract void syncMemberPay(String syncBalancePay, String syncPointPay, String syncSingleCouponPay);

		public abstract void syncSingleCouponPay(String syncSingleCouponPay);

		public abstract void wshCreateDeal(WshCreateDeal.Request request);

		public abstract void wshYjset(CXJWshCreateDeal.Request request);

		public abstract void commitWshDeal(String bizId, String verifySms);

		public abstract void syncRefund(int paymethod, String data);

		public abstract void syncRevoke(int paymethod, String data);

		public abstract void syncCancelUseCoupon(String data);

		public abstract void syncCancelPointRule(String data);

		public abstract void validationSetout(String meituanquanid);

		public abstract void executeMeituanCode(String orderId, String couponCodes);

		public abstract void queryWechatPayResult(PayModel model);

		public abstract void writeTouchText(String contents);

		public abstract void checkOut(String jsondata);

		public abstract void queryCXJPayResult(int oid, String payType, String total, long orderidentify);

		public abstract void goLogin();

		public abstract void cxjNewOrder(String contents);

		public abstract void closeNewOrder(long orderidentity);

		public abstract void cxjPrecheck(String jsondata);
	}
}
