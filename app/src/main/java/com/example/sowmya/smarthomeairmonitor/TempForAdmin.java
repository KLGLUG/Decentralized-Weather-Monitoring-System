package com.example.sowmya.smarthomeairmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class TempForAdmin extends AppCompatActivity {
    WebView wv;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_for_admin);
        wv = (WebView) findViewById(R.id.webView);
        spinner = (ProgressBar)findViewById(R.id.progressBar);

        //FirebaseMessaging.getInstance().subscribeToTopic("airpollution");
        //String token=FirebaseInstanceId.getInstance().getToken();
       // Log.d("Token firebase: ", token);
       // Toast.makeText(this,token,Toast.LENGTH_LONG).show();
        // String url = "http://192.168.0.7:8095/storeappfinal1/index.jsp";
       final String url = getIntent().getExtras().getString("ip");
       final String type = getIntent().getExtras().getString("type");
       final String moni = getIntent().getExtras().getString("moni");
       final String loc = getIntent().getExtras().getString("loc");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setSupportZoom(false);
        wv.getSettings().setAllowContentAccess(true);
        //wv.getSettings().setAllowFileAccessFromFileURLs(true);
        //wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setHorizontalScrollBarEnabled(false);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://"+url+"/AirMonitor/Graph.jsp?moni="+moni+"&loc="+loc);
        Log.d("url ", "http://192.168.43.43:8091/AirMonitor/Graph.jsp?moni="+moni+"&loc="+loc);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                wv.loadUrl("http://192.168.43.43:8091/AirMonitor/Graph.jsp?moni="+moni+"&loc="+loc);
                Log.d("url ", "http://192.168.43.43:8091/AirMonitor/Graph.jsp?moni="+moni+"&loc="+loc);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
        class WebViewClient extends android.webkit.WebViewClient {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                try {
                    // TODO Auto-generated method stub
                    super.onPageStarted(view, url, favicon);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                spinner.setVisibility(View.GONE);
            }
        }
    }
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            try {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            spinner.setVisibility(View.GONE);
        }
    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected=activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
        if(isConnected!=true){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Please check your internet Connection");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                }
            });
            builder.setNegativeButton("go back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog b=builder.create();
            b.show();
        }
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
