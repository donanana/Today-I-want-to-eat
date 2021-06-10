package com.example.afinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class item2 extends AppCompatActivity implements View.OnClickListener{

    String a[]={"樹薯粉","果汁","砂糖","玉米粉"};
    float b[]={150,450,50,25};
    String b_change[]=new String[b.length];
    float rate[]=new float[b.length];
    String c[]={"1.","2.","3.","4.","5","6"};
    String d[]={"拿一個耐熱容器，裡面抹上少許\n油防沾黏","玉米粉倒入炒鍋裡小火乾炒3-4分鐘，取出放涼備用","將樹薯粉、果汁、砂糖拌勻至無顆粒狀","開小火持續攪拌到呈濃稠微透明狀就可以關火","趁熱倒入作法1的容器裡，表面抹平，電鍋蒸至呈現透明狀","將涼糕放涼後脫模，切成合適的方塊狀，沾上熟玉米粉"};
    ListView lv1,lv2,lv3,lv4;
    ArrayAdapter<String> am,bm,cm,dm;
    Button cal;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);

        lv1=(ListView)findViewById(R.id.lv1);
        am=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
        lv1.setAdapter(am);
        justifyListViewHeightBasedOnChildren(lv1);

        for(int i=0;i<b.length;i++){
            b_change[i]=String.valueOf(Math.round(b[i]))+"g";
        }

        lv2=(ListView)findViewById(R.id.lv2);
        lv2.setChoiceMode(lv2.CHOICE_MODE_MULTIPLE);
        bm=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,b_change);
//        bm.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv2.setAdapter(bm);
        justifyListViewHeightBasedOnChildren(lv2);

        lv3=(ListView)findViewById(R.id.lv3);
        cm=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,c);
        lv3.setAdapter(cm);
        justifyListViewHeightBasedOnChildren(lv3);

        lv4=(ListView)findViewById(R.id.lv4);
        dm=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,d);
//        bm.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv4.setAdapter(dm);
        justifyListViewHeightBasedOnChildren(lv4);

        cal=(Button)findViewById(R.id.cal);
        cal.setOnClickListener(this);
    }
    public void Calculate(){
        for (int i=0;i<b.length;i++){
            rate[i]=(b[i]/b[0]);
        }
    }
    public void onClick(View view) {
        if (view == cal){
            dialog();
        }
    }

    public static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    private void dialog() {
        LayoutInflater inflater = LayoutInflater.from(item2.this);
        final View v = inflater.inflate(R.layout.alert, null); //final一個editText
        new AlertDialog.Builder(item2.this)
                .setTitle("請輸入"+a[0]+"的公克數")
                .setView(v)
                .setNegativeButton("取消", null)
                .setPositiveButton("確定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) (v.findViewById(R.id.editText1));
                        String k=editText.getText().toString();
                        Calculate();
                        b[0]=Float.valueOf(k);
                        for (int i=0;i<b.length;i++){
                            b[i]=b[0]*rate[i];
                            b_change[i]=String.valueOf(Math.round(b[i]))+"g";
                        }

                        bm=new ArrayAdapter<String>(item2.this,android.R.layout.simple_list_item_checked,b_change);
                        lv2.setAdapter(bm);
                    }
                }).show();
    }

}




