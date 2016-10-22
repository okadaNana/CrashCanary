package com.owen.crashcanary;


import android.app.Application;
import android.content.Context;

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
    public void uncaughtException(Thread t, Throwable e) {
        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        e.printStackTrace(printWriter);

        Throwable nextCause = e.getCause();
        while (nextCause != null) {
            nextCause.printStackTrace(printWriter);
            nextCause = nextCause.getCause();
        }

        String crashInfo = strWriter.toString();
        printWriter.close();

        CrashInfoActivity.actionStart(mContext, crashInfo);

        exitApp();
    }

    private void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
