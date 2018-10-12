package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/14 15:07
 * Desc：
 */
public class CXJNewOrderReq {


	/**
	 * people : 3
	 * omids :
	 * freememo :
	 * tids : 1
	 * ctid :
	 * newuid : 1
	 * saleuid :
	 * bid :
	 * ordertype : 1
	 * mid : -1
	 * identity : 1534231104566
	 * normalitems : [{"did":3817,"dish":"鸡中翅","duid":43,"amount":1,"price":8,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3815,"dish":"肥牛串","duid":23,"amount":1,"price":0.01,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[{"cid":"1","price":"0.00","bcal":1,"seat":1},{"cid":"11","price":"15.00","bcal":1,"seat":1},{"cid":"11","price":"15.00","bcal":1,"seat":1}],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3813,"dish":"鸡脆骨","duid":23,"amount":1,"price":0.01,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[{"cid":"8","price":"0.02","bcal":1,"seat":1},{"cid":"9","price":"0.00","bcal":1,"seat":1},{"cid":"7","price":"0.01","bcal":1,"seat":1}],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3816,"dish":"鸡腿","duid":43,"amount":1,"price":8,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3813,"dish":"鸡脆骨","duid":23,"amount":1,"price":0.01,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[{"cid":"8","price":"0.02","bcal":1,"seat":1},{"cid":"8","price":"0.02","bcal":1,"seat":1}],"bgift":0,"grid":"","print":1,"bwait":0}]
	 * setmeals : [{"did":3840,"dish":"何5选3套餐","duid":23,"amount":1,"price":300,"omids":"","freememo":"","cookinfo":[],"bgift":0,"items":[{"did":3643,"dish":"宫保鸡丁饭","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3632,"dish":"特色香辣火锅面","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3808,"dish":"芥末青瓜","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0}],"grid":"","print":1,"bwait":0},{"did":3840,"dish":"何5选3套餐","duid":23,"amount":1,"price":300,"omids":"","freememo":"","cookinfo":[],"bgift":0,"items":[{"did":3643,"dish":"宫保鸡丁饭","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3643,"dish":"宫保鸡丁饭","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3643,"dish":"宫保鸡丁饭","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0}],"grid":"","print":1,"bwait":0},{"did":3840,"dish":"何5选3套餐","duid":23,"amount":1,"price":300,"omids":"","freememo":"","cookinfo":[],"bgift":0,"items":[{"did":3807,"dish":"韭菜","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3632,"dish":"特色香辣火锅面","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3809,"dish":"茄子","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0}],"grid":"","print":1,"bwait":0}]
	 * freeitems : []
	 * accessid :
	 */

	private String people;
	private String                omids;
	private String                freememo;
	private int                   tids;
	private String                ctid;
	private String                newuid;
	private String                saleuid;
	private String                bid;
	private int                   ordertype;
	private int                   mid;
	private long                  identity;
	private String                accessid;
	private List<NormalitemsBean> normalitems;
	private List<SetmealsBean>    setmeals;
	private List<?>               freeitems;

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
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

	public String getNewuid() {
		return newuid;
	}

	public void setNewuid(String newuid) {
		this.newuid = newuid;
	}

	public String getSaleuid() {
		return saleuid;
	}

