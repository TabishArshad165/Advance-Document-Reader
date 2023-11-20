package com.example.documentreader01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout btnAll, btnPdf, btnWord, btnExcel, btnPpt, btnTxt, btnFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure that the IDs used here match the IDs in your activity_main.xml layout
        btnAll = findViewById(R.id.ll_all);
        btnPdf = findViewById(R.id.ll_pdf);
        btnWord = findViewById(R.id.ll_word);
        btnExcel = findViewById(R.id.ll_excel);
        btnPpt = findViewById(R.id.ll_ppt);

        // Check if ll_txt is defined in your activity_main.xml layout
        btnTxt = findViewById(R.id.ll_txt);

        btnFav = findViewById(R.id.ll_fav);

        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainToPdf = new Intent(MainActivity.this, pdf.class);
                startActivity(intentMainToPdf);
            }
        });
    }
}
