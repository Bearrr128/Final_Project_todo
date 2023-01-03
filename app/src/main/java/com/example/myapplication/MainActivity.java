package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add  =findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        ListView listView =findViewById(R.id.listview);

        final NoteOperator noteOperator =new NoteOperator(MainActivity.this);
        ArrayList<HashMap<String, String>> list = noteOperator.getNoteList();
        final ListAdapter listAdapter = new SimpleAdapter(MainActivity.this, list,
                R.layout.item,new String[]{"id","title"},new int[]{R.id.note.id, R.id.note_title});
        View.setAdapter(listAdapter);

        Intent intent =getIntent();
        int flag =intent.getIntExtra("Insert",0);
        if (flag==1){
            list  =noteOperator.getNoteList();
            listView.setAdapter(listAdapter);
        }

    }


}