package com.hanshin.ncs_imprintsaga;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class mapSelectDialog {
    private Context context;

    //구글로그인 회원정보
    String loginName ="";
    String loginEmail = "";
    //파이어베이스
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    //파이어베이스로부터 받을 정보데이타 변수클래스
    StageResult stageData = new StageResult();

    public mapSelectDialog(Context context) {
        this.context = context;
    }

    public void callFunction(String stageNum) {

        loginName = StageActivity.loginName;
        loginEmail =StageActivity.loginEmail;


        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.stage_start_info);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final TextView stageTv = (TextView) dlg.findViewById(R.id.stageTv);
        final RatingBar rankRb = (RatingBar) dlg.findViewById(R.id.rankRb);
        final TextView levelTv = (TextView) dlg.findViewById(R.id.levelTb);
        final TextView rateTv = (TextView) dlg.findViewById(R.id.rateTv);
        final Button selectBtn = (Button) dlg.findViewById(R.id.selectBtn);

        db.collection(loginEmail).document("stage"+stageNum).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                stageData = document.toObject(StageResult.class);
                int rank = Integer.parseInt(stageData.rank);
                String total = stageData.totalNum;
                String correct = stageData.correctNum;
                
                rankRb.setRating(rank);
                rateTv.setText("정답률 : "+ correct+" / "+ total );
            }
        });

        final String stage = stageTv.getText()+stageNum;
        stageTv.setText(stage);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), BattleActivity.class);
                intent.putExtra("stageNum",stage);
                context.startActivity(intent);
                dlg.cancel();
            }
        });
    }
}
