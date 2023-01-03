package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public void onClick(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,AddActivity.class);
        MainActivity.this.startActivity(intent);
    }
=======

>>>>>>> 1d03de4cbbe9e89ec620d16c3bf4e8c7638442ce
}