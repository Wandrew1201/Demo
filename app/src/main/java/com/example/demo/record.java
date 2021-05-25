package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class record extends AppCompatActivity {
    TextView breakfastview,lunchview,dinnerview;
    int check;
     double cal;
     double pro;
     double car;
     double fat;
     String fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Intent intentg=getIntent();
        Intent intentd=getIntent();
        breakfastview=(TextView)findViewById(R.id.breakfast);
        lunchview=(TextView)findViewById(R.id.lunch);
        dinnerview=(TextView)findViewById(R.id.dinner);
        fd=intentd.getStringExtra("fdate");
        Button cb = (Button)findViewById(R.id.cleanb);
        Button cl = (Button)findViewById(R.id.cleanl);
        Button cd = (Button)findViewById(R.id.cleand);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(fd+"breakfasts.txt");
                if(f.exists()){
                deleteFile(fd+"breakfasts.txt");}
                breakfastview.setText("0");

            }
        });
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(fd+"lunchs.txt");
                if(f.exists()){
                deleteFile(fd+"lunchs.txt");}
                lunchview.setText("0");
            }
        });
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(fd+"dinners.txt");
                if(f.exists()){
                deleteFile(fd+"dinners.txt");}
                dinnerview.setText("0");
            }
        });

        Button bf =(Button)findViewById(R.id.bfbtt);
        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(record.this , eating.class);
                check=1;
                intent1.putExtra("check",check);
                startActivity(intent1);
            }
        });
        Button lunchb =(Button)findViewById(R.id.lbtt);
        lunchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(record.this , eating.class);
                check=2;
                intent2.putExtra("check",check);
                startActivity(intent2);
            }
        });
        Button dinnerb =(Button)findViewById(R.id.dbtt);
        dinnerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(record.this , eating.class);
                check=3;
                intent3.putExtra("check",check);
                startActivity(intent3);
            }
        });

         cal= intentg.getDoubleExtra("calsum",0);
         pro= intentg.getDoubleExtra("prosum",0);
         car= intentg.getDoubleExtra("carsum",0);
         fat= intentg.getDoubleExtra("fatsum",0);
        int rc= intentg.getIntExtra("returncheck",0);
        if(rc==1){
            meal b = new meal();
            b.setMdata(Math.round(cal),Math.round(pro),
                    Math.round(car),Math.round(fat));
            try {
                FileOutputStream outStream1=this.openFileOutput(fd+"breakfasts.txt",Context.MODE_PRIVATE);
                outStream1.write(Double.toString(b.getMcal()).getBytes());
                //outStream1.write(Double.toString(b.getMpro()).getBytes());
                //outStream1.write(Double.toString(b.getMcar()).getBytes());
                //outStream1.write(Double.toString(b.getMfat()).getBytes());
                outStream1.close();
                Toast.makeText(record.this,"Saved",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                return;
            }
            catch (IOException e){
                return ;
            }

            /*saveObject(b,"record.dat");
            meal br = (meal) readObject("record.dat");*/
        /*breakfastview.setText(Math.round(br.getMcal())+"熱量,"+Math.round(br.getMpro())+
                "蛋白質,"+Math.round(br.getMcar())+"碳水化合物,"+Math.round(br.getMfat())+"脂肪");*/}

        else if(rc==2){
            meal lu = new meal();
            lu.setMdata(Math.round(cal),Math.round(pro),
                    Math.round(car),Math.round(fat));
            /*meal b = new meal();
            b.setMdata(Math.round(cal),Math.round(pro),
                    Math.round(car),Math.round(fat));*/
            try {
                FileOutputStream outStream2=this.openFileOutput(fd+"lunchs.txt",Context.MODE_PRIVATE);
                outStream2.write(Double.toString(lu.getMcal()).getBytes());
                //outStream2.write(Double.toString(lu.getMpro()).getBytes());
                //outStream2.write(Double.toString(lu.getMcar()).getBytes());
                //outStream2.write(Double.toString(lu.getMfat()).getBytes());
                outStream2.close();
                Toast.makeText(record.this,"Saved",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                return;
            }
            catch (IOException e){
                return ;
            }

             }
        else if(rc==3){
            meal di = new meal();
            di.setMdata(Math.round(cal),Math.round(pro),
                    Math.round(car),Math.round(fat));

            try {
                FileOutputStream outStream2=this.openFileOutput(fd+"dinners.txt",Context.MODE_PRIVATE);
                outStream2.write(Double.toString(di.getMcal()).getBytes());
                //outStream2.write(Double.toString(di.getMpro()).getBytes());
                //outStream2.write(Double.toString(di.getMcar()).getBytes());
                //outStream2.write(Double.toString(di.getMfat()).getBytes());
                outStream2.close();
                Toast.makeText(record.this,"Saved",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                return;
            }
            catch (IOException e){
                return ;
            }

             }

        try
            {
                FileInputStream inStream = this.openFileInput(fd + "breakfasts.txt");
                ByteArrayOutputStream streamb = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    streamb.write(buffer, 0, length);
                }
                streamb.close();
                inStream.close();
                breakfastview.setText(streamb.toString() + "大卡");
                Toast.makeText(record.this, "Loaded", Toast.LENGTH_LONG).show();
            } catch(
            FileNotFoundException e)

            {
                e.printStackTrace();
            }
        catch(
            IOException e)

            {
                return;
            }

        try

            {
                FileInputStream inStream = this.openFileInput(fd + "lunchs.txt");
                ByteArrayOutputStream streaml = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    streaml.write(buffer, 0, length);
                }
                streaml.close();
                inStream.close();
                lunchview.setText(streaml.toString() + "大卡");
                Toast.makeText(record.this, "Loaded", Toast.LENGTH_LONG).show();
            } catch(
            FileNotFoundException e)

            {
                e.printStackTrace();
            }
        catch(
            IOException e)

            {
                return;
            }
        try

            {
                FileInputStream inStream = this.openFileInput(fd + "dinners.txt");
                ByteArrayOutputStream streamd = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    streamd.write(buffer, 0, length);
                }
                streamd.close();
                inStream.close();
                dinnerview.setText(streamd.toString() + "大卡");
                Toast.makeText(record.this, "Loaded", Toast.LENGTH_LONG).show();
            } catch(
            FileNotFoundException e)

            {
                e.printStackTrace();
            }
        catch(
            IOException e)

            {
                return;
            }


        Button nextPageBtn1 = (Button) findViewById(R.id.mainbtt);
        Button nextPageBtn2 = (Button) findViewById(R.id.timebtt);
        Button nextPageBtn3 = (Button) findViewById(R.id.bmibtt);
        Button nextPageBtn4 = (Button) findViewById(R.id.caloriesbtt);

        nextPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(record.this , MainPage.class);
                startActivity(intent1);
            }
        });
        nextPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(record.this , Timing.class);
                startActivity(intent2);
            }
        });
        nextPageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(record.this , BMI.class);
                startActivity(intent3);
            }
        });
        nextPageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(record.this , CaloriesCaculate.class);
                startActivity(intent4);
            }
        });
    }
    //implements Serializable
    class meal {
        double mcal,mpro,mcar,mfat;
        public void setMdata(double mcal,double mpro,double mcar,double mfat){
            this.mcal=mcal;
            this.mcar=mcar;
            this.mfat=mfat;
            this.mpro=mpro;
        }
        public double getMcal(){
            return this.mcal;
        }
        public double getMcar(){
            return this.mcar;
        }
        public double getMpro(){
            return this.mpro;
        }
        public double getMfat(){
            return this.mfat;
        }
    }
    /*public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try {
                oos.close();
            } catch (Exception e) {}
            try {
                fos.close();
            } catch (Exception e) {}
        }
    }
    public Serializable readObject(String file){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable)ois.readObject();
        }catch(FileNotFoundException e){
        }catch(Exception e){
            e.printStackTrace();
//反序列化失敗 - 刪除快取檔案
            if(e instanceof InvalidClassException){
                File data = getFileStreamPath(file);
                data.delete();
            }
        }finally{
            try {
                ois.close();
            } catch (Exception e) {}
            try {
                fis.close();
            } catch (Exception e) {}
        }
        return null;
    }*/

}
