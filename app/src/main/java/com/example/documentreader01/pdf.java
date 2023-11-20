package com.example.documentreader01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.rvPdfAdapter;

public class pdf extends AppCompatActivity {

    ArrayList<String> pdfArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfArrayList = getPdfFiles();
        System.out.println(pdfArrayList);
        RecyclerView rvPdf = findViewById(R.id.rv_pdf);
        rvPdf.setLayoutManager(new LinearLayoutManager(this));
        rvPdf.setAdapter(new rvPdfAdapter(pdf.this, pdfArrayList));

    }

    public ArrayList<String> getPdfFiles() {
        ContentResolver contentResolver = getContentResolver();

        String mime = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
        String[] args = new String[]{mimeType};
        String[] proj = {MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.DISPLAY_NAME};
        String sortingOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";
        Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), proj, mime, args, sortingOrder);

        ArrayList<String> pdfArrayList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

                String path = cursor.getString(index);
                pdfArrayList.add(path);
            }
            cursor.close();
        }
        return pdfArrayList;
    }

}