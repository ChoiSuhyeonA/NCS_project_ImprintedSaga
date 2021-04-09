package com.hanshin.imprintedsaga;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ShopViewAdapter extends BaseAdapter {
    Context context;
    Integer[] shopListID ={
            R.drawable.char1a, R.drawable.char2a, R.drawable.char3a,
            R.drawable.wp1, R.drawable.wp2, R.drawable.wp3,
            R.drawable.pet1, R.drawable.pet2, R.drawable.pet3
    };

    public ShopViewAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return shopListID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(context);
        image.setLayoutParams(new GridView.LayoutParams(420, 650));
        image.setPadding(10,5,10,5);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        image.setImageResource(shopListID[position]);

        return image;
    }
}
