package com.acewill.slefpos.system.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.eftimoff.androipathview.PathView;

/**
 * Author：Anch
 * Date：2018/6/28 10:02
 * Desc：
 */
public class TestActivity extends BaseActivity {
	@Override
	public int getLayoutId() {
		return R.layout.act_test;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		final PathView pathView = (PathView) findViewById(R.id.pathView);
		//        final Path path = makeConvexArrow(50, 100);
		//        pathView.setPath(path);
		//       pathView.setFillAfter(true);
		//  pathView.useNaturalColors();
		pathView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pathView.getPathAnimator().
						//pathView.getSequentialPathAnimator().
								delay(100).
						duration(1500).
						interpolator(new AccelerateDecelerateInterpolator()).
						start();
			}
		});
	}
}
