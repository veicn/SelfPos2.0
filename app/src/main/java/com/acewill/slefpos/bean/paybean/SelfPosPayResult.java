package com.acewill.slefpos.bean.paybean;

import com.acewill.slefpos.bean.syncbean.syncpay.SyncQureyPayResultRes;
import com.jaydenxiao.common.utils.logutil.FileLog;

/**
 * Author：Anch
 * Date：2018/5/4 13:16
 * Desc：
 */
public class SelfPosPayResult {

	private int                   reslut;
	private String                msg;
	private String                content;
	private SyncPayResult         mSyncPayResult;
	private SyncQureyPayResultRes mSyncQureyPayResult;

	public SelfPosPayResult(int reslut, String msg) {
		FileLog.log("SelfPosPayResult> result>" + reslut + ",msg>" + msg);
		this.reslut = reslut;
		this.msg = msg;
	}

	public SelfPosPayResult(int reslut, String msg, String content) {
		FileLog.log("SelfPosPayResult> result>" + reslut + ",msg>" + msg + ",content>" + content);
		this.reslut = reslut;
		this.msg = msg;
		this.content = content;
	}

	public int getReslut() {
		return reslut;
	}

	public void setReslut(int reslut) {
		this.reslut = reslut;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public SyncPayResult getSyncPayResult() {
		return mSyncPayResult;
	}

	public void setSyncPayResult(SyncPayResult syncPayResult) {
		mSyncPayResult = syncPayResult;
	}

	public SyncQureyPayResultRes getSyncQureyPayResult() {
		return mSyncQureyPayResult;
	}

	public void setSyncQureyPayResult(SyncQureyPayResultRes syncQureyPayResult) {
		mSyncQureyPayResult = syncQureyPayResult;
	}
}
