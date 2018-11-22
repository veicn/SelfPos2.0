package com.acewill.slefpos.api;

import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjMemberLoginRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjWriteTouchTextRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjWshYuJieRes;
import com.acewill.slefpos.bean.meituanbean.ExecuteMeituanCodeResult;
import com.acewill.slefpos.bean.meituanbean.ValidationSetoutResult;
import com.acewill.slefpos.bean.orderbean.CheckCountResp;
import com.acewill.slefpos.bean.orderbean.NewOrderIdRes;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.paybean.BaseAliPayResult;
import com.acewill.slefpos.bean.paybean.BaseMeiTuanPayResult;
import com.acewill.slefpos.bean.paybean.BaseWechatPayResult;
import com.acewill.slefpos.bean.paybean.SyncPayResult;
import com.acewill.slefpos.bean.paybean.SyncRefundRes;
import com.acewill.slefpos.bean.paybean.SyncRevokeRes;
import com.acewill.slefpos.bean.smarantbean.DishKindData;
import com.acewill.slefpos.bean.smarantbean.DishMenu;
import com.acewill.slefpos.bean.smarantstorebean.BaseEntity;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallsData;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.bean.smarantstorebean.MarketData;
import com.acewill.slefpos.bean.smarantstorebean.PayTypeData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterTemplatesData;
import com.acewill.slefpos.bean.smarantstorebean.SelfPosConfigurationData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishPosResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishRegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.QryStoreResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.RegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitResponseData;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelPointRuleRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncCancelUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncPointPayRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncQureyPayResultRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.wshbean.CommitWshDealRes;
import com.acewill.slefpos.bean.wshbean.CreateDealRes;
import com.acewill.slefpos.bean.wshbean.WshAccountRes;
import com.acewill.slefpos.kds.kdsbean.KDSRes;
import com.jaydenxiao.common.basebean.BaseRespose;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * 三种请求方式
 * 1、
 */
public interface ApiService {

	//    @GET("login")
	//    Observable<BaseRespose<User>> login(@Query("username") String username, @Query("password") String password);
	//
	//    @GET("nc/article/{postId}/full.html")
	//    Observable<Map<String, NewsDetail>> getNewDetail(
	//		    @Header("Cache-Control") String cacheControl,
	//		    @Path("postId") String postId);
	//"terminalMac":"s45437926","terminalType":"2"
	@POST("api/terminal/bind")
	Observable<BaseRespose<BindEntity>> bind(
			@Header("Cache-Control") String cacheControl,
			@Query("terminalMac") String terminalMac,
			@Query("terminalType") String terminalType);

	//	String appid, String brandid, String storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid
	@POST("api/terminal/login")
	Observable<LoginEntity> login(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("tname") String tname,
			@Query("terminalmac") String terminalmac,
			@Query("receiveNetOrder") String receiveNetOrder,
			@Query("longitute") String longitute,
			@Query("latitute") String latitute,
			@Query("description") String description,
			@Query("currentVersion") String currentVersion,
			@Query("versionid") String versionid
	);

	@POST("api/terminal/unbind")
	Observable<BaseEntity> unbind(
			@Header("Cache-Control") String cacheControl,
			@Query("terminalMac") String terminalMac,
			@Query("terminalType") String terminalType,
			@Query("token") String token);

	@POST("api/terminal/market")
	Observable<MarketData> market(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("sourcetype") String sourcetype,
			@Query("token") String token);

	@POST("api/kichenStalls/getPrinters")
	Observable<PrinterData> getPrinters(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);

	@POST("api/terminal/paytypes")
	Observable<PayTypeData> payTypes(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);


	@POST("api/terminal/getOtherfiles")
	Observable<ImagesData> getOtherfiles(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);

	@POST("api/store_operation/getSelfposConfiguration")
	Observable<SelfPosConfigurationData> getSelfposConfiguration(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);


	@POST("api/kichenStalls/getKichenStalls")
	Observable<KichenStallsData> getKichenStalls(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);

	@POST("api/printTemplate/getAllTemplates")
	Observable<PrinterTemplatesData> getAllTemplates(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);

	@POST("api/kichenStalls/getKDSes")
	Observable<KdsData> getKDSes(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("token") String token);

