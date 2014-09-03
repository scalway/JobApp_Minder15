package com.agapep.JobApp_Minder15;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
/**
 * Created by slovic on 03.09.14.
 */
public class DoSomethingImportantReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, DoSomethingImportantService.class);
        service.putExtra("url", "http://www.biblia-mp3.pl/Ewangelia_Mateusza_1-7.mp3");
        startWakefulService(context, service);
    }
}
