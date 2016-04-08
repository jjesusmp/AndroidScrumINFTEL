package com.example.asus.androidscruminftel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Task extends AppCompatActivity {

    public String tittle;
    TextView tittleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Bundle bundle = this.getIntent().getExtras();
        tittle = bundle.getString("tittle");

        tittleTv = (TextView) findViewById(R.id.tittle);
        tittleTv.setText(tittle);
    }
}
