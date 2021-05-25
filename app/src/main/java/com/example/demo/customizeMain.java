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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class customizeMain extends AppCompatActivity {
    public Connection con;
    EditText cName,cCal,cPro,cCar,cFat;
    String name,cal,pro,car,fat;
    Button confirmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_main);
        cName=(EditText)findViewById(R.id.cName);
        cCal=(EditText)findViewById(R.id.cCal);
        //cPro=(EditText)findViewById(R.id.cPro);
        //cCar=(EditText)findViewById(R.id.cCar);
        //cFat=(EditText)findViewById(R.id.cFat);


        confirmed=(Button)findViewById(R.id.cBut);
        confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=cName.getText().toString();
                cal=cCal.getText().toString();
                //pro=cPro.getText().toString();
                pro="0";
                //car=cCar.getText().toString();
                car="0";
                //fat=cFat.getText().toString();
                fat="0";
                CheckLogin checkLogin=new CheckLogin();
                checkLogin.execute();
                Intent customize=new Intent();
                Bundle bundle2=new Bundle();
                customize.setClass(customizeMain.this,eating.class);
                bundle2.putString("cName",name);
                bundle2.putString("cCal",cal);
                bundle2.putString("cPro",pro);
                bundle2.putString("cCar",car);
                bundle2.putString("cFat",fat);
                customize.putExtras(bundle2);
                customizeMain.this.setResult(2,customize);
                customizeMain.this.finish();
            }
        });
    }


    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        protected void onPostExecute(String r){
            Toast.makeText(customizeMain.this,r,Toast.LENGTH_LONG).show();

        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="INSERT INTO dbo.食物資料表 VALUES " +
                            "('F1022', N'"+name+"','"+cal+"', '"+pro+"', '"+car+"', '"+fat+"', NULL)";
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