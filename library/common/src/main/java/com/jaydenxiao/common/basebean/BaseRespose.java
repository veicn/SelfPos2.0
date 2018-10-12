package com.jaydenxiao.common.basebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseRespose<T extends Parcelable> implements Parcelable {
	//    public String code;
	//    public String msg;
	//
	//    public T data;
	//
	//    public boolean success() {
	//        return "1".equals(code);
	//    }
	//
	//    @Override
	//    public String toString() {
	//        return "BaseRespose{" +
	//                "code='" + code + '\'' +
	//                ", msg='" + msg + '\'' +
	//                ", data=" + data +
	//                '}';
	//    }
	private String errmsg;
	private T      content;
	private String result;

	public String getResult() {
		return result;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.result);
		dest.writeString(this.content.getClass().getName());
		dest.writeParcelable(this.content, flags);
		dest.writeString(this.errmsg);
	}

	public BaseRespose() {
	}

	protected BaseRespose(Parcel in) {
		this.result = in.readString();
		String dataName = in.readString();
		try {
			this.content = in.readParcelable(Class.forName(dataName).getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.errmsg = in.readString();
	}

}
