package com.acewill.slefpos.orderui.main.presenter;

import com.acewill.slefpos.bean.smarantbean.DishKindData;
import com.acewill.slefpos.bean.smarantbean.DishMenu;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.orderui.main.contract.WelContract;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author：Anch
 * Date：2018/1/27 16:21
 * Desc：
 */
public class WelPresenter extends WelContract.Presenter {
	private int i = 0;

	@Override
	public void goLoadMenuData() {


		Observable<DishMenu> dishMenuObservable = mModel
				.loadMenuData(BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), "1", TerminalConfigure
						.getTerminalmac(), BaseConfigure.getToken());
		Observable<DishKindData> dishKindDataObservable = mModel
				.getDishKind(BaseConfigure.getAppid(), BaseConfigure
						.getBrandid(), BaseConfigure.getStoreid(), "1", TerminalConfigure
						.getTerminalmac(), BaseConfigure.getToken());

		mRxManage.add(Observable.merge(dishMenuObservable, dishKindDataObservable)
				.subscribeOn(Schedulers.io())
				.subscribe(new RxSubscriber<Object>(mContext, true) {
					@Override
					protected void _onNext(Object o) {
						i++;
						if (o instanceof DishKindData)
							SmarantDataProvider.setDishKindData((DishKindData) o);
						if (o instanceof DishMenu)
							SmarantDataProvider.setDishMenu((DishMenu) o);
						if (i == 2)
							mView.loadDataSuccess();
					}

					@Override
					protected void _onError(String message) {
						ToastUitl.showShort(mContext, message);
					}

					@Override
					protected void _onTimeOut() {

					}
				}));
	}
}
