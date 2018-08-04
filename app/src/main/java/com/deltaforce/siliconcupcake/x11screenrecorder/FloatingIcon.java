package com.deltaforce.siliconcupcake.x11screenrecorder;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FloatingIcon extends Service {
    Boolean isRec;
    ImageView stop,start;
    LinearLayout startRec;
    LinearLayout stopRec;
    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;
    WindowManager mWindowManager;
    View floatingView;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public FloatingIcon()
    {

    }
    @Override
    public void onCreate() {
        isRec = false;
        super.onCreate();


        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.x = 0;
        params.y = 0;

        floatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_icon, null);

        startRec = floatingView.findViewById(R.id.start_layout);
        stopRec = floatingView.findViewById(R.id.stop_layout);
        stop = floatingView.findViewById(R.id.stop);
        start = floatingView.findViewById(R.id.start);

        startRec.setVisibility(View.VISIBLE);
        stopRec.setVisibility(View.GONE);

        floatingView.findViewById(R.id.loc).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        initialX = params.x;
                        initialY = params.y;

                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        mWindowManager.updateViewLayout(floatingView, params);
                        return true;
                }
                return false;
            }
        });
        floatingView.findViewById(R.id.loc1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        initialX = params.x;
                        initialY = params.y;

                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        mWindowManager.updateViewLayout(floatingView, params);
                        return true;
                }
                return false;
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Recording
                isRec = true;
                startRec.setVisibility(View.GONE);
                stopRec.setVisibility(View.VISIBLE);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stop Recording
                isRec = false;
                stopRec.setVisibility(View.GONE);
                startRec.setVisibility(View.VISIBLE);
            }
        });
        floatingView.findViewById(R.id.exit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit
            }
        });
        floatingView.findViewById(R.id.exit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit
            }
        });
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(floatingView, params);


    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(floatingView!=null)
            mWindowManager.removeViewImmediate(floatingView);
    }

}

