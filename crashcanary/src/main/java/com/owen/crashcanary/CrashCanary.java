package com.owen.crashcanary;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.owen.crashcanary.model.CrashCause;
import com.owen.crashcanary.model.CrashLogs;
import com.owen.crashcanary.ui.CrashInfoActivity;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashCanary implements Thread.UncaughtExceptionHandler {

    private Context mContext;

    public static CrashCanary install(Application context) {
        return new CrashCanary(context);
    }

    private CrashCanary(Context context) {
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        CrashLogs logs = getCrashLogs(ex);
        CrashInfoActivity.actionStart(mContext, logs);
        exitApp();
    }

    @NonNull
    private CrashLogs getCrashLogs(Throwable ex) {
        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        ex.printStackTrace(printWriter);

        CrashLogs logs = new CrashLogs();
        CrashCause cause = new CrashCause(strWriter.toString());
        logs.add(cause);

        Throwable nextCause = ex.getCause();
        while (nextCause != null) {
            nextCause.printStackTrace(printWriter);
            nextCause = nextCause.getCause();

            logs.add(new CrashCause(strWriter.toString()));
        }
        printWriter.close();
        return logs;
    }

    private void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
