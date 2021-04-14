package com.hanshin.imprintedsaga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MypageViewAdapter extends BaseAdapter {
    Context context;

    //개인페이지에 내가 갖고 있는 아이템 이미지 리스트
    Integer[] mypageListImage = {
            R.drawable.char3a,
            R.drawable.pet2a, R.drawable.pet3a,
            R.drawable.wp1, R.drawable.wp3
    };


    //그리드뷰 이미지 저장위치
    Integer[] shopListImage ={
            R.drawable.char1a, R.drawable.char2a, R.drawable.char3a,
            R.drawable.pet1a, R.drawable.pet2a, R.drawable.pet3a,
            R.drawable.wp1, R.drawable.wp2, R.drawable.wp3
    };

    //그리드뷰 이미지 타이틀
    String[] shpoListTitle=  {
            "character1", "character2", "character3",
            "pet1", "pet2", "pet3",
            "weapon1", "weapon2", "weapon3"
    };

    //그리드뷰 이미지 가격
    Integer[] shopPriceListID ={
            1000, 3000, 5000,
            500, 1000, 2000,
            800, 1500, 3000
    };

    public MypageViewAdapter(Context c) {
        context = c;
    }



    @Override
    public int getCount() {
        return mypageListImage.length;
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
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mypage_list_image, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.mypage_ListImage);


        image.setImageResource(mypageListImage[position]);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setPadding(10,0,10,0);

        return  convertView;
    }
}
