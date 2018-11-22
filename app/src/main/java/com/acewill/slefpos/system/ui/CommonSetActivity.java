package com.acewill.slefpos.system.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.acewill.slefpos.R;
import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.smarantstorebean.BaseEntity;
import com.acewill.slefpos.bean.smarantstorebean.BindEntity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.FileCallBack;
import com.acewill.slefpos.okhttp.callback.StringCallback;
import com.acewill.slefpos.orderui.main.ui.eventbus.ResultEvent;
import com.acewill.slefpos.print.PrintManager;
import com.acewill.slefpos.system.bean.Store;
import com.acewill.slefpos.system.contract.CommonSetContract;
import com.acewill.slefpos.system.dialog.CommonEditDialog;
import com.acewill.slefpos.system.dialog.DownLoadProgressDialog;
import com.acewill.slefpos.system.dialog.NewVersionDialog;
import com.acewill.slefpos.system.dialog.SetDialog;
import com.acewill.slefpos.system.model.CommonSetModel;
import com.acewill.slefpos.system.presenter.CommonSetPresenter;
import com.acewill.slefpos.utils.DownLoadAPPUtils;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;
import rx.functions.Action1;


/**
 * Created by hzc on 2017/3/30.
 * 如果之前有登录过的话，不用login 都已经you数据的了
 */
