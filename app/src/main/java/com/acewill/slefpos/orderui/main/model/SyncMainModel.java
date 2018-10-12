package com.acewill.slefpos.orderui.main.model;

import android.content.Context;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishPosResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishRegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.QryStoreResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.RegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitResponseData;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.SyncMainContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/4/19 17:50
 * Desc：
 */
public class SyncMainModel extends SyncMainContract.Model {
	private static final String TAG = "SyncMainModel";

	@Override
	public Observable<SyncInitResponseData> init(final Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS)
				.syncInit(body).map(new Func1<SyncInitResponseData, SyncInitResponseData>() {
					@Override
					public SyncInitResponseData call(SyncInitResponseData data) {
						return data;
					}
				}).compose(RxSchedulers.<SyncInitResponseData>io_main());
	}

	@Override
	public Observable<QryStoreResponseData> qryStore(final Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);
		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS)
				.syncQryStore(body).map(new Func1<QryStoreResponseData, QryStoreResponseData>() {
					@Override
					public QryStoreResponseData call(QryStoreResponseData data) {
						return data;
					}
				}).compose(RxSchedulers.<QryStoreResponseData>io_main());
	}

	@Override
	public Observable<FinishPosResponseData> finishPos(final Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);

		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS)
				.finishPos(body).map(new Func1<FinishPosResponseData, FinishPosResponseData>() {
					@Override
					public FinishPosResponseData call(FinishPosResponseData data) {
						return data;
					}
				}).compose(RxSchedulers.<FinishPosResponseData>io_main());
	}

	@Override
	public Observable<RegisterResponseData> register(Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);

		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS2 : HostType.SYNC_NORMAL_HOSTS2)
				.register(body).map(new Func1<RegisterResponseData, RegisterResponseData>() {
					@Override
					public RegisterResponseData call(RegisterResponseData data) {
						return data;
					}
				}).compose(RxSchedulers.<RegisterResponseData>io_main());
	}

	@Override
	public Observable<FinishRegisterResponseData> registerFinish(Context context, String data) {
		RequestBody body = RequestBody
				.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), data);

		return Api
				.getDefault(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS2 : HostType.SYNC_NORMAL_HOSTS2)
				.registerFinish(body)
				.map(new Func1<FinishRegisterResponseData, FinishRegisterResponseData>() {
					@Override
					public FinishRegisterResponseData call(FinishRegisterResponseData data) {
						return data;
					}
				}).compose(RxSchedulers.<FinishRegisterResponseData>io_main());
	}

}
