package com.acewill.slefpos.orderui.main.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.orderui.main.ui.fragment.CartFragmentNew;

/**
 * Author：Anch
 * Date：2018/2/8 18:00
 * Desc：todo 未完成点击进入修改选择项的操作
 * todo 未完成是否是同一个套餐的判断
 */
public class CartActivity extends BaseActivity {

	@Override
	public int getLayoutId() {
		return R.layout.act_cart;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView(Bundle savedInstanceState) {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragment(savedInstanceState);
		//监听菜单显示或隐藏
	}

	private CartFragmentNew cartFragment;

	private void initFragment(Bundle savedInstanceState) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (savedInstanceState != null) {
			cartFragment = (CartFragmentNew) getSupportFragmentManager()
					.findFragmentByTag("cartFragment");

		} else {
			cartFragment = new CartFragmentNew();
			transaction.add(R.id.container, cartFragment, "cartFragment");
		}
		transaction.show(cartFragment);
		transaction.commit();
	}
}
