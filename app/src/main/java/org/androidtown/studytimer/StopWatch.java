package org.androidtown.studytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class StopWatch extends AppCompatActivity implements View.OnClickListener {

    private Chronometer mChronometer;
    private long timeWhenStopped = 0;
    private boolean stopClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);

        mChronometer = (Chronometer) findViewById(R.id.chronometer_view);
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h = (int)(time /3600000);
                int m = (int)(time - h*3600000)/60000;
                int s = (int)(time - h*3600000- m*60000)/1000 ;
                String t = (h < 10 ? "0"+h: h)+ ":" +(m < 10 ? "0"+m: m)+ ":" + (s < 10 ? "0"+s: s);
                chronometer.setText(t);
            }
        });
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setText("00:00:00");

        //버튼 가져오기
        Button startBtn = (Button) findViewById(R.id.start_button);
        Button pauseBtn = (Button) findViewById(R.id.pause_button);
        Button resetBtn = (Button) findViewById(R.id.reset_button);
        Button storeBtn = (Button) findViewById(R.id.store_button);

        //버튼 이벤트 연결
        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        storeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //버튼 이벤트 처리
        switch (v.getId()){
            case R.id.start_button:
                mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                mChronometer.start();
                stopClicked = false;
                break;
            case R.id.pause_button:
                if (!stopClicked) {
                    timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                    mChronometer.stop();
                    stopClicked = true;
                    break;
                }
            case R.id.reset_button:
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.stop();
                timeWhenStopped = 0;
                break;
            case R.id.store_button:
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.stop();
                timeWhenStopped = 0;
                break;
        }
    }
}
