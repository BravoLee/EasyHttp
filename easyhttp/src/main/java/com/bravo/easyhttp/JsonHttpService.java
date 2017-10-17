package com.bravo.easyhttp;

import android.net.http.HttpResponseCache;
import android.util.Log;

import com.bravo.easyhttp.interfaces.IHttpListener;
import com.bravo.easyhttp.interfaces.IHttpService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bravo.lee on 2017/10/15.
 */

class JsonHttpService implements IHttpService {

    private static final String TAG = "bravo";
    private IHttpListener httpListener;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httpPost;
    private HttpResponseHandler responseHandler = new HttpResponseHandler();
    private byte[] requestParams;
    private String url;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void excute() {
        httpPost = new HttpPost(url);
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
                HttpEntity entity = response.getEntity();
                httpListener.onSuccess(entity);
            } else {
                httpListener.onFailed();
            }
            return null;
        }
    }
}
