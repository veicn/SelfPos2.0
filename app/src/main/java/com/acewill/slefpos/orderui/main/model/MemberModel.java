package com.acewill.slefpos.orderui.main.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.canxingjianbean.CxjMemberLoginRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.wshbean.WshAccountRes;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.MemberContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/5/28 11:05
 * Desc：
 */
public class MemberModel extends MemberContract.Model {
	@Override
	public Observable<WshAccountRes> doWshMemberLogin(String memberId, int type) {
			return Api
					.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
					.wshMemberLogin(Api.getCacheControl(),
							BaseConfigure.getAppid(), BaseConfigure.getBrandid(), BaseConfigure
									.getStoreid(), memberId, BaseConfigure.getToken())
					.map(new Func1<WshAccountRes, WshAccountRes>() {
						@Override
						public WshAccountRes call(WshAccountRes orderRes) {
							return orderRes;
						}
					}).compose(RxSchedulers.<WshAccountRes>io_main());
	}

	@Override
	public Observable<CxjMemberLoginRes> doCXJMemberLogin(String memberId, int type) {
		return Api
				.getDefault(HostType.CANXINGJIAIN_IP_ADRESS)
				.getMemberInfo( memberId)
				.map(new Func1<CxjMemberLoginRes, CxjMemberLoginRes>() {
					@Override
					public CxjMemberLoginRes call(CxjMemberLoginRes orderRes) {
						return orderRes;
					}
				}).compose(RxSchedulers.<CxjMemberLoginRes>io_main());
	}

	@Override
	public Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberLoginInfo) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType
						.parse("application/json; charset=utf-8"), memberLoginInfo);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncMemberLogin(body)
				.map(new Func1<SyncMemberLoginRes, SyncMemberLoginRes>() {
					@Override
					public SyncMemberLoginRes call(SyncMemberLoginRes syncMemberLoginRes) {
						return syncMemberLoginRes;
					}
				}).compose(RxSchedulers.<SyncMemberLoginRes>io_main());
	}

	//	@Override
	//	public Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberInfo) {
	//		return Api.getDefault(HostType.SYNC_MEMBER).syncMemberLogin();
	//	}


	//	@Override
	//	public Observable<WshAccountRes> memberLogin(String memberId) {

	//	}
}
