package com.iven.widget.yan.imgpicker.camera;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.iven.widget.R;
import com.iven.widget.yan.imgpicker.util.BasePhotoActivity;
import com.iven.widget.yan.imgpicker.util.FuGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends BasePhotoActivity {

    private FuGridView gridView;
    private Context context;
    private AddImageView addImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_t);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        init();
    }

    private void init() {
        context=this;
        gridView= (FuGridView) findViewById(R.id.fgv_addImage);
        addImageView = new AddImageView(this,gridView,buttonDialog);
    }

    @Override
    protected void onPhotoTaked(String photoPath) {
        super.onPhotoTaked(photoPath);
        addImageView.addImageStr(photoPath);
    }
}
