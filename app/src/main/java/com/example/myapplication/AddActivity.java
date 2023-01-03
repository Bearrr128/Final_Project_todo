package com.example.myapplication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class AddActivity {



    public void onClick(View view) {

        if (view == findViewById(R.id.finish)) {
            NoteOperator noteOperator = new NoteOperator(AddActivity.this);
            get_title = title.getText().toString().trim();
            get_context = context.getText().toString().trim();

            if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_context)) {
                Toast.makeText(AddActivity.this, "添加信息不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note();
                note.title = get_title;
                note.context = get_context;
                boolean add = noteOperator.insert(note);
                //如果添加数据成功，跳到待办事项界面，并通过传值，让目标界面进行刷新
                if (add) {
                    //Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
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