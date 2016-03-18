package project.scu.edu.chew.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import project.scu.edu.chew.R;

// Thank you Screen - Activity to display thankyou on viewing notification
public class OrderStausActivity extends AppCompatActivity {

    ImageButton alarmButton;
    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 20;


    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_staus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        setup();

        alarmButton = (ImageButton) findViewById(R.id.image);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                am.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +
                        TWENTY_SECONDS, pi );
            }
        });
    }

    private void setup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                Toast.makeText(c, "Your order has been placed", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(br, new IntentFilter("com.authorwjf.wakeywakey"));
        pi = PendingIntent.getBroadcast( this, 0, new Intent("com.authorwjf.wakeywakey"),
                0 );
        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
    }



}
