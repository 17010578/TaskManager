package com.myapplicationdev.android.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDes,etSec;
    Button btnAdd, btnCancel;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDes = findViewById(R.id.editTextDes);
        etName = findViewById(R.id.editTextName);
        etSec = findViewById(R.id.editTextSec);
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.buttonCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String desc = etDes.getText().toString();
                String sec = etSec.getText().toString();


                if (name.length() == 0) {
                    Toast.makeText(getBaseContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                } else if (desc.length() == 0) {
                    Toast.makeText(getBaseContext(), "Description cannot be empty", Toast.LENGTH_LONG).show();
                }
                else if (sec.length() == 0){
                    Toast.makeText(getBaseContext(), "Seconds cannot be empty", Toast.LENGTH_LONG).show();
                }
                else{
                    int seconds = Integer.parseInt(sec);
                    DBHelper db = new DBHelper(AddActivity.this);
                    db.getWritableDatabase();
                    long result = db.insertTask(name,desc);

                    Intent i = new Intent(AddActivity.this, BroadcastTaskReceiver.class);
                    i.putExtra("name", name);

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, seconds);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToMain = new Intent(AddActivity.this, MainActivity.class);
                startActivity(moveToMain);
                finish();
            }
        });
    }
}
