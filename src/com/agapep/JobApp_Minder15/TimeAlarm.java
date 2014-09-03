package com.agapep.JobApp_Minder15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeAlarm extends BroadcastReceiver {
	NotificationManager nm;
	@Override
	public void onReceive(Context context, Intent intent) {
		nm = (NotificationManager) context
		.getSystemService(Context.NOTIFICATION_SERVICE);
		CharSequence from = "Pobrano";
		CharSequence message = "Plik: Ewangelia_Łukasza.mp3";
		Intent notificationIntent = new Intent(context, MinderActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		Notification notif = new Notification(android.R.drawable.ic_lock_idle_alarm,
				"posłuchaj tego ...", System.currentTimeMillis());
		notif.setLatestEventInfo(context, from, message, contentIntent);
	    notif.defaults |= Notification.DEFAULT_SOUND;
		nm.notify(1, notif);
	}
}
