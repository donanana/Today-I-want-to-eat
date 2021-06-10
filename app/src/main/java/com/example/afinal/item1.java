package com.example.afinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

public class item1 extends AppCompatActivity implements View.OnClickListener{

    String a[]={"地瓜","樹薯粉","糖"};
    float b[]={100,70,30};
    String b_change[]=new String[b.length];
    float rate[]=new float[b.length];
    String c[]={"1.","2.","3.","4."};
    String d[]={"地瓜去皮切塊，蒸熟後加入砂糖\n搗成泥","在地瓜泥中加入樹薯粉拌勻，製作成地瓜麵團","把地瓜麵糰搓成一顆顆小球，準\n備下鍋","炸地瓜球"};
    ListView lv1,lv2,lv3,lv4;
    ArrayAdapter<String> am,bm,cm,dm;
    Button cal;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1);

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
        LayoutInflater inflater = LayoutInflater.from(item1.this);
        final View v = inflater.inflate(R.layout.alert, null); //final一個editText
        new AlertDialog.Builder(item1.this)
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

                        bm=new ArrayAdapter<String>(item1.this,android.R.layout.simple_list_item_checked,b_change);
                        lv2.setAdapter(bm);
                    }
                }).show();
    }

}