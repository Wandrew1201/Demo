package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Button btn =(Button)findViewById(R.id.btt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText h=(EditText)findViewById(R.id.editHigh);
                EditText w=(EditText)findViewById(R.id.editWeight);
                if(!("".equals(h.getText().toString())||"".equals(w.getText().toString()))){
                    Intent intent=new Intent();
                    intent.setClass(BMI.this,BMIresult.class);
                    double high=Double.parseDouble(h.getText().toString());
                    double weight=Double.parseDouble(w.getText().toString());
                    double ans;
                    high=high/100;
                    high=high*high;
                    ans=weight/high;
                    intent.putExtra("ans",ans);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(BMI.this,"Error!",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button nextPageBtn1 = (Button) findViewById(R.id.mainbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.timebtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.recordbtt);
        Button nextPageBtn4 = (Button) findViewById(R.id.Caloriesbtt);

        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(BMI.this , MainPage.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(BMI.this , Timing.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(BMI.this , daterecord.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(BMI.this , CaloriesCaculate.class);
                startActivity(intent4);
            }
        });
    }

}
