package com.example.varsh.projectthreeapp2;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    Button button1;
    BroadcastReceiver fReceiver1 = new ForeignReceiver();    //Dynamic Receivers
    IntentFilter fFilter1 = new IntentFilter("com.example.varsh.projectthreeapp1.SAN FRANSISCO");

    BroadcastReceiver fReceiver2 = new ForeignReceiver();
    IntentFilter fFilter2 = new IntentFilter("com.example.varsh.projectthreeapp1.NEW YORK");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                checkForPermissionAndAct();

            }

        });

    }

    public void checkForPermissionAndAct(){
        Log.i("MainActivity", "checkForPermissionAndAct");
        if(ContextCompat.checkSelfPermission(this, "edu.uic.cs478.f18.project3")==PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.f18.project3"}, 0);
        }
        else{
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            fFilter1.setPriority(100);
            fFilter2.setPriority(100);
            registerReceiver(fReceiver1, fFilter1, "edu.uic.cs478.f18.project3", null); //Registers receivers if permission  is already granted
            registerReceiver(fReceiver2, fFilter2, "edu.uic.cs478.f18.project3", null);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results){
        if(results.length>0){

            int permissionIndex = Arrays.asList(permissions).indexOf("edu.uic.cs478.f18.project3");
            Log.i("MainActivity", "perm: "+permissionIndex);
            if(results[permissionIndex] == PackageManager.PERMISSION_GRANTED){
                fFilter1.setPriority(100);
                fFilter2.setPriority(100);
               // Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                registerReceiver(fReceiver1, fFilter1, "edu.uic.cs478.f18.project3", null);   //Registers receivers after requesting for permission
                registerReceiver(fReceiver2, fFilter2, "edu.uic.cs478.f18.project3", null);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG);
            }
        }
    }
}
