package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/5/30 11:34
 * Desc：
 */
public interface SyncMemberContract {
	abstract class Model extends BaseModel {
		public abstract Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberLoginInfo);
	}

	interface View extends BaseView {
		void returnSyncMemberLoginResult(SyncMemberLoginRes syncMemberLoginRes);
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void syncMemberLogin(String memberLoginInfo);
	}
}
