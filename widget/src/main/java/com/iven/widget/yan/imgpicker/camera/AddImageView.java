package com.iven.widget.yan.imgpicker.camera;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.iven.widget.yan.imgpicker.adapter.UpImageAdapter;
import com.iven.widget.yan.imgpicker.bean.ImageTag;
import com.iven.widget.yan.imgpicker.util.FuGridView;
import com.iven.widget.yan.imgpicker.util.ThreeButtonDialog;

import java.util.ArrayList;
import java.util.List;

public class AddImageView implements UpImageAdapter.OnItemClickListener,AdapterView.OnItemClickListener {
    private List<ImageTag> listImg = new ArrayList<ImageTag>();
    private FuGridView fgv_addImage;//图片
    private UpImageAdapter adapterPic = null;
    private List<String> uploadListImg = new ArrayList<String>();
    private Context context;
    ThreeButtonDialog buttonDialog;


    public AddImageView(Context context, FuGridView fgv_addImage, ThreeButtonDialog buttonDialog){
        this.context = context;
        this.fgv_addImage =  fgv_addImage;
        this.buttonDialog = buttonDialog;
        init();
    }
    private void init(){
        ImageTag tag2 = new ImageTag();
        tag2.setImageStr("");
        tag2.setImgTag(true);
        listImg.add(tag2);
        adapterPic= new UpImageAdapter(context,listImg);
        fgv_addImage.setAdapter(adapterPic);
        fgv_addImage.setOnItemClickListener(this);
        adapterPic.setClickListener(this);
    }

    /**
     * 添加图片 供外部调用
     * @param imgUrl
     */
    public void addImageStr(String imgUrl){
        uploadListImg.add(imgUrl);
        notifyImageList();
    }

    /**
     * 得到需要上传的图片
     * @return
     */
    public List<String> getUploadListImg(){
        return uploadListImg;
    }

    /**
     * 删除图片操作
     * @param view
     * @param position
     */
    @Override
    public void onImageItemClick(ImageView view, int position) {
        uploadListImg.remove(position);
        notifyImageList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listImg.size() > 10) {
            Toast.makeText(context,"最多只能上传30张图片！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (listImg.size() == 1) {
            buttonDialog.show();
        } else if (listImg.size() > 1) {
            if (position == (listImg.size() - 1)) {
                buttonDialog.show();
            } else {
                //查看大图
//                Utils.addImageListener(context,uploadListImg.get(position));
            }
        }
    }

    /**
     * 刷新adapter
     */
    private void notifyImageList() {
        if(adapterPic == null){
            return;
        }
        listImg.clear();
        for (int i = 0; i < uploadListImg.size(); i++) {
            ImageTag tag = new ImageTag();
            tag.setImageStr(uploadListImg.get(i));
            tag.setImgTag(false);
            listImg.add(tag);
        }
        ImageTag tag2 = new ImageTag();
        tag2.setImageStr("");
        tag2.setImgTag(true);
        listImg.add(tag2);
        adapterPic.notifyDataSetChanged();
    }
}
