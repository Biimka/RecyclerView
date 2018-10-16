package com.startandroid.biimka.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    final static String KEY = "KEY";
    private MyAdapter adapter;
    private Student[] students = new Student[10];


    private void initializeData() {
        students[0] = new Student("Артем Скляниченко", 24, 1);
        students[1] = new Student("Никита Левченко", 24, 2);
        students[2] = new Student("Вячеслав Ломовцев", 25, 3);
        students[3] = new Student("Людмила Тунина", 24, 4);
        students[4] = new Student("Андрей Самолов", 26, 5);
        students[5] = new Student("Павел Васильев", 23, 6);
        students[6] = new Student("Валентина Бабий", 25, 7);
        students[7] = new Student("Денис Данилов", 23, 8);
        students[8] = new Student("Ирина Захарова", 23, 9);
        students[9] = new Student("Павел Захаров", 24, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            students = (Student[]) savedInstanceState.getParcelableArray(KEY);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher_foreground);

        initializeData();
        adapter = new MyAdapter(students);
        adapter.setListener(listener);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private MyAdapter.Listener listener = new MyAdapter.Listener() {
        @Override
        public void onItemClicked(Student item) {
            startActivityForResult(new Intent(MainActivity.this, SecondActivity.class).putExtra(SecondActivity.TEXT, item), 1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        final Student updatedStudent = data.getParcelableExtra(SecondActivity.RESULT);
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == updatedStudent.getId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            students[index] = updatedStudent;
        }
        adapter.notifyItemChanged(index);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray(KEY, students);
        super.onSaveInstanceState(outState);
    }

}

