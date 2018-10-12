package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

import com.acewill.slefpos.bean.smarantstorebean.Market;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/27 15:20
 * Desc：
 */
public class UIDish implements Serializable, Parcelable {
	/**
	 * dishID : 1
	 * dishName : 马来西亚水煮肉片好吃（500g）
	 * price : 3.0
	 * cost : 8.0
	 * memberPrice : 1.0
	 * waimaiPrice : 3.0
	 * dishUnitID : 1
	 * dishUnit : 份
	 * dishCount : 999952
	 * imageName : http://szfileserver.419174855.mtmss.com/common/fileupload/20180125155911_7441.jpg
	 * comment : 这个菜特别好吃,但是我不喜欢吃哈哈哈哈哈，鹅鹅鹅，曲项向天歌，白毛浮露水，红掌拨清波。
	 * dishDiscount : []
	 * isPackage : 0
	 * packageItems : []
	 * optionCategoryList : [{"id":1068,"appId":"87825359","brandId":34,"optionList":[{"id":1530,"appId":"87825359","brandId":34,"optionName":"微辣","price":1,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1531,"appId":"87825359","brandId":34,"optionName":"中辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1532,"appId":"87825359","brandId":34,"optionName":"猛辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null}],"optionCategoryName":"口味","minimalOptions":1,"multipleOptions":true,"maximalOptions":2,"multipleOptionsStr":null},{"id":1086,"appId":"87825359","brandId":34,"optionList":[{"id":1439,"appId":"87825359","brandId":34,"optionName":"鸡肉","price":2,"required":false,"categoryId":1086,"numSeq":null,"requiredStr":null},{"id":1440,"appId":"87825359","brandId":34,"optionName":"腊肠","price":1,"required":false,"categoryId":1086,"numSeq":null,"requiredStr":null},{"id":1441,"appId":"87825359","brandId":34,"optionName":"芝士","price":0,"required":false,"categoryId":1086,"numSeq":null,"requiredStr":null}],"optionCategoryName":"配菜","minimalOptions":1,"multipleOptions":true,"maximalOptions":1,"multipleOptionsStr":null},{"id":1089,"appId":"87825359","brandId":34,"optionList":[{"id":1391,"appId":"87825359","brandId":34,"optionName":"热饮","price":0,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null},{"id":1392,"appId":"87825359","brandId":34,"optionName":"冻饮","price":2,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null}],"optionCategoryName":"饮料","minimalOptions":1,"multipleOptions":true,"maximalOptions":1,"multipleOptionsStr":null}]
	 * dishKind : 1
	 * dishKindColor : ffd58a
	 * printerStr :
	 * sortMark : 1103
	 * specificationList : []
	 * kindSeq : 1
	 * kindMachineSeq : 0
	 * kindScanSeq : 0
	 * waiDai_cost : 0.0
	 * subBrandId : null
	 * marketList : []
	 * salesCount : 0
	 * discounted : 1
	 * optionRequired : false
	 * dishSeq : 1
	 * wx_count : 0
	 * dishPublicity : 0
	 * dishPublicityStr :
	 * weighted : 0
	 * temp_priced : 0
	 * recommandList : [{"packageid":41,"packageName":"套餐2","description":"","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20180125153447_559.jpg","price":"8.00","memberPrice":"12.00","specialPrice":"8.00"}]
	 * englishName : my_name_dishmumumuuoiju_tghujikngdswkjwi
	 * marketPrice : null
	 * marketName : null
	 * abandonPrice : null
	 * showPackageItemsFlag : false
	 */

	private String                 dishID;
	private String                 skuId;//同步时平台独有的字段，在下单的接口用到
	private String                 dishName;
	private String                 price;
	private String                 cost;
	private String                 memberPrice;
	private double                 waimaiPrice;
	private int                    dishUnitID;
	private String                 dishUnit;
	private int                    dishCount;
	private String                 imageName;
	private String                 comment;
	private int                    isPackage;
	private String                 dishKind;
	private String                 dishKindColor;
	private String                 printerStr;
	private String                 sortMark;
	private int                    kindSeq;
	private int                    kindMachineSeq;
	private int                    kindScanSeq;
	private double                 waiDai_cost;
	private int                    salesCount;
	private int                    discounted;
	private boolean                optionRequired;
	private int                    dishSeq;
	private int                    wx_count;
	private int                    dishPublicity;
	private String                 dishPublicityStr;
	private int                    weighted;
	private int                    temp_priced;
	private String                 englishName;
	private String                 marketPrice;
	private String                 marketName;
	private List<UIPackageItem>    packageItems;
	private List<UIOptionCategory> optionCategoryList;
	private List<Market>           marketList;
	private List<UIRecommand>      recommandList;
	private Integer dealId;

	/**
	 * 同步时独有菜品的规格id
	 */
	private String                    skuSpecOuid;
	//	===================================================
	//	以下是订单数据
	private String                    dishKindStr;//菜的分类
	private int                       quantity;//菜的数量
	private List<UITasteOption>       optionList;//这个菜的定制项
	private List<UIPackageOptionItem> subItemList;//这个菜的套餐项


	public UIDish myclone() {
		UIDish outer = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream    ois  = new ObjectInputStream(bais);
			outer = (UIDish) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return outer;
	}

	public String getDishID() {
		return dishID;
	}

	public void setDishID(String dishID) {
		this.dishID = dishID;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	public double getWaimaiPrice() {
		return waimaiPrice;
	}

	public void setWaimaiPrice(double waimaiPrice) {
		this.waimaiPrice = waimaiPrice;
	}

	public int getDishUnitID() {
		return dishUnitID;
	}

	public void setDishUnitID(int dishUnitID) {
		this.dishUnitID = dishUnitID;
	}

	public String getDishUnit() {
		return dishUnit;
	}

	public void setDishUnit(String dishUnit) {
		this.dishUnit = dishUnit;
	}

	public int getDishCount() {
		return dishCount;
	}

	public void setDishCount(int dishCount) {
		this.dishCount = dishCount;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(int isPackage) {
		this.isPackage = isPackage;
	}

	public String getDishKind() {
		return dishKind;
	}

	public void setDishKind(String dishKind) {
		this.dishKind = dishKind;
	}

	public String getDishKindColor() {
		return dishKindColor;
	}

	public void setDishKindColor(String dishKindColor) {
		this.dishKindColor = dishKindColor;
	}

	public String getPrinterStr() {
		return printerStr;
	}

	public void setPrinterStr(String printerStr) {
		this.printerStr = printerStr;
	}

	public String getSortMark() {
		return sortMark;
	}

	public void setSortMark(String sortMark) {
		this.sortMark = sortMark;
	}

	public int getKindSeq() {
		return kindSeq;
	}

	public void setKindSeq(int kindSeq) {
		this.kindSeq = kindSeq;
	}

	public int getKindMachineSeq() {
		return kindMachineSeq;
	}

	public void setKindMachineSeq(int kindMachineSeq) {
		this.kindMachineSeq = kindMachineSeq;
	}

	public int getKindScanSeq() {
		return kindScanSeq;
	}

	public void setKindScanSeq(int kindScanSeq) {
		this.kindScanSeq = kindScanSeq;
	}

	public double getWaiDai_cost() {
		return waiDai_cost;
	}

	public void setWaiDai_cost(double waiDai_cost) {
		this.waiDai_cost = waiDai_cost;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public int getDiscounted() {
		return discounted;
	}

	public void setDiscounted(int discounted) {
		this.discounted = discounted;
	}

	public boolean isOptionRequired() {
		return optionRequired;
	}

	public void setOptionRequired(boolean optionRequired) {
		this.optionRequired = optionRequired;
	}

	public int getDishSeq() {
		return dishSeq;
	}

	public void setDishSeq(int dishSeq) {
		this.dishSeq = dishSeq;
	}

	public int getWx_count() {
		return wx_count;
	}

	public void setWx_count(int wx_count) {
		this.wx_count = wx_count;
	}

	public int getDishPublicity() {
		return dishPublicity;
	}

	public void setDishPublicity(int dishPublicity) {
		this.dishPublicity = dishPublicity;
	}

	public String getDishPublicityStr() {
		return dishPublicityStr;
	}

	public void setDishPublicityStr(String dishPublicityStr) {
		this.dishPublicityStr = dishPublicityStr;
	}

	public int getWeighted() {
		return weighted;
	}

	public void setWeighted(int weighted) {
		this.weighted = weighted;
	}

	public int getTemp_priced() {
		return temp_priced;
	}

	public void setTemp_priced(int temp_priced) {
		this.temp_priced = temp_priced;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public List<UIPackageItem> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(List<UIPackageItem> packageItems) {
		this.packageItems = packageItems;
	}

	public List<UIOptionCategory> getOptionCategoryList() {
		return optionCategoryList;
	}

	public void setOptionCategoryList(List<UIOptionCategory> optionCategoryList) {
		this.optionCategoryList = optionCategoryList;
	}

	public List<Market> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<Market> marketList) {
		this.marketList = marketList;
	}

	public List<UIRecommand> getRecommandList() {
		return recommandList;
	}

	public void setRecommandList(List<UIRecommand> recommandList) {
		this.recommandList = recommandList;
	}

	public String getDishKindStr() {
		return dishKindStr;
	}

	public void setDishKindStr(String dishKindStr) {
		this.dishKindStr = dishKindStr;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<UITasteOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<UITasteOption> optionList) {
		this.optionList = optionList;
	}

	public List<UIPackageOptionItem> getSubItemList() {
		return subItemList;
	}

	public void setSubItemList(List<UIPackageOptionItem> subItemList) {
		this.subItemList = subItemList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.dishID);
		dest.writeString(this.dishName);
		dest.writeString(this.price);
		dest.writeString(this.cost);
		dest.writeString(this.memberPrice);
		dest.writeDouble(this.waimaiPrice);
		dest.writeInt(this.dishUnitID);
		dest.writeString(this.dishUnit);
		dest.writeInt(this.dishCount);
		dest.writeString(this.imageName);
		dest.writeString(this.comment);
		dest.writeInt(this.isPackage);
		dest.writeString(this.dishKind);
		dest.writeString(this.dishKindColor);
		dest.writeString(this.printerStr);
		dest.writeString(this.sortMark);
		dest.writeInt(this.kindSeq);
		dest.writeInt(this.kindMachineSeq);
		dest.writeInt(this.kindScanSeq);
		dest.writeDouble(this.waiDai_cost);
		dest.writeInt(this.salesCount);
		dest.writeInt(this.discounted);
		dest.writeByte(this.optionRequired ? (byte) 1 : (byte) 0);
		dest.writeInt(this.dishSeq);
		dest.writeInt(this.wx_count);
		dest.writeInt(this.dishPublicity);
		dest.writeString(this.dishPublicityStr);
		dest.writeInt(this.weighted);
		dest.writeInt(this.temp_priced);
		dest.writeString(this.englishName);
		dest.writeString(this.marketPrice);
		dest.writeString(this.marketName);
		dest.writeTypedList(this.packageItems);
		dest.writeTypedList(this.optionCategoryList);
		dest.writeTypedList(this.marketList);
		dest.writeTypedList(this.recommandList);
		dest.writeString(this.dishKindStr);
		dest.writeInt(this.quantity);
		dest.writeTypedList(this.optionList);
		dest.writeTypedList(this.subItemList);
	}

	public UIDish() {
	}

	protected UIDish(Parcel in) {
		this.dishID = in.readString();
		this.dishName = in.readString();
		this.price = in.readString();
		this.cost = in.readString();
		this.memberPrice = in.readString();
		this.waimaiPrice = in.readDouble();
		this.dishUnitID = in.readInt();
		this.dishUnit = in.readString();
		this.dishCount = in.readInt();
		this.imageName = in.readString();
		this.comment = in.readString();
		this.isPackage = in.readInt();
		this.dishKind = in.readString();
		this.dishKindColor = in.readString();
		this.printerStr = in.readString();
		this.sortMark = in.readString();
		this.kindSeq = in.readInt();
		this.kindMachineSeq = in.readInt();
		this.kindScanSeq = in.readInt();
		this.waiDai_cost = in.readDouble();
		this.salesCount = in.readInt();
		this.discounted = in.readInt();
		this.optionRequired = in.readByte() != 0;
		this.dishSeq = in.readInt();
		this.wx_count = in.readInt();
		this.dishPublicity = in.readInt();
		this.dishPublicityStr = in.readString();
		this.weighted = in.readInt();
		this.temp_priced = in.readInt();
		this.englishName = in.readString();
		this.marketPrice = in.readString();
		this.marketName = in.readString();
		this.packageItems = in.createTypedArrayList(UIPackageItem.CREATOR);
		this.optionCategoryList = in.createTypedArrayList(UIOptionCategory.CREATOR);
		this.marketList = in.createTypedArrayList(Market.CREATOR);
		this.recommandList = in.createTypedArrayList(UIRecommand.CREATOR);
		this.dishKindStr = in.readString();
		this.quantity = in.readInt();
		this.optionList = in.createTypedArrayList(UITasteOption.CREATOR);
		this.subItemList = in.createTypedArrayList(UIPackageOptionItem.CREATOR);
	}

	public static final Creator<UIDish> CREATOR = new Creator<UIDish>() {
		@Override
		public UIDish createFromParcel(Parcel source) {
			return new UIDish(source);
		}

		@Override
		public UIDish[] newArray(int size) {
			return new UIDish[size];
		}
	};
	private boolean visible;

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getSkuSpecOuid() {
		return skuSpecOuid;
	}

	public void setSkuSpecOuid(String skuSpecOuid) {
		this.skuSpecOuid = skuSpecOuid;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
}