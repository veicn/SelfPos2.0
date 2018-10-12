package com.acewill.slefpos.orderui.main.presenter;

import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.orderui.main.contract.SyncMemberContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Author：Anch
 * Date：2018/5/30 11:34
 * Desc：
 */
public class SyncMemberPresenter extends SyncMemberContract.Presenter {
	@Override
	public void syncMemberLogin(String memberid) {
		mRxManage.add(mModel.doSyncMemberLogin(memberid)
				.subscribe(new RxSubscriber<SyncMemberLoginRes>(mContext, false) {
					@Override
					protected void _onNext(SyncMemberLoginRes syncMemberLoginRes) {
						mView.returnSyncMemberLoginResult(syncMemberLoginRes);
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showLong(mContext,message);
					}

					@Override
					protected void _onTimeOut() {
						ToastUitl.showLong(mContext,"请求超时");
					}
				}));
	}
}
