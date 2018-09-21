package com.unitfunction.funfrag.dbunitfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseFragment;
import com.unitfunction.funactivity.dbunit.DbunitContract;

import butterknife.BindView;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitFragment extends BaseFragment implements DbunitContract.IView{

    private DbFragCallBack dbFragCallBack;
    private DbunitContract.IPresenter iPresenter;

    @BindView(R.id.create_db)
    Button createDb;
    @BindView(R.id.add_db)
    Button addDb;
    @BindView(R.id.update_db)
    Button updateDb;
    @BindView(R.id.query_db)
    Button queryeDb;
    @BindView(R.id.delete_db)
    Button deleteDb;


    @Override
    protected int getLayoutId() {
        return R.layout.dbunit_fragment;
    }

    @Override
    protected void initData(Bundle saveInstanceStatus) {

    }

    @Override
    protected void initView(View view) {
        createDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    @Override
    protected void initPresent() {

    }

}
