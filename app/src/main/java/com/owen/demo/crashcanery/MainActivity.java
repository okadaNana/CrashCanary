package com.owen.demo.crashcanery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_divide_by_zero).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_throw_nullpointer).setOnClickListener(mOnClickListener);
    }

    private String text;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_divide_by_zero:
                    int wrongNum = 1 / 0;
                    break;
                case R.id.btn_throw_nullpointer:
                    Log.d(TAG, "onClick: " + text.toString());
                    break;
            }
        }
    };
}
