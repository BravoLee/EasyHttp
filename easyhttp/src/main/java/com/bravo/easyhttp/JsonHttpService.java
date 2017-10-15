package com.bravo.easyhttp;

import android.net.http.HttpResponseCache;

import com.bravo.easyhttp.interfaces.IHttpListener;
import com.bravo.easyhttp.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by bravo.lee on 2017/10/15.
 */

class JsonHttpService implements IHttpService {

    private IHttpListener httpListener;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httpPost;
    private HttpResponseHandler responseHandler = new HttpResponseHandler();
    private byte[] requestParams;

    @Override
    public void setUrl(String url) {
        httpPost = new HttpPost(url);
    }

    @Override
    public void excute() {
        httpPost.setEntity(new ByteArrayEntity(requestParams));
        try {
            httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            httpListener.onFailed();
        }
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void setRequestParams(byte[] requestParams) {
        this.requestParams = requestParams;
    }

    class HttpResponseHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                httpListener.onSuccess(response.getEntity());
            } else {
                httpListener.onFailed();
            }
            return null;
        }
    }
}
