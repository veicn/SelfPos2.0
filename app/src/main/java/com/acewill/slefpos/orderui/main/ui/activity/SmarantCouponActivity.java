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
import com.acewill.slefpos.bean.canxingjianbean.CxjOrderProvider;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.bean.uibean.CouponType;
import com.acewill.slefpos.bean.wshbean.WshAccountCoupon;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.contract.SyncCouponContract;
import com.acewill.slefpos.orderui.main.model.SyncCouponModel;
import com.acewill.slefpos.orderui.main.presenter.SyncCouponPresenter;
import com.acewill.slefpos.orderui.main.ui.adapter.CouponTypeAdapter;
import com.acewill.slefpos.orderui.main.uihelper.MemberPayHelper;
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
public class SmarantCouponActivity extends
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
	private CommonRecycleViewAdapter<WshAccountCoupon> adapter;

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
		//		WshAccount memberAccount = SmarantDataProvider.getWshAccount();
		List<WshAccountCoupon> coupons = MemberPayHelper.getCoupons();
		//		if (memberAccount == null || memberAccount.getCoupons().size() == 0)
		//			return;
		List<CouponType> couponTypeList = new ArrayList<>();
		couponTypeList.add(new CouponType("ALL", coupons.size(), true));
		for (WshAccountCoupon coupon : coupons) {
			if (couponTypeList.size() > 0) {
				boolean hasAdd = false;
				for (CouponType couponType : couponTypeList) {
					if (couponType.getType().equals(String.valueOf(coupon.getType()))) {
						couponType.setType_size(couponType.getType_size() + 1);
						hasAdd = true;
						break;
					}
				}
				if (!hasAdd) {
					CouponType type = new CouponType();
					type.setType(String.valueOf(coupon.getType()));
					type.setType_size(1);
					couponTypeList.add(type);
				}
			} else {
				CouponType type = new CouponType();
				type.setType(String.valueOf(coupon.getType()));
				type.setType_size(1);
				couponTypeList.add(type);
			}
		}
		adapter.setOnTabClickListener(this);
		adapter.setData(couponTypeList);
	}

	private WshAccountCoupon currentSelect;

	private void initCouponListView() {
		adapter = new CommonRecycleViewAdapter<WshAccountCoupon>(mContext, R.layout.item_coupon) {

			@Override
			public void convert(ViewHolderHelper helper, final WshAccountCoupon coupon) {
				TextView couponName   = helper.getView(R.id.coupon_name);
				TextView couponExpire = helper.getView(R.id.coupon_expire);
				TextView couponLimit  = helper.getView(R.id.coupon_limit);
				CardView cardView     = helper.getView(R.id.cardView);
				couponName.setText(coupon.getTitle());

				if (coupon.isCanUse()) {
					cardView
							.setCardBackgroundColor(mContext.getResources()
									.getColor(R.color.main_color_white));
				} else {
					cardView
							.setCardBackgroundColor(mContext.getResources()
									.getColor(R.color.wheel_item_text_color_no_select));
				}

				//未满足条件，禁止选择优惠券
				//				if (coupon.getType() == 2) {
				//					holder.coupon_money.setVisibility(View.GONE);
				//					holder.yuan.setVisibility(View.GONE);
				//				} else {
				//					holder.coupon_money.setVisibility(View.VISIBLE);
				//					holder.yuan.setVisibility(View.VISIBLE);
				//				}
				//				holder.coupon_money.setText(coupon.getDeno() + "");
				couponExpire.setText(coupon.getEffective_time() + " - " + coupon.getFailure_time());


				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						currentSelect = coupon;
						if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
							if (coupon.isCanUse()) {
								Order.getInstance().clearFavor();

								if (coupon.getType() == 1) {
									//代金券
									if (SystemConfig.isCanXingJianSystem && Float
											.parseFloat(Price.getInstance().getTotal()) < coupon
											.getDeno()) {
										float actualCost =coupon
												.getDeno()- Float.parseFloat(Price.getInstance().getTotal()) ;
										CxjOrderProvider.getInstance().setDiscountValue(actualCost);
									}
									Price.getInstance().setCouponValue(coupon.getDeno());
								} else if (coupon.getType() == 2) {
									//菜品券
									Price.getInstance().setCouponValue(Float
											.parseFloat(coupon.getDishPirce()));
								}
								Price.getInstance().setWshCoupon(currentSelect);
								Price.getInstance().setCouponNo(coupon.getCoupon_ids().get(0));
								Intent intent = new Intent();
								intent.putExtra("memberCoupon", currentSelect);
								setResult(100, intent);
								finish();
							} else {
								ToastUitl.showLong(mContext, "未达到该券的使用条件!");
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
		//		adapter.initAnimation(irc);
		if (MemberPayHelper.getCoupons().size() > 0) {
			adapter.addAll(MemberPayHelper.getCoupons());
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
			adapter.addAll(MemberPayHelper.getCoupons());
		} else {
			List<WshAccountCoupon> newCoponList = new ArrayList<>();
			for (WshAccountCoupon coupon : MemberPayHelper.getCoupons()) {
				if (type.equals(String.valueOf(coupon.getType()))) {
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
				if (orderRes.getData().getStatus() == 1) {
					Order.getInstance().clearFavor();
					Price.getInstance().setCouponValue(orderRes.getData().getDiscountAmount());
					Price.getInstance().setCouponNo(orderRes.getData().getCouponNo());
					Intent intent = new Intent();
					intent.putExtra("memberCoupon", currentSelect);
					setResult(100, intent);
					finish();
				} else {
					ToastUitl.showLong(mContext, orderRes.getData().getIgnoreCause());
				}
			}
		} else {
			ToastUitl.showLong(mContext, "");
		}
	}
}
