package com.unitfunction.subfunction.httpclient.internal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 2018/8/23.
 */

public class HttpClientCusData implements Parcelable {

    private int timeout = 60;

    protected HttpClientCusData(Parcel in) {
        timeout = in.readInt();
    }

    public static final Creator<HttpClientCusData> CREATOR = new Creator<HttpClientCusData>() {
        @Override
        public HttpClientCusData createFromParcel(Parcel in) {
            return new HttpClientCusData(in);
        }

        @Override
        public HttpClientCusData[] newArray(int size) {
            return new HttpClientCusData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(timeout);
    }

    public HttpClientCusData(int timeout) {
        this.timeout = timeout;
    }


}
