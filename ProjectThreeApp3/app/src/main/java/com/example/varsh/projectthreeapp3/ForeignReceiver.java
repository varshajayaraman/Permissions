package com.example.varsh.projectthreeapp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ForeignReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        String place = intent.getStringExtra("place");

        //Decides based on the location chosen by the user.
        if(place.equals("SF")){
            Toast.makeText(context, (place)+"  selected Toast3", Toast.LENGTH_LONG).show();
            Intent sfIntent = new Intent(context, SF.class);
            context.startActivity(sfIntent);
        }
        else{
            Toast.makeText(context, place+"  selected Toast3", Toast.LENGTH_LONG).show();
            Intent nyIntent = new Intent(context, NY.class);
            context.startActivity(nyIntent);
        }
    }
}
