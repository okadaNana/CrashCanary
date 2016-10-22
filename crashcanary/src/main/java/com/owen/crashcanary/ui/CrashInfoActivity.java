package com.owen.crashcanary.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.owen.crashcanary.R;

public class CrashInfoActivity extends AppCompatActivity {

    private static final String EXTRA_CRASH_INFO = "com.owen.crashcanary.crash_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_info);

        TextView tvCrashInfo = (TextView) findViewById(R.id.tv_crash_info);
        String crashInfo = getIntent().getStringExtra(EXTRA_CRASH_INFO);
        tvCrashInfo.setText(crashInfo);
    }

    public static void actionStart(Context context, String crashInfo) {
        Intent intent = new Intent(context, CrashInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_CRASH_INFO, crashInfo);
        context.startActivity(intent);
    }
}
