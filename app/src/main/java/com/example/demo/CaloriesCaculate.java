package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class
CaloriesCaculate extends AppCompatActivity {
    public Connection con;
    ArrayList<String> exerciseArray=new ArrayList<String>();
    ArrayList<Double>exerciseTime=new ArrayList<Double>();
    ArrayAdapter<String> excList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calories_caculate);
        Button mbutton = (Button)findViewById(R.id.button);


        final TextView mresult=(TextView)findViewById(R.id.result);
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute("");
        excList = new ArrayAdapter<>(CaloriesCaculate.this,R.layout.myspinner,exerciseArray);
        spinner.setAdapter(excList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected: " + excList.getItem(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText c=(EditText)findViewById(R.id.editcarlo);
                if(!("".equals(c.getText().toString()))){
                double calories = Double.parseDouble(c.getText().toString());
                double t=(calories)/(exerciseTime.get(spinner.getSelectedItemPosition()));
                t=t/60;
                String r1=Double.toString(Math.round(t));
                mresult.setText("需要運動"+r1+"分鐘");}
            }
        });
        Button nextPageBtn1 = (Button) findViewById(R.id.mainbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.timebtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.recordbtt);
        Button nextPageBtn4 = (Button) findViewById(R.id.bmibtt);

        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(CaloriesCaculate.this , MainPage.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(CaloriesCaculate.this , Timing.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(CaloriesCaculate.this , daterecord.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(CaloriesCaculate.this , BMI.class);
                startActivity(intent4);
            }
        });

    }
    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        protected void onPostExecute(String r){
            Toast.makeText(CaloriesCaculate.this,r,Toast.LENGTH_LONG).show();
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
