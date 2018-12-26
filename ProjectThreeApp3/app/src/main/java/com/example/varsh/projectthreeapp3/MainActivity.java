package com.example.varsh.projectthreeapp3;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

import static android.support.v7.app.AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        checkForPermissionAndAct();

        }

    //Checks for permission and requests if not granted.
    public void checkForPermissionAndAct(){
        Log.i("MainActivity", "checkForPermissionAndAct for b1");
        if(ContextCompat.checkSelfPermission(this, "edu.uic.cs478.f18.project3")==PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();


        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.f18.project3"}, 1);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results){

        if(results.length>0){

            int permissionIndex = Arrays.asList(permissions).indexOf("edu.uic.cs478.f18.project3");
            Log.i("MainActivity", "perm: "+permissionIndex);
            if(results[permissionIndex] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();


            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
