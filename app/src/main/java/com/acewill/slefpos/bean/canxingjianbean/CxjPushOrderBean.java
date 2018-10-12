package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/11/10 14:49
 * Desc：
 */
public class CxjPushOrderBean {

	/**
	 * foodid : 3203
	 * foodname : 水煮肉片
	 * foodprice : 1.00
	 * foodoffset : 23
	 * issetmeal : 1
	 * isgiftmeal : false
	 * isgiftmealid : -1
	 * ismainfood : 0
	 * foodcook : []
	 * foodcookhtml : []
	 * foodcookprice : 0
	 * foodcookweight :
	 * foodnews :
	 * foodnewshtml :
	 * foodseat :
	 * foodnum : 1
	 * freeremark :
	 * setmeallevel : 0
	 * arrayindex : 0
	 * allarrayindex : 0
	 * foodsublist : []
	 * mainfoodid : -1
	 * minunit : 1.00
	 */

	private String       foodid;
	private String       foodname;
	private String       foodprice;
	private String       foodoffset;
	private String       issetmeal;
	private boolean      isgiftmeal;
	private int          isgiftmealid;
	private String       ismainfood;
	private int          foodcookprice;
	private String       foodcookweight;
	private String       foodnews;
	private String       foodnewshtml;
	private String       foodseat;
	private int          foodnum;
	private String       freeremark;
	private int          setmeallevel;
	private int          arrayindex;
	private int          allarrayindex;
	private int          mainfoodid;
	private String       minunit;
	private List<Object> foodcook;
	private Object[] foodcookhtml;
	private List<CxjPushOrderBean> foodsublist;

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getFoodprice() {
		return foodprice;
	}

	public void setFoodprice(String foodprice) {
		this.foodprice = foodprice;
	}

	public String getFoodoffset() {
		return foodoffset;
	}

	public void setFoodoffset(String foodoffset) {
		this.foodoffset = foodoffset;
	}

	public String getIssetmeal() {
		return issetmeal;
	}

	public void setIssetmeal(String issetmeal) {
		this.issetmeal = issetmeal;
	}

	public boolean isIsgiftmeal() {
		return isgiftmeal;
	}

	public void setIsgiftmeal(boolean isgiftmeal) {
		this.isgiftmeal = isgiftmeal;
	}

	public int getIsgiftmealid() {
		return isgiftmealid;
	}

	public void setIsgiftmealid(int isgiftmealid) {
		this.isgiftmealid = isgiftmealid;
	}

	public String getIsmainfood() {
		return ismainfood;
	}

	public void setIsmainfood(String ismainfood) {
		this.ismainfood = ismainfood;
	}

	public int getFoodcookprice() {
		return foodcookprice;
	}

	public void setFoodcookprice(int foodcookprice) {
		this.foodcookprice = foodcookprice;
	}

	public String getFoodcookweight() {
		return foodcookweight;
	}

	public void setFoodcookweight(String foodcookweight) {
		this.foodcookweight = foodcookweight;
	}

	public String getFoodnews() {
		return foodnews;
	}

	public void setFoodnews(String foodnews) {
		this.foodnews = foodnews;
	}

	public String getFoodnewshtml() {
		return foodnewshtml;
	}

	public void setFoodnewshtml(String foodnewshtml) {
		this.foodnewshtml = foodnewshtml;
	}

	public String getFoodseat() {
		return foodseat;
	}

	public void setFoodseat(String foodseat) {
		this.foodseat = foodseat;
	}

	public int getFoodnum() {
		return foodnum;
	}

	public void setFoodnum(int foodnum) {
		this.foodnum = foodnum;
	}

	public String getFreeremark() {
		return freeremark;
	}

	public void setFreeremark(String freeremark) {
		this.freeremark = freeremark;
	}

	public int getSetmeallevel() {
		return setmeallevel;
	}

	public void setSetmeallevel(int setmeallevel) {
		this.setmeallevel = setmeallevel;
	}

	public int getArrayindex() {
		return arrayindex;
	}

	public void setArrayindex(int arrayindex) {
		this.arrayindex = arrayindex;
	}

	public int getAllarrayindex() {
		return allarrayindex;
	}

	public void setAllarrayindex(int allarrayindex) {
		this.allarrayindex = allarrayindex;
	}

	public int getMainfoodid() {
		return mainfoodid;
	}

	public void setMainfoodid(int mainfoodid) {
		this.mainfoodid = mainfoodid;
	}

	public String getMinunit() {
		return minunit;
	}

	public void setMinunit(String minunit) {
		this.minunit = minunit;
	}

	public List<Object> getFoodcook() {
		return foodcook;
	}

	public void setFoodcook(List<Object> foodcook) {
		this.foodcook = foodcook;
	}

	public Object[] getFoodcookhtml() {
		return foodcookhtml;
	}

	public void setFoodcookhtml(Object[] foodcookhtml) {
		this.foodcookhtml = foodcookhtml;
	}

	public List<CxjPushOrderBean> getFoodsublist() {
		return foodsublist;
	}

	public void setFoodsublist(List<CxjPushOrderBean> foodsublist) {
		this.foodsublist = foodsublist;
	}
}