	public void setSaleuid(String saleuid) {
		this.saleuid = saleuid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public long getIdentity() {
		return identity;
	}

	public void setIdentity(long identity) {
		this.identity = identity;
	}

	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

	public List<NormalitemsBean> getNormalitems() {
		return normalitems;
	}

	public void setNormalitems(List<NormalitemsBean> normalitems) {
		this.normalitems = normalitems;
	}

	public List<SetmealsBean> getSetmeals() {
		return setmeals;
	}

	public void setSetmeals(List<SetmealsBean> setmeals) {
		this.setmeals = setmeals;
	}

	public List<?> getFreeitems() {
		return freeitems;
	}

	public void setFreeitems(List<?> freeitems) {
		this.freeitems = freeitems;
	}

	public static class NormalitemsBean {
		/**
		 * did : 3817
		 * dish : 鸡中翅
		 * duid : 43
		 * amount : 1
		 * price : 8
		 * assistduid :
		 * assistamount :
		 * omids :
		 * freememo :
		 * cookinfo : []
		 * bgift : 0
		 * grid :
		 * print : 1
		 * bwait : 0
		 */

		private int did;
		private String  dish;
		private int     duid;
		private int     amount;
		private float     price;
		private String  assistduid;
		private String  assistamount;
		private String  omids;
		private String  freememo;
		private int     bgift;
		private String  grid;
		private int     print;
		private int     bwait;
		private List<CookInfo> cookinfo;

		public int getDid() {
			return did;
		}

		public void setDid(int did) {
			this.did = did;
		}

		public String getDish() {
			return dish;
		}

		public void setDish(String dish) {
			this.dish = dish;
		}

		public int getDuid() {
			return duid;
		}

		public void setDuid(int duid) {
			this.duid = duid;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getAssistduid() {
			return assistduid;
		}

		public void setAssistduid(String assistduid) {
			this.assistduid = assistduid;
		}

		public String getAssistamount() {
			return assistamount;
		}

		public void setAssistamount(String assistamount) {
			this.assistamount = assistamount;
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

		public int getBgift() {
			return bgift;
		}

		public void setBgift(int bgift) {
			this.bgift = bgift;
		}

		public String getGrid() {
			return grid;
		}

		public void setGrid(String grid) {
			this.grid = grid;
		}

		public int getPrint() {
			return print;
		}

		public void setPrint(int print) {
			this.print = print;
		}

		public int getBwait() {
			return bwait;
		}

		public void setBwait(int bwait) {
			this.bwait = bwait;
		}

		public List<CookInfo> getCookinfo() {
			return cookinfo;
		}

		public void setCookinfo(List<CookInfo> cookinfo) {
			this.cookinfo = cookinfo;
		}
	}

	public static class SetmealsBean {
		/**
		 * did : 3840
		 * dish : 何5选3套餐
		 * duid : 23
		 * amount : 1
		 * price : 300
		 * omids :
		 * freememo :
		 * cookinfo : []
		 * bgift : 0
		 * items : [{"did":3643,"dish":"宫保鸡丁饭","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3632,"dish":"特色香辣火锅面","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0},{"did":3808,"dish":"芥末青瓜","duid":23,"amount":1,"price":0,"assistduid":"","assistamount":"","omids":"","freememo":"","cookinfo":[],"bgift":0,"grid":"","print":1,"bwait":0}]
		 * grid :
		 * print : 1
		 * bwait : 0
		 */

		private int did;
		private String          dish;
		private int             duid;
		private int             amount;
		private float             price;
		private String          omids;
		private String          freememo;
		private int             bgift;
		private String          grid;
		private int             print;
		private int             bwait;
		private List<CookInfo>         cookinfo;
		private List<ItemsBean> items;

		public int getDid() {
			return did;
		}

		public void setDid(int did) {
			this.did = did;
		}

		public String getDish() {
			return dish;
		}

		public void setDish(String dish) {
			this.dish = dish;
		}

		public int getDuid() {
			return duid;
		}

		public void setDuid(int duid) {
			this.duid = duid;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
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

		public int getBgift() {
			return bgift;
		}

		public void setBgift(int bgift) {
			this.bgift = bgift;
		}

		public String getGrid() {
			return grid;
		}

		public void setGrid(String grid) {
			this.grid = grid;
		}

		public int getPrint() {
			return print;
		}

		public void setPrint(int print) {
			this.print = print;
		}

		public int getBwait() {
			return bwait;
		}

		public void setBwait(int bwait) {
			this.bwait = bwait;
		}

		public List<CookInfo> getCookinfo() {
			return cookinfo;
		}

		public void setCookinfo(List<CookInfo> cookinfo) {
			this.cookinfo = cookinfo;
		}

		public List<ItemsBean> getItems() {
			return items;
		}

		public void setItems(List<ItemsBean> items) {
			this.items = items;
		}

		public static class ItemsBean {
			/**
			 * did : 3643
			 * dish : 宫保鸡丁饭
			 * duid : 23
			 * amount : 1
			 * price : 0
			 * assistduid :
			 * assistamount :
			 * omids :
			 * freememo :
			 * cookinfo : []
			 * bgift : 0
			 * grid :
			 * print : 1
			 * bwait : 0
			 */

			private int did;
			private String  dish;
			private int     duid;
			private int     amount;
			private float     price;
			private String  assistduid;
			private String  assistamount;
			private String  omids;
			private String  freememo;
			private int     bgift;
			private String  grid;
			private int     print;
			private int     bwait;
			private List<?> cookinfo;

			public int getDid() {
				return did;
			}

			public void setDid(int did) {
				this.did = did;
			}

			public String getDish() {
				return dish;
			}

			public void setDish(String dish) {
				this.dish = dish;
			}

			public int getDuid() {
				return duid;
			}

			public void setDuid(int duid) {
				this.duid = duid;
			}

			public int getAmount() {
				return amount;
			}

			public void setAmount(int amount) {
				this.amount = amount;
			}

			public float getPrice() {
				return price;
			}

			public void setPrice(float price) {
				this.price = price;
			}

			public String getAssistduid() {
				return assistduid;
			}

			public void setAssistduid(String assistduid) {
				this.assistduid = assistduid;
			}

			public String getAssistamount() {
				return assistamount;
			}

			public void setAssistamount(String assistamount) {
				this.assistamount = assistamount;
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

			public int getBgift() {
				return bgift;
			}

			public void setBgift(int bgift) {
				this.bgift = bgift;
			}

			public String getGrid() {
				return grid;
			}

			public void setGrid(String grid) {
				this.grid = grid;
			}

			public int getPrint() {
				return print;
			}

			public void setPrint(int print) {
				this.print = print;
			}

			public int getBwait() {
				return bwait;
			}

			public void setBwait(int bwait) {
				this.bwait = bwait;
			}

			public List<?> getCookinfo() {
				return cookinfo;
			}

			public void setCookinfo(List<?> cookinfo) {
				this.cookinfo = cookinfo;
			}
		}
	}

	public static class CookInfo{

		/**
		 * cid : 1
		 * price : 0.00
		 * bcal : 1
		 * seat : 1
		 */

		private String cid;
		private String price;
		private int    bcal;
		private int    seat;

		public String getCid() {
			return cid;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public int getBcal() {
			return bcal;
		}

		public void setBcal(int bcal) {
			this.bcal = bcal;
		}

		public int getSeat() {
			return seat;
		}

		public void setSeat(int seat) {
			this.seat = seat;
		}
	}
}
