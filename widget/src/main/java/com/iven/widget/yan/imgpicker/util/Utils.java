package com.iven.widget.yan.imgpicker.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

/**
 * Created by Administrator on 2017/4/25.
 */

public class Utils {


//    /**
//     * 查看大图
//     * @param context
//     * @param img
//     * @param url
//     */
//    public static void addImageListener(final Context context, ImageView img, final String url){
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳到大图页面
//                Intent intent = new Intent(context, DisplayImageActivity.class);
//                intent.putExtra("path", url);
//                context.startActivity(intent);
//            }
//        });
//    }

//    /**
//     * 查看大图
//     * @param context
//     * @param url
//     */
//    public static void addImageListener(final Context context, final String url){
//        //跳到大图页面
//        Intent intent = new Intent(context, DisplayImageActivity.class);
//        intent.putExtra("path", url);
//        context.startActivity(intent);
//    }


    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }
        return s.trim()=="";
    }




    public static Bitmap getDiskBitmap(String pathString){
        Bitmap bitmap = null;
        try{
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e){
            // TODO: handle exception
        }
        return bitmap;
    }




    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath, float ww, float hh) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
//		float hh = 800f;//
//		float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        //   newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        bitmap = BitmapFactory.decodeFile(filePath, newOpts);
        return bitmap;
    }

}
