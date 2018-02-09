package com.yanftch.collections;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yanftch.applibrary.net.HttpManager;
import com.yanftch.applibrary.net.ICallBack;
import com.yanftch.applibrary.net.MyTestBean;
import com.yanftch.applibrary.net.RetrofitManager;
import com.yanftch.collections.module.anim.activity.AnimActivity;
import com.yanftch.collections.module.circlemenu.CircleMenuLayoutActivity;
import com.yanftch.collections.module.circleprogress.CircleProgressActivity;
import com.yanftch.collections.module.convenientbanner.ConvenientBannerActivity;
import com.yanftch.collections.module.design.activity.WeiBoFindPageActivity;
import com.yanftch.collections.module.dialog_and_countdown.DialogAndCountdownBtnActivity;
import com.yanftch.collections.module.edittext.InputTypeLimitActivity;
import com.yanftch.collections.module.five_photos.SelectFivePhotosActivity;
import com.yanftch.collections.module.popupwindow.PopupWindowActivity;
import com.yanftch.collections.module.pulltorefreshswipemenulistview.PulltoRefreshSwipemenuListviewActivity;
import com.yanftch.collections.module.shape.ShapeTextViewActivity;
import com.yanftch.collections.module.sign_calendar.CalendarActivity;
import com.yanftch.collections.module.source.SourceCodeActivity;
import com.yanftch.collections.module.swipe.SwipeMenuActivity;
import com.yanftch.collections.module.swiperecyclerview.EditTextAutoActivity;
import com.yanftch.collections.module.tab_viewpagerindicater.AllKindsOfViewPagerIndicaterActivity;
import com.yanftch.collections.module.xrecyclerview.XRecyclerViewActivity;
import com.yanftch.collections.test.TestActivity;

/**
 * Author : yanftch
 * Date   : 2017/5/23
 * Time   : 08:53
 * Desc   : 开发库集成、收集
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dah_MainActivity";
    int[] array = {12, 1, 3, 34, 121, 565};
    private EditText mEditText;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    private void testFunction() {

    }

    public void btnTurnAct(View view) {
        switch (view.getId()) {
            case R.id.btn_00://源码
                startActivity(SourceCodeActivity.class);
                break;
            case R.id.btn_01://ListView侧滑菜单效果
                startActivity(SwipeMenuActivity.class);
//                startActivity(CountDownViewActivity.class);
                break;
            case R.id.btn_02://集成的Recyclerview
                startActivity(XRecyclerViewActivity.class);
                break;
            case R.id.btn_03://
                startActivity(SelectFivePhotosActivity.class);
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
            case R.id.btn_07:
                startActivity(TestActivity.class);
                break;
            case R.id.btn_08:
                startActivity(EditTextAutoActivity.class);
                break;
            case R.id.btn_09:
                startActivity(AllKindsOfViewPagerIndicaterActivity.class);
                break;
            case R.id.btn_10:
                startActivity(PulltoRefreshSwipemenuListviewActivity.class);
                break;
            case R.id.btn_11:
                startActivity(DialogAndCountdownBtnActivity.class);
                break;
            case R.id.btn_12:
                startActivity(ShapeTextViewActivity.class);
                break;
            case R.id.btn_13:
                startActivity(CalendarActivity.class);
                break;
            case R.id.btn_14:
                startActivity(CircleProgressActivity.class);
                break;
            case R.id.btn_15:
                startActivity(PopupWindowActivity.class);
                break;
            case R.id.btn_16:
                startActivity(WeiBoFindPageActivity.class);
                break;
            case R.id.btn_17://跳转动画
                startActivity(AnimActivity.class);
                break;
            case R.id.btn_18:
                HttpManager.getInstance()
                        .with(MainActivity.this)
                        .setObservable(RetrofitManager.getService().getFengHome())
                        .setCallBack(true, new ICallBack<MyTestBean>() {
                            @Override
                            public void onSuccess(MyTestBean normalBean) {
                                Log.e(TAG, "onSuccess: " + normalBean);
                                Log.e(TAG, "onSuccess: " + normalBean.getBannerList());
                                for (int i = 0; i < normalBean.getBannerList().size(); i++) {
                                    Log.e(TAG, "onSuccess: " + normalBean.getBannerList().get(i).getAdTitle());
                                }
                            }

                            @Override
                            public void onError(String message) {
                                Toast.makeText(mContext, "message===" + message, Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "--------------------" + message);
                            }
                        });
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
