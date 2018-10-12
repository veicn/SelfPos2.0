package com.acewill.slefpos.orderui.main.contract;

import android.content.Context;

import com.acewill.slefpos.base.BaseModel;
import com.acewill.slefpos.base.BasePresenter;
import com.acewill.slefpos.base.BaseView;
import com.acewill.slefpos.bean.canxingjianbean.CxjLoginModel;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallsData;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.bean.smarantstorebean.MarketData;
import com.acewill.slefpos.bean.smarantstorebean.PayTypeData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterTemplatesData;
import com.acewill.slefpos.bean.smarantstorebean.SelfPosConfigurationData;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Author：Anch
 * Date：2018/1/25 11:55
 * Desc：
 */
public interface LoadDataContract {
	abstract class Model extends BaseModel {
		public abstract Observable<PrinterData> getPrinterList();

		public abstract Observable<MarketData> getMarketList();

		public abstract Observable<PayTypeData> getPayTypeList();

		public abstract Observable<ImagesData> getImageFiles();

		public abstract Observable<SelfPosConfigurationData> getSelfposConfiguration();

		public abstract Observable<KichenStallsData> getKichenStalls();

		public abstract Observable<KdsData> getKDSInfo();

		public abstract Observable<ResponseBody> downLoadSyncData(Context context, String data);

		public abstract Observable<ResponseBody> downLoadCanxingjianData();

		public abstract Observable<LoginEntity> login(String appid, int brandid, int storeid, String tname, String terminalmac, String receiveNetOrder, String longitute, String latitute, String description, String currentVersion, String versionid);


		public abstract Observable<PrinterTemplatesData> getAllTemplates();



		public abstract Observable<CxjLoginModel> cxjUserLogin(String username, String pwd);

	}

	interface View extends BaseView {
		void returnData(boolean b);

		void clearSP();

		void returnLoginResult(LoginEntity loginEntity);

	}

	abstract class Presenter extends BasePresenter<View, Model> {
		public abstract void goLoadData(int type);

		public abstract void goLogin();

	}
}
