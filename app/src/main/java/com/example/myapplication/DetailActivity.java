package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Toast;


public class DetailActivity<note_id> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_xml);
    }
    Intent intent=getIntent();
    note_id = intent.getIntExtra("note_id",0);
    NoteOperator noteOperator=new NoteOperator( this);
    private Object note_id;
    ContactsContract.CommonDataKinds.Note note = noteOperator.getNoteById(note_id);

        title.setText(String.valueOf(note.title));
        context.setText(String.valueOf(note.context));
        if(view == findViewById(R.id.save_detail)){
            get_title=title.getText().toString().trim();
            get_context=context.getText().toString().trim();
        if(TextUtils.isEmpty(get_title)||TextUtils.isEmpty(get_context)){
            Toast.makeText(this,"修改内容不能為空",Toast.LENGTH_SHORT).show();
        }else{
            ContactsContract.CommonDataKinds.Note note=new ContactsContract.CommonDataKinds.Note();
            note.note_id = note_id;
            note.title = get_title;
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