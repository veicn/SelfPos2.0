package com.acewill.slefpos.bean.syncmember;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.configure.SyncConfig;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.SPUtils;

/**
 * Author：Anch
 * Date：2018/5/30 10:40
 * Desc：
 */
public class SyncMember {
	private static RxManager  mRxManager;
	private static SyncMember mMember;

	/**
	 * 获取购物车对象
	 *
	 * @return
	 */
	public static SyncMember getInstance() {
		if (mMember == null) {
			mMember = new SyncMember();
			mRxManager = new RxManager();
		}
		return mMember;
	}


	public SyncMemberLoginReq getMemberInfo(String memberid) {
		SyncMemberLoginReq req = new SyncMemberLoginReq();
		req.setConsumerKey(SyncConfig.CONSUMERKEY);
		req.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		SyncMemberLoginReq.DataBean bean1 = new SyncMemberLoginReq.DataBean();
		bean1.setAuthCode(memberid);
		bean1.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
		req.setData(bean1);
		return req;

	}
}
