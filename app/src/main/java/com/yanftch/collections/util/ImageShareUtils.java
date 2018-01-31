package com.yanftch.collections.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Author : yanftch
 * Date : 2018/1/19
 * Time : 13:33
 * Desc : 分享合成的图片
 */

public class ImageShareUtils {
    /**
     * 将View变成Bitmap
     * 如果生成的Bitmap  过大   使用压缩方法压缩一下子...
     *
     * @param measure 最好传TRUE
     * @return Bitmap
     */
    public static Bitmap convertViewToBitmap(View view, boolean measure) {
        if (measure) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (null == bitmap) {
            bitmap = loadBitmapFromView(view);
        }
        return bitmap;
    }

    private static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        v.draw(c);
        return screenshot;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * 这样适合去传递二进制的图片数据，比如微信分享图片，要传入二进制数据过去，限制32kb之内。
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
//    private void share(Bitmap bitmap) {
//        UMImage mUMImage = new UMImage(InvitationPage.this, bitmap);
//        new ShareAction(InvitationPage.this).setPlatform(SHARE_MEDIA.WEIXIN).
//                withMedia(mUMImage).setCallback(umShareListener).share();
//    }
}
