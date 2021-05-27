package com.hanshin.ncs_imprintsaga;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Battle_FinishDialog {
    private Context context;

    //구글로그인 회원정보
    String loginName ="";
    String loginEmail = "";
    //파이어베이스
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    int correct;
    int total;
    int rank;
    double answerRate;

    public Battle_FinishDialog(Context context) {
        this.context = context;
    }

    public void callFunction(boolean b , int correctNum, int totalNum) {

        loginName = BattleActivity.loginName;
        loginEmail =BattleActivity.loginEmail;

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.stage_end_info);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
         correct = correctNum;
         total = totalNum;
         answerRate = ((double)correct/total)*100; // 정답률

        if(answerRate<40)
            rank = 1;
        else if(answerRate<80)
            rank = 2;
        else
            rank = 3;

        final TextView resultTv = (TextView) dlg.findViewById(R.id.resultTv_onBattle);
        if(b)
            resultTv.setText("WIN");
        else
            resultTv.setText("LOSE");

        final RatingBar rankRb = (RatingBar) dlg.findViewById(R.id.rankRb_onBattle);
        rankRb.setRating(rank);

        final TextView rateTv = (TextView) dlg.findViewById(R.id.rateTv_onBattle);
        rateTv.setText(rateTv.getText() + String.valueOf(correctNum) + "/" + String.valueOf(totalNum));

        //파이어베이스 데이터 업데이트
        Map<String, Object> data = new HashMap<>();
        data.put("correctNum", String.valueOf(correct));
        data.put("totalNum", String.valueOf(total));
        data.put("answerRate", String.valueOf(answerRate));
        data.put("rank", String.valueOf(rank));
        data.put("result", resultTv.getText());

        final String stageNum = BattleActivity.num;

        db.collection(loginEmail).document("stage"+stageNum).update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //업데이트 완료
                Log.d("TAG", stageNum + "|" +correct + "|" + total + "|"
                        + answerRate + "|" + rank + "|" + rateTv.getText() +"\n");
            }
        });



        final Button endBtn = (Button) dlg.findViewById(R.id.endBtn_onBattle);
        final BattleActivity Ba = (BattleActivity)BattleActivity.BattlePageActivity;
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ba.finish();
            }
        });

        final Button retryBtn = (Button) dlg.findViewById(R.id.retryBtn_onBattle);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ba.finish();
                Intent intent=new Intent();
                intent.setClass(Ba, Ba.getClass());
                Ba.startActivity(intent);
            }
        });
    }
}
