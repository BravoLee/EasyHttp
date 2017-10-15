package com.bravo.easyhttp.interfaces;

import org.apache.http.HttpEntity;

/**
 * Created by bravo.lee on 2017/10/15.
 */

public interface IHttpListener {

    void onSuccess(HttpEntity httpEntity);

    void onFailed();
}
