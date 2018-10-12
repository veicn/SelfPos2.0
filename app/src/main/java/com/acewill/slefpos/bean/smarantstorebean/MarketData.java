package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 16:47
 * Desc：
 */
public class MarketData implements Parcelable {

	/**
	 * result : 0
	 * content : [{"appid":"59841423","brandid":47,"marketid":1,"marketName":"折扣（65折）","triggerType":2,"marketType":1,"dateAvailable":false,"timeAvailable":false,"weekAvailable":false,"startDate":0,"endDate":0,"startTime":"","endTime":"","week":"","rate":1,"cash":5,"loopFullCash":0,"fullCash":0,"commonExecute":true,"memberOnly":false,"triggerDishCount":2,"giftDishCount":1,"theSecondRate":10,"theSecondPrice":0,"theSecondType":0,"status":true,"supportPos":0,"supportMealMachine":1,"supportWechat":1,"grade":true,"numSeq":null,"statusStr":"","memberOnlyStr":"","marketTypeStr":"","triggerTypeStr":"","marketDishList":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],"triggerDishList":[1,2,3,4,5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],"triggerDishSum":null,"marketDishOrderItem":null,"storeList":[],"gradeList":[2004800,3001772,3001773,3008358],"marketKindDishData":null,"triggerKindDishData":null}]
	 * errmsg : 0
	 */

	private int          result;
	private String       errmsg;
	private List<Market> content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<Market> getContent() {
		return content;
	}

	public void setContent(List<Market> content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.result);
		dest.writeString(this.errmsg);
		dest.writeTypedList(this.content);
	}

	public MarketData() {
	}

	protected MarketData(Parcel in) {
		this.result = in.readInt();
		this.errmsg = in.readString();
		this.content = in.createTypedArrayList(Market.CREATOR);
	}

	public static final Parcelable.Creator<MarketData> CREATOR = new Parcelable.Creator<MarketData>() {
		@Override
		public MarketData createFromParcel(Parcel source) {
			return new MarketData(source);
		}

		@Override
		public MarketData[] newArray(int size) {
			return new MarketData[size];
		}
	};
}
