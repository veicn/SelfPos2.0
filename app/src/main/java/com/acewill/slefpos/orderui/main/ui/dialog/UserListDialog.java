package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUser;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUserRes;
import com.acewill.slefpos.dialog.BaseDialog;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

/**
 * Author：Anch
 * Date：2018/6/9 14:37
 * Desc：
 */
public class UserListDialog extends BaseDialog implements View.OnClickListener {
	private IRecyclerView                       xre;
	private CommonRecycleViewAdapter<FePosUser> adapter;

	/**
	 * @return
	 */
	public static UserListDialog newInstance(FePosUserRes fePosUserRes, String fromWho) {
		UserListDialog fragment = new UserListDialog();
		Bundle         bundle   = new Bundle();
		bundle.putSerializable("fePosUserRes", fePosUserRes);
		bundle.putString("fromWho", fromWho);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_sync_user_list, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		TextView     title   = (TextView) view.findViewById(R.id.title);
		FePosUserRes res     = (FePosUserRes) getArguments().getSerializable("fePosUserRes");
		final String fromWho = (String) getArguments().getSerializable("fromWho");
		if (fromWho.equals("eatmethod")) {
			title.setText("请选择账号登出");
		} else {
			title.setText("请选择账号登录");
		}
		xre = (IRecyclerView) view.findViewById(R.id.dialog_user_xre);
		view.findViewById(R.id.iv_cancer).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TimeConfigure.resetScreenTime();
				if (fromWho.equals("eatmethod")) {
					//啥也不干
				} else {
					//提示开始加载数据
					mRxManager.post(AppConstant.SYNCUSERLOGINCANCLE, 1);
				}
				dismiss();
			}
		});
		adapter = new CommonRecycleViewAdapter<FePosUser>(mcontext, R.layout.item_user) {
			@Override
			public void convert(ViewHolderHelper helper, final FePosUser user) {
				TextView  tvName  = helper.getView(R.id.tv);
				ImageView user_iv = helper.getView(R.id.user_iv);
				tvName.setText(user.getName());
				if ("M".equals(user.getSex())) {
					//男性
					user_iv.setImageDrawable(getResources().getDrawable(R.mipmap.user_man));
				} else {
					user_iv.setImageDrawable(getResources().getDrawable(R.mipmap.user_women));
				}
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						TimeConfigure.resetScreenTime();
						SyncUserLoginDialog dialog = SyncUserLoginDialog.newInstance(user, fromWho);
						dialog.setCancelable(false);
						dialog.show(getFragmentManager(), "SyncUserLoginDialog");
						dismiss();
					}
				});
			}
		};
		xre.setLayoutManager(new GridLayoutManager(mcontext, 2, LinearLayoutManager.VERTICAL, false));
		xre.setRefreshEnabled(false);
		xre.setLoadMoreEnabled(false);
		adapter.addAll(res.getFePosUser());
		xre.setAdapter(adapter);
	}

	@Override
	public float getSize() {
		return 0.7f;
	}
}
