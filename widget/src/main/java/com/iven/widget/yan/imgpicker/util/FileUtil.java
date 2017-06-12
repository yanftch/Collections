package com.iven.widget.yan.imgpicker.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/5/5.
 */

public class FileUtil {

    /**
     *
     * @param fileName
     * @param path
     * @return
     */
    public static File createTypeTempFile(String fileName, String path) {
        String storageStatus = Environment.getExternalStorageState();
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(storageStatus)) {

            file = new File( CommonUtil.getSDCardPath().concat(path), fileName);
            File folder = new File(CommonUtil.getSDCardPath().concat(path));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            try {
                if (file.exists()) {
                    file.delete();
                }

                if (!file.exists()) {
                    file = File.createTempFile(
                            fileName.substring(0, fileName.indexOf(".")),
                            fileName.substring(fileName.indexOf("."),
                                    fileName.length()), folder);
                }
            } catch (Exception e) {
            }
        }

        return file;
    }


}
