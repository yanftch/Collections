package com.iven.widget.my_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iven.widget.R;

/**
 * Author : yanftch
 * Date : 2018/1/31
 * Time : 13:22
 * Desc : 通用Dialog封装
 */

public class CommonDialog {
    private Context mContext;
    private Dialog mDialog;
    private TextView mTitle;
    private TextView mContent;
    private TextView mLeft;
    private TextView mRight;
    private View.OnClickListener mLeftListener;
    private View.OnClickListener mRightListener;

    public static CommonDialog getInstance(Context context) {
        return new CommonDialog().build(context);
    }

    private CommonDialog build(Context context) {
        mContext = context;
        mDialog = new Dialog(context, R.style.dialog_background_dimEnabled);
        mDialog.setContentView(R.layout.common_dialog_view);
        mTitle = (TextView) mDialog.findViewById(R.id.dialog_textView_title);
        mLeft = (TextView) mDialog.findViewById(R.id.dialog_textView_left);
        mRight = (TextView) mDialog.findViewById(R.id.dialog_textView_right);
        mContent = (TextView) mDialog.findViewById(R.id.dialog_textView_content);
        click();
        resetDialogSize();
        return this;
    }

    private void click() {
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disMiss();
                if (mLeftListener != null) {
                    mLeftListener.onClick(v);
                }
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disMiss();
                if (mRightListener != null) {
                    mRightListener.onClick(v);
                }
            }
        });
    }

    private void disMiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 设置对话框外部是否可点击
     * 默认不可点击外部
     *
     * @param clickable false 外部不可点击且返回键禁止
     *                  true 可点击消失
     */
    public CommonDialog setOutsideOnClick(boolean clickable) {
        if (!clickable) {
            setCanceledOnTouchOutside();
        } else {
            mDialog.setCanceledOnTouchOutside(true);
        }
        return this;
    }

    /**
     * 显示一定要调用的方法
     */
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 禁用外部点击
     */
    private void setCanceledOnTouchOutside() {
        if (mDialog == null && !mDialog.isShowing()) {
            return;
        }
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
    }

    /**
     * 重置dialog的大小及位置
     */
    private void resetDialogSize() {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(p);
    }

    public CommonDialog setRightBtnListener(View.OnClickListener listener) {
        mRightListener = listener;
        return this;
    }

    public CommonDialog setRightBtnTextColor(int color) {
        mRight.setTextColor(mContent.getResources().getColor(color));
        return this;
    }

    public CommonDialog setRightBtnText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mRight.setText(text);
        }
        return this;
    }

    public CommonDialog setLeftBtnListener(View.OnClickListener listener) {
        mLeftListener = listener;
        return this;
    }

    public CommonDialog setLeftBtnTextColor(int color) {
        mLeft.setTextColor(mContent.getResources().getColor(color));
        return this;
    }

    public CommonDialog setLeftBtnText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mLeft.setText(text);
        }
        return this;
    }

    public CommonDialog setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            mContent.setText(content);
            mContent.setVisibility(View.VISIBLE);
        } else {
            mContent.setVisibility(View.GONE);
        }
        return this;
    }

    public CommonDialog setContentColor(int color) {
        mContent.setTextColor(mContent.getResources().getColor(color));
        return this;
    }

    public CommonDialog setTitleColor(int color) {
        mTitle.setTextColor(mContent.getResources().getColor(color));
        return this;
    }

    public CommonDialog setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
        } else {
            mTitle.setVisibility(View.GONE);
        }
        return this;
    }
}
