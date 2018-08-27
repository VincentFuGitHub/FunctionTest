package com.unitfunction.common.https;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by fuwencheng on 2018/7/16.
 */

public class HttpAddHeader {
    private static boolean LOG_ENABLE = true;
    private static String LOG_TAG = "HttpAddHeader";
    private Map<String, String> mHeader;
    private Request.Builder mBuilder = new Request.Builder();

    public HttpAddHeader(Map<String, String> mHeader) {
        this.mHeader = mHeader;
    }

    public Request.Builder addRequestBuilderHeader(){
        if(mBuilder == null){
            return null;
        }

        for(String headerName : this.mHeader.keySet()){
            String headerValue = this.mHeader.get(headerName);
            if (headerName == null){
                break;
            }
            else{
                System.out.print("header key=value -> "  + headerName + "=" + headerValue);
                mBuilder.addHeader(headerName, headerValue);
            }
        }
        return mBuilder;
    }
}
