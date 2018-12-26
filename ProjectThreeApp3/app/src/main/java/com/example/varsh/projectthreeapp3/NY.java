package com.example.varsh.projectthreeapp3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class NY extends AppCompatActivity {

    //Displays a toast for NY.
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "NEW YORK Information is under construction. Locations of interest in NEW YORK are being collected...", Toast.LENGTH_LONG).show();
    }
}
