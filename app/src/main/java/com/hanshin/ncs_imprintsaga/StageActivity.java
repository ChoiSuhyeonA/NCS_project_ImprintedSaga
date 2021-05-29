package com.hanshin.ncs_imprintsaga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StageActivity extends AppCompatActivity {
    Button main_MY, main_SHOP, main_SETTING, main_TRAINING;
    ScrollView scrollview;
    Button stageBtn[] = new Button[9];

    //구글로그인 회원정보
    static String loginName ="";
    static String loginEmail = "";

    //파이어베이스 선언 변수
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //이전맵 스테이지 결과정보
    static ArrayList<String> preStage = new ArrayList<String>();
    int k;
    //쓰레드 변수
    final BackgroundThreads thread = new BackgroundThreads();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage);

        preStage.clear();

        main_MY = findViewById(R.id.main_my_btn);
        main_SHOP = findViewById(R.id.main_shop_btn);
        main_SETTING = findViewById(R.id.main_setting_btn);
        main_TRAINING = findViewById(R.id.main_training_btn);
        scrollview = findViewById(R.id.scrollview);

        //로그인한 회원정보를 가져오는 변수
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            //회원정보 이름
            loginName = signInAccount.getDisplayName();
            //회원정보 이메일
            loginEmail = signInAccount.getEmail();
            Toast.makeText(StageActivity.this, loginName+" "+loginEmail, Toast.LENGTH_SHORT).show();
        }

//        //맵을 실행하기 전에 이전맵을 클리어했는지 확인.
//        for(int i=0; i<9; i++){
//            db.collection(loginEmail).document("stage"+String.valueOf(i+1)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    DocumentSnapshot document = task.getResult();
//                    StageResult r = document.toObject(StageResult.class);
//                        preStage.add(r.getResult());
//                        //함수호출 (이전맵이 lose 이면 비활성화 상태로 변경)
//                        mapSetting();
//                }
//            });
//        }


        main_MY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });
        main_SHOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
            }
        });
        main_SETTING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        main_TRAINING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrainingActivity.class);
                startActivity(intent);
            }
        });

        for(int i =0;i<9;i++){
            int k = getResources().getIdentifier("main_stage1_"+(i+1), "id", getPackageName());
            stageBtn[i] = findViewById( k );
            final String stageNum = String.valueOf(i+1);
            stageBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapSelectDialog mapSelectDialog = new mapSelectDialog(StageActivity.this);
                    mapSelectDialog.callFunction(stageNum);
                }
            });
        }


    }

//    private void mapSetting() {
//        for(int j=1; j<preStage.size(); j++){
//                if( preStage.get(j-1).equals("LOSE")){
//                    stageBtn[j].setEnabled(false);
//                    stageBtn[j].setBackground(getDrawable(R.drawable.button_map_enable));
//                }
//        }
//    }
}
//대기 시간을 할 수 있도록 하는 클래스
class BackgroundThreads extends  Thread{
    public  void run(){
        try{
            Thread.sleep(100);
        }catch (Exception e){
        }
    }
}