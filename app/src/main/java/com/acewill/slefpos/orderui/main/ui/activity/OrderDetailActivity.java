package com.acewill.slefpos.orderui.main.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.acewill.slefpos.R;
import com.acewill.slefpos.animation.RotateAnimation;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.base.BaseHandler;
import com.acewill.slefpos.bean.canxingjianbean.CXJPrecheckRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjCheckOutRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjOrderProvider;
import com.acewill.slefpos.bean.canxingjianbean.CxjWriteTouchTextRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjWshYuJieRes;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.meituanbean.ExecuteMeituanCodeResult;
import com.acewill.slefpos.bean.meituanbean.ValidationSetoutResult;
import com.acewill.slefpos.bean.orderbean.CheckCountResp;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.paybean.PayResultType;
import com.acewill.slefpos.bean.paybean.SelfPosPayResult;
import com.acewill.slefpos.bean.paybean.SmarantRefundReq;
import com.acewill.slefpos.bean.paybean.SyncPayReq;
import com.acewill.slefpos.bean.paybean.SyncPayResult;
import com.acewill.slefpos.bean.paybean.SyncRevokeReq;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.syncbean.syncnumber.SyncOrderNumber;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncPointPayRes;
import com.acewill.slefpos.bean.syncbean.syncpay.SyncQureyPayResultRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.systembean.ErrorType;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.wshbean.CommitWshDealRes;
import com.acewill.slefpos.bean.wshbean.CreateDealRes;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.bean.wshbean.WshAccountCoupon;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SyncConfig;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.helper.ScanGunKeyEventHelper;
import com.acewill.slefpos.kds.KdsUtil;
import com.acewill.slefpos.orderui.main.contract.OrderContract;
import com.acewill.slefpos.orderui.main.exception.SelfPosThrowable;
import com.acewill.slefpos.orderui.main.listener.OnCancleClickListener;
import com.acewill.slefpos.orderui.main.model.OrderModel;
import com.acewill.slefpos.orderui.main.presenter.OrderPresenter;
import com.acewill.slefpos.orderui.main.ui.adapter.OrderDialogAdapter;
import com.acewill.slefpos.orderui.main.ui.dialog.ComfirmDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.ComfirmPayDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.MemberPwdInputDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.NumberInputDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.PayDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.QuanmaEditDialog2;
import com.acewill.slefpos.orderui.main.ui.fragment.BannerFragment;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.orderui.main.uihelper.MemberPayHelper;
import com.acewill.slefpos.orderui.main.uihelper.Refund;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.print.PrintManager;
import com.acewill.slefpos.print.chikenprintlibrary.PosKitchenPrintAdapter;
import com.acewill.slefpos.print.ticketprint.SmarantPrintUtil;
import com.acewill.slefpos.print.ticketprint.SmarantTicketPrintHandler;
import com.acewill.slefpos.utils.httputils.GsonUtils;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.acewill.slefpos.utils.scanutils.QRCodeScanUtil;
import com.eftimoff.androipathview.PathView;
import com.google.gson.Gson;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.TimeUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.security.Md5Security;
import com.jaydenxiao.common.utils.NetSpeed;
import com.jaydenxiao.common.utils.NetSpeedTimer;
import com.jaydenxiao.common.utils.logutil.FileLog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/27 17:51
 * Desc：
 */
public class OrderDetailActivity extends BaseActivity<OrderPresenter, OrderModel> implements
		OrderContract.View, ScanGunKeyEventHelper.OnScanSuccessListener,
		RotateAnimation.InterpolatedTimeListener, MemberPwdInputDialog.OnYesClickListener,
		MemberPwdInputDialog.OnMemCancleClickListener {
	private static final String TAG = "OrderDetailActivity";

	@Bind(R.id.elistview)
	ExpandableListView mExpandableListView;
	@Bind(R.id.edit_input)
	EditText           edit_input;
	@Bind(R.id.orderdetail_takeout_tv)
	TextView           orderdetail_takeout_tv;
	@Bind(R.id.fra_orderdetail_total_count)
	TextView           fra_orderdetail_total_count;
	@Bind(R.id.total_price)
	TextView           total_price;
	@Bind(R.id.orderdetail_pay_tips_layout)
	RelativeLayout     orderdetail_pay_tips_layout;
	@Bind(R.id.tips_image)
	ImageView          tips_image;
	@Bind(R.id.orderdetail_bottom_layout)
	RelativeLayout     orderdetail_bottom_layout;
	@Bind(R.id.orderdetail_pay_success_layout)
	LinearLayout       orderdetail_pay_success_layout;
	@Bind(R.id.orderdetail_pay_layout)
	LinearLayout       orderdetail_pay_layout;
	@Bind(R.id.pay_tips_titile)
	TextView           pay_tips_titile;
	@Bind(R.id.pay_tips_one)
	TextView           tips_one;
	@Bind(R.id.sync_member_layout)
	LinearLayout       sync_member_layout;
	@Bind(R.id.sync_point_layout)
	LinearLayout       sync_point_layout;
	@Bind(R.id.sync_point_rule)
	TextView           sync_point_rule;
	@Bind(R.id.sync_rest_point)
	TextView           sync_rest_point;
	@Bind(R.id.sync_use_point)
	TextView           sync_use_point;
	@Bind(R.id.sync_point_swift)
	ToggleButton       sync_point_swift;
	@Bind(R.id.eat_method_swift)
	ToggleButton       eat_method_swift;
	@Bind(R.id.sync_balance_swift)
	ToggleButton       sync_balance_swift;
	@Bind(R.id.sync_left_balance)
	TextView           sync_left_balance;
	@Bind(R.id.sync_use_banlance)
	TextView           sync_use_banlance;
	@Bind(R.id.sync_use_coupon_name)
	TextView           sync_use_coupon_name;
	@Bind(R.id.pay_test)
	LinearLayout       pay_test;
	@Bind(R.id.sync_coupon_layout)
	RelativeLayout     sync_coupon_layout;
	@Bind(R.id.discount_amount_ly)
	RelativeLayout     discount_amount_ly;
	@Bind(R.id.orderdetail_pay_method_member)
	LinearLayout       orderdetail_pay_method_member;
	@Bind(R.id.sync_use_coupon_value)
	TextView           sync_use_coupon_value;
	@Bind(R.id.discount_amount)
	TextView           discountAmount;
	@Bind(R.id.price_danju)
	TextView           price_danju;

	@Bind(R.id.pathView)
	PathView       pathView;
	@Bind(R.id.order_success_tips)
	TextView       order_success_tips;
	@Bind(R.id.order_success_tips2)
	TextView       order_success_tips2;
	@Bind(R.id.orderdetail_pay_method_wechat)
	LinearLayout   orderdetail_pay_method_wechat;
	@Bind(R.id.orderdetail_pay_method_zhifubao)
	LinearLayout   orderdetail_pay_method_zhifubao;
	@Bind(R.id.meituan_dealids_layout)
	LinearLayout   meituan_dealids_layout;
	@Bind(R.id.meituan_layout)
	LinearLayout   meituan_layout;
	@Bind(R.id.view1)
	View           view1;
	@Bind(R.id.scroll_view)
	ScrollView     scroll_view;
	@Bind(R.id.main_bg)
	FrameLayout    main_bg;
	@Bind(R.id.waidai_cost_ly)
	RelativeLayout waidai_cost_ly;
	@Bind(R.id.waidai_cost_tv)
	TextView       waidai_cost_tv;


	/**
	 * 查询时间和关闭时间需要搞清楚，有时候查询的时候，
	 * 用户还没有开始扫码，这个时候订单是不存在的的，如果只设置30s关闭的话，
	 * 用户开始扫码之后，订单生成了，需要用户输入密码，这个时候才开始重新倒计时
	 */
	private int     closePayTime       = 0;//这个只有在in_pay_progress状态下才会开始计时
	private boolean firstGetInProgress = true;
	private int     timeout            = 60;
	private int     payType            = -1;//这个是用户选择的支付方式，是微信还是支付宝用户点了才知道

	private PayDialog             mPayDialog;
	private QRCodeScanUtil        d2xxUtil;
	private ScanGunKeyEventHelper mScanGunKeyEventHelper;
	private boolean               payInit;
	private BannerFragment        bannerFragment;
	private int queryOrderTime = 0;
	private boolean enableRefresh;


	@OnClick({R.id.iv_back, R.id.orderdetail_pay_method_zhifubao,
			R.id.orderdetail_pay_method_wechat, R.id.orderdetail_pay_method_member,
			R.id.btn_cancel, R.id.pay_test,
			R.id.sync_coupon_layout, R.id.meituan_layout})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.iv_back:
				finish();
				break;

			case R.id.sync_coupon_layout:
				if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
					Intent intent = new Intent(OrderDetailActivity.this, SmarantCouponActivity.class);
					startActivityForResult(intent, 200);
				} else if (SystemConfig.isSyncSystem) {
					Intent intent = new Intent(OrderDetailActivity.this, SyncCouponActivity.class);
					startActivityForResult(intent, 100);
				}

				break;
			case R.id.orderdetail_pay_method_zhifubao:
				if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
					showComfirmPayDialog(PayMethod.ALI);
				} else {
					//扫码支付，弹出二维码
					aliPay();
				}

				break;
			case R.id.orderdetail_pay_method_wechat:
				if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
					showComfirmPayDialog(PayMethod.WECHAT);
				} else {
					//扫码支付，弹出二维码
					wechatPay();
				}
				break;
			case R.id.btn_cancel:
				onPayCancle();
				break;

			case R.id.orderdetail_pay_method_member:

				mPresenter.goLogin();
				//				mPresenter.cxjNewOrder(new Gson()
				//						.toJson(CxjOrderProvider.getInstance().createCxjNewOrderBean()));

				//				showAleartDialog("验券异常", "美团团购套餐id设置不正确,请检查!");

				//				ComfirmDialog dialog = ComfirmDialog.getInstance();
				//				dialog.show(getSupportFragmentManager(), "ComfirmDialog");
				//				connectKDS();
				//				startProgressDialog("正在支付...");
				//				resetPayStatu();
				//				Log.e(TAG, "orderdetail_pay_method_member-resetPayStatu");
				//				String trim = edit_input.getText().toString().trim();
				//				if (TextUtils.isEmpty(trim))
				//					return;
				//				if (trim.startsWith("13"))
				//					payType = PayMethod.WECHAT;
				//				else if (trim.startsWith("28"))
				//					payType = PayMethod.ALI;
				//				mPresenter.syncPay(payType, Order.getInstance()
				//						.createSyncPayRequest(mContext, payType, trim));


				//								connectKDS();
				//				startProgressDialog("正在支付...");

				//会员预交易
				//				mPresenter.wshCreateDeal(MemberPayHelper
				//						.creatDeal(mContext, SmarantDataProvider.getWshAccount(), String
				//										.valueOf(Price.getInstance().getBalance()),
				//								null, String.valueOf(Price.getInstance()
				//										.getPointValue()), Price.getInstance()
				//										.getDishTotalWithMix()));


				//				Log.e(TAG, "onClick-startProgressDialog");
				//				resetPayStatu();
				//				mPresenter.meituanPay(1, Order.getInstance()
				//						.createPayModel(edit_input.getText()
				//								.toString().trim(), 1));
				//				startProgressDialog("正在支付...");

				//				mPresenter.wxPay(SPUtils.getSharedIntData(mContext, "payType"), Order.getInstance()
				//						.createPayModel(edit_input.getText()
				//								.toString().trim(), payType));
				//
				//				payType = PayMethod.ALI;
				//				mPresenter.aliPay(SPUtils.getSharedIntData(mContext, "payType"), Order.getInstance()
				//						.createPayModel(edit_input.getText().toString().trim(), 1));

				//				payType = PayMethod.WECHAT;
				//				resetPayStatu();
				//				mPresenter.wechatPay(SPUtils.getSharedIntData(mContext, "payType"), Order
				//						.getInstance()
				//						.createPayModelNew(edit_input.getText().toString().trim(), payType));
				//				payType = PayMethod.CXJ_WECHAT;
				//				resetPayStatu();
				//				mPresenter.writeTouchText(new Gson()
				//						.toJson(CxjOrderProvider.getInstance().getWriteTouchTextBeanList()));


				break;

			case R.id.pay_test:
				//				wshPay();
				break;
			case R.id.meituan_layout:
				if (Price.getInstance().getDealsValueMap().size() == Cart
						.getInstance()
						.getDealIdCount()) {
					ToastUitl
							.showLong(mContext, "已经达到最大可验券张数" + "，请使用其他支付方式完成支付");
					return;
				}
				showInputQuanMaDialog();
				break;
		}
	}


	private QuanmaEditDialog2 mQuanmaEditDialog;

	/**
	 * 美团验券对话框
	 */
	private void showInputQuanMaDialog() {
		mQuanmaEditDialog = new QuanmaEditDialog2(this);
		resetScanGun();
		mQuanmaEditDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				//				if (mQuanmaEditDialog != null)
				//					mQuanmaEditDialog.dismiss();
				if (mScanGunKeyEventHelper != null) {
					mScanGunKeyEventHelper.analysisKeyEvent(event);
				} else {
					FileLog.log("扫码枪选择端口不正确，需要选择外界扫码枪!");
				}
				return false;
			}
		});
		mQuanmaEditDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				isMeituanQuan = false;
			}
		});
		isMeituanQuan = true;
		mQuanmaEditDialog.show();
		//		mQuanmaEditDialog = QuanmaEditDialog.newInstance();
		//		mQuanmaEditDialog.setOnMemberLoginDialogCancleListener(new OnCancleClickListener() {
		//			@Override
		//			public void onCancleClick() {
		//				if (d2xxUtil != null) {
		//					d2xxUtil.stop();
		//				}
		//			}
		//		});
		//		mQuanmaEditDialog.show(getSupportFragmentManager(), "QuanmaEditDialog");
	}


	/**
	 * 支付宝支付
	 */
	private void aliPay() {

		if (SystemConfig.isSmarantSystem) {
			payType = PayMethod.ALI;
			connectKDS();
		} else if (SystemConfig.isCanXingJianSystem) {
			payType = PayMethod.CXJ_ALI;
			//			mPresenter.writeTouchText(new Gson()
			//					.toJson(CxjOrderProvider.getInstance().getWriteTouchTextBeanList()));
			mPresenter.goLogin();
		} else if (SystemConfig.isSyncSystem) {
			//					resetPayStatu();
			//					mPresenter.syncPay(1, Order.getInstance()
			//							.createSyncPayRequest(mContext, edit_input.getText().toString().trim()));
			payType = PayMethod.ALI;
			goPay();//点击支付宝

		}
	}

	/**
	 * 当会员抵扣金额等于订单金额时,弹出对话框让用户确认
	 */
	private void showComfirmPayDialog(int type) {
		ComfirmPayDialog dialog = ComfirmPayDialog.getInstance(type);
		dialog.show(getSupportFragmentManager(), "ComfirmPayDialog");
	}

	/**
	 * 微信支付
	 */
	private void wechatPay() {

		if (SystemConfig.isSmarantSystem) {
			payType = PayMethod.WECHAT;
			connectKDS();
		} else if (SystemConfig.isCanXingJianSystem) {
			payType = PayMethod.CXJ_WECHAT;
			//			mPresenter.writeTouchText(new Gson()
			//					.toJson(CxjOrderProvider.getInstance().getWriteTouchTextBeanList()));
			mPresenter.goLogin();
		} else if (SystemConfig.isSyncSystem) {
			payType = PayMethod.WECHAT;
			goPay();//点击微信

		}
	}


	/**
	 * 同步时会员支付
	 */
	private void syncMemberPay() {
		if (!Order.getInstance().getPayList().contains(PayMethod.ALI) && !Order.getInstance()
				.getPayList().contains(PayMethod.WECHAT)) {
			resetPayStatu();//同步时会员支付
			Refund.getInstance().clear();//单独使用会员支付的时候，先清空退款想关的参数
			startProgressDialog("正在支付...");
		}
		Log.e(TAG, "syncMemberPay-resetPayStatu");

		if (Price.getInstance().getCouponNo() != null && !Order.getInstance().getPayList()
				.contains(PayMethod.SYNCCOUPON)) {
			mPresenter.syncSingleCouponPay(Order.getInstance()
					.createSyncSingleCouponPay(Price.getInstance().getCouponNo(), SyncDataProvider
							.getSyncMemberAccount().getMemberNo()));
		} else if (Price.getInstance().getPointValue() > 0 && !Order.getInstance().getPayList()
				.contains(PayMethod.SYNCPOINT))
			mPresenter.syncPointPay(Order.getInstance().createSyncPointPay());
		else if (Price.getInstance().getBalance() >= 0 && !Order.getInstance().getPayList()
				.contains(PayMethod.SYNCBALANCE)) {
			mPresenter.syncPay(PayMethod.SYNCBALANCE, Order.getInstance()
					.createSyncPayRequest(mContext, PayMethod.SYNCBALANCE, SyncDataProvider
							.getSyncMemberAccount().getMemberNo()));
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100 & resultCode == 100) {
			sync_point_swift.setChecked(false);
			sync_balance_swift.setChecked(false);
			useSyncCoupon(data);
		} else if (requestCode == 200 & resultCode == 100) {
			sync_point_swift.setChecked(false);
			sync_balance_swift.setChecked(false);
			useWshCoupon(data);
		}
	}

	private void useWshCoupon(Intent data) {
		WshAccountCoupon memberCoupon = (WshAccountCoupon) data
				.getSerializableExtra("memberCoupon");
		List<WshAccountCoupon> coupons = new ArrayList<>();
		coupons.add(memberCoupon);
		Price.getInstance()
				.setWshAccountCouponList(coupons);
		total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		sync_use_coupon_value.setText("-￥" + Price.getInstance().getCouponValue());
		sync_use_coupon_name.setText(memberCoupon.getTitle());
		if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
			showComfirmDialog(AppConstant.COMFIRM_ORDER_A);
			Log.e(TAG, "useSyncCoupon-syncMemberPay");
		}
	}

	private void useSyncCoupon(Intent data) {
		SyncMemberLoginRes.DataBean.MemberCoupon memberCoupon = (SyncMemberLoginRes.DataBean.MemberCoupon) data
				.getSerializableExtra("memberCoupon");
		total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		sync_use_coupon_value.setText("-￥" + Price.getInstance().getCouponValue());
		sync_use_coupon_name.setText(memberCoupon.getCouponName());
		if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
			showComfirmDialog(AppConstant.COMFIRM_ORDER_B);
			Log.e(TAG, "useSyncCoupon-syncMemberPay");
		}
	}

	/**
	 * 当会员抵扣金额等于订单金额时,弹出对话框让用户确认
	 */
	private void showComfirmDialog(int type) {
		ComfirmDialog dialog = ComfirmDialog.getInstance(type);
		dialog.show(getSupportFragmentManager(), "ComfirmDialog");
	}

	/**
	 * 用户触发取消支付， 如刷卡支付的页面，选择餐牌号的界面
	 */
	private void onPayCancle() {
		Common.currentScreentProtectTime = Common.ScreenProtectTime;
		changeToOrderView();
		if (SystemConfig.isCanXingJianSystem) {
			//这里是因为餐行健的在点击支付的时候，如果是会员，那么就会将会员支付方式添加进去了
			//所以这个
			Order.getInstance().getPaymentList().clear();
			mPresenter.closeNewOrder(CxjOrderProvider.getInstance().getOrderIdentity());//取消新建订单
		}
		payInit = false;
		stopDxxUtil();
	}

	/**
	 * 检查支付环境之后真正下单的
	 */
	private void goPay() {
		Refund.getInstance().clear();//使用微信或者是支付宝支付的时候，先清空退款相关的数据
		if (SystemConfig.isSmarantSystem && Price.getInstance().getDealsValueMap()
				.size() > 0 && Float
				.parseFloat(Price.getInstance().getTotal()) == 0) {
			meiTuanQuanPay();//GoPay（）
		} else if (Order.getInstance().isMember() && Order.getInstance()
				.isMemberPay() && Float
				.parseFloat(Price.getInstance().getTotal()) == 0) {
			if (SystemConfig.isSmarantSystem) {
				if (!LoadingDialog.isIsLoading())
					startProgressDialog();//微生活支付确认
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else if (SystemConfig.isSyncSystem) {
				showComfirmDialog(AppConstant.COMFIRM_ORDER_B);
			} else if (SystemConfig.isCanXingJianSystem) {
				//				ToastUitl.showShort(mContext, "餐行健会员支付");
				LoadingDialog.setLoadingText("正在下单...");
				CxjOrderProvider.getInstance().setCheackOutBean("");
				mPresenter
						.checkOut(new Gson().toJson(CxjOrderProvider.getInstance().getCheckBean()));
				//				wshPay();//餐行健会员支付
			}
		} else {
			if (SystemConfig.isSmarantSystem) {
				if (Order.getInstance().isMember() && Order.getInstance()
						.isMemberPay()) {
				} else {
					resetPayStatu();//智慧快餐和餐行健需要进行支付宝或者微信支付
				}
			} else if (SystemConfig.isSyncSystem || SystemConfig.isCanXingJianSystem) {
				resetPayStatu();//同步时需要进行支付宝或者微信支付
			}
			//			else if (SystemConfig.isCanXingJianSystem) {
			//				Order.getInstance()
			//						.createPayment(edit_input.getText().toString().trim(), Price.getInstance()
			//								.getTotal(), String
			//								.valueOf(payType), payType == PayMethod.WECHAT ? "微信支付" : "支付宝支付", Order
			//								.getInstance().getBiz_id(), "");//创建餐行健的payment
			//				mPresenter.checkOut(new Gson()
			//						.toJson(CxjOrderProvider.getInstance().setCheackOutBean(edit_input.getText().toString().trim())));
			//				ToastUitl.showLong(mContext, "餐行健在线支付");
			//			}
			//刷卡支付，跳转界面
			if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
				changeToPayTipView();
				resetScanGun();
			} else {
				//扫码支付，弹出二维码
				doPay("");
			}
		}
	}

	@Override
	public void returnSyncPayResult(SelfPosPayResult result) {
		if (result != null && result.getReslut() == PayResultType.PAY_SUCCESS) {
			SyncPayResult.ResponseBean.ResultBean result1 = result.getSyncPayResult()
					.getResponse().getResult();
			if ("M".equals(result1.getPayMode())) {
				Order.getInstance()
						.createSyncPayment(result1
										.getTotalAmount(), result1.getTxNo(), result1.getTradeNo(),
								result1.getTradeId(), "M", result1
										.getPayPlatform(), result1
										.getAccountNo(), result1.getShopDiscountAmount(), result1
										.getBizNo(), result1.getOutTradeNo());
				/**
				 * 添加支付方式
				 */
				Order.getInstance().getPayList().add(PayMethod.SYNCBALANCE);
				Refund.getInstance().addSyncRevokeParam("M", result1
						.getPayPlatform(), result1.getOutTradeNo());
			} else {
				Order.getInstance()
						.createSyncPayment(result1
										.getTotalAmount(), result1.getTxNo(), result1.getTradeNo(),
								result1.getTradeId(), payType == PayMethod.ALI ? "A" : "W", result1
										.getPayPlatform(), result1
										.getAccountNo(), result1.getShopDiscountAmount(), result1
										.getBizNo(), result1.getOutTradeNo());
				/**
				 * 添加支付方式
				 */
				Order.getInstance().getPayList().add(payType);
				Refund.getInstance()
						.addSyncRevokeParam(payType == PayMethod.ALI ? "A" : "W", result1
								.getPayPlatform(), result1.getOutTradeNo());

			}
			if (Order.getInstance().isMember() && Price.getInstance().needMemberPay()) {
				//组合支付的时候需要进行此操作
				syncMemberPay();//在线支付完成之后会员支付
				Log.e(TAG, "returnSyncPayResult-syncMemberPay");
				FileLog.log("returnSyncPayResult-syncMemberPay");
			} else {
				FileLog.log("returnSyncPayResult-doSyncPaySuccess");
				doSyncPaySuccess();
			}
		} else if ((result != null && result.getReslut() == PayResultType.PAY_TIME_OUT)
				|| (result != null && result.getReslut() == PayResultType.PAY_INPROGRESS)) {
			FileLog.log("returnSyncPayResult-handler.post(queryPayResultRunnable)");
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);
		} else {
			if (result != null) {
				FileLog.log("returnSyncPayResult-result != null_payFail");
				try {
					payFail(result.getSyncPayResult().getResponse().getResult().getFailMsg());
				} catch (Exception e) {
					e.printStackTrace();
					payFail("支付失败");
					FileLog.log("returnSyncPayResult-result != null_payFail_Exception");
				}
			} else {
				FileLog.log("returnSyncPayResult-result== null_payFail");
				payFail("支付失败");
			}
		}
	}

	/**
	 * 同步时退款
	 */
	private void syncRefund() {
		List<SyncRevokeReq> revokeReqList = Refund.getInstance().getSyncRevokeParam();
		if (revokeReqList != null && revokeReqList.size() != 0) {
			for (SyncRevokeReq bean : revokeReqList) {
				int payMethod = -1;
				if (bean.getContent().getPayMode().equals("M")) {
					payMethod = PayMethod.SYNCBALANCE;
				} else if (bean.getContent().getPayMode().equals("W")) {
					payMethod = PayMethod.WECHAT;
				} else if (bean.getContent().getPayMode().equals("A")) {
					payMethod = PayMethod.ALI;
				}
				//退款,不用这个
				//mPresenter.syncRefund(payMethod, Order.getInstance()
				//.createSyncRefundParam(bean));
				//撤销支付
				mPresenter.syncRevoke(payMethod, new Gson().toJson(bean));
			}
		}

		List<SyncAcceptReq.DataBean.PromosBean> promosList = Refund.getInstance()
				.getPromoBeanList();

		for (SyncAcceptReq.DataBean.PromosBean bean : promosList) {
			if ("C".equals(bean.getPromoType())) {
				mPresenter.syncCancelUseCoupon(Order.getInstance().createCancleUseCouponParam());
			} else if ("O".equals(bean.getPromoType())) {
				mPresenter.syncCancelPointRule(Order.getInstance().createCanclePointParam());
			}
		}

		stopProgressDialog();
		changeToOrderView();
		Order.getInstance().getSyncPaymentList().clear();
		Order.getInstance().getPayList().clear();
		showAleartDialog("下单失败", "下单失败,已将支付金额退回到您的账户,请注意查收!");
	}


	private void doSyncPaySuccess() {
		int timestamp = TimeUtil
				.getSecondTimestamp(new Date());
		String data = Order.getInstance()
				.getSyncAcceptData(mContext);
		String sign = Md5Security
				.getMD5(data + timestamp + SyncConfig.SIGN);
		mPresenter.syncAccept(timestamp, sign, data);
	}


	/**
	 * Activity截获按键事件.发给ScanGunKeyEventHelper
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		if (mScanGunKeyEventHelper != null) {
			mScanGunKeyEventHelper.analysisKeyEvent(event);
		} else {
			FileLog.log("扫码枪选择端口不正确，需要选择外界扫码枪!");
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onUsbScanSuccess(String code) {
		TimeConfigure.resetScreenTime();
		FileLog.log("是否初始化>" + payInit);
		if (payInit) {
			payInit = false;
			onScanSuccess(code);
		}
	}

	private void onScanSuccess(String code) {
		FileLog.log("d2xxListener>QRCODE:" + code);
		if (TextUtils.isEmpty(code)) {
			return;
		}
		if (isMeituanQuan) {
			if (mQuanmaEditDialog != null)
				mQuanmaEditDialog.dismiss();
			doMeiTuanYanQuan(code);
		} else {
			if (code.length() != 18) {
				resetScanGun();
				ToastUitl.showLong(mContext, "扫描二维码失败，请重试!");
				return;
			}
			if (payType == -1) {
				ToastUitl.showLong(mContext, "请选择一种支付方式!");
				return;
			}
			doPay(code);
		}
	}

	private boolean isMeituanQuan;

	/**
	 * 支付
	 *
	 * @param code
	 */
	private void doPay(String code) {
		startProgressDialog("正在支付...");
		Log.e(TAG, "onScanSuccess-startProgressDialog");
		if (SystemConfig.isSmarantSystem) {
			if (StoreConfigure.isUseMeiTuan()) {
				mPresenter.meituanPay(SPUtils.getSharedIntData(mContext, "payType"), Order
						.getInstance().createPayModelNew(code.trim(), payType));
			} else if (payType == PayMethod.ALI) {
				mPresenter.aliPay(SPUtils.getSharedIntData(mContext, "payType"), Order.getInstance()
						.createPayModelNew(code
								.trim(), payType));
			} else if (payType == PayMethod.WECHAT) {
				//				mPresenter.wxPay(SPUtils.getSharedIntData(mContext, "payType"), Order.getInstance()
				//						.createPayModel(code
				//								.trim(), payType));
				mPresenter.wechatPay(SPUtils.getSharedIntData(mContext, "payType"), Order
						.getInstance()
						.createPayModelNew(code
								.trim(), payType));
			}
		} else if (SystemConfig.isSyncSystem) {
			if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
				mPresenter.syncPay(payType, Order.getInstance()
						.createSyncPayRequest(mContext, payType, code));
			} else {
				ToastUitl.showLong(mContext, "暂不支持扫码支付...");
			}

		} else if (SystemConfig.isCanXingJianSystem) {
			if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
				Order.getInstance().createPayment(code, Price.getInstance().getTotal(), String
						.valueOf(payType), payType == PayMethod.CXJ_WECHAT ? "微信支付" : "支付宝支付", Order
						.getInstance().getBiz_id(), "");//创建餐行健的payment
				CxjOrderProvider.getInstance().setCheackOutBean(code);
				mPresenter
						.checkOut(new Gson().toJson(CxjOrderProvider.getInstance().getCheckBean()));
			} else {
				ToastUitl.showLong(mContext, "暂不支持扫码支付...");
			}
		}

	}

	/**
	 * 初始化扫码枪
	 */
	private void resetScanGun() {
		if (SPUtils.getSharedIntData(mContext, "payType") == 0) {
			//初始化内置扫码枪
			if (SPUtils.getSharedIntData(this, "scantype") == 0) {
				//初始化扫码枪
				if (d2xxUtil != null) {
					d2xxUtil.startWithRetries(new QRCodeScanUtil.D2xxListner() {
						@Override
						public void d2xxListener(String code) {
							onScanSuccess(code.toString().trim());
						}
					});
				}
			} else {
				//初始化外置扫码枪
				payInit = true;
			}
		}
	}

	/**
	 * 每次支付前重置一些常量
	 */
	private void resetPayStatu() {
		timeout = TimeConfigure.TIMEOUTSTART;
		closePayTime = TimeConfigure.CLOSETIMESTART;//重置查询
		firstGetInProgress = true;//第一次查询到正在支付的状态
		queryOrderTime = TimeConfigure.CLOSETIMESTART;
		Order.getInstance().setBiz_id();
	}

	public void stopDxxUtil() {
		if (d2xxUtil != null)
			d2xxUtil.stop();
	}


	@Override
	public int getLayoutId() {
		return R.layout.activity_orderdetail;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		initFragment(savedInstanceState);

		//		initNetView();
		initPrint();
		initOrderView();
		initMemberLayout();
		initPayMethodLayout();
		initTotalPrice();
		initScanGun();
		initEatMethod();
		initListener();
		initParam();
		debug();
	}

	/**
	 * 初始化重新下單的次數
	 */
	private void initParam() {
		times = 0;
	}

	/**
	 * 显示支付方式,美团优先
	 */
	private void initPayMethodLayout() {
		meituan_layout.setVisibility(Cart
				.getInstance()
				.getDealIdCount() != 0 ? View.VISIBLE : View.GONE);
		view1.setVisibility(Price.getInstance().getDealsValueMap()
				.size() > 0 ? View.VISIBLE : View.GONE);
		if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
			if (StoreConfigure.isUseMeiTuan()) {
				orderdetail_pay_method_wechat
						.setVisibility(View.VISIBLE);
				orderdetail_pay_method_zhifubao
						.setVisibility(View.VISIBLE);
			} else {
				orderdetail_pay_method_wechat
						.setVisibility(StoreConfigure.isUseWechat() ? View.VISIBLE : View.GONE);
				orderdetail_pay_method_zhifubao
						.setVisibility(StoreConfigure.isUseAli() ? View.VISIBLE : View.GONE);
			}
		} else {
			orderdetail_pay_method_wechat
					.setVisibility(SyncDataProvider.canUse("W") ? View.VISIBLE : View.GONE);
			orderdetail_pay_method_zhifubao
					.setVisibility(SyncDataProvider.canUse("A") ? View.VISIBLE : View.GONE);
		}
	}

	private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();


	private NetSpeedTimer mNetSpeedTimer;

	/**
	 * 测网速的
	 */
	private void initNetView() {
		Handler handler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
					case NetSpeedTimer.NET_SPEED_TIMER_DEFAULT:
						String speed = (String) msg.obj;
						//打印你所需要的网速值，单位默认为kb/s
						Log.i(TAG, "current net speed  = " + speed);
						FileLog.log("current net speed  = " + speed);
						break;
					default:
						break;
				}
				return false;
			}
		});
		//创建NetSpeedTimer实例
		mNetSpeedTimer = new NetSpeedTimer(this, new NetSpeed(), handler).setDelayTime(1000)
				.setPeriodTime(2000);
		//在想要开始执行的地方调用该段代码
		mNetSpeedTimer.startSpeedTimer();
	}

	private void initMemberLayout() {
		if (SystemConfig.isSyncSystem && Order.getInstance()
				.isMember() && SyncDataProvider.getSyncMemberAccount() != null) {
			sync_member_layout.setVisibility(View.VISIBLE);
			SyncMemberLoginRes.DataBean.PointRule rule = SyncDataProvider.getSyncMemberAccount()
					.getPointRule();
			if (rule != null) {
				sync_point_rule
						.setText("(注:" +
								rule.getPromoValue() + "积分可抵现1元)");
			} else {
				sync_point_rule.setVisibility(View.GONE);
			}

			sync_rest_point
					.setText("(剩余" + SyncDataProvider.getSyncMemberAccount()
							.getPoint() + "积分)");
			sync_left_balance
					.setText("(剩余" + SyncDataProvider.getSyncMemberAccount().getBalance() + ")");

			sync_point_swift
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							useSyncPoint(isChecked);
						}
					});
			sync_balance_swift
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							useSyncBalance(isChecked);
						}
					});
			sync_balance_swift.setEnabled(SyncDataProvider.getSyncMemberAccount()
					.getBalance() > 0 ? true : false);
			if (rule != null && Float
					.parseFloat(Price.getInstance().getTotal()) < 1 || SyncDataProvider
					.getSyncMemberAccount()
					.getPoint() <= 0) {
				sync_point_layout.setVisibility(View.GONE);
			}
			if (SyncDataProvider.getSyncMemberAccount().getCoupon() == null || SyncDataProvider
					.getSyncMemberAccount().getCoupon().size() == 0)
				sync_coupon_layout.setVisibility(View.GONE);
			else
				sync_use_coupon_name
						.setText("您有" + SyncDataProvider.getSyncMemberCouponsSize() + "张优惠券");
		} else if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && Order
				.getInstance()
				.isMember()) {
			sync_member_layout.setVisibility(View.VISIBLE);

			sync_point_rule
					.setText("(注:1积分可抵现1元)");
			sync_rest_point
					.setText("(剩余" + WshDataProvider.getWshAccount()
							.getCredit() + "积分)");
			sync_left_balance
					.setText("(剩余" + WshDataProvider.getWshAccount().getBalance() + ")");

			sync_point_swift
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							useWshCredit(isChecked);
						}
					});
			sync_balance_swift
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							useWshBalance(isChecked);
						}
					});
			if (Float.parseFloat(Price.getInstance().getTotal()) < 1 || WshDataProvider
					.getWshAccount()
					.getCredit() == 0)
				sync_point_layout.setVisibility(View.GONE);
			if (WshDataProvider.getWshAccount().getCoupons() == null || WshDataProvider
					.getWshAccount().getCoupons().size() == 0)
				sync_coupon_layout.setVisibility(View.GONE);
			else
				sync_use_coupon_name.setText(MemberPayHelper.getCoupons()
						.size() + "张优惠券");
		} else {
			sync_member_layout.setVisibility(View.GONE);
		}

	}

	private void useWshCredit(boolean checked) {
		if (checked) {
			WshAccount account = WshDataProvider.getWshAccount();
			int        credit  = account.getCredit();
			if (credit > Float.parseFloat(Price.getInstance().getTotal())) {
				int temp = (int) Float.parseFloat(Price.getInstance().getTotal());//得到总价格
				Price.getInstance().setPointValue(temp);//记录使用积分抵掉的金额，在购物车中的getTotal要减去这个金额
			} else {
				Price.getInstance().setPointValue(credit);
			}
			sync_use_point.setText("-￥" + Price.getInstance().getPointValue());
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));

			if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
				showComfirmDialog(AppConstant.COMFIRM_ORDER_A);
				Log.e(TAG, "useSyncCoupon-syncMemberPay");
			}
		} else {
			SPUtils.setSharedStringData(mContext, "tempMemberPassword", "");
			Price.getInstance().setPointValue(0);
			sync_use_point.setText("");
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		}
	}

	/**
	 * 微生活交易预览
	 */
	private void wshPay() {
		Refund.getInstance().clear();//微生活预览交易,在微信、支付宝，美团支付之前调用
		resetPayStatu();//微生活 预览支付
		Log.e(TAG, "wshPay-resetPayStatu");
		Order.getInstance().getPayList().add(PayMethod.WSH);
		if (SystemConfig.isCanXingJianSystem) {
			//			mPresenter.wshYjset(CxjOrderProvider.getInstance().createWshYjSetBean());

			mPresenter.cxjPrecheck(new Gson()
					.toJson(CxjOrderProvider.getInstance().createPrecheckBean()));
		} else if (SystemConfig.isSmarantSystem) {
			startProgressDialog();
			mPresenter.wshCreateDeal(MemberPayHelper
					.creatDeal(mContext, WshDataProvider.getWshAccount(), String
									.valueOf(Price.getInstance().getBalance()),
							Price.getInstance().getWshAccountCouponList(), String
									.valueOf(Price.getInstance()
											.getPointValue()), Price.getInstance()
									.getDishTotalWithMix()));
		}
	}

	private void useWshBalance(boolean checked) {
		if (checked) {
			WshAccount account = WshDataProvider.getWshAccount();
			float      balance = account.getBalance();
			if (balance > Float.parseFloat(Price.getInstance().getTotal())) {
				sync_use_banlance.setText("-￥" + Price.getInstance().getTotal());
				Price.getInstance()
						.setBalance(Float.parseFloat(Price.getInstance().getTotal()));
			} else {
				sync_use_banlance.setText("-￥" + balance);
				Price.getInstance().setBalance(balance);
			}
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
			if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
				showComfirmDialog(AppConstant.COMFIRM_ORDER_A);
				Log.e(TAG, "useSyncCoupon-syncMemberPay");
			}
		} else {
			SPUtils.setSharedStringData(mContext, "tempMemberPassword", "");
			Price.getInstance().setBalance(0);
			sync_use_banlance.setText("");
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		}
	}

	private void useSyncBalance(boolean isChecked) {
		if (isChecked) {
			SyncMemberLoginRes.DataBean account = SyncDataProvider.getSyncMemberAccount();
			float                       balance = account.getBalance();
			if (balance > Float.parseFloat(Price.getInstance().getTotal())) {
				sync_use_banlance.setText("-￥" + Price.getInstance().getTotal());
				Price.getInstance()
						.setBalance(Float.parseFloat(Price.getInstance().getTotal()));
			} else {
				sync_use_banlance.setText("-￥" + balance);
				Price.getInstance().setBalance(balance);
			}
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
			if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
				showComfirmDialog(AppConstant.COMFIRM_ORDER_B);
				Log.e(TAG, "useSyncBalance-syncMemberPay");
			}
		} else {
			Price.getInstance().setBalance(0);
			sync_use_banlance.setText("");
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		}
	}

	private void useSyncPoint(boolean isChecked) {
		if (isChecked) {
			SyncMemberLoginRes.DataBean account = SyncDataProvider
					.getSyncMemberAccount();
			SyncMemberLoginRes.DataBean.PointRule rule = account.getPointRule();
			if (rule == null) {
				ToastUitl.showLong(mContext, "积分规则没有设置!");
				return;
			}
			int limit       = rule.getUpperLimit();
			int point       = account.getPoint();
			int canUsePoint = 0;//这个是判断能使用的积分数量
			if (point <= limit || limit == 0) {
				//积分全部使用
				canUsePoint = point;
			} else {
				canUsePoint = limit - point;
			}
			int actulUsePoint = 0;//			实际使用的积分
			int cash          = canUsePoint / rule.getPromoValue();//取商得到积分抵扣的实际金额
			if (cash > Float.parseFloat(Price.getInstance().getTotal())) {
				int temp = (int) Float.parseFloat(Price.getInstance().getTotal());//得到总价格
				actulUsePoint = temp;
			} else {
				actulUsePoint = cash;
			}
			Price.getInstance().setPointValue(actulUsePoint);
			sync_use_point.setText("-￥" + actulUsePoint);
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
			if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
				showComfirmDialog(AppConstant.COMFIRM_ORDER_B);
				Log.e(TAG, "useSyncPoint-syncMemberPay");
			}
		} else {
			Price.getInstance().setPointValue(0);
			sync_use_point.setText("");
			total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		}

	}

	private void initListener() {
		mRxManager.on(AppConstant.WXPAY, new Action1<SelfPosPayResult>() {
			@Override
			public void call(SelfPosPayResult result) {
				returnWeChatPayResult(result);
			}
		});
		mRxManager.on(AppConstant.COMFIRM_PAY, new Action1<Integer>() {
			@Override
			public void call(Integer o) {
				switch (o) {
					case PayMethod.ALI:
						aliPay();
						break;
					case PayMethod.WECHAT:
						wechatPay();
				}
			}
		});
		mRxManager.on(AppConstant.COMFIRM_ORDER, new Action1<Integer>() {
			@Override
			public void call(Integer result) {
				switch (result) {
					case AppConstant.COMFIRM_ORDER_A:
						if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
							if (SystemConfig.isSmarantSystem)
								connectKDS();//使用代金券的时候，刚好满足金额
							else if (SystemConfig.isCanXingJianSystem) {
								startProgressDialog("正在支付...");
								Log.e(TAG, "餐行健会员优惠券支付-startProgressDialog");
								//直接下单
								ToastUitl.showLong(mContext, "餐行健会员优惠券支付");
								//				mPresenter.writeTouchText(new Gson()
								//						.toJson(CxjOrderProvider.getInstance().getWriteTouchTextBeanList()));
								mPresenter.goLogin();
							}
						}
						break;
					case AppConstant.COMFIRM_ORDER_B:
						syncMemberPay();//确认框确认会员支付
						break;

				}
			}
		});
		mRxManager.on(AppConstant.MEITUANYANQUAN, new Action1<String>() {
			@Override
			public void call(String meituanquanid) {
				//美团验券，用户输入券码
				doMeiTuanYanQuan(meituanquanid.trim());
				if (mQuanmaEditDialog != null)
					mQuanmaEditDialog.dismiss();
			}
		});

	}

	/**
	 * 美团验券预览
	 */
	private void doMeiTuanYanQuan(String meituanquanid) {
		mPresenter.validationSetout(meituanquanid);
	}

	private void initFragment(Bundle savedInstanceState) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (savedInstanceState != null) {
			bannerFragment = (BannerFragment) getSupportFragmentManager()
					.findFragmentByTag("bannerFragment");
		} else {
			bannerFragment = new BannerFragment();
			transaction.add(R.id.banner_contain, bannerFragment, "bannerFragment");
		}
		transaction.show(bannerFragment);
		transaction.commit();
	}

	private void initTotalPrice() {
		fra_orderdetail_total_count.setText(String.valueOf(Cart.getInstance().getCartCount()));
		price_danju.setText("￥" + String.valueOf(Price.getInstance().getDishTotalWithMix()));
		total_price.setText("￥" + String.valueOf(Price.getInstance().getTotal()));
		if (SystemConfig.isSyncSystem) {
			if (Price.getInstance().getTotalDiscountAmount() != 0) {
				discount_amount_ly.setVisibility(View.VISIBLE);
				discountAmount.setText("-￥" + PriceUtil
						.formatPrice(Price.getInstance().getTotalDiscountAmount()));
			}
		} else if (SystemConfig.isSmarantSystem) {
			if (Price.getInstance().getActualCost() != null && PriceUtil
					.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
							.getActualCost()).floatValue() != 0) {
				discount_amount_ly.setVisibility(View.VISIBLE);
				discountAmount.setText("-￥" + PriceUtil
						.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
								.getActualCost()).toString());
			}
		}

		if (Price.getInstance().getTotalWaidai_Cost() != 0) {
			waidai_cost_ly.setVisibility(View.GONE);
			waidai_cost_tv.setText("+￥" + PriceUtil
					.formatPrice(Price.getInstance().getTotalWaidai_Cost()));
		}
	}

	private void debug() {
		if (SystemConfig.DEBUG) {
			orderdetail_pay_method_member.setVisibility(View.VISIBLE);
			edit_input.setVisibility(View.VISIBLE);
			pay_test.setVisibility(View.VISIBLE);
		}
	}


	private void initEatMethod() {
		orderdetail_takeout_tv.setText(Order.getInstance().getTakeOutStr());
	}


	private void initScanGun() {
		if (!SystemConfig.DEBUG && SPUtils.getSharedIntData(mContext, "scantype") == 0) {
			d2xxUtil = QRCodeScanUtil.getInstance(mContext);
		} else {
			mScanGunKeyEventHelper = new ScanGunKeyEventHelper(this);
		}
	}

	private OrderDialogAdapter orderDialogAdapter;

	private void initOrderView() {
		mExpandableListView.setGroupIndicator(null);
		orderDialogAdapter = new OrderDialogAdapter(this, Cart.getInstance()
				.getCartDishes(), 0);
		mExpandableListView.setAdapter(orderDialogAdapter);
		orderDialogAdapter.expandedGroup(mExpandableListView);
		eat_method_swift.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				enableRefresh = true;
				float           cX         = orderdetail_takeout_tv.getWidth() / 2.0f;
				float           cY         = orderdetail_takeout_tv.getHeight() / 2.0f;
				RotateAnimation rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
				rotateAnim.setInterpolatedTimeListener(OrderDetailActivity.this);
				rotateAnim.setFillAfter(true);
				orderdetail_takeout_tv.startAnimation(rotateAnim);
			}
		});
	}

	/**
	 * 初始化点餐机小票打印机
	 */
	private void initPrint() {
		if ((HostType.IS_SYNC_DEBUG || HostType.IS_SMARANT_DEBUG) && SPUtils
				.getSharedBooleanData(mContext, "PHONE_TEST")) {
			FileLog.log("手机测试，不去获取打印权限!");
		} else {
			PrintManager.init(this);
			SmarantTicketPrintHandler.getInstance().initdotLint();
		}
	}

	@Override
	public void showLoading(String title) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip(String msg) {

	}

	/**
	 * 查询支付结果的runnable，重复查询结果
	 */
	private Runnable queryPayResultRunnable = new Runnable() {
		@Override
		public void run() {
			if (SystemConfig.isSmarantSystem) {
				if (StoreConfigure.isUseMeiTuan()) {
					mPresenter.queryMeiTuanPayResult(Order.getInstance().getPayModel());
				} else if (payType == PayMethod.ALI)
					mPresenter.queryAliPayResult(Order.getInstance().getPayModel());
				else if (payType == PayMethod.WECHAT) {
					mPresenter.queryWechatPayResult(Order.getInstance().getPayModel());
				}
			} else if (SystemConfig.isSyncSystem) {
				SyncPayReq req       = Order.getInstance().getSyncPayReq();
				int        payMethod = -1;
				if (req.getContent().getPayMode().equals("M")) {
					payMethod = PayMethod.SYNCBALANCE;
				} else if (req.getContent().getPayMode().equals("A")) {
					payMethod = PayMethod.ALI;
				} else if (req.getContent().getPayMode().equals("W")) {
					payMethod = PayMethod.WECHAT;
				}
				mPresenter.syncQueryPayResult(payMethod, Order.getInstance().getSyncQureyPayReq());
			} else if (SystemConfig.isCanXingJianSystem) {
				mPresenter
						.queryCXJPayResult(CxjOrderProvider.getInstance().getOid(), String
								.valueOf(payType), Price.getInstance()
								.getTotal(), CxjOrderProvider.getInstance().getCheckBean()
								.getOrderidentity());
			}
		}
	};

	/**
	 * 支付倒计时
	 */
	private Runnable timeOutRunnable = new Runnable() {
		@Override
		public void run() {
			if (timeout > TimeConfigure.TIMEOUTEND) {
				timeout--;
				Log.e("timeout", timeout + "");
				handler.sendEmptyMessage(BaseHandler.What.ZERO);
				handler.postDelayed(timeOutRunnable, TimeConfigure.TIMEOUTGAP);
			} else {
				closePayDialog();
			}
		}
	};


	/**
	 * 扫码支付和刷卡支付都在这里回调
	 *
	 * @param result
	 */
	@Deprecated
	public void returnWeChatPayResult(SelfPosPayResult result) {
		if (result.getReslut() == PayResultType.PAY_FAIL) {
			stopProgressDialog();
			changeToOrderView();
			ToastUitl.showLong(mContext, result.getMsg());
		} else if (result.getReslut() == PayResultType.PAY_SUCCESS) {
			if (mPayDialog != null)
				mPayDialog.dismiss();
			ToastUitl.showShort(mContext, result.getMsg());
			Order.getInstance()
					.createPayment("", String
							.valueOf(Price.getInstance().getTotal()), String
							.valueOf(PayMethod.WECHAT), "微信支付", Order
							.getInstance().getBiz_id(), "");
			Order.getInstance().getPayList().add(PayMethod.WECHAT);
			if (Price.getInstance().getDealsValueMap().size() > 0) {
				meiTuanQuanPay();//微信扫码和刷卡支付回调
			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				FileLog.log("returnWeChatPayResult>" + "commitWshDeal");
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//微信支付成功后下单
			}
		} else if (result.getReslut() == PayResultType.PAY_INPROGRESS) {
			stopProgressDialog();
			showPayDialog(result.getContent());
			Log.e(TAG, "returnWeChatPayResult-stopProgressDialog");
			//			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);
		} else if (result.getReslut() == PayResultType.USER_PAYING) {
			LoadingDialog.setLoadingText("请输入支付密码!");
		}
	}

	@Override
	public void returnAliShuaKaResult(SelfPosPayResult result) {
		doOnLineShuaKaResult(result, PayMethod.ALI, "支付宝支付");
		//		if (result
		//				.getReslut() == PayResultType.PAY_INPROGRESS) {
		//			LoadingDialog.setLoadingText("请输入支付密码!");
		//			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);//支付宝刷卡支付查询
		//		} else if (result.getReslut() == PayResultType.PAY_UNKNOW) {
		//			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);//支付宝刷卡支付查询
		//		} else if (result.getReslut() == PayResultType.PAY_SUCCESS) {
		//			ToastUitl.showShort(mContext, result.getMsg());
		//			Order.getInstance()
		//					.createPayment("", String
		//							.valueOf(Price.getInstance().getTotal()), String
		//							.valueOf(PayMethod.ALI), "支付宝支付", Order
		//							.getInstance().getBiz_id(), "");
		//			Order.getInstance().getPayList().add(PayMethod.ALI);
		//			if (Price.getInstance().getDealsValueMap().size() > 0) {
		//				meiTuanQuanPay();//支付宝刷卡支付
		//			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
		//				FileLog.log("returnAliShuaKaResult>" + "commitWshDeal");
		//				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
		//						.getSharedStringData(mContext, "tempMemberPassword"));
		//			} else {
		//				mPresenter.getNewOrderId();//支付宝刷卡成功下单
		//			}
		//		} else if (result.getReslut() == PayResultType.PAY_FAIL) {
		//			stopProgressDialog();
		//			changeToOrderView();
		//			ToastUitl.showLong(mContext, result.getContent());
		//		}
	}

	@Override
	public void returnWechatShuaKaResult(SelfPosPayResult result) {
		doOnLineShuaKaResult(result, PayMethod.WECHAT, "微信支付");
	}

	private void doOnLineShuaKaResult(SelfPosPayResult result, int payType, String payTypeName) {
		if (result.getReslut() == PayResultType.PAY_FAIL) {
			stopProgressDialog();
			changeToOrderView();
			ToastUitl.showLong(mContext, result.getContent());
		} else if (result.getReslut() == PayResultType.PAY_SUCCESS) {
			Order.getInstance()
					.createPayment("", String
							.valueOf(Price.getInstance().getTotal()), String
							.valueOf(payType), payTypeName, Order
							.getInstance().getBiz_id(), "");
			Order.getInstance().getPayList().add(PayMethod.WECHAT);
			Refund.getInstance().addSmarantRevokeParam(String
					.valueOf(Price.getInstance().getTotal()), Order
					.getInstance().getBiz_id(), String
					.valueOf(payType));
			if (Price.getInstance().getDealsValueMap().size() > 0) {
				meiTuanQuanPay();//微信刷卡支付完成之后
			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				FileLog.log("doOnLineShuaKaResult>" + "commitWshDeal");
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//支付宝刷卡成功下单
			}
		} else if (result
				.getReslut() == PayResultType.USER_PAYING) {
			LoadingDialog.setLoadingText("请输入支付密码!");
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);//微信刷卡支付查询
		} else if (result.getReslut() == PayResultType.PAY_UNKNOW || result
				.getReslut() == PayResultType.PAY_TIME_OUT) {
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);//微信刷卡支付查询
		} else if (result.getReslut() == PayResultType.PAY_ERROR) {
			stopProgressDialog();
			showAleartDialog("服务器异常", "服务器异常,请重试!");
		}
	}


	/**
	 * 千万不要在ondestrory调用此方法
	 */
	private void closePayDialog() {
		if (mPayDialog != null)
			mPayDialog.dismiss();
	}

	/**
	 * 交易关闭
	 */
	public void tradeClose() {
		handler.removeCallbacks(timeOutRunnable);
		handler.removeCallbacks(queryPayResultRunnable);
	}

	@Override
	public void returnQueryAliPayResult(SelfPosPayResult result) {
		doOnLineQueryPayResult(result, PayMethod.ALI, "支付宝支付");
	}


	public void changeToOrderView() {
		orderdetail_bottom_layout.setVisibility(View.VISIBLE);
		orderdetail_pay_layout.setVisibility(View.VISIBLE);
		orderdetail_pay_tips_layout.setVisibility(View.GONE);
		orderdetail_pay_success_layout.setVisibility(View.GONE);
	}

	public void changeToPayTipView() {
		orderdetail_pay_layout.setVisibility(View.GONE);
		Drawable image = null;
		if (payType == PayMethod.ALI || payType == PayMethod.CXJ_ALI) {
			image = getResources()
					.getDrawable(R.mipmap.zfb_process);
			pay_tips_titile.setText(getResources().getString(R.string.ali_pay_tips));
			tips_one.setText(getResources().getString(R.string.pay_tip1));
		} else if (payType == PayMethod.WECHAT || payType == PayMethod.CXJ_WECHAT) {
			image = getResources()
					.getDrawable(R.mipmap.wechat_process);
			pay_tips_titile.setText(getResources().getString(R.string.wechat_pay_tips));
			tips_one.setText(getResources().getString(R.string.pay_tip11));
		}
		tips_image.setImageDrawable(image);
		orderdetail_pay_tips_layout.setVisibility(View.VISIBLE);
		orderdetail_pay_success_layout.setVisibility(View.GONE);
		orderdetail_bottom_layout.setVisibility(View.GONE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/**
		 * 因为我不会主动的去关闭扫码支付的窗口，也不会主动的停止去执行查询订单的操作；
		 * 所以，扫码支付的窗口会在点餐机倒计时的时候才会关闭，所以当页面关闭的时候，就要去关闭他们
		 */
		tradeClose();//Ondestroy()
		stopScanGun();
		Order.getInstance().clearFavor();
		Order.getInstance().getSyncPaymentList().clear();
		Order.getInstance().getPaymentList().clear();
		Order.getInstance().getPayList().clear();
		Price.getInstance().setDealsValueMap(null);
		Order.getInstance().setDealsMap(null);
		SPUtils.setSharedStringData(mContext, "tempMemberPassword", "");
	}

	private void stopNetView() {
		if (null != mNetSpeedTimer) {
			mNetSpeedTimer.stopSpeedTimer();
		}
	}

	/**
	 * 停止扫码枪的监听
	 */
	public void stopScanGun() {
		if (d2xxUtil != null) {
			d2xxUtil.resetListener();// stop to call
			d2xxUtil.stopThread();
		}
	}

	@Override
	public void returnALiSaoMaResult(SelfPosPayResult result) {
		doOnLineSaoMaResult(result);
	}

	private void doOnLineSaoMaResult(SelfPosPayResult result) {
		if (result.getReslut() == PayResultType.PAY_INPROGRESS) {
			showPayDialog(result.getContent());
			stopProgressDialog();
			Log.e(TAG, "doOnLineSaoMaResult-stopProgressDialog");
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMESTART);//查询阿里支付扫码结果
		} else {
			stopProgressDialog();
			Log.e(TAG, "doOnLineSaoMaResult-stopProgressDialog2");
			ToastUitl.showLong(mContext, new Gson().toJson(result));
		}
	}

	@Override
	public void returnWechatSaoMaResult(SelfPosPayResult result) {
		doOnLineSaoMaResult(result);
	}

	@Override
	public void returnQueryWechatPayResult(SelfPosPayResult result) {
		doOnLineQueryPayResult(result, PayMethod.WECHAT, "微信支付");
	}

	/**
	 * 支付宝和微信的查询回调都到这里来
	 * 通用回调方法
	 *
	 * @param result
	 * @param payType
	 * @param payTypeName
	 */
	private void doOnLineQueryPayResult(SelfPosPayResult result, int payType, String payTypeName) {
		if (result.getReslut() == PayResultType.PAY_FAIL) {
			ToastUitl.showLong(mContext, result.getContent());
			closePayDialog();
			tradeClose();
		} else if ((result
				.getReslut() == PayResultType.USER_PAYING || result
				.getReslut() == PayResultType.PAY_INPROGRESS || result
				.getReslut() == PayResultType.PAY_UNKNOW) && closePayTime < TimeConfigure.CLOSETIMEEND) {
			if (result.getReslut() == PayResultType.PAY_INPROGRESS && firstGetInProgress) {
				//扫码支付的时候会回调 PAY_INPROGERESS, 刷卡支付不会
				firstGetInProgress = false;
				handler.postDelayed(timeOutRunnable, TimeConfigure.TIMEOUTGAP);
			}
			if (result.getReslut() == PayResultType.USER_PAYING) {
				if (mPayDialog != null) {
					mPayDialog.setTips("请输入支付密码");
				} else
					LoadingDialog.setLoadingText("请输入支付密码");
			}
			TimeConfigure.resetScreenTime();//这个时候也要重置倒计时
			closePayTime++;
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);//查询阿里支付结果
		} else if ((result
				.getReslut() == PayResultType.PAY_INPROGRESS || result
				.getReslut() == PayResultType.USER_PAYING || result
				.getReslut() == PayResultType.PAY_UNKNOW) && closePayTime >= TimeConfigure.CLOSETIMEEND) {
			handler.removeCallbacks(timeOutRunnable);
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);//查询阿里支付结果
		} else if (result.getReslut() == PayResultType.PAY_TIME_OUT) {
			//服务器回调的 timeOut
			Log.e(TAG, "服务器回调的 timeOut");
			FileLog.log("服务器回调的 timeOut");
			handler.removeCallbacks(timeOutRunnable);
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);//查询阿里支付结果
			//			tradeClose();//查询之间到了，不查询了
		} else if (result.getReslut() == PayResultType.PAY_ERROR) {
			//服务器异常
			Log.e(TAG, "服务器异常回调的 PAY_ERROR");
			FileLog.log("服务器异常回调的 PAY_ERROR");
			closePayDialog();
			tradeClose();
			showAleartDialog("服务器异常", "服务器异常,请重试!");
		} else {
			// TODO: 2018/5/10 anch 不确定支付是否成功
			if (SPUtils.getSharedIntData(mContext, "payType") == 1) {
				startProgressDialog("正在下单...");//扫码支付需要
			}
			Order.getInstance()
					.createPayment("", String
							.valueOf(Price.getInstance().getTotal()), String
							.valueOf(payType), payTypeName, Order
							.getInstance().getBiz_id(), "");
			Order.getInstance().getPayList().add(PayMethod.WECHAT);
			Refund.getInstance().addSmarantRevokeParam(String
					.valueOf(Price.getInstance().getTotal()), Order
					.getInstance().getBiz_id(), String
					.valueOf(payType));
			if (Price.getInstance().getDealsValueMap().size() > 0) {
				meiTuanQuanPay();//支付宝查询支付结果回调
			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				FileLog.log("returnQueryWechatPayResult>" + "commitWshDeal");
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//查询支付宝刷卡支付成功后下单
			}
			closePayDialog();
			tradeClose();//微信支付完成了
		}
	}


	@Override
	public void returnMeiTuanMicroPayResult(SelfPosPayResult result) {
		if (result.getReslut() == PayResultType.PAY_SUCCESS) {
			ToastUitl.showShort(mContext, result.getMsg());
			Order.getInstance()
					.createPayment("", String
							.valueOf(Price.getInstance().getTotal()), String
							.valueOf(PayMethod.MEITUAN), "美团支付", Order
							.getInstance().getBiz_id(), "");
			Order.getInstance().getPayList().add(PayMethod.MEITUAN);
			Refund.getInstance()
					.addSmarantRevokeParam(Price.getInstance().getTotal(), Order.getInstance()
							.getBiz_id(), String.valueOf(PayMethod.MEITUAN));
			if (Price.getInstance().getDealsValueMap().size() > 0) {
				meiTuanQuanPay();//美团刷卡支付
			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				FileLog.log("returnMeiTuanMicroPayResult>" + "commitWshDeal");
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//美团支付成功后下单
			}
		} else if (result.getReslut() == PayResultType.PAY_INPROGRESS) {
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEEND);
		} else {
			stopProgressDialog();
			Log.e(TAG, "returnMeiTuanMicroPayResult-stopProgressDialog2");
			changeToOrderView();
			ToastUitl.showShort(mContext, result.getReslut() + "," + result.getContent());
		}
	}

	@Override
	public void returnMeiTuanSaomaPayResult(SelfPosPayResult result) {
		stopProgressDialog();
		if (result != null) {
			if (result.getReslut() == PayResultType.PAY_INPROGRESS) {
				showPayDialog(result.getContent());
				handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEEND);
			} else {
				Log.e(TAG, "returnMeiTuanSaomaPayResult1");
			}
		} else {
			Log.e(TAG, "returnMeiTuanSaomaPayResult2");
		}
	}

	@Override
	public void returnQueryMeiTuanPayResult(SelfPosPayResult result) {
		if (result.getReslut() == PayResultType.PAY_SUCCESS) {
			ToastUitl.showShort(mContext, result.getMsg());
			closePayDialog();
			Order.getInstance()
					.createPayment("", String
							.valueOf(Price.getInstance().getTotal()), String
							.valueOf(PayMethod.MEITUAN), "美团支付", Order
							.getInstance().getBiz_id(), "");
			Order.getInstance().getPayList().add(PayMethod.MEITUAN);
			Refund.getInstance()
					.addSmarantRevokeParam(Price.getInstance().getTotal(), Order.getInstance()
							.getBiz_id(), String.valueOf(PayMethod.MEITUAN));
			if (Price.getInstance().getDealsValueMap().size() > 0) {
				meiTuanQuanPay();//查询美团支付结果回调
			} else if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				FileLog.log("returnQueryMeiTuanPayResult>" + "commitWshDeal");
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//查询美团支付刷卡支付成功后下单
			}
		} else if (result.getReslut() == PayResultType.PAY_INPROGRESS) {
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);
		} else {
			closePayDialog();
			stopProgressDialog();
			Log.e(TAG, "returnQueryMeiTuanPayResult-stopProgressDialog");
			ToastUitl.showShort(mContext, result.getReslut() + "," + result.getContent());
		}
	}

	@Override
	public void returnNewOrderIdResutl(int orderId) {
		if (orderId != -1) {
			Order.getInstance().setOrderId(orderId);
			LoadingDialog.setLoadingText("正在下单...");
			mPresenter.pushOrder(Order.getInstance()
					.createNewOrderReq(orderId, null));
		} else {
			stopProgressDialog();
			Log.e(TAG, "returnNewOrderIdResutl-stopProgressDialog");
			changeToOrderView();
			for (SmarantRefundReq refundReq : Refund.getInstance().getSmarantRevokeParam()) {
				mPresenter.backOut(refundReq);//获取订单号失败
			}
		}
	}


	@Override
	public void returnQueryOrderResult(final NewOrderRes orderRes) {
		if (orderRes != null && orderRes.getContent() != null) {
			handler.removeCallbacks(queryOrderRunnable);
			queryOrderTime = 0;
			Log.e(TAG, "returnQueryOrderResult-stopProgressDialog");
			if (SmarantDataProvider.getSelfposConfigurationdata().getContent().isInformKDS()) {
				mPresenter.notifyKDS(KdsUtil.getInstance().getKdsOrderBaen(orderRes));
			}
			if (!TextUtils.isEmpty(StoreConfigure.getJyjAddress())) {
				mPresenter.pushOrderToJYJ(Order.getInstance()
						.createNewOrderReq(Integer.parseInt(orderRes.getContent().getId()), orderRes
								.getContent().getCallNumber()));
			} else {
				stopProgressDialog();
				showPaySuccessView(orderRes.getContent().getCallNumber());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							SmarantTicketPrintHandler.getInstance()
									.printSmarantTicket(mContext, orderRes);
							PosKitchenPrintAdapter
									.printChikenTicket(mContext, orderRes.getContent()
											.getId(), orderRes
											.getContent().getCallNumber());
						} catch (Exception e) {
							FileUtil.saveCrashInfo2File(e);
						}
					}
				}).start();
			}
		} else {
			if (queryOrderTime < 3) {
				Log.e(TAG, "查询订单的次数>" + queryOrderTime);
				queryOrder();
			} else {
				//下单失败,退款，这里可以再次下单的，但是为了避免重复下单，还是不要去再次下单而是去退款
				handler.removeCallbacks(queryOrderRunnable);
				queryOrderTime = 0;
				smarantRefund();
			}
		}
	}

	private void smarantRefund() {
		for (SmarantRefundReq refundReq : Refund.getInstance().getSmarantRevokeParam()) {
			mPresenter.backOut(refundReq);//获取订单号失败
		}
		stopProgressDialog();
		changeToOrderView();
		Order.getInstance().getSyncPaymentList().clear();
		Order.getInstance().getPayList().clear();
		Refund.getInstance().clear();
		showAleartDialog("下单失败", "下单失败,已将支付金额退回到您的账户,请注意查收!");
	}


	@Override
	public void returnBackOutResult(boolean success, String msg) {

	}

	@Override
	public void returnPushOrderToJYJ(final NewOrderRes orderRes) {
		stopProgressDialog();
		Log.e(TAG, "returnPushOrderToJYJ-stopProgressDialog");
		if (orderRes != null) {
			showPaySuccessView(orderRes.getContent().getCallNumber());
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						SmarantTicketPrintHandler.getInstance()
								.printSmarantTicket(mContext, orderRes);
						PosKitchenPrintAdapter
								.printChikenTicket(mContext, orderRes.getContent().getId(), orderRes
										.getContent().getCallNumber());
					} catch (Exception e) {
						FileUtil.saveCrashInfo2File(e);
					}
				}
			}).start();
		} else {
			Log.e(TAG, "returnPushOrderToJYJ>" + "null");
			//// TODO: 2018/5/26 anch 提示下单到吉野家异常
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						SmarantTicketPrintHandler.getInstance().printSmarantTicket(mContext, null);
					} catch (Exception e) {
						FileUtil.saveCrashInfo2File(e);
					}
				}
			}).start();

			//下单失败的界面
			changeToStartView();
		}
	}

	@Override
	public void returnSyncPointPayResult(SyncPointPayRes orderRes) {
		if (orderRes != null) {
			Order.getInstance().setMemberPointLogOuid(orderRes.getData().getMemberPointLogOuid());
			Order.getInstance().getPayList().add(PayMethod.SYNCPOINT);
			Refund.getInstance().createPromoBean("O");
			if (Price.getInstance().getBalance() > 0 && !Order.getInstance().getPayList()
					.contains(PayMethod.SYNCBALANCE)) {
				mPresenter.syncPay(PayMethod.SYNCBALANCE, Order.getInstance()
						.createSyncPayRequest(mContext, PayMethod.SYNCBALANCE, SyncDataProvider
								.getSyncMemberAccount().getMemberNo()));
			} else {
				LoadingDialog.setLoadingText("正在下单");
				doSyncPaySuccess();
			}
		} else {
			syncRefund();//使用积分失败退款
		}
	}


	@Override
	public void returnSyncMemberPayResult() {

	}

	/*
		查询订单是否存在，如果存在的话就退单，如果不存在就退款
    */
	private void queryOrder() {
		queryOrderTime++;
		handler.postDelayed(queryOrderRunnable, 3000);
	}

	private Runnable queryOrderRunnable = new Runnable() {
		@Override
		public void run() {
			mPresenter.queryOrderById(Order.getInstance().getOrderId());
		}
	};

	@Override
	public void returnNotifyKDSResult(boolean isOk) {
		// TODO: 2018/5/22 anch 无需调用stopProgressDialog();
		//		stopProgressDialog();
		//		Log.e(TAG,"returnNotifyKDSResult-stopProgressDialog");
		if (TextUtils.isEmpty(StoreConfigure.getJyjAddress()))
			stopProgressDialog();
		if (isOk) {
		} else {
			showAleartDialog("发生异常", "KDS下单异常,请与管理员联系!");
		}
	}


	@Override
	public void kdsConnectOk(boolean isOK) {
		if (isOK) {
			mPresenter.checkDishCounts(Cart.getInstance().getCheckDishStr());
		} else {
			stopProgressDialog();
			showAleartDialog("发生异常", "KDS连接异常,请联系工作人员!");
			//			Log.e(TAG, "kdsConnectOk-stopProgressDialog");
			//			ToastUitl.showLong(mContext, "KDS连接异常!");
		}
	}

	@Override
	public void returnCheckDishCountResult(CheckCountResp response) {
		stopProgressDialog();
		Log.e(TAG, "returnCheckDishCountResult-stopProgressDialog");
		if (response.getResult() != 0) {
			ToastUitl.showLong(mContext, response.getErrmsg());
			return;
		}
		if (response.getDishIDData() != null
				&& response.getDishIDData().size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < response.getDishIDData().size(); i++) {
				CheckCountResp.CheckDish dish = response.getDishIDData().get(i);
				UIDish dishModel = UIDataProvider
						.getDishByDishId(dish.getDishid());
				if (i != response.getDishIDData().size()) {
					builder.append(dishModel.getDishName() + "剩余 " + dish
							.getCount() + "  " + dishModel.getDishUnit() + ",");
				} else {
					builder.append(dishModel.getDishName() + "剩余 " + dish
							.getCount() + " " + dishModel.getDishUnit());
				}
				//自动删除用户选择的商品
				//						Cart.getInstance().deleteDish(dish.dishid);
			}
			builder.append("，请重新点餐");
			ToastUitl.showShort(mContext, builder.toString());
			//记录售罄的商品
			return;
		}

		if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
			wshPay();//微生活 支付预览，判断是否需要输入密码
		} else {
			checkShowTable();//判断是否需要显示桌台,后台判断菜品是否足够，足够了
		}

	}


	/**
	 * 是否需要选择桌台
	 */
	private void checkShowTable() {
		if (SPUtils.getSharedIntData(mContext, "SHOWTABLE") == 1) {
			stopProgressDialog();
			//显示桌台
			final NumberInputDialog dialog = NumberInputDialog
					.newInstance(Common.tableNumberInput);
			dialog.setOnComfirmListener(new NumberInputDialog.OnConfirmListener() {
				@Override
				public void onConfirm(String tableNo) {
					Order.getInstance().setTableId(tableNo);
					goPay();//选好桌台，开始支付
					//					showComfirmDialog(payType);
				}
			});
			dialog.setOnCancleListener(new NumberInputDialog.OnCancleListener() {
				@Override
				public void onCancle() {
					//取消选择桌台
					dialog.dismiss();
				}
			});
			dialog.setOnDialogDismiss(new NumberInputDialog.OnDialogDismissListener() {
				@Override
				public void onDialogDismiss() {
				}
			});
			dialog.show(getSupportFragmentManager(), "NumberInputDialog");
		} else {
			goPay();//不显示桌台，直接支付

		}
	}


	@Override
	public void returnPushOrderResult(final NewOrderRes orderRes) {
		if (orderRes != null) {
			Log.e(TAG, "returnPushOrderResult-stopProgressDialog");
			//		mRxManager.post(AppConstant.ORDERSUCCESS, new OrderSuccessEvent(orderRes));
			if (SmarantDataProvider.getSelfposConfigurationdata().getContent().isInformKDS()) {
				mPresenter.notifyKDS(KdsUtil.getInstance().getKdsOrderBaen(orderRes));
			}
			if (!TextUtils.isEmpty(StoreConfigure.getJyjAddress())) {
				mPresenter.pushOrderToJYJ(Order.getInstance()
						.createNewOrderReq(Integer.parseInt(orderRes.getContent().getId()), orderRes
								.getContent().getCallNumber()));
			} else {
				stopProgressDialog();
				showPaySuccessView(orderRes.getContent().getCallNumber());//智慧快餐下单成功
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							SmarantTicketPrintHandler.getInstance()
									.printSmarantTicket(mContext, orderRes);
							SmarantPrintUtil.setPrintList(orderRes);
							PosKitchenPrintAdapter
									.printChikenTicket(mContext, orderRes.getContent()
											.getId(), orderRes
											.getContent().getCallNumber());
						} catch (Exception e) {
							FileUtil.saveCrashInfo2File(e);
						}
					}
				}).start();
			}
		} else {
			//查询下单的结果
			// TODO: 2018/5/22 anch 无需startProgressDialog（）；
			//			startProgressDialog();
			//			Log.e(TAG, "returnPushOrderResult-startProgressDialog");
			LoadingDialog.setLoadingText("正在查询订单状态...");
			queryOrder();
		}
	}


	@Override
	public void returnSyncAccept(final SyncAcceptRes res) {

		Log.e(TAG, "returnSyncAccept-stopProgressDialog");
		if (res != null) {
			if (res.getCode() == 200) {
				//				syncRefund();//测试退款
				stopProgressDialog();
				FileLog.log("下单成功!");
				showPaySuccessView("#" + Order.getInstance().getOrderSeq());
				if ((HostType.IS_SYNC_DEBUG || HostType.IS_SMARANT_DEBUG) && SPUtils
						.getSharedBooleanData(mContext, "PHONE_TEST")) {
					FileLog.log("手机测试，不去获取打印权限!");
				} else {
					SmarantTicketPrintHandler.getInstance()
							.printSyncTicket(mContext, Order.getInstance()
									.getPrintOrder(res.getData().getOrdersNo()));
				}
			} else if (res.getCode() == 502) {
				//再次发起下单
				if (times < 12) {
					TimeConfigure.resetScreenTime();
					handler.sendEmptyMessageDelayed(AppConstant.SYNC_RE_ACCEPT, 5000);
				} else {
					//退款
					syncRefund();// 下单重试12次失败后退款
					FileLog.log("下单失败3!");
				}
			} else {
				//退款
				syncRefund();// 下单后台返回订单数据异常，退款

				FileLog.log("下单失败2!");
			}
		} else {
			syncRefund();//未知异常退款

			//退款
			Log.e(TAG, "下单失败!");
			FileLog.log("下单失败!");
		}
	}


	private void sendOrder() {

	}

	/**
	 * 下单成功后跳转
	 */
	private void changeToStartView() {
		TimeConfigure.CURRENTSCREENPRPOTECTTIME = -1;//屏保倒计时时间
		startActivity(new Intent(OrderDetailActivity.this,
				EatMethodActivity.class));
		finish();
	}

	/**
	 * 显示下单成功的界面
	 */
	private void showPaySuccessView(String callId) {
		//保存生成的这个
		SyncOrderNumber number1 = new SyncOrderNumber();
		number1.setOrderreq(Order.getInstance().getOrderseq_i());
		number1.save();
		orderdetail_pay_tips_layout.setVisibility(View.GONE);
		orderdetail_pay_success_layout.setVisibility(View.VISIBLE);
		orderdetail_bottom_layout.setVisibility(View.GONE);
		StringBuilder builder = new StringBuilder();
		String        tips    = "";
		if (SPUtils.getSharedIntData(mContext, "SHOWTABLE") == 1) {
			//显示桌台
			tips = "餐牌号 ";
			callId = Order.getInstance().getTableId();
			order_success_tips2
					.setText(getResources().getString(R.string.showtable_paysuccess_tips));
		} else {
			tips = "您的取餐号为 ";
		}

		builder.append(tips);
		builder.append(callId);
		SpannableStringBuilder style = new SpannableStringBuilder(builder.toString());
		style.setSpan(new RelativeSizeSpan(1.5f), tips.length(), builder.toString()
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		style.setSpan(new ForegroundColorSpan(getResources()
				.getColor(R.color.colorPrimary)), tips.length(), builder.toString()
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		order_success_tips.setText(style);
		scroll_view.smoothScrollTo(0, 0);
		main_bg.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
		//播放下单成功音效
		BaseApplication.getInstance().playSound();
		//播放下单成功动画
		pathView.getPathAnimator().
				//pathView.getSequentialPathAnimator().
						delay(100).
				duration(1500).
				interpolator(new AccelerateDecelerateInterpolator()).
				start();
		//设置5秒后退出下单成功页面
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				changeToStartView();
			}
		}, TimeConfigure.ORDER_SUCCESS_TIME);
	}


	@Override
	public void returnQuerySyncPayResult(SelfPosPayResult result) {
		if (result != null && result.getReslut() == PayResultType.PAY_SUCCESS) {
			FileLog.log("returnQuerySyncPayResult1");
			SyncQureyPayResultRes.ResponseBean.ResultBean.TradeBean result1 = result
					.getSyncQureyPayResult()
					.getResponse().getResult().getTrade();
			if ("M".equals(result1.getPayMode())) {
				Order.getInstance()
						.createSyncPayment(result1
										.getTotalAmount(), result1.getTxNo(), result1.getTradeNo(),
								result1.getTradeId(), "M", result1
										.getPayPlatform(), result1
										.getAccountNo(), result1.getShopDiscountAmount(), result1
										.getBizNo(), result1.getOutTradeNo());
				/**
				 * 添加支付方式
				 */
				FileLog.log("returnQuerySyncPayResult2");
				Order.getInstance().getPayList().add(PayMethod.SYNCBALANCE);
				Refund.getInstance().addSyncRevokeParam("M", result1
						.getPayPlatform(), result1.getOutTradeNo());
			} else {
				Order.getInstance()
						.createSyncPayment(result1
										.getTotalAmount(), result1.getTxNo(), result1.getTradeNo(),
								result1.getTradeId(), payType == PayMethod.ALI ? "A" : "W", result1
										.getPayPlatform(), result1
										.getAccountNo(), result1.getShopDiscountAmount(), result1
										.getBizNo(), result1.getOutTradeNo());
				/**
				 * 添加支付方式
				 */
				FileLog.log("returnQuerySyncPayResult3");
				Order.getInstance().getPayList().add(payType);
				Refund.getInstance()
						.addSyncRevokeParam(payType == PayMethod.ALI ? "A" : "W", result1
								.getPayPlatform(), result1.getOutTradeNo());
			}
			if (Order.getInstance().isMember() && Price.getInstance().needMemberPay()) {
				//组合支付的时候需要进行此操作
				syncMemberPay();//在线支付查询支付成功后使用会员支付
				FileLog.log("returnQuerySyncPayResult-syncMemberPay");
				Log.e(TAG, "returnQuerySyncPayResult-syncMemberPay");
			} else {
				FileLog.log("returnQuerySyncPayResult-doSyncPaySuccess");
				doSyncPaySuccess();
			}
		} else if ((result != null && result
				.getReslut() == PayResultType.PAY_INPROGRESS) || (result != null && result
				.getReslut() == PayResultType.PAY_UNKNOW) || (result != null && result
				.getReslut() == PayResultType.PAY_TIME_OUT)) {
			FileLog.log("returnQuerySyncPayResult-closePayDialog");
			closePayDialog();
			handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);//查询阿里支付结果
		} else {
			FileLog.log("returnQuerySyncPayResult-payFail");
			if (result != null)
				payFail(result.getMsg());
			else
				payFail("支付失败!");
		}
	}

	private void payFail(String msg) {
		stopProgressDialog();
		changeToOrderView();
		handler.removeCallbacks(queryPayResultRunnable);
		ToastUitl.showLong(mContext, msg);
	}


	public void connectKDS() {
		if (SmarantDataProvider.getSelfposConfigurationdata().getContent().isInformKDS()) {
			if (KdsUtil.getInstance().needNotifyKds()) {
				startProgressDialog();//连接KDS
				Log.e(TAG, "connectKDS-startProgressDialog");
				mPresenter.connectKDS();
			} else
				showAleartDialog("发生异常", "KDS设置不正确,请与管理员联系!");
		} else {
			startProgressDialog();//判断菜品数量
			Log.e(TAG, "connectKDS-startProgressDialog2");
			mPresenter.checkDishCounts(Cart.getInstance().getCheckDishStr());
		}
	}

	/**
	 * 显示支付的二维码
	 *
	 * @param content
	 */
	private void showPayDialog(String content) {
		TimeConfigure.resetScreenTime();
		mPayDialog = PayDialog.newInstance(content, String
				.valueOf(Price.getInstance().getTotal()), getPayMethod());
		mPayDialog.setOnCancelListener(new OnCancleClickListener() {
			@Override
			public void onCancleClick() {
				tradeClose();
				mPayDialog.dismiss();
			}
		});
		mPayDialog.show(getSupportFragmentManager(), "PayDialog");
	}

	public int getPayMethod() {
		return payType;
	}

	MyHandler handler = new MyHandler(this);

	@Override
	public void interpolatedTime(float interpolatedTime) {
		// 监听到翻转进度过半时，更新txtNumber显示内容。
		if (enableRefresh && interpolatedTime > 0.5f) {
			Order.getInstance().setTakeOut(Order.getInstance().getTakeOut() == 0 ? 1 : 0);
			orderdetail_takeout_tv.setText(Order.getInstance().getTakeOut() == 0 ? "堂食" : "外带");
			enableRefresh = false;
		}
	}

	private int times;//下單失敗之後重試的次數

	private class MyHandler extends BaseHandler {

		public MyHandler(Activity activity) {
			super(activity);
		}

		@Override
		public void handleMessage(Message msg, int what) {
			switch (what) {
				case What.ZERO://查询支付宝支付结果
					if (mPayDialog != null) {
						mPayDialog.setTimeTV(timeout);
					}
					break;
				case What.ONE:
					break;
				case What.ACTIVITY_GONE:

					break;
				case AppConstant.SYNC_RE_ACCEPT:
					Log.e(TAG, "再次发起下单!");
					FileLog.log("再次发起下单!");
					times++;
					doSyncPaySuccess();
					break;


			}
		}
	}

	@Override
	public void returnSyncSingleCouponPayResult(SyncSingleUseCouponRes orderRes) {
		if (orderRes != null) {
			if (orderRes.getCode() != 0) {
				ToastUitl.showLong(mContext, orderRes.getMessage());
				// TODO: 2018/6/29 anch 未解决
				Price.getInstance().clear();
				refreshUI();
				//退款
				//				Iterator<Integer> iterator = Order.getInstance().getPayList().iterator();
				//				if (iterator.hasNext()) {
				//					Integer next = iterator.next();
				//					if (next == PayMethod.SYNCCOUPON)
				//						iterator.remove();
				//				}
				syncRefund();//代金券使用失败退款
			} else {
				Order.getInstance().getPayList().add(PayMethod.SYNCCOUPON);
				Refund.getInstance().createPromoBean("C");
				if (Price.getInstance().getPointValue() > 0 && !Order.getInstance().getPayList()
						.contains(PayMethod.SYNCPOINT)) {
					mPresenter.syncPointPay(Order.getInstance().createSyncPointPay());
				} else if (Price.getInstance().getBalance() > 0 && !Order.getInstance()
						.getPayList()
						.contains(PayMethod.SYNCBALANCE)) {
					mPresenter.syncPay(PayMethod.SYNCBALANCE, Order.getInstance()
							.createSyncPayRequest(mContext, PayMethod.SYNCBALANCE, SyncDataProvider
									.getSyncMemberAccount().getMemberNo()));
				} else {
					LoadingDialog.setLoadingText("正在下单");
					doSyncPaySuccess();
				}
			}
		} else {
			ToastUitl.showLong(mContext, "使用优惠券出错!");
		}
	}

	/**
	 * 界面重置
	 */
	private void refreshUI() {
		initTotalPrice();
		initMemberLayout();
		sync_balance_swift.setChecked(false);
		sync_point_swift.setChecked(false);
		sync_use_point.setText("");
		sync_use_banlance.setText("");
		sync_use_coupon_value.setText("");
		view1.setVisibility(Price
				.getInstance()
				.getDealsValueMap().size() > 0 ? View.VISIBLE : View.GONE);
	}

	@Override
	public void returnWshCreateDealResult(CreateDealRes res) {
		stopProgressDialog();
		if (res.getResult() == 0) {
			CreateDealRes.ContentBean content = res.getContent();
			if (content.isVerify_password() && TextUtils
					.isEmpty(SPUtils
							.getSharedStringData(mContext, "tempMemberPassword"))) {
				showInputDialog(1, -1);
			} else if (content
					.isVerify_sms() && TextUtils.isEmpty(SPUtils
					.getSharedStringData(mContext, "tempMemberPassword"))) {
				showInputDialog(0, -1);
			} else {
				checkShowTable();//会员不需要输入密码
			}
		} else {
			ToastUitl.showLong(mContext, res.getErrmsg());
		}
	}

	@Override
	public void returnWshCommitDealResult(CommitWshDealRes res) {
		if (res != null) {
			if (res.getResult() == 0) {
				Order.getInstance().createMemberPayment();
				mPresenter.getNewOrderId();//会员支付成功后下单
			} else if (res.getResult() == 3056) {
				stopProgressDialog();
				ToastUitl.showLong(mContext, res.getErrmsg());
				//				useWshBalance(false);
				sync_balance_swift.setChecked(false);
				sync_point_swift.setChecked(false);
			} else {
				stopProgressDialog();
				ToastUitl.showLong(mContext, res.getErrmsg());
			}
		} else {
			stopProgressDialog();
			Log.e(TAG, "returnWshCommitDealResult == null");
		}

	}

	/**
	 * 美团验券的结果
	 *
	 * @param result
	 */
	@Override
	public void returnMeituanValidateCodeResult(ValidationSetoutResult result) {
		if (result != null) {
			if (result.getResult() == 0) {
				HashMap<Integer, Integer> deadsMap = Order.getInstance().getDealsMap();
				if (deadsMap.containsKey(result.getContent()
						.getDealId()) && deadsMap
						.get(result.getContent().getDealId()) > result
						.getContent().getCount()) {
					ToastUitl
							.showShort(mContext, "该券最多能使用" + result
									.getContent().getCount() + "张");
					return;
				}

				CartDish model = Cart.getInstance()
						.checkContainDealId(result.getContent().getDealId());
				if (model != null) {
					if (!deadsMap.containsKey(result.getContent().getDealId()))
						deadsMap.put(result.getContent().getDealId(), 1);
					else {
						Integer integer = deadsMap
								.get(result.getContent().getDealId());
						integer++;
						deadsMap.put(result.getContent().getDealId(), integer);
					}
					RelativeLayout layout = (RelativeLayout) View
							.inflate(mContext, R.layout.item_meituan_layout, null);
					TextView deal_title = (TextView) layout.findViewById(R.id.deal_title);
					TextView deal_value = (TextView) layout.findViewById(R.id.deal_value);
					deal_title.setText(result.getContent().getDealTitle());
					deal_value.setText("-￥" + String.valueOf(model.getCost()));
					meituan_dealids_layout.addView(layout);
					Price.getInstance().clear();
					//				Order.MeituanQuanInfo info = Order.getInstance().getMeituanQuanInfo();
					//				info.getDealIds().add(result.getContent().getCouponCode());
					//				info.setTotalPrice(PriceUtil
					//						.add(new BigDecimal(info.getTotalPrice()), model.getCost())
					//						.floatValue());
					//
					//				Order.getInstance().createPayment("", model.getCost(), String
					//						.valueOf(PayMethod.MEITUANQUAN), "美团验券", Order.getInstance().getBiz_id());
					Price.getInstance().getDealsValueMap()
							.put(result.getContent().getCouponCode(), Float
									.parseFloat(model.getCost()));
					refreshUI();
					if (Float.parseFloat(Price.getInstance().getTotal()) == 0) {
						connectKDS();//验券完毕之后
					} else {
						stopProgressDialog();
					}


				} else {
					showAleartDialog("验券异常", "美团团购项目ID设置不正确,请检查!");
				}

			} else {
				ToastUitl.showShort(mContext, result.getErrmsg());
			}
		} else {

		}
	}

	/**
	 * 智慧快餐美团执行验券结果
	 *
	 * @param result
	 */
	@Override
	public void returnExecuteMeituanCodeResult(ExecuteMeituanCodeResult result) {
		if (result.getResult() == 0) {
			Map<String, Float> map = Price.getInstance().getDealsValueMap();
			for (String key : map.keySet()) {
				Float aFloat = map.get(key);
				Order.getInstance().createPayment("", String.valueOf(aFloat), String
						.valueOf(PayMethod.MEITUANQUAN), "美团验券", Order.getInstance()
						.getBiz_id(), key);
				Refund.getInstance().addSmarantRevokeParam(String.valueOf(aFloat), key, String
						.valueOf(PayMethod.MEITUANQUAN));
			}
			if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
				//组合支付的时候需要进行此操作
				mPresenter.commitWshDeal(Order.getInstance().getBiz_id(), SPUtils
						.getSharedStringData(mContext, "tempMemberPassword"));
			} else {
				mPresenter.getNewOrderId();//美团验券成功后下单
			}
		} else {
			ToastUitl.showShort(mContext, result.getErrmsg());
		}
	}

	/**
	 * 餐行健 上传菜品
	 *
	 * @param result
	 */
	@Override
	public void returnWriteTouchTextResult(CxjWriteTouchTextRes result, ErrorType errorType) {

		if (errorType == ErrorType.SUCCESS) {
			if (result != null && result.isSuccess()) {
				//检查菜品完毕
				if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
					wshPay();//微生活 支付预览，判断是否需要输入密码
				} else {
					checkShowTable();//判断是否需要显示桌台,后台判断菜品是否足够，足够了
				}
			} else if (result != null) {
				ToastUitl.showLong(mContext, "下单异常,无法上传菜品!");
			}
		} else if (errorType == ErrorType.ERROR) {
			stopProgressDialog();
			showAleartDialog("异常", "服务器异常!");
		} else if (errorType == ErrorType.TIME_OUT) {
			stopProgressDialog();
			showAleartDialog("连接超时", "服务器连接超时,请重试!");
		}
	}

	/**
	 * 餐行健支付下单结果
	 *
	 * @param result
	 */
	@Override
	public void returnCheckOutResult(ResponseBody result) {
		try {
			String json = new String(result.bytes());
			Log.e(TAG, "returnCheckOutResult>" + json);
			if (json.contains("会话过期")) {
				//				mPresenter
				//						.checkOut(new Gson().toJson(CxjOrderProvider.getInstance().getCheckBean()));
				showAleartDialog("发生异常", "请刷新后重新结账!");
				return;
			}
			boolean isSucces = false;
			int     statu    = -1;
			String  msg      = "";
			try {
				int success = GsonUtils.getInt("success", json);
				statu = success;
				isSucces = true;
				try {
					CxjCheckOutRes bean = GsonUtils.getSingleBean(json, CxjCheckOutRes.class);
					msg = bean.getMsg();
				} catch (Exception e) {
					try {
						throw new SelfPosThrowable(e.getMessage());
					} catch (SelfPosThrowable execption) {
						execption.printStackTrace();
					}
				}
			} catch (Throwable e) {
				if (e instanceof SelfPosThrowable) {

				} else {
					msg = GsonUtils.getString("msg", json);
					statu = GsonUtils.getInt("code", json);
					isSucces = false;
				}
			}
			if (isSucces) {
				SPUtils.setSharedIntData(mContext, "wid", 0);
				if (0 == statu) {
					if ("请输入验证或交易码".equals(msg)) {
						//微生活的需要再次提交
						mPresenter.checkOut(new Gson()
								.toJson(CxjOrderProvider.getInstance().getCheckBean()));
					} else {
						//不成功
						stopProgressDialog();
						Order.getInstance().getPaymentList().clear();
						showAleartDialog("下单失败", msg);//餐行健下单失败
					}
				} else if (1 == statu) {
					//成功了
					//			SharedPreferencesUtil.setWid(mContext, 0);
					//			SharedPreferencesUtil.saveTempMemberPassword(mContext, "");
					CxjCheckOutRes bean  = GsonUtils.getSingleBean(json, CxjCheckOutRes.class);
					final String   order = bean.getOrder();//取餐号
					stopProgressDialog();
					showPaySuccessView(order);//下单成功

					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								NewOrderRes printOrder = Order.getInstance()
										.getPrintOrder(order);
								SmarantPrintUtil.setPrintList(printOrder);
								SmarantTicketPrintHandler.getInstance()
										.printCXJTicket(mContext, printOrder);


							} catch (Exception e) {
								FileUtil.saveCrashInfo2File(e);
							}
						}
					}).start();
				}
			} else {
				Log.e(TAG, "msg>>>" + msg);
				if (statu == 2) {

					LoadingDialog.setLoadingText("请输入支付密码...");
					handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);
				}
				//				else if (statu == 1) {
				//					stopProgressDialog();
				//					showPaySuccessView(String.valueOf(Order.getInstance().getOid()));//智慧快餐下单成功
				//					handler.removeCallbacks(queryPayResultRunnable);
				//					new Thread(new Runnable() {
				//						@Override
				//						public void run() {
				//							try {
				//								SmarantTicketPrintHandler.getInstance()
				//										.printSmarantTicket(mContext, Order.getInstance()
				//												.getPrintOrder(String
				//														.valueOf(Order.getInstance().getOid())));
				//							} catch (Exception e) {
				//								FileUtil.saveCrashInfo2File(e);
				//							}
				//						}
				//					}).start();
				//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 餐行健微生活预结结果
	 *
	 * @param result
	 */
	@Override
	public void returnWshYjset(CxjWshYuJieRes result) {
		if (result.isSuccess()) {
			Order.getInstance().createMemberPayment();
			SPUtils.setSharedIntData(mContext, "wid", result.getData().getWid());
			WshAccount.TradeVerify verify = WshDataProvider.getWshAccount().getTrade_verify();
			if (verify == null) {
				checkShowTable();//餐行健会员不需要输入密码
				return;
			}
			if ((verify.isCash_coupon_verify() && Price.getInstance()
					.getCouponValue() > 0) || (verify
					.isCharge_verify() && Price.getInstance().getBalance() > 0) || (verify
					.isCredit_verify() && Price.getInstance().getPointValue() > 0)) {
				if ("sms".equals(WshDataProvider.getWshAccount().getTrade_verify_type())) {
					stopProgressDialog();
					showInputDialog(0, -1);//餐行健
				} else if ("password"
						.equals(WshDataProvider.getWshAccount().getTrade_verify_type())) {
					stopProgressDialog();
					showInputDialog(1, -1);//餐行健
				}
			} else {
				checkShowTable();
			}
		}
	}

	@Override
	public void returnQueryCXJPayResult(ResponseBody result) {
		String json = null;
		try {
			json = new String(result.bytes());
			Log.e(TAG, "returnQueryCXJPayResult>" + json);
			int statu = GsonUtils.getInt("code", json);
			if (statu == 2) {
				handler.postDelayed(queryPayResultRunnable, TimeConfigure.CLOSETIMEGAP);
			} else if (statu == 1) {
				mPresenter.checkOut(new Gson()
						.toJson(CxjOrderProvider.getInstance().getCheckBean()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void returnCXJLoginResult(CxjLoginModel lo) {
		mPresenter.cxjNewOrder(new Gson()
				.toJson(CxjOrderProvider.getInstance().createCxjNewOrderBean()));
	}

	@Override
	public void returnCXJNewOrderResult(ResponseBody result) {
		String json = null;
		try {
			if (result == null) {
				showAleartDialog("发生异常", "result为空!");
				return;
			}
			json = new String(result.bytes());
			Log.e(TAG, "returnCXJNewOrderResult>" + json);
			boolean success = GsonUtils.getBoolean("success", json);
			if (success) {
				int oid = GsonUtils.getInt("oid", json);
				CxjOrderProvider.getInstance().setOid(oid);
				if (Order.getInstance().isMember() && Order.getInstance().isMemberPay()) {
					wshPay();//微生活 支付预览，判断是否需要输入密码
				} else {
					checkShowTable();//判断是否需要显示桌台,后台判断菜品是否足够，足够了
				}
			} else {
				ToastUitl.showLong(mContext, "未知异常!");
			}
		} catch (Throwable e) {
			try {
				String msg = GsonUtils.getString("msg", json);
				showAleartDialog("发生异常", msg);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void returnCXJPrecheckResult(ResponseBody result) {
		String json = null;
		try {
			json = new String(result.bytes());
			boolean success = false;
			String  msg     = "";
			Log.e(TAG, "returnCXJPrecheckResult>" + json);
			try {
				success = GsonUtils.getBoolean("success", json);
			} catch (JSONException e) {
				success = false;
				msg = GsonUtils.getString("msg", json);
				e.printStackTrace();
			}
			if (success) {
				CXJPrecheckRes bean = GsonUtils.getSingleBean(json, CXJPrecheckRes.class);
				Order.getInstance().createMemberPayment();
				WshAccount.TradeVerify verify = WshDataProvider.getWshAccount().getTrade_verify();
				if (verify == null) {
					checkShowTable();//餐行健会员不需要输入密码
					return;
				}
				if ((verify.isCash_coupon_verify() && Price.getInstance()
						.getCouponValue() > 0) || (verify
						.isCharge_verify() && Price.getInstance().getBalance() > 0) || (verify
						.isCredit_verify() && Price.getInstance().getPointValue() > 0)) {
					if ("sms".equals(WshDataProvider.getWshAccount().getTrade_verify_type())) {
						stopProgressDialog();
						showInputDialog(0, -1);//餐行健
					} else if ("password"
							.equals(WshDataProvider.getWshAccount().getTrade_verify_type())) {
						stopProgressDialog();
						showInputDialog(1, -1);//餐行健
					}
				} else {
					checkShowTable();
				}
			} else {
				showAleartDialog("会员预结异常", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 美团优惠券支付
	 */
	private void meiTuanQuanPay() {
		if (!Order.getInstance().getPayList().contains(PayMethod.ALI) && !Order.getInstance()
				.getPayList().contains(PayMethod.WECHAT) && !Order.getInstance()
				.getPayList().contains(PayMethod.MEITUAN) && !Order.getInstance()
				.getPayList().contains(PayMethod.WSH)) {
			resetPayStatu();//如果只有美团验券
			Refund.getInstance().clear();//单独使用美团验券的时候，先清空退款想关的参数
			if (LoadingDialog.isIsLoading())
				LoadingDialog.setLoadingText("正在支付");
			else
				startProgressDialog("正在支付...");
		}
		StringBuilder      sb  = new StringBuilder();
		Map<String, Float> map = Price.getInstance().getDealsValueMap();
		for (String key : map.keySet()) {
			sb.append(key + ",");
		}
		mPresenter.executeMeituanCode(Order.getInstance().getBiz_id(), sb.toString());
		Log.e(TAG, "meiTuanQuanPay-resetPayStatu");
	}

	private MemberPwdInputDialog mMemberPwdInputDialog;

	private void showInputDialog(int dialogType, int useType) {
		TimeConfigure.resetScreenTime();
		mMemberPwdInputDialog = MemberPwdInputDialog.newInstance(dialogType, useType);
		mMemberPwdInputDialog.setOnYesClickListener(this);
		mMemberPwdInputDialog.setOnMemCancleClickListener(this);
		mMemberPwdInputDialog.show(getSupportFragmentManager(), "MemberPwdInputDialog");
	}

	@Override
	public void onYesClick(String password, int useType) {
		if (!TextUtils.isEmpty(password) && password.length() == 6) {
			SPUtils
					.setSharedStringData(mContext, "tempMemberPassword", password);
			mMemberPwdInputDialog.dismiss();
			checkShowTable();//会员输入密码完毕
		} else {
			ToastUitl.showLong(mContext, "请输入6位数密码!");
		}
	}

	@Override
	public void onMemberCancleClick(int useType) {
		resetMemberPay();
	}

	private void resetMemberPay() {
		WshAccount.TradeVerify verify = WshDataProvider.getWshAccount().getTrade_verify();
		if (verify != null) {
			if (verify.isCredit_verify())
				sync_point_swift.setChecked(false);
			if (verify.isCharge_verify())
				sync_balance_swift.setChecked(false);
		} else {
			sync_point_swift.setChecked(false);
			sync_balance_swift.setChecked(false);
		}
	}
}
