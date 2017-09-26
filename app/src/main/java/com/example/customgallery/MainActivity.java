package com.example.customgallery;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int REQ_GALLERY = 99;
    ImageView imageView;


    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

    }



    public void onGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_GALLERY:
                if (resultCode == RESULT_OK) {
                    //String datadata = data.getStringExtra("imagePath");
                    Uri imageUri = data.getParcelableExtra("imagePath");
                    imageView.setImageURI(imageUri);
                }
                break;
        }
    }
}
