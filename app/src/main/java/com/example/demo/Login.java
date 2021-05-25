package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private Button registerBtn,loginBtn;
    private Intent registerPage,personalPage;
    private EditText accountE,passwordE;
    private ArrayList<String> accountArray=new ArrayList<String>();
    private ArrayList<String>passwordArray=new ArrayList<String>();
    private String accountPass;
    private Bundle bundleAccount;
    public Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute();
        accountE=(EditText)findViewById(R.id.accountE);
        passwordE=(EditText)findViewById(R.id.passwordE);
        registerBtn=(Button)findViewById(R.id.registerBtn);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        accountPass=accountE.getText().toString();
        File f = new File("Account.txt");
        if(f.exists()){
            deleteFile("Account.txt");}
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPage=new Intent();
                registerPage.setClass(Login.this,Register.class);
                startActivity(registerPage);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<accountArray.size();i++){
                    String a=accountArray.get(i);
                    String p=passwordArray.get(i);
                    if(accountE.getText().toString().equals(a)&passwordE.getText().toString().equals(p)){
                        Toast.makeText(Login.this,"登入成功",Toast.LENGTH_LONG).show();

                        personalPage=new Intent();
                        //Intent in=new Intent();
                        //in.setClass(Login.this,MainPage.class);
                        bundleAccount=new Bundle();
                        personalPage.setClass(Login.this,MainPage.class);
                        /*bundleAccount.putString("ACCOUNT",accountE.getText().toString());
                        personalPage.putExtras(bundleAccount);*/
                        try {
                            FileOutputStream outStream1=openFileOutput("Account.txt", Context.MODE_PRIVATE);
                            outStream1.write(accountE.getText().toString().getBytes());
                            outStream1.close();
                            Toast.makeText(Login.this,"Saved",Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            return;
                        }
                        catch (IOException e){
                            return ;
                        }
                        startActivity(personalPage);
                    }
                    else {
                        Toast.makeText(Login.this,"帳號或密碼有誤",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        protected void onPostExecute(String r){
            Toast.makeText(Login.this,r,Toast.LENGTH_LONG).show();
        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="select 信箱, 密碼 from dbo.使用者基本資料";
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs=stmt.executeQuery(query);
                    while(rs.next()){
                        rs.absolute(counter);
                        accountArray.add(rs.getString("信箱"));
                        passwordArray.add(rs.getString("密碼"));
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