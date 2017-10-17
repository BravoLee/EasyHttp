package com.bravo.easyhttp;

import android.net.http.HttpResponseCache;
import android.util.Log;

import com.bravo.easyhttp.interfaces.IHttpListener;
import com.bravo.easyhttp.interfaces.IHttpService;

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
//        httpPost = new HttpPost(url);
        HttpGet httpGet = new HttpGet(url);
        Log.e(TAG, "开始发送网络请求   A  ");
//        httpPost.setEntity(new ByteArrayEntity(requestParams));

        try {
            Log.e(TAG, "开始发送网络请求  B  ");
            httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            httpListener.onFailed();
        }


//        URL mUrl = null;
//        HttpURLConnection urlConnection = null;
//        try {
//
//            mUrl = new URL(url);
//            urlConnection = (HttpURLConnection) mUrl.openConnection();
//
//            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
//            // http正文内，因此需要设为true, 默认情况下是false;
//            urlConnection.setDoOutput(true);
//
//            // 设置是否从httpUrlConnection读入，默认情况下是true;
//            urlConnection.setDoInput(true);
//
//            // Post 请求不能使用缓存
//            urlConnection.setUseCaches(false);
//
//            // 设定传送的内容类型是可序列化的java对象
//            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
////            urlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
//
//            // 设定请求的方法为"POST"，默认是GET
////            urlConnection.setRequestMethod("POST");
//
//            // 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
//            urlConnection.connect();
//            Log.e(TAG, "开始发送网络请求   A  ");
//            int statusCode = urlConnection.getResponseCode();
//
//            if (statusCode == 200) {
//                Log.e(TAG, "statusCode  == 200");
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//                httpListener.onSuccess(in);
//            }else{
//                Log.e(TAG, "statusCode  != 200");
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            httpListener.onFailed();
//        } catch (IOException e) {
//            e.printStackTrace();
//            httpListener.onFailed();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//        }
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
            Log.e(TAG, "服务器响应。");
            if (statusCode == 200) {
                Log.e(TAG, "服务器响应。200");
                httpListener.onSuccess(response.getEntity());
            } else {
                httpListener.onFailed();
            }
            return null;
        }
    }
}
