package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class BMIresult extends AppCompatActivity {
     int sggbmi;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);
        Intent intent=getIntent();
        double ans=intent.getDoubleExtra("ans",0);

        TextView res=(TextView)findViewById(R.id.newView);
        TextView res1=(TextView)findViewById(R.id.newView1);
                File f = new File("bmiset.txt");
                if(f.exists()){
                    deleteFile("bmiset.txt");
                    try {
                        FileOutputStream outStream1 = this.openFileOutput("bmiset.txt", Context.MODE_PRIVATE);
                        outStream1.write(Double.toString(ans).getBytes());
                        outStream1.close();
                        Toast.makeText(BMIresult.this, "Saved", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        return;
                    } catch (IOException e) {
                        return;
                    }}
                else {
                    try {
                        FileOutputStream outStream1 = this.openFileOutput("bmiset.txt", Context.MODE_PRIVATE);
                        outStream1.write(Double.toString(ans).getBytes());
                        outStream1.close();
                        Toast.makeText(BMIresult.this, "Saved", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        return;
                    } catch (IOException e) {
                        return;
                    }
                }
        res.setText(String.format("%.2f",ans));
        if(ans<18.5){
            res1.setText("????????????");
            sggbmi=1;
        }
        else if(ans>=18.5&&ans<24){
            res1.setText("????????????");
            sggbmi=1;


        }
        else if(ans>=24&&ans<27){
            res1.setText("????????????");
            sggbmi=2;
        }
        else if(ans>=27&&ans<30){
            res1.setText("????????????");
            sggbmi=2;
        }
        else if(ans>=30&&ans<35){
            res1.setText("????????????");
            sggbmi=2;
        }
        else{
            res1.setText("????????????");
            sggbmi=2;
        }
        Button nextbtt =(Button)findViewById(R.id.sggbtt);
        nextbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BMIresult.this , smenu.class);
                startActivity(intent);
            }
        });

    }


}