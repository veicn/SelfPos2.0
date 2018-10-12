package com.acewill.slefpos.system.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.smarantstorebean.BaseEntity;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.system.contract.CommonSetContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.exception.ApiException;

import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/1/24 19:19
 * Desc：
 */
public class CommonSetModel extends CommonSetContract.Model {
	@Override
	public Observable<BindEntity> bind(String terminalMac) {
		return filterStatus(Api
				.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.bind(Api.getCacheControl(), terminalMac, "2"));


	}

	@Override
	public Observable<LoginEntity> login(String appid, int brandid, int storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.login(Api
						.getCacheControl(), appid, brandid, storeid, tname, terminalmac, receiveNetOrder, longitute, latitute, description, currentVersion, versionid)
				.map(new Func1<LoginEntity, LoginEntity>() {
					@Override
					public LoginEntity call(LoginEntity entity) {
						if (!"0".equals(entity.getResult()))
							throw new ApiException(entity.getErrmsg());
						return entity;
					}
				}).compose(RxSchedulers.<LoginEntity>io_main());

	}

	@Override
	public Observable<BaseEntity> unbind(String terminalmac, String token) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST)
				.unbind(Api.getCacheControl(), terminalmac, "2", token)
				.map(new Func1<BaseEntity, BaseEntity>() {
					@Override
					public BaseEntity call(BaseEntity entity) {
						if (!"0".equals(entity.getResult()))
							throw new ApiException(entity.getErrmsg());
						return entity;
					}
				}).compose(RxSchedulers.<BaseEntity>io_main());
	}
}