public class CommonSetActivity extends BaseActivity<CommonSetPresenter, CommonSetModel> implements
		View.OnClickListener, CommonSetContract.View {

	@Bind(R.id.tv_print_id)
	TextView     tv_print_id;
	@Bind(R.id.tv_pay_type)
	TextView     tv_pay_type;
	@Bind(R.id.updata_tv)
	TextView     updata_tv;
	@Bind(R.id.tv_qr_init)
	TextView     tv_qr_init;
	@Bind(R.id.tv_cache_size)
	TextView     tv_cache_size;
	@Bind(R.id.tv_show_table_num)
	TextView     tv_show_table_num;
	@Bind(R.id.tv_show_banner)
	TextView     tv_show_banner;
	@Bind(R.id.tv_scantype)
	TextView     tv_scantype;
	@Bind(R.id.tv_textviewtype)
	TextView     tv_textviewtype;
	@Bind(R.id.et_canxingjian_host_ip)
	EditText     et_canxingjian_host_ip;
	@Bind(R.id.et_createorder_ip)
	EditText     et_createorder_ip;
	@Bind(R.id.tv_changelanguage)
	TextView     tv_changelanguage;
	@Bind(R.id.tv_inner_print_id)
	TextView     tv_inner_print_id;
	@Bind(R.id.tv_printticket_type)
	TextView     tv_printticket_type;
	@Bind(R.id.tv_print_type)
	TextView     tv_print_type;
	@Bind(R.id.store_ip)
	TextView     store_ip;
	@Bind(R.id.tv_bind_status)
	TextView     tv_bind_status;
	@Bind(R.id.store_info_ll)
	LinearLayout store_info_ll;
	@Bind(R.id.ll_set_start_callnumber)
	LinearLayout ll_set_start_callnumber;
	@Bind(R.id.ll_set_start_callnumber_view)
	View         ll_set_start_callnumber_view;
	@Bind(R.id.ll_show_table_num)
	LinearLayout ll_show_table_num;
	@Bind(R.id.ll_set_validate_memberno)
	LinearLayout ll_set_validate_memberno;
	@Bind(R.id.ll_pay_type)
	LinearLayout ll_pay_type;
	@Bind(R.id.ll_set_jiyejia_ip)
	LinearLayout ll_set_jiyejia_ip;
	@Bind(R.id.ll_set_canxingjian_ip)
	LinearLayout ll_set_canxingjian_ip;
	@Bind(R.id.ll_set_printticket_type)
	LinearLayout ll_set_printticket_type;
	@Bind(R.id.store_info_tv)
	TextView     store_info_tv;
	@Bind(R.id.tv_validate_memberno)
	TextView     tv_validate_memberno;
	@Bind(R.id.tv_set_start_callnumber)
	TextView     tv_set_start_callnumber;
	@Bind(R.id.ll_set_validate_memberno_view)
	View         ll_set_validate_memberno_view;
	@Bind(R.id.ll_show_table_num_view)
	View         ll_show_table_num_view;
	@Bind(R.id.ll_pay_type_view)
	View         ll_pay_type_view;
	@Bind(R.id.ll_set_jiyejia_ip_view)
	View         ll_set_jiyejia_ip_view;


	private static final String TAG = "CommonSetActivity";


	private String[] qr_init_swift = new String[]{"0",
			"1"};
	Dialog bindDialog;
	private boolean hasNewVersion;
	private String  mPath;
	private String  mVersion;
	private String mDescription = "暂无";

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.e(TAG, "onNewIntent");
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_set;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		initClickListener();
		initSwift();
		initSetUI();
		initListener();
		terminalLogin();
		refreshUI();
		addTextChangeListener();
	}

	private void addTextChangeListener() {
		et_createorder_ip.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				SPUtils.setSharedStringData(mContext, "jyjAddress", s.toString().trim());
				StoreConfigure.setJyjAddress(SPUtils.getSharedStringData(mContext, "jyjAddress"));
			}
		});
		et_canxingjian_host_ip.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				SPUtils.setSharedStringData(mContext, "canxingjian_ip_address", s.toString()
						.trim());
				StoreConfigure.setJyjAddress(SPUtils
						.getSharedStringData(mContext, "canxingjian_ip_address"));
			}
		});

	}

	private void initClickListener() {
		findViewById(R.id.ll_bind).setOnClickListener(this);
		findViewById(R.id.btn_set_print).setOnClickListener(this);
		findViewById(R.id.ll_print_select).setOnClickListener(this);
		findViewById(R.id.ll_pay_type).setOnClickListener(this);
		findViewById(R.id.ll_finish).setOnClickListener(this);
		findViewById(R.id.ll_updata_layout).setOnClickListener(this);
		findViewById(R.id.ll_upload_catalog).setOnClickListener(this);
		findViewById(R.id.ll_clear_catalog).setOnClickListener(this);
		findViewById(R.id.ll_set_qr_init_layout).setOnClickListener(this);
		findViewById(R.id.ll_store_ip).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.ll_show_table_num).setOnClickListener(this);
		findViewById(R.id.ll_download_videos).setOnClickListener(this);
		findViewById(R.id.ll_show_banner).setOnClickListener(this);
		findViewById(R.id.ll_set_scantype).setOnClickListener(this);
		findViewById(R.id.ll_set_textviewtype).setOnClickListener(this);
		findViewById(R.id.ll_set_changelanguage).setOnClickListener(this);
		findViewById(R.id.ll_set_checkorder).setOnClickListener(this);
		findViewById(R.id.ll_set_inner_print).setOnClickListener(this);
		findViewById(R.id.ll_set_validate_memberno).setOnClickListener(this);
		findViewById(R.id.ll_set_start_callnumber).setOnClickListener(this);
		findViewById(R.id.ll_set_printticket_type).setOnClickListener(this);
		findViewById(R.id.print_test).setOnClickListener(this);
	}

	/*根据前面的设置去回显相关数据*/
	private void initSetUI() {
		initPayType(SPUtils.getSharedIntData(this, "payType"));//设置支付方式   正扫 或者是 反扫

		initShowTable(SPUtils.getSharedIntData(this, "SHOWTABLE"));

		initScanType(SPUtils.getSharedIntData(this, "scantype"));
		tv_cache_size.setText(FileUtil.getCacheSize());
		tv_print_id.setText(SPUtils.getSharedStringData(this, "productId"));
		tv_qr_init.setText(SPUtils.getSharedIntData(this, "QRINIT") + "");
		tv_show_banner
				.setText(getResources().getStringArray(R.array.showbanner)[SPUtils
						.getSharedIntData(this, "showBanner")]);
		et_createorder_ip.setText(SPUtils.getSharedStringData(this, "jyjAddress"));

		tv_textviewtype.setText(getResources()
				.getStringArray(R.array.textviewtype)[SPUtils
				.getSharedIntData(this, "getTextViewType")]);
		tv_changelanguage
				.setText(getResources().getStringArray(R.array.language)[SPUtils
						.getSharedIntData(this, "changelanguage")]);

		tv_inner_print_id.setText(getResources()
				.getStringArray(R.array.innerprinter)[SPUtils
				.getSharedIntData(this, "innerprinter")]);

		et_canxingjian_host_ip.setText(SPUtils
				.getSharedStringData(this, "canxingjian_ip_address"));

		tv_validate_memberno.setText(getResources()
				.getStringArray(R.array.validate_member)[SPUtils
				.getSharedIntData(this, "validatememberno")]);


		tv_printticket_type.setText(getResources()
				.getStringArray(R.array.printtickettype)[SPUtils
				.getSharedIntData(this, "printtickettype")]);


		if (SPUtils.getSharedIntData(mContext, "call_number") != 0)
			tv_set_start_callnumber
					.setText("起始前缀 " + SPUtils.getSharedIntData(mContext, "call_number"));
		//		phone_test_swift
		//				.setChecked(SPUtils.getSharedBooleanData(mContext, "USE_WECHAT_PAY", true));

		phone_test_swift
				.setChecked(SPUtils.getSharedBooleanData(mContext, "PHONE_TEST", false));
		ali_payset_swift.setChecked(SPUtils.getSharedBooleanData(mContext, "USE_ALI_PAY", true));
		if (HostType.IS_SYNC_DEBUG || HostType.IS_SMARANT_DEBUG) {
			test_layout.setVisibility(View.VISIBLE);
		}

		if (SystemConfig.isSmarantSystem) {
			ll_set_validate_memberno.setVisibility(View.GONE);
			ll_set_validate_memberno_view.setVisibility(View.GONE);
			ll_set_start_callnumber.setVisibility(View.GONE);
			ll_set_start_callnumber_view.setVisibility(View.GONE);
			ll_set_canxingjian_ip.setVisibility(View.GONE);
			ll_set_printticket_type.setVisibility(View.GONE);
		} else if (SystemConfig.isSyncSystem) {
			ll_set_canxingjian_ip.setVisibility(View.GONE);
			ll_show_table_num.setVisibility(View.GONE);
			ll_pay_type.setVisibility(View.GONE);
			ll_pay_type_view.setVisibility(View.GONE);
			ll_show_table_num_view.setVisibility(View.GONE);
			ll_set_jiyejia_ip.setVisibility(View.GONE);
			ll_set_jiyejia_ip_view.setVisibility(View.GONE);
		} else if (SystemConfig.isCanXingJianSystem) {
			ll_show_table_num.setVisibility(View.GONE);
			ll_pay_type.setVisibility(View.GONE);
			ll_pay_type_view.setVisibility(View.GONE);
			ll_show_table_num_view.setVisibility(View.GONE);
			ll_set_jiyejia_ip.setVisibility(View.GONE);
			ll_set_start_callnumber.setVisibility(View.GONE);
			ll_set_start_callnumber_view.setVisibility(View.GONE);
			ll_set_validate_memberno.setVisibility(View.GONE);
			ll_set_validate_memberno_view.setVisibility(View.GONE);
			ll_set_printticket_type.setVisibility(View.GONE);
		}

	}

	@Bind(R.id.phone_test_swift)
	ToggleButton phone_test_swift;
	@Bind(R.id.ali_payset_swift)
	ToggleButton ali_payset_swift;
	@Bind(R.id.test_layout)
	LinearLayout test_layout;

	private void initSwift() {
		phone_test_swift
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						SPUtils.setSharedBooleanData(mContext, "PHONE_TEST", isChecked);
					}
				});
		ali_payset_swift.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SPUtils.setSharedBooleanData(mContext, "USE_ALI_PAY", isChecked);
			}
		});
	}

	private void terminalLogin() {
		if (BaseConfigure.isInit())
			mPresenter.goLogin();
	}

	/**
	 * 初始化监听
	 */
	private void initListener() {
		mRxManager.on(AppConstant.SETRESULT, new Action1<ResultEvent>() {
			@Override
			public void call(ResultEvent event) {
				int requestcode = event.getRequestcode();
				int position    = event.getPosition();
				switch (requestcode) {
					case AppConstant.PAY_RESULT_CODE:
						setPayType(position);
						break;
					case AppConstant.RESULT_FINISH:
						AppApplication.getInstance().exit();
						break;
					case AppConstant.IP_RESULT_CODE:
						initHost(position);
						break;
					case AppConstant.LANGUAGE_RESULT_CODE:
						Store.setLanguageLocal(CommonSetActivity.this, locals[position]);
						RxBus.getInstance().post(AppConstant.EVENT_REFRESH_LANGUAGE);
						setLanguage(position);
						break;
					case AppConstant.upLoadFileDay_RESULT_CODE:
						upLogFile(position);
						break;
					case AppConstant.VALIDATEMEMBER:
						setValidateMemberNo(position);
						break;
					case AppConstant.TEXTVIEWTYPE:
						setTextViewType(position);
						break;

					case AppConstant.SET_START_CALL_NUMBER:
						setStartCallNumber(position);
						break;
					case AppConstant.QR_INIT_CODE:
						setQRINIT(position);
						break;
					case AppConstant.SCANTYPECODE:
						setScanType(position);
						break;
					case AppConstant.ifShowTableCode:
						setShowTable(position);
						break;
					case AppConstant.showBannerCode:
						setShowBanner(position);
						break;
					case AppConstant.INNER_PRINTER_TYPE:
						setInnerPrinter(position);
						break;
					case AppConstant.PRINT_TICKET_TYPE:
						setPrintTiketType(position);
						break;
					default:
						break;
				}
			}
		});
	}

	private void setPrintTiketType(int position) {
		tv_printticket_type
				.setText(getResources().getStringArray(R.array.printtickettype)[position]);
		SPUtils.setSharedIntData(mContext, "printtickettype", position);
	}

	private void setStartCallNumber(int position) {
		String callNumStart = getResources().getStringArray(R.array.start_call_number)[position];
		tv_set_start_callnumber
				.setText("起始前缀 " + callNumStart);
		SPUtils.setSharedIntData(mContext, "call_number", Integer.parseInt(callNumStart));
	}

	private void setValidateMemberNo(int position) {
		tv_validate_memberno
				.setText(getResources().getStringArray(R.array.validate_member)[position]);
		SPUtils.setSharedIntData(mContext, "validatememberno", position);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	private void initData() {
		//		mVideoPaths.add("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4");
		//		mVideoPaths.add("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
		mVideoPaths
				.add("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv");
	}


	private void initScanType(int type) {
		tv_scantype.setText(getResources().getStringArray(R.array.scantype)[type]);
	}


	private void initShowTable(int i) {
		tv_show_table_num.setText(getResources().getStringArray(R.array.ifshowtable)[i]);
	}

	private ArrayList<String> mVideoPaths = new ArrayList<>();


	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
			case R.id.btn_set_print://打印设置
				//				if (!TextUtils.isEmpty(SPUtils.getSharedStringData(this, "productId"))) {
				//					ToastUitl.showLong(mContext, "无需重复设置打印机");
				//					return;
				//				}
				intent = new Intent(this,
						PrintSetActivity.class);
				startActivityForResult(intent, AppConstant.PRINT_ID_RESULT_CODE);
			case R.id.ll_print_select:
				//				showSetDialog(getResources()
				//						.getStringArray(R.array.printdata), AppConstant.PRINT_RESULT_CODE);
				break;
			case R.id.print_test:
				//				printImageTest();
				//				print58Ticket();
				break;
			case R.id.ll_set_validate_memberno:
				showSetDialog(getResources()
						.getStringArray(R.array.validate_member), AppConstant.VALIDATEMEMBER);
				break;
			case R.id.ll_set_start_callnumber:
				showSetDialog(getResources()
						.getStringArray(R.array.start_call_number), AppConstant.SET_START_CALL_NUMBER);
				break;
			case R.id.ll_set_changelanguage:
				showSetDialog(getResources()
						.getStringArray(R.array.language), AppConstant.LANGUAGE_RESULT_CODE);
				break;
			case R.id.ll_pay_type:
				showSetDialog(getResources()
						.getStringArray(R.array.paydata), AppConstant.PAY_RESULT_CODE);
				break;
			case R.id.ll_finish:
				showSetDialog(new String[]{"确定"}, AppConstant.RESULT_FINISH);
				break;
			case R.id.ll_bind:
				showBindDialog(TextUtils
						.isEmpty(SPUtils.getSharedStringData(mContext, "terminalmac")));
				break;
			case R.id.ll_download_videos:
				//判断有没有新版本
				if (hasNewVideos()) {
					//					downVideos(mVideoPaths);
				} else {
					ToastUitl.showShort(mContext, "没有更新!");
				}
				break;
			case R.id.ll_updata_layout:
				//判断有没有新版本
				if (hasNewVersion) {
					showDownAppDialog(mPath, mVersion, mDescription);
				} else {
					ToastUitl.showShort(mContext, getResources()
							.getString(R.string.no_newversion));
				}
				break;
			case R.id.ll_upload_catalog:
				showSetDialog(getResources()
						.getStringArray(R.array.uploadfileday), AppConstant.upLoadFileDay_RESULT_CODE);
				break;
			case R.id.ll_show_banner:
				showSetDialog(getResources()
						.getStringArray(R.array.showbanner), AppConstant.showBannerCode);
				break;
			case R.id.ll_store_ip:
				showSetDialog(getResources()
						.getStringArray(R.array.urldata), AppConstant.IP_RESULT_CODE);
				break;
			case R.id.ll_set_qr_init_layout:
				showSetDialog(qr_init_swift, AppConstant.QR_INIT_CODE);
				break;
			case R.id.back:
				setResult(RESULT_OK);
				finish();
				break;
			case R.id.ll_show_table_num:
				showSetDialog(getResources()
						.getStringArray(R.array.ifshowtable), AppConstant.ifShowTableCode);
				break;
			case R.id.ll_set_scantype:
				showSetDialog(getResources()
						.getStringArray(R.array.scantype), AppConstant.SCANTYPECODE);
				break;
			case R.id.ll_set_textviewtype:
				showSetDialog(getResources()
						.getStringArray(R.array.textviewtype), AppConstant.TEXTVIEWTYPE);
				break;
			case R.id.ll_set_printticket_type:
				showSetDialog(getResources()
						.getStringArray(R.array.printtickettype), AppConstant.PRINT_TICKET_TYPE);
				break;
			case R.id.ll_set_inner_print:
				showSetDialog(getResources()
						.getStringArray(R.array.innerprinter), AppConstant.INNER_PRINTER_TYPE);
				break;
			case R.id.ll_clear_catalog:
				if (FileUtil.getCacheSize().equals("0 kb")) {
					ToastUitl.showShort(mContext, getResources().getString(R.string.nomore_cache));
					return;
				}
				if (tv_cache_size.getText().equals("0 kb")) {
					ToastUitl.showShort(mContext, getResources().getString(R.string.nomore_cache));
					return;
				}
				if (clearing) {
					ToastUitl.showShort(mContext, getResources()
							.getString(R.string.working));
					return;
				}
				clearing = true;
				clearLog();
				break;
			default:
				break;
		}
	}

	/**
	 * 这个打印出来的是 笑脸+< +笑脸
	 * PrintManager.printlnText("" + "\n");
	 *
	 *
	 */
	//	private void print58Ticket() {
	//		PrintManager.size1();
	//		PrintManager.bold();
	//		PrintManager.center();
	//		PrintManager.printlnText("" + "\n");
	//		//桌牌号和堂食外带
	//		PrintManager.size1();
	//		PrintManager.left();
	//		PrintManager.normal();
	//	}

	/**
	 * 测试打印二位图
	 */
	private void printImageTest() {
		PrintManager.printText("");
		PrintManager.printText("");
		PrintManager.printText("");
		PrintManager.printText("");
		Bitmap bitmap = BitmapFactory
				.decodeResource(mContext.getResources(), R.mipmap.test);
		PrintManager.printPictrue(bitmap);
		PrintManager.printText("");
		PrintManager.printText("");
		PrintManager.printText("");
		PrintManager.printText("");
		PrintManager.cut();
	}


	private void showSetDialog(String[] data, int requestcode) {
		SetDialog.newInstance(data, requestcode)
				.show(getSupportFragmentManager(), "SetDialog");
	}

	private boolean hasNewVideos() {

		return mVideoPaths != null && mVideoPaths.size() > 0;
	}

	private boolean clearing;

	private void clearLog() {

		final File[] files = FileUtil.clearLog(this);
		if (files == null || files.length == 0) {
			ToastUitl.showShort(this, "没有需要清理的缓存和日志");
			return;
		}
		showDownLoadProgressDialog(files.length);
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < files.length; i++) {
					boolean delete = files[i].delete();
					if (i == files.length - 1) {
						//删完了
						mHandler.sendEmptyMessage(1);
					} else {
						Message message = new Message();
						message.what = 0;
						message.obj = i + 1;
						mHandler.sendMessage(message);
					}
				}
			}
		}).start();

	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				downLoadProgressDialog.setProgress((int) msg.obj);
			} else {
				clearing = false;
				tv_cache_size.setText("0 kb");
				downLoadProgressDialog.dismiss();
				ToastUitl.showShort(mContext, "删除完毕");
			}
		}
	};

	private void upLogFile(int position) {
		//0.表示今天
		//1.表示昨天
		//2.表示前天
		File   log = FileUtil.getUploadLog(position);
		String day = null;
		if (log == null) {
			switch (position) {
				case 0:
					day = "没有今天的日志";
					break;
				case 1:
					day = "没有昨天的日志";
					break;
				case 2:
					day = "没有前天的日志";
					break;
				default:
					day = "找不到日志";
					break;
			}
			ToastUitl.showShort(mContext, day);
			return;
		}
		uploadLog2(log);
	}

	public String uploadLogUrl = "api/terminal/uploadLog";

	public String getUploadLogUrl() {
		String host = "";
		host = HostType.IS_SMARANT_DEBUG ? ApiConstants.TEST_HOST : ApiConstants.NORMAL_HOST;
		return host + uploadLogUrl;
	}

	public void uploadLog2(final File file) {
		if (file == null) {
			return;
		}
		OkHttpUtils
				.post()
				.addFile("file", file.getName(), file)
				.url(getUploadLogUrl())
				.addParams("appid", BaseConfigure.getAppid())
				.addParams("brandid", String.valueOf(BaseConfigure.getBrandid()))
				.addParams("storeid", String.valueOf(BaseConfigure.getStoreid()))
				.addParams("tname", TerminalConfigure.getTname())
				.addParams("terminalid", TerminalConfigure.getTerminalid())
				.addParams("token", BaseConfigure.getToken())
				.build()
				.connTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.writeTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS).
				readTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						e.printStackTrace();
						ToastUitl.showLong(mContext, "上传失败!");
					}

					@Override
					public void onResponse(String response, int id) {
						ToastUitl.showLong(mContext, "上传成功!");
					}
				});
	}

	private void showDownAppDialog(final String url, String version, String description) {
		NewVersionDialog dialog = NewVersionDialog.newInstance(version, description);
		dialog.setOnYesClickListener(new NewVersionDialog.OnYesClickListener() {
			@Override
			public void onYesClick() {
				downFile(url, FileUtil.SDPATH, "selfpos.apk");
			}
		});
		dialog.show(getSupportFragmentManager(), "NewVersionDialog");
	}

	/**
	 * */
	public void downFile(final String urlStr, final String path,
	                     final String fileName) {
		showDownLoadProgressDialog(100);
		OkHttpUtils.get().url(urlStr).build().readTimeOut(60000).writeTimeOut(60000)
				.execute(new FileCallBack(path, fileName) {
					@Override
					public void inProgress(float progress, long total, int id) {
						downLoadProgressDialog.setProgress((int) (progress * 100));
					}

					@Override
					public void onResponse(File response, int id) {
						downLoadProgressDialog.dismiss();
						DownLoadAPPUtils.getInstance(CommonSetActivity.this).install(response);
					}

					@Override
					public void onError(okhttp3.Call call, Exception e, int id) {
						downLoadProgressDialog.dismiss();
						e.printStackTrace();
					}
				});

	}

	private void setInnerPrinter(int position) {
		tv_inner_print_id.setText(getResources().getStringArray(R.array.innerprinter)[position]);
		SPUtils.setSharedIntData(mContext, "innerprinter", position);
	}


	private DownLoadProgressDialog downLoadProgressDialog;

	private void showDownLoadProgressDialog(int max) {
		downLoadProgressDialog = DownLoadProgressDialog.newInstance(max);
		downLoadProgressDialog.show(getSupportFragmentManager(), "DownLoadProgressDialog");
	}

	private void showBindDialog(final boolean bind) {
		final CommonEditDialog dialog = CommonEditDialog.newInstance(bind);
		dialog.setOnYesClickListener(new CommonEditDialog.OnYesClickListener() {
			@Override
			public void onYesClick(String s) {
				bindTerminal(s);
				dialog.dismiss();
			}
		});
		dialog.setOnNoClickListener(new CommonEditDialog.OnNoClickListener() {
			@Override
			public void onNoClick() {
				unbindTerminal();
				dialog.dismiss();
			}
		});
		dialog.show(getSupportFragmentManager(), "CommonEditDialog");
	}

	private void unbindTerminal() {
		mPresenter.unBind();
	}


	private void dismissBindDialog() {
		if (bindDialog != null) {
			bindDialog.dismiss();
		}
	}

	final String[] locals = {"zh_CN", "en"};


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
			case AppConstant.PRINT_ID_RESULT_CODE:
				tv_print_id.setText(data.getStringExtra("productId"));
				break;
			default:
				break;
		}
	}

	private void setLanguage(int position) {
		tv_changelanguage.setText(getResources().getStringArray(R.array.language)[position]);
		SPUtils.setSharedIntData(mContext, "changelanguage", position);
	}

	private void setTextViewType(int position) {
		tv_textviewtype.setText(getResources().getStringArray(R.array.textviewtype)[position]);
		SPUtils.setSharedIntData(mContext, "textviewtype", position);
	}

	private void setScanType(int position) {
		tv_scantype.setText(getResources().getStringArray(R.array.scantype)[position]);
		SPUtils.setSharedIntData(mContext, "scantype", position);
	}

	private void setShowBanner(int position) {
		tv_show_banner.setText(getResources().getStringArray(R.array.showbanner)[position]);
		SPUtils.setSharedIntData(mContext, "showBanner", position);
	}

	private void setShowTable(int position) {

		tv_show_table_num.setText(getResources().getStringArray(R.array.ifshowtable)[position]);
		SPUtils.setSharedIntData(mContext, "SHOWTABLE", position);
	}

	private void setQRINIT(int position) {
		tv_qr_init.setText(qr_init_swift[position]);
		SPUtils.setSharedIntData(mContext, "QRINIT", position);//o表示关，1表示开
	}


	private void setPayType(int position) {
		tv_pay_type.setText(getResources().getStringArray(R.array.paydata)[position]);
		tv_pay_type.setTag(position);
		SPUtils.setSharedIntData(mContext, "payType", position);
	}

	private void initPayType(int position) {
		tv_pay_type.setText(position == 0 ? getResources()
				.getString(R.string.credit_card_payment) : getResources()
				.getString(R.string.sweep_the_code_to_pay));
	}


	@Override
	public void showLoading(String title) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip(String msg) {

	}

	@Override
	public void returnBindResult(BindEntity entity) {
		SPUtils.setSharedStringData(this, "appid", entity.getAppid());
		SPUtils.setSharedIntData(this, "storeid", entity.getStoreid());
		SPUtils.setSharedIntData(this, "brandid", entity.getBrandid());
		SPUtils.setSharedStringData(this, "tname", entity.getTname());
		SPUtils.setSharedStringData(this, "sname", entity.getSname());
		SPUtils.setSharedStringData(this, "phone", entity.getPhone());
		SPUtils.setSharedStringData(this, "mobile", entity.getMobile());
		SPUtils.setSharedStringData(this, "adress", entity.getAddress());
		SPUtils.setSharedlongData(this, "storeEndTime", entity.getStoreEndTime());
		SPUtils.setSharedStringData(this, "currentVersion", entity.getCurrentVersion());
		SPUtils.setSharedStringData(this, "brandName", entity.getBrandName());
		SPUtils.setSharedStringData(this, "terminalmac", entity.getTerminalMac());
		SPUtils.setSharedStringData(this, "longitute", entity.getLongitutde());
		SPUtils.setSharedStringData(this, "latitute", entity.getLatitude());
		SPUtils.setSharedStringData(this, "versionid", "selfpos");
		SPUtils.setSharedStringData(this, "terminalid", String.valueOf(entity.getTerminalid()));
		SPUtils.setSharedIntData(this, "kdsid", entity.getKdsid());
		BaseConfigure.setAppid(SPUtils.getSharedStringData(this, "appid"));
		BaseConfigure.setStoreid(SPUtils.getSharedIntData(this, "storeid"));
		BaseConfigure.setBrandid(SPUtils.getSharedIntData(this, "brandid"));
		TerminalConfigure.setTname(SPUtils.getSharedStringData(this, "tname"));
		TerminalConfigure.setTerminalid(SPUtils.getSharedStringData(this, "terminalid"));
		TerminalConfigure.setTerminalmac(SPUtils.getSharedStringData(this, "terminalmac"));
		StoreConfigure.setLongitute(SPUtils.getSharedStringData(this, "longitute"));
		StoreConfigure.setLatitute(SPUtils.getSharedStringData(this, "latitute"));
		TerminalConfigure.setKdsid(SPUtils.getSharedIntData(this, "kdsid"));
		refreshUI();
		mPresenter.goLogin();
	}

	/**
	 * 绑定成功,刷新界面
	 *
	 * @param
	 */
	private void refreshUI() {
		if (!TextUtils.isEmpty(SPUtils.getSharedStringData(mContext, "brandName"))) {
			store_info_ll.setVisibility(View.VISIBLE);
			store_info_tv.setText(SPUtils.getSharedStringData(mContext, "brandName") + "-" + SPUtils
					.getSharedStringData(mContext, "sname"));
		} else {
			store_info_ll.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(SPUtils.getSharedStringData(mContext, "terminalmac"))) {
			tv_bind_status.setText(SPUtils
					.getSharedStringData(mContext, "tname") + " - " + SPUtils
					.getSharedStringData(mContext, "terminalmac"));
		} else {
			tv_bind_status.setText("未绑定");
		}
	}

	@Override
	public void returnUnBindResult(BaseEntity baseEntity) {
		if (baseEntity.getResult().equals("0")) {
			ToastUitl.showShort(mContext, "解绑成功");
			clearSP();
			refreshUI();
		}
	}

	/**
	 * 清除缓存
	 */
	@Override
	public void clearSP() {
		BaseConfigure.setAppid("");
		BaseConfigure.setStoreid(0);
		BaseConfigure.setBrandid(0);
		TerminalConfigure.setTname("");
		TerminalConfigure.setTerminalid("");
		TerminalConfigure.setTerminalmac("");
		StoreConfigure.setLongitute("");
		StoreConfigure.setLatitute("");
		SPUtils.setSharedStringData(this, "appid", "");
		SPUtils.setSharedIntData(this, "storeid", 0);
		SPUtils.setSharedIntData(this, "brandid", 0);
		SPUtils.setSharedStringData(this, "tname", "");
		SPUtils.setSharedStringData(this, "sname", "");
		SPUtils.setSharedStringData(this, "phone", "");
		SPUtils.setSharedStringData(this, "mobile", "");
		SPUtils.setSharedStringData(this, "adress", "");
		SPUtils.setSharedlongData(this, "storeEndTime", 0);
		SPUtils.setSharedStringData(this, "currentVersion", "");
		SPUtils.setSharedStringData(this, "brandName", "");
		SPUtils.setSharedStringData(this, "terminalmac", "");
		SPUtils.setSharedStringData(this, "longitute", "");
		SPUtils.setSharedStringData(this, "latitute", "");
		SPUtils.setSharedStringData(this, "versionid", "selfhelp_ordermachine");
		SPUtils.setSharedIntData(this, "kdsid", 0);
	}

	@Override
	public void returnLoginResult(LoginEntity loginEntity) {


		if ("0".equals(loginEntity.getResult())) {
			BaseConfigure.setToken(loginEntity.getToken());
			TerminalConfigure.setTerminalid(String.valueOf(loginEntity.getTerminalid()));

			//进来先登录一下再说，获得需要显示的数据
			//需要显示的数据有 （品牌-门店-自助点餐机名称）
			//版本信息
			int versionCode = DownLoadAPPUtils.getInstance(CommonSetActivity.this)
					.getAPPVersionCode();
			//显示当前版本号
			if ((!TextUtils
					.isEmpty(loginEntity.getDownloadpath())
					&& versionCode < loginEntity.getVersion())) {
				updata_tv.setText(getResources().getString(R.string.new_version) + loginEntity
						.getVersion());
				mPath = loginEntity.getDownloadpath();
				mVersion = String.valueOf(loginEntity.getVersion());

				mDescription = loginEntity.getDescription();
				hasNewVersion = true;
			} else {
				updata_tv.setText(getResources()
						.getString(R.string.current_version) + versionCode);
				hasNewVersion = false;
			}
		} else {
			FileLog.log("登录失败");
			//网络错误
			//			if (getResources().getString(R.string.bind_error).equals(loginEntity.getErrmsg())) {
			//				clearSP();
			//				tv_bind_status.setText(getResources().getString(R.string.nobind));
			//			}
		}
	}


	private void bindTerminal(String s) {
		mPresenter.goBind(s);
	}

	private void initHost(int hostType) {
		ApiConstants.setType(hostType);
		switch (hostType) {
			case HostType.TEST_HOST:
				store_ip.setText(ApiConstants.TEST_HOST);
				break;
			case HostType.NORMAL_HOST:
				store_ip.setText(ApiConstants.NORMAL_HOST);
				break;
			case HostType.SYNC_TEST_HOSTS:
				store_ip.setText(ApiConstants.SYNC_TEST_HOST);
				break;
			case HostType.SYNC_NORMAL_HOSTS:
				store_ip.setText(ApiConstants.SYNC_NORMAL_HOST);
				break;
			default:
				store_ip.setText("");
				break;
		}
	}

}
