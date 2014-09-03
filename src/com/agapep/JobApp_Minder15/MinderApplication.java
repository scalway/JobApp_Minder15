package com.agapep.JobApp_Minder15;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by slovic on 03.09.14.
 */
public class MinderApplication extends Application {
    private AlarmManager am;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getBaseContext(), "startnąłem się", Toast.LENGTH_SHORT).show();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setAlarm();
    }

    public void setAlarm() {
        Intent intent = new Intent(this, TimeAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //set  time to 15:00
        Date now = new Date();
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, 15);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
//        long oneDayInterval = 86400; //fake one day
        long oneDayInterval = 86400000; //one day 1000*100*60:60*24
        if (time < now.getTime()) time += oneDayInterval;
        Log.d("MinderApp", "startuje alarm");
        //set alarm on
        am.setRepeating(AlarmManager.RTC_WAKEUP, time , oneDayInterval , pendingIntent);
    }
}
