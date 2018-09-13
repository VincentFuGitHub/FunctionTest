package com.unitfunction.home;

import android.app.Application;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseActivityForFragment;

import butterknife.BindView;

/**
 * Created by Vincent on 2018/9/12.
 */

public class FunctionListActivity extends BaseActivityForFragment{

    private final String Tag = "FunctionList";

    @BindView(R.id.test_http)
    Button testHttp;
    @BindView(R.id.test_db)
    Button testDb;

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
