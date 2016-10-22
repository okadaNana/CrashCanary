package com.owen.crashcanary.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owen.crashcanary.R;
import com.owen.crashcanary.adapter.CrashListAdapter;
import com.owen.crashcanary.model.CrashLogs;

public class CrashInfoActivity extends AppCompatActivity {

    private static final String EXTRA_CRASH_INFO = "com.owen.crashcanary.crash_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_info);

        CrashLogs logs = (CrashLogs) getIntent().getSerializableExtra(EXTRA_CRASH_INFO);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_crash_log);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CrashListAdapter(logs.getCauses()));
    }

    public static void actionStart(Context context, CrashLogs crashInfo) {
        Intent intent = new Intent(context, CrashInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_CRASH_INFO, crashInfo);
        context.startActivity(intent);
    }
}
