package com.acewill.slefpos.bean.cartbean;

import android.os.Parcel;
import android.os.Parcelable;

import com.acewill.slefpos.bean.uibean.UIDish;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Author：Anch
 * Date：2018/2/5 16:51
 * Desc：
 */
public class CartDish extends UIDish implements Parcelable {
	private String kindId;
	/**
	 * 商品的优惠金额， 不一定为正值，还有可能为负值
	 * <p>
	 * 正值表示的是 商品的优惠
	 * <p>
	 * 负值表示的是 商品的加价
	 */
	private float  discountAmount;
	private boolean isCalculate;//该字段用于判断是否已经执行了营销方案
	private  boolean     gift;//是否是赠送的菜品
	public int itemIndex;

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public boolean isCalculate() {
		return isCalculate;
	}

	public void setCalculate(boolean calculate) {
		isCalculate = calculate;
	}

	public boolean isGift() {
		return gift;
	}

	public void setGift(boolean gift) {
		this.gift = gift;
	}

	public int getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public CartDish myclone() {
		CartDish outer = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream    ois  = new ObjectInputStream(bais);
			outer = (CartDish) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return outer;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(this.kindId);
		dest.writeFloat(this.discountAmount);
		dest.writeByte(this.isCalculate ? (byte) 1 : (byte) 0);
		dest.writeByte(this.gift ? (byte) 1 : (byte) 0);
		dest.writeInt(this.itemIndex);
	}

	public CartDish() {
	}

	protected CartDish(Parcel in) {
		super(in);
		this.kindId = in.readString();
		this.discountAmount = in.readFloat();
		this.isCalculate = in.readByte() != 0;
		this.gift = in.readByte() != 0;
		this.itemIndex = in.readInt();
	}

	public static final Creator<CartDish> CREATOR = new Creator<CartDish>() {
		@Override
		public CartDish createFromParcel(Parcel source) {
			return new CartDish(source);
		}

		@Override
		public CartDish[] newArray(int size) {
			return new CartDish[size];
		}
	};
}
