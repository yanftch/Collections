package com.iven.widget.yan.imgpicker.util;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class BasePhotoActivity extends AppCompatActivity {
    private Context context;
    private Date date;
    private String filePath = "/qkbx/photo/";
    private final int TAKE_PHOTO_REQUEST = 100;
    private final int PHOTO_PICKED_WITH_DATA = 200;
    private final int VIDEO_PICKED_WITH_DATA = 300;
    private Uri mTempFileUri;
    private Uri bitmapFileUri;
    private FileOutputStream fileOutputStream;
    private String photoFileName=null;//压缩后的图片的名字
    public ThreeButtonDialog buttonDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }
    public void initViews() {
        buttonDialog = new ThreeButtonDialog(this).setFirstButtonText("拍照")
                .setBtn1Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        doTakePhoto();
                    }
                }).setThecondButtonText("从手机相册选择")
                .setBtn2Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        pickPhoto();
                    }
                }).autoHide();

    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public int getLayoutId() {
//        return 0;
//    }


    /**
     * 图片拍照以后回调
     * @param photoPath
     */
    protected  void onPhotoTaked(String photoPath){

    }


    /**
     * 从相册选择照片
     */
    public void pickPhoto(){
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context,"没有找到照片",Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 调用系统 拍照
     * 解决三星手机拍照后无法获取数据，android 7.0手机拍照后获取数据适配。
     * 报错FileUriExposedException(SamSung SM-N9006 Android5.0也有这问题)
     */
    protected void doTakePhoto() {
        context = this;
        date = new Date();
        File mTmpFile = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager())!=null){
            try {
                mTmpFile = UpdateCapturedTempBitmap();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mTmpFile!=null){
                mTempFileUri = Uri.fromFile(mTmpFile);
//                Uri photoURI = Uri.fromFile(mTmpFile);
                // fix java.lang.SecurityException: Permission Denial: opening provider android.support.v4.content.FileProvider from ProcessRecord{42725078 24872:com.android.camera/u0a14} (pid=24872, uid=10014) that is not exported from uid 10310
                List<ResolveInfo> resInfoList= context.getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    context.grantUriPermission(packageName, mTempFileUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                // fix java.lang.SecurityException: Permission Denial: opening provider android.support.v4.content.FileProvider from ProcessRecord{42725078 24872:com.android.camera/u0a14} (pid=24872, uid=10014) that is not exported from uid 10310
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,mTempFileUri);
                this.startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST);
            }
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == TAKE_PHOTO_REQUEST) {
            if (mTempFileUri == null || Utils.isNull(mTempFileUri.getPath())) {
                return;
            }
            Bitmap bitmap = Utils.getSmallBitmap(mTempFileUri.getPath(), 480, 800);//找到压缩前的照片
            if (bitmap == null) {
                return;
            }
            File tempFile = UpdateCapturedTempBitmap();
            photoFileName = tempFile.getName(); //记录压缩后文件的名字
            bitmapFileUri = Uri.fromFile(tempFile);
            try {
                fileOutputStream = new FileOutputStream(bitmapFileUri.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);

            onPhotoTaked(bitmapFileUri.toString());
            String path = mTempFileUri.getPath();
            Log.i("TAG", "path:" + path);
            Log.i("TAG", "path12:" + mTempFileUri.getPath());
            //删除当前的照片
            File file = new File(path);
            try {
                file.delete();
            } catch (Exception e) {
                Log.i("TAG", "删除文件出错");
                e.printStackTrace();
            }

        }else if(requestCode == PHOTO_PICKED_WITH_DATA){

            if (Build.VERSION.SDK_INT >= 19){
                //4.4以上的系统使用这个方法处理照片
                handleImageOnKitKat(data);
            }else{
                //4.4以下的使用这个方法处理
                handleImageBeforeKitKat(data);
            }
            //---视频
        }
//        else if(requestCode == VIDEO_PICKED_WITH_DATA){
//            String result_value = data.getStringExtra("result");
//            if(result_value!= null){
//                Bitmap bitThumbnail = ThumbnailVideoImage.getVideoThumb(result_value);
//                String  imagePath = ThumbnailVideoImage.saveImage(bitThumbnail).getAbsolutePath();
//                onVideoTaked(result_value,bitThumbnail,imagePath);
//            }
//        }
    }


    /**
     * 创建临时图片文件
     * @return
     */
    private File UpdateCapturedTempBitmap() {
        String  mPhotoFileName= CalendarUtil.createInstance()
                .dataToString(CalendarUtil.FULLDATEFORMATNOCONN, new Date(), null, null, null);
        return FileUtil.createTypeTempFile(mPhotoFileName,filePath);
    }


    /**
     * 那么关于4.4以上的系统，对于返回的URI怎么进行解析呢？
     * 一般就是集中判断
     * 返回的URI是不是document类型，如果是那么就取出来document id 进行处理，如果不是就进行普通的URI处理
     * 如果URI的authority是media格式的话，document id 还需要再进行一次解析，通过字符串的分割取出真正的id，取出来的id用于构建新的URI和条件语句
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的uri，则通过document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的uri则使用普通的方式处理
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的URI则直接获取图片路径即可
            imagePath = uri.getPath();
        }
        onPhotoTaked(imagePath);//根据图片路径显示图片
    }

    /**
     * 通过传过来的条件，获取图片的真实路径
     * @param uri
     * @param selection
     * @return
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过URI和selection来获取真是的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }



    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        onPhotoTaked(imagePath);//根据图片路径显示图片
    }


}
