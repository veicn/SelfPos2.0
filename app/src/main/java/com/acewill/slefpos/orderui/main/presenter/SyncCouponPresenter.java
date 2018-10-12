package com.acewill.slefpos.orderui.main.presenter;

import android.util.Log;

import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.orderui.main.contract.SyncCouponContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.utils.logutil.FileLog;

/**
 * Author：Anch
 * Date：2018/6/8 10:03
 * Desc：
 */
public class SyncCouponPresenter extends SyncCouponContract.Presenter {
	private static final String TAG = "SyncCouponPresenter";

	@Override
	public void syncSingleCouponPreview(String syncSingleCouponPay) {
		Log.e(TAG, "loadSingleCouponPay>" + syncSingleCouponPay);
		FileLog.log( "loadSingleCouponPay>" + syncSingleCouponPay);
		mRxManage.add(mModel.syncSingleCouponPreview(syncSingleCouponPay)
				.subscribe(new RxSubscriber<SyncSingleUseCouponRes>(mContext, true) {
					@Override
					protected void _onNext(SyncSingleUseCouponRes res) {
						mView.returnSyncSingleCouponPreviewResult(res);
					}

					@Override
					protected void _onError(String message) {
						mView.returnSyncSingleCouponPreviewResult(null);
					}

					@Override
					protected void _onTimeOut() {
						mView.returnSyncSingleCouponPreviewResult(null);
					}
				}));
	}
}
