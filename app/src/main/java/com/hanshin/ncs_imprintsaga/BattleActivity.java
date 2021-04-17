package com.hanshin.ncs_imprintsaga;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.xmlpull.v1.XmlPullParserFactory;

public class BattleActivity extends Activity {

    CsvData[] dataArray = new CsvData[10];
    TextView textView;

    public void readDataFromCsv(InputStream input) throws IOException {

        InputStreamReader streamReader = null;
        streamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
        CSVReader reader = new CSVReader(streamReader); // 1. CSVReader 생성

        String[] nextLine;

        int j = 0;

        while ((nextLine = reader.readNext()) != null) {   // 2. CSV 파일을 한줄씩 읽음
            Log.d("TAG", nextLine[0] + "|" + nextLine[1] + "|"
                    + nextLine[2] + "|" + nextLine[3] +"\n");
            dataArray[j] = new CsvData();
            dataArray[j].number = nextLine[0];
            dataArray[j].words = nextLine[1];
            dataArray[j].meaning = nextLine[2];
            dataArray[j].grade = nextLine[3];
            j++;
            if (j == 10)
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);

        InputStream input = getResources().openRawResource(R.raw.words);

        try {
            readDataFromCsv(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView = findViewById(R.id.textTest);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataArray.length; i++ )
            sb.append(dataArray[i].words + "\n");

        textView.setText(sb.toString());
    }

    public class CsvData{
        String number;
        String words;
        String meaning;
        String grade;
    }

}