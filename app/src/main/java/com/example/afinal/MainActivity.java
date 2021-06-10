package com.example.afinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout item1,item2,item3,item4,item5,item6;
    Button top1,top2;
    ScrollView sv1,sv2;
    Button go;
    EditText i1ed1,i2ed1,i3ed1,i4ed1,i5ed1,i1ed2,i2ed2,i3ed2,i4ed2,i5ed2;
    float ed[]=new float[5];
    float rate[]=new float[5];
    String name[]=new String[5];
    TextView tv;
    String result="計算比例如下：\n\n";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv1=(ScrollView) findViewById(R.id.sv1);
        sv2=(ScrollView)findViewById(R.id.sv2);
        top1=(Button)findViewById(R.id.top1);
        top1.setOnClickListener(this);
        top2=(Button)findViewById(R.id.top2);
        top2.setOnClickListener(this);

        item1=(LinearLayout)findViewById(R.id.item1);
        item1.setOnClickListener(this);
        item2=(LinearLayout)findViewById(R.id.item2);
        item2.setOnClickListener(this);
        item3=(LinearLayout)findViewById(R.id.item3);
        item3.setOnClickListener(this);
        item4=(LinearLayout)findViewById(R.id.item4);
        item4.setOnClickListener(this);
        item5=(LinearLayout)findViewById(R.id.item5);
        item5.setOnClickListener(this);
        item6=(LinearLayout)findViewById(R.id.item6);
        item6.setOnClickListener(this);

        go=(Button)findViewById(R.id.go);
        go.setOnClickListener(this);
        i1ed1=(EditText) findViewById(R.id.i1ed1);
        i2ed1=(EditText) findViewById(R.id.i2ed1);
        i3ed1=(EditText) findViewById(R.id.i3ed1);
        i4ed1=(EditText) findViewById(R.id.i4ed1);
        i5ed1=(EditText) findViewById(R.id.i5ed1);

        i1ed2=(EditText) findViewById(R.id.i1ed2);
        i2ed2=(EditText) findViewById(R.id.i2ed2);
        i3ed2=(EditText) findViewById(R.id.i3ed2);
        i4ed2=(EditText) findViewById(R.id.i4ed2);
        i5ed2=(EditText) findViewById(R.id.i5ed2);

        tv=(TextView)findViewById(R.id.tv);
    }

    public void onClick(View view) {
        if (view == top1){
            top1.setEnabled(false);
            top2.setEnabled(true);
            sv1.setVisibility(View.VISIBLE);
            sv2.setVisibility(View.INVISIBLE);
        }
        if (view == top2){
            top1.setEnabled(true);
            top2.setEnabled(false);
            sv1.setVisibility(View.INVISIBLE);
            sv2.setVisibility(View.VISIBLE);
        }
        if (view == item1) {startActivity(new Intent(this,item1.class));}

        if (view == item2) {startActivity(new Intent(this,item2.class));}

        if (view == item3) {startActivity(new Intent(this,item3.class));}

        if (view == item4) {startActivity(new Intent(this,item4.class));}

        if (view == item5) {startActivity(new Intent(this,item5.class));}

        if (view == item6) {startActivity(new Intent(this,item6.class));}

        if (view == go){
            result="計算比例如下：\n\n";
            String a=i1ed2.getText().toString();
            String b=i2ed2.getText().toString();
            String c=i3ed2.getText().toString();
            String d=i4ed2.getText().toString();
            String e=i5ed2.getText().toString();
            name[0]=i1ed1.getText().toString();
            name[1]=i2ed1.getText().toString();
            name[2]=i3ed1.getText().toString();
            name[3]=i4ed1.getText().toString();
            name[4]=i5ed1.getText().toString();
            if (a.length() > 0 && b.length() > 0 && c.length() > 0 && d.length() > 0 && e.length() > 0) {
                ed[0]=Float.valueOf(a);
                ed[1]=Float.valueOf(b);
                ed[2]=Float.valueOf(c);
                ed[3]=Float.valueOf(d);
                ed[4]=Float.valueOf(e);
                dialog();
            }else{
                Toast.makeText(this,"有資料為空值，請填入0或輸入數字",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Calculate(){
        for (int i=0;i<ed.length;i++){
            rate[i]=(ed[i]/ed[0]);
        }
    }
    private void dialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View v = inflater.inflate(R.layout.alert, null); //final一個editText
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("請輸入"+name[0]+"的公克數")
                .setView(v)
                .setNegativeButton("取消", null)
                .setPositiveButton("確定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) (v.findViewById(R.id.editText1));
                        String k=editText.getText().toString();
                        Calculate();
                        ed[0]=Float.valueOf(k);
                        for (int i=0;i<ed.length;i++){
                            ed[i]=ed[0]*rate[i];
                            result+="   "+name[i]+" - "+Math.round(ed[i])+"g\n";
                        }
                        tv.setText(result);
                    }
                }).show();
    }


}