package com.acewill.slefpos.system.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.smarantstorebean.BaseEntity;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/1/24 19:19
 * Desc：
 */
public interface CommonSetContract {
	abstract class Model extends BaseModel {
		public abstract Observable<BindEntity> bind(String terminalCode);

		public abstract Observable<LoginEntity> login(String appid, int brandid, int storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid);

		public abstract Observable<BaseEntity> unbind(String terminalmac, String token);
	}

	interface View extends BaseView {
		void returnBindResult(BindEntity bindEntity);

		void returnUnBindResult(BaseEntity baseEntity);

		void returnLoginResult(LoginEntity loginEntity);

		void clearSP();
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void goBind(String terminalCode);

		public abstract void unBind();

		public abstract void goLogin();
	}
}
