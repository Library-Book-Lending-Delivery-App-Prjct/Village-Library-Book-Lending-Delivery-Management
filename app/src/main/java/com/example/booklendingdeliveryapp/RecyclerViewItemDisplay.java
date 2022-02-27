package com.example.booklendingdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewItemDisplay extends AppCompatActivity {

    TextView mtitle,mauthor,mpublisher,misbn;
    ImageView mimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_display);

        mtitle = findViewById(R.id.rTitleView2);
        mauthor = findViewById(R.id.rAuthorView2);
        mpublisher = findViewById(R.id.rPublisherView2);
        misbn = findViewById(R.id.rISBNView2);
        mimage = findViewById(R.id.rImageView2);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String publisher = getIntent().getStringExtra("publisher");
        String isbn = getIntent().getStringExtra("isbn");

        Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0, bytes.length);

        mtitle.setText(title);
        mauthor.setText(author);
        mpublisher.setText(publisher);
        misbn.setText(isbn);
        mimage.setImageBitmap(bmp);

    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }
}