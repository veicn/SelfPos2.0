package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 15:49
 * Desc：
 */
public class Market implements Parcelable, Cloneable {


	/**
	 * result : 0
	 * content : [{"appid":"59841423","brandid":47,"marketid":1,"marketName":"折扣（65折）","triggerType":2,"marketType":1,"dateAvailable":false,"timeAvailable":false,"weekAvailable":false,"startDate":0,"endDate":0,"startTime":"","endTime":"","week":"","rate":1,"cash":5,"loopFullCash":0,"fullCash":0,"commonExecute":true,"memberOnly":false,"triggerDishCount":2,"giftDishCount":1,"theSecondRate":10,"theSecondPrice":0,"theSecondType":0,"status":true,"supportPos":0,"supportMealMachine":1,"supportWechat":1,"grade":true,"numSeq":null,"statusStr":"","memberOnlyStr":"","marketTypeStr":"","triggerTypeStr":"","marketDishList":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],"triggerDishList":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],"triggerDishSum":null,"marketDishOrderItem":null,"storeList":[],"gradeList":[2004800,3001772,3001773,3008358],"marketKindDishData":null,"triggerKindDishData":null}]
	 * errmsg : 0
	 */


	/**
	 * appid : 59841423
	 * brandid : 47
	 * marketid : 1
	 * marketName : 折扣（65折）
	 * triggerType : 2
	 * marketType : 1
	 * dateAvailable : false
	 * timeAvailable : false
	 * weekAvailable : false
	 * startDate : 0
	 * endDate : 0
	 * startTime :
	 * endTime :
	 * week :
	 * rate : 1.0
	 * cash : 5.0
	 * loopFullCash : 0.0
	 * fullCash : 0.0
	 * commonExecute : true
	 * memberOnly : false
	 * triggerDishCount : 2
	 * giftDishCount : 1
	 * theSecondRate : 10.0
	 * theSecondPrice : 0.0
	 * theSecondType : 0
	 * status : true
	 * supportPos : 0
	 * supportMealMachine : 1
	 * supportWechat : 1
	 * grade : true
	 * numSeq : null
	 * statusStr :
	 * memberOnlyStr :
	 * marketTypeStr :
	 * triggerTypeStr :
	 * marketDishList : [1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
	 * triggerDishList : [1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
	 * triggerDishSum : null
	 * marketDishOrderItem : null
	 * storeList : []
	 * gradeList : [2004800,3001772,3001773,3008358]
	 * marketKindDishData : null
	 * triggerKindDishData : null
	 */

	private String  appid;
	private int     brandid;
	private int     marketid;
	private String  marketName;
	private int     triggerType;
	private int     marketType;
	private boolean dateAvailable;
	private boolean timeAvailable;
	private boolean weekAvailable;
	private long     startDate;
	private long     endDate;
	private String  startTime;
	private String  endTime;
	private String  week;
	private double  rate;
	private double  cash;
	private double  loopFullCash;
	private double  fullCash;
	private boolean commonExecute;
	private boolean memberOnly;
	private int     triggerDishCount;
	private int     giftDishCount;
	private double  theSecondRate;
	private double  theSecondPrice;
	private int     theSecondType;
	private boolean status;
	private int     supportPos;
	private int     supportMealMachine;
	private int     supportWechat;
	private boolean grade;
	private String  statusStr;

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

	public int getMarketid() {
		return marketid;
	}

