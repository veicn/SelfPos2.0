package com.acewill.slefpos.orderui.main.uidataprovider;

import android.util.Log;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.syncbean.FeCompanyConfigRes;
import com.acewill.slefpos.bean.syncbean.FeReceiveTypeRes;
import com.acewill.slefpos.bean.syncbean.FeStoreshopBean;
import com.acewill.slefpos.bean.syncbean.FeStoreshopRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeCompanyConfig;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUser;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUserRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeReceiveTypeBean;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSku;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuCate;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuCateRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuCombo;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuComboKind;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuComboKindRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuComboRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuComposite;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuCompositeRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuFeature;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuFeatureKind;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuFeatureKindRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuFeatureRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuMix;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuMixKind;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuMixKindRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuMixRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuSpec;
import com.acewill.slefpos.bean.syncbean.syncdish.FeSkuSpecRes;
import com.acewill.slefpos.bean.syncbean.syncdish.FeUom;
import com.acewill.slefpos.bean.syncbean.syncdish.FeUomRes;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.utils.httputils.GsonUtils;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.compressorutils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/25 15:12
 * Desc：SKU規格.
 */
public class SyncDataProvider {


	public static List<FeSkuCate> getFeSkuCateList() {
		return feSkuCateList;
	}

	public static void setFeSkuCateList(List<FeSkuCate> feSkuCateList) {
		SyncDataProvider.feSkuCateList = feSkuCateList;
	}

	public static List<FeSkuCate> feSkuCateList;

	public static List<FeSkuFeatureKind> getFeSkuFeatureKindList() {
		return FeSkuFeatureKindList;
	}

	public static void setFeSkuFeatureKindList(List<FeSkuFeatureKind> feSkuFeatureKindList) {
		FeSkuFeatureKindList = feSkuFeatureKindList;
	}

	public static List<FeSkuFeatureKind> FeSkuFeatureKindList;

	public static List<FeUom> getFeUomList() {
		return FeUomList;
	}

	public static void setFeUomList(List<FeUom> feUomList) {
		FeUomList = feUomList;
	}

	public static List<FeUom> FeUomList;

	public static List<FeSkuFeature> getFeSkuFeatureList() {
		return feSkuFeatureList;
	}

	public static void setFeSkuFeatureList(List<FeSkuFeature> feSkuFeatureList) {
		SyncDataProvider.feSkuFeatureList = feSkuFeatureList;
	}

	public static List<FeSkuFeature> feSkuFeatureList;

	public static List<FeSkuMixKind> getFeSkuMixKindList() {
		return feSkuMixKindList;
	}

	public static void setFeSkuMixKindList(List<FeSkuMixKind> feSkuMixKindList) {
		SyncDataProvider.feSkuMixKindList = feSkuMixKindList;
	}

	public static List<FeSkuMixKind> feSkuMixKindList;

	public static List<FeSkuCombo> getFeSkuComboList() {
		return feSkuComboList;
	}

	public static void setFeSkuComboList(List<FeSkuCombo> feSkuComboList) {
		SyncDataProvider.feSkuComboList = feSkuComboList;
	}

	public static List<FeSkuCombo> feSkuComboList;

	public static List<FeSkuMix> getFeSkuMixList() {
		return feSkuMixList;
	}

	public static void setFeSkuMixList(List<FeSkuMix> feSkuMixList) {
		SyncDataProvider.feSkuMixList = feSkuMixList;
	}

	public static List<FeSkuMix> feSkuMixList;

	public static List<FeSkuComposite> getFeSkuCompositeList() {
		return feSkuCompositeList;
	}

	public static void setFeSkuCompositeList(List<FeSkuComposite> feSkuCompositeList) {
		SyncDataProvider.feSkuCompositeList = feSkuCompositeList;
	}

	public static List<FeSkuComposite> feSkuCompositeList;

	public static List<FeSkuComboKind> getFeSkuComboKindList() {
		return feSkuComboKindList;
	}

	public static void setFeSkuComboKindList(List<FeSkuComboKind> feSkuComboKindList) {
		SyncDataProvider.feSkuComboKindList = feSkuComboKindList;
	}

	public static List<FeSkuComboKind> feSkuComboKindList;

	public static List<FeSkuSpec> getFeSkuSpecList() {
		return feSkuSpecList;
	}

	public static void setFeSkuSpecList(List<FeSkuSpec> feSkuSpecList) {
		SyncDataProvider.feSkuSpecList = feSkuSpecList;
	}

	public static List<FeSkuSpec> feSkuSpecList;

	public static List<FeReceiveTypeBean> getFeReceiveTypeList() {
		return feReceiveTypeList;
	}

