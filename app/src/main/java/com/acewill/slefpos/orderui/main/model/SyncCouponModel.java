package com.acewill.slefpos.orderui.main.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.SyncCouponContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/6/8 10:03
 * Desc：
 */
public class SyncCouponModel extends SyncCouponContract.Model {
	@Override
	public Observable<SyncSingleUseCouponRes> syncSingleCouponPreview(String syncSingleCouponPay) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType
						.parse("application/json; charset=utf-8"), syncSingleCouponPay);
		return Api.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_MEMBER : HostType.SYNC_MEMBER_NORMAL)
				.syncPreviewCouponSingle(body)
				.map(new Func1<SyncSingleUseCouponRes, SyncSingleUseCouponRes>() {
					@Override
					public SyncSingleUseCouponRes call(SyncSingleUseCouponRes res) {
						return res;
					}
				}).compose(RxSchedulers.<SyncSingleUseCouponRes>io_main());
	}
}
