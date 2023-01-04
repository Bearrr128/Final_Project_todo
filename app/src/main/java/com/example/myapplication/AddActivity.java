package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    String get_title,get_context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Button back_add =findViewById(R.id.back_add);
        Button finish   =findViewById(R.id.finish);
        EditText title_add  =findViewById(R.id.title_add);
        EditText context_add=findViewById(R.id.context_add);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteOperator noteOperator = new NoteOperator(AddActivity.this);
                get_title = title_add.getText().toString().trim();
                get_context = context_add.getText().toString().trim();

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
            }
        });

        back_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}