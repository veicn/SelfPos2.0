package com.acewill.slefpos.bean.canxingjianbean;

import com.acewill.slefpos.bean.uibean.UIDish;

import java.util.ArrayList;
import java.util.List;

public class CxjMenuResp extends UIDish {
	public String currentServerDate;
	public List<CxjKindModel>  categoryList = new ArrayList<CxjKindModel>();
	public List<CxjDishModel>  dishList     = new ArrayList<CxjDishModel>();
	public List<CxjDishModel>  dishList_out = new ArrayList<CxjDishModel>();
	public List<CxjTasteModel> tasteList    = new ArrayList<CxjTasteModel>();
}
