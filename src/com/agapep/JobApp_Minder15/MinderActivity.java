package com.agapep.JobApp_Minder15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MinderActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.textView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("MinderApp", "start downloading from activity");
        Intent intent = new Intent(getBaseContext(), DoSomethingImportantService.class);
        intent.putExtra("url", "http://www.biblia-mp3.pl/Ewangelia_Mateusza_1-7.mp3");
        startService(intent);
    }
}
