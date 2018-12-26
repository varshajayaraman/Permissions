package com.example.varsh.projectthreeapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ForeignReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("place")+" is selected. Toast2", Toast.LENGTH_SHORT).show();
    }
}
