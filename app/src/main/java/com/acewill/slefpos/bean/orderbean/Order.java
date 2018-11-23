package com.acewill.slefpos.bean.orderbean;

import android.content.Context;
import android.text.TextUtils;

import com.acewill.paylibrary.tencent.WXPay;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.canxingjianbean.CxjOrderProvider;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.paybean.PayModel;
import com.acewill.slefpos.bean.paybean.PaymentInfo;
import com.acewill.slefpos.bean.paybean.SyncPayReq;
import com.acewill.slefpos.bean.paybean.SyncRefundReq;
import com.acewill.slefpos.bean.smarantstorebean.PaymentTypesEntity;
import com.acewill.slefpos.bean.syncbean.Discount;
import com.acewill.slefpos.bean.syncbean.DiscountAmount;
import com.acewill.slefpos.bean.syncbean.SyncShoppingBean;
import com.acewill.slefpos.bean.syncbean.syncmember.CancelUseCouponReq;
import com.acewill.slefpos.bean.syncbean.syncmember.CanclePointReq;
import com.acewill.slefpos.bean.syncbean.syncnumber.SyncOrderNumber;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncQueryPayResultReq;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SyncConfig;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.orderui.main.exception.SelfPosThrowable;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.orderui.main.uihelper.Refund;
import com.acewill.slefpos.orderui.main.utils.SyncGenerate;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.utils.fileutil.JsonFileUtils;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.acewill.slefpos.utils.uiutils.NumberUtil;
import com.google.gson.Gson;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.SPUtils;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Author：Anch
 * Date：2018/5/8 10:21
 * Desc：用户选择生成订单的信息
 */
public class Order {
	private static RxManager     mRxManager;
	/**
	 * 订单对象
	 */
	private static Order         mOrder;
	/**
	 * 支付方式列表
	 */
	private        List<Payment> mPaymentList;
	/**
	 * 下单时间
	 */
	private        String        payTime;
	/**
	 * 堂食外带
	 */
	private        int           mTakeOut;

	/**
	 * 订单号
	 */
	private int    contentId;
	/**
	 * 桌台号，这个是送餐模式， 用户自己取得的那个牌号
	 * 取餐模式这个为空
	 */
	private String tableId;


	/**
	 * 微信支付宝美团威富通支付所需要的model
	 */
	private PayModel mPayModel;

	/**
	 * 业务流水号，订单流水号
	 */
	private String biz_id;


	/**
	 * 是否是会员
	 */
	private boolean isMember;
	private String  orderseq;
	private int     orderId;
	private int     newOrderTime;//这个是创建一次订单，调用下单的次数，最大两次
	private int     backOutSize;
	private int     orderseq_i;

	public int getTakeOut() {
		return mTakeOut;
	}


	public String getTakeOutStr() {
		return mTakeOut == 0 ? "堂食" : "外带";
	}

	public void setTakeOut(int takeOut) {
		mTakeOut = takeOut;
	}

	public int getContentId() {
		return contentId;
	}


	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	private List<SyncAcceptReq.DataBean.PaymentItemBean> mPaymentItemBeanList;

	public List<SyncAcceptReq.DataBean.PaymentItemBean> getSyncPaymentList() {
		if (mPaymentItemBeanList == null)
			mPaymentItemBeanList = new ArrayList<>();
		return mPaymentItemBeanList;
	}

	public List<Payment> getPaymentList() {
		if (mPaymentList == null)
			mPaymentList = new ArrayList<>();
		return mPaymentList;
	}


	/**
	 * 获取购物车对象
	 *
	 * @return
	 */
	public static Order getInstance() {
		if (mOrder == null) {
			mOrder = new Order();
			mRxManager = new RxManager();
		}
		return mOrder;
	}

	public String getPayTime() {
		if (payTime == null)
			payTime = String.valueOf(System.currentTimeMillis());
		return payTime;
	}

	/**
	 * 构建支付方式Payment,这个必须放到支付之后，因为openid 是在支付之后才获取到的
	 * 智慧快餐和餐行健都是这个Payment
	 *
	 * @param openid
	 * @param value
	 * @param paymentTypeId
	 * @param paymentTypeName
	 */
	public void createPayment(String openid, String value, String paymentTypeId, String paymentTypeName, String payOutTradeNo, String couponCode) {
		Payment payment = new Payment();
		payment.setValue(value);
		payment.setOpenid(openid);
		payment.setPaymentTypeId(paymentTypeId);
		payment.setPaidAt(Order.getInstance().getPayTime());
		payment.setCreatedAt(Order.getInstance().getPayTime());
		if (String.valueOf(PayMethod.MEITUANQUAN).equals(paymentTypeId)) {
			payment.setPaymentNo(couponCode);
		} else {
			payment.setPaymentNo(String.valueOf(Order.getInstance().getBiz_id()));
		}
		payment.setPaymentTypeName(paymentTypeName);
		payment.setPayName(paymentTypeName);
		payment.setTransaction_no(payOutTradeNo);
		Order.getInstance().getPaymentList().add(payment);
	}

	/**
	 * 清除
	 */
	public void clear() {
		Order.getInstance().getPaymentList().clear();
		Order.getInstance().setNewOrderTime(0);
		Order.getInstance().setBackOutSize(0);
		Order.getInstance().getSyncPaymentList().clear();
		Order.getInstance().setMemberPointLogOuid(null);
		Order.getInstance().setPayList(null);
		CxjOrderProvider.getInstance().setOid(-1);
		CxjOrderProvider.getInstance().setDiscountValue(-1);
	}

	/**
	 * 清除
	 */
	public void clearFavor() {
		Price.getInstance().setBalance(0);
		Price.getInstance().setPointValue(0);
		Price.getInstance().setCouponValue(0);
		Price.getInstance().setSyncmemberactivitydiscountamount(0);
		Price.getInstance().setCouponNo(null);
		Price.getInstance().setWshAccountCouponList(null);
		Price.getInstance().setSyncCoupon(null);
		Price.getInstance().setWshCoupon(null);
		Discount.getInstance().clear();
	}


