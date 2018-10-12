package com.acewill.slefpos.orderui.main.presenter;

import android.util.Log;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishPosResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishRegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.QryStoreResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.RegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitResponseData;
import com.acewill.slefpos.orderui.main.contract.SyncMainContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Author：Anch
 * Date：2018/4/19 17:50
 * Desc：
 */
public class SyncMainPresenter extends SyncMainContract.Presenter {
	private static final String TAG = "SyncMainPresenter";

	@Override
	public void goinit(String data) {
		mRxManage.add(mModel.init(mContext, data)
				.subscribe(new RxSubscriber<SyncInitResponseData>(mContext, false) {
					@Override
					protected void _onNext(SyncInitResponseData data) {

						mView.returnInitResult(data);
					}

					@Override
					protected void _onError(String message) {
						mView.stopLoading();
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.stopLoading();
						ToastUitl.showLong(mContext, mContext.getString(R.string.timeout));
					}
				}));
	}

	@Override
	public void goQryStore(String data) {
		mRxManage.add(mModel.qryStore(mContext, data)
				.subscribe(new RxSubscriber<QryStoreResponseData>(mContext, false) {
					@Override
					protected void _onNext(QryStoreResponseData body1) {
						mView.returnQueryResult(body1);
					}

					@Override
					protected void _onError(String message) {
						mView.stopLoading();
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.stopLoading();
						ToastUitl.showLong(mContext, mContext.getString(R.string.timeout));
					}
				}));
	}

	@Override
	public void finishPos(String data) {
		mRxManage.add(mModel.finishPos(mContext, data)
				.subscribe(new RxSubscriber<FinishPosResponseData>(mContext, true) {
					@Override
					protected void _onNext(FinishPosResponseData data) {


						mView.returnFinishResult(data);
					}

					@Override
					protected void _onError(String message) {
						mView.stopLoading();
						ToastUitl.showLong(mContext, message);
					}

					@Override
					protected void _onTimeOut() {
						mView.stopLoading();
						ToastUitl.showLong(mContext, mContext.getString(R.string.timeout));
					}
				}));
	}

	@Override
	public void register(String data) {
		Log.e(TAG, "注册的参数:" + data);
		mRxManage.add(mModel.register(mContext, data)
				.subscribe(new RxSubscriber<RegisterResponseData>(mContext, false) {
					@Override
					protected void _onNext(RegisterResponseData data) {
						mView.returnRegister(data);
					}

					@Override
					protected void _onError(String message) {
						mView.returnRegister(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnRegister(null);
					}
				}));
	}

	@Override
	public void registerFinish(String data) {
		Log.e(TAG, "注册完成的参数:" + data);
		mRxManage.add(mModel.registerFinish(mContext, data)
				.subscribe(new RxSubscriber<FinishRegisterResponseData>(mContext, false) {
					@Override
					protected void _onNext(FinishRegisterResponseData data) {
						mView.returnFinishRegister(data);
					}

					@Override
					protected void _onError(String message) {
						mView.returnFinishRegister(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnFinishRegister(null);
					}
				}));
	}
}
