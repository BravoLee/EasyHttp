package com.bravo.easyhttp.interfaces;

/**
 * Created by bravo.lee on 2017/10/15.
 */

public interface IHttpService {
    /**
     * 设置请求地址
     * @param url
     */
    void setUrl(String url);

    /**
     * 执行请求
     */
    void excute();

    /**
     * 设置http的回调
     * @param httpListener
     */
    void setHttpListener(IHttpListener httpListener);

    /**
     * 设置请求参数
     * @param requestParams
     */
    void setRequestParams(byte[] requestParams);
}
