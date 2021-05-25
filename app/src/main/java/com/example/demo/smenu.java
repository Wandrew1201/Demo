package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class smenu extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    private ListView listView;
    private String[] menuArray = {"減肥餐","健身餐"};
    //ArrayAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smenu);
        listView = (ListView) findViewById(R.id.listview1);
        ArrayAdapter listAdapter = new ArrayAdapter(this,R.layout.activity_list_item_a, menuArray);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent();
                bundle = new Bundle();
                int po =position;
                bundle.putInt("selected",po);
                intent.putExtras(bundle);
                intent.setClass(smenu.this,suggest.class);
                startActivity(intent);
            }
        });
    }


    public class ListAdapter extends BaseAdapter {
        private LayoutInflater mLayInf;
        String[] str;
        public ListAdapter(Context context, String[] arraylist)
        {
            mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            str=arraylist;
        }

        @Override
        public int getCount()
        {
            return str.length;
        }

        @Override
        public Object getItem(int position)
        {
            return position;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
            View v = mLayInf.inflate(R.layout.list_item, parent, false);

            TextView txtView = (TextView) v.findViewById(R.id.item1);

            txtView.setText(str[position]);

            return v;
        }
    }
}