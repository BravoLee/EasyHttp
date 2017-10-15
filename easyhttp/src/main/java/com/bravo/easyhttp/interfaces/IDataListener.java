package com.bravo.easyhttp.interfaces;

/**
 * Created by bravo.lee on 2017/10/15.
 */

public interface IDataListener<R> {
    void onSuccess(R r);
    void onFailed();
}
