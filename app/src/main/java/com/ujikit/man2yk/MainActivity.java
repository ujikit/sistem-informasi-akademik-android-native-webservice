package com.ujikit.man2yk;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int splashInterval = 2000;
    private static final int LENGTH_LONG = 6500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo!=null && nInfo.isConnected()){
//            Toast.makeText(this, "Internet Connection", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(MainActivity.this, a_login.class);
                    startActivity(i);

                }
            }, splashInterval);
        }
        else{
//          Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
            final Toast toast = Toast.makeText(
                    MainActivity.this,
                    "10 second",
                    Toast.LENGTH_SHORT);
            toast.show();

            new CountDownTimer(10000, 1000)
            {
                int count = 10;

                public void onTick(long millisUntilFinished) {
//                    toast.show();
//                    count++;
////                    toast.setText(count + " sec");
//                    toast.setText("Tidak Ada Koneksi Internet & Restart Aplikasi dan Coba Masuk Lagi!");

                    Toast t=Toast.makeText(getApplicationContext(),"Text",Toast.LENGTH_LONG);
                    t.setText("Tidak Ada Koneksi Internet & Restart Aplikasi dan Coba Masuk Lagi!");
                    t.setGravity(0, 0, 0);
                    t.show();

                }

                public void onFinish() {
                    toast.cancel();

                }
            }.start();
        }

    }
}
