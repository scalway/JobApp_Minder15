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
        Toast.makeText(getBaseContext(), "start application", Toast.LENGTH_SHORT).show();
        Log.d("MinderApp", "start application");
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setUpBackupShedule();
    }

    public void setUpBackupShedule() {
        Intent intent = new Intent(this, DoSomethingImportantReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //set  time to 15:00
        Date now = new Date();
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        if (time < now.getTime()) time += AlarmManager.INTERVAL_DAY;
        Log.d("MinderApp", "ustawiam pobieranie");
        am.setRepeating(AlarmManager.RTC_WAKEUP, time , AlarmManager.INTERVAL_DAY , pendingIntent);
    }
}
