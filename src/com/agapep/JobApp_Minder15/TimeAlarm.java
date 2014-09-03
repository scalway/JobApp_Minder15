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
		CharSequence from = "Pora na spowiedź...";
		CharSequence message = "Co porabiasz?";
		Intent notificationIntent = new Intent(context, MinderActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		Notification notif = new Notification(android.R.drawable.ic_lock_idle_alarm,
				"Co robisz cwaniaczku?", System.currentTimeMillis());
		notif.setLatestEventInfo(context, from, message, contentIntent);
	    notif.defaults |= Notification.DEFAULT_SOUND;
		nm.notify(1, notif);
	}
}
