package com.acewill.slefpos.orderui.main.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.SyncMemberContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/5/30 11:34
 * Desc：
 */
public class SyncMemberModel extends SyncMemberContract.Model {
	@Override
	public Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberLoginInfo) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType
						.parse("application/json; charset=utf-8"), memberLoginInfo);
		return Api.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL).syncMemberLogin(body)
				.map(new Func1<SyncMemberLoginRes, SyncMemberLoginRes>() {
					@Override
					public SyncMemberLoginRes call(SyncMemberLoginRes syncMemberLoginRes) {
						return syncMemberLoginRes;
					}
				}).compose(RxSchedulers.<SyncMemberLoginRes>io_main());
	}
}
