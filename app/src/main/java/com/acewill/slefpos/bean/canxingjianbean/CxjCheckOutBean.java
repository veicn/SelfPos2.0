package com.acewill.slefpos.bean.canxingjianbean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2017/11/10 14:42
 * Desc：
 */
public class CxjCheckOutBean implements Serializable {

	/**
	 * accessid :
	 * orderidentity : 1510300764
	 * mid : -1
	 * username : 1
	 * pwd : 1
	 * people : 1
	 * tid : 1
	 * tids : 1
	 * ctid :
	 * saleuid : 1
	 * oid : -1
	 * alldiscount : 1
	 * bid :
	 * omids :
	 * freememo :
	 * normalitems : []
	 * setmeals : [[0,"3216","23",1,"","",[],0,[[0,"3214","23",1,"0.00","","","","",[],0,"",1],[0,"3204","23",1,"0.00","","","","",[],0,"",1]],"","1"]]
	 * freeitems : []
	 * payinfo : [[0.01,"-1","","","","","","","",""]]
	 * recieve : 0.01
	 * change : 0.00
	 * advance : 0
	 * ptid :
	 * accountpid :
	 * cardmbid :
	 * cardcode :
	 * password :
	 * bsnack : 1
	 * ordertype : 1
	 * phone :
	 * mobile :
	 * address :
	 * name :
	 * diningtime :
	 * alipaycode :
	 * sendm :
	 * wid :
	 * ispaid : 0
	 */

	private String                               accessid;
	private long                                 orderidentity;
	private int                                  mid;
	private String                               username;
	private String                               pwd;
	private String                               people;
	private int                                  tid;
	private int                                  tids;
	private String                               ctid;
	private int                                  saleuid;
	private int                                  oid;
	private int                                  alldiscount;
	private String                               bid;
	private String                               omids;
	private String                               freememo;
	private float                                recieve;
	private float                                change;
	private int                                  advance;
	private int                                  ptid;
	private String                               accountpid;
	private String                               cardmbid;
	private String                               cardcode;
	private String                               password;
	private int                                  bsnack;
	private int                                  ordertype;
	private String                               phone;
	private String                               mobile;
	private String                               address;
	private String                               name;
	private String                               diningtime;
	private String                               alipaycode;
	private String                               sendm;
	private String                               wid;
	private int                                  ispaid;
	private List<CXJNewOrderReq.NormalitemsBean> normalitems;
	private List<CXJNewOrderReq.SetmealsBean>    setmeals;
	private Object[]                             freeitems;
	private List<CXJPayInfo>                     payinfo;
	private int                                  cashDeposit;

	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

	public long getOrderidentity() {
		return orderidentity;
	}

