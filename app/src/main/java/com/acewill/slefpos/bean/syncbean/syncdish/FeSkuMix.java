package com.acewill.slefpos.bean.syncbean.syncdish;

/**
 * Author：Anch
 * Date：2018/4/25 17:10
 * Desc：
 */
public class FeSkuMix {

	/**
	 * ouid : w1eZqvnKTIeKWtM7lpRg8g
	 * verUuid : XSG1V41R0L2gjD2vjOInBQ
	 * statusFlag : Y
	 * statusTime : 2017-06-09T15:35:15.380+08:00
	 * crtTime : 2017-06-09T15:35:15.380+08:00
	 * updTime : 2017-11-14T12:41:07.987+08:00
	 * storeshopOuid : uCWJGGLkTLCsX6hxNebMWg
	 * name : 椰果5
	 * price : 4.5
	 * seqOrder : 0
	 * skuMixId : w1eZqvnKTIeKWtM
	 * skuMixKindOuid : TYdSbjlgRd2I2HmqLBqPFA
	 */

	private String ouid;
	private String verUuid;
	private String statusFlag;
	private String statusTime;
	private String crtTime;
	private String updTime;
	private String storeshopOuid;
	private String name;
	private double price;
	private int    seqOrder;
	private String skuMixId;
	private String skuMixKindOuid;

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}

	public String getVerUuid() {
		return verUuid;
	}

	public void setVerUuid(String verUuid) {
		this.verUuid = verUuid;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getStoreshopOuid() {
		return storeshopOuid;
	}

	public void setStoreshopOuid(String storeshopOuid) {
		this.storeshopOuid = storeshopOuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeqOrder() {
		return seqOrder;
	}

	public void setSeqOrder(int seqOrder) {
		this.seqOrder = seqOrder;
	}

	public String getSkuMixId() {
		return skuMixId;
	}

	public void setSkuMixId(String skuMixId) {
		this.skuMixId = skuMixId;
	}

	/**
	 * 用来区别同一组的两个不同分类
	 *
	 * @return
	 */
	public String getSkuMixKindOuid() {
		return skuMixKindOuid;
	}

	public void setSkuMixKindOuid(String skuMixKindOuid) {
		this.skuMixKindOuid = skuMixKindOuid;
	}
}
