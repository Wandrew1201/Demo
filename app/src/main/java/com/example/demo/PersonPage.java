package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonPage extends AppCompatActivity {

    private Intent intent;
    private String account;
    private TextView nameV,sexV,ageV,heightV,weightV,bmiV,rV;
    public Connection con;
    private double b,r1;
    private ArrayList<String> nameArray=new ArrayList<String>();
    ArrayAdapter<String> nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        nameV=(TextView)findViewById(R.id.nameV);
        sexV=(TextView)findViewById(R.id.sexV);
        ageV=(TextView)findViewById(R.id.ageV);
        heightV=(TextView)findViewById(R.id.heightV);
        weightV=(TextView)findViewById(R.id.weightV);
        bmiV=(TextView)findViewById(R.id.bmiV);
        rV=(TextView)findViewById(R.id.resultV);
        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute();

        intent=getIntent();
        Button nextPageBtn4 = (Button) findViewById(R.id.mb);
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(PersonPage.this , MainPage.class);
                startActivity(intent4);
            }
        });
        account=intent.getExtras().getString("ACCOUNT");
        try
        {
            FileInputStream inStream = this.openFileInput("bmiset.txt");
            ByteArrayOutputStream streama = new ByteArrayOutputStream();
            int count =0;
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                streama.write(buffer, 0, length);
            }
            r1=Double.parseDouble(streama.toString());
            streama.close();
            inStream.close();
            Toast.makeText(PersonPage.this, "Loaded", Toast.LENGTH_LONG).show();
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
       if(r1==b){
           rV.setText("最新輸入的BMI為"+Math.round(r1)+"，和原本的bmi維持不變");
       }
       else if(r1>b){
           rV.setText("最新輸入的BMI為"+Math.round(r1)+"，比原本的bmi低囉，繼續加油");
       }
       else{
           rV.setText("最新輸入的BMI為"+Math.round(r1)+"，比原本的bmi高囉，繼續加油");
       }

    }
    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        double h;
        String name,sex,age,height,weight;
        protected void onPostExecute(String r){
            Toast.makeText(PersonPage.this,r,Toast.LENGTH_LONG).show();
            nameV.setText("姓名："+name);
            sexV.setText("性別："+sex);
            ageV.setText("年齡："+age);
            heightV.setText("身高："+height);
            weightV.setText("體重："+weight);
            h=Double.parseDouble(height)/100;
            b=Double.parseDouble(weight)/(h*h);
            bmiV.setText("BMI: "+Math.round(b));
        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="select * from dbo.使用者基本資料 where 信箱 = '"+account+"'";
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs=stmt.executeQuery(query);
                    if(rs.next()){
                        rs.absolute(counter);
                        name=rs.getString("姓名");
                        sex=rs.getString("性別");
                        age=rs.getString("年齡");
                        height=rs.getString("身高");
                        weight=rs.getString("體重");
                        con.close();
                    }
                }
            }
            catch (Exception ex){
                z=ex.getMessage();
                Log.d("sql error",z);
            }
            return z;
        }
    }

    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL= null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://project1997.database.windows.net:1433;DatabaseName=畢業專題;user=andrew@project1997;password=Wanwolfa1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se){
            Log.e("error here 1:",se.getMessage());
        }
        catch (ClassNotFoundException e){
            Log.e("error here 2:",e.getMessage());
        }
        catch (Exception e){
            Log.e("error here 3:",e.getMessage());
        }
        return connection;
    }
}