package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import com.jaydenxiao.common.basebean.BaseRespose;

/**
 * Author：Anch
 * Date：2018/1/24 18:53
 * Desc：
 */
public class BindEntity extends BaseRespose<BindEntity> implements Parcelable {

	/**
	 * result : 0
	 * content : {"appid":"59841423","brandid":47,"storeid":1,"terminalid":15,"storename":null,"tname":"L","isactive":1,"isonline":0,"logintime":-1,"receiveNetOrder":0,"terminalMac":"S45437926","terminalIp":null,"longitutde":null,"latitude":null,"description":null,"currentVersion":"66","pushtype":null,"targetpackage":null,"pushserver":null,"xgtoken":null,"mitoken":null,"jgtoken":null,"gttoken":null,"bdtoken":null,"terminalType":2,"printerid":-1,"secondaryPrinterId":null,"kdsid":-1,"createdAt":1515566192000,"updatedAt":1516789383000,"takeoutPrinterid":-1,"bindUUID":"c2d06b1292e94810935a37005907d8fc","screenType":-1,"secondaryPrinterName":null,"printerName":null,"printerIp":null,"vendor":null,"kdsName":null,"isactiveStr":"激活","isonlineStr":"离线","logintimeStr":"","receiveNetOrderStr":"否","sname":"喜多多一号店","phone":"4458288","mobile":"13787719750","address":"深证市南山区平山村喜多多一号店","brandName":"喜多多","terminalTypeStr":"自助点餐机","storeEndTime":1540483200000,"takeoutPrinterName":null}
	 */
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public int getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(int terminalid) {
		this.terminalid = terminalid;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public int getIsonline() {
		return isonline;
	}

	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}

	public int getLogintime() {
		return logintime;
	}

	public void setLogintime(int logintime) {
		this.logintime = logintime;
	}

	public int getReceiveNetOrder() {
		return receiveNetOrder;
	}

	public void setReceiveNetOrder(int receiveNetOrder) {
		this.receiveNetOrder = receiveNetOrder;
	}

	public String getTerminalMac() {
		return terminalMac;
	}

	public void setTerminalMac(String terminalMac) {
		this.terminalMac = terminalMac;
	}

