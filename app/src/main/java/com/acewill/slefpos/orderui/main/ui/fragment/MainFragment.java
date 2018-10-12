package com.acewill.slefpos.orderui.main.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.jaydenxiao.common.commonutils.LogUtils;

/**
 * Author：Anch
 * Date：2018/2/7 15:40
 * Desc：
 */
public class MainFragment extends BaseFragment {
	private PayFragment      payFragment;
	private DishMenuFragment dishMenuFragment;
	private BannerFragment   bannerFragment;


	@Override
	protected int getLayoutResource() {
		return R.layout.fra_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragment(savedInstanceState);
		//监听菜单显示或隐藏

	}

	private void initFragment(Bundle savedInstanceState) {
		FragmentTransaction transaction        = getActivity().getSupportFragmentManager().beginTransaction();
		int                 currentTabPosition = 0;
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
			payFragment = new   PayFragment();
			bannerFragment = new BannerFragment();
			transaction.add(R.id.banner_contain, bannerFragment, "bannerFragment");
			transaction.add(R.id.content_contain, dishMenuFragment, "dishMenuFragment");
			transaction.add(R.id.content_contain, payFragment, "payFragment");
		}
		transaction.show(bannerFragment);
		SwitchTo(transaction,currentTabPosition);
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
		if (currentTab != -1){
			LogUtils.loge("onSaveInstanceState进来了2");
			outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, currentTab);
		}
	}
}
