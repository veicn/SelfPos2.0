package com.acewill.slefpos.configure;

/**
 * Author：Anch
 * Date：2018/1/25 9:20
 * Desc：
 */
public class StoreConfigure {


	/**
	 * sname : 喜多多一号店
	 * phone : 4458288
	 * mobile : 13787719750
	 * address : 深证市南山区平山村喜多多一号店
	 * brandName : 喜多多
	 */
	private static String  latitute;//纬度
	private static String  longitute;//经度
	private static String  sname;//门店名称
	private static String  phone;//门店电话
	private static String  mobile;//收银员电话
	private static String  address;//门店地址
	private static String  brandName;//品牌名称
	private static long    storeEndTime;//品牌名称
	private static boolean useMeiTuan;//是否使用美团支付
	private static boolean useWechat;//是否使用美团支付
	private static boolean useAli;//是否使用美团支付

	public static void init(String longitute, String latitute, String sname, String phone, String mobile, String address, String brandName, long storeEndTime) {
		setLatitute(latitute);
		setLongitute(longitute);
		setSname(sname);
		setPhone(phone);
		setMobile(mobile);
		setAddress(address);
		setBrandName(brandName);
		setStoreEndTime(storeEndTime);
	}

	public static long getStoreEndTime() {
		return storeEndTime;
	}

	public static void setStoreEndTime(long storeEndTime) {
		StoreConfigure.storeEndTime = storeEndTime;
	}


	public static String getLongitute() {
		return longitute;
	}

	public static void setLongitute(String longitute) {
		StoreConfigure.longitute = longitute;
	}

	public static String getSname() {
		return sname;
	}

	public static void setSname(String sname) {
		StoreConfigure.sname = sname;
	}

	public static String getPhone() {
		return phone;
	}

	public static void setPhone(String phone) {
		StoreConfigure.phone = phone;
	}

	public static String getMobile() {
		return mobile;
	}

	public static void setMobile(String mobile) {
		StoreConfigure.mobile = mobile;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		StoreConfigure.address = address;
	}

	public static String getBrandName() {
		return brandName;
	}

	public static void setBrandName(String brandName) {
		StoreConfigure.brandName = brandName;
	}

	public static String getLatitute() {
		return latitute;
	}

	public static void setLatitute(String latitute) {
		StoreConfigure.latitute = latitute;
	}


	public static boolean isUseMeiTuan() {
		return useMeiTuan;
	}

	public static void setUseMeiTuan(boolean useMeiTuan) {
		StoreConfigure.useMeiTuan = useMeiTuan;
	}

	private static String jyjAddress;

	public static void setJyjAddress(String jyjAddress1) {
		jyjAddress = jyjAddress1;
	}

	public static String getJyjAddress() {
		return jyjAddress;
	}

	public static boolean isUseWechat() {
		return useWechat;
	}

	public static void setUseWechat(boolean useWechat) {
		StoreConfigure.useWechat = useWechat;
	}

	public static boolean isUseAli() {
		return useAli;
	}

	public static void setUseAli(boolean useAli) {
		StoreConfigure.useAli = useAli;
	}
}
