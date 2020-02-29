package org.androidtown.studytimer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnYearMonthPicker;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            Log.d("StudyTimer", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
            Intent intent=new Intent(MainActivity.this,Statistics.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editBtn = findViewById(R.id.edit_button);
        Button statisticsBtn = findViewById(R.id.statistics_button);
        Button stopwatchBtn = findViewById(R.id.stopwatch_button);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit.class);
                startActivity(intent);
            }
        });

        statisticsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearMonthPicker pd = new MyYearMonthPicker();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });

        stopwatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StopWatch.class);
                startActivity(intent);
            }
        });

        btnYearMonthPicker = findViewById(R.id.statistics_button);

        btnYearMonthPicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearMonthPicker pd = new MyYearMonthPicker();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "StudyTimer");
            }
        });
    }
}
