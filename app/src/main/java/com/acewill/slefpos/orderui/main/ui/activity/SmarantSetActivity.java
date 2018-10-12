//package com.acewill.slefpos.orderui.main.ui.activity;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.acewill.slefpos.R;
//import com.acewill.slefpos.api.ApiConstants;
//import com.acewill.slefpos.emuee.HostType;
//import com.acewill.slefpos.base.BaseActivity;
//import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
//import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
//import com.acewill.slefpos.configure.BaseConfigure;
//import com.acewill.slefpos.configure.StoreConfigure;
//import com.acewill.slefpos.configure.TerminalConfigure;
//import com.acewill.slefpos.orderui.main.contract.MainContract;
//import com.acewill.slefpos.orderui.main.model.MainModel;
//import com.acewill.slefpos.orderui.main.presenter.MainPresenter;
//import com.jaydenxiao.common.commonutils.SPUtils;
//import com.wevey.selector.dialog.DialogOnItemClickListener;
//import com.wevey.selector.dialog.MDSelectionDialog;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//
///**
// * 这个已经不用了
// */
//public class SmarantSetActivity extends BaseActivity<MainPresenter, MainModel> implements
//		MainContract.View {
//	private static final java.lang.String TAG = "SmarantSetActivity";
//
//	@Bind(R.id.host_url)
//	TextView host_url;
//
//	@Bind(R.id.bind_et)
//	EditText bindEt;
//
//	@OnClick(R.id.start_menu)
//	public void startMenu() {
//		startAction(this);
//	}
//
//	@OnClick(R.id.bind)
//	public void doBind() {
//		mPresenter.goBind(bindEt.getText().toString().trim());
//	}
//
//
//	@Override
//	public int getLayoutId() {
//		return R.layout.act_set;
//	}
//
//	@Override
//	public void initPresenter() {
//		mPresenter.setVM(this, mModel);
//	}
//
//	@Override
//	public void initView(Bundle savedInstanceState) {
//		//		irc.setLayoutManager(new LinearLayoutManager(this));
//		//定义host
//		host_url.setText(ApiConstants
//				.getType() == HostType.TEST_HOST ? ApiConstants.TEST_HOST : ApiConstants.NORMAL_HOST);
//	}
//
//
//	@OnClick(R.id.host_url)
//	public void changeHostUrl() {
//		showSelectDialog();
//		//		initHost(ApiConstants
//		//				.getType() == HostType.TEST_HOST ? HostType.NORMAL_HOST : HostType.TEST_HOST);
//	}
//
//	private void showSelectDialog() {
//		ArrayList<String> list = new ArrayList<>();
//		list.add("智慧快餐");//2
//		list.add("同步时");//3
//		MDSelectionDialog.Builder builder = new MDSelectionDialog.Builder(mContext);
//		final MDSelectionDialog   dialog  = new MDSelectionDialog(builder);
//		builder.setOnItemListener(new DialogOnItemClickListener() {
//			@Override
//			public void onItemClick(Button button, int position) {
//				Log.e(TAG, "position>" + position);
//				switch (position) {
//					case 0:
//						initHost(HostType.IS_SMARANT_DEBUG ? HostType.TEST_HOST : HostType.NORMAL_HOST);
//						break;
//					case 1:
//						initHost(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS);
//						break;
//					default:
//						break;
//
//				}
//				dialog.dismiss();
//			}
//		});
//		dialog.setDataList(list);
//		dialog.show();
//	}
//
//	private void initHost(int hostType) {
//		ApiConstants.setType(hostType);
//		switch (hostType) {
//			case HostType.TEST_HOST:
//				host_url.setText(ApiConstants.TEST_HOST);
//				break;
//			case HostType.NORMAL_HOST:
//				host_url.setText(ApiConstants.NORMAL_HOST);
//				break;
//			case HostType.SYNC_TEST_HOSTS:
//				host_url.setText(ApiConstants.SYNC_TEST_HOST);
//				break;
//			case HostType.SYNC_NORMAL_HOSTS:
//				host_url.setText(ApiConstants.SYNC_NORMAL_HOST);
//				break;
//			default:
//				host_url.setText("");
//				break;
//		}
//	}
//
//	@Override
//	public void showLoading(String title) {
//
//	}
//
//	@Override
//	public void stopLoading() {
//
//	}
//
//	@Override
//	public void showErrorTip(String msg) {
//
//	}
//
//	@Override
//	public void returnBindResult(BindEntity entity) {
//		BaseConfigure.init(entity.getAppid(), entity.getStoreid(), entity.getBrandid());
//		StoreConfigure.init(entity.getLongitutde(), entity.getLatitude(), entity.getSname(), entity
//				.getPhone(), entity.getMobile(), entity.getAddress(), entity.getBrandName(), entity
//				.getStoreEndTime());
//		TerminalConfigure.init(entity.getTname(), entity.getTerminalMac(), entity
//				.getCurrentVersion(), String.valueOf(entity.getTerminalid()));
//
//		SPUtils.setSharedStringData(this, "appid", entity.getAppid());
//		SPUtils.setSharedIntData(this, "storeid", entity.getStoreid());
//		SPUtils.setSharedIntData(this, "brandid", entity.getBrandid());
//		SPUtils.setSharedStringData(this, "tname", entity.getTname());
//		SPUtils.setSharedStringData(this, "terminalmac", entity.getTerminalMac());
//		SPUtils.setSharedStringData(this, "longitute", entity.getLongitutde());
//		SPUtils.setSharedStringData(this, "latitute", entity.getLatitude());
//		SPUtils.setSharedStringData(this, "currentVersion", entity.getCurrentVersion());
//		SPUtils.setSharedStringData(this, "versionid", "selfhelp_ordermachine");
//		mPresenter.goLogin();
//	}
//
//	@Override
//	public void returnLoginResult(LoginEntity loginEntity) {
//		BaseConfigure.setToken(loginEntity.getToken());
//		//		startAction(this);
//	}
//
//	/**
//	 * 入口
//	 *
//	 * @param activity
//	 */
//	public static void startAction(Activity activity) {
//		Intent intent = new Intent(activity, LoadDataActivity.class);
//		activity.startActivity(intent);
//		activity.overridePendingTransition(R.anim.fade_in,
//				com.jaydenxiao.common.R.anim.fade_out);
//	}
//
//
//	private void showAuthorityDialog() {
//		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
//		builder.setTitle("提示");
//		builder.setMessage("缺少程序运行时必要的权限");
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				finish();
//			}
//		});
//		builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialogInterface, int i) {
//				//								AppApplication.getAppContext().exitApp();
//
//			}
//		});
//		builder.show();
//	}
//}