	@POST("api/terminal/dishmenu/sku")
	Observable<DishMenu> getDishMenu(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("sourcetype") String sourcetype,
			@Query("terminalid") String terminalid,
			@Query("token") String token);

	@POST("api/terminal/dishKind")
	Observable<DishKindData> getDishKind(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("sourcetype") String sourcetype,
			@Query("terminalid") String terminalid,
			@Query("token") String token);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("pos/pub/register/init.action")
	Observable<SyncInitResponseData> syncInit(@Body RequestBody route);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("pos/pub/register/qryStore.action")
	Observable<QryStoreResponseData> syncQryStore(@Body RequestBody route);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("pos/pub/register/finishPos.action")
	Observable<FinishPosResponseData> finishPos(@Body RequestBody route);


	@Streaming
	@POST("pos/pub/data.action")
	Observable<ResponseBody> downSyncData(@Body RequestBody route);

	@POST("alipay/FaceToFacePayment")
	Observable<BaseAliPayResult> aliPayShuaKa(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("outTradeNo") String outTradeNo,
			@Query("authCode") String authCode,
			@Query("subject") String subject,
			@Query("totalAmount") String totalAmount,
			@Query("timeoutExpress") String timeoutExpress,
			@Query("paymentStr") String paymentStr);

	@POST("alipay/FaceToFacePaymentOfPrecreate")
	Observable<BaseAliPayResult> aliPaySaoMa(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("outTradeNo") String outTradeNo,
			@Query("authCode") String authCode,
			@Query("subject") String subject,
			@Query("totalAmount") String totalAmount,
			@Query("timeoutExpress") String timeoutExpress,
			@Query("terminalId") String terminalId,
			@Query("paymentStr") String paymentStr);

	@POST("alipay/queryAlipayResult")
	Observable<BaseAliPayResult> aliQueryResult(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("outTradeNo") String outTradeNo,
			@Query("paymentStr") String paymentStr);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("api/trade/scan/pay")
	Observable<SyncPayResult> syncPay(@Body RequestBody route);

	@POST("/api/meituan/micropay")
	Observable<BaseMeiTuanPayResult> meituanMicroPay(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appid,
			@Query("storeId") int brandid,
			@Query("outTradeNo") String outTradeNo,
			@Query("totalFee") int totalFee,
			@Query("subject") String subject,
			@Query("body") String body,
			@Query("authCode") String authCode,
			@Query("expireMinutes") short expireMinutes,
			@Query("payType") String payType);


	@POST("api/meituan/query")
	Observable<BaseMeiTuanPayResult> queryMeiTuanPayResult(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appid,
			@Query("storeId") int brandid,
			@Query("outTradeNo") String outTradeNo);


	@GET("api/orders/nextOrderId")
	Observable<NewOrderIdRes> nextOrderId(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("token") String token);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/orders")
	Observable<NewOrderRes> pushOrder(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("order") String order,
			@Query("token") String token);



	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/orders/create")
	Observable<NewOrderRes> pushOrder2(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Body RequestBody route);





	@POST("/AcewillKDS/newOrder.do")
	Observable<KDSRes> notiKDS(
			@Header("Cache-Control") String cacheControl,
			@Query("orderdata") String kdsOrderJson);

	@POST("/AcewillKDS/connectionCheck.do")
	Observable<KDSRes> connectKds(@Header("Cache-Control") String cacheControl);


	@POST("/api/terminal/checkCounts")
	Observable<CheckCountResp> checkDishCounts(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appId,
			@Query("brandid") int brandId,
			@Query("storeid") int storeId,
			@Query("dishIDStr") String dishJson,
			@Query("token") String token);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/openapi/trade/orders/accept.action")
	Observable<SyncAcceptRes> syncAccept(
			@Header("X-Timestamp") int timestamp,
			@Header("X-Partner") String partner,
			@Header("X-Sign") String sign,
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/trade/scan/pay/query")
	Observable<SyncQureyPayResultRes> syncQueryPayResult(
			@Body RequestBody route);

	@POST("/api/orders/queryOrder")
	Observable<NewOrderRes> queryOrderById(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("orderId") int orderId);

	/**
	 * 智慧快餐下单失败退款
	 */
	@POST("/api/orders/backout")
	Observable<SyncQureyPayResultRes> smarantBackOut(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("paymentNo") String paymentNo,
			@Query("paymentTypeId") String paymentTypeId,
			@Query("returnUserName") String returnUserName,
			@Query("total_fee") String cost,
			@Query("token") String token);


	@POST("/api/terminal/uploadLog")
	Observable<NewOrderRes> uploadLog(
			@Header("Cache-Control") String cacheControl,
			@Query("appid") String appid,
			@Query("brandid") int brandid,
			@Query("storeid") int storeid,
			@Query("terminalid") int terminalid,
			@Query("tname") int tname);

	@GET("/public/members/getMemberInfo")
	Observable<WshAccountRes> wshMemberLogin(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("account") String account,
			@Query("token") String token);

	@POST("/public/members/createDealDCJ")
	Observable<CreateDealRes> createDeal(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("deal") String deal,
			@Query("token") String token);

	@POST("/public/members/commitDeal")
	Observable<CommitWshDealRes> commitWshDeal(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("bizId") String bizId,
			@Query("verifySms") String verifySms,
			@Query("token") String token);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/getMemberInfo")
	Observable<SyncMemberLoginRes> syncMemberLogin(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/user/pay")
	Observable<SyncPayResult> syncBalancePay(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/pointRule")
	Observable<SyncPointPayRes> syncPointPay(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/useCouponSingle")
	Observable<SyncSingleUseCouponRes> syncUseCouponSingle(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/loadCouponSingle")
	Observable<SyncSingleUseCouponRes> syncPreviewCouponSingle(
			@Body RequestBody route);


	//	@Headers({"Content-Type: application/json", "Accept: application/json"})
	//	@POST("/api/trade/scan/revoke")
	//	Observable<SyncRevokeReq> syncOnlinePayRevoke(
	//			@Body RequestBody route);
	//
	//	@Headers({"Content-Type: application/json", "Accept: application/json"})
	//	@POST("/api/user/revoke")
	//	Observable<SyncRevokeReq> syncBalanceRevoke(
	//			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/cancelUseCoupon")
	Observable<SyncCancelUseCouponRes> syncCancelUseCoupon(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/posapi/cancelPointRule")
	Observable<SyncCancelPointRuleRes> syncCancelPointRule(
			@Body RequestBody route);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/trade/scan/refund")
	Observable<SyncRefundRes> syncOnlineRefund(
			@Body RequestBody route);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/user/refund")
	Observable<SyncRefundRes> syncMemberRefund(
			@Body RequestBody route);


	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/trade/revoke")
	Observable<SyncRevokeRes> syncOnlineRevoke(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/api/user/revoke")
	Observable<SyncRevokeRes> syncMemberRevoke(
			@Body RequestBody route);

	@POST("/api/meituan/createCodeUrl")
	Observable<BaseMeiTuanPayResult> createCodeUrl(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("outTradeNo") String outTradeNo,
			@Query("totalFee") int totalFee,
			@Query("body") String body,
			@Query("payType") String payType,
			@Query("subject") String subject);

	@POST("/public/waimai/meituan/shop/validationSetout")
	Observable<ValidationSetoutResult> validationSetout(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("couponCode") String couponCode);

	@POST("/public/waimai/meituan/shop/executeCode")
	Observable<ExecuteMeituanCodeResult> executeMeituanCode(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("couponCodes") String couponCodes,
			@Query("eName") String eName,
			@Query("orderId") String orderId);

	@POST("/api/wxpay/micropayToWepay")
	Observable<BaseWechatPayResult> micropayToWepay(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("outTradeNo") String outTradeNo,
			@Query("totalFee") int totalFee,
			@Query("body") String body,
			@Query("authCode") String authCode,
			@Query("paymentStr") String paymentStr,
			@Query("timeStart") String timeStart,
			@Query("timeExpire") String timeExpire,
			@Query("deviceInfo") String deviceInfo,
			@Query("spbillCreateIp") String spbillCreateIp);


	@POST("/api/wxpay/orderQuery")
	Observable<BaseWechatPayResult> queryWechatPayResult(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("outTradeNo") String outTradeNo,
			@Query("paymentStr") String paymentStr);

	@POST("/api/wxpay/unifiedOrderToWepay")
	Observable<BaseWechatPayResult> unifiedOrderToWepay(
			@Header("Cache-Control") String cacheControl,
			@Query("appId") String appId,
			@Query("brandId") int brandId,
			@Query("storeId") int storeId,
			@Query("outTradeNo") String outTradeNo,
			@Query("totalFee") int totalFee,
			@Query("spbillCreateIp") String spbillCreateIp,
			@Query("body") String body,
			@Query("paymentStr") String paymentStr);

	/**
	 * 餐行健登录系统
	 *
	 * @param username
	 * @param pwd
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=clientTouch&do=userLogin")
	Observable<CxjLoginModel> cxjUserLogin(@Field("username") String username,
	                                       @Field("pwd") String pwd);


	/**
	 * 餐行健下载菜品档案
	 *
	 * @return
	 */
	@GET("/order/api/api.php?do=downloadSqliteFile&app=ClientNewAndroidMobile")
	Observable<ResponseBody> downCanXingJianData();


	/**
	 * 餐行健下载菜品档案
	 *
	 * @return
	 */
	@POST("/order/api/api.php?do=isSqliteFileUpdated&app=ClientNewAndroidMobile")
	Observable<ResponseBody> isSqliteFileUpdated(@Query("timestamp") String timestamp);

	/**
	 * 餐行健会员登录
	 *
	 * @param mid
	 * @return 就的链接
	 * @POST("/order/api/api.php?app=ClientTouchMember&do=getcardmbidbymid")
	 */
	@FormUrlEncoded
	@POST("order/api/api.php?app=ClientTouchFast&do=getWeiShengHuoCardInfo")
	Observable<CxjMemberLoginRes> getMemberInfo(@Field("mid") String mid);

	/**
	 * 餐行健会员预结
	 * <p>
	 * map.put("wid", "");
	 * map.put("oid", oid);
	 * map.put("uid", uid);
	 * map.put("cardid", cardid);
	 * map.put("type", type);
	 * map.put("activityid", "");
	 * map.put("activityname", "");
	 * if (coupons != null && coupons.size() > 0)
	 * map.put("couponid", coupons.get(0).getCoupon_ids().get(0));
	 * else
	 * map.put("couponid", "");
	 * map.put("cno", cno);
	 *
	 * @param mid
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientTouchMember&do=wshYjset")
	Observable<CxjWshYuJieRes> wshYjset(@Field("wid") String mid,
	                                    @Field("oid") String oid,
	                                    @Field("uid") String uid,
	                                    @Field("cardid") String cardid,
	                                    @Field("type") String type,
	                                    @Field("activityid") String activityid,
	                                    @Field("activityname") String activityname,
	                                    @Field("couponid") String couponid,
	                                    @Field("cno") String cno);


	/**
	 * 餐行健 提交菜品
	 *
	 * @param contents
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientTouchFast&do=writeTouchText")
	Observable<CxjWriteTouchTextRes> writeTouchText(@Field("contents") String contents);

	/**
	 * 餐行健 提交菜品
	 *
	 * @param contents
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientSnackOrder&do=precheck")
	Observable<ResponseBody> precheck(@Field("jsondata") String contents);

	/**
	 * 餐行健 新建订单
	 *
	 * @param contents
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientSnackOrder&do=newOrder")
	Observable<ResponseBody> cxjNewOrder(@Field("jsondata") String contents);

	/**
	 * 餐行健查询在线支付结果
	 *
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientTouchFast&do=checkOnLinePay")
	Observable<ResponseBody> checkOnLinePay(@Field("oid") int oid,
	                                        @Field("orderidentity") long orderidentity,
	                                        @Field("ptid") String ptid,
	                                        @Field("cost") String cost);


	/**
	 * 餐行健 下单
	 *
	 * @param mid
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientSnackOrder&do=checkOut")
	Observable<ResponseBody> checkOut(@Field("jsondata") String mid);


	/**
	 * 餐行健 取消新建订单
	 *
	 * @param mid
	 * @return
	 */
	@FormUrlEncoded
	@POST("/order/api/api.php?app=ClientTouchFast&do=setCloseOrder")
	Observable<ResponseBody> setCloseOrder(@Field("orderidentity") long orderidentity);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/public/terminal/register/auth")
	Observable<RegisterResponseData> register(
			@Body RequestBody route);

	@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/public/terminal/register/finish")
	Observable<FinishRegisterResponseData> registerFinish(
			@Body RequestBody route);

}
