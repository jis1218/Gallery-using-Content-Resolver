package com.example.customgallery;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GalleryAdapter adapter;
    ArrayList<String> list = null;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
    }

    private void init(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new GalleryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<String> list = load();
        adapter.setData(list);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    // Content Resolver를 통해서 이미지 목록을 가져온다
    private List<String> load(){
        List<String> list = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        String proj[] = {
                MediaStore.Images.Thumbnails.DATA
        };
        Cursor cursor = resolver.query(uri, proj, null, null, null);
        if(cursor != null){
            while(cursor.moveToNext()){
                int index = cursor.getColumnIndex(proj[0]);
                String path = cursor.getString(index);
                list.add(path);
            }
        }

        return list;
    }
}
