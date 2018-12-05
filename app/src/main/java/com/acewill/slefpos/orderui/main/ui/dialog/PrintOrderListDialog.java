package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.orderbean.PrintOrder;
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
	private boolean                              isPrepare;
	private View                                 view;
	private CommonRecycleViewAdapter<PrintOrder> adapter;


	/**
	 * @return
	 */
	public static PrintOrderListDialog newInstance() {
		PrintOrderListDialog fragment = new PrintOrderListDialog();
		Bundle               bundle   = new Bundle();
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
		List<PrintOrder> list = SmarantPrintUtil.getPrintOrderList();
		initView(list);
	}

	private void initView(List<PrintOrder> list) {
		adapter = new CommonRecycleViewAdapter<PrintOrder>(getActivity(), R.layout.item_printorder) {
			@Override
			public void convert(final ViewHolderHelper helper, final PrintOrder printOrder) {
				TextView       tv_order_callid        = helper.getView(R.id.tv_order_callid);
				TextView       tv_order_eatmethod     = helper.getView(R.id.tv_order_eatmethod);
				RelativeLayout tv_order_memberinfo_ly = helper.getView(R.id.tv_order_memberinfo_ly);
				TextView       tv_order_memberinfo    = helper.getView(R.id.tv_order_memberinfo);
				TextView       tv_order_time          = helper.getView(R.id.tv_order_time);

				tv_order_callid.setText(printOrder.getCallId());
				tv_order_eatmethod.setText(printOrder.getEatmethod());
				if (printOrder.isMember() && !TextUtils
						.isEmpty(printOrder.getMemberNameAndPhone())) {
					tv_order_memberinfo_ly.setVisibility(View.VISIBLE);
					tv_order_memberinfo.setText(printOrder.getMemberNameAndPhone());
				}
				tv_order_time.setText(printOrder.getCreateTime());

				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						SmarantTicketPrintHandler.getInstance()
								.printTicket(printOrder);
						dismiss();
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

	private void showUserLoginDialog() {
		UserLoginDialog dialog = UserLoginDialog.newInstance();
		dialog.show(getFragmentManager(), "UserLoginDialog");
	}

	@Override
	public float getSize() {
		return 0.7f;
	}
}
