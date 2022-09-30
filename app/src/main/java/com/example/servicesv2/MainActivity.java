package com.example.servicesv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

    }

    public void lancer(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public void afficher(View view) {
        if(connect){
            int value = service.getNumber();
            textView.setText(String.valueOf(value));
        }

    }

    public void arreter(View view) {
        if(connect){
            unbindService(myConnection);
            connect = false;
        }

    }

    MyService service;
    boolean connect = false;

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder)iBinder;
            service = binder.getService();
            connect = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}