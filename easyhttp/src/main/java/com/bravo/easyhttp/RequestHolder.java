package com.bravo.easyhttp;

import com.bravo.easyhttp.interfaces.IHttpListener;
import com.bravo.easyhttp.interfaces.IHttpService;

/**
 * Created by Administrator on 2017/10/17.
 */

public class RequestHolder<T> {

    IHttpService httpService;
    IHttpListener httpListener;
    T requestInfo;
    String url;

    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return httpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    public T getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(T requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
