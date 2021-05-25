package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends AppCompatActivity {

    public Connection con;
    private Intent backMain;
    private EditText accountE,passwordE,nameE,ageE,heightE,weightE;
    private RadioGroup radioGroup;
    private Button confirmBtn;
    private String sexual="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        accountE=(EditText)findViewById(R.id.accountE2);
        passwordE=(EditText)findViewById(R.id.passwordE2);
        nameE=(EditText)findViewById(R.id.nameE);
        ageE=(EditText)findViewById(R.id.ageE);
        heightE=(EditText)findViewById(R.id.heightE);
        weightE=(EditText)findViewById(R.id.weightE);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup1);
        confirmBtn=(Button)findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sex=radioGroup.getCheckedRadioButtonId();
                if(sex==R.id.maleBtn){
                    sexual="male";
                }
                else {
                    sexual="female";
                }
                CheckLogin checkLogin=new CheckLogin();
                checkLogin.execute();
                backMain=new Intent();
                backMain.setClass(Register.this,Login.class);
                startActivity(backMain);
            }
        });

    }
    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        protected void onPostExecute(String r){
            Toast.makeText(Register.this,r,Toast.LENGTH_LONG).show();
        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="INSERT INTO dbo.使用者基本資料 VALUES" +
                            "(N'"+nameE.getText().toString()+"', '"+accountE.getText().toString()+"', '"+passwordE.getText().toString()+"', '"+ageE.getText().toString()+"', '"+sexual+"', '"+heightE.getText().toString()+"', '"+weightE.getText().toString()+"' )";
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate(query);
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