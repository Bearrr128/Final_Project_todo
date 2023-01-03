package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends AppCompatActivity {

    private TextView note_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_xml);

        EditText title_detail=findViewById(R.id.title_detail);
        EditText context_detail=findViewById(R.id.context_detail);

        Intent intent=getIntent();
        note_id=intent.getIntExtra("note_id",0);
        NoteOperator noteOperator=new NoteOperator( this);
        Note note = noteOperator.getNoteById(note_id);

        title_detail.setText(String.valueOf(note.title));
        context_detail.setText(String.valueOf(note.context));
    }

        if(view == findViewById(R.id.save_detail)){
        get_title=title.getText().toString().trim();
        get_context=context.getText().toString().trim();
        if(TextUtils.isEmpty(get_title)||TextUtils.isEmpty(get_context)){
            Toast.makeText(this,"修改内容不能为空",Toast.LENGTH_SHORT).show();
        }else{
            Note note=new Note();
            note.note_id=note_id;
            note.title=get_title;
            note.context=get_context;
            NoteOperator noteOperator=new NoteOperator(DetailActivity.this);
            noteOperator.update(note);

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            intent.setClass(DetailActivity.this,MainActivity.class);
            startActivity(intent);

        }
    }

}