package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class food_details extends AppCompatActivity {
    private TextView food;
    private Spinner spinner;
    private ArrayList<String> recipeArray=new ArrayList<String>();
    private ArrayAdapter<String> recipeAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        food=(TextView)findViewById(R.id.foodText);
        spinner=(Spinner)findViewById(R.id.foodSpi);
        recipeArray.add("蒜泥白肉");
        recipeArray.add("酪梨雞肉沙拉");
        recipeArray.add("鮭魚茶泡飯");
        recipeAd=new ArrayAdapter<>(food_details.this,android.R.layout.simple_spinner_dropdown_item,recipeArray);
        spinner.setAdapter(recipeAd);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    food.setText("食材 （3~4 人份） 45 分鐘\n" +
                            "豬五花肉  600克\n" +
                            "青蔥  2支\n" +
                            "薑片  6片\n" +
                            "米酒  2大匙\n" +
                            "醬料：\n" +
                            "醬油膏  2.5大匙\n" +
                            "烏醋  1小匙\n" +
                            "蒜泥  2大匙\n" +
                            "香油  1小匙\n" +
                            "開水  1.5大匙\n" +
                            "1.燒一鍋滾水，將豬肉稍微川燙後取出，並用冷水洗淨外表的雜質。\n" +
                            "2.接著再重新燒一鍋滾水，並加入青蔥、薑片、米酒和豬肉，並用最小火煮30分鐘後，關火，\n" +
                            "接著繼續浸泡約10分鐘。\n" +
                            "3.將豬肉取出後放入冰(冷)水讓豬肉冷卻。(Tips 3)\n" +
                            "4.等待豬肉冷卻的同時，把醬料的材料均勻混合後備用。\n" +
                            "5.最後把冷卻後的豬肉切片，淋上醬料\n");
                }
                else if(position==1){
                    food.setText("雞胸肉(去皮):1 個（180g）\n" +
                            "酪梨:1小顆（150g）\n" +
                            "小番茄:3 小顆\n" +
                            "鹽:1 小撮\n" +
                            "研磨黑胡椒:少許\n" +
                            "檸檬汁:1 小匙\n" +
                            "1.雞胸肉切片（厚約0.5cm）後加入醃料，按抓至水分被雞胸肉吸收為止（或置於冷藏醃過夜）。\n" +
                            "2.小番茄對切，去籽後切丁。\n" +
                            "3.將酪梨取出果肉後搗成泥狀，加入番茄丁、鹽、黑胡椒、檸檬汁，拌勻備用。\n" +
                            "4.鍋內倒入油，以中小火預熱後，擺入醃好的雞胸肉片香煎，煎至雞胸肉邊緣出現一圈暈白色後翻面續煎，兩面均煎熟即可起鍋。\n" +
                            "5.將作法3 與作法4 拌勻即完成。\n");
                }
                else if(position==2){
                    food.setText(
                            "紫米、糙米飯適量\n" +
                                    "洋蔥絲半顆量\n" +
                                    "鮭魚肉120g\n" +
                                    "玄米茶包1包（可用綠茶代替）\n" +
                                    "海苔絲適量\n" +
                                    "柴魚粉2匙\n" +
                                    "味醂50ml\n" +
                                    "醬油100ml\n" +
                                    "香鬆1匙\n" +
                                    "1.取一平底鍋倒入適量油，將鮭魚煎熟至表面金黃後取出，再以夾子或手撕碎備用。\n" +
                                    "2.另取一湯鍋倒入冷水500ml，加入柴魚粉、醬油、味醂，煮滾後放入洋蔥，再繼續煮沸約5分鐘。\n" +
                                    "3.將玄米茶包以100ml的熱水泡約3分鐘，拿掉茶包，將茶水倒入高湯調和，接著濾掉洋蔥，成為茶高湯。(高湯與茶的比例為5：1。)\n" +
                                    "4.將紫米或糙米飯盛入碗中，撒上60g鮭魚肉後，再依個人口味淋上適量茶高湯，最後撒上香鬆及海苔絲即完成。\n");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}