	public List<Integer> getPayList() {
		if (payList == null)
			payList = new ArrayList<>();
		return payList;
	}

	public void setPayList(List<Integer> payList) {
		this.payList = payList;
	}

	private List<Integer> payList;

	public boolean isMethodHasPay(int payMethod) {
		if (payList != null) {
			return payList.contains(payMethod);
		}
		return false;
	}

	public NewOrderReq createNewOrderReq(int orderId, String callNumber) {
		NewOrderReq orderReq = new NewOrderReq();// 用于下单
		orderReq.setId(orderId);
		List<OrderDish> modelList = new ArrayList<>();
		modelList.addAll(Order.getInstance().getOrderList());
		orderReq.setItemList(modelList);
		String tableId = Order.getInstance().getTableId();
		if (tableId != null) {
			orderReq.setTableId(Long.parseLong(tableId));
			ArrayList<Long> longs = new ArrayList<>();
			longs.add(Long.parseLong(tableId));
			orderReq.setTableIds(longs);
		}
		orderReq.setCallNumber(callNumber);
		orderReq.setSource(TerminalConfigure.getTname());
		orderReq.setCreatedBy(TerminalConfigure.getTname());
		if (getTakeOut() == 0) {
			orderReq.setOrderType(Common.EAT_IN);
		} else {
			orderReq.setOrderType(Common.TAKE_OUT);
		}
		orderReq.setPaymentStatus("PAYED");
		if (isMember()) {
			WshAccount account = WshDataProvider.getWshAccount();
			orderReq.setMemberGrade(String
					.valueOf(account.getGradeName()));
			orderReq.setMemberBizId(Order.getInstance().getBiz_id());
			orderReq.setMemberid(account.getUno());
			orderReq.setMemberName(account.getName());
			orderReq.setMemberPhoneNumber(account.getPhone());
		}
		//原总价
		orderReq.setTotal(String.valueOf(Price.getInstance().getDishTotalWithMix()));
		//这是计算优惠方案之后实际支付的价格
		orderReq.setCost(Price.getInstance().getActualCost());
		orderReq.setBusinessId(Order.getInstance().getBiz_id());
		orderReq.setComment("");
		orderReq.setCustomerAmount("0");
		orderReq.setSubtraction("0");
		orderReq.setDiscount("1");
		orderReq.setCreatedAt(getPayTime());
		orderReq.setPaidAt(getPayTime());
		orderReq.setPaymentList(getPaymentList());
		return orderReq;
	}

	/**
	 * 智慧快餐获取支付的model
	 *
	 * @param authCode
	 * @return
	 */
	@Deprecated
	public PayModel createPayModel(String authCode, int payType) {
		mPayModel = new PayModel();
		mPayModel.setAuthCode(authCode);
		mPayModel.setOutTradeNo(Order.getInstance().getBiz_id());
		if (StoreConfigure.isUseMeiTuan()) {
			mPayModel.setTotalAmount(PriceUtil
					.multiplynofloat(new BigDecimal(Price.getInstance()
							.getTotal()), new BigDecimal(100))
					.toString());
		} else {
			mPayModel.setTotalAmount(String.valueOf(Price.getInstance().getTotal()));
		}
		mPayModel.setStore_name(StoreConfigure.getSname());
		mPayModel.setSubject("订单主题");
		mPayModel.setBody("订单内容");
		mPayModel.setAuthCode(authCode);
		mPayModel.setExpireMinutes((short) 5);
		mPayModel.setPayType(String.valueOf(payType));
		List<PaymentTypesEntity> types = SmarantDataProvider.getPayTypeList().getPaymentTypes();
		if (payType == PayMethod.ALI || payType == PayMethod.WECHAT) {
			for (PaymentTypesEntity entity : types) {
				if (entity.getId() == PayMethod.ALI) {
					PaymentInfo info = new PaymentInfo();
					info.appIDs = entity.getAppIDs();
					info.keyStr = entity.getKeyStr();
					info.appsecret = entity.getAppsecret();
					mPayModel.setPaymentStr(new Gson().toJson(info));
				} else if (payType == PayMethod.WECHAT) {
					WXPay.SUB_MCH_ID = entity.getSubMchID();
					WXPay.KEY = entity.getKeyStr();
					WXPay.MCH_ID = entity.getMchID();
					WXPay.APPID = entity.getAppIDs();
					mPayModel.setWxGoodsDetail("深圳奥琦玮");
				}
			}
		}
		return mPayModel;
	}

	/**
	 * 智慧快餐获取支付的model
	 *
	 * @param authCode
	 * @return
	 */

