package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import com.jaydenxiao.common.basebean.BaseRespose;

/**
 * Author：Anch
 * Date：2018/1/25 10:20
 * Desc：
 */
public class LoginEntity extends BaseRespose implements Parcelable {

	/**
	 * result : 0
	 * content : null
	 * errmsg : 0
	 * terminalid : 15
	 * version : 66
	 * updatetime : 1516593120196
	 * downloadpath : http://szfileserver.419174855.mtmss.com/terminal/66/66/diancan_ip_smarant_V6.4.2_code66_Time_20180122.apk
	 * description : 解决辣请坐报表问题
	 * token : 859fdfdb-8d41-42b5-a210-aa10461e9184
	 * phone : 4458288
	 * address : 深证市南山区平山村喜多多一号店
	 * storeEndTime : 1540483200000
	 * bindUUID : 9fe9046dc16845aa81af61ac5270b1da
	 */

	private int    terminalid;
	private int    version;
	private long   updatetime;
	private String downloadpath;
	private String description;
	private String token;
	private String phone;
	private String address;
	private long   storeEndTime;
	private String bindUUID;

	public int getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(int terminalid) {
		this.terminalid = terminalid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public String getDownloadpath() {
		return downloadpath;
	}

	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getStoreEndTime() {
		return storeEndTime;
	}

	public void setStoreEndTime(long storeEndTime) {
		this.storeEndTime = storeEndTime;
	}

	public String getBindUUID() {
		return bindUUID;
	}

	public void setBindUUID(String bindUUID) {
		this.bindUUID = bindUUID;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.terminalid);
		dest.writeInt(this.version);
		dest.writeLong(this.updatetime);
		dest.writeString(this.downloadpath);
		dest.writeString(this.description);
		dest.writeString(this.token);
		dest.writeString(this.phone);
		dest.writeString(this.address);
		dest.writeLong(this.storeEndTime);
		dest.writeString(this.bindUUID);
	}

	public LoginEntity() {
	}

	protected LoginEntity(Parcel in) {
		this.terminalid = in.readInt();
		this.version = in.readInt();
		this.updatetime = in.readLong();
		this.downloadpath = in.readString();
		this.description = in.readString();
		this.token = in.readString();
		this.phone = in.readString();
		this.address = in.readString();
		this.storeEndTime = in.readLong();
		this.bindUUID = in.readString();
	}

	public static final Parcelable.Creator<LoginEntity> CREATOR = new Parcelable.Creator<LoginEntity>() {
		@Override
		public LoginEntity createFromParcel(Parcel source) {
			return new LoginEntity(source);
		}

		@Override
		public LoginEntity[] newArray(int size) {
			return new LoginEntity[size];
		}
	};
}
