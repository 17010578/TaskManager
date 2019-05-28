package com.myapplicationdev.android.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btnAdd;

    ArrayAdapter aa;
    ArrayList<Task> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.listView);

        DBHelper db = new DBHelper(MainActivity.this);

        aa = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1 ,task);
        task = db.getTasks();

        if (task.size() != 0){
            lv.setAdapter(aa);
            aa.notifyDataSetChanged();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,12345);
            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 12345){
//            DBHelper db = new DBHelper(MainActivity.this);
//            task.clear();
//            task = db.getTasks();
//            aa = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1 ,task);
//            lv.setAdapter(aa);
//        }
//    }
}
