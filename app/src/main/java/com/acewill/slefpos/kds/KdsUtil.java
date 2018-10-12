package com.acewill.slefpos.kds;

import android.text.TextUtils;

import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KdsEntity;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.kds.kdsbean.KdsDishItem;
import com.acewill.slefpos.kds.kdsbean.KdsOrderBean;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.jaydenxiao.common.baserx.RxManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/9 15:34
 * Desc：
 */
public class KdsUtil {
	private static KdsUtil   util;
	private static RxManager manager;

	/**
	 * 获取购物车对象
	 *
	 * @return
	 */
	public static KdsUtil getInstance() {
		if (util == null) {
			util = new KdsUtil();
			manager = new RxManager();
		}
		return util;
	}


	public KdsOrderBean getKdsOrderBaen(NewOrderRes orderRes) {
		if (SmarantDataProvider.getSelfposConfigurationdata().getContent().isInformKDS()) {
			KdsData info = SmarantDataProvider.getKdsInfo();
			if (info != null) {
				List<KdsEntity> kdsEntities = info.getContent();
				if (kdsEntities != null && kdsEntities.size() > 0) {
					for (int i = 0; i < kdsEntities.size(); i++) {
						if (kdsEntities.get(i).getId() == TerminalConfigure.getKdsid()) {
							if (!TextUtils.isEmpty(kdsEntities
									.get(i).getIp())) {
								ApiConstants.setKds("http://" + kdsEntities
										.get(i).getIp() + ":8080");
								String fetchID = "";
								if (!TextUtils.isEmpty(Cart.getInstance().getCallNumber())) {
									fetchID = Cart.getInstance().getCallNumber();
								} else {
									fetchID = orderRes.getContent().getCallNumber();
								}
								return getKdsOrderBean(orderRes.getContent().getId(), fetchID);
							}
						}
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		return null;
	}

	public boolean needNotifyKds() {
		if (SmarantDataProvider.getSelfposConfigurationdata().getContent().isInformKDS()) {
			KdsData info = SmarantDataProvider.getKdsInfo();
			if (info != null) {
				List<KdsEntity> kdsEntities = info.getContent();
				if (kdsEntities != null && kdsEntities.size() > 0) {
					for (int i = 0; i < kdsEntities.size(); i++) {
						if (kdsEntities.get(i).getId() == TerminalConfigure.getKdsid()) {
							if (!TextUtils.isEmpty(kdsEntities
									.get(i).getIp())) {
								ApiConstants.setKds("http://" + kdsEntities
										.get(i).getIp() + ":8080");
								return true;
							}
						}
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}


	public KdsOrderBean getKdsOrderBean(String oid, String fetchID) {
		KdsOrderBean kdsOrderBean = new KdsOrderBean();
		kdsOrderBean.oid = oid;
		kdsOrderBean.fetchID = fetchID;
		kdsOrderBean.createTime = Long.parseLong(Order.getInstance().getPayTime());
		kdsOrderBean.type = Order.getInstance().getTakeOut();
		kdsOrderBean.total = Price.getInstance().getTotal() + "";
		kdsOrderBean.price = Price.getInstance().getTotal() + "";
		kdsOrderBean.paystatus = 1;
		kdsOrderBean.tablename = fetchID;
		kdsOrderBean.openID = "";
		kdsOrderBean.pos = "点餐机";
		kdsOrderBean.dishitems = new ArrayList<KdsDishItem>();
		for (CartDish item : Cart.getInstance().getCartDishes()) {
			if (item.getSubItemList() != null && item.getSubItemList().size() > 0) {
				for (UIPackageOptionItem pack : item.getSubItemList()) {
					KdsDishItem dishItem = new KdsDishItem();
					dishItem.did = pack.getDishID();
					dishItem.name = pack.getDishName();
					if (pack.getQuantity() > 0) {
						dishItem.count = pack.getQuantity() * item.getQuantity();
					} else {
						dishItem.count = item.getQuantity();
					}
					dishItem.dishKind = pack.getDishName();
					//					dishItem.mOptionBean = pack.optionList;
					StringBuilder sb = new StringBuilder();
					if (pack.getOptionList() != null) {
						for (int i = 0; i < pack.getOptionList().size(); i++) {
							if (i == pack.getOptionList().size() - 1) {//这是最后一个
								sb.append(pack.getOptionList().get(i).getOptionName());
							} else {
								sb.append(pack.getOptionList().get(i).getOptionName() + " , ");
							}
						}
					}
					dishItem.price = "0";
					dishItem.seq = "";
					dishItem.cook = sb.toString();
					kdsOrderBean.dishitems.add(dishItem);
				}
			} else {
				KdsDishItem dishItem = new KdsDishItem();
				dishItem.did = item.getDishID();
				dishItem.name = item.getDishName();
				dishItem.count = item.getQuantity();
				dishItem.dishKind = item.getDishKindStr();
				dishItem.cook = getOptionStr(item);
				dishItem.price = "0";
				dishItem.seq = "";
				kdsOrderBean.dishitems.add(dishItem);
			}
		}
		return kdsOrderBean;
	}

	public String getOptionStr(CartDish item) {
		if (item.getOptionList() != null && item.getOptionList().size() > 0) {
			int           j  = 0;
			StringBuilder sb = new StringBuilder();
			for (UITasteOption o : item.getOptionList()) {
				if (j > 0) {
					sb.append("/");
				}
				sb.append(o.getOptionName());
				j++;
			}
			return sb.toString();
		}
		return "";
	}
}
