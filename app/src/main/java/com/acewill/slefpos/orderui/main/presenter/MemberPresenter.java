package com.acewill.slefpos.orderui.main.presenter;

import android.util.Log;

import com.acewill.slefpos.bean.canxingjianbean.CxjMemberLoginRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.wshbean.WshAccountRes;
import com.acewill.slefpos.orderui.main.contract.MemberContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Author：Anch
 * Date：2018/5/28 11:05
 * Desc：
 */
public class MemberPresenter extends MemberContract.Presenter {
	private static final String TAG ="MemberPresenter" ;

	@Override
	public void wshMemberLogin(String memberId, int type) {
		if (type==0){
			mRxManage.add(mModel.doWshMemberLogin(memberId,type)
					.subscribe(new RxSubscriber<WshAccountRes>(mContext, true) {
						@Override
						protected void _onNext(WshAccountRes entity) {
							mView.returnWshMemberLoginResult(entity);
						}

						@Override
						protected void _onError(String message) {
							mView.returnWshMemberLoginResult(null);
						}

						@Override
						protected void _onTimeOut() {
							mView.returnWshMemberLoginResult(null);
						}
					}));
		}else if ( type ==1){
			mRxManage.add(mModel.doCXJMemberLogin(memberId, type)
					.subscribe(new RxSubscriber<CxjMemberLoginRes>(mContext, true) {
						@Override
						protected void _onNext(CxjMemberLoginRes entity) {
							mView.returnCXJMemberLoginResult(entity);
						}

						@Override
						protected void _onError(String message) {
							mView.returnCXJMemberLoginResult(null);
						}

						@Override
						protected void _onTimeOut() {
							mView.returnCXJMemberLoginResult(null);
						}
					}));
		}

	}

	@Override
	public void syncMemberLogin(String memberLoginInfo) {
		Log.e(TAG, "syncMemberLogin>" + memberLoginInfo);
		mRxManage.add(mModel.doSyncMemberLogin(memberLoginInfo)
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
