package com.unitfunction.common.https;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by fuwencheng on 2018/7/16.
 */

public class HttpObject extends Application{
    private static final int BASE_ERROR = -1000;
    private static final int PARAMETER_ERROR = BASE_ERROR - 1;
    private static final int CONNECT_ERROR   = BASE_ERROR - 2;


    private static boolean LOG_ENABLE = true;
    private static String LOG_TAG = "HttpObject";
    private OkHttpClient mOkHttpClient = null;
    private Map<String, String> httpHeader = null;
    private String url = null;
    private String rspStr = null;
    private Request request = null;
    private RequestBody body = null;
    private Request.Builder requestBuiler = null;
    private InputStream certificateInputStream = null;
    private Context context;

    private void setBody(String strBody) {
        this.body = setRequestBody(strBody);
    }

    private RequestBody setRequestBody(String jsonStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        return body;
    }

    private void setHttpHeader(Map<String, String> httpHeader) {
        this.httpHeader = httpHeader;
    }

    private Map<String, String> defaultMsgHeader() {
        Map<String, String> mHeader = new HashMap<String, String>();
        mHeader.put("X-Rm-Platform", "application/terminal");
        mHeader.put("Content-Type", "application/json");
        return mHeader;
    }

    public HttpObject(Context context, long connectTimeoutSec, long readTimeoutSec, long writeTimeoutSec, boolean isSslConnect) {
        OkHttpClient.Builder tmpClient = null;
        File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        tmpClient = new OkHttpClient.Builder()
                                    .connectTimeout(connectTimeoutSec, TimeUnit.SECONDS)
                                    .readTimeout(readTimeoutSec, TimeUnit.SECONDS)
                                    .writeTimeout(writeTimeoutSec, TimeUnit.SECONDS)
                                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        this.context = context;
        if(isSslConnect){
            tmpClient = setSslContext(tmpClient);
            if(tmpClient == null){
                this.mOkHttpClient = null;
                return;
            }
        }
        this.mOkHttpClient = tmpClient.build();
    }

    public HttpObject(Context context, boolean isSslConnect){
        OkHttpClient.Builder tmpClient = null;
        File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
        Log.d(LOG_TAG, "Dir="+sdcache);
        int cacheSize = 10 * 1024 * 1024;
        tmpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        this.context = context;
        if(isSslConnect){
            tmpClient = setSslContext(tmpClient);
            if(tmpClient == null){
                this.mOkHttpClient = null;
                return;
            }
        }
        this.mOkHttpClient = tmpClient.build();
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setGetRequest(){
        if(httpHeader == null){
            httpHeader = defaultMsgHeader();
        }
        Request.Builder tmpBuild = new HttpAddHeader(this.httpHeader).addRequestBuilderHeader();
        this.request = tmpBuild.get().url(this.url).build();
    }

    private void setPostRequest() {
        if(httpHeader == null){
            httpHeader = defaultMsgHeader();
        }
        Request.Builder tmpBuild = new HttpAddHeader(this.httpHeader).addRequestBuilderHeader();
        this.request = tmpBuild.post(body).url(this.url).build();
    }

    private void setPatchRequest() {
        if(httpHeader == null){
            httpHeader = defaultMsgHeader();
        }
        Request.Builder tmpBuild = new HttpAddHeader(this.httpHeader).addRequestBuilderHeader();
        this.request = tmpBuild.patch(body).url(this.url).build();
    }

    public String getRspStr() {
        return this.rspStr;
    }

    private void setRspStr(String rspStr) {
        this.rspStr = rspStr;
    }

    private OkHttpClient.Builder setSslContext(OkHttpClient.Builder builder) {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .allEnabledTlsVersions()
                .allEnabledCipherSuites()
                .build();

        try {
            //1. set certificate type as X509
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            //2. open http certificate in assets
//            InputStream stream = new Context().this.getAssets().open("cert.pem");
//            AssetManager am = super.getApplicationContext().getAssets();
            AssetManager am = context.getAssets();
            InputStream stream = am.open("cert.pem");
            Certificate certificate = certificateFactory.generateCertificate(stream);
            //3. certificate type
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //4. certificate password for host
            keyStore.load(null, null);
            keyStore.setCertificateEntry("certificate", certificate);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            //5. certificate password for client
            keyManagerFactory.init(keyStore, "555".toCharArray());

            X509TrustManager trustManager;

            trustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.connectionSpecs(Collections.singletonList(spec))
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    });

            return builder;

        } catch(SSLHandshakeException e){
            Log.d(LOG_TAG, "Handshake error");
            e.printStackTrace();
        }catch (CertificateException e) {
            Log.d(LOG_TAG, "certificate error");
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private int doCommu(){
        if(this.mOkHttpClient == null){
            System.out.print("Create HttpClient Failed");
            return PARAMETER_ERROR;
        }

        Call call = this.mOkHttpClient.newCall(this.request);
        int rspCode = 0;
        try {
            Response response = call.execute();
            String receiveString = response.body().string();
            Log.d(LOG_TAG, "http receivce string-> " + receiveString);
            setRspStr(receiveString);
            rspCode = response.code();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "enqueue fail->" + call.toString());
            rspCode = CONNECT_ERROR;
        } catch (Exception e){
            e.printStackTrace();
            Log.d(LOG_TAG, "Exception");
        }
         /*final int[] rspCode = {0};
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AppLogUtils.debug(LOG_ENABLE, LOG_TAG,  "enqueue fail->" + call.toString());
                rspCode[0] = CONNECT_ERROR;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rspStr = response.body().string();
                AppLogUtils.debug(LOG_ENABLE, LOG_TAG,  "enqueue response -> " + rspStr);
                setRspStr(rspStr);
                rspCode[0] = response.code();
            }
        });*/

        return rspCode;
    }

    public int handleHttpsGetCommu(String url, Map<String, String> httpHeader){
        setUrl(url);
        setHttpHeader(httpHeader);
        setGetRequest();
        return doCommu();
    }

    public int handleHttpsPostCommu(String url, Map<String, String> httpHeader, String strBody){
        setUrl(url);
        setHttpHeader(httpHeader);
        setBody(strBody);
        setPostRequest();
        return doCommu();
    }

    public int handleHttpsPatchCommu(String url, Map<String, String> httpHeader, String strBody){
        setUrl(url);
        setHttpHeader(httpHeader);
        setBody(strBody);
        setPatchRequest();
        return doCommu();
    }
}
