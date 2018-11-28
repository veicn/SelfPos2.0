package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.print.ticketprint.SmarantPrintUtil;
import com.acewill.slefpos.print.ticketprint.SmarantTicketPrintHandler;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.CustomItemAnimator;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/27 0027.
 */

public class PrintOrderListDialog extends BaseDialog {
	@Bind(R.id.irc)
	IRecyclerView irc;
	private boolean                               isPrepare;
	private View                                  view;
	private CommonRecycleViewAdapter<NewOrderRes> adapter;


	/**
	 * @return
	 */
	public static PrintOrderListDialog newInstance() {
		PrintOrderListDialog fragment = new PrintOrderListDialog();
		Bundle      bundle   = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {

		if (!isPrepare) {
			view = View.inflate(mcontext, R.layout.dialog_printorderlist, null);
			ButterKnife.bind(this, view);
			initData();
			isPrepare = true;
		}
		return view;
	}

	private void initData() {
		List<NewOrderRes> list = SmarantPrintUtil.getPrintOrderList();
		initView(list);
	}

	private void initView(List<NewOrderRes> list) {
		adapter = new CommonRecycleViewAdapter<NewOrderRes>(getActivity(), R.layout.item_printorder) {
			@Override
			public void convert(final ViewHolderHelper helper, final NewOrderRes printOrder) {
				TextView tv_order_id   = helper.getView(R.id.tv_order_id);
				TextView tv_order_time = helper.getView(R.id.tv_order_time);
				TextView tv_order_oid  = helper.getView(R.id.tv_order_oid);


				tv_order_id.setText("取餐号:" + printOrder.getContent().getCallNumber());
				tv_order_time.setText("下单时间:" + printOrder.getCreate_time());
				tv_order_oid.setText("支付流水号:" + printOrder.getBiz_id());


				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						SmarantTicketPrintHandler.getInstance()
								.printSmarantTicket(mContext, printOrder);
					}
				});
			}
		};
		irc.setAdapter(adapter);
		irc.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
		irc.setItemAnimator(new CustomItemAnimator());
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		//		adapter.initAnimation(irc);//初始化刚进入界面时候的动画
		adapter.addAll(list);
	}

	@Override
	public float getSize() {
		return 0.7f;
	}
}
