package com.acewill.slefpos.bean.syncbean.syncorder;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/14 16:39
 * Desc：同步时下单model
 */
public class SyncAcceptReq {

	/**
	 * companyOuid : 【同步时提供】
	 * consumerKey : 【同步时提供】
	 * data : {}
	 */

	private String   companyOuid;
	private String   consumerKey;
	private DataBean data;

	public String getCompanyOuid() {
		return companyOuid;
	}

	public void setCompanyOuid(String companyOuid) {
		this.companyOuid = companyOuid;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {

		/**
		 * shopId : 订单所属门店编号
		 * ordersNo : 订单号
		 * ordersSeq : 流水号或桌号
		 * ordersTime : 下单时间
		 * expectTime : 期望送达(取货)时间
		 * deliverType : 交付方式
		 * totalAmount : 订单金额
		 * paidStatus : 付款状态
		 * paidAmount : 已付款金额
		 * customerRefNo : 顾客参考号码
		 * memo : 备注
		 * takeaway : {"receiverTel":"收货人联系电话","receiverName":"收货人姓名","receiverRegion":"收货人所在区域","receiverAddress":"收货人详细地址","receiverPosition":"收货人坐标位置"}
		 * items : [{"skuId":"商品编号","skuName":"商品名称","salePrice":"商品单价","qty":"商品数量","saleAmount":"商品销售金额","saleSubtotal":"单项销售金额合计","memo":"备注","mixs":[{"mixId":"加料编号","mixName":"加料名称","mixPrice":"加料单价","mixQty":"加料数量","mixAmount":"加料金额"}],"features":[{"featureId":"口味编号","featureName":"口味名称"}],"combos":[{"skuId":"商品编号","skuName":"商品名称","salePrice":"商品单价","qty":"商品数量","saleAmount":"商品销售金额","saleSubtotal":"单项销售金额合计","memo":"备注","mixs":[{"mixId":"加料编号","mixName":"加料名称","mixPrice":"加料单价","mixQty":"加料数量","mixAmount":"加料金额"}],"features":[{"featureId":"口味编号","featureName":"口味名称"}]}]}]
		 * fees : [{"feeType":"费用类型","feeName":"费用名称","feePrice":"费用单价","feeQty":"费用数量","feeAmount":"费用金额"}]
		 */

		private String          shopId;
		private String          ordersNo;
		private String          ordersSeq;
		private String          ordersTime;
		private String          expectTime;
		private String          deliverType;
		private String          totalAmount;
		private String          paidStatus;
		private String          paidAmount;
		private String          customerRefNo;
		private String          customerOuid;
		private String          memo;
		private TakeawayBean    takeaway;
		private List<ItemsBean> items;
		private List<FeesBean>  fees;

		public List<PromosBean> getPromos() {
			return promos;
		}

		public void setPromos(List<PromosBean> promos) {
			this.promos = promos;
		}

		private List<PromosBean>      promos;
		private List<PaymentItemBean> paymentItems;


		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getOrdersNo() {
			return ordersNo;
		}

		public void setOrdersNo(String ordersNo) {
			this.ordersNo = ordersNo;
		}

		public String getOrdersSeq() {
			return ordersSeq;
		}

		public void setOrdersSeq(String ordersSeq) {
			this.ordersSeq = ordersSeq;
		}

		public String getOrdersTime() {
			return ordersTime;
		}

		public void setOrdersTime(String ordersTime) {
			this.ordersTime = ordersTime;
		}

		public String getExpectTime() {
			return expectTime;
		}

		public void setExpectTime(String expectTime) {
			this.expectTime = expectTime;
		}

		public String getDeliverType() {
			return deliverType;
		}

		public void setDeliverType(String deliverType) {
			this.deliverType = deliverType;
		}

		public String getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getPaidStatus() {
			return paidStatus;
		}

		public void setPaidStatus(String paidStatus) {
			this.paidStatus = paidStatus;
		}

		public String getPaidAmount() {
			return paidAmount;
		}

		public void setPaidAmount(String paidAmount) {
			this.paidAmount = paidAmount;
		}

		public String getCustomerRefNo() {
			return customerRefNo;
		}

		public void setCustomerRefNo(String customerRefNo) {
			this.customerRefNo = customerRefNo;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public TakeawayBean getTakeaway() {
			return takeaway;
		}

		public void setTakeaway(TakeawayBean takeaway) {
			this.takeaway = takeaway;
		}

		public List<ItemsBean> getItems() {
			return items;
		}

		public void setItems(List<ItemsBean> items) {
			this.items = items;
		}

		public List<FeesBean> getFees() {
			return fees;
		}

		public void setFees(List<FeesBean> fees) {
			this.fees = fees;
		}

		public List<PaymentItemBean> getPaymentItems() {
			return paymentItems;
		}

		public void setPaymentItems(List<PaymentItemBean> paymentItems) {
			this.paymentItems = paymentItems;
		}

		public String getCustomerOuid() {
			return customerOuid;
		}

		public void setCustomerOuid(String customerOuid) {
			this.customerOuid = customerOuid;
		}

		public static class TakeawayBean {
			/**
			 * receiverTel : 收货人联系电话
			 * receiverName : 收货人姓名
			 * receiverRegion : 收货人所在区域
			 * receiverAddress : 收货人详细地址
			 * receiverPosition : 收货人坐标位置
			 */

			private String receiverTel;
			private String receiverName;
			private String receiverRegion;
			private String receiverAddress;
			private String receiverPosition;

			public String getReceiverTel() {
				return receiverTel;
			}

			public void setReceiverTel(String receiverTel) {
				this.receiverTel = receiverTel;
			}

			public String getReceiverName() {
				return receiverName;
			}

			public void setReceiverName(String receiverName) {
				this.receiverName = receiverName;
			}

			public String getReceiverRegion() {
				return receiverRegion;
			}

			public void setReceiverRegion(String receiverRegion) {
				this.receiverRegion = receiverRegion;
			}

			public String getReceiverAddress() {
				return receiverAddress;
			}

			public void setReceiverAddress(String receiverAddress) {
				this.receiverAddress = receiverAddress;
			}

			public String getReceiverPosition() {
				return receiverPosition;
			}

			public void setReceiverPosition(String receiverPosition) {
				this.receiverPosition = receiverPosition;
			}
		}

		public static class ItemsBean {
			/**
			 * skuId : 商品编号
			 * skuName : 商品名称
			 * salePrice : 商品单价
			 * qty : 商品数量
			 * saleAmount : 商品销售金额
			 * saleSubtotal : 单项销售金额合计
			 * memo : 备注
			 * mixs : [{"mixId":"加料编号","mixName":"加料名称","mixPrice":"加料单价","mixQty":"加料数量","mixAmount":"加料金额"}]
			 * features : [{"featureId":"口味编号","featureName":"口味名称"}]
			 * combos : [{"skuId":"商品编号","skuName":"商品名称","salePrice":"商品单价","qty":"商品数量","saleAmount":"商品销售金额","saleSubtotal":"单项销售金额合计","memo":"备注","mixs":[{"mixId":"加料编号","mixName":"加料名称","mixPrice":"加料单价","mixQty":"加料数量","mixAmount":"加料金额"}],"features":[{"featureId":"口味编号","featureName":"口味名称"}]}]
			 */

			private String             skuId;
			private String             skuName;
			private String             salePrice;
			private int                qty;
			private String             saleAmount;
			private String             saleSubtotal;
			private String             memo;
			private List<MixsBean>     mixs;
			private List<FeaturesBean> features;
			private List<CombosBean>   combos;

			public String getSkuId() {
				return skuId;
			}

			public void setSkuId(String skuId) {
				this.skuId = skuId;
			}

			public String getSkuName() {
				return skuName;
			}

			public void setSkuName(String skuName) {
				this.skuName = skuName;
			}

			public String getSalePrice() {
				return salePrice;
			}

			public void setSalePrice(String salePrice) {
				this.salePrice = salePrice;
			}

			public int getQty() {
				return qty;
			}

			public void setQty(int qty) {
				this.qty = qty;
			}

			public String getSaleAmount() {
				return saleAmount;
			}

			public void setSaleAmount(String saleAmount) {
				this.saleAmount = saleAmount;
			}

			public String getSaleSubtotal() {
				return saleSubtotal;
			}

			public void setSaleSubtotal(String saleSubtotal) {
				this.saleSubtotal = saleSubtotal;
			}

			public String getMemo() {
				return memo;
			}

			public void setMemo(String memo) {
				this.memo = memo;
			}

			public List<MixsBean> getMixs() {
				return mixs;
			}

			public void setMixs(List<MixsBean> mixs) {
				this.mixs = mixs;
			}

			public List<FeaturesBean> getFeatures() {
				return features;
			}

			public void setFeatures(List<FeaturesBean> features) {
				this.features = features;
			}

			public List<CombosBean> getCombos() {
				return combos;
			}

			public void setCombos(List<CombosBean> combos) {
				this.combos = combos;
			}

			public static class MixsBean {
				/**
				 * mixId : 加料编号
				 * mixName : 加料名称
				 * mixPrice : 加料单价
				 * mixQty : 加料数量
				 * mixAmount : 加料金额
				 */

				private String mixId;
				private String mixName;
				private float  mixPrice;
				private int    mixQty;
				private float  mixAmount;

				public String getMixId() {
					return mixId;
				}

				public void setMixId(String mixId) {
					this.mixId = mixId;
				}

				public String getMixName() {
					return mixName;
				}

				public void setMixName(String mixName) {
					this.mixName = mixName;
				}

				public float getMixPrice() {
					return mixPrice;
				}

				public void setMixPrice(float mixPrice) {
					this.mixPrice = mixPrice;
				}

				public int getMixQty() {
					return mixQty;
				}

				public void setMixQty(int mixQty) {
					this.mixQty = mixQty;
				}

				public float getMixAmount() {
					return mixAmount;
				}

				public void setMixAmount(float mixAmount) {
					this.mixAmount = mixAmount;
				}
			}

			public static class FeaturesBean {
				/**
				 * featureId : 口味编号
				 * featureName : 口味名称
				 */

				private String featureId;
				private String featureName;

				public String getFeatureId() {
					return featureId;
				}

				public void setFeatureId(String featureId) {
					this.featureId = featureId;
				}

				public String getFeatureName() {
					return featureName;
				}

				public void setFeatureName(String featureName) {
					this.featureName = featureName;
				}
			}

			public static class CombosBean {
				/**
				 * skuId : 商品编号
				 * skuName : 商品名称
				 * salePrice : 商品单价
				 * qty : 商品数量
				 * saleAmount : 商品销售金额
				 * saleSubtotal : 单项销售金额合计
				 * memo : 备注
				 * mixs : [{"mixId":"加料编号","mixName":"加料名称","mixPrice":"加料单价","mixQty":"加料数量","mixAmount":"加料金额"}]
				 * features : [{"featureId":"口味编号","featureName":"口味名称"}]
				 */

				private String             skuId;
				private String             skuName;
				private String             salePrice;
				private String             qty;
				private String             saleAmount;
				private String             saleSubtotal;
				private String             memo;
				private List<MixsBean>     mixs;
				private List<FeaturesBean> features;

				public String getSkuId() {
					return skuId;
				}

				public void setSkuId(String skuId) {
					this.skuId = skuId;
				}

				public String getSkuName() {
					return skuName;
				}

				public void setSkuName(String skuName) {
					this.skuName = skuName;
				}

				public String getSalePrice() {
					return salePrice;
				}

				public void setSalePrice(String salePrice) {
					this.salePrice = salePrice;
				}

				public String getQty() {
					return qty;
				}

				public void setQty(String qty) {
					this.qty = qty;
				}

				public String getSaleAmount() {
					return saleAmount;
				}

				public void setSaleAmount(String saleAmount) {
					this.saleAmount = saleAmount;
				}

				public String getSaleSubtotal() {
					return saleSubtotal;
				}

				public void setSaleSubtotal(String saleSubtotal) {
					this.saleSubtotal = saleSubtotal;
				}

				public String getMemo() {
					return memo;
				}

				public void setMemo(String memo) {
					this.memo = memo;
				}

				public List<MixsBean> getMixs() {
					return mixs;
				}

				public void setMixs(List<MixsBean> mixs) {
					this.mixs = mixs;
				}

				public List<FeaturesBean> getFeatures() {
					return features;
				}

				public void setFeatures(List<FeaturesBean> features) {
					this.features = features;
				}


			}
		}

		public static class PromosBean {

			/**
			 * promoType : 优惠类型
			 * promoName : 优惠名称
			 * promoValue : 优惠值
			 * promoAmount : 优惠金额
			 * referenceNo : 优惠参考号
			 */

			private String promoType;
			private String promoName;
			private String promoValue;
			private String promoAmount;
			private String referenceNo;

			public String getPromoType() {
				return promoType;
			}

			public void setPromoType(String promoType) {
				this.promoType = promoType;
			}

			public String getPromoName() {
				return promoName;
			}

			public void setPromoName(String promoName) {
				this.promoName = promoName;
			}

			public String getPromoValue() {
				return promoValue;
			}

			public void setPromoValue(String promoValue) {
				this.promoValue = promoValue;
			}

			public String getPromoAmount() {
				return promoAmount;
			}

			public void setPromoAmount(String promoAmount) {
				this.promoAmount = promoAmount;
			}

			public String getReferenceNo() {
				return referenceNo;
			}

			public void setReferenceNo(String referenceNo) {
				this.referenceNo = referenceNo;
			}
		}

		public static class FeesBean {
			/**
			 * feeType : 费用类型
			 * feeName : 费用名称
			 * feePrice : 费用单价
			 * feeQty : 费用数量
			 * feeAmount : 费用金额
			 */

			private String feeType;
			private String feeName;
			private String feePrice;
			private String feeQty;
			private String feeAmount;

			public String getFeeType() {
				return feeType;
			}

			public void setFeeType(String feeType) {
				this.feeType = feeType;
			}

			public String getFeeName() {
				return feeName;
			}

			public void setFeeName(String feeName) {
				this.feeName = feeName;
			}

			public String getFeePrice() {
				return feePrice;
			}

			public void setFeePrice(String feePrice) {
				this.feePrice = feePrice;
			}

			public String getFeeQty() {
				return feeQty;
			}

			public void setFeeQty(String feeQty) {
				this.feeQty = feeQty;
			}

			public String getFeeAmount() {
				return feeAmount;
			}

			public void setFeeAmount(String feeAmount) {
				this.feeAmount = feeAmount;
			}
		}

		public static class PaymentItemBean {

			/**
			 * txOuid : 交易唯一标识
			 * txAmount : 交易金额
			 * txTime : 交易时间
			 * txNo : 交易单号
			 * txReference : 服务端交易参考号
			 * platformReference : 平台交易参考号
			 * payMode : 支付方式
			 * linkId : 对接方标识
			 * accountNo : 支付账号
			 * snapshotBalance : 支付后账户余额
			 * shopDiscountAmount : 商家折扣
			 */

			private String txOuid;
			private float  txAmount;
			private String txTime;
			private String txNo;
			private String txReference;
			private String platformReference;
			private String payMode;
			private String linkId;
			private String accountNo;
			private float  snapshotBalance;
			private float  shopDiscountAmount;
			private String bizNo;
			private String outTradeNo;

			public String getTxOuid() {
				return txOuid;
			}

			public void setTxOuid(String txOuid) {
				this.txOuid = txOuid;
			}

			public float getTxAmount() {
				return txAmount;
			}

			public void setTxAmount(float txAmount) {
				this.txAmount = txAmount;
			}

			public String getTxTime() {
				return txTime;
			}

			public void setTxTime(String txTime) {
				this.txTime = txTime;
			}

			public String getTxNo() {
				return txNo;
			}

			public void setTxNo(String txNo) {
				this.txNo = txNo;
			}

			public String getTxReference() {
				return txReference;
			}

			public void setTxReference(String txReference) {
				this.txReference = txReference;
			}

			public String getPlatformReference() {
				return platformReference;
			}

			public void setPlatformReference(String platformReference) {
				this.platformReference = platformReference;
			}

			public String getPayMode() {
				return payMode;
			}

			public void setPayMode(String payMode) {
				this.payMode = payMode;
			}

			public String getLinkId() {
				return linkId;
			}

			public void setLinkId(String linkId) {
				this.linkId = linkId;
			}

			public String getAccountNo() {
				return accountNo;
			}

			public void setAccountNo(String accountNo) {
				this.accountNo = accountNo;
			}

			public float getSnapshotBalance() {
				return snapshotBalance;
			}

			public void setSnapshotBalance(float snapshotBalance) {
				this.snapshotBalance = snapshotBalance;
			}

			public float getShopDiscountAmount() {
				return shopDiscountAmount;
			}

			public void setShopDiscountAmount(float shopDiscountAmount) {
				this.shopDiscountAmount = shopDiscountAmount;
			}

			public void setBizNo(String no) {
				this.bizNo = no;
			}

			public String getBizNo() {
				return bizNo;
			}

			public String getOutTradeNo() {
				return outTradeNo;
			}

			public void setOutTradeNo(String outTradeNo) {
				this.outTradeNo = outTradeNo;
			}
		}
	}
}
