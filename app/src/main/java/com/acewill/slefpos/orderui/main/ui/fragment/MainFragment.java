package com.acewill.slefpos.orderui.main.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.orderui.main.market.MarketBean;
import com.acewill.slefpos.orderui.main.market.MarketController;
import com.acewill.slefpos.orderui.main.ui.dialog.FavorDialog;
import com.jaydenxiao.common.commonutils.LogUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Author：Anch
 * Date：2018/2/7 15:40
 * Desc：
 */
public class MainFragment extends BaseFragment {
	private PayFragment      payFragment;
	private DishMenuFragment dishMenuFragment;
	private BannerFragment   bannerFragment;
	@Bind(R.id.marquee_view)
	ViewFlipper marquee_view;
	@Bind(R.id.marquee_tv)
	TextView    marquee_tv;
	@Bind(R.id.marquee_layout)
	View        layout;


	@Override
	protected int getLayoutResource() {
		return R.layout.fra_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView() {
		List<MarketBean> marketBeanList = MarketController.getMarketBeanList();
		if (marketBeanList != null && marketBeanList.size() > 0) {
			layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showFavorDialog();
				}
			});
			layout.setVisibility(View.VISIBLE);
			marquee_tv.setText("今日有" + marketBeanList.size() + getResources()
					.getString(R.string._preference));
			if (marketBeanList.size() == 1) {
				marketBeanList.add(marketBeanList.get(0));
			}
			for (MarketBean bean : marketBeanList) {
				View      inflate   = View.inflate(mContext, R.layout.noticelayout, null);
				ImageView imageView = (ImageView) inflate.findViewById(R.id.market_ad_img);
				imageView.setImageResource(bean.getMarketImg());
				TextView tv = (TextView) inflate.findViewById(R.id.market_ad_tv);
				tv.setText(bean.getMarketName());
				marquee_view.addView(inflate);
			}
		}
	}

	/**
	 * 这是显示优惠方案的弹窗
	 */
	private void showFavorDialog() {
		FavorDialog dialog = FavorDialog.newInstance();
		dialog.show(getFragmentManager(), "FavorDialog");

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragment(savedInstanceState);
		//监听菜单显示或隐藏

	}

	private void initFragment(Bundle savedInstanceState) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		int currentTabPosition = 0;
		if (savedInstanceState != null) {
			bannerFragment = (BannerFragment) getActivity().getSupportFragmentManager()
					.findFragmentByTag("bannerFragment");
			dishMenuFragment = (DishMenuFragment) getActivity().getSupportFragmentManager()
					.findFragmentByTag("dishMenuFragment");
			payFragment = (PayFragment) getActivity().getSupportFragmentManager()
					.findFragmentByTag("payFragment");
			currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
		} else {
			dishMenuFragment = new DishMenuFragment();
			payFragment = new PayFragment();
			bannerFragment = new BannerFragment();
			transaction.add(R.id.banner_contain, bannerFragment, "bannerFragment");
			transaction.add(R.id.content_contain, dishMenuFragment, "dishMenuFragment");
			transaction.add(R.id.content_contain, payFragment, "payFragment");
		}
		transaction.show(bannerFragment);
		SwitchTo(transaction, currentTabPosition);
	}

	/**
	 * 切换
	 */
	private void SwitchTo(FragmentTransaction transaction, int position) {
		LogUtils.logd("主页菜单position" + position);
		switch (position) {
			//菜单页
			case 0:
				transaction.hide(payFragment);
				transaction.show(dishMenuFragment);
				transaction.commitAllowingStateLoss();
				break;
			//结账页面
			case 1:
				transaction.hide(dishMenuFragment);
				transaction.show(payFragment);
				transaction.commitAllowingStateLoss();
				break;
			default:
				break;
		}
	}

	//	@Override
	//	public boolean onKeyDown(int keyCode, KeyEvent event) {
	//		if (keyCode == KeyEvent.KEYCODE_BACK) {
	//			moveTaskToBack(false);
	//			return true;
	//		}
	//		return super.onKeyDown(keyCode, event);
	//	}

	private int currentTab = -1;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//奔溃前保存位置
		LogUtils.loge("onSaveInstanceState进来了1");
		if (currentTab != -1) {
			LogUtils.loge("onSaveInstanceState进来了2");
			outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, currentTab);
		}
	}
}
