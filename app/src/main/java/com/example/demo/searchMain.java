package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class searchMain extends AppCompatActivity {
    public Connection con;
    ArrayList<String>foodArray=new ArrayList<String>();
    ArrayList<String>calArray=new ArrayList<String>();
    ArrayList<String>proArray=new ArrayList<String>();
    ArrayList<String>carArray=new ArrayList<String>();
    ArrayList<String>fatArray=new ArrayList<String>();

    ArrayAdapter<String> foodAdapter;
    ArrayAdapter<String> calAdapter;
    ArrayAdapter<String> proAdapter;
    ArrayAdapter<String> carAdapter;
    ArrayAdapter<String> fatAdapter;

    private ListView listView;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute("");
        listView=(ListView)findViewById(R.id.food);
        foodAdapter=new ArrayAdapter<>(searchMain.this,R.layout.activity_list_item_b,foodArray);
        calAdapter=new ArrayAdapter<>(searchMain.this,R.layout.activity_list_item_b,calArray);
        proAdapter=new ArrayAdapter<>(searchMain.this,R.layout.activity_list_item_b,proArray);
        carAdapter=new ArrayAdapter<>(searchMain.this,R.layout.activity_list_item_b,carArray);
        fatAdapter=new ArrayAdapter<>(searchMain.this,R.layout.activity_list_item_b,fatArray);
        listView.setAdapter(foodAdapter);
        listView.setSelection(5);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(listView_OnItemClickListener);
        listView.setTextFilterEnabled(true);

        searchView=(SearchView)findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){
                    Filter filter=foodAdapter.getFilter();
                    filter.filter(newText);
                    listView.setAdapter(foodAdapter);
                }
                else {
                    Filter filter=foodAdapter.getFilter();
                    filter.filter("");
                    listView.setAdapter(foodAdapter);
                }
                return false;
            }
        });
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(false);
        searchView.requestFocusFromTouch();
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("輸入食物名稱");
    }




    private AdapterView.OnItemClickListener listView_OnItemClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(parent.getContext(),"Selected："+foodAdapter.getItem(position),Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            intent.setClass(searchMain.this,eating.class);
            bundle.putString("name",foodAdapter.getItem(position));
            bundle.putString("cal",calAdapter.getItem(position));
            bundle.putString("pro",proAdapter.getItem(position));
            bundle.putString("car",carAdapter.getItem(position));
            bundle.putString("fat",fatAdapter.getItem(position));
            intent.putExtras(bundle);
            searchMain.this.setResult(RESULT_OK,intent);
            searchMain.this.finish();
        }
    };


    public class CheckLogin extends AsyncTask<String,String,String> {
        String z="";
        int counter=1;
        protected void onPostExecute(String r){
            Toast.makeText(searchMain.this,r,Toast.LENGTH_LONG).show();
            foodAdapter.notifyDataSetChanged();
        }
        protected String doInBackground(String... params){
            try{
                con=connectionclass();
                if(con==null){
                    z="Internet Error!";
                }
                else {
                    String query="select * from dbo.食物資料表";
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs=stmt.executeQuery(query);
                    while(rs.next()){
                        rs.absolute(counter);
                        foodArray.add(rs.getString("食物名稱"));
                        calArray.add(rs.getString("熱量"));
                        if(rs.getString("蛋白質")!=null){
                        proArray.add(rs.getString("蛋白質"));}
                        else{
                            proArray.add("0");
                        }
                        if(rs.getString("碳水化合物")!=null){
                        carArray.add(rs.getString("碳水化合物"));}
                        else{
                            carArray.add("0");
                        }
                        if(rs.getString("脂肪")!=null){
                        fatArray.add(rs.getString("脂肪"));}
                        else{
                            fatArray.add("0");
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