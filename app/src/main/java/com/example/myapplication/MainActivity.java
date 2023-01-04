package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{


    ArrayList<HashMap<String,String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add  =findViewById(R.id.btn_add);
        ListView listView =findViewById(R.id.listview);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        final NoteOperator noteOperator =new NoteOperator(MainActivity.this);
        list = noteOperator.getNoteList();
        final ListAdapter listAdapter = new SimpleAdapter(MainActivity.this, list,
                R.layout.item,new String[]{"id","title"},new int[]{R.id.note_id, R.id.note_title});
        listView.setAdapter(listAdapter);

        Intent intent =getIntent();
        int flag =intent.getIntExtra("Insert",0);
        if (flag==1){
            list =noteOperator.getNoteList();
            listView.setAdapter(listAdapter);
        }

        if(list.size()!=0){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view,int position, long i){
                    String id=  list.get(position).get("id");
                    Intent intent =new Intent();
                    intent.putExtra("note_id",Integer.parseInt(id));
                    startActivity(intent);
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("确定删除？");
                    builder.setTitle("提示");

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String id = list.get(position).get("id");
                            noteOperator.delete(Integer.parseInt(id));
                            list.remove(position);
                            listView.setAdapter(listAdapter);
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                    return true;
                }
            });
        }else{
            Toast.makeText(this,"暫無待辦事項，請添加", Toast.LENGTH_SHORT).show();
        }
    }
}