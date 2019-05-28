package com.myapplicationdev.android.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDes;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDes = findViewById(R.id.editTextDes);
        etName = findViewById(R.id.editTextName);
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.buttonCancel);
    }


}
