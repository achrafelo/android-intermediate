package com.possiblelabs.alarmtest;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private EditText ediDate;
    private EditText ediTime;

    private Calendar currentCalendar;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    public static final int DATE_DIALOG_ID = 0;
    public static final int TIME_DIALOG_ID = 1;

    public static final int ALARM_ID = 0;

    private PendingIntent pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ediDate = (EditText) findViewById(R.id.ediDate);
        ediTime = (EditText) findViewById(R.id.ediTime);
        loadNowTime();
    }

    private void loadNowTime() {
        currentCalendar = Calendar.getInstance();
        TimeZone tz = currentCalendar.getTimeZone();
        Log.d("FULL TIME", String.format("%1$tA %1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", currentCalendar));
        Log.d("Time zone", "=" + tz.getDisplayName());
        currentCalendar.setTimeInMillis(System.currentTimeMillis());
        hour = currentCalendar.get(Calendar.HOUR_OF_DAY);
        minute = currentCalendar.get(Calendar.MINUTE);
        year = currentCalendar.get(Calendar.YEAR);
        month = currentCalendar.get(Calendar.MONTH);
        day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        updateDate();
        updateTime();
        updateCalendar();
    }

    public void doClickDate(View view) {
        showDialog(DATE_DIALOG_ID);
    }

    public void doClickTime(View view) {
        showDialog(TIME_DIALOG_ID);
    }

    public void addAlarm(View view) {
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pending = PendingIntent.getBroadcast(this, ALARM_ID, intent, 0);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis(), pending);

        Toast.makeText(this, "Alarm added", Toast.LENGTH_SHORT).show();
    }

    public void removeAlarm(View view) {
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmMgr.cancel(pending);
        Toast.makeText(this, "Alarm removed", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, hour, minute, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            MainActivity.this.year = year;
            MainActivity.this.month = monthOfYear;
            MainActivity.this.day = dayOfMonth;
            updateDate();
            updateCalendar();
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            MainActivity.this.hour = hourOfDay;
            MainActivity.this.minute = minute;
            updateTime();
            updateCalendar();
        }
    };


    private void updateDate() {
        ediDate.setText(new StringBuilder()
                .append(day)
                .append("-")
                .append(month + 1)
                .append("-")
                .append(year).append(" "));

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void updateTime() {
        ediTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
    }

    private void updateCalendar() {
        currentCalendar.set(year, month, day, hour, minute, 0);
    }
}
