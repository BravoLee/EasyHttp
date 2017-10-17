package com.bravo.easyhttp.interfaces;

import org.apache.http.HttpEntity;

import java.io.InputStream;

/**
 * Created by bravo.lee on 2017/10/15.
 */

public interface IHttpListener {

    void onSuccess(HttpEntity entity);

    void onFailed();
}
