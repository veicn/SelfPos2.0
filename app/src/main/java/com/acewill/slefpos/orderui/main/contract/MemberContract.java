package com.acewill.slefpos.orderui.main.contract;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.canxingjianbean.CxjMemberLoginRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.wshbean.WshAccountRes;

import rx.Observable;

/**
 * Author：Anch
 * Date：2018/5/28 11:05
 * Desc：
 */
public interface MemberContract {
	abstract class Model extends BaseModel {
		public abstract Observable<WshAccountRes> doWshMemberLogin(String memberId, int type);

		public abstract Observable<CxjMemberLoginRes> doCXJMemberLogin(String memberId, int type);

		public abstract Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberId);
		//		public abstract Observable<SyncMemberLoginRes> doSyncMemberLogin(String memberInfo);
	}

	interface View extends BaseView {
		void returnWshMemberLoginResult(WshAccountRes wshAccountRes);

		void returnCXJMemberLoginResult(CxjMemberLoginRes wshAccountRes);

		void returnSyncMemberLoginResult(SyncMemberLoginRes syncMemberLoginRes);
		//		void returnSyncMemberLoginResult(SyncMemberLoginRes wshAccountRes);
	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void wshMemberLogin(String memberId, int type);

		public abstract void syncMemberLogin(String terminalCode);
		//		public abstract void syncMemberLogin(String memberInfo);
	}
}
