package com.hanshin.imprintedsaga;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MyPageActivity extends AppCompatActivity {
    ImageView mypage_charIv;
    ImageButton mypage_closeBtn;
    TextView mypage_charnameTv, mypage_levelTv, mypage_pointTv, mypage_stageTv;
    GridView mypage_gridview;
    ImageButton medal1, medal2, medal3, medal4, medal5;
    ProgressBar mypage_achievementPb, mypage_answerratePb;

    //그리드뷰 이미지 타이틀
    String[] shopListTitle=  {
            "character1", "character2", "character3",
            "pet1", "pet2", "pet3",
            "weapon1", "weapon2", "weapon3"
    };
//    //그리드뷰 이미지 저장위치
//    Integer[] shopListImage ={
//            R.drawable.char1, R.drawable.char2, R.drawable.char3,
//            R.drawable.pet1, R.drawable.pet2, R.drawable.pet3,
//            R.drawable.wp1, R.drawable.wp2, R.drawable.wp3
//    };
    //그리드뷰 이미지 가격
    Integer[] shopPriceListID ={
            1000, 3000, 5000,
            500, 1000, 2000,
            800, 1500, 3000
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        //위젯 연결
        mypage_charIv = findViewById(R.id.mypage_charIv);  // 캐릭터 이미지
        mypage_closeBtn = findViewById(R.id.mypage_closeBtn); // 창닫기 버튼
        mypage_charnameTv = findViewById(R.id.mypage_charnameTv); // 캐릭터 이름
        mypage_levelTv = findViewById(R.id.mypage_levelTv); // 레벨
        mypage_pointTv = findViewById(R.id.mypage_pointTv); // 포인트
        mypage_stageTv = findViewById(R.id.mypage_stageTv); // 스테이지
        mypage_gridview = findViewById(R.id.mypage_gridview);
        medal1 = findViewById(R.id.medal1); medal2 = findViewById(R.id.medal2);
        medal3 = findViewById(R.id.medal3); medal4 = findViewById(R.id.medal4);
        medal5 = findViewById(R.id.medal5);
        mypage_achievementPb = findViewById(R.id.mypage_achievementPb);
        mypage_answerratePb = findViewById(R.id.mypage_answerratePb);

        //개인페이지 그리드뷰 및 어댑터 설정
        Adapter adapter = new MypageViewAdapter(this);
        mypage_gridview.setAdapter(( MypageViewAdapter) adapter);


//        //메달숨기기
//        medal1.setVisibility(View.GONE);

        // 달성도 프로그레스바 설정
        mypage_achievementPb.setProgress(40);
        // 정답률 프로그레스바 설정
        mypage_answerratePb.setProgress(80);
    }
}
