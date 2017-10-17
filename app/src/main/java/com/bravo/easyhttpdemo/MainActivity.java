package com.bravo.easyhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bravo.easyhttp.EasyHttp;
import com.bravo.easyhttp.interfaces.IDataListener;
import com.bravo.easyhttpdemo.bean.RequestParams;
import com.bravo.easyhttpdemo.bean.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final TextView tvResult = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<User> resultClass = User.class;
                String url = "http://192.168.0.110:8080/json/user.json";
                RequestParams requestParams = new RequestParams();
                EasyHttp.sendRequest(requestParams, resultClass, url, new IDataListener<User>() {

                    @Override
                    public void onSuccess(User user) {
                        tvResult.setText(getResources().getString(R.string.result) + " : " + user.toString());
                    }

                    @Override
                    public void onFailed() {
                        tvResult.setText(getResources().getString(R.string.result) + " :  onFailed !");
                    }
                });
            }
        });
    }
}
