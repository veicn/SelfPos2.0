package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/1/24 19:19
 * Desc：
 */
public interface MainContract {
	abstract class Model extends BaseModel {
		public abstract Observable<BindEntity> bind(String terminalCode);

		public abstract Observable<LoginEntity> login(String appid, int brandid, int storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid);


	}

	interface View extends BaseView {
		void returnBindResult(BindEntity bindEntity);
		void returnLoginResult(LoginEntity loginEntity);
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void goBind(String terminalCode);

		public abstract void goLogin();


	}
}
