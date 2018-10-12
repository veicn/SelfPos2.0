package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/15 11:28
 * Desc：
 */
public class CxjPrecheckBean {
	/**
	 * oid : 243
	 * prechecktype : 1
	 * rate : 1
	 * servicerate : 1
	 * extradiscount :
	 * maid :
	 * dsid :
	 * bservicediscount : 1
	 * bsave : 1
	 * bprint : 0
	 * marketgiftcouponcode :
	 * authcode :
	 * marketcouponcode :
	 * marketcardcodevalue : 1300575
	 * usenumberamount : []
	 * chainmaid :
	 * ordertype : 1
	 * precheckcardcode : 1300575
	 * precheckmbid :
	 * phone :
	 */

	private int oid;
	private int     prechecktype;
	private int     rate;
	private int     servicerate;
	private String  extradiscount;
	private String  maid;
	private String  dsid;
	private int     bservicediscount;
	private int     bsave;
	private int     bprint;
	private String  marketgiftcouponcode;
	private String  authcode;
	private String  marketcouponcode;
	private String  marketcardcodevalue;
	private String  chainmaid;
	private int     ordertype;
	private String  precheckcardcode;
	private String  precheckmbid;
	private String  phone;
	private List<?> usenumberamount;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getPrechecktype() {
		return prechecktype;
	}

	public void setPrechecktype(int prechecktype) {
		this.prechecktype = prechecktype;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getServicerate() {
		return servicerate;
	}

	public void setServicerate(int servicerate) {
		this.servicerate = servicerate;
	}

	public String getExtradiscount() {
		return extradiscount;
	}

	public void setExtradiscount(String extradiscount) {
		this.extradiscount = extradiscount;
	}

	public String getMaid() {
		return maid;
	}

	public void setMaid(String maid) {
		this.maid = maid;
	}

	public String getDsid() {
		return dsid;
	}

	public void setDsid(String dsid) {
		this.dsid = dsid;
	}

	public int getBservicediscount() {
		return bservicediscount;
	}

	public void setBservicediscount(int bservicediscount) {
		this.bservicediscount = bservicediscount;
	}

	public int getBsave() {
		return bsave;
	}

	public void setBsave(int bsave) {
		this.bsave = bsave;
	}

	public int getBprint() {
		return bprint;
	}

	public void setBprint(int bprint) {
		this.bprint = bprint;
	}

	public String getMarketgiftcouponcode() {
		return marketgiftcouponcode;
	}

	public void setMarketgiftcouponcode(String marketgiftcouponcode) {
		this.marketgiftcouponcode = marketgiftcouponcode;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getMarketcouponcode() {
		return marketcouponcode;
	}

	public void setMarketcouponcode(String marketcouponcode) {
		this.marketcouponcode = marketcouponcode;
	}

	public String getMarketcardcodevalue() {
		return marketcardcodevalue;
	}

	public void setMarketcardcodevalue(String marketcardcodevalue) {
		this.marketcardcodevalue = marketcardcodevalue;
	}

	public String getChainmaid() {
		return chainmaid;
	}

	public void setChainmaid(String chainmaid) {
		this.chainmaid = chainmaid;
	}

	public int getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}

	public String getPrecheckcardcode() {
		return precheckcardcode;
	}

	public void setPrecheckcardcode(String precheckcardcode) {
		this.precheckcardcode = precheckcardcode;
	}

	public String getPrecheckmbid() {
		return precheckmbid;
	}

	public void setPrecheckmbid(String precheckmbid) {
		this.precheckmbid = precheckmbid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<?> getUsenumberamount() {
		return usenumberamount;
	}

	public void setUsenumberamount(List<?> usenumberamount) {
		this.usenumberamount = usenumberamount;
	}
}