	public String getTerminalIp() {
		return terminalIp;
	}

	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}

	public String getLongitutde() {
		return longitutde;
	}

	public void setLongitutde(String longitutde) {
		this.longitutde = longitutde;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getPushtype() {
		return pushtype;
	}

	public void setPushtype(String pushtype) {
		this.pushtype = pushtype;
	}

	public String getTargetpackage() {
		return targetpackage;
	}

	public void setTargetpackage(String targetpackage) {
		this.targetpackage = targetpackage;
	}

	public String getPushserver() {
		return pushserver;
	}

	public void setPushserver(String pushserver) {
		this.pushserver = pushserver;
	}

	public String getXgtoken() {
		return xgtoken;
	}

	public void setXgtoken(String xgtoken) {
		this.xgtoken = xgtoken;
	}

	public String getMitoken() {
		return mitoken;
	}

	public void setMitoken(String mitoken) {
		this.mitoken = mitoken;
	}

	public String getJgtoken() {
		return jgtoken;
	}

	public void setJgtoken(String jgtoken) {
		this.jgtoken = jgtoken;
	}

	public String getGttoken() {
		return gttoken;
	}

	public void setGttoken(String gttoken) {
		this.gttoken = gttoken;
	}

	public String getBdtoken() {
		return bdtoken;
	}

	public void setBdtoken(String bdtoken) {
		this.bdtoken = bdtoken;
	}

	public int getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(int terminalType) {
		this.terminalType = terminalType;
	}

	public int getPrinterid() {
		return printerid;
	}

	public void setPrinterid(int printerid) {
		this.printerid = printerid;
	}

	public String getSecondaryPrinterId() {
		return secondaryPrinterId;
	}

	public void setSecondaryPrinterId(String secondaryPrinterId) {
		this.secondaryPrinterId = secondaryPrinterId;
	}

	public int getKdsid() {
		return kdsid;
	}

	public void setKdsid(int kdsid) {
		this.kdsid = kdsid;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getTakeoutPrinterid() {
		return takeoutPrinterid;
	}

	public void setTakeoutPrinterid(int takeoutPrinterid) {
		this.takeoutPrinterid = takeoutPrinterid;
	}

	public String getBindUUID() {
		return bindUUID;
	}

	public void setBindUUID(String bindUUID) {
		this.bindUUID = bindUUID;
	}

	public int getScreenType() {
		return screenType;
	}

	public void setScreenType(int screenType) {
		this.screenType = screenType;
	}

	public String getSecondaryPrinterName() {
		return secondaryPrinterName;
	}

	public void setSecondaryPrinterName(String secondaryPrinterName) {
		this.secondaryPrinterName = secondaryPrinterName;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getPrinterIp() {
		return printerIp;
	}

	public void setPrinterIp(String printerIp) {
		this.printerIp = printerIp;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getKdsName() {
		return kdsName;
	}

	public void setKdsName(String kdsName) {
		this.kdsName = kdsName;
	}

	public String getIsactiveStr() {
		return isactiveStr;
	}

	public void setIsactiveStr(String isactiveStr) {
		this.isactiveStr = isactiveStr;
	}

	public String getIsonlineStr() {
		return isonlineStr;
	}

	public void setIsonlineStr(String isonlineStr) {
		this.isonlineStr = isonlineStr;
	}

	public String getLogintimeStr() {
		return logintimeStr;
	}

	public void setLogintimeStr(String logintimeStr) {
		this.logintimeStr = logintimeStr;
	}

	public String getReceiveNetOrderStr() {
		return receiveNetOrderStr;
	}

	public void setReceiveNetOrderStr(String receiveNetOrderStr) {
		this.receiveNetOrderStr = receiveNetOrderStr;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getTerminalTypeStr() {
		return terminalTypeStr;
	}

	public void setTerminalTypeStr(String terminalTypeStr) {
		this.terminalTypeStr = terminalTypeStr;
	}

	public long getStoreEndTime() {
		return storeEndTime;
	}

	public void setStoreEndTime(long storeEndTime) {
		this.storeEndTime = storeEndTime;
	}

	public String getTakeoutPrinterName() {
		return takeoutPrinterName;
	}

	public void setTakeoutPrinterName(String takeoutPrinterName) {
		this.takeoutPrinterName = takeoutPrinterName;
	}

	/**
	 * appid : 59841423
	 * brandid : 47
	 * storeid : 1
	 * terminalid : 15
	 * storename : null
	 * tname : L
	 * isactive : 1
	 * isonline : 0
	 * logintime : -1
	 * receiveNetOrder : 0
	 * terminalMac : S45437926
	 * terminalIp : null
	 * longitutde : null
	 * latitude : null
	 * description : null
	 * currentVersion : 66
	 * pushtype : null
	 * targetpackage : null
	 * pushserver : null
	 * xgtoken : null
	 * mitoken : null
	 * jgtoken : null
	 * gttoken : null
	 * bdtoken : null
	 * terminalType : 2
	 * printerid : -1
	 * secondaryPrinterId : null
	 * kdsid : -1
	 * createdAt : 1515566192000
	 * updatedAt : 1516789383000
	 * takeoutPrinterid : -1
	 * bindUUID : c2d06b1292e94810935a37005907d8fc
	 * screenType : -1
	 * secondaryPrinterName : null
	 * printerName : null
	 * printerIp : null
	 * vendor : null
	 * kdsName : null
	 * isactiveStr : 激活
	 * isonlineStr : 离线
	 * logintimeStr :
	 * receiveNetOrderStr : 否
	 * sname : 喜多多一号店
	 * phone : 4458288
	 * mobile : 13787719750
	 * address : 深证市南山区平山村喜多多一号店
	 * brandName : 喜多多
	 * terminalTypeStr : 自助点餐机
	 * storeEndTime : 1540483200000
	 * takeoutPrinterName : null
	 */


	private String appid;
	private int    brandid;
	private int    storeid;
	private int    terminalid;
	private String storename;
	private String tname;
	private int    isactive;
	private int    isonline;
	private int    logintime;
	private int    receiveNetOrder;
	private String terminalMac;
	private String terminalIp;
	private String longitutde;
	private String latitude;
	private String description;
	private String currentVersion;
	private String pushtype;
	private String targetpackage;
	private String pushserver;
	private String xgtoken;
	private String mitoken;
	private String jgtoken;
	private String gttoken;
	private String bdtoken;
	private int    terminalType;
	private int    printerid;
	private String secondaryPrinterId;
	private int    kdsid;
	private long   createdAt;
	private long   updatedAt;
	private int    takeoutPrinterid;
	private String bindUUID;
	private int    screenType;
	private String secondaryPrinterName;
	private String printerName;
	private String printerIp;
	private String vendor;
	private String kdsName;
	private String isactiveStr;
	private String isonlineStr;
	private String logintimeStr;
	private String receiveNetOrderStr;
	private String sname;
	private String phone;
	private String mobile;
	private String address;
	private String brandName;
	private String terminalTypeStr;
	private long   storeEndTime;
	private String takeoutPrinterName;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.appid);
		dest.writeInt(this.brandid);
		dest.writeInt(this.storeid);
		dest.writeInt(this.terminalid);
		dest.writeString(this.storename);
		dest.writeString(this.tname);
		dest.writeInt(this.isactive);
		dest.writeInt(this.isonline);
		dest.writeInt(this.logintime);
		dest.writeInt(this.receiveNetOrder);
		dest.writeString(this.terminalMac);
		dest.writeString(this.terminalIp);
		dest.writeString(this.longitutde);
		dest.writeString(this.latitude);
		dest.writeString(this.description);
		dest.writeString(this.currentVersion);
		dest.writeString(this.pushtype);
		dest.writeString(this.targetpackage);
		dest.writeString(this.pushserver);
		dest.writeString(this.xgtoken);
		dest.writeString(this.mitoken);
		dest.writeString(this.jgtoken);
		dest.writeString(this.gttoken);
		dest.writeString(this.bdtoken);
		dest.writeInt(this.terminalType);
		dest.writeInt(this.printerid);
		dest.writeString(this.secondaryPrinterId);
		dest.writeInt(this.kdsid);
		dest.writeLong(this.createdAt);
		dest.writeLong(this.updatedAt);
		dest.writeInt(this.takeoutPrinterid);
		dest.writeString(this.bindUUID);
		dest.writeInt(this.screenType);
		dest.writeString(this.secondaryPrinterName);
		dest.writeString(this.printerName);
		dest.writeString(this.printerIp);
		dest.writeString(this.vendor);
		dest.writeString(this.kdsName);
		dest.writeString(this.isactiveStr);
		dest.writeString(this.isonlineStr);
		dest.writeString(this.logintimeStr);
		dest.writeString(this.receiveNetOrderStr);
		dest.writeString(this.sname);
		dest.writeString(this.phone);
		dest.writeString(this.mobile);
		dest.writeString(this.address);
		dest.writeString(this.brandName);
		dest.writeString(this.terminalTypeStr);
		dest.writeLong(this.storeEndTime);
		dest.writeString(this.takeoutPrinterName);
	}

	public BindEntity() {
	}

	protected BindEntity(Parcel in) {
		this.appid = in.readString();
		this.brandid = in.readInt();
		this.storeid = in.readInt();
		this.terminalid = in.readInt();
		this.storename = in.readString();
		this.tname = in.readString();
		this.isactive = in.readInt();
		this.isonline = in.readInt();
		this.logintime = in.readInt();
		this.receiveNetOrder = in.readInt();
		this.terminalMac = in.readString();
		this.terminalIp = in.readString();
		this.longitutde = in.readString();
		this.latitude = in.readString();
		this.description = in.readString();
		this.currentVersion = in.readString();
		this.pushtype = in.readString();
		this.targetpackage = in.readString();
		this.pushserver = in.readString();
		this.xgtoken = in.readString();
		this.mitoken = in.readString();
		this.jgtoken = in.readString();
		this.gttoken = in.readString();
		this.bdtoken = in.readString();
		this.terminalType = in.readInt();
		this.printerid = in.readInt();
		this.secondaryPrinterId = in.readString();
		this.kdsid = in.readInt();
		this.createdAt = in.readLong();
		this.updatedAt = in.readLong();
		this.takeoutPrinterid = in.readInt();
		this.bindUUID = in.readString();
		this.screenType = in.readInt();
		this.secondaryPrinterName = in.readString();
		this.printerName = in.readString();
		this.printerIp = in.readString();
		this.vendor = in.readString();
		this.kdsName = in.readString();
		this.isactiveStr = in.readString();
		this.isonlineStr = in.readString();
		this.logintimeStr = in.readString();
		this.receiveNetOrderStr = in.readString();
		this.sname = in.readString();
		this.phone = in.readString();
		this.mobile = in.readString();
		this.address = in.readString();
		this.brandName = in.readString();
		this.terminalTypeStr = in.readString();
		this.storeEndTime = in.readLong();
		this.takeoutPrinterName = in.readString();
	}

	public static final Parcelable.Creator<BindEntity> CREATOR = new Parcelable.Creator<BindEntity>() {
		@Override
		public BindEntity createFromParcel(Parcel source) {
			return new BindEntity(source);
		}

		@Override
		public BindEntity[] newArray(int size) {
			return new BindEntity[size];
		}
	};
}
