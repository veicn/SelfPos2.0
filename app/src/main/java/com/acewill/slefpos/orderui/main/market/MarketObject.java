package com.acewill.slefpos.orderui.main.market;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by DHH on 2017/3/15.
 */

public class MarketObject implements Serializable,Parcelable {
	private String     marketType;//营销方案类型
	private String     category;//营销方案类型  MANUAL手动   AUTO营销活动
	private BigDecimal reduceCash; //营销方案优惠了多少金额
	private String     marketName; //营销方案名称

	public MarketObject() {

	}

	public MarketObject(MarketObject object) {
		marketType = object.getMarketType();
		reduceCash = object.getReduceCash();
		marketName = object.getMarketName();
	}

	protected MarketObject(Parcel in) {
		marketType = in.readString();
		category = in.readString();
		marketName = in.readString();
	}

	public static final Creator<MarketObject> CREATOR = new Creator<MarketObject>() {
		@Override
		public MarketObject createFromParcel(Parcel in) {
			return new MarketObject(in);
		}

		@Override
		public MarketObject[] newArray(int size) {
			return new MarketObject[size];
		}
	};

	public MarketObject clone(MarketObject object) {
		return new MarketObject(object);
	}

	public MarketObject(String marketName, BigDecimal reduceCash, String marketType) {
		this.marketName = marketName;
		this.reduceCash = reduceCash;
		this.marketType = marketType;
		category = "AUTO";// 营销活动
	}

	public BigDecimal getReduceCash() {
		return reduceCash.setScale(2, BigDecimal.ROUND_DOWN);
	}

	public void setReduceCash(BigDecimal reduceCash) {
		this.reduceCash = reduceCash;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(marketType);
		dest.writeString(category);
		dest.writeString(marketName);
	}
}
