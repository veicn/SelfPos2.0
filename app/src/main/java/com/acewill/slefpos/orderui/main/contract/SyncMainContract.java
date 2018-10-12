package com.acewill.slefpos.orderui.main.contract;

import android.content.Context;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishPosResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishRegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.QryStoreResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.RegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitResponseData;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/4/19 17:50
 * Desc：
 */
public interface SyncMainContract {
	abstract class Model extends BaseModel {
		public abstract Observable<SyncInitResponseData> init(Context context, String data);

		public abstract Observable<QryStoreResponseData> qryStore(Context context, String data);

		public abstract Observable<FinishPosResponseData> finishPos(Context context, String data);

		public abstract Observable<RegisterResponseData> register(Context context, String data);

		public abstract Observable<FinishRegisterResponseData> registerFinish(Context context, String data);
	}

	interface View extends BaseView {
		void returnInitResult(SyncInitResponseData data);

		void returnQueryResult(QryStoreResponseData body1);

		void returnFinishResult(FinishPosResponseData body1);

		void returnRegister(RegisterResponseData data);

		void returnFinishRegister(FinishRegisterResponseData data);
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void goinit(String data);

		public abstract void goQryStore(String data);

		public abstract void finishPos(String data);

		public abstract void register(String s);

		public abstract void registerFinish(String s);
	}
}
