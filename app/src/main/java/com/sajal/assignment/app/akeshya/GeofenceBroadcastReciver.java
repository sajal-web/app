package com.sajal.assignment.app.akeshya;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
import java.util.Map;

public class GeofenceBroadcastReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

//
//        Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_LONG).show();
        MessageHelper messageHelper = new MessageHelper(context);

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            Log.i("onReceive","ERROR RECEIVING GEOFENCE EVENT...");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence : geofenceList){
            Log.i("onReceive",geofence.getRequestId());
        }
        int transitionType =geofencingEvent.getGeofenceTransition();
        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "You Have Reached Your Destination", Toast.LENGTH_LONG).show();
                messageHelper.sendHighPriorityNotification("Arrived","You Have Reached Your Destination",MapsActivity.class);

                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
//                messageHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL","You arrived your destination...",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
//                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
//                messageHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT","You arrived your destination...",MapsActivity.class);
                break;
        }
    }
}