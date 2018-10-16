package com.startandroid.biimka.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private Student studentSet;

    public final static String TEXT = "TEXT";
    public final static String RESULT = "RESULT";
    private EditText editTextName, editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        final Toolbar toolbarSecond = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarSecond);
        getSupportActionBar().setTitle(R.string.studentDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarSecond.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        editTextName = (EditText) findViewById(R.id.editTextSecondActivityName);
        editTextAge = (EditText) findViewById(R.id.editTextSecondActivityAge);
        final Student student = (Student) getIntent().getParcelableExtra(TEXT);

        editTextName.setText(student.getName());
        editTextAge.setText(String.valueOf(student.getAge()));

        ((Button) findViewById(R.id.buttonSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                studentSet = new Student(editTextName.getText().toString(), Integer.parseInt(editTextAge.getText().toString()), student.getId());
                intent.putExtra(RESULT, studentSet);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
