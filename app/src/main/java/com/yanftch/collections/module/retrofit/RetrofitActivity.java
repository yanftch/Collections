package com.yanftch.collections.module.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yanftch.collections.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = "dah_RetrofitActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mTextView = (TextView) findViewById(R.id.myTextView);
        findViewById(R.id.btnRe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal();
            }
        });
    }

    private void normal() {
        Retrofit retrofit = RetrofitService.createRetrofit("http://dat.dahuobaoxian.com/member/app/");
        ApiService apiService = retrofit.create(ApiService.class);
        Call<TestBean> call = apiService.getActivity();
        call.enqueue(new Callback<TestBean>() {
            @Override
            public void onResponse(Call<TestBean> call, Response<TestBean> response) {
                Log.e(TAG, "onResponse: response=" + response);
                if (null != response && null != response.body()) {
                    Log.e(TAG, "onResponse: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    public class MyCallBack<T> implements Callback {

        @Override
        public void onResponse(Call call, Response response) {

        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    }
}
