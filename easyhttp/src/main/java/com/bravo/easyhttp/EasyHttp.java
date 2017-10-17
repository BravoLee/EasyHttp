package com.bravo.easyhttp;

import com.bravo.easyhttp.interfaces.IDataListener;
import com.bravo.easyhttp.interfaces.IHttpListener;
import com.bravo.easyhttp.interfaces.IHttpService;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2017/10/17.
 */

public class EasyHttp {

    /**
     * 发送网络请求
     */
    public static <T, R> void sendRequest(T requestInfo, Class<R> resultClass, String url, IDataListener<R> dataListener) {

        RequestHolder<T> requestHolder = new RequestHolder<>();
        requestHolder.setUrl(url);
        requestHolder.setRequestInfo(requestInfo);

        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonHttpListener<>(resultClass, dataListener);

        requestHolder.setHttpService(httpService);
        requestHolder.setHttpListener(httpListener);

        HttpTask<T> httpTask = new HttpTask<>(requestHolder);
        try {
            ThreadPoolManager.getInstance().excute(new FutureTask<Object>(httpTask,null));
        } catch (InterruptedException e) {
            e.printStackTrace();
            dataListener.onFailed();
        }
    }

}
