package com.jaydenxiao.common.baserx;

import android.app.Activity;
import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.exception.ApiException;
import com.jaydenxiao.common.utils.logutil.FileLog;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
public abstract class RxSubscriber<T> extends Subscriber<T> {

	private Context mContext;
	private String  msg;
	private boolean showDialog = true;

	/**
	 * 是否显示浮动dialog
	 */
	public void showDialog() {
		this.showDialog = true;
	}

	public void hideDialog() {
		this.showDialog = true;
	}

	public RxSubscriber(Context context, String msg, boolean showDialog) {
		this.mContext = context;
		this.msg = msg;
		this.showDialog = showDialog;
	}

	public RxSubscriber(Context context) {
		this(context, context.getString(R.string.loading), true);
	}

	public RxSubscriber(Context context, boolean showDialog) {
		this(context, context.getString(R.string.loading), showDialog);
	}

	@Override
	public void onCompleted() {
		if (showDialog)
			LoadingDialog.cancelDialogForLoading();
	}

	@Override
	public void onStart() {
		super.onStart();
		if (showDialog) {
			try {
				LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void onNext(T t) {
		_onNext(t);
	}

	@Override
	public void onError(Throwable e) {
		FileUtil.saveCrashInfo2File(e, null);
		if (showDialog)
			LoadingDialog.cancelDialogForLoading();
		e.printStackTrace();
		//网络
		if (!NetWorkUtils.isNetConnected(mContext)) {
			_onError(mContext.getString(R.string.no_net));
		} else if (e instanceof NullPointerException) {
			_onError(e, e.getMessage());
		} else if (e instanceof SocketTimeoutException) {
			_onTimeOut();
		}
		//服务器
		else if (e instanceof ServerException || e instanceof ApiException ||
				e instanceof IllegalArgumentException || e instanceof JSONException || e instanceof JsonSyntaxException) {
			FileLog.log("error", RxSubscriber.class, "onError(1)", "log", e.getMessage());
			_onError(e.getMessage());
		} else if (e instanceof HttpException || e instanceof UnknownHostException) {
			FileLog.log("error", RxSubscriber.class, "onError(2)", "log", e.getMessage());
			_onError(e, e.getMessage());
		}
		//其它
		else {
			_onError(mContext.getString(R.string.net_error));
		}
	}

	protected abstract void _onNext(T t);

	protected abstract void _onError(String message);

	protected void _onError(Throwable exception, String message) {
	}

	protected abstract void _onTimeOut();

}
