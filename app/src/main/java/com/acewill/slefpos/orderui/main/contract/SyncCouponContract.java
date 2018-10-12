package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.syncbean.syncmember.SyncSingleUseCouponRes;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/6/8 10:03
 * Desc：
 */
public interface SyncCouponContract {
	abstract class Model extends BaseModel {
		public abstract Observable<SyncSingleUseCouponRes> syncSingleCouponPreview(String syncSingleCouponPay);
	}

	interface View extends BaseView {
		void returnSyncSingleCouponPreviewResult(SyncSingleUseCouponRes orderRes);
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void syncSingleCouponPreview(String syncSingleCouponPay);
	}
}
