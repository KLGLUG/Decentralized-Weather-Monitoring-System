package com.example.sowmya.smarthomeairmonitor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminMain extends AppCompatActivity {
    private static Button btnTemp, btnHum, btnGas;
   // private EditText et;
    RadioGroup rg;
    String url="";
    RadioButton loc,loc1;
    Spinner sp;
    RadioButton selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        OnClickButtonListenerTemp();
        OnClickButtonListenerHum();
        OnClickButtonListenerGas();
        url=getIntent().getExtras().getString("ip");
        //loc=(RadioButton)findViewById(R.id.loc);
        //loc1=(RadioButton)findViewById(R.id.loc1);
        sp=(Spinner)findViewById(R.id.location);
        List<String> Locations = new ArrayList<String>();
        Locations.add("klu");
        Locations.add("klf");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(AdminMain.this,android.R.layout.simple_spinner_dropdown_item,Locations);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BloodPost_frag.this,R.layout.support_simple_spinner_dropdown_item, bloodgrps);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);


    }

    public void OnClickButtonListenerTemp()
    {
        btnTemp= (Button)findViewById(R.id.button5);
        btnTemp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminMain.this,TempForAdmin.class);


                        intent.putExtra("ip",url);
                        intent.putExtra("type","");
                        intent.putExtra("moni","temp");
                        //intent.putExtra("loc","");
                        //selected=(RadioButton)findViewById(rg.getCheckedRadioButtonId());


                         intent.putExtra("loc",sp.getSelectedItem().toString());
                         startActivity(intent);


                    }
                }
        );
    }

    public void OnClickButtonListenerHum()
    {
        btnHum= (Button)findViewById(R.id.button6);
        btnHum.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminMain.this,TempForAdmin.class);
                        intent.putExtra("ip",url);
                        intent.putExtra("type","");
                        intent.putExtra("moni","hum");
                        //intent.putExtra("loc","");
                        intent.putExtra("loc",sp.getSelectedItem().toString());
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickButtonListenerGas()
    {
        btnGas= (Button)findViewById(R.id.button7);
        btnGas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminMain.this, TempForAdmin.class);
                        intent.putExtra("ip",url);
                        intent.putExtra("type","");
                        intent.putExtra("moni","smoke");
                        //intent.putExtra("loc","");
                        intent.putExtra("loc",sp.getSelectedItem().toString());
                        startActivity(intent);
                    }
                }
        );
    }


}
