package com.unitfunction.subfunction.httpclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseFragment;
import com.unitfunction.common.base.RecyclerViewAdapter;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientContract;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;
import com.unitfunction.subfunction.httpclient.internal.HttpClientPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Vincent on 2018/8/22.
 */

public class HttpClientFragment extends BaseFragment implements HttpClientContract.IView{

    private static final String TITLE_TAG = "HTTP_CLIENT_TITLE_TAG";
    private static final String CUSTOMER_TAG = "HTTP_CLIENT_CUS_DATA_TAG";

    @BindView(R.id.ev_input_host_addr)
    EditText evHostAddr;
    @BindView(R.id.bt_http)
    Button btHttp;
    @BindView(R.id.bt_https)
    Button btHttps;
   /* @BindView(R.id.lv_res)
    ListView lvRes;*/
    @BindView(R.id.rv_res)
    RecyclerView rvRes;

    private String title;
    private HttpClientCusData httpClientCusData;
    private HttpClientCallBack httpClientCallBack;
    private Handler handler = new Handler(Looper.getMainLooper());

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    private HttpClientContract.IPresent present;
    private List<String> strArray = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.http_client_fragment;
    }

    @Override
    protected void initData(Bundle saveInstanceStatus) {
        OnHttpClientCallBack onHttpClientCallBack = (OnHttpClientCallBack) getActivity();
        httpClientCallBack = onHttpClientCallBack.getHttpClientCallBack();

        Bundle bundle = getArguments();
        if(bundle != null){
            title = bundle.getString(TITLE_TAG);
            httpClientCusData = bundle.getParcelable(CUSTOMER_TAG);
        }
    }

    @Override
    protected void initView(View view) {
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_listview, strArray);
        lvRes.setAdapter(adapter);*/
        initRecyclerView();
        btHttp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                httpClientCallBack.httpCallBack(HttpClientFragment.this.getContext());
                present.httpCallBack(HttpClientFragment.this.getContext());
            }
        });

        btHttps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                httpClientCallBack.httpsCallBack(HttpClientFragment.this.getContext());
                present.httpsCallBack(HttpClientFragment.this.getContext());
            }
        });
    }

    @Override
    protected void initPresent() {
        present = new HttpClientPresent(this, httpClientCallBack);
        present.startLogic();
    }

    @Override
    public void updateRecyclerView(final List<String> list){
        handler.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewAdapter.updateViewAdapter(list);
            }
        });
    }

    public interface OnHttpClientCallBack {
        HttpClientCallBack getHttpClientCallBack();
    }


    private void initRecyclerView(){
        rvRes.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        rvRes.setLayoutManager(mLayoutManager);
        strArray.add("Wait for update");
        recyclerViewAdapter = new RecyclerViewAdapter(strArray);
        mAdapter = recyclerViewAdapter;
        rvRes.setAdapter(mAdapter);
    }

    //normal create fragment, save data in serial
    public static <T extends Activity & OnHttpClientCallBack> HttpClientFragment newInstance(String title,
                                                                                             HttpClientCusData httpCusData,
                                                                                             T callback){
        HttpClientFragment httpClientFragment = new HttpClientFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_TAG, title);
        bundle.putParcelable(CUSTOMER_TAG, httpCusData);
        httpClientFragment.setArguments(bundle);
        return httpClientFragment;
    }

}