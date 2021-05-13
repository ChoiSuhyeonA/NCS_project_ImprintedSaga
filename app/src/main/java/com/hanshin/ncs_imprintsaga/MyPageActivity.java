package com.hanshin.ncs_imprintsaga;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPageActivity extends AppCompatActivity {
    ArrayList<String>  haveItem= new ArrayList<String>();
    //아이템 장착 여부
    int[] itemCheck={0,0,0,0,0,0,0,0,0};

    ImageView mypage_charIv;
    ImageButton mypage_closeBtn;
    TextView mypage_levelTv;
    TextView mypage_pointTv;
    TextView mypage_stageTv;
    TextView mypage_hpTv;
    TextView mypage_atkTv;
    TextView mypage_dfdTv;
    TextView mypage_skillTv ;
    GridView mypage_gridview;
    ImageButton medal1, medal2, medal3, medal4, medal5;
    ProgressBar mypage_achievementPb, mypage_answerratePb;

    MypageViewAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MyPage_Item item;
    //현재 갖고있는 아이템을 보여줌


    //구글로그인 회원정보
    String loginName ="";
    String loginEmail = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);


        //위젯 연결
        mypage_charIv = findViewById(R.id.mypage_charIv);  // 캐릭터 이미지
        mypage_closeBtn = findViewById(R.id.mypage_closeBtn); // 창닫기 버튼
        //데이터 위젯
        mypage_levelTv = findViewById(R.id.mypage_levelTv); // 레벨
        mypage_pointTv = findViewById(R.id.mypage_pointTv); // 포인트
        mypage_stageTv = findViewById(R.id.mypage_stageTv); // 스테이지
        mypage_atkTv = findViewById(R.id.mypage_atkTv); //공격력
        mypage_dfdTv = findViewById(R.id.mypage_dfdTv); //방어력
        mypage_skillTv = findViewById(R.id.mypage_skillTv); //능력

        mypage_gridview = findViewById(R.id.mypage_gridview); //그리드뷰
        medal1 = findViewById(R.id.medal1); medal2 = findViewById(R.id.medal2); //메달
        medal3 = findViewById(R.id.medal3); medal4 = findViewById(R.id.medal4);
        medal5 = findViewById(R.id.medal5);
        mypage_achievementPb = findViewById(R.id.mypage_achievementPb); //달성도
        mypage_answerratePb = findViewById(R.id.mypage_answerratePb);//정답률

        //그리드뷰 이미지 타이틀
        String[] shopListTitle=  {
                "item1", "item2", "item3",
                "item4", "item5", "item6",
                "item7", "item8", "item9"
        };
        //그리드뷰 이미지 저장위치
        final Integer[] shopListImage ={
                R.drawable.item1, R.drawable.item2, R.drawable.item3,
                R.drawable.item4, R.drawable.item5, R.drawable.item6,
                R.drawable.item7, R.drawable.item8, R.drawable.item9
        };

        //그리드뷰 이미지 가격
        Integer[] shopListPrice ={
                1000, 3000, 5000,
                500, 1000, 2000,
                800, 1500, 3000
        };


        //그리드뷰 대화상자 아이템능력
        final String[] shopListAbility = {
                //기본 HP = 100, 기본 공격력 = 10, 기본 방어력 = 0, 능력 = x
                "방어 10 증가", "방어 15 증가 ", "방어 20 증가 ",
                "공격 10 증가", "공격 20 증가", "공격 30 증가",
                "타이머 5초 증가", "힌트 1회 제공", "실드 2회"
        };



        //로그인한 회원정보를 가져오는 변수
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            //회원정보 이름
            loginName = signInAccount.getDisplayName();
            //회원정보 이메일
            loginEmail = signInAccount.getEmail();
            Toast.makeText(MyPageActivity.this, loginName+" "+loginEmail, Toast.LENGTH_SHORT).show();
        }


        //개인페이지 그리드뷰 및 어댑터 설정
          adapter = new MypageViewAdapter(this, haveItem);

        //현재 갖고 있는 아이템 리스트 정보 가져오기.
        db.collection(loginEmail).document("itemlist"). get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                Mypage_HavingItem havingItem = document.toObject(Mypage_HavingItem.class);

                //현재 갖고 있는 아이템의 유무를 리스트에 등록시킨다.
                haveItem.add(havingItem.getItem1());
                haveItem.add(havingItem.getItem2());
                haveItem.add(havingItem.getItem3());
                haveItem.add(havingItem.getItem4());
                haveItem.add(havingItem.getItem5());
                haveItem.add(havingItem.getItem6());
                haveItem.add(havingItem.getItem7());
                haveItem.add(havingItem.getItem8());
                haveItem.add(havingItem.getItem9());
                adapter.notifyDataSetChanged();
            }
        });

        mypage_gridview.setAdapter(( MypageViewAdapter) adapter);

        //그리드뷰에서 아이템을 클릭할 때, 이벤트 작성
        mypage_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final View  dialogView =(LinearLayout)  View.inflate(com.hanshin.ncs_imprintsaga.MyPageActivity.this,  R.layout.mypage_dialog_list, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(com.hanshin.ncs_imprintsaga.MyPageActivity.this);
                final int pos = position;
                ImageView image =dialogView.findViewById(R.id.mypage_dialogImage);
                image.setImageResource(shopListImage[position]);
                TextView text1 = dialogView.findViewById(R.id.mypage_dialogAbilty);
                text1.setText(shopListAbility[position]);
                TextView text2 = dialogView.findViewById(R.id.mypage_dialogText);
                if(haveItem.get(position).equals("0")){
                    text2.setText("아이템 보유 x");
                }else{
                    text2.setText("아이템 보유 O");
                }

                dlg.setTitle("아이템을 장착하시겠습니까?");
                dlg.setView(dialogView);
                dlg.setPositiveButton("장착", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //아이템이 없을 경우 실행
                        if(haveItem.get(pos).equals("0")){
                            Toast.makeText(getApplicationContext(),"아이템을 보유하지 않아 장착할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        }else{
                           switch (pos){
                               case 0:
                                   if(itemCheck[0]==0){
                                       item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())+10));
                                       itemCheck[0] =1;
                                    }

                                   mypage_dfdTv.setText(item.getDfd());
                                   Map<String, Object> data = new HashMap<>();
                                   data.put("dfd" , item.getDfd());
                                   db.collection(loginEmail).document("item").update(data);
                                   //이미지 업데이트 하기
                                   //아이템중에서 2개이상 선택못하게
                                   //체크리스트 오류 체크하기
                                   break;
                               case 1:
                                   if(itemCheck[1]==0){
                                       item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())+15));
                                       itemCheck[1] =1;
                                   }

                                   mypage_dfdTv.setText(item.getDfd());
                                   Map<String, Object> data2 = new HashMap<>();
                                   data2.put("dfd" , item.getDfd());
                                   db.collection(loginEmail).document("item").update(data2);
                                   break;
                               case 2:
                                   if(itemCheck[2]==0){
                                       item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())+20));
                                       itemCheck[2] =1;
                                   }

                                   mypage_dfdTv.setText(item.getDfd());
                                   Map<String, Object> data3 = new HashMap<>();
                                   data3.put("dfd" , item.getDfd());
                                   db.collection(loginEmail).document("item").update(data3);
                                   break;
                           }
                        }
                    }
                });
                dlg.setNegativeButton("장착해제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(haveItem.get(pos).equals("0")){
                            Toast.makeText(getApplicationContext(),"아이템을 보유하지 않아 장착해제 할 수 없습니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            switch (pos){
                                case 0:
                                    if(itemCheck[0]==1){
                                        item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())-10));
                                        itemCheck[0]=0;
                                    }
                                    mypage_dfdTv.setText(item.getDfd());
                                    Map<String, Object> data = new HashMap<>();
                                    data.put("dfd" , item.getDfd());
                                    db.collection(loginEmail).document("item").update(data);
                                    //이미지 업데이트 하기
                                    break;
                                case 1:
                                    if(itemCheck[1]==1){
                                        item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())-15));
                                        itemCheck[1] =0;
                                    }
                                    mypage_dfdTv.setText(item.getDfd());
                                    Map<String, Object> data2 = new HashMap<>();
                                    data2.put("dfd" , item.getDfd());
                                    db.collection(loginEmail).document("item").update(data2);
                                    break;
                                case 2:
                                    if(itemCheck[2]==1){
                                        item.setDfd(String.valueOf(Integer.parseInt(item.getDfd())-20));
                                        itemCheck[2] =0;
                                    }
                                    mypage_dfdTv.setText(item.getDfd());
                                    Map<String, Object> data3 = new HashMap<>();
                                    data3.put("dfd" , item.getDfd());
                                    db.collection(loginEmail).document("item").update(data3);
                                    break;
                            }
                        }
                    }
                });
                dlg.setNeutralButton("취소",null);

                dlg.show();

            }
        });

        //파이어베이스 데이터 정보가져오기
         db.collection(loginEmail).document("item"). get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 DocumentSnapshot document = task.getResult();
                 //객체(MYPage_Item)에 뿌려주기
                 item = document.toObject(MyPage_Item.class);
                 //파이어베이스에서 데이터 가져와서, 각 위젯에 데이터 설정해주기.
                //클래스 객체 필드와 파이어베이스 필드명 같아야함 (틀리면 값을 못가져온다)
                 mypage_levelTv.setText(item.getLevel());
                 mypage_pointTv.setText(item.getPoint());
                 mypage_stageTv.setText(item.getStage());
                 mypage_atkTv.setText(item.getAtk());
                 mypage_dfdTv.setText(item.getDfd());
                 mypage_skillTv.setText(item.getSkill());
             }
         });





       //메달숨기기
//        medal1.setVisibility(View.GONE);

        // 달성도 프로그레스바 설정
        mypage_achievementPb.setProgress(40);
        // 정답률 프로그레스바 설정
        mypage_answerratePb.setProgress(80);

        //창닫기 버튼 클릭시 메인페이지로 이동하기
       mypage_closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),StageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