	public void setMarketid(int marketid) {
		this.marketid = marketid;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public int getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(int triggerType) {
		this.triggerType = triggerType;
	}

	public int getMarketType() {
		return marketType;
	}

	public void setMarketType(int marketType) {
		this.marketType = marketType;
	}

	public boolean isDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(boolean dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public boolean isTimeAvailable() {
		return timeAvailable;
	}

	public void setTimeAvailable(boolean timeAvailable) {
		this.timeAvailable = timeAvailable;
	}

	public boolean isWeekAvailable() {
		return weekAvailable;
	}

	public void setWeekAvailable(boolean weekAvailable) {
		this.weekAvailable = weekAvailable;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getLoopFullCash() {
		return loopFullCash;
	}

	public void setLoopFullCash(double loopFullCash) {
		this.loopFullCash = loopFullCash;
	}

	public double getFullCash() {
		return fullCash;
	}

	public void setFullCash(double fullCash) {
		this.fullCash = fullCash;
	}

	public boolean isCommonExecute() {
		return commonExecute;
	}

	public void setCommonExecute(boolean commonExecute) {
		this.commonExecute = commonExecute;
	}

	public boolean isMemberOnly() {
		return memberOnly;
	}

	public void setMemberOnly(boolean memberOnly) {
		this.memberOnly = memberOnly;
	}

	public int getTriggerDishCount() {
		return triggerDishCount;
	}

	public void setTriggerDishCount(int triggerDishCount) {
		this.triggerDishCount = triggerDishCount;
	}

	public int getGiftDishCount() {
		return giftDishCount;
	}

	public void setGiftDishCount(int giftDishCount) {
		this.giftDishCount = giftDishCount;
	}

	public double getTheSecondRate() {
		return theSecondRate;
	}

	public void setTheSecondRate(double theSecondRate) {
		this.theSecondRate = theSecondRate;
	}

	public double getTheSecondPrice() {
		return theSecondPrice;
	}

	public void setTheSecondPrice(double theSecondPrice) {
		this.theSecondPrice = theSecondPrice;
	}

	public int getTheSecondType() {
		return theSecondType;
	}

	public void setTheSecondType(int theSecondType) {
		this.theSecondType = theSecondType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSupportPos() {
		return supportPos;
	}

	public void setSupportPos(int supportPos) {
		this.supportPos = supportPos;
	}

	public int getSupportMealMachine() {
		return supportMealMachine;
	}

	public void setSupportMealMachine(int supportMealMachine) {
		this.supportMealMachine = supportMealMachine;
	}

	public int getSupportWechat() {
		return supportWechat;
	}

	public void setSupportWechat(int supportWechat) {
		this.supportWechat = supportWechat;
	}

	public boolean isGrade() {
		return grade;
	}

	public void setGrade(boolean grade) {
		this.grade = grade;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getMemberOnlyStr() {
		return memberOnlyStr;
	}

	public void setMemberOnlyStr(String memberOnlyStr) {
		this.memberOnlyStr = memberOnlyStr;
	}

	public String getMarketTypeStr() {
		return marketTypeStr;
	}

	public void setMarketTypeStr(String marketTypeStr) {
		this.marketTypeStr = marketTypeStr;
	}

	public String getTriggerTypeStr() {
		return triggerTypeStr;
	}

	public void setTriggerTypeStr(String triggerTypeStr) {
		this.triggerTypeStr = triggerTypeStr;
	}

	public int getMarketKindDishData() {
		return marketKindDishData;
	}

	public void setMarketKindDishData(int marketKindDishData) {
		this.marketKindDishData = marketKindDishData;
	}

	public int getTriggerKindDishData() {
		return triggerKindDishData;
	}

	public void setTriggerKindDishData(int triggerKindDishData) {
		this.triggerKindDishData = triggerKindDishData;
	}

	public List<Integer> getMarketDishList() {
		return marketDishList;
	}

	public void setMarketDishList(List<Integer> marketDishList) {
		this.marketDishList = marketDishList;
	}

	public List<Integer> getTriggerDishList() {
		return triggerDishList;
	}

	public void setTriggerDishList(List<Integer> triggerDishList) {
		this.triggerDishList = triggerDishList;
	}

	public List<Integer> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Integer> storeList) {
		this.storeList = storeList;
	}

	public List<Long> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Long> gradeList) {
		this.gradeList = gradeList;
	}


	@Override
	public Object clone() {
		Market market = null;
		try {
			market = (Market) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return market;
	}


	private String        memberOnlyStr;
	private String        marketTypeStr;
	private String        triggerTypeStr;
	private int           marketKindDishData;
	private int           triggerKindDishData;
	private List<Integer> marketDishList;
	private List<Integer> triggerDishList;
	private List<Integer> storeList;
	private List<Long>    gradeList;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.appid);
		dest.writeInt(this.brandid);
		dest.writeInt(this.marketid);
		dest.writeString(this.marketName);
		dest.writeInt(this.triggerType);
		dest.writeInt(this.marketType);
		dest.writeByte(this.dateAvailable ? (byte) 1 : (byte) 0);
		dest.writeByte(this.timeAvailable ? (byte) 1 : (byte) 0);
		dest.writeByte(this.weekAvailable ? (byte) 1 : (byte) 0);
		dest.writeLong(this.startDate);
		dest.writeLong(this.endDate);
		dest.writeString(this.startTime);
		dest.writeString(this.endTime);
		dest.writeString(this.week);
		dest.writeDouble(this.rate);
		dest.writeDouble(this.cash);
		dest.writeDouble(this.loopFullCash);
		dest.writeDouble(this.fullCash);
		dest.writeByte(this.commonExecute ? (byte) 1 : (byte) 0);
		dest.writeByte(this.memberOnly ? (byte) 1 : (byte) 0);
		dest.writeInt(this.triggerDishCount);
		dest.writeInt(this.giftDishCount);
		dest.writeDouble(this.theSecondRate);
		dest.writeDouble(this.theSecondPrice);
		dest.writeInt(this.theSecondType);
		dest.writeByte(this.status ? (byte) 1 : (byte) 0);
		dest.writeInt(this.supportPos);
		dest.writeInt(this.supportMealMachine);
		dest.writeInt(this.supportWechat);
		dest.writeByte(this.grade ? (byte) 1 : (byte) 0);
		dest.writeString(this.statusStr);
		dest.writeString(this.memberOnlyStr);
		dest.writeString(this.marketTypeStr);
		dest.writeString(this.triggerTypeStr);
		dest.writeInt(this.marketKindDishData);
		dest.writeInt(this.triggerKindDishData);
		dest.writeList(this.marketDishList);
		dest.writeList(this.triggerDishList);
		dest.writeList(this.storeList);
		dest.writeList(this.gradeList);
	}

	public Market() {
	}

	protected Market(Parcel in) {
		this.appid = in.readString();
		this.brandid = in.readInt();
		this.marketid = in.readInt();
		this.marketName = in.readString();
		this.triggerType = in.readInt();
		this.marketType = in.readInt();
		this.dateAvailable = in.readByte() != 0;
		this.timeAvailable = in.readByte() != 0;
		this.weekAvailable = in.readByte() != 0;
		this.startDate = in.readInt();
		this.endDate = in.readInt();
		this.startTime = in.readString();
		this.endTime = in.readString();
		this.week = in.readString();
		this.rate = in.readDouble();
		this.cash = in.readDouble();
		this.loopFullCash = in.readDouble();
		this.fullCash = in.readDouble();
		this.commonExecute = in.readByte() != 0;
		this.memberOnly = in.readByte() != 0;
		this.triggerDishCount = in.readInt();
		this.giftDishCount = in.readInt();
		this.theSecondRate = in.readDouble();
		this.theSecondPrice = in.readDouble();
		this.theSecondType = in.readInt();
		this.status = in.readByte() != 0;
		this.supportPos = in.readInt();
		this.supportMealMachine = in.readInt();
		this.supportWechat = in.readInt();
		this.grade = in.readByte() != 0;
		this.statusStr = in.readString();
		this.memberOnlyStr = in.readString();
		this.marketTypeStr = in.readString();
		this.triggerTypeStr = in.readString();
		this.marketKindDishData = in.readInt();
		this.triggerKindDishData = in.readInt();
		this.marketDishList = new ArrayList<Integer>();
		in.readList(this.marketDishList, Integer.class.getClassLoader());
		this.triggerDishList = new ArrayList<Integer>();
		in.readList(this.triggerDishList, Integer.class.getClassLoader());
		this.storeList = new ArrayList<Integer>();
		in.readList(this.storeList, Integer.class.getClassLoader());
		this.gradeList = new ArrayList<Long>();
		in.readList(this.gradeList, Integer.class.getClassLoader());
	}

	public static final Parcelable.Creator<Market> CREATOR = new Parcelable.Creator<Market>() {
		@Override
		public Market createFromParcel(Parcel source) {
			return new Market(source);
		}

		@Override
		public Market[] newArray(int size) {
			return new Market[size];
		}
	};
}
