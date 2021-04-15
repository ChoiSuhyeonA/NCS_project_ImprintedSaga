package com.hanshin.imprintedsaga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton shop_closeBtn;
    ImageView shop_charIv, shop_petIv;
    TextView shop_pointTv;
    GridView shop_GridView;
    ShopViewAdapter adapter;

    //그리드뷰 이미지 타이틀
    String[] shopListTitle=  {
            "character1", "character2", "character3",
            "pet1", "pet2", "pet3",
            "weapon1", "weapon2", "weapon3"
    };
    //그리드뷰 이미지 저장위치
    Integer[] shopListImage ={
            R.drawable.question, R.drawable.question, R.drawable.question, R.drawable.question, R.drawable.question,
            R.drawable.question, R.drawable.question, R.drawable.question, R.drawable.question
    };
//    //그리드뷰 이미지 저장위치
//    Integer[] shopListImage ={
//            R.drawable.char1a, R.drawable.char2a, R.drawable.char3a,
//            R.drawable.pet1a, R.drawable.pet2a, R.drawable.pet3a,
//            R.drawable.wp1, R.drawable.wp2, R.drawable.wp3
//    };
    //그리드뷰 이미지 가격
    Integer[] shopPriceListID ={
            1000, 3000, 5000,
            500, 1000, 2000,
            800, 1500, 3000
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop_closeBtn = findViewById(R.id.shop_closeBtn);
        shop_charIv = findViewById(R.id.shop_charIv);
        shop_pointTv = findViewById(R.id.shop_pointTv);
        shop_GridView = findViewById(R.id.shop_GridView);

        //shop페이지 그리드뷰 및 어댑터 설정
        adapter = new ShopViewAdapter(this);
        shop_GridView.setAdapter(adapter);

        //상단의 왼쪽 닫기 버튼을 클릭할때 이벤트 작성 (메인페이지로 돌아가게 설정하기.)
        shop_closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),MyPageActivity.class);
            startActivity(intent);
            }
        });
        //그리드뷰에 각각 리스트를 클릭할 때 이벤트 작성. (대화상자 띄우고 구매할지 물어보기 설정하기)
        shop_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            View dialogView = View.inflate(MainActivity.this, R.layout.shop_list_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                AlertDialog dialog = dlg.create();

                ImageView img =  dialogView.findViewById(R.id.shopListImage);
                TextView tv = dialogView.findViewById(R.id.shopListPrice);
                img.setImageResource(shopListImage[position]);
                tv.setText(shopPriceListID[position].toString());
                dlg.setTitle(shopListTitle[position]);


                dlg.setIcon(R.drawable.ic_baseline_shopping_basket_24);
                dlg.setView(dialogView);
                //리스트에 아이템 구매버튼을 클릭했을 때, 이벤트 작성.
                dlg.setPositiveButton("구매", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"구매했습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }

}