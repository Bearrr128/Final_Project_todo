package com.example.myapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class AddActivity extends AppCompatActivity {

    private String get_title,get_context;

    public void onClick(View view) {

        if (view == findViewById(R.id.finish)) {
            NoteOperator noteOperator = new NoteOperator(AddActivity.this);
            BreakIterator title = null;
            get_title = title.getText().toString().trim();
            BreakIterator context = null;
            get_context = context.getText().toString().trim();

            if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_context)) {
                Toast.makeText(AddActivity.this, "添加信息不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note();
                note.title = get_title;
                note.context = get_context;
                boolean add = noteOperator.insert(note);

                if (add) {
                    Intent intent = new Intent();
                    intent.setClass(AddActivity.this, MainActivity.class);
                    intent.putExtra("Insert", 1);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == findViewById(R.id.back_add)) {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}