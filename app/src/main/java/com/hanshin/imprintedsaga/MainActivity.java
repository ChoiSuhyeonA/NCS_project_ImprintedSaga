package com.hanshin.imprintedsaga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton shop_closeBtn;
    ImageView shop_charIv, shop_petIv;
    TextView shop_pointTv;
    GridView shop_GridView;
    ShopViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop_closeBtn = findViewById(R.id.shop_closeBtn);
        shop_charIv = findViewById(R.id.shop_charIv);
        shop_petIv = findViewById(R.id.shop_petIv);
        shop_pointTv = findViewById(R.id.shop_pointTv);
        shop_GridView = findViewById(R.id.shop_GridView);

        adapter = new ShopViewAdapter(this);
        shop_GridView.setAdapter(adapter);


    }
}