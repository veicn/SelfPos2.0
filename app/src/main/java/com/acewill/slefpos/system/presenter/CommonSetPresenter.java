package com.acewill.slefpos.system.presenter;

import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.smarantstorebean.BaseEntity;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.system.contract.CommonSetContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/1/24 19:19
 * Desc：
 */
public class CommonSetPresenter extends CommonSetContract.Presenter {

	@Override
	public void onStart() {
		super.onStart();
		mRxManage.on(AppConstant.BIND, new Action1<BindEntity>() {
			@Override
			public void call(BindEntity entity) {
				if (entity != null)
					mView.returnBindResult(entity);
				else
					ToastUitl.showLong(mContext, "绑定数据为空");
			}
		});
	}


	@Override
	public void goBind(String terminalCode) {
		mRxManage.add(mModel.bind(terminalCode)
				.subscribe(new RxSubscriber<BindEntity>(mContext, false) {
					@Override
					protected void _onNext(BindEntity entity) {
						mView.returnBindResult(entity);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}

	@Override
	public void goLogin() {
		mRxManage.add(mModel
				.login(BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
								.getStoreid(),
						TerminalConfigure.getTname(), TerminalConfigure
								.getTerminalmac(), "0", StoreConfigure
								.getLongitute(), StoreConfigure.getLatitute(), ""
						, TerminalConfigure.getCurrentVersion(), TerminalConfigure.getVersionid())
				.subscribe(new RxSubscriber<LoginEntity>(mContext, false) {
					@Override
					protected void _onNext(LoginEntity entity) {
						mView.returnLoginResult(entity);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
						if (message.equals("终端已经被解绑定，请重新绑定终端！")){
							mView.clearSP();
						}
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}

	@Override
	public void unBind() {
		mRxManage.add(mModel
				.unbind(TerminalConfigure
						.getTerminalmac(), BaseConfigure.getToken())
				.subscribe(new RxSubscriber<BaseEntity>(mContext, false) {
					@Override
					protected void _onNext(BaseEntity entity) {
						mView.returnUnBindResult(entity);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}
}
