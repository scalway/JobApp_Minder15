package com.agapep.JobApp_Minder15;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by slovic on 03.09.14.
 */
public class DoSomethingImportantService extends IntentService {

    public DoSomethingImportantService() {
        super("DOSOMETHING");
    }


    @Override
    public void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        Log.d("MinderApp", "startuje pobieranie");
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            File outputFile = new File(getExternalFilesDir(null), "Ewangelia.mp3");
            Log.d("MinderApp","Outputfile:" + outputFile.getAbsolutePath());
            OutputStream output = new FileOutputStream(outputFile);

            byte data[] = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("MinderApp", "kończe pobieranie");
        setAlarm();
        Log.i("SimpleWakefulReceiver", "Completed service @ " + SystemClock.elapsedRealtime());
        DoSomethingImportantReceiver.completeWakefulIntent(intent);
    }

    public void setAlarm() {
        Log.d("MinderApp", "ustawiam alarm");
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimeAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //set alarm on
        am.set(AlarmManager.RTC_WAKEUP, new Date().getTime() , pendingIntent);
    }
}
