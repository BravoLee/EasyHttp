package com.bravo.easyhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bravo.easyhttp.interfaces.IDataListener;
import com.bravo.easyhttp.interfaces.IHttpListener;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bravo.lee on 2017/10/15.
 */

class JsonHttpListener<R> implements IHttpListener {

    private static final String TAG = "bravo";
    private Class<R> responeseClass;
    private IDataListener<R> iDataListener;

    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<R> responeseClass, IDataListener<R> iDataListener) {
        this.responeseClass = responeseClass;
        this.iDataListener = iDataListener;
    }

    @Override
    public void onSuccess(HttpEntity entity) {
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();
            String responeseStr = getContent(inputStream);
            final R r = JSON.parseObject(responeseStr, responeseClass);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iDataListener.onSuccess(r);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            iDataListener.onFailed();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailed() {
        iDataListener.onFailed();
    }

    private String getContent(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }
}
