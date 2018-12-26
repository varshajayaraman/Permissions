package com.example.varsh.projectthreeapp1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        //Passes customised action based on the button selected.

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                checkForPermissionAndAct();

            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                checkForPermissionAndActButton2();

            }

        });
    }


    public void checkForPermissionAndAct(){
        Log.i("MainActivity", "checkForPermissionAndAct for b1");
        if(ContextCompat.checkSelfPermission(this, "edu.uic.cs478.f18.project3")==PackageManager.PERMISSION_GRANTED){
            Log.i("MainActivity", "sent action");
            Intent broadcastIntent = new Intent("com.example.varsh.projectthreeapp1.SAN FRANSISCO");
            broadcastIntent.putExtra("place", "SF");
            broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendOrderedBroadcast(broadcastIntent, "edu.uic.cs478.f18.project3") ;

        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.f18.project3"}, 1);
        }

    }

    public void checkForPermissionAndActButton2(){
        Log.i("MainActivity", "checkForPermissionAndAct for b2");
        if(ContextCompat.checkSelfPermission(this, "edu.uic.cs478.f18.project3")==PackageManager.PERMISSION_GRANTED){

            Log.i("MainActivity", "hasperm");
            Intent broadcastIntent = new Intent("com.example.varsh.projectthreeapp1.NEW YORK");
            broadcastIntent.putExtra("place", "NY");
            broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendOrderedBroadcast(broadcastIntent, "edu.uic.cs478.f18.project3") ;

        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.f18.project3"}, 2);
        }

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results){

        if(results.length>0){

            int permissionIndex = Arrays.asList(permissions).indexOf("edu.uic.cs478.f18.project3");
            Log.i("MainActivity", "perm: "+permissionIndex);
            if(results[permissionIndex] == PackageManager.PERMISSION_GRANTED){
                Log.i("MainActivity", "inonResultPerm");
                Intent broadcastIntent;
                if(requestCode==1) {
                    broadcastIntent = new Intent("com.example.varsh.projectthreeapp1.SAN FRANSISCO");
                    broadcastIntent.putExtra("place", "SF");
                }
                else{
                    broadcastIntent = new Intent("com.example.varsh.projectthreeapp1.NEW YORK");
                    broadcastIntent.putExtra("place", "NY");
                    Log.i("MainActivity", "gotpermfor2");
                }
                broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendOrderedBroadcast(broadcastIntent, "edu.uic.cs478.f18.project3") ;
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
