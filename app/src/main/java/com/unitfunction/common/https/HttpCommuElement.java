package com.unitfunction.common.https;

import java.util.Map;

/**
 * Created by fuwencheng on 2018/7/17.
 */

public abstract class HttpCommuElement {
    public enum Method{
        GET,
        POST,
        PATCH
    }

    private Method method;
    private String url;
    private Map<String, String> header;
    private String body;
/*    private Object classOfTSuccRspJson;
    private Object classOfTFailRspJson;
    private Object classOfRspJson;*/
    private int httpCommuStatus = 0;
    private String strRspRet;

    public HttpCommuElement(Method method, String url, Map<String, String> header,
                            String body) {
        this.method = method;
        this.url = url;
        this.header = header;
        this.body = body;
   /*     this.classOfTSuccRspJson = classOfTSuccRspJson;
        this.classOfTFailRspJson = classOfTFailRspJson;*/
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public int getHttpCommuStatus() {
        return httpCommuStatus;
    }

    public void setHttpCommuStatus(int httpCommuStatus) {
        this.httpCommuStatus = httpCommuStatus;
    }

    private String getStrRspRet() {
        return strRspRet;
    }

    public void setStrRspRet(String strRspRet) {
        this.strRspRet = strRspRet;
    }

    public void handleRspRet() {
        if(httpCommuStatus == 200){
            handleHttpSucRet(strRspRet);
        }
        else{
            handleHttpErrRet(strRspRet);
        }
    }

    public abstract void handleHttpSucRet(String strRspRet);
    public abstract void handleHttpErrRet(String strRspRet);
}
