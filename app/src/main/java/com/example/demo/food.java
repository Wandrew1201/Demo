package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class food extends AppCompatActivity {
    ListView food;
    //private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food);
        String [] Food={"餐廳"};
        Bundle bundle = getIntent().getExtras();
        food = (ListView) findViewById(R.id.food);
        int i = bundle.getInt("rest");
        //test = findViewById(R.id.testt);
        //test.setText(i);
        switch (i){
            case 0:
                String [] Food0=  getResources().getStringArray(R.array.foodname1);
                ListAdapter adapter0 = new ArrayAdapter<>(this , R.layout.activity_list_item_b ,Food0);
                food.setAdapter(adapter0);
                break;
            case 1:
                String [] Food2=  getResources().getStringArray(R.array.foodname2);
                ListAdapter adapter2 = new ArrayAdapter<>(this , R.layout.activity_list_item_b ,Food2);
                food.setAdapter(adapter2);
                break;
            case 2:
                String [] Food3=  getResources().getStringArray(R.array.foodname3);
                ListAdapter adapter3 = new ArrayAdapter<>(this , R.layout.activity_list_item_b ,Food3);
                food.setAdapter(adapter3);
                break;
            case 3:
                String [] Food4=  getResources().getStringArray(R.array.foodname4);
                ListAdapter adapter4 = new ArrayAdapter<>(this , R.layout.activity_list_item_b,Food4);
                food.setAdapter(adapter4);
                break;

        }

    }
}
