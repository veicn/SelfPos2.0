package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.bean.smarantbean.DishKindData;
import com.acewill.slefpos.bean.smarantbean.DishMenu;
import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/1/27 16:21
 * Desc：
 */
public interface WelContract {
	abstract class Model extends BaseModel {
		public abstract Observable<DishMenu> loadMenuData(String appid, int brandid, int storeid, String sourcetype, String terminalid, String token);
		public abstract Observable<DishKindData> getDishKind(String appid, int brandid, int storeid, String sourcetype, String terminalid, String token);
		//		public abstract Observable<DishMenu> loadMenuData(String appid, String brandid, String storeid, String sourceType);
		//		abstract List loadKindData();
	}

	interface View extends BaseView {
		void loadDataSuccess();
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void goLoadMenuData();
	}
}
