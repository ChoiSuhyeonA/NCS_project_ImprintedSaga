package com.hanshin.imprintedsaga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button main_MY, main_SHOP, main_SETTING, main_TRAINING;
    Button stageBtn[] = new Button[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_MY = findViewById(R.id.main_my_btn);
        main_SHOP = findViewById(R.id.main_shop_btn);
        main_SETTING = findViewById(R.id.main_setting_btn);
        main_TRAINING = findViewById(R.id.main_training_btn);

        main_MY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });
        main_SHOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent =  new Intent(getApplicationContext(), ShopActivity.class);
            startActivity(intent);
            }
        });
        main_SETTING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        main_TRAINING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        for(int i =0;i<9;i++){
            int k = getResources().getIdentifier("main_stage1_"+(i+1), "id", getPackageName());
            stageBtn[i] = findViewById( k );
        }

        stageBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapSelectDialog mapSelectDialog = new mapSelectDialog(MainActivity.this);
                mapSelectDialog.callFunction();
            }
        });

    }
}