package com.unitfunction.home;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseActivityForFragment;
import com.unitfunction.common.log.LogUtil;
import com.unitfunction.funactivity.dbunit.DbunitActivity;
import com.unitfunction.funactivity.http.HttpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.unitfunction.common.log.LogUtil.DEBUG;

/**
 * Created by Vincent on 2018/9/12.
 */

public class FunctionListActivity extends AppCompatActivity{

    private final String Tag = "FunctionList";

    @BindView(R.id.test_http)
    Button testHttp;
    @BindView(R.id.test_db)
    Button testDb;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.functionlist_activity);
        unbinder = ButterKnife.bind(this);
        initListener();
    }

    @Override
    protected void onDestroy(){
        unbinder.unbind();
        super.onDestroy();
    }

    private void initListener(){
   /*     btnTestHttp();
        btnTestDb();*/
        testHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTestHttp();
            }
        });

        testDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTestDb();
            }
        });
    }

    @OnClick(R.id.test_http)
    void btnTestHttp(){
        LogUtil.d(Tag, "Start Http Activity", DEBUG);
        Intent intent = new Intent(FunctionListActivity.this, HttpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.test_db)
    void btnTestDb(){
        LogUtil.d(Tag, "Start Db Activity", DEBUG);
        Intent intent = new Intent(FunctionListActivity.this, DbunitActivity.class);
        startActivity(intent);
    }

}
