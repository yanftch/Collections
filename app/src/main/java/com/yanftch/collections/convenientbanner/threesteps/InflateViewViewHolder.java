package com.yanftch.collections.convenientbanner.threesteps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iven.widget.bigkoo.convenientbanner.holder.Holder;
import com.yanftch.collections.MainActivity;
import com.yanftch.collections.R;

/**
 * User : yanftch
 * Date : 2017/5/26
 * Time : 09:04
 * Desc :
 */

public class InflateViewViewHolder implements Holder<BannerBean> {
    private Button btn_into;
    private RelativeLayout rl_background;

    public interface onButtonClickListener {
        void onClickEvent();
    }


    public InflateViewViewHolder() {
    }

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_three_item, null);
        btn_into = (Button) view.findViewById(R.id.btn_into);
        rl_background = (RelativeLayout) view.findViewById(R.id.rl_background);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, int position, final BannerBean data) {
        btn_into.setText(data.getName());
        if (data.getId() == 0) {
            rl_background.setBackgroundResource(R.drawable.ic_item_1);
        } else if (data.getId() == 1) {
            rl_background.setBackgroundResource(R.drawable.ic_item_2);
        } else if (data.getId() == 2) {
            rl_background.setBackgroundResource(R.drawable.ic_item_3);
        } else {
            rl_background.setBackgroundResource(R.drawable.ic_item_4);
        }
        btn_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "do===" + data.getDetailUrl(), Toast.LENGTH_SHORT).show();
                if (data.getId() == 2){
                    context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
                }
            }
        });
    }

}