	public PayModel createPayModelNew(String authCode, int payType) {
		mPayModel = new PayModel();
		mPayModel.setAuthCode(authCode);
		mPayModel.setOutTradeNo(Order.getInstance().getBiz_id());
		if (StoreConfigure.isUseMeiTuan() || payType == PayMethod.WECHAT) {
			mPayModel.setTotalAmount(PriceUtil
					.multiplynofloat(new BigDecimal(Price.getInstance()
							.getTotal()), new BigDecimal(100))
					.toString());
		} else {
			mPayModel.setTotalAmount(String.valueOf(Price.getInstance().getTotal()));
		}
		mPayModel.setStore_name("门店名称");
		mPayModel.setSubject("订单主题");
		mPayModel.setBody("订单内容");
		mPayModel.setAuthCode(authCode);
		mPayModel.setExpireMinutes((short) 5);
		mPayModel.setPayType(String.valueOf(payType));
		List<PaymentTypesEntity> types = SmarantDataProvider.getPayTypeList().getPaymentTypes();
		if (payType == PayMethod.ALI) {
			for (PaymentTypesEntity entity : types) {
				if (entity.getId() == PayMethod.ALI) {
					PaymentInfo info = new PaymentInfo();
					info.appIDs = entity.getAppIDs();
					info.keyStr = entity.getKeyStr();
					info.appsecret = entity.getAppsecret();
					mPayModel.setPaymentStr(new Gson().toJson(info));
				}
			}
		} else if (payType == PayMethod.WECHAT) {
			for (PaymentTypesEntity entity : types) {
				if (entity.getId() == PayMethod.WECHAT) {
					PaymentInfo info = new PaymentInfo();
					info.appIDs = entity.getAppIDs();
					info.keyStr = entity.getKeyStr();
					info.appsecret = entity.getAppsecret();
					info.mchID = entity.getMchID();
					info.subMchID = entity.getSubMchID();
					mPayModel.setPaymentStr(new Gson().toJson(info));
				}
			}
			Calendar nowTime = Calendar.getInstance();
			mPayModel.setTimeStart(format1.format(nowTime.getTime()));
			nowTime.add(Calendar.MINUTE, 2);
			mPayModel.setTimeExpire(format1.format(nowTime.getTime()));
			mPayModel.setDeviceInfo(TerminalConfigure.getTname());
		}
		if (TextUtils.isEmpty(mPayModel.getPaymentStr())) {
			//如果为空，说明后台配置不正确
		}
		//		} else if (payType == PayMethod.WECHAT) {
		//			for (PaymentTypesEntity entity : types) {
		//				if (entity.getId() == PayMethod.WECHAT) {
		//					WXPay.SUB_MCH_ID = entity.getSubMchID();
		//					WXPay.KEY = entity.getKeyStr();
		//					WXPay.MCH_ID = entity.getMchID();
		//					WXPay.APPID = entity.getAppIDs();
		//					mPayModel.setWxGoodsDetail("深圳奥琦玮");
		//				}
		//			}
		//		}
		return mPayModel;
	}

	public String getBiz_id() {
		return biz_id;
	}

	public PayModel getPayModel() {
		if (mPayModel == null) {
			try {
				throw new SelfPosThrowable("需要先设置PayModel!");
			} catch (SelfPosThrowable execption) {
				execption.printStackTrace();
			}
		}
		return mPayModel;
	}


	public boolean isMember() {
		if (SystemConfig.isSyncSystem)
			return isMember && SyncDataProvider.getSyncMemberAccount() != null;
		else
			return isMember && WshDataProvider.getWshAccount() != null;
	}

	public void setMember(boolean member) {
		isMember = member;
	}

	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");// 用于格式化日期,作为日志文件名的一部分


	public void setBiz_id() {
		if (SystemConfig.isSyncSystem) {
			StringBuilder builder = new StringBuilder();
			builder.append("8888");
			builder.append(String
					.valueOf(format1.format(new Date())));
			//			this.orderseq = String.valueOf(NumberUtil.getRandomNum());
			this.orderseq = generateOrderReq();
			this.biz_id = builder.toString() + orderseq;
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("101");
			builder.append(String
					.valueOf(System.currentTimeMillis()));
			this.biz_id = builder.toString();
		}

	}

	private String generateOrderReq() {
		//找到最后一个
		SyncOrderNumber last       = LitePal.findLast(SyncOrderNumber.class);
		int             orderreq_i = 0;
		if (last != null) {
			orderreq_i = last.getOrderreq();
		}
		orderreq_i++;
		this.orderseq_i = orderreq_i;
		StringBuilder builder1 = new StringBuilder();
		builder1.append(SPUtils.getSharedIntData(BaseApplication.getContext(), "call_number"));
		if (orderreq_i > 0 && orderreq_i < 10) {
			builder1.append("00" + orderreq_i);
		} else if (orderreq_i >= 10 && orderreq_i < 100) {
			builder1.append("0" + orderreq_i);
		} else if (orderreq_i >= 100 && orderreq_i < 1000) {
			builder1.append("" + orderreq_i);
		}

		return builder1.toString();
	}


	public SyncPayReq getSyncPayReq() {
		if (mSyncPayReq == null) {
			try {
				throw new SelfPosThrowable("需要先设置PayModel!");
			} catch (SelfPosThrowable execption) {
				execption.printStackTrace();
			}
		}
		return mSyncPayReq;
	}


	private SyncPayReq mSyncPayReq;


	/**
	 * 创建同步时支付的模型
	 *
	 * @param author
	 * @return
	 */
	public String createSyncPayRequest(Context mContext, int payType, String author) {
		mSyncPayReq = new SyncPayReq();
		mSyncPayReq.setSignModel(SyncConfig.SIGNMODEL);
		mSyncPayReq.setTimestamp(System.currentTimeMillis());
		SyncPayReq.ContentBean content = new SyncPayReq.ContentBean();
		content.setCompanyOuid(SPUtils.getSharedStringData(mContext, "companyOuid"));
		content.setShopId(SPUtils.getSharedStringData(mContext, "shopId"));
		content.setDeviceId(SPUtils.getSharedStringData(mContext, "posId"));
		if (payType == PayMethod.ALI) {
			content.setPayMode("A");
			content.setPayPlatform("alipay");
		} else if (payType == PayMethod.WECHAT) {
			content.setPayMode("W");
			content.setPayPlatform("wxpay");
		} else if (payType == PayMethod.SYNCBALANCE) {
			content.setPayMode("M");
			content.setPayPlatform("mehub");
		}
		content.setShopNo(SPUtils.getSharedStringData(mContext, "shopNo"));
		content.setOutTradeNo(SyncGenerate.getUUID(UUID.randomUUID()));
		content.setTxNo(Order.getInstance().getBiz_id());
		String biz_no = SyncGenerate.getUUID(UUID.randomUUID());
		content.setBizNo(biz_no);
		content.setAuthCode(author);
		if (payType == PayMethod.SYNCBALANCE) {
			content.setTotalAmount(PriceUtil
					.multiplynofloat(new BigDecimal(Price.getInstance()
							.getBalance()), new BigDecimal(100))
					.intValue());
		} else {
			content.setTotalAmount(PriceUtil
					.multiplynofloat(new BigDecimal(Price.getInstance()
							.getTotal()), new BigDecimal(100))
					.intValue());
		}
		content.setBody(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "feStoreshopName"));
		content.setCodeType("A");
		content.setDeviceNo(SPUtils.getSharedStringData(mContext, "posId"));
		content.setCashierNo(TerminalConfigure.getTname());
		content.setCurrency("CNY");
		//		SyncShoppingBean shoppingBean = new SyncShoppingBean();
		//		shoppingBean.setBizNo(biz_no);
		//		shoppingBean.setOrdersNo(biz_no);
		//		shoppingBean.setOrderPromoAmount(0);
		//		shoppingBean.setOrderAmount(2);
		//		shoppingBean.setOrderAmount(2);
		//		shoppingBean.setOrderAmount(1);
		//		SyncShoppingBean.ItemsBean bean = new SyncShoppingBean.ItemsBean();
		//		bean.setOuid(SyncGenerate.getUUID(UUID.randomUUID()));
		//		bean.setSkuId("22");
		//		bean.setSkuName("2");
		//		bean.setQty(1);
		//		bean.setUsableQty(1);
		//		bean.setSkuPrice(2);
		//		bean.setPromoAmount(0);
		//		bean.setItemAmount(2);
		//		List<SyncShoppingBean.ItemsBean> items = new ArrayList<>();
		//		items.add(bean);
		//		shoppingBean.setItems(items);
		//		content.setShopping(shoppingBean);
		mSyncPayReq.setContent(content);

		return new Gson().toJson(mSyncPayReq);
	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分


	/**
	 * 保存下单数据，以防止支付成功下单失败的时候，无法再次下单
	 * <p>
	 * 该数据保存的操作在支付之前
	 * <p>
	 * 该数据的文件以biz_id命名，下单成功后删除，如果不成功则保存，收银员可以到系统设置页面找到该订单，并且重新下单
	 */
	public void saveAcceptData(Context context) {
		SyncAcceptReq req = new SyncAcceptReq();
		req.setCompanyOuid(SPUtils.getSharedStringData(context, "companyOuid"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		SyncAcceptReq.DataBean dataBean = new SyncAcceptReq.DataBean();
		dataBean.setShopId(SPUtils.getSharedStringData(context, "shopNo"));
		dataBean.setOrdersNo(Order.getInstance().getBiz_id());
		dataBean.setOrdersSeq(Order.getInstance().getOrderSeq());
		dataBean.setOrdersTime(format.format(new Date()));
		dataBean.setDeliverType(Order.getInstance().getTakeOut() == 0 ? "H" : "T");
		dataBean.setTotalAmount(getPaymentAmout());
		dataBean.setPaidStatus("P");
		dataBean.setPaidAmount(getPaymentAmout());
		dataBean.setMemo("");
		if (Order.getInstance().isMember() && SyncDataProvider.getSyncMemberAccount() != null) {
			dataBean.setCustomerRefNo(SyncDataProvider.getSyncMemberAccount().getMemberNo());
			dataBean.setCustomerOuid(SyncDataProvider.getSyncMemberAccount().getOuid());
		} else {
			dataBean.setCustomerRefNo("");
			dataBean.setCustomerOuid("");
		}
		dataBean.setItems(Cart.getInstance().getSyncAcceptItems());
		dataBean.setPaymentItems(Order.getInstance().getSyncPaymentList());
		dataBean.setPromos(Order.getInstance().getSyncPromosList());
		//		dataBean.setFees(Order.getInstance().getSyncFeesList());
		req.setData(dataBean);
		JsonFileUtils.saveDataToFile(Order.getInstance().getBiz_id(), new Gson().toJson(req));
	}


	/**
	 * 创建同步时下单的模型
	 *
	 * @return
	 */
	public String getSyncAcceptData(Context context) {
		SyncAcceptReq req = new SyncAcceptReq();
		req.setCompanyOuid(SPUtils.getSharedStringData(context, "companyOuid"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		SyncAcceptReq.DataBean dataBean = new SyncAcceptReq.DataBean();
		dataBean.setShopId(SPUtils.getSharedStringData(context, "shopNo"));
		dataBean.setOrdersNo(Order.getInstance().getBiz_id());
		dataBean.setOrdersSeq(Order.getInstance().getOrderSeq());
		dataBean.setOrdersTime(format.format(new Date()));
		dataBean.setDeliverType(Order.getInstance().getTakeOut() == 0 ? "H" : "T");
		dataBean.setTotalAmount(getPaymentAmout());
		dataBean.setPaidStatus("P");
		dataBean.setPaidAmount(getPaymentAmout());
		dataBean.setMemo("");
		if (Order.getInstance().isMember() && SyncDataProvider.getSyncMemberAccount() != null) {
			dataBean.setCustomerRefNo(SyncDataProvider.getSyncMemberAccount().getMemberNo());
			dataBean.setCustomerOuid(SyncDataProvider.getSyncMemberAccount().getOuid());
		} else {
			dataBean.setCustomerRefNo("");
			dataBean.setCustomerOuid("");
		}
		dataBean.setItems(Cart.getInstance().getSyncAcceptItems());
		dataBean.setPaymentItems(Order.getInstance().getSyncPaymentList());
		dataBean.setPromos(Order.getInstance().getSyncPromosList());
		req.setData(dataBean);
		return new Gson().toJson(req);
	}

	/**
	 * 没有优惠，实际支付的金额
	 *
	 * @return
	 */
	public String getPaymentAmout() {
		BigDecimal totalAmount = new BigDecimal("0");
		for (SyncAcceptReq.DataBean.PaymentItemBean bean : Order.getInstance()
				.getSyncPaymentList()) {
			totalAmount = PriceUtil.add(totalAmount, new BigDecimal(bean.getTxAmount()));
		}
		return totalAmount.toString();
	}

	/*"fees": [
{
	"feeType": "费用类型",
		"feeName": "费用名称",
		"feePrice": "费用单价",
		"feeQty": "费用数量",
		"feeAmount": "费用金额"
}
],*/
	private List<SyncAcceptReq.DataBean.FeesBean> getSyncFeesList() {
		List<SyncAcceptReq.DataBean.FeesBean> feesBeanList = new ArrayList<>();
		for (DiscountAmount amount : Discount.getInstance().getDiscountAmountList()) {
			SyncAcceptReq.DataBean.FeesBean bean = new SyncAcceptReq.DataBean.FeesBean();
			bean.setFeeType(amount.getDiscountAmountType());
			bean.setFeeName(amount.getDiscountAmountName());
			bean.setFeePrice(String.valueOf(amount.getDiscountAmountValue()));
			bean.setFeeAmount("-" + PriceUtil
					.multiply(String.valueOf(bean.getFeePrice()), Integer
							.parseInt(bean.getFeeQty())).toString());
			feesBeanList.add(bean);
		}
		if (Price.getInstance().getPointValue() > 0) {
			SyncAcceptReq.DataBean.FeesBean bean = new SyncAcceptReq.DataBean.FeesBean();
			bean.setFeeAmount(String.valueOf(-Price.getInstance().getPointValue()));
			bean.setFeeType("S");
			bean.setFeeName("积分抵现");
			bean.setFeePrice(String
					.valueOf(Price.getInstance().getPointValue()));
			bean.setFeeQty(String.valueOf(1));
			feesBeanList.add(bean);
		}
		if (Price.getInstance().getCouponValue() > 0) {
			SyncAcceptReq.DataBean.FeesBean bean = new SyncAcceptReq.DataBean.FeesBean();
			bean.setFeeAmount(String.valueOf(-Price.getInstance().getCouponValue()));
			bean.setFeeType("S");
			bean.setFeeName("优惠券");
			bean.setFeePrice(String.valueOf(Price.getInstance().getCouponValue()));
			bean.setFeeQty(String.valueOf(1));
			feesBeanList.add(bean);
		}
		//		if (Price.getInstance().getSyncmemberactivitydiscountamount() > 0) {
		//			SyncAcceptReq.DataBean.FeesBean bean = new SyncAcceptReq.DataBean.FeesBean();
		//			bean.setFeeAmount(String.valueOf(-Price.getInstance().getSyncmemberactivitydiscountamount()));
		//			bean.setFeeType("S");
		//			bean.setFeeName("会员折扣");
		//			bean.setFeePrice(String.valueOf(Price.getInstance().getSyncmemberactivitydiscountamount()));
		//			bean.setFeeQty(String.valueOf(1));
		//			feesBeanList.add(bean);
		//		}
		return feesBeanList;
	}


	/*"promos":[
	{
		"promoType":"优惠类型",
			"promoName":"优惠名称",
			"promoValue":"优惠值",
			"promoAmount":"优惠金额",
			"referenceNo":"优惠参考号"
	}
    ]*/
	public List<SyncAcceptReq.DataBean.PromosBean> getSyncPromosList() {
		List<SyncAcceptReq.DataBean.PromosBean> promosBeanList = new ArrayList<>();
		for (DiscountAmount amount : Discount.getInstance().getDiscountAmountList()) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoType(amount.getDiscountAmountType());
			bean.setPromoName(amount.getDiscountAmountName());
			bean.setPromoValue(String.valueOf(amount.getDiscountAmountValue()));
			bean.setPromoAmount(String.valueOf(amount.getDiscountAmount()));
			promosBeanList.add(bean);
		}
		if (Price.getInstance().getPointValue() > 0) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoValue(String.valueOf(Price.getInstance().getPointValue()));
			bean.setPromoType("O");//積分抵現
			bean.setPromoName("积分抵现");
			bean.setPromoAmount(String
					.valueOf(Price.getInstance().getPointValue()));
			promosBeanList.add(bean);
		}
		if (Price.getInstance().getCouponValue() > 0) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoType("C");//代金券
			bean.setPromoName(Price.getInstance().getSyncCoupon().getCouponName());
			bean.setPromoValue(String.valueOf(Price.getInstance().getCouponValue()));
			bean.setPromoAmount(String.valueOf(Price.getInstance().getCouponValue()));
			bean.setReferenceNo(Price.getInstance().getCouponNo());
			promosBeanList.add(bean);
		}
		return promosBeanList;
	}

	public String createCancleUseCouponParam() {
		CancelUseCouponReq          req  = new CancelUseCouponReq();
		CancelUseCouponReq.DataBean bean = new CancelUseCouponReq.DataBean();
		req.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		bean.setCouponNo(Price.getInstance().getCouponNo());
		bean.setOrigExchangeNo(Order.getInstance().getBiz_id());
		bean.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopNo"));
		req.setData(bean);
		return new Gson().toJson(req);
	}

	public String createCanclePointParam() {
		CanclePointReq          req  = new CanclePointReq();
		CanclePointReq.DataBean bean = new CanclePointReq.DataBean();
		bean.setMemberPointLogOuid(getMemberPointLogOuid());
		req.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		bean.setStoreshopOuid(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		req.setData(bean);

		return new Gson().toJson(req);
	}

	/**
	 * 随机生成，但是不重复的四位数
	 *
	 * @return
	 */
	public String getOrderSeq() {
		return orderseq;
	}

	/**
	 * 创建同步时查询支付的模型
	 *
	 * @return
	 */
	public String getSyncQureyPayReq() {
		SyncPayReq            req          = getSyncPayReq();
		SyncQueryPayResultReq payResultReq = new SyncQueryPayResultReq();
		payResultReq.setSignModel(SyncConfig.SIGNMODEL);
		payResultReq.setConsumerKey(SyncConfig.CONSUMERKEY);
		payResultReq.setTimestamp(req.getTimestamp());
		payResultReq.setSign(SyncConfig.SIGN);
		SyncQueryPayResultReq.ContentBean bean = new SyncQueryPayResultReq.ContentBean();
		bean.setCompanyOuid(req.getContent().getCompanyOuid());
		bean.setDeviceId(req.getContent().getDeviceId());
		bean.setOutTradeNo(req.getContent().getOutTradeNo());
		bean.setPayMode(req.getContent().getPayMode());
		bean.setPayPlatform(req.getContent().getPayPlatform());
		bean.setShopId(req.getContent().getShopId());
		payResultReq.setContent(bean);
		return new Gson().toJson(payResultReq);
	}

	public void setOrderId(int id) {
		this.orderId = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setNewOrderTime(int i) {
		this.newOrderTime = i;
	}

	public int getNewOrderTime() {
		return newOrderTime;
	}


	public int getBackOutSize() {
		return backOutSize;
	}

	public void setBackOutSize(int backOutSize) {
		this.backOutSize = backOutSize;
	}


	public NewOrderRes getNewOrderRes(int i) {
		NewOrderRes         res     = new NewOrderRes();
		NewOrderRes.Content content = new NewOrderRes.Content();
		res.setContent(content);
		return res;
	}

	/**
	 * 创建智慧快餐下单菜品列表
	 *
	 * @return
	 */
	public List<OrderDish> getOrderList() {
		List<OrderDish> cartDishes = new ArrayList<>();

		List<CartDish> orderDishs = null;//智慧快餐的dish可能要合并得到
		if (Cart.getInstance().getMarketDishList() != null) {
			orderDishs = Cart.getInstance().getMarketDishList();
		} else {
			orderDishs = Cart.getInstance().getCartDishes();
		}

		for (CartDish cartDish :  orderDishs) {
			OrderDish orderDish = new OrderDish();
			orderDish.setDishName(cartDish.getDishName());
			orderDish.setDishID(cartDish.getDishID());
			orderDish.setKindId(cartDish.getDishKind());
			orderDish.setDishKindStr(cartDish.getDishKindStr());
			orderDish.setQuantity(cartDish.getQuantity());
			orderDish.setPrice(Order.getInstance().isMember() ? cartDish.getMemberPrice() : cartDish
					.getPrice());
			orderDish.setCost(cartDish.getCost());//这里应该是实收
			orderDish.setOptionList(cartDish.getOptionList());
			orderDish.setSubItemList(cartDish.getSubItemList());
			orderDish.setMemberPrice(cartDish.getMemberPrice());
			orderDish.setDishUnit(cartDish.getDishUnit());
			orderDish.setMarketList(cartDish.getMarketList());
			cartDishes.add(orderDish);
		}
		return cartDishes;
	}

	//	public class Content {
	//		private String              callNumber;
	//		private String              id;
	//		private ArrayList<CartDish> itemList;
	public NewOrderRes getPrintOrder(String orderNo) {
		NewOrderRes         res1    = new NewOrderRes();
		NewOrderRes.Content content = new NewOrderRes.Content();
		content.setId(orderNo);
		content.setCallNumber(orderNo);
		res1.setContent(content);
		return res1;
	}
	//	/**
	//	 * 创建同步时储值支付模型
	//	 *
	//	 * @return
	//	 */
	//	public String createSyncBalancePay() {
	//		SyncBalancePayReq          req  = new SyncBalancePayReq();
	//		SyncBalancePayReq.DataBean bean = new SyncBalancePayReq.DataBean();
	//		bean.setAuthCode(SyncDataProvider.getSyncMemberAccount().getMemberNo());
	//		bean.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
	//		bean.setTxNo(Order.getInstance().getBiz_id());
	//		bean.setTxAmount(Price.getInstance().getBalance());
	//		req.setCompanyOuid(SPUtils
	//				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
	//		req.setConsumerKey(SyncConfig.CONSUMERKEY);
	//		req.setData(bean);
	//		return new Gson().toJson(req);
	//	}

	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分

	/**
	 * 创建同步时积分支付模型
	 *
	 * @return
	 */
	public String createSyncPointPay() {
		SyncPointPayReq req = new SyncPointPayReq();
		req.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		SyncPointPayReq.DataBean bean = new SyncPointPayReq.DataBean();
		bean.setMemberOuid(SPUtils.getSharedStringData(BaseApplication.getContext(), "memberOuid"));
		bean.setOnLineTxLogOuid(Order.getInstance().getBiz_id());
		bean.setPoint(PriceUtil.multiply(new BigDecimal(Price.getInstance()
				.getPointValue()), new BigDecimal(SyncDataProvider
				.getSyncMemberAccount().getPointRule().getPromoValue())).intValue());
		bean.setCurrentSoDate(format2.format(new Date()));
		bean.setPosUserOuid(SPUtils.getSharedStringData(BaseApplication.getContext(), "posId"));
		bean.setStoreshopOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "storeshopOuid"));
		req.setData(bean);
		return new Gson().toJson(req);
	}

	/**
	 * 创建同步时优惠券支付模型
	 *
	 * @param couponNo
	 * @param memberNo
	 * @return
	 */
	public String createSyncSingleCouponPay(String couponNo, String memberNo) {
		SyncSingleUseCouponReq req = new SyncSingleUseCouponReq();
		req.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		SyncSingleUseCouponReq.DataBean dataBean = new SyncSingleUseCouponReq.DataBean();
		dataBean.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
		dataBean.setMemberNo(memberNo);
		dataBean.setCouponNo(couponNo);
		SyncShoppingBean shoppingBean = new SyncShoppingBean();
		shoppingBean
				.setOrderPromoAmount(Price.getInstance().getTotalDiscountAmount());

		List<SyncShoppingBean.ItemsBean> itemsBeanList = new ArrayList<>();
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			SyncShoppingBean.ItemsBean itemsBean = new SyncShoppingBean.ItemsBean();
			itemsBean.setSkuId(cartDish.getDishID());
			itemsBean.setQty(cartDish.getQuantity());
			itemsBean.setPromoAmount(cartDish.getDiscountAmount());
			itemsBean.setItemAmount(PriceUtil
					.multiply(Price.getInstance().getDishCost(cartDish), cartDish.getQuantity())
					.floatValue());
			itemsBeanList.add(itemsBean);
			if (cartDish.getOptionList() != null && cartDish.getOptionList().size() > 0) {
				ArrayList<SyncShoppingBean.ItemsBean.CondimentsItem> condimentsItems = new ArrayList<>();
				for (UITasteOption option : cartDish.getOptionList()) {
					if (option.getSourceType().equals("G") || option.getSourceType().equals("F")) {
						continue;
					}
					SyncShoppingBean.ItemsBean.CondimentsItem item = new SyncShoppingBean.ItemsBean.CondimentsItem();
					item.setId(option.getOuid());
					item.setName(option.getOptionName());
					item.setQty(option.getQuantity() * cartDish.getQuantity());
					item.setPrice(option.getPrice());
					item.setSourceType(option.getSourceType());
					condimentsItems.add(item);
				}
				itemsBean.setCondimentsItem(condimentsItems);
			}
		}
		shoppingBean.setItems(itemsBeanList);
		shoppingBean.setOrderAmount(PriceUtil
				.subtract(Price.getInstance().getDishTotalWithMix(), new BigDecimal(Price
						.getInstance()
						.getTotalDiscountAmount())).floatValue());
		dataBean.setExchangeNo(Order.getInstance().getBiz_id());
		dataBean.setShopping(shoppingBean);
		req.setData(dataBean);
		//		dataBean.sets(Order.getInstance().getBiz_id());
		return new Gson().toJson(req);
	}

	/**
	 * 是否需要会员支付
	 */
	public boolean isMemberPay() {
		if (Price.getInstance().getPointValue() > 0 || Price.getInstance()
				.getBalance() > 0 || Price.getInstance().getCouponValue() > 0) {
			return true;
		}
		return false;
	}

	//	/**
	//	 * 创建同步时的paymentList
	//	 *
	//	 * @param amount   支付的金额
	//	 * @param balance  支付后账户余额
	//	 * @param payMode  支付方式
	//	 * @param memberNo 会员账号
	//	 */
	//	public void createSyncPayment(float amount, float balance, String payMode, String memberNo) {
	//		SyncAcceptReq.DataBean.PaymentItemBean bean = new SyncAcceptReq.DataBean.PaymentItemBean();
	//		bean.setTxOuid(Order.getInstance().getBiz_id());
	//		bean.setTxAmount(amount);
	//		bean.setTxTime(format.format(new Date()));
	//		bean.setTxNo(Order.getInstance().getBiz_id());
	//		bean.setTxReference(Order.getInstance().getBiz_id());
	//		bean.setPlatformReference(Order.getInstance().getBiz_id());
	//		bean.setPayMode(payMode);
	//		bean.setAccountNo(memberNo);
	//		bean.setSnapshotBalance(balance);
	//		bean.setShopDiscountAmount(0);
	//		if (payMode.equals("A")){
	//			bean.setLinkId("alipay");
	//		}else if (payMode.equals("W")){
	//			bean.setLinkId("wxpay");
	//		}
	//		Order.getInstance().getSyncPaymentList().add(bean);
	//	}

	/**
	 * 创建智慧快餐会员支付方式
	 */
	public void createMemberPayment() {
		if (Price.getInstance().getPointValue() > 0) {
			createPayment("", String
					.valueOf(Price.getInstance()
							.getPointValue()), SystemConfig.isCanXingJianSystem ? String
					.valueOf(PayMethod.CXJ_POINT) : String.valueOf(PayMethod.JIFEN), "会员积分", Order
					.getInstance().getBiz_id(), "");
			Refund.getInstance().addSmarantRevokeParam(String
					.valueOf(Price.getInstance()
							.getPointValue()), Order
					.getInstance().getBiz_id(), String.valueOf(PayMethod.JIFEN));
		}
		if (Price.getInstance().getBalance() > 0) {
			createPayment("", String.valueOf(Price.getInstance().getBalance())
					.toString(), SystemConfig.isCanXingJianSystem ? String
					.valueOf(PayMethod.CXJ_BALANCE) : String
					.valueOf(PayMethod.CHUZHI), "会员储值", Order.getInstance()
					.getBiz_id(), "");
			Refund.getInstance().addSmarantRevokeParam(String
					.valueOf(Price.getInstance()
							.getBalance()), Order
					.getInstance().getBiz_id(), String.valueOf(PayMethod.CHUZHI));
		}
		if (Price.getInstance().getCouponValue() > 0) {
			createPayment("", String
					.valueOf(Price.getInstance()
							.getCouponValue()), SystemConfig.isCanXingJianSystem ? String
					.valueOf(PayMethod.CXJ_COUPON) : String
					.valueOf(PayMethod.YOUHUIQUAN), "会员优惠券", Order
					.getInstance()
					.getBiz_id(), "");
			Refund.getInstance().addSmarantRevokeParam(String
					.valueOf(Price.getInstance()
							.getCouponValue()), Order
					.getInstance().getBiz_id(), String.valueOf(PayMethod.YOUHUIQUAN));
		}
	}


	/**
	 * 组成规则 6666+yyyyMMddHHmmss+取餐号
	 *
	 * @return
	 */
	private String createSyncPaymentOuid() {
		StringBuilder builder = new StringBuilder();
		builder.append("6666");
		builder.append(String.valueOf(format1.format(new Date())));
		return builder.toString() + String.valueOf(NumberUtil.getRandomNum());
	}

	/**
	 * 组成规则 7777+yyyyMMddHHmmss+取餐号
	 *
	 * @return
	 */
	private String createSyncRefundOuid() {
		StringBuilder builder = new StringBuilder();
		builder.append("7777");
		builder.append(String.valueOf(format1.format(new Date())));
		return builder.toString() + String.valueOf(NumberUtil.getRandomNum());
	}

	public void createSyncPayment(int txAmount, String txNo, String tradeNo, String tradeId, String payMode, String linkId, String accountNo, int shopDiscountAmount, String bizNo, String outTradeNo) {
		SyncAcceptReq.DataBean.PaymentItemBean bean = new SyncAcceptReq.DataBean.PaymentItemBean();
		bean.setTxOuid(createSyncPaymentOuid());
		bean.setTxAmount(PriceUtil.divide(new BigDecimal(txAmount), new BigDecimal("100"))
				.floatValue());
		Date date = new Date(Long.parseLong(getPayTime()));
		bean.setTxTime(format.format(date));
		bean.setTxNo(txNo);
		bean.setPlatformReference(tradeNo);//平台交易参考号
		bean.setTxReference(tradeId);//服务端交易参考号
		bean.setPayMode(payMode);
		bean.setLinkId(linkId);
		bean.setAccountNo(accountNo);
		bean.setOutTradeNo(outTradeNo);
		bean.setBizNo(bizNo);
		bean.setShopDiscountAmount(PriceUtil
				.divide(new BigDecimal(shopDiscountAmount), new BigDecimal("100"))
				.floatValue());
		Order.getInstance().getSyncPaymentList().add(bean);
	}

	public String createSyncRefundParam(SyncAcceptReq.DataBean.PaymentItemBean paymentItemBean) {
		//		String payPlatform = "";
		//		if (payMode.equals("A")) {
		//			payPlatform = "alipay";
		//		} else if (payMode.equals("W")) {
		//			payPlatform = "wxpay";
		//		} else if (payMode.equals("M")) {
		//			payPlatform = "mehub";
		//		}
		//		private String txOuid;
		//		private float  txAmount;
		//		private String txTime;
		//		private String txNo;
		//		private String txReference;
		//		private String platformReference;
		//		private String payMode;
		//		private String linkId;
		//		private String accountNo;
		//		private float  snapshotBalance;
		//		private float  shopDiscountAmount;


		SyncRefundReq             req  = new SyncRefundReq();
		SyncRefundReq.ContentBean bean = new SyncRefundReq.ContentBean();
		bean.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		bean.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
		bean.setDeviceId(SPUtils.getSharedStringData(BaseApplication.getContext(), "posId"));
		bean.setPayMode(paymentItemBean.getPayMode());
		bean.setPayPlatform(paymentItemBean.getLinkId());
		bean.setShopNo(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopNo"));
		bean.setOriginTradeId(paymentItemBean.getTxReference());
		bean.setRefundOutTradeNo(createSyncRefundOuid());
		bean.setTxNo(paymentItemBean.getTxNo());
		bean.setBizNo(paymentItemBean.getBizNo());
		bean.setTotalAmount(PriceUtil
				.multiply(new BigDecimal(paymentItemBean.getTxAmount()), new BigDecimal("100"))
				.intValue());
		req.setTimestamp(System.currentTimeMillis());
		req.setSign(SyncConfig.SIGN);
		req.setContent(bean);
		return new Gson().toJson(req);
	}


	public String getMemberPointLogOuid() {
		return memberPointLogOuid;
	}

	private String memberPointLogOuid;//這個積分支付成功了才會有，退款才會用到，所以要記錄

	public void setMemberPointLogOuid(String ouid) {
		memberPointLogOuid = ouid;
	}


	public void setDealsMap(HashMap<Integer, Integer> dealsMap) {
		this.dealsMap = dealsMap;
	}

	/**
	 * map key 美团菜品id ,已经添加的id的个数
	 */
	private HashMap<Integer, Integer> dealsMap;

	public HashMap<Integer, Integer> getDealsMap() {
		if (dealsMap == null)
			dealsMap = new HashMap<>();
		return dealsMap;
	}

	public int getOrderseq_i() {
		return orderseq_i;
	}

	public void setOrderseq_i(int orderseq_i) {
		this.orderseq_i = orderseq_i;
	}


	//	public MeituanQuanInfo getMeituanQuanInfo() {
	//		if (mMeituanQuanInfo == null)
	//			mMeituanQuanInfo = new MeituanQuanInfo();
	//		return mMeituanQuanInfo;
	//	}
	//
	//	public void setMeituanQuanInfo(MeituanQuanInfo meituanQuanInfo) {
	//		mMeituanQuanInfo = meituanQuanInfo;
	//	}
	//
	//	private MeituanQuanInfo mMeituanQuanInfo;


	//	/**
	//	 * 使用美团团购券
	//	 */
	//	public class MeituanQuanInfo {
	//		public List<String> getDealIds() {
	//			if (dealIds == null)
	//				dealIds = new ArrayList<>();
	//			return dealIds;
	//		}
	//
	//		public void setDealIds(List<String> dealIds) {
	//			this.dealIds = dealIds;
	//		}
	//
	//
	//		/**
	//		 * 美团团购券id
	//		 */
	//		private List<String> dealIds;
	//
	//
	//		public float getTotalPrice() {
	//			return totalPrice;
	//		}
	//
	//		public void setTotalPrice(float totalPrice) {
	//			this.totalPrice = totalPrice;
	//		}
	//		private float                totalPrice;
	//	}

}
