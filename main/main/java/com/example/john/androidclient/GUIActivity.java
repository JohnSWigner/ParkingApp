package com.example.john.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

public class GUIActivity extends AppCompatActivity {

    Button button;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        final String addr = "10.0.2.2";
        final int port = 2345;
        ImageView iv = (ImageView) findViewById(R.id.imageView2);
        com.example.john.androidclient.Client myClient = new com.example.john.androidclient.Client(addr, port, iv);
        myClient.execute();

    }
}
