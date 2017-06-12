package com.iven.widget.yan.imgpicker.adapter;


import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.iven.widget.R;
import com.iven.widget.yan.imgpicker.bean.ImageTag;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.List;

/***
 * 
 * 
 * 选择多个图片adapter
 * 
 */

public class UpImageAdapter extends BaseAd<ImageTag> {
    private Context context;

    private OnItemClickListener clickListener;
    public UpImageAdapter(Context context, List<ImageTag> list) {
		setActivity(context, list);
        this.context = context;
        
    }


	public OnItemClickListener getClickListener() {
		return clickListener;
	}
	public void setClickListener(OnItemClickListener clickListener) {
		this.clickListener = clickListener;
	}
	@Override
	protected View setConvertView(View convertView, final int position) {
    	ItemView itemView = null;
		if(convertView  ==null){
			itemView = new ItemView();
			convertView = mInflater.inflate(R.layout.list_item_up_pic, null);
			itemView.iv_pic = (ImageView)convertView.findViewById(R.id.iv_pic);
			itemView.iv_delete = (ImageView)convertView.findViewById(R.id.iv_delete);
			convertView.setTag(itemView);
		}else{
			itemView = (ItemView)convertView.getTag();
		}
		final ImageTag model = mList.get(position);
		boolean tag = model.isImgTag();

		 if(!tag){
			 itemView.iv_delete.setVisibility(View.VISIBLE);

			 //5.0以后手机得到图片路径判断==/storage/emulated/0/qkbx
			 if(!model.getImageStr().contains("//")){
				 itemView.iv_pic.setImageURI(Uri.fromFile(new File(model.getImageStr())));
			 }else{
				 ImageLoader.getInstance().displayImage(model.getImageStr(), itemView.iv_pic);
			 }
		 }else{
			 itemView.iv_delete.setVisibility(View.GONE);//设置删除按钮是否显示
			 String imgstr = "drawable://" + R.drawable.add_photo_pic_add;
			 ImageLoader.getInstance().displayImage(imgstr,itemView.iv_pic);

		 }
		 itemView.iv_delete.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int pos = position;
					if(clickListener != null){
						clickListener.onImageItemClick((ImageView)v, pos);
					}
					
				}
			});
		return convertView;
	}


    private class ItemView{
        ImageView iv_pic,iv_delete;
        
    }

    
    public interface OnItemClickListener{
		public void onImageItemClick(ImageView view, int position);
	} 
    
    

	
}