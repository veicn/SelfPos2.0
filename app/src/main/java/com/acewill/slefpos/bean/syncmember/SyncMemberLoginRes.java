package com.acewill.slefpos.bean.syncmember;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/30 11:08
 * Desc：
 */
public class SyncMemberLoginRes implements Serializable {

	/**
	 * code : 0
	 * data : {"ouid":"JccAMqU8SLuTZO-yBscqpg","memberNo":"0605100005287415","memberName":"Lyndon","balance":9997.84,"coupon":[],"birthday":"2018-05-28","mobile":"13822540060","point":16,"memberType":{"ouid":"G0DSIO6RRCWuNsmX9bDb5g","name":"测试会员"},"memberTypeLevel":{"ouid":"wh7tLUbfRHmVL5IfBLAWbQ","levelNo":1,"name":"普通会员"},"storeName":"乔鑫模式"}
	 */

	private int      code;
	private String   message;
	private DataBean data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static class DataBean implements Serializable {
		/**
		 * ouid : JccAMqU8SLuTZO-yBscqpg
		 * memberNo : 0605100005287415
		 * memberName : Lyndon
		 * balance : 9997.84
		 * coupon : []
		 * birthday : 2018-05-28
		 * mobile : 13822540060
		 * point : 16
		 * memberType : {"ouid":"G0DSIO6RRCWuNsmX9bDb5g","name":"测试会员"}
		 * memberTypeLevel : {"ouid":"wh7tLUbfRHmVL5IfBLAWbQ","levelNo":1,"name":"普通会员"}
		 * storeName : 乔鑫模式
		 */

		private String              ouid;
		private String              memberNo;
		private String              memberName;
		private float               balance;
		private String              birthday;
		private String              mobile;
		private int                 point;
		private MemberTypeBean      memberType;
		private MemberTypeLevelBean memberTypeLevel;
		private PointRule           pointRule;

		public MemberDateActivity getMemberDateActivity() {
			return memberDateActivity;
		}

		public void setMemberDateActivity(MemberDateActivity memberDateActivity) {
			this.memberDateActivity = memberDateActivity;
		}

		private MemberDateActivity memberDateActivity;
		private String             storeName;
		private List<MemberCoupon> coupon;

		public SyncMemberLoginRes.DataBean myclone() {
			SyncMemberLoginRes.DataBean outer = null;
			try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream    oos  = new ObjectOutputStream(baos);
				oos.writeObject(this);
				// 将流序列化成对象
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				ObjectInputStream    ois  = new ObjectInputStream(bais);
				outer = (SyncMemberLoginRes.DataBean) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return outer;
		}

		public String getOuid() {
			return ouid;
		}

		public void setOuid(String ouid) {
			this.ouid = ouid;
		}

		public String getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(String memberNo) {
			this.memberNo = memberNo;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public float getBalance() {
			return balance;
		}

		public void setBalance(float balance) {
			this.balance = balance;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public MemberTypeBean getMemberType() {
			return memberType;
		}

		public void setMemberType(MemberTypeBean memberType) {
			this.memberType = memberType;
		}

		public MemberTypeLevelBean getMemberTypeLevel() {
			return memberTypeLevel;
		}

		public void setMemberTypeLevel(MemberTypeLevelBean memberTypeLevel) {
			this.memberTypeLevel = memberTypeLevel;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public List<MemberCoupon> getCoupon() {
			return coupon;
		}

		public void setCoupon(List<MemberCoupon> coupon) {
			this.coupon = coupon;
		}

		public PointRule getPointRule() {
			return pointRule;
		}

		public void setPointRule(PointRule pointRule) {
			this.pointRule = pointRule;
		}

		public static class MemberTypeBean implements Serializable {
			/**
			 * ouid : G0DSIO6RRCWuNsmX9bDb5g
			 * name : 测试会员
			 */

			private String ouid;
			private String name;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		public static class MemberDateActivity implements Serializable {
			/**
			 * ouid : G0DSIO6RRCWuNsmX9bDb5g
			 * name : 测试会员
			 */

			private String         singleDiscountType;
			private String         singleDiscountValue;
			private String         multipleFlag;
			private int            useCondition;
			private List<ItemBean> itemList;

			public String getSingleDiscountType() {
				return singleDiscountType;
			}

			public void setSingleDiscountType(String singleDiscountType) {
				this.singleDiscountType = singleDiscountType;
			}

			public String getSingleDiscountValue() {
				return singleDiscountValue;
			}

			public void setSingleDiscountValue(String singleDiscountValue) {
				this.singleDiscountValue = singleDiscountValue;
			}

			public String getMultipleFlag() {
				return multipleFlag;
			}

			public void setMultipleFlag(String multipleFlag) {
				this.multipleFlag = multipleFlag;
			}

			public int getUseCondition() {
				return useCondition;
			}

			public void setUseCondition(int useCondition) {
				this.useCondition = useCondition;
			}

			public List<ItemBean> getItemList() {
				return itemList;
			}

			public void setItemList(List<ItemBean> itemList) {
				this.itemList = itemList;
			}
		}


		public static class MemberTypeLevelBean implements Serializable {
			/**
			 * ouid : wh7tLUbfRHmVL5IfBLAWbQ
			 * levelNo : 1
			 * name : 普通会员
			 */

			private String ouid;
			private int    levelNo;
			private String name;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public int getLevelNo() {
				return levelNo;
			}

			public void setLevelNo(int levelNo) {
				this.levelNo = levelNo;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		public static class PointRule implements Serializable {
			private String ouid;
			private int    promoValue;
			private int    upperLimit;
			private String multipleFlag;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public int getPromoValue() {
				return promoValue;
			}

			public void setPromoValue(int promoValue) {
				this.promoValue = promoValue;
			}

			public String getMultipleFlag() {
				return multipleFlag;
			}

			public void setMultipleFlag(String multipleFlag) {
				this.multipleFlag = multipleFlag;
			}

			public int getUpperLimit() {
				return upperLimit;
			}

			public void setUpperLimit(int upperLimit) {
				this.upperLimit = upperLimit;
			}
		}

		public static class ItemBean implements Serializable {
			private String skuOuid;
			private String discountType;
			private float  discountValue;

			public String getSkuOuid() {
				return skuOuid;
			}

			public void setSkuOuid(String skuOuid) {
				this.skuOuid = skuOuid;
			}

			public String getDiscountType() {
				return discountType;
			}

			public void setDiscountType(String discountType) {
				this.discountType = discountType;
			}

			public float getDiscountValue() {
				return discountValue;
			}

			public void setDiscountValue(float discountValue) {
				this.discountValue = discountValue;
			}
		}


		public static class MemberCoupon implements Serializable {


			/**
			 * couponNo : 0605060145081266
			 * couponName : 10元
			 * couponType : B
			 * couponValue : 10.0
			 * estimateAmount : 10.0
			 * multipleFlag : Y
			 * storeList : ["奈雪的茶测试门店"]
			 */

			private String       couponNo;
			private String       couponName;
			private String       couponType;
			private float        couponValue;
			private float        estimateAmount;
			private String       multipleFlag;

			public float getFullAmount() {
				return fullAmount;
			}

			public void setFullAmount(float fullAmount) {
				this.fullAmount = fullAmount;
			}

			public String getFullType() {
				return fullType;
			}

			public void setFullType(String fullType) {
				this.fullType = fullType;
			}

			private float        fullAmount;
			private String       fullType;
			private List<String> storeList;
			private List<String> skuOuidList;


			public boolean isCanUse() {
				return canUse;
			}

			private boolean canUse;//标记这个券是否可用

			public String getCouponNo() {
				return couponNo;
			}

			public void setCouponNo(String couponNo) {
				this.couponNo = couponNo;
			}

			public String getCouponName() {
				return couponName;
			}

			public void setCouponName(String couponName) {
				this.couponName = couponName;
			}

			public String getCouponType() {
				return couponType;
			}

			public void setCouponType(String couponType) {
				this.couponType = couponType;
			}

			public float getCouponValue() {
				return couponValue;
			}

			public void setCouponValue(float couponValue) {
				this.couponValue = couponValue;
			}

			public float getEstimateAmount() {
				return estimateAmount;
			}

			public void setEstimateAmount(float estimateAmount) {
				this.estimateAmount = estimateAmount;
			}

			public String getMultipleFlag() {
				return multipleFlag;
			}

			public void setMultipleFlag(String multipleFlag) {
				this.multipleFlag = multipleFlag;
			}

			public List<String> getStoreList() {
				return storeList;
			}

			public void setStoreList(List<String> storeList) {
				this.storeList = storeList;
			}

			public List<String> getSkuOuidList() {
				return skuOuidList;
			}

			public void setSkuOuidList(List<String> skuOuidList) {
				this.skuOuidList = skuOuidList;
			}

			public void setCanUse(boolean b) {
				this.canUse = b;
			}
		}


	}


}
