package com.yanftch.collections.module.dialog_and_countdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iven.widget.view.common_dialog.DialogUtils;
import com.iven.widget.countdownview.CountdownButton;
import com.yanftch.collections.R;

/**
 * Author : yanftch
 * Date   : 2017/6/20
 * Time   : 10:55
 * Desc   : 倒计时按钮封装   通用Dialog封装
 */

public class DialogAndCountdownBtnActivity extends AppCompatActivity {

    private CountdownButton countdown_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_and_countdown_btn);
        initButton();
        initDialog();
    }

    private void initDialog() {
        findViewById(R.id.btn_showdialog_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        findViewById(R.id.btn_showdialog_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOneBtnDialog();
            }
        });
    }

    private void showOneBtnDialog() {
        final DialogUtils dialogUtils = new DialogUtils(this, false);
        dialogUtils.setDialogVerify(this, "温馨提示", "告诉你，今天北京有大暴雨哦~", null, "知道了", null, false, null, new DialogUtils.DialogClickListener() {
            @Override
            public void leftEvent() {

            }

            @Override
            public void rightEvent() {
                dialogUtils.closeDilog();
            }
        });
        dialogUtils.showStandardDialog();
    }

    private void showDialog() {
        final DialogUtils dialogUtils = new DialogUtils(this, false);
        //控制两个Button的颜色
//        dialogUtils.setButtonTextColor(this.getResources().getColor(R.color.black),this.getResources().getColor(R.color.black));

        dialogUtils.setDialogVerify(this, "TITLE", "此处是要显示的内容主体", "取消", "确定", null, false, null, new DialogUtils.DialogClickListener() {
            @Override
            public void leftEvent() {
                Toast.makeText(DialogAndCountdownBtnActivity.this, "左边点击", Toast.LENGTH_SHORT).show();
                dialogUtils.closeDilog();
            }

            @Override
            public void rightEvent() {
                Toast.makeText(DialogAndCountdownBtnActivity.this, "右边边点击", Toast.LENGTH_SHORT).show();
                dialogUtils.closeDilog();
            }
        });
        dialogUtils.showStandardDialog();
    }

    /**
     * 倒计时Button
     */
    private void initButton() {
        countdown_btn = (CountdownButton) findViewById(R.id.countdown_btn);
        countdown_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdown_btn.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != countdown_btn) {
            countdown_btn.cancle();
        }
    }
}
