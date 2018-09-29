package com.unitfunction.funfrag.dbunitfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseFragment;
import com.unitfunction.common.log.LogUtil;
import com.unitfunction.funactivity.dbunit.DbunitContract;
import com.unitfunction.funactivity.dbunit.DbunitPresenter;

import butterknife.BindView;

/**
 * Created by Vincent on 2018/9/14.
 */

public class DbunitFragment extends BaseFragment implements DbunitFragContract.IView{

    static final String TAG = "DbunitFragment";

    private DbFragCallBack dbFragCallBack;
    private DbunitFragContract.IPresenter iPresenter;

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
        OnDbFragCallBack onDbFragCallBack = (OnDbFragCallBack)getActivity();
        dbFragCallBack = onDbFragCallBack.getDbFragCallBack();

        Bundle bundle = getArguments();
        if(bundle != null){
            //get data or title from bundle
            LogUtil.d(TAG, "bundle is not null", LogUtil.DEBUG);
        }
    }

    @Override
    protected void initView(View view) {
        createDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbFragCallBack.createDb();
            }
        });

        addDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbFragCallBack.addDbItem();
            }
        });

        updateDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbFragCallBack.updateItem();
            }
        });

        queryeDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbFragCallBack.queryItem();
            }
        });

        deleteDb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbFragCallBack.deleteItem();
            }
        });
    }

    @Override
    protected void initPresent() {
        iPresenter = new DbunitFragPresenter(this);
        iPresenter.startLogic();
    }

    public interface OnDbFragCallBack{
        DbFragCallBack getDbFragCallBack();
    }

    public static <T extends Activity & OnDbFragCallBack> DbunitFragment newInstance(T callback){
        DbunitFragment dbunitFragment = new DbunitFragment();
        //if there is anydata, handle here
        //use bundle or parcelable
        return dbunitFragment;
    }

}
