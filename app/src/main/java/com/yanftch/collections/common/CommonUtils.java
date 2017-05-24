package com.yanftch.collections.common;

import android.text.method.NumberKeyListener;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * User : yanftch
 * Date : 2017/5/23
 * Time : 09:10
 * Desc : 公共工具类，大杂烩
 */

public class CommonUtils {
    public static char[] char_number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] char_l_word = {'a', 'b'};
    public static char[] char_u_word = {'A', 'B'};

    /**
     * EditText第一次调起数字键盘、后续可以随意切换任意键盘，但是输入的字符只能是：0-9、X、x(也就是只能输入身份证号)
     *
     * @param editText 要输入身份证号的EditText
     */
    public static void showIDKeyBoard(EditText editText) {
        editText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'x'};
                return chars;
            }

            /**
             * 0：无键盘,键盘弹不出来
             * 1：英文键盘
             * 2：模拟键盘
             * 3：数字键盘
             *
             * @return
             */
            @Override
            public int getInputType() {
                return 3;
            }
        });
    }

    /**
     * 对EditText只能输入指定的字符
     *
     * @param editText 待操作的EditText
     * @param chars    允许输入的字符
     */
    public static void showKeyBoardAllowSpecialChar(EditText editText, final char[] chars) {
        editText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                return chars;
            }

            /**
             * 0：无键盘,键盘弹不出来
             * 1：英文键盘
             * 2：模拟键盘
             * 3：数字键盘
             *
             * @return
             */
            @Override
            public int getInputType() {
                return 3;
            }
        });
    }

    /**
     * 列表数据
     *
     * @return
     */
    public static List<Bean.T1348648517839Bean> getDataList() {
        ArrayList<Bean> list = new ArrayList<>();
        Gson gson = new Gson();
        Bean bean = gson.fromJson(Constant.NEWS, Bean.class);
        List<Bean.T1348648517839Bean> t1348648517839 = bean.getT1348648517839();
        return t1348648517839;
    }
}
