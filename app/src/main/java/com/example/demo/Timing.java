package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Timing extends AppCompatActivity {
    long escTime=0;
    public Connection con;
    ArrayList<String>exerciseArray=new ArrayList<String>();
    ArrayList<Double>exerciseTime=new ArrayList<Double>();
    ArrayAdapter<String>excList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timing);

        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute("");
        excList = new ArrayAdapter<>(Timing.this,R.layout.myspinner,exerciseArray);
        spinner.setAdapter(excList);
        Button Start =(Button)findViewById(R.id.start);
        Button Stop =(Button)findViewById(R.id.stop);
        Button Reset = (Button)findViewById(R.id.reset);
        Button Result1=(Button)findViewById(R.id.result1);
        final TextView getres=(TextView)findViewById(R.id.getres);
        final Chronometer time=(Chronometer)findViewById(R.id.timer);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected: " + excList.getItem(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Start.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                SystemClock.elapsedRealtime();
                time.setBase(SystemClock.elapsedRealtime()+escTime);
                time.start();
            }
        });
        Stop.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                escTime=time.getBase()-SystemClock.elapsedRealtime();
                time.stop();
            }
        });
        Reset.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                time.setBase(SystemClock.elapsedRealtime());
                time.stop();
                escTime=0;
            }
        });
        Result1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escTime/=1000;
                escTime*=(-1);

                double res=escTime*exerciseTime.get(spinner.getSelectedItemPosition());
                String res1=Double.toString(res);
                getres.setText("消耗了"+res1+"大卡");

            }
        });

        Button nextPageBtn1 = (Button) findViewById(R.id.mainbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.bmibtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.recordbtt);
        Button nextPageBtn4 = (Button) findViewById(R.id.timebtt);

        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Timing.this , MainPage.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(Timing.this , BMI.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(Timing.this , record.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(Timing.this , CaloriesCaculate.class);
                startActivity(intent4);
            }
        });
    }

    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        protected void onPostExecute(String r){
            Toast.makeText(Timing.this,r,Toast.LENGTH_LONG).show();
            excList.notifyDataSetChanged();
        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="select * from dbo.運動資料表";
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs=stmt.executeQuery(query);

                    while(rs.next()){
                        rs.absolute(counter);
                        exerciseArray.add(rs.getString("運動名稱"));
                        exerciseTime.add(rs.getDouble("每秒消耗"));
                        z="query successful";
                        counter++;
                    }



                }
            }
            catch (Exception ex){
                //isSuccess=false;
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

