package com.bravo.easyhttp;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bravo.easyhttp.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/10/17.
 */

public class HttpTask<T> implements  Runnable{

    private static final String TAG = "bravo";
    IHttpService httpService;
    public HttpTask(RequestHolder<T> requestHolder) {
        httpService = requestHolder.getHttpService();
        httpService.setUrl(requestHolder.getUrl());
        T requestInfo = requestHolder.getRequestInfo();
        String json = JSON.toJSONString(requestInfo);
        try {
            httpService.setRequestParams(json.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Log.e(TAG,"httpService.excute()   before ...");
        httpService.excute();
    }
}
