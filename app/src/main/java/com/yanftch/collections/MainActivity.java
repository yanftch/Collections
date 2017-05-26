package com.yanftch.collections;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.yanftch.collections.circlemenu.CircleMenuLayoutActivity;
import com.yanftch.collections.convenientbanner.ConvenientBannerActivity;
import com.yanftch.collections.edittext.InputTypeLimitActivity;
import com.yanftch.collections.swipe.SwipeMenuActivity;
import com.yanftch.collections.xrecyclerview.XRecyclerViewActivity;

/**
 * Author : yanftch
 * Date   : 2017/5/23
 * Time   : 08:53
 * Desc   : 开发库集成、收集
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dah_MainActivity";
    int[] array = {12, 1, 3, 34, 121, 565};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFunction();
    }

    private void testFunction() {
        String s = "{\n" +
                "\t\t\t\"adsId\": \"d90bc66fd56f4f8da43fae20e82ddd56\",\n" +
                "\t\t\t\"adsTitle\": \"安邦长青树限时狂欢\",\n" +
                "\t\t\t\"adsContent\": null,\n" +
                "\t\t\t\"adsImageUrl\": \"https://s3.cn-north-1.amazonaws.com.cn/uploadfiles/activityFile/df55c7b298e64361b95df8607da47b13.png\",\n" +
                "\t\t\t\"adsUrl\": \"http://dh.bangcommunity.com:8080/abpartner/ab/pages/abcqs.html\",\n" +
                "\t\t\t\"adsStartUp\": \"1\",\n" +
                "\t\t\t\"adsShowType\": null,\n" +
                "\t\t\t\"adsState\": \"1\",\n" +
                "\t\t\t\"version\": null,\n" +
                "\t\t\t\"createTime\": 1494480853000,\n" +
                "\t\t\t\"startTime\": 1493370000000,\n" +
                "\t\t\t\"endTime\": 1496134800000,\n" +
                "\t\t\t\"adsType\": \"1\",\n" +
                "\t\t\t\"productNo\": null\n" +
                "\t\t}";

        Gson gson = new Gson();
        AdsInfoModel adsInfoModel = gson.fromJson(s, AdsInfoModel.class);
    }

    public void btnTurnAct(View view) {
        switch (view.getId()) {
            case R.id.btn_01://ListView侧滑菜单效果
                startActivity(SwipeMenuActivity.class);
                break;
            case R.id.btn_02://集成的Recyclerview
                startActivity(XRecyclerViewActivity.class);
                break;
            case R.id.btn_03://
                startActivity(com.yan.imgpicker.camera.MainActivity.class);
                break;
            case R.id.btn_04://
                startActivity(InputTypeLimitActivity.class);
                break;
            case R.id.btn_05:
                startActivity(CircleMenuLayoutActivity.class);
                break;
            case R.id.btn_06:
                startActivity(ConvenientBannerActivity.class);
                break;
            default:
                break;

        }
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
