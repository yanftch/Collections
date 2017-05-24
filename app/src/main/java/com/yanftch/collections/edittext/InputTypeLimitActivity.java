package com.yanftch.collections.edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.yanftch.collections.R;
import com.yanftch.collections.common.CommonUtils;

/**
 * Author : yanftch
 * Date   : 2017/5/24
 * Time   : 10:11
 * Desc   : 限制输入字符的EditText----此处是身份证的校验，也就是只能输入数字和X
 */

public class InputTypeLimitActivity extends AppCompatActivity {

    private EditText et_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_type_limit);
        et_main = (EditText) findViewById(R.id.et_main);
        CommonUtils.showIDKeyBoard(et_main);
    }
}
