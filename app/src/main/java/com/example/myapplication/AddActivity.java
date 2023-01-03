package com.example.myapplication;

import android.content.Intent;

public class AddActivity {
    @Override
    public void onClick(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,AddActivity.class);
        MainActivity.this.startActivity(intent);
    }



}
