package com.example.android.yosafat_1202154119_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mListNamaMhs;
    Button mPencariGbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListNamaMhs = (Button) findViewById(R.id.btn_listnamamhs);
        mPencariGbr = (Button) findViewById(R.id.btn_pencarigambar);

        mListNamaMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intListNamaMhs = new Intent(MainActivity.this, ListNamaMhs.class);
                startActivity(intListNamaMhs);
            }
        });

        mPencariGbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intPencariGbr = new Intent(MainActivity.this, PencariGbr.class);
                startActivity(intPencariGbr);
            }
        });
    }
}
