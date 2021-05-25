package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class eating extends AppCompatActivity {
    ListView listView2;
    ArrayList<String> foodArray2=new ArrayList<String>();
    ArrayAdapter<String> foodAdapter2;
    Intent intent;
    Intent intent2;
    ArrayList<Double> calArray2=new ArrayList<Double>();
    ArrayList<Double> proArray2=new ArrayList<Double>();
    ArrayList<Double> carArray2=new ArrayList<Double>();
    ArrayList<Double> fatArray2=new ArrayList<Double>();
    Double calNum,proNum,carNum,fatNum;
    Double calSum,proSum,carSum,fatSum;
    TextView addT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating);
        calSum=proSum=carSum=fatSum=0.0;
        Button search=(Button)findViewById(R.id.addB);
        Button customize=(Button)findViewById(R.id.addB2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent();
                intent.setClass(eating.this,searchMain.class);
                startActivityForResult(intent,1);
            }
        });
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2=new Intent();
                intent2.setClass(eating.this,customizeMain.class);
                startActivityForResult(intent2,2);
            }
        });
        listView2=(ListView)findViewById(R.id.food2);
        foodAdapter2=new ArrayAdapter<>(eating.this,R.layout.activity_list_item_a,foodArray2);
        foodAdapter2.notifyDataSetChanged();
        listView2.setAdapter(foodAdapter2);
        listView2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView2.setOnItemClickListener(listView2_OnItemClickListener);
        Button done=(Button)findViewById(R.id.donebtt);
        Intent intentE = getIntent();
        final int check=intentE.getIntExtra("check",0);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent();
                intentR.setClass(eating.this , record.class);
                intentR.putExtra("calsum",calSum);
                intentR.putExtra("prosum",proSum);
                intentR.putExtra("carsum",carSum);
                intentR.putExtra("fatsum",fatSum);
                intentR.putExtra("returncheck",check);
                startActivity(intentR);
                eating.this.finish();
            }
        });
    }

    private AdapterView.OnItemClickListener listView2_OnItemClickListener =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            foodArray2.remove(foodAdapter2.getItem(position));
            foodAdapter2.notifyDataSetChanged();
            listView2.setAdapter(foodAdapter2);
            calSum-=calArray2.get(position);proSum-=proArray2.get(position);
            carSum-=carArray2.get(position);fatSum-=fatArray2.get(position);
            calArray2.remove(position);proArray2.remove(position);
            carArray2.remove(position);fatArray2.remove(position);
            addT.setText(Math.round(calSum)+"熱量,");
            //+Math.round(proSum)+
            //                    "蛋白質,"+Math.round(carSum)+"碳水化合物,"+Math.round(fatSum)+"脂肪"

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            String result=data.getExtras().getString("name");
            calNum=Double.parseDouble(data.getExtras().getString("cal"));
            if (calNum==null){
                calNum=0.0;
            }
            proNum=Double.parseDouble(data.getExtras().getString("pro"));
            if (proNum==null){
                proNum=0.0;
            }
            carNum=Double.parseDouble(data.getExtras().getString("car"));
            if (carNum==null){
                carNum=0.0;
            }
            fatNum=Double.parseDouble(data.getExtras().getString("fat"));
            if (fatNum==null){
                fatNum=0.0;
            }
            calArray2.add(calNum);proArray2.add(proNum);carArray2.add(carNum);fatArray2.add(fatNum);
            calSum+=calNum;proSum+=proNum;carSum+=carNum;fatSum+=fatNum;
            foodArray2.add(result);
            addT= (TextView)findViewById(R.id.addT);
            addT.setText(Math.round(calSum)+"大卡,");
            listView2.setAdapter(foodAdapter2);
        }
        else if(resultCode==2){
            String result2=data.getExtras().getString("cName");
            calNum=Double.parseDouble(data.getExtras().getString("cCal"));
            if (calNum==null){
                calNum=0.0;
            }
            proNum=Double.parseDouble(data.getExtras().getString("cPro"));
            if (proNum==null){
                proNum=0.0;
            }
            carNum=Double.parseDouble(data.getExtras().getString("cCar"));
            if (carNum==null){
                carNum=0.0;
            }
            fatNum=Double.parseDouble(data.getExtras().getString("cFat"));
            if (fatNum==null){
                fatNum=0.0;
            }
            calArray2.add(calNum);proArray2.add(proNum);carArray2.add(carNum);fatArray2.add(fatNum);
            calSum+=calNum;proSum+=proNum;carSum+=carNum;fatSum+=fatNum;
            foodArray2.add(result2);
            addT= (TextView)findViewById(R.id.addT);
            addT.setText(Math.round(calSum)+"大卡,");
            //+Math.round(proSum)+
            //                    "蛋白質,"+Math.round(carSum)+"碳水化合物,"+Math.round(fatSum)+"脂肪"
            listView2.setAdapter(foodAdapter2);
        }

    }

}
