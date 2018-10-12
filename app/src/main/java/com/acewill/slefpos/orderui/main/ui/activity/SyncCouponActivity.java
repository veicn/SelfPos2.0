package com.acewill.slefpos.orderui.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.uibean.CouponType;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.contract.SyncCouponContract;
import com.acewill.slefpos.orderui.main.model.SyncCouponModel;
import com.acewill.slefpos.orderui.main.presenter.SyncCouponPresenter;
import com.acewill.slefpos.orderui.main.ui.adapter.CouponTypeAdapter;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.acewill.slefpos.widgetlibrary.MyGridView;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：Anch
 * Date：2018/6/6 10:16
 * Desc：
 */
public class SyncCouponActivity extends
		BaseActivity<SyncCouponPresenter, SyncCouponModel> implements
		OnLoadMoreListener, OnRefreshListener,
		CouponTypeAdapter.TabClickListener, SyncCouponContract.View {
	@Bind(R.id.irc)
	IRecyclerView  irc;
	@Bind(R.id.loadedTip)
	LoadingTip     loadedTip;
	@Bind(R.id.fra_coupon_layout)
	RelativeLayout fra_coupon_layout;
	@Bind(R.id.coupon_type_list)
	MyGridView     couponTypeListView;
	private CommonRecycleViewAdapter<SyncMemberLoginRes.DataBean.MemberCoupon> adapter;

	@Override
	public int getLayoutId() {
		return R.layout.act_coupon;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	@OnClick(R.id.iv_back)
	public void onClick() {
		finish();
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		initCouponTypeListView();
		initCouponListView();
	}


	private void initCouponTypeListView() {
		CouponTypeAdapter adapter = new CouponTypeAdapter(mContext);
		couponTypeListView.setAdapter(adapter);
		SyncMemberLoginRes.DataBean memberAccount = SyncDataProvider
				.getSyncMemberAccount();
		if (memberAccount == null || memberAccount.getCoupon().size() == 0)
			return;
		List<CouponType> couponTypeList = new ArrayList<>();
		couponTypeList.add(new CouponType("ALL", memberAccount.getCoupon().size(), true));
		for (SyncMemberLoginRes.DataBean.MemberCoupon coupon : memberAccount.getCoupon()) {
			if (couponTypeList.size() > 0) {
				boolean hasAdd = false;
				for (CouponType couponType : couponTypeList) {
					if (couponType.getType().equals(coupon.getCouponType())) {
						couponType.setType_size(couponType.getType_size() + 1);
						hasAdd = true;
						break;
					}
				}
				if (!hasAdd) {
					CouponType type = new CouponType();
					type.setType(coupon.getCouponType());
					type.setType_size(1);
					couponTypeList.add(type);
				}
			} else {
				CouponType type = new CouponType();
				type.setType(coupon.getCouponType());
				type.setType_size(1);
				couponTypeList.add(type);
			}
		}
		adapter.setOnTabClickListener(this);
		adapter.setData(couponTypeList);
	}

	private SyncMemberLoginRes.DataBean.MemberCoupon currentSelect;

	private void initCouponListView() {
		adapter = new CommonRecycleViewAdapter<SyncMemberLoginRes.DataBean.MemberCoupon>(mContext, R.layout.item_coupon) {

			@Override
			public void convert(ViewHolderHelper helper, final SyncMemberLoginRes.DataBean.MemberCoupon memberCoupon) {
				TextView couponName   = helper.getView(R.id.coupon_name);
				TextView couponExpire = helper.getView(R.id.coupon_expire);
				TextView couponLimit  = helper.getView(R.id.coupon_limit);
				CardView cardView     = helper.getView(R.id.cardView);
				couponName.setText(memberCoupon.getCouponName());
				if (memberCoupon.isCanUse()) {
					cardView
							.setCardBackgroundColor(mContext.getResources()
									.getColor(R.color.main_color_white));
				} else {
					cardView
							.setCardBackgroundColor(mContext.getResources()
									.getColor(R.color.wheel_item_text_color_no_select));
				}
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						currentSelect = memberCoupon;
						if (SystemConfig.isSyncSystem) {
							if (memberCoupon.isCanUse()) {
								mPresenter.syncSingleCouponPreview(Order.getInstance()
										.createSyncSingleCouponPay(memberCoupon
														.getCouponNo(),
												SyncDataProvider
														.getSyncMemberAccount().getMemberNo()));
							} else {
								ToastUitl.showLong(mContext, "该券与适用的商品不符!!!");
							}


						} else {
							ToastUitl.showLong(mContext, "系统出错!");
						}
					}
				});
			}
		};
		irc.setAdapter(adapter);
		irc.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
		irc.setOnLoadMoreListener(this);
		irc.setOnRefreshListener(this);
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		List<SyncMemberLoginRes.DataBean.MemberCoupon> coupons = SyncDataProvider
				.getSyncMemberCoupons();
		if (coupons.size() > 0) {
			adapter.addAll(coupons);
			loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
		} else {
			loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
		}

	}


	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}

	@Override
	public void onTabClickListener(String type) {
		if (type.equals("ALL")) {
			adapter.addAll(SyncDataProvider.getSyncMemberCoupons());
		} else {
			List<SyncMemberLoginRes.DataBean.MemberCoupon> newCoponList = new ArrayList<>();
			for (SyncMemberLoginRes.DataBean.MemberCoupon coupon : SyncDataProvider
					.getSyncMemberCoupons()) {
				if (type.equals(coupon.getCouponType())) {
					newCoponList.add(coupon);
				}
			}
			adapter.addAll(newCoponList);
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

	@Override
	public void returnSyncSingleCouponPreviewResult(SyncSingleUseCouponRes orderRes) {
		if (orderRes != null) {
			if (orderRes.getCode() != 0) {
				ToastUitl.showLong(mContext, orderRes.getCode() + "," + orderRes.getMessage());
			} else {
				if (orderRes.getData() != null) {
					if (orderRes.getData().getStatus() == 1) {

						/**
						 * 清空 且只能清空之前记录的 优惠券 储值 积分 等非会员优惠的信息
						 */

						Price.getInstance().clear();
						/**
						 * 四舍五入,将代金券计算返回的值控制在两位精度
						 */
						orderRes
								.getData()
								.setDiscountAmount(Float.parseFloat(PriceUtil.formatPrice(orderRes
										.getData()
										.getDiscountAmount())));
						boolean is = false;
						if (Float.parseFloat(Price.getInstance().getTotal()) < orderRes
								.getData()
								.getDiscountAmount()) {
							is = true;
						} else {
							is = false;
						}

						if (is) {
							Price.getInstance()
									.setCouponValue(Float
											.parseFloat(Price.getInstance().getTotal()));
						} else {
							Price.getInstance().setCouponValue(orderRes.getData()
									.getDiscountAmount());
						}
						Price.getInstance().setCouponNo(orderRes.getData().getCouponNo());
						Price.getInstance().setSyncCoupon(currentSelect);
						Intent intent = new Intent();
						intent.putExtra("memberCoupon", currentSelect);
						setResult(100, intent);
						finish();
					} else {
						ToastUitl.showLong(mContext, orderRes.getData().getIgnoreCause());
					}
				} else {
					ToastUitl.showLong(mContext, "该券不满足使用条件!");
				}
			}
		} else {
			ToastUitl.showLong(mContext, "该券不满足使用条件!!");
		}
	}
}
