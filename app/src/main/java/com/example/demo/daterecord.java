package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class daterecord extends AppCompatActivity {
    //private int mYear, mMonth, mDay;
    ArrayList<String> dateArray=new ArrayList<String>();
    ArrayList<String> dateArray2=new ArrayList<String>();
    ArrayAdapter<String> dateAdapter;
    public Connection con;
    //String date;
    CheckLogin2 checkLogin2=new CheckLogin2();
    //CheckLogin checkLogin=new CheckLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daterecord);


        //checkLogin.execute("");

        checkLogin2.execute("");
        //date1.setText(dateAdapter.getItem(1));
        //Button datebtt = (Button)findViewById(R.id.datebtt);
        final ListView datel = (ListView)findViewById(R.id.datelist);
        /*datebtt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(daterecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = setDateFormat(year,month,day);
                        date=format;
                        checkLogin.execute("");
                        checkLogin2.execute("");
                        dateAdapter=new ArrayAdapter(daterecord.this,R.layout.activity_list_item_b,dateArray);
                        datel.setAdapter(dateAdapter);
                        dateAdapter.notifyDataSetChanged();
                        }
                }, mYear,mMonth, mDay).show();

                }

        });*/
        dateAdapter=new ArrayAdapter<>(daterecord.this,R.layout.activity_list_item_b,dateArray);
        datel.setAdapter(dateAdapter);
        datel.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        datel.setOnItemClickListener(datel_OnItemClickListener);

        Button nextPageBtn1 = (Button) findViewById(R.id.mainbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.timebtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.bmibtt);
        Button nextPageBtn4 = (Button) findViewById(R.id.caloriesbtt);

        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(daterecord.this , MainPage.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(daterecord.this , Timing.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(daterecord.this , BMI.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(daterecord.this , CaloriesCaculate.class);
                startActivity(intent4);
            }
        });
    }

    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return (String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + String.valueOf(dayOfMonth));
    }
    private AdapterView.OnItemClickListener datel_OnItemClickListener =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.putExtra("fdate",dateAdapter.getItem(position));
            intent.setClass(daterecord.this,record.class);
            startActivity(intent);
        }
    };

    /*public class CheckLogin extends AsyncTask<String,String,String> {
        String z = "";
        //int counter = 1;

        protected void onPostExecute(String r) {
            Toast.makeText(daterecord.this, r, Toast.LENGTH_LONG).show();

        }

        protected String doInBackground(String... params) {
            try {
                con = connectionclass();
                if (con == null) {
                    z = "Internet Error!";
                } else {
                    String query = "INSERT INTO dbo.進食紀錄表 VALUES ('Z0000', NULL ,"+date+")";
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate(query);
                    //String s="select * from dbo.進食紀錄表";

                }
            } catch (Exception ex) {
                z = ex.getMessage();
                Log.d("sql error", z);
            }
            return z;
        }
    }*/

        public class CheckLogin2 extends AsyncTask<String,String,String> {
            String z="";
            int counter=1;
            protected void onPostExecute(String r){
                Toast.makeText(daterecord.this,r,Toast.LENGTH_LONG).show();
                dateAdapter.notifyDataSetChanged();
            }
            protected String doInBackground(String... params){
                try{
                    con=connectionclass();
                    if(con==null){
                        z="Internet Error!";
                    }
                    else {
                        String s="select 用餐時間 from dbo.進食紀錄表";
                        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs=stmt.executeQuery(s);
                        while(rs.next()){
                            rs.absolute(counter);

                            if(rs.getString("用餐時間")!=null){
                                dateArray.add(rs.getString("用餐時間"));}
                            else{
                                dateArray.add("0");
                            }

                            z="";
                            counter++;
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