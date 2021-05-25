package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class suggest extends AppCompatActivity {

    private Intent intent1;
    private TextView details;
    private TextView suggest;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        intent1 = new Intent();
        details=(TextView)findViewById(R.id.details) ;
        suggest=(TextView)findViewById(R.id.suggest);
        btn=(Button) findViewById(R.id.btn);
        final Bundle bundle1=getIntent().getExtras();
        final int judge=bundle1.getInt("selected");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1.setClass(suggest.this,food_details.class);
                startActivity(intent1);
            }
        });



        if(judge==0){
            suggest.setText("多靠攝取蛋白質讓自己不感到飢餓，少攝取澱粉類食物，並且盡量不要攝取加工過後的食物\n");
            details.setText("早餐:全麥麵包、全麥饅頭、糙米飯、奶類、豆漿、水煮蛋\n" +
                    "午餐及晚餐:\n" +
                    "飯類: 糙米飯or紫米飯or地瓜飯(以上皆少量)\n" +
                    "肉類: 雞腿去皮or清蒸魚or清燙豬肉(EX:蒜泥白肉)\n" +
                    "蔬菜: 每餐攝取一到兩樣深綠蔬菜。\n" +
                    "水果: 蘋果、番茄、莓果、葡萄、紅龍果、柑橘、李子、桃子、酪梨。\n" +
                    "點心: 堅果類食物\n");

        }
        else if(judge==1){
            suggest.setText("減少熱量攝取、並少量多餐(原先三餐由6-7餐取代)\n");
            details.setText("第一餐(早上7-8點):\n" +
                    "碳水化合物: 饅頭、麵包、吐司\n" +
                    "蛋白質: 一杯高蛋白加兩顆蛋白\n" +
                    "水果: 蘋果一顆、香蕉一根\n" +
                    "第二餐(早上10點):\n" +
                    "碳水化合物: 麵包、吐司\n" +
                    "蛋白質: 一杯高蛋白加一顆蛋白\n" +
                    "水果: 香蕉一根、奇異果一顆\n" +
                    "第三餐(午餐):\n" +
                    "碳水化合物: 飯類、麵類為主食\n" +
                    "蛋白質: 魚肉、牛肉或雞肉(需去皮)\n" +
                    "蔬果: 花椰菜、豆芽菜、黃花菜、海帶、甜椒、菠菜\n" +
                    "第四餐(下午3點):\n" +
                    "碳水化合物: 吐司一片\n" +
                    "蛋白質: 一杯高蛋白加一顆蛋白\n" +
                    "水果: 橘子一顆、香蕉一根\n" +
                    "第五餐(晚餐):\n" +
                    "碳水化合物: 飯類、麵類為主食\n" +
                    "蛋白質: 魚肉、牛肉或雞肉(需去皮)皆須盡量清燉、清蒸\n" +
                    "蔬果: 花椰菜、豆芽菜、黃花菜、海帶、甜椒、菠菜\n" +
                    "第六餐(晚上9-10點):\n" +
                    "碳水化合物: 麵包、吐司\n" +
                    "蛋白質: 一杯高蛋白加一顆蛋白\n" +
                    "水果: 香蕉一根、奇異果一顆\n");
        }


    }
}