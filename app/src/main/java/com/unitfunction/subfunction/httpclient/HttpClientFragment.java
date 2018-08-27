package com.unitfunction.subfunction.httpclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.unitfunction.R;
import com.unitfunction.common.base.BaseFragment;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCallBack;
import com.unitfunction.subfunction.httpclient.internal.HttpClientContract;
import com.unitfunction.subfunction.httpclient.internal.HttpClientCusData;
import com.unitfunction.subfunction.httpclient.internal.HttpClientPresent;

import butterknife.BindView;

/**
 * Created by Vincent on 2018/8/22.
 */

public class HttpClientFragment extends BaseFragment implements HttpClientContract.IView{

    private static final String TITLE_TAG = "HTTP_CLIENT_TITLE_TAG";
    private static final String CUSTOMER_TAG = "HTTP_CLIETN_CUS_DATA_TAG";

    private String title;
    private HttpClientCusData httpClientCusData;
    private HttpClientCallBack httpClientCallBack;

    @BindView(R.id.ev_input_host_addr)
    EditText evHostAddr;
    @BindView(R.id.bt_http)
    Button btHttp;
    @BindView(R.id.bt_https)
    Button btHttps;
    @BindView(R.id.lv_res)
    ListView lvRes;

    private HttpClientContract.IPresent present;
    private String[] strArray = {"default1", "default2"};

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_listview, strArray);
        lvRes.setAdapter(adapter);
        btHttp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                httpClientCallBack.httpCallBack(HttpClientFragment.this.getContext());
            }
        });

        btHttps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpClientCallBack.httpsCallBack(HttpClientFragment.this.getContext());
            }
        });
    }

    @Override
    protected void initPresent() {
        present = new HttpClientPresent(this);
        present.startLogic();
    }

    public interface OnHttpClientCallBack {
        HttpClientCallBack getHttpClientCallBack();
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