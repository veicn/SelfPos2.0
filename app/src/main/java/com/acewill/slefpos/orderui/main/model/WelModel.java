package com.acewill.slefpos.orderui.main.model;

import com.acewill.slefpos.api.Api;
import com.acewill.slefpos.bean.smarantbean.DishKindData;
import com.acewill.slefpos.bean.smarantbean.DishMenu;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.WelContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.exception.ApiException;

import rx.Observable;
import rx.functions.Func1;

/**
 * Author：Anch
 * Date：2018/1/27 16:21
 * Desc：
 */
public class WelModel extends WelContract.Model {

	@Override
	public Observable<DishMenu> loadMenuData(String appid, int brandid, int storeid, String sourcetype, String terminalid, String token) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST).getDishMenu(Api
				.getCacheControl(), appid, brandid, storeid, sourcetype, terminalid, token)
				.map(new Func1<DishMenu, DishMenu>() {
					@Override
					public DishMenu call(DishMenu entity) {
						if (entity.getResult()!=0)
							throw new ApiException(entity.getErrmsg());
						return entity;
					}
				}).compose(RxSchedulers.<DishMenu>io_main());
	}

	@Override
	public Observable<DishKindData> getDishKind(String appid, int brandid, int storeid, String sourcetype, String terminalid, String token) {
		return Api.getDefault(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST).getDishKind(Api
				.getCacheControl(), appid, brandid, storeid, sourcetype, terminalid, token)
				.map(new Func1<DishKindData, DishKindData>() {
					@Override
					public DishKindData call(DishKindData data) {
						if (data.getResult()!=0)
							throw new ApiException(data.getErrmsg());
						return data;
					}
				}).compose(RxSchedulers.<DishKindData>io_main());
	}
}
