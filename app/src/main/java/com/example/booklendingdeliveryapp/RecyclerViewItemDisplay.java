package com.example.booklendingdeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RecyclerViewItemDisplay extends AppCompatActivity {

    TextView mtitle,mauthor,mpublisher,misbn;
    ImageView mimage,Favourite;
    Button addCart,bookNow;

    boolean isInMyFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_display);

        mtitle = findViewById(R.id.rTitleView2);
        mauthor = findViewById(R.id.rAuthorView2);
        mpublisher = findViewById(R.id.rPublisherView2);
        misbn = findViewById(R.id.rISBNView2);
        mimage = findViewById(R.id.rImageView2);
        addCart = findViewById(R.id.addCartBtn);
        bookNow = findViewById(R.id.bookNowBtn);
        Favourite = findViewById(R.id.FavouriteBtn);

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

        Favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favourite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(RecyclerViewItemDisplay.this, "Book added to Favourites", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static void addToFavorite(Context context, String bookId){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            Toast.makeText(context,"You're not logged in",Toast.LENGTH_SHORT).show();
        }
        else{
            long timestamp = System.currentTimeMillis();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("bookId", ""+bookId);
            hashMap.put("timestamp", ""+timestamp);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(firebaseAuth.getUid()).child("favourites").child(bookId)
                    .setValue(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context,"Book Added to your favourites",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,"Failed to Add book to your favourites due to"+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    public static void removeFromFavourite(Context context, String bookId){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            Toast.makeText(context,"You're not logged in",Toast.LENGTH_SHORT).show();
        }
        else{

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(firebaseAuth.getUid()).child("favourites").child(bookId)
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context,"Book removed from your favourites",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,"Failed to remove book from your favourites due to"+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }
}