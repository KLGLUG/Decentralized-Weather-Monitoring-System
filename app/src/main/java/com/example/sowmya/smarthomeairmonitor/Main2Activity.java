package com.example.sowmya.smarthomeairmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {

    private static Button btnTemp, btnHum, btnGas;
    //private EditText et;
    String url="";
    //RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      //  et=(EditText)findViewById(R.id.ip);
      //  rg=(RadioGroup)findViewById(R.id.location);
        OnClickButtonListenerTemp();
        OnClickButtonListenerHum();
        OnClickButtonListenerGas();
        url=getIntent().getExtras().getString("ip");
    }

    public void OnClickButtonListenerTemp()
    {
     btnTemp= (Button)findViewById(R.id.button2);
     btnTemp.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(Main2Activity.this, TempForAdmin.class);
                     intent.putExtra("ip",url);
                     intent.putExtra("type","");
                     intent.putExtra("moni","temp");
                     intent.putExtra("loc",getIntent().getExtras().getString("loc"));
                     /*if(rg.getCheckedRadioButtonId()==R.id.loc){
                         intent.putExtra("loc","klef");
                     }else {
                         intent.putExtra("loc","klu");
                     }*/
                     startActivity(intent);
                 }
             }
     );
    }


    public void OnClickButtonListenerHum()
    {
        btnHum= (Button)findViewById(R.id.button3);
        btnHum.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Main2Activity.this,TempForAdmin.class);
                        intent.putExtra("ip",url);
                        intent.putExtra("type","");
                        intent.putExtra("moni","hum");
                        intent.putExtra("loc",getIntent().getExtras().getString("loc"));
                        /*if(rg.getCheckedRadioButtonId()==R.id.loc){
                            intent.putExtra("loc","klef");
                        }else {
                            intent.putExtra("loc","klu");
                        }*/
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickButtonListenerGas()
    {
        btnGas= (Button)findViewById(R.id.button4);
        btnGas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Main2Activity.this,TempForAdmin.class);
                        intent.putExtra("ip",url);
                        intent.putExtra("type","");
                        intent.putExtra("moni","smoke");
                        intent.putExtra("loc",getIntent().getExtras().getString("loc"));
                        startActivity(intent);
                    }
                }
        );
    }

}