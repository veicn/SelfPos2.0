package com.acewill.slefpos.orderui.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.StringCallback;
import com.acewill.slefpos.system.dialog.UpDateCxjDataDialog;
import com.jaydenxiao.common.commonutils.SPUtils;

import okhttp3.Call;

/**
 * Author：Anch
 * Date：2018/1/24 13:20
 * Desc：
 */
public class EmptyActivity extends BaseActivity {


	private static final String TAG = "EmptyActivity";

	@Override
	public int getLayoutId() {
		return R.layout.act_empty;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		//				if (false)

		final UpDateCxjDataDialog dataDialog = UpDateCxjDataDialog.getInstance(0);
		dataDialog.setOnYesClickListener(new UpDateCxjDataDialog.OnYesClickListener() {
			@Override
			public void onYesClick() {
				checkDbUpdate();
				startActivity(new Intent(EmptyActivity.this, LoadDataActivity.class));
			}

			@Override
			public void onCancle() {
				startActivity(new Intent(EmptyActivity.this, LoadDataActivity.class));
			}
		});
		dataDialog.show(getSupportFragmentManager(), "UpDateCxjDataDialog");
	}

	private void checkDbUpdate() {
		OkHttpUtils
				.post()
				.url(getSqliteUpadateUrl())
				.addParams("timestamp", (System.currentTimeMillis() / 1000) + "")
				.build()
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						Log.e(TAG, "checkDbUpdate_onError");
					}

					@Override
					public void onResponse(String response, int id) {
						Log.e(TAG, "checkDbUpdate_onResponse");
					}
				});
	}

	private String getSqliteUpadateUrl() {
		return "http://"
				+ SPUtils.getSharedStringData(mContext, "canxingjian_ip_address")
				+ "/order/api/api.php?do=isSqliteFileUpdated&app=ClientNewAndroidMobile";
	}
}