	/**
	 * 判断某个支付方式是否可用
	 *
	 * @param payMode
	 * @return
	 */
	public static boolean canUse(String payMode) {
		if (feReceiveTypeList == null)
			return false;
		for (FeReceiveTypeBean bean : feReceiveTypeList) {
			if (payMode.equals(bean.getPayMode())) {
				return true;
			}
		}
		return false;
	}


	public static void setFeReceiveTypeList(List<FeReceiveTypeBean> feReceiveTypeList) {
		SyncDataProvider.feReceiveTypeList = feReceiveTypeList;
	}

	public static List<FeReceiveTypeBean> feReceiveTypeList;

	public static List<FeSku> getFeSkuList() {
		return feSkuList;
	}

	public static void setFeSkuList(List<FeSku> feSkuList) {
		SyncDataProvider.feSkuList = feSkuList;
	}

	public static List<FeSku> feSkuList;

	public static List<FeStoreshopBean> getFeStoreshopBeanList() {
		return feStoreshopBeanList;
	}

	public static void setFeStoreshopBeanList(List<FeStoreshopBean> feStoreshopBeanList) {
		SyncDataProvider.feStoreshopBeanList = feStoreshopBeanList;
	}

	public static List<FeStoreshopBean> feStoreshopBeanList;


	public static void initData(String filename) {
		File file = new File(FileUtil
				.getSyncFoldPath() + File.separator + "jsondata" + File.separator + filename);
		if (!file.exists()) {
			//			ToastUitl.showLong(mContext, "菜品信息有误，请检查是否已经初始化门店信息!");
			return;
		}
		StringBuilder builder = new StringBuilder();
		try {
			FileInputStream stream = new FileInputStream(file);

			BufferedReader bf = new BufferedReader(new InputStreamReader(stream));
			String         line;
			while ((line = bf.readLine()) != null) {
				builder.append(line);
			}
			/* if (filename.equals("FeAccountTitleItem.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePosRoleUser.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePosPromoFunc.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePromoActiv.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeCompany.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePostalCode.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeSecondScreen.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeCompanyCate.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePublishDataSummary.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeSkuCateMajor.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeStoreshopGrpAuth.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePosRoleFunc.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeCurrencyExchangeRate.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeStoreshopTax.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePrintContent.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeStoreshopGrp.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeSecondScreenItem.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePosRole.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePos.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else */
			if (filename.equals("FeSkuCate.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeSku.json")) {
				FeSkuRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuRes.class);
				SyncDataProvider.setFeSkuList(bean.getFeSku());
			} else if (filename.equals("FeSkuSpec.json")) {
				FeSkuSpecRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuSpecRes.class);
				SyncDataProvider.setFeSkuSpecList(bean.getFeSkuSpec());
			} else if (filename.equals("FeSkuComposite.json")) {
				FeSkuCompositeRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCompositeRes.class);
				SyncDataProvider.setFeSkuCompositeList(bean.getFeSkuComposite());
			} else if (filename.equals("FeSkuComboKind.json")) {
				FeSkuComboKindRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuComboKindRes.class);
				SyncDataProvider.setFeSkuComboKindList(bean.getFeSkuComboKind());
			} else if (filename.equals("FeSkuCombo.json")) {
				FeSkuComboRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuComboRes.class);
				SyncDataProvider.setFeSkuComboList(bean.getFeSkuCombo());
			} else if (filename.equals("FeSkuMix.json")) {
				FeSkuMixRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuMixRes.class);
				SyncDataProvider.setFeSkuMixList(bean.getFeSkuMix());
			} else if (filename.equals("FeSkuMixKind.json")) {
				FeSkuMixKindRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuMixKindRes.class);
				SyncDataProvider.setFeSkuMixKindList(bean.getFeSkuMixKind());
			} else if (filename.equals("FeSkuFeature.json")) {
				FeSkuFeatureRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuFeatureRes.class);
				SyncDataProvider.setFeSkuFeatureList(bean.getFeSkuFeature());
			} else if (filename.equals("FeSkuFeatureKind.json")) {
				FeSkuFeatureKindRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuFeatureKindRes.class);
				SyncDataProvider.setFeSkuFeatureKindList(bean.getFeSkuFeatureKind());
			} else if (filename.equals("FeUom.json")) {
				FeUomRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeUomRes.class);
				SyncDataProvider.setFeUomList(bean.getFeUom());
			} else if (filename.equals("FePosUser.json")) {
				//放在了另外一个地方解析
				//				FePosUserRes bean = GsonUtils
				//						.getSingleBean(builder.toString(), FePosUserRes.class);
				//				SyncDataProvider.setmFePosUserList(bean.getFePosUser());
			} else if (filename.equals("FeCompanyConfig.json")) {
				FeCompanyConfigRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeCompanyConfigRes.class);
				SyncDataProvider.setCompanyConfigList(bean.getFeCompanyConfig());
			} else if (filename.equals("FeReceiveType.json")) {
				FeReceiveTypeRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeReceiveTypeRes.class);
				SyncDataProvider.setFeReceiveTypeList(bean.getFeReceiveType());
			} else if (filename.equals("FeStoreshop.json")) {
				FeStoreshopRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeStoreshopRes.class);
				if (bean != null) {
					for (FeStoreshopBean bean1 : bean.getFeStoreshop()) {
						if (SPUtils.getSharedStringData(BaseApplication
								.getContext(), "shopId").equals(bean1.getOuid())) {
							SPUtils.setSharedStringData(BaseApplication
									.getContext(), "shopNo", bean1.getStoreshopId());
						}
					}
				}

				SyncDataProvider.setFeStoreshopBeanList(bean.getFeStoreshop());
			}
		/*	else if (filename.equals("FeSecondScreenDocItem.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			}else if (filename.equals("FeWorkShift.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeAccountTitle.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FeSkuMore.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePromoActivConfig.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			}   else if (filename.equals("FeRechargeRule.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			}    else if (filename.equals("FeTax.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			}  else if (filename.equals("FePrintStyleTemplate.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePartnerType.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePosFunc.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePromoActivSkuGroup.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			} else if (filename.equals("FePromoActivSku.json")) {
				FeSkuCateRes bean = GsonUtils
						.getSingleBean(builder.toString(), FeSkuCateRes.class);
				SyncDataProvider.setFeSkuCateList(bean.getFeSkuCate());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<FeCompanyConfig> getCompanyConfigList() {
		return mCompanyConfigList;
	}

	public static void setCompanyConfigList(List<FeCompanyConfig> companyConfigList) {
		mCompanyConfigList = companyConfigList;
	}

	private static List<FeCompanyConfig> mCompanyConfigList;

	public static List<FePosUser> getmFePosUserList() {
		return mFePosUserList;
	}

	public static void setmFePosUserList(List<FePosUser> mFePosUserList) {
		SyncDataProvider.mFePosUserList = mFePosUserList;
	}

	private static List<FePosUser> mFePosUserList;


	public static List<UIDish> getSyncData() {
		if (feSkuList == null)
			return null;


		Collections.sort(feSkuList, new Comparator<FeSku>() {

			@Override
			public int compare(FeSku o1, FeSku o2) {
				int i = o1.getSeqOrder() - o2.getSeqOrder();
				return i;
			}
		});


		List<UIDish> uiDishList = new ArrayList<>();
		//		for (FeSku sku : feSkuList) {
		/**
		 * 先将菜品按名称分开（因为他们的规格是通过菜品名称来分类的）
		 */
		//		}
		for (FeSku sku : feSkuList) {
			if ("N".equals(sku.getSeparateFlag())) {
				continue;
			}
			UIDish uiDish = new UIDish();
			uiDish.setDishName(sku.getName());
			uiDish.setPrice(String.valueOf(sku.getSalePrice()));
			uiDish.setDishKind(sku.getSkuCateOuid());
			uiDish.setImageName(sku.getMediaUrl());
			uiDish.setPackageItems(getUIDishPackageItems(sku));
			uiDish.setSkuSpecOuid(sku.getSkuSpecOuid());
			uiDish.setDishKindStr(sku.getSkuCateOuid());//这个要通过skucateOuid去找
			uiDish.setDishID(sku.getOuid());//这个尴尬了，他们是string的类型，我们是int类型，如何统一？？
			uiDish.setSkuId(sku.getSkuId());//这个尴尬了，他们是string的类型，我们是int类型，如何统一？？
			uiDish.setDishUnit(getDishUnitByUomId(sku.getStockUomOuid()));//这个要通过stockUomOuid去找
			uiDish.setIsPackage(0);
			uiDish.setMemberPrice(String.valueOf(sku.getSalePrice()));
			uiDish.setDishCount(10000);
			uiDish.setImageName(sku.getMediaUrl());
			if (uiDish.getPackageItems() == null || uiDish.getPackageItems().size() == 0)
				addSpec(uiDish, uiDishList);//如果规格不为空，那么uidish就已经创建了List<UIOptionCategory>
			if (uiDish.getOptionCategoryList() == null)
				uiDish.setOptionCategoryList(getUIDishOptionCategory(new ArrayList<UIOptionCategory>(), sku
						.getOuid()));
			else {
				uiDish.setOptionCategoryList(getUIDishOptionCategory(uiDish
						.getOptionCategoryList(), sku.getOuid()));
			}
			uiDish.setVisible(true);
			for (UIDish dish : uiDishList) {
				if (dish.getDishName().equals(uiDish.getDishName())) {
					uiDish.setVisible(false);
				}
			}
			uiDishList.add(uiDish);
		}
		return uiDishList;
	}

	/**
	 * 规格
	 *
	 * @param uiDish
	 * @param list
	 */
	private static void addSpec(UIDish uiDish, List<UIDish> list) {
		if (list.size() == 0) {
			FeSkuSpec spec1 = getDishFeSkuSpec(uiDish
					.getSkuSpecOuid());
			if (spec1 != null) {
				UITasteOption option2 = new UITasteOption("", spec1.getSeqOrder(), uiDish
						.getDishID(), spec1
						.getName(), 0, true, "888888", "G", "");
				List<UITasteOption> uiTasteOptions = new ArrayList<>();
				uiTasteOptions.add(option2);

				UIOptionCategory       uiOptionCategory   = new UIOptionCategory("888888", "规格", 1, false, 1, uiTasteOptions,"");
				List<UIOptionCategory> uiOptionCategories = new ArrayList<>();
				uiOptionCategories.add(uiOptionCategory);
				uiDish.setOptionCategoryList(uiOptionCategories);
			}
			return;
		}
		for (UIDish uiDishHasAdd : list) {
			if (uiDishHasAdd.getDishName().equals(uiDish.getDishName())) {
				FeSkuSpec spec1 = getDishFeSkuSpec(uiDish
						.getSkuSpecOuid());
				if (spec1 != null) {
					boolean hasAdd = false;
					for (UITasteOption tasteOption : uiDishHasAdd.getOptionCategoryList().get(0)
							.getOptionList()) {
						if (tasteOption.getOptionName().equals(spec1.getName())) {
							hasAdd = true;
						}
					}
					if (!hasAdd) {
						UITasteOption option2 = new UITasteOption("", spec1.getSeqOrder(), uiDish
								.getDishID(), spec1
								.getName(), 0, false, "888888", "G", "");
						uiDishHasAdd.getOptionCategoryList().get(0).getOptionList().add(option2);
						List<UIOptionCategory> list1        = uiDish.getOptionCategoryList();
						List<UITasteOption>    cloneOptions = new ArrayList<>();
						for (UITasteOption option : uiDishHasAdd.getOptionCategoryList().get(0)
								.getOptionList()) {
							UITasteOption myclone = option.myclone();
							cloneOptions.add(myclone);
						}


						if (list1 == null) {
							list1 = new ArrayList<>();
							for (UITasteOption option3 : cloneOptions) {
								if (option3.getOptionName().equals(option2.getOptionName())) {
									option3.setRequired(true);
								} else {
									option3.setRequired(false);
								}
							}

							UIOptionCategory uiOptionCategory = new UIOptionCategory("888888", "规格", 1, false, 1, cloneOptions,"");
							list1.add(uiOptionCategory);
							uiDish.setOptionCategoryList(list1);
						} else {
							for (UITasteOption option3 : cloneOptions) {
								if (option3.getOptionName().equals(option2.getOptionName())) {
									option3.setRequired(true);
								} else {
									option3.setRequired(false);
								}
							}

							UIOptionCategory uiOptionCategory = new UIOptionCategory("888888", "规格", 1, false, 1, cloneOptions,"");

							list1.remove(0);
							list1.add(0, uiOptionCategory);
						}
					}
					continue;
				}
			} else if (uiDish.getOptionCategoryList() == null) {
				FeSkuSpec spec1 = getDishFeSkuSpec(uiDish
						.getSkuSpecOuid());
				if (spec1 != null) {
					UITasteOption option2 = new UITasteOption("", spec1.getSeqOrder(), uiDish
							.getDishID(), spec1
							.getName(), 0, true, "888888", "G", "");
					List<UITasteOption> uiTasteOptions = new ArrayList<>();
					uiTasteOptions.add(option2);

					UIOptionCategory       uiOptionCategory   = new UIOptionCategory("888888", "规格", 1, false, 1, uiTasteOptions,"");
					List<UIOptionCategory> uiOptionCategories = new ArrayList<>();
					uiOptionCategories.add(uiOptionCategory);
					uiDish.setOptionCategoryList(uiOptionCategories);
				}
			}
		}
	}


	/**
	 * 获取某个产品规格
	 *
	 * @return
	 */
	public static FeSkuSpec getDishFeSkuSpec(String skuSpecOuid) {
		if (feSkuSpecList == null) {
			return null;
		}
		Collections.sort(feSkuSpecList, new Comparator<FeSkuSpec>() {

			@Override
			public int compare(FeSkuSpec o1, FeSkuSpec o2) {
				int i = o1.getSeqOrder() - o2.getSeqOrder();
				return i;
			}
		});

		for (FeSkuSpec skuSpec : feSkuSpecList) {
			if (skuSpec.getOuid().equals(skuSpecOuid)) {
				return skuSpec;
			}
		}

		return null;
	}

	private static String getDishUnitByUomId(String ouid) {
		if (FeUomList == null)
			return null;
		for (FeUom feUom : FeUomList) {
			if (feUom.getOuid().equals(ouid)) {
				return feUom.getName();
			}
		}
		return null;
	}

	private static List<UIPackageItem> getUIDishPackageItems(FeSku feSkuOut) {
		if (feSkuComboKindList == null)
			return null;
		List<UIPackageItem> uiPackageItems = new ArrayList<>();
		for (FeSkuComboKind kind : feSkuComboKindList) {
			if (feSkuOut.getOuid().equals(kind.getSkuOuid())) {
				UIPackageItem packageItem = new UIPackageItem();
				packageItem.setIsMust(0);
				packageItem.setExpanded(true);
				packageItem.setItemID(kind.getOuid());
				packageItem.setMinQty(kind.getMinQty());
				packageItem.setMaxQty(kind.getMaxQty());
				packageItem
						.setItemName(kind.getName() + "(" + kind.getMinQty() + "~" + kind
								.getMaxQty() + ")");
				List<UIPackageOptionItem> uiPackageOptionItems = new ArrayList<>();
				//				List<UITasteOption>            uiTastOptions        = new ArrayList<>();
				for (FeSkuCombo skuCombo : feSkuComboList) {
					if (skuCombo.getSkuComboKindOuid().equals(kind.getOuid())) {
						FeSku feSku = getDishFromFeSku(skuCombo.getSkuOuid());
						UIPackageOptionItem item = new UIPackageOptionItem(kind.getOuid(), feSku
								.getName(), feSku
								.getOuid(), String.valueOf(skuCombo
								.getPrice()), String.valueOf("0"), 100, 1, "份", "ljl", "自动", "");
						item.setOptionCategoryList(getUIDishOptionCategory(new ArrayList<UIOptionCategory>(), feSku
								.getOuid()));
						item.setImageName(feSku.getMediaUrl());
						uiPackageOptionItems.add(item);
					}
				}
				//这里有点问题，主要是因为同步时的套餐和我们的不太一样，他们是设置最多选多少项，最少选多少项，最少选择可以为0
				//而智慧快餐的是设置最少选，并没有最大选
				packageItem.setItemType(uiPackageOptionItems.size() == 1 ? 1 : 0);

				packageItem.setOptions(uiPackageOptionItems);
				uiPackageItems.add(packageItem);
			}
		}
		if (uiPackageItems.size() == 0)
			return null;
		return uiPackageItems;
	}

	public static FeSku getDishFromFeSku(String ouid) {
		if (feSkuList == null)
			return null;
		for (FeSku feSku : feSkuList) {
			if (feSku.getOuid().equals(ouid))
				return feSku;
		}
		return null;
	}

	private static List<UIOptionCategory> getUIDishOptionCategory(List<UIOptionCategory> uiOptionCategories, String ouid) {
		if (feSkuCompositeList == null)
			return null;
		List<UITasteOption> tasteOptionsM = new ArrayList<>();
		List<UITasteOption> tasteOptionsF = new ArrayList<>();

		for (FeSkuComposite feSkuComposite : feSkuCompositeList) {
			if (feSkuComposite.getSkuOuid().equals(ouid)) {
				if (feSkuComposite.getSourceType().equals("M")) {
					//加料
					FeSkuMix mix = getDishFeSkuMix(feSkuComposite.getSourceOuid());
					tasteOptionsM.add(new UITasteOption(mix.getOuid(), mix.getSeqOrder(), mix
							.getSkuMixId(), mix
							.getName(), mix
							.getPrice(), false, String
							.valueOf(10000), "M", mix.getSkuMixKindOuid()));
				} else if (feSkuComposite.getSourceType().equals("F")) {
					//口味
					FeSkuFeature feture = getDishFeSkuFeture(feSkuComposite.getSourceOuid());
					tasteOptionsF.add(new UITasteOption("", feture.getSeqOrder(), feture
							.getSkuFeatureId(), feture
							.getName(), 0, false, feture.getSkuFeatureKindOuid(), "F", feture
							.getSkuFeatureKindOuid()));

				}
			}
		}

		Collections.sort(tasteOptionsM, new Comparator<UITasteOption>() {

			@Override
			public int compare(UITasteOption o1, UITasteOption o2) {
				int i = o1.getSeqOrder() - o2.getSeqOrder();
				return i;
			}
		});
		Collections.sort(tasteOptionsF, new Comparator<UITasteOption>() {

			@Override
			public int compare(UITasteOption o1, UITasteOption o2) {
				int i = o1.getSeqOrder() - o2.getSeqOrder();
				return i;
			}
		});
		if (uiOptionCategories == null) {
			uiOptionCategories = new ArrayList<>();
		}
		List<String> feKindOuid = new ArrayList<>();

		if (tasteOptionsF.size() > 0) {
			for (UITasteOption feature : tasteOptionsF) {
				if (!feKindOuid.contains(feature.getKindOuid()))
					feKindOuid.add(feature.getKindOuid());
			}
			for (int i = 0; i < feKindOuid.size(); i++) {
				ArrayList<UITasteOption> arrayList = new ArrayList<>();
				for (UITasteOption option : tasteOptionsF) {
					if (option.getCategoryId().equals(feKindOuid.get(i))) {
						arrayList.add(option);
					}
				}
				UIOptionCategory category222 = new UIOptionCategory(feKindOuid
						.get(i), getFeskuFeatureKindNameById(feKindOuid
						.get(i)), 0, isUIOptionCategoryMix(feKindOuid
						.get(i)), isFeatureMuti(feKindOuid
						.get(i)) ? arrayList.size() : 1, arrayList,getFeskuFeatureMemoById(feKindOuid.get(i)));
				uiOptionCategories.add(category222);
			}
		}

		if (tasteOptionsM.size() > 0) {
			UIOptionCategory category111 = new UIOptionCategory(String
					.valueOf(10000), "加料", 0, true, tasteOptionsM.size(), tasteOptionsM,"");
			uiOptionCategories.add(category111);
		}
		if (uiOptionCategories.size() > 0)
			return uiOptionCategories;
		return null;
	}
	private static String getFeskuFeatureMemoById(String ouid) {
		List<FeSkuFeatureKind> list = getFeSkuFeatureKindList();
		if (list == null)
			return null;
		for (FeSkuFeatureKind li : list) {
			if (li.getOuid().equals(ouid)) {
				return li.getMemo();
			}
		}
		return null;
	}
	private static String getFeskuFeatureKindNameById(String ouid) {
		List<FeSkuFeatureKind> list = getFeSkuFeatureKindList();
		if (list == null)
			return null;
		for (FeSkuFeatureKind li : list) {
			if (li.getOuid().equals(ouid)) {
				return li.getName();
			}
		}
		return null;
	}

	/**
	 * 判断这个类别是否可以多选
	 *
	 * @param ouid
	 * @return
	 */
	private static boolean isFeatureMuti(String ouid) {
		List<FeSkuFeatureKind> list = getFeSkuFeatureKindList();
		if (list == null)
			return false;
		for (FeSkuFeatureKind li : list) {
			if (li.getOuid().equals(ouid)) {
				return li.getMultipleFlag().equals("Y");
			}
		}
		return false;
	}

	private String getFeskuMixKindNameById(String ouid) {
		List<FeSkuMixKind> list = getFeSkuMixKindList();
		if (list == null)
			return null;
		for (FeSkuMixKind li : list) {
			if (li.getOuid().equals(ouid)) {
				return li.getName();
			}
		}
		return null;
	}

	/**
	 * 判断这个口味是否可以多选
	 *
	 * @param fetureKindOuid
	 * @return
	 */
	private static boolean isUIOptionCategoryMix(String fetureKindOuid) {
		for (FeSkuFeatureKind featureKind : FeSkuFeatureKindList)
			if (featureKind.getOuid().equals(fetureKindOuid)) {
				if (featureKind.getMultipleFlag().equals("Y")) {
					return true;
				} else
					return false;
			}
		return false;
	}

	/**
	 * 获取菜品的口味
	 *
	 * @param sourceOuid
	 * @return
	 */
	private static FeSkuFeature getDishFeSkuFeture(String sourceOuid) {
		if (feSkuFeatureList == null)
			return null;
		for (FeSkuFeature feature : feSkuFeatureList) {
			if (feature.getOuid().equals(sourceOuid)) {
				return feature;
			}
		}
		return null;
	}

	private static FeSkuMix getDishFeSkuMix(String sourceOuid) {
		if (feSkuMixList == null)
			return null;
		for (FeSkuMix feskumix : feSkuMixList) {
			if (feskumix.getOuid().equals(sourceOuid)) {
				return feskumix;
			}
		}
		return null;
	}

	public static List<UIDishKind> getSyncKindData() {
		if (feSkuCateList == null)
			return null;
		Collections.sort(feSkuCateList, new Comparator<FeSkuCate>() {

			@Override
			public int compare(FeSkuCate o1, FeSkuCate o2) {
				int i = o1.getSeqOrder() - o2.getSeqOrder();
				return i;
			}
		});
		ArrayList<UIDishKind> kinds = new ArrayList<>();
		for (FeSkuCate feSkuCate : feSkuCateList) {
			if (feSkuCate.getSkuCateType().equals("D")) {
				UIDishKind kind = new UIDishKind(feSkuCate.getOuid(), feSkuCate.getName(), FileUtil
						.getSyncImagePath(feSkuCate.getName()));
				kinds.add(kind);
			}
		}
		ArrayList<UIDishKind> usefulKinds = new ArrayList<>();
		for (UIDishKind kind : kinds) {
			List<UIDish> dishList = UIDataProvider
					.getDishListByKindId(kind.getKindID(), kind.getKindName());
			if (dishList != null && dishList.size() > 0) {
				usefulKinds.add(kind);
			}
		}
		return usefulKinds;
	}

	/**
	 * 获取同步时可用的優惠券
	 *
	 * @return
	 */
	public static int getSyncMemberCouponsSize() {
		SyncMemberLoginRes.DataBean account = SyncDataProvider
				.getSyncMemberAccount();
		SyncMemberLoginRes.DataBean                    memberAccount = account.myclone();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> canuse        = new ArrayList<>();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> cannotuse     = new ArrayList<>();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> allCoupon     = new ArrayList<>();
		if (memberAccount != null && memberAccount.getCoupon().size() > 0) {
			for (SyncMemberLoginRes.DataBean.MemberCoupon coupon : memberAccount.getCoupon()) {
				if (coupon.getSkuOuidList() != null && coupon.getSkuOuidList().size() > 0) {
					boolean canUse = false;
					for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
						if (coupon.getSkuOuidList().contains(cartDish.getDishID())) {
							canUse = true;
							continue;
						}
						if (!canUse) {
							if (cartDish.getOptionList() != null) {
								for (UITasteOption option : cartDish.getOptionList()) {
									if (option.getSourceType().equals("M") && coupon
											.getSkuOuidList()
											.contains(option.getOuid())) {
										canUse = true;
										break;
									}
								}
							}
						}
					}
					coupon.setCanUse(canUse);
					if (canUse) {
						canuse.add(coupon);
					} else
						cannotuse.add(coupon);
				} else {
					if ("B".equals(coupon.getCouponType())) {
						if ("A".equals(coupon.getFullType())) {
							//這個是 金額 全單滿減的
							if (Price.getInstance().getDishTotalWithMix().floatValue() >= coupon
									.getFullAmount()) {
								coupon.setCanUse(true);
								canuse.add(coupon);
							} else {
								coupon.setCanUse(false);
								cannotuse.add(coupon);
							}
						} else if ("Q".equals(coupon.getFullType())) {
							//這個是 數量 全單滿減的
							if (Cart.getInstance().getCartCount() >= coupon.getFullAmount()) {
								coupon.setCanUse(true);
								canuse.add(coupon);
							} else {
								coupon.setCanUse(false);
								cannotuse.add(coupon);
							}
						} else {
							coupon.setCanUse(true);
							canuse.add(coupon);
						}
					} else {
						coupon.setCanUse(true);
						canuse.add(coupon);
					}
				}
			}
		}
		allCoupon.addAll(canuse);
		allCoupon.addAll(cannotuse);
		return allCoupon.size();
	}

	/**
	 * 获取同步时会员优惠券
	 *
	 * @return
	 */
	public static List<SyncMemberLoginRes.DataBean.MemberCoupon> getSyncMemberCoupons() {
		SyncMemberLoginRes.DataBean account = SyncDataProvider
				.getSyncMemberAccount();
		SyncMemberLoginRes.DataBean                    memberAccount = account.myclone();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> canuse        = new ArrayList<>();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> cannotuse     = new ArrayList<>();
		List<SyncMemberLoginRes.DataBean.MemberCoupon> allCoupon     = new ArrayList<>();
		if (memberAccount != null && memberAccount.getCoupon().size() > 0) {
			for (SyncMemberLoginRes.DataBean.MemberCoupon coupon : memberAccount.getCoupon()) {
				if (coupon.getSkuOuidList() != null && coupon.getSkuOuidList().size() > 0) {
					boolean canUse = false;
					for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
						if (coupon.getSkuOuidList().contains(cartDish.getDishID())) {
							canUse = true;
							continue;
						}
						if (!canUse) {
							if (cartDish.getOptionList() != null) {
								for (UITasteOption option : cartDish.getOptionList()) {
									if (option.getSourceType().equals("M") && coupon
											.getSkuOuidList()
											.contains(option.getOuid())) {
										canUse = true;
										break;
									}
								}
							}
						}
					}
					coupon.setCanUse(canUse);
					if (canUse) {
						canuse.add(coupon);
					} else
						cannotuse.add(coupon);
				} else {
					if ("B".equals(coupon.getCouponType())) {
						if ("A".equals(coupon.getFullType())) {
							//這個是 金額 全單滿減的
							if (Price.getInstance().getDishTotalWithMix().floatValue() >= coupon
									.getFullAmount()) {
								coupon.setCanUse(true);
								canuse.add(coupon);
							} else {
								coupon.setCanUse(false);
								cannotuse.add(coupon);
							}
						} else if ("Q".equals(coupon.getFullType())) {
							//這個是 數量 全單滿減的
							if (Cart.getInstance().getCartCount() >= coupon.getFullAmount()) {
								coupon.setCanUse(true);
								canuse.add(coupon);
							} else {
								coupon.setCanUse(false);
								cannotuse.add(coupon);
							}
						} else {
							coupon.setCanUse(true);
							canuse.add(coupon);
						}
					} else {
						coupon.setCanUse(true);
						canuse.add(coupon);
					}
				}
			}
		}
		allCoupon.addAll(canuse);
		allCoupon.addAll(cannotuse);
		return allCoupon;
	}


	/**
	 * 获取某个产品的规格
	 *
	 * @return
	 */
	public static List<FeSkuComposite> getDishFeSkuCompositeList(String skuOid) {
		if (feSkuCompositeList == null) {
			return null;
		}
		ArrayList<FeSkuComposite> feSkuComposites = new ArrayList<>();
		for (FeSkuComposite feSkuComposite : feSkuCompositeList) {
			if (feSkuComposite.getSkuOuid().equals(skuOid)) {
				feSkuComposites.add(feSkuComposite);
			}
		}
		return feSkuComposites;
	}

	private static SyncMemberLoginRes.DataBean memberAccount;

	public static void setSyncMemberAccount(SyncMemberLoginRes.DataBean data) {
		SyncDataProvider.memberAccount = data;
		if (data != null && data.getCoupon() != null) {
			for (SyncMemberLoginRes.DataBean.MemberCoupon coupon : data.getCoupon()) {
				if ("B".equals(coupon.getCouponType())) {
					Log.e("setSyncMemberAccount>", coupon.getCouponName() + "");
					Log.e("setSyncMemberAccount>", coupon.getEstimateAmount() + "");
					Log.e("setSyncMemberAccount>", coupon.getCouponValue() + "");
				}
			}
		}
	}

	public static SyncMemberLoginRes.DataBean getSyncMemberAccount() {
		return memberAccount;
	}

	public static String getSyncCompanySetByConfigKey(String configKey) {
		if (mCompanyConfigList == null)
			return null;
		for (FeCompanyConfig config : mCompanyConfigList) {
			if (config.getConfigKey().equals(configKey)) {
				return config.getConfigValue();
			}
		}
		return null;
	}

	public static void clearMemberLoginInfo() {
		setSyncMemberAccount(null);
	}

	public static String getSyncShopAddress(String shopNo) {
		if (feStoreshopBeanList == null) {
			return "";
		}
		for (FeStoreshopBean bean : feStoreshopBeanList) {
			if (shopNo.equals(bean.getStoreshopId())) {
				return bean.getStreeAddr();
			}
		}
		return "";
	}

	public static String getSyncShopTel(String shopNo) {
		if (feStoreshopBeanList == null) {
			return "";
		}
		for (FeStoreshopBean bean : feStoreshopBeanList) {
			if (shopNo.equals(bean.getStoreshopId())) {
				return bean.getTel();
			}
		}
		return "";
	}

	public static FePosUserRes getUserRes() {
		File file = new File(FileUtil
				.getSyncFoldPath() + File.separator + "jsondata" + File.separator + "FePosUser.json");
		if (!file.exists()) {
			return null;
		}
		StringBuilder   builder = new StringBuilder();
		FileInputStream stream  = null;
		try {
			stream = new FileInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(stream));
			String         line;
			while ((line = bf.readLine()) != null) {
				builder.append(line);
			}
			FePosUserRes bean = GsonUtils.getSingleBean(builder.toString(), FePosUserRes.class);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
