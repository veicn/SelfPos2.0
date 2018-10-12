package com.acewill.slefpos.orderui.main.ui.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.CustomItemAnimator;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/7/11 11:13
 * Desc：确认支付对话框
 */
public class ComfirmDialog extends BaseDialog implements OnLoadMoreListener, OnRefreshListener {
	private IRecyclerView                                               irc;
	private CommonRecycleViewAdapter<SyncAcceptReq.DataBean.PromosBean> mAdapter;

	public static ComfirmDialog getInstance() {
		ComfirmDialog dialog = new ComfirmDialog();
		return dialog;
	}

	private boolean isInit;
	private View    view;

	@Override
	public View getView() {
		if (isInit) {
			return view;
		}
		view = View.inflate(mcontext, R.layout.dialog_comfirm, null);
		irc = (IRecyclerView) view.findViewById(R.id.irc);
		TextView total_price = (TextView) view.findViewById(R.id.total_price);
		total_price.setText(String.valueOf("￥ " + Price.getInstance().getDishTotalWithMix()));
		view.findViewById(R.id.cancer).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				mRxManager.post(AppConstant.COMFIRM_ORDER, 1);
			}
		});
		view.findViewById(R.id.iv_cancer).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		mAdapter = new CommonRecycleViewAdapter<SyncAcceptReq.DataBean.PromosBean>(mcontext, R.layout.item_comfirm_dialog) {
			@Override
			public void convert(ViewHolderHelper helper, SyncAcceptReq.DataBean.PromosBean bean) {
				TextView paymethod_name  = helper.getView(R.id.paymethod_name);
				TextView paymethod_price = helper.getView(R.id.paymethod_price);
				paymethod_name.setText(bean.getPromoName());
				paymethod_price.setTextColor(mContext.getResources()
						.getColor(R.color.role_yellow_gray));
				paymethod_price.setText("-￥ " + bean.getPromoAmount());
			}
		};
		irc.setAdapter(mAdapter);
		irc.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
		irc.setItemAnimator(new CustomItemAnimator());
		irc.setOnLoadMoreListener(this);
		irc.setOnRefreshListener(this);
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		//		adapter.initAnimation(irc);//初始化刚进入界面时候的动画
		mAdapter.addAll(getData());
		isInit = true;
		return view;
	}

	private List<SyncAcceptReq.DataBean.PromosBean> getData() {
		List<SyncAcceptReq.DataBean.PromosBean> list = new ArrayList<>();
		for (SyncAcceptReq.DataBean.PromosBean bean : Order.getInstance().getSyncPromosList()) {
			if (bean.getPromoName().equals("会员价")) {
				list.add(bean);
			}
		}
		ArrayList<SyncAcceptReq.DataBean.PromosBean> actualList = new ArrayList<>();
		for (SyncAcceptReq.DataBean.PromosBean bean : Order.getInstance().getSyncPromosList()) {
			if (!bean.getPromoName().equals("会员价")) {
				actualList.add(bean);
			}
		}
		if (list.size() > 0) {
			BigDecimal totalMember = new BigDecimal("0");
			for (SyncAcceptReq.DataBean.PromosBean bean : list) {
				totalMember = PriceUtil.add(totalMember, new BigDecimal(bean.getPromoAmount()));
			}
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoName("会员优惠");
			bean.setPromoAmount(totalMember.toString());
			actualList.add(bean);
		}

		if (Price.getInstance().getBalance() > 0) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoName("会员储值");
			bean.setPromoAmount(String.valueOf(Price.getInstance().getBalance()));
			actualList.add(bean);
		}

		//		SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
		//		bean.setPromoName("订单金额");
		//		bean.setPromoAmount(String.valueOf(Price.getInstance().getDishTotalWithMix()));
		//		actualList.add(bean);

		return actualList;
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}
}
