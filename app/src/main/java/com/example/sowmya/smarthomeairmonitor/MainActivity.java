package com.example.sowmya.smarthomeairmonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText et;
    EditText pw;
    private Button proceed;
    private EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.editText);
        pw=(EditText)findViewById(R.id.editText2);
        url=(EditText)findViewById(R.id.url);
        OnClickListenerProceed();
    }

    public void OnClickListenerProceed(){



        proceed= (Button)findViewById(R.id.button);
        proceed.setOnClickListener(new View.OnClickListener() {
            ProgressDialog progress= new ProgressDialog(MainActivity.this);
            @Override
            public void onClick(View view) {
                String data = "";
                try {
                    data = URLEncoder.encode("user", "UTF-8")
                            + "=" + URLEncoder.encode(et.getText().toString(),"UTF-8");
                    data +="&"+URLEncoder.encode("pass", "UTF-8")
                            + "="+ URLEncoder.encode(pw.getText().toString(),"UTF-8");
                }catch (Exception e){

                }
                String url2=url.getText().toString();
                Background1 bg= new Background1(MainActivity.this,progress);
                Toast.makeText(MainActivity.this,"connecting..",Toast.LENGTH_LONG).show();
                try{
                    String result =bg.execute(data,"login",url2).get().toString();
                    String result1[]=result.split(",");
                    Toast.makeText(MainActivity.this,"result: "+result,Toast.LENGTH_LONG).show();
                    if(result1[0].equals("general")){
                        Intent intent= new Intent("com.example.sowmya.smarthomeairmonitor.Main2Activity");
                        intent.putExtra("loc",result1[1]);
                        intent.putExtra("ip",url2);
                        startActivity(intent);
                    }
                    else if(result1[0].equals("admin")){
                        Intent intent = new Intent("com.example.sowmya.smarthomeairmonitor.AdminMain");
                        intent.putExtra("ip",url2);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid UserName or Password",Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){}
            }
        });
    }


}