	public void setOrderidentity(long orderidentity) {
		this.orderidentity = orderidentity;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getTids() {
		return tids;
	}

	public void setTids(int tids) {
		this.tids = tids;
	}

	public String getCtid() {
		return ctid;
	}

	public void setCtid(String ctid) {
		this.ctid = ctid;
	}

	public int getSaleuid() {
		return saleuid;
	}

	public void setSaleuid(int saleuid) {
		this.saleuid = saleuid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getAlldiscount() {
		return alldiscount;
	}

	public void setAlldiscount(int alldiscount) {
		this.alldiscount = alldiscount;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getOmids() {
		return omids;
	}

	public void setOmids(String omids) {
		this.omids = omids;
	}

	public String getFreememo() {
		return freememo;
	}

	public void setFreememo(String freememo) {
		this.freememo = freememo;
	}

	public float getRecieve() {
		return recieve;
	}

	public void setRecieve(float recieve) {
		this.recieve = recieve;
	}

	public float getChange() {
		return change;
	}

	public void setChange(float change) {
		this.change = change;
	}

	public int getAdvance() {
		return advance;
	}

	public void setAdvance(int advance) {
		this.advance = advance;
	}

	public int getPtid() {
		return ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}

	public String getAccountpid() {
		return accountpid;
	}

	public void setAccountpid(String accountpid) {
		this.accountpid = accountpid;
	}

	public String getCardmbid() {
		return cardmbid;
	}

	public void setCardmbid(String cardmbid) {
		this.cardmbid = cardmbid;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBsnack() {
		return bsnack;
	}

	public void setBsnack(int bsnack) {
		this.bsnack = bsnack;
	}

	public int getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiningtime() {
		return diningtime;
	}

	public void setDiningtime(String diningtime) {
		this.diningtime = diningtime;
	}

	public String getAlipaycode() {
		return alipaycode;
	}

	public void setAlipaycode(String alipaycode) {
		this.alipaycode = alipaycode;
	}

	public String getSendm() {
		return sendm;
	}

	public void setSendm(String sendm) {
		this.sendm = sendm;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public int getIspaid() {
		return ispaid;
	}

	public void setIspaid(int ispaid) {
		this.ispaid = ispaid;
	}

	public List<CXJNewOrderReq.NormalitemsBean> getNormalitems() {
		return normalitems;
	}

	public void setNormalitems(List<CXJNewOrderReq.NormalitemsBean> normalitems) {
		this.normalitems = normalitems;
	}

	public List<CXJNewOrderReq.SetmealsBean> getSetmeals() {
		return setmeals;
	}

	public void setSetmeals(List<CXJNewOrderReq.SetmealsBean> setmeals) {
		this.setmeals = setmeals;
	}

	public Object[] getFreeitems() {
		return freeitems;
	}

	public void setFreeitems(Object[] freeitems) {
		this.freeitems = freeitems;
	}

	public int getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(int cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public static class CouponInfo {

		/**
		 * id : 1601788035776605292
		 * title : 30元代金券
		 * deno : 30
		 * type : 1
		 * typeName : 代金券
		 * starttime : 2018-05-29
		 * endtime : 2019-05-28
		 * enableAmount : 0
		 * maxUse : 1
		 * mixUse : true
		 * templateId : 8917963
		 * count : 1
		 * checked : true
		 * did :
		 * disable : false
		 */

		private String  id;
		private String  title;
		private float   deno;
		private int     type;
		private String  typeName;
		private String  starttime;
		private String  endtime;
		private float   enableAmount;
		private int     maxUse;
		private boolean mixUse;
		private int     templateId;
		private int     count;
		private boolean checked;
		private String  did;
		private boolean disable;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public float getDeno() {
			return deno;
		}

		public void setDeno(float deno) {
			this.deno = deno;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public String getEndtime() {
			return endtime;
		}

		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}

		public float getEnableAmount() {
			return enableAmount;
		}

		public void setEnableAmount(float enableAmount) {
			this.enableAmount = enableAmount;
		}

		public int getMaxUse() {
			return maxUse;
		}

		public void setMaxUse(int maxUse) {
			this.maxUse = maxUse;
		}

		public boolean isMixUse() {
			return mixUse;
		}

		public void setMixUse(boolean mixUse) {
			this.mixUse = mixUse;
		}

		public int getTemplateId() {
			return templateId;
		}

		public void setTemplateId(int templateId) {
			this.templateId = templateId;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public String getDid() {
			return did;
		}

		public void setDid(String did) {
			this.did = did;
		}

		public boolean isDisable() {
			return disable;
		}

		public void setDisable(boolean disable) {
			this.disable = disable;
		}


	}

	public static class CXJPayInfo {

		/**
		 * paytype : 现金
		 * kind : 1
		 * status : 1
		 * give : 0
		 * pay : 8
		 * cost : 8
		 * ptid : -1
		 * cardcode :
		 * coupons : []
		 * checked : true
		 * type :
		 * activityid :
		 * activityname :
		 */

		private String           paytype;
		private String           kind;
		private String           status;
		private float            give;
		private float            pay;
		private float            cost;
		private int              ptid;
		private String           cardcode;
		private boolean          checked;
		private String           type;
		private String           activityid;
		private String           activityname;
		private List<CouponInfo> coupons;
		private String           cno;

		public String getPaytype() {
			return paytype;
		}

		public void setPaytype(String paytype) {
			this.paytype = paytype;
		}

		public String getKind() {
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public float getGive() {
			return give;
		}

		public void setGive(float give) {
			this.give = give;
		}

		public float getPay() {
			return pay;
		}

		public void setPay(float pay) {
			this.pay = pay;
		}

		public float getCost() {
			return cost;
		}

		public void setCost(float cost) {
			this.cost = cost;
		}

		public int getPtid() {
			return ptid;
		}

		public void setPtid(int ptid) {
			this.ptid = ptid;
		}

		public String getCardcode() {
			return cardcode;
		}

		public void setCardcode(String cardcode) {
			this.cardcode = cardcode;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getActivityid() {
			return activityid;
		}

		public void setActivityid(String activityid) {
			this.activityid = activityid;
		}

		public String getActivityname() {
			return activityname;
		}

		public void setActivityname(String activityname) {
			this.activityname = activityname;
		}

		public List<CouponInfo> getCoupons() {
			return coupons;
		}

		public void setCoupons(List<CouponInfo> coupons) {
			this.coupons = coupons;
		}

		public String getCno() {
			return cno;
		}

		public void setCno(String cno) {
			this.cno = cno;
		}
	}

	public List<CXJPayInfo> getPayinfo() {
		return payinfo;
	}

	public void setPayinfo(List<CXJPayInfo> payinfo) {
		this.payinfo = payinfo;
	}
}
