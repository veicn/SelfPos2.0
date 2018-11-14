package com.acewill.slefpos.orderui.main.ui.syncactivity;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishPosResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.FinishRegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.QryStoreResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.RegisterResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitRequestData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncInitResponseData;
import com.acewill.slefpos.bean.syncbean.syncinit.SyncRegisterRequestData;
import com.acewill.slefpos.orderui.main.contract.SyncMainContract;
import com.acewill.slefpos.orderui.main.model.SyncMainModel;
import com.acewill.slefpos.orderui.main.presenter.SyncMainPresenter;
import com.acewill.slefpos.orderui.main.ui.activity.LoadDataActivity;
import com.acewill.slefpos.orderui.main.utils.RegisterUtil;
import com.acewill.slefpos.orderui.main.utils.SyncGenerate;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.google.gson.Gson;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.jaydenxiao.common.compressorutils.FileUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：Anch
 * Date：2018/4/19 17:46
 * Desc：
 */
public class SyncSetActivity extends BaseActivity<SyncMainPresenter, SyncMainModel> implements
		SyncMainContract.View, OnLoadMoreListener, OnRefreshListener {

	@Bind(R.id.init)
	Button         init;
	@Bind(R.id.system_code)
	EditText       system_code;
	@Bind(R.id.install_code)
	EditText       install_code;
	@Bind(R.id.irc)
	IRecyclerView  irc;
	@Bind(R.id.shop_list_ly)
	RelativeLayout shop_list_ly;
	@Bind(R.id.loadedTip)
	LoadingTip     loadedTip;
	private CommonRecycleViewAdapter<QryStoreResponseData.BodyBean.FeStoreshop> adapter;

	@Override
	public int getLayoutId() {
		return R.layout.act_sync_set;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}


	private SyncInitRequestData.HeaderBean getCommonHeaderBean(int i) {
		SyncInitRequestData.HeaderBean bean     = new SyncInitRequestData.HeaderBean();
		Date                           date     = new Date();
		SimpleDateFormat               sformat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat               sformat2 = new SimpleDateFormat("HH:mm:ss");
		bean.setVersion("18.8.0");
		bean.setTxTime(sformat1.format(date) + "T" + sformat2.format(date));
		bean.setTimeZone("Asia/Shanghai");
		bean.setLocale("zh_CN");
		if (i == 0)
			bean.setInstanceSid("5");
		else
			bean.setInstanceSid(RegisterUtil
					.buildInstanceSid(SPUtils.getSharedStringData(mContext, "companyOuid"), SPUtils
							.getSharedStringData(this, "storeshopOuid"), SPUtils
							.getSharedStringData(mContext, "terminalOuid")));
		return bean;
	}


	private SyncInitRequestData getRequestData(int type) {
		SyncInitRequestData            data            = new SyncInitRequestData();
		SyncInitRequestData.HeaderBean bean            = null;
		String                         clientReference = null;
		String                         txReference     = null;
		String                         cipherKey       = null;
		switch (type) {
			case 0://init
				bean = getCommonHeaderBean(0);
				SyncInitRequestData.BodyBean bodyBean1 = new SyncInitRequestData.BodyBean();
				clientReference = SyncGenerate.getUUID(UUID.randomUUID());
				txReference = SyncGenerate.getUUID(UUID.randomUUID());
				SPUtils.setSharedStringData(this, "clientReference", clientReference);
				SPUtils.setSharedStringData(this, "posId", clientReference);
				SPUtils.setSharedStringData(this, "terminalOuid", clientReference);
				SPUtils.setSharedStringData(this, "txReference", txReference);
				data.setBody(bodyBean1);
				break;
			case 1:
				//query
				bean = getCommonHeaderBean(0);
				SyncInitRequestData.qryStoreBodyBean bodyBean2 = new SyncInitRequestData.qryStoreBodyBean();
				clientReference = SPUtils.getSharedStringData(this, "clientReference");
				txReference = SPUtils.getSharedStringData(this, "txReference");
				String ratepoId = SyncGenerate.getEncodeCipherKey(mContext, SPUtils
						.getSharedStringData(mContext, "systemCode"));
				String registerAuthCode = SyncGenerate.getEncodeCipherKey(mContext, SPUtils
						.getSharedStringData(mContext, "installCode"));
				bodyBean2.setRatepoId(ratepoId);
				bodyBean2.setRegisterAuthCode(registerAuthCode);
				data.setBody(bodyBean2);
				break;
			case 2://finishPos
				bean = getCommonHeaderBean(0);
				SyncInitRequestData.FinishPoseBodyBean bodyBean3 = new SyncInitRequestData.FinishPoseBodyBean();
				clientReference = SPUtils.getSharedStringData(this, "clientReference");
				txReference = SPUtils.getSharedStringData(this, "txReference");
				bodyBean3.setCompanyOuid(SPUtils.getSharedStringData(this, "companyOuid"));
				bodyBean3.setStoreshopOuid(SPUtils.getSharedStringData(this, "storeshopOuid"));
				data.setBody(bodyBean3);
				break;
			case 3:
				bean = getCommonHeaderBean(1);
				SyncInitRequestData.DownLoadRequestBean bodyBean4 = new SyncInitRequestData.DownLoadRequestBean();
				clientReference = SPUtils.getSharedStringData(this, "clientReference");
				txReference = SPUtils.getSharedStringData(this, "txReference");
				bodyBean4.setCompanyOuid(SPUtils.getSharedStringData(this, "companyOuid"));
				bodyBean4.setStoreshopOuid(SPUtils.getSharedStringData(this, "storeshopOuid"));
				bodyBean4.setVersionSeqNumber("1");
				data.setBody(bodyBean4);
				break;
		}
		bean.setClientReference(clientReference);
		bean.setTxReference(txReference);
		data.setHeader(bean);
		return data;
	}


	@OnClick(R.id.init)
	public void init() {
		if (!TextUtils.isEmpty(SPUtils.getSharedStringData(mContext, "instanceSid"))) {
			Log.e("SyncSplashActivity", "lalal");
			return;
		}
		if (TextUtils.isEmpty(system_code.getText().toString().trim())) {
			showShortToast("公司系统编码不能为空!");
			return;
		}
		if (TextUtils.isEmpty(install_code.getText().toString().trim())) {
			showShortToast("安装授权码不能为空!");
			return;
		}
		SPUtils.setSharedStringData(mContext, "systemCode", system_code.getText().toString()
				.trim());
		SPUtils.setSharedStringData(mContext, "installCode", install_code.getText().toString()
				.trim());
		startProgressDialog();
		SyncInitRequestData data = getRequestData(0);
		mPresenter.goinit(new Gson().toJson(data));
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		if (!TextUtils.isEmpty(SPUtils.getSharedStringData(mContext, "instanceSid"))) {
			system_code.setVisibility(View.GONE);
			install_code.setVisibility(View.GONE);
			init.setVisibility(View.GONE);
		}
		FileUtil.createSyncImageFile();
	}

	@Override
	public void showLoading(String title) {

	}

	@Override
	public void stopLoading() {
		stopProgressDialog();
	}

	@Override
	public void showErrorTip(String msg) {

	}

	private SyncRegisterRequestData mRegisterRequestData;

	@Override
	public void returnInitResult(SyncInitResponseData data) {
		if (data.getHeader() != null) {
			if ("200".equals(data.getHeader().getStatusCode())) {
				SPUtils.setSharedStringData(mContext, "cipherKey", data.getBody()
						.getCipherKey());
				SPUtils.setSharedStringData(mContext, "instanceSid", data.getHeader()
						.getInstanceSid());
				SPUtils.setSharedStringData(mContext, "version", data.getHeader()
						.getVersion());
				//				SyncInitRequestData initRequestData = getRequestData(1);
				mRegisterRequestData = getSyncRegisterData();
				mPresenter.register(new Gson().toJson(mRegisterRequestData));
				//				mPresenter.goQryStore(new Gson().toJson(initRequestData));
			} else {
				stopProgressDialog();
				showAleartDialog("初始化失败", data.getHeader().getStatusDescription());
			}
		} else {
			stopProgressDialog();
			showAleartDialog("发生异常", "请联系工作人员!");
		}
	}

	private SyncRegisterRequestData getSyncRegisterData() {
		SyncRegisterRequestData data = new SyncRegisterRequestData();
		data.setTerminalOuid(SPUtils
				.getSharedStringData(mContext, "clientReference"));
		data.setTerminalType("5");
		data.setCompanyId(SPUtils.getSharedStringData(mContext, "systemCode"));
		data.setAppVersion(SPUtils.getSharedStringData(mContext, "version"));
		data.setAuthCode(RegisterUtil
				.getAuthCode(data.getTerminalOuid(), data.getAppVersion(), SPUtils
						.getSharedStringData(mContext, "installCode")));
		return data;
	}

	@Override
	public void returnQueryResult(QryStoreResponseData body1) {
		if (!body1.getHeader().isSuccess()) {
			String code        = body1.getHeader().getStatusCode();
			String description = body1.getHeader().getStatusDescription();
			ToastUitl.showLong(mContext, description);
			stopProgressDialog();
			return;
		}
		QryStoreResponseData.BodyBean               body2   = body1.getBody();
		QryStoreResponseData.BodyBean.FeCompanyBean company = body2.getFeCompany();

		SPUtils.setSharedStringData(mContext, "companyOuid", company.getOuid());


		//隐藏输入框
		shop_list_ly.setVisibility(View.VISIBLE);
		init.setVisibility(View.GONE);
		system_code.setVisibility(View.GONE);
		install_code.setVisibility(View.GONE);
		initList(body1.getBody().getFeStoreshop());
	}

	@Override
	public void returnFinishResult(FinishPosResponseData body1) {
		SPUtils.setSharedStringData(mContext, "instanceSid", body1.getBody()
				.getInstanceSid());
		SPUtils.setSharedStringData(mContext, "posId", body1.getBody()
				.getPos().getPosId());
		startActivity(LoadDataActivity.class);
		finish();
	}

	@Override
	public void returnRegister(RegisterResponseData data) {
		if (data != null) {
			if (200 == data.getCode()) {
				RegisterResponseData.DataBean companyData = data.getData();
				SPUtils.setSharedStringData(mContext, "companyOuid", companyData.getOuid());
				//隐藏输入框
				shop_list_ly.setVisibility(View.VISIBLE);
				init.setVisibility(View.GONE);
				system_code.setVisibility(View.GONE);
				install_code.setVisibility(View.GONE);
				List<QryStoreResponseData.BodyBean.FeStoreshop> storeshop = toFeStoreshop(companyData
						.getStoreshops());
				initList(storeshop);
			} else {
				stopProgressDialog();
				ToastUitl.showLong(mContext, data.getMessage());
				SPUtils.setSharedStringData(mContext, "instanceSid","");
			}
		} else {
			stopProgressDialog();
			ToastUitl.showLong(mContext, "注册失败!");
		}
	}

	@Override
	public void returnFinishRegister(FinishRegisterResponseData data) {
		//// TODO: 2018/9/17 anch


		//		SPUtils.setSharedStringData(mContext, "posId", body1.getBody()
		//				.getPos().getPosId());
		startActivity(LoadDataActivity.class);
		finish();
	}

	private List<QryStoreResponseData.BodyBean.FeStoreshop> toFeStoreshop(List<RegisterResponseData.DataBean.StoreshopsBean> storeshops) {
		List<QryStoreResponseData.BodyBean.FeStoreshop> list = new ArrayList<>();
		for (RegisterResponseData.DataBean.StoreshopsBean shop : storeshops) {
			QryStoreResponseData.BodyBean.FeStoreshop storeshop = new QryStoreResponseData.BodyBean.FeStoreshop();
			storeshop.setName(shop.getName());
			storeshop.setOuid(shop.getOuid());
			storeshop.setRatepoId(shop.getStoreshopSn());
			storeshop.setStoreshopId(shop.getStoreshopId());
			list.add(storeshop);
		}
		return list;
	}

	private void initList(final List<QryStoreResponseData.BodyBean.FeStoreshop> storeshop) {
		adapter = new CommonRecycleViewAdapter<QryStoreResponseData.BodyBean.FeStoreshop>(mContext, R.layout.item_ll_set) {
			@Override
			public void convert(ViewHolderHelper helper, final QryStoreResponseData.BodyBean.FeStoreshop feStoreshop) {
				TextView view = helper.getView(R.id.tv);
				view.setText(feStoreshop.getName());
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						SPUtils.setSharedStringData(mContext, "shopId", feStoreshop.getOuid());
						SPUtils.setSharedStringData(mContext, "storeshopOuid", feStoreshop
								.getOuid());

						SPUtils.setSharedStringData(mContext, "shopNo", feStoreshop
								.getStoreshopId());
						SPUtils.setSharedStringData(mContext, "feStoreshopName", feStoreshop
								.getName());


						//						SyncInitRequestData data = getRequestData(2);
						mRegisterRequestData.setCompanyOuid(SPUtils
								.getSharedStringData(mContext, "companyOuid"));
						mRegisterRequestData.setStoreshopOuid(feStoreshop
								.getOuid());
						mPresenter.registerFinish(new Gson().toJson(mRegisterRequestData));
						//						mPresenter.finishPos(new Gson().toJson(data));


					}
				});
			}
		};
		irc.setAdapter(adapter);
		irc.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
		irc.setOnLoadMoreListener(this);
		irc.setOnRefreshListener(this);
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		adapter.addAll(storeshop);
		loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
		stopProgressDialog();
	}

	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}
}
