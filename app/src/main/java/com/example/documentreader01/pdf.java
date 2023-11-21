package com.example.documentreader01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.rvPdfAdapter;

public class pdf extends AppCompatActivity {

    List<File> pdfList;
    Adapter adapter;
    RecyclerView rv_pdf;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

//        rvPdfAdapter = findViewById(R.id.rv_pdf);
        progressBar = findViewById(R.id.progressBar);
        rv_pdf = findViewById(R.id.rv_pdf);
        pdfList = new ArrayList<>();

        checkPermission();
    }

    private void checkPermission() {
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        setuprv();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
    private void setuprv(){
        rv_pdf.setLayoutManager(new LinearLayoutManager(this));
        rv_pdf.setHasFixedSize(false);

        new Thread(()->{
            List<File> files = getAllFiles();
            Collections.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return Long.valueOf(o2.lastModified()).compareTo(o1.lastModified());
                }
            });
            pdfList.addAll(files);
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    //////////////////////////////////////////////////////
                    adapter = (Adapter) new rvPdfAdapter(pdf.this, pdfList);
                    /////////////////////////////////
                    rv_pdf.setAdapter((RecyclerView.Adapter) adapter);
                    handleProgress();
                }
            });
        }).start();
    }
    private List<File> getAllFiles(){
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        String Selection = MediaStore.Files.FileColumns.MIME_TYPE+"=?";
        String[] SelectionArgus = {"application/pdf"};

        Cursor cursor = getContentResolver().query(uri, projection, Selection, SelectionArgus, null);
        int pathIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
        ArrayList<File> list = new ArrayList<>();

        while (cursor.moveToNext()){
            if (pathIndex != -1){
                String pdfpath = cursor.getString(pathIndex);
                File file = new File(pdfpath);
                list.add(file);
            }
        }
        cursor.close();

        return list;
    }




    private void handleProgress(){
        progressBar.setVisibility(View.GONE);
        if(adapter.getCount()==0){
            Toast.makeText(this, "No pdf files in phone", Toast.LENGTH_SHORT).show();
        }
        else {
            rv_pdf.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }

}