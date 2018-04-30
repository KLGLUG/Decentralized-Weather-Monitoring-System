package com.example.sowmya.smarthomeairmonitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by appu on 22/7/17.
 */
public class Background1 extends AsyncTask{
    Context cn;
    String result="";

    ProgressDialog progress;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progress.setTitle("Connecting Server");
        progress.setMessage("Please wait until registration complete");
        progress.setIndeterminate(true);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    Background1(Context cn1,ProgressDialog pd){
        cn=cn1;
        progress=pd;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        try {

            try {

                //Toast.makeText(cn.getApplicationContext(), "m1 pass", Toast.LENGTH_LONG).show();

                URL url;

                    url=new URL("http://"+params[2]+"/AirMonitor/login.jsp");
                Log.d("background", url.toString());
                //else
                  //  url=new URL("https://appukck.000webhostapp.com/appserver/request.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Toast.makeText(cn.getApplicationContext(),"m2 pass", Toast.LENGTH_LONG).show();

                bufferedWriter.write(params[0].toString());
                Log.d("back ground", params[0].toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                String line = "";
                //Toast.makeText(cn.getApplicationContext(), "m3 pass", Toast.LENGTH_LONG).show();
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("result", result);
                //Toast.makeText(cn.getApplicationContext(), result, Toast.LENGTH_LONG).show();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progress.dismiss();
    }
}
