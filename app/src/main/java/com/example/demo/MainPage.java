package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainPage extends AppCompatActivity {
    public String[] str = {"飲食日記","BMI計算","熱量消耗","個人資料"};
    private Intent intentA,personalPage;
    private Bundle bundleAccount;
    private String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        try
        {
            FileInputStream inStream = this.openFileInput("Account.txt");
            ByteArrayOutputStream streamb = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                streamb.write(buffer, 0, length);
            }
            streamb.close();
            inStream.close();
            account=streamb.toString();
            Toast.makeText(MainPage.this, "Loaded", Toast.LENGTH_LONG).show();
        } catch(
                FileNotFoundException e)

        {
            e.printStackTrace();
        }
        catch(
                IOException e)

        {
            return;
        }
        /*intentA=getIntent();
        if(intentA.getExtras().getString("ACCOUNT")!=null){
        account=intentA.getExtras().getString("ACCOUNT");}*/

        /*bmi = findViewById(R.id.timebtt);
        eating = findViewById(R.id.eatingbtt);
        calories = findViewById(R.id.timebtt);
        time = findViewById(R.id.timebtt);
        Button nextPageBtn1 = (Button) findViewById(R.id.caloriesbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.eatingbtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.bmibtt);


        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(MainPage.this , CaloriesCaculate.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(MainPage.this , record.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(MainPage.this , BMI.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(MainPage.this , Timing.class);
                startActivity(intent4);
            }
        });*/
        Button nextPageBtn4 = (Button) findViewById(R.id.timebtt);
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(MainPage.this , Timing.class);
                startActivity(intent4);
            }
        });
            ListView listview = (ListView) findViewById(R.id.listview);
        //activity_list_item_a
            ArrayAdapter adapter = new ArrayAdapter(this,
                    R.layout.activity_list_item_a,
                    str);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(onClickListView);
        }
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    Intent intent1 = new Intent();
                    intent1.setClass(MainPage.this , daterecord.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent();
                    intent2.setClass(MainPage.this , BMI.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent();
                    intent3.setClass(MainPage.this , CaloriesCaculate.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent();
                    personalPage=new Intent();
                    //Intent in=new Intent();
                    //in.setClass(Login.this,MainPage.class);
                    bundleAccount=new Bundle();
                    personalPage.setClass(MainPage.this,PersonPage.class);
                    bundleAccount.putString("ACCOUNT",account);
                    personalPage.putExtras(bundleAccount);
                    startActivity(personalPage);
                    break;
            }
        }
    };
}
