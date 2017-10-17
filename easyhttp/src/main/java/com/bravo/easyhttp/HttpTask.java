package com.bravo.easyhttp;

import com.alibaba.fastjson.JSON;
import com.bravo.easyhttp.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/10/17.
 */

public class HttpTask<T> implements  Runnable{

    IHttpService httpService;
    public HttpTask(RequestHolder<T> requestHolder) {
        httpService = requestHolder.getHttpService();
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
        httpService.excute();
    }
}
