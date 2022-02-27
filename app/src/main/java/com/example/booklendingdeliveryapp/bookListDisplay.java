package com.example.booklendingdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class bookListDisplay extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_display);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("books");

        FirebaseRecyclerAdapter<Member, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Member, ViewHolder>(
                Member.class,
                R.layout.image,
                ViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {

                viewHolder.setdetails(getApplicationContext(),member.getTitle(),member.getAuthor(),member.getPublisher(), member.getImage());
//                , member.getIsbn(),

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemclick(View view, int position) {

                        TextView mtitletv = view.findViewById(R.id.rTitleView);
                        TextView mauthortv = view.findViewById(R.id.rAuthorView);
                        TextView mpublishertv = view.findViewById(R.id.rPublisherView);
                        TextView misbntv = view.findViewById(R.id.rISBNView);
                        ImageView mimageView = view.findViewById((R.id.rImageView));

                        String mtitle = mtitletv.getText().toString();
                        String mauthor = mauthortv.getText().toString();
                        String mpublisher = mpublishertv.getText().toString();
                        String misbn = misbntv.getText().toString();
                        Drawable mDrawable = mimageView.getDrawable();
                        Bitmap mbitmap = ((BitmapDrawable)mDrawable).getBitmap();

                        Intent intent = new Intent(view.getContext(),RecyclerViewItemDisplay.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mbitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[]bytes = stream.toByteArray();

                        intent.putExtra("image",bytes);
                        intent.putExtra("title",mtitle);
                        intent.putExtra("author",mauthor);
                        intent.putExtra("publisher",mpublisher);
                        intent.putExtra("isbn",misbn);
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongclick(View view, int position) {

                    }
                });

                return viewHolder;
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}