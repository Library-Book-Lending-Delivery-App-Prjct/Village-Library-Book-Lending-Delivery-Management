package com.example.booklendingdeliveryapp;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClicklistener.onItemclick(view,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                mClicklistener.onItemLongclick(view,getAdapterPosition());
                return false;
            }
        });

    }
    public void setdetails(Context context, String title,String author,String publisher, String image){
//        , String isbn


        TextView mTitleView = view.findViewById(R.id.rTitleView);
        TextView mAuthorView = view.findViewById(R.id.rAuthorView);
        TextView mPublisherView = view.findViewById(R.id.rPublisherView);
//        TextView mISBNView = view.findViewById(R.id.rISBNView);
        ImageView mImageView = view.findViewById(R.id.rImageView);


        mTitleView.setText(title);
        mAuthorView.setText(author);
        mPublisherView.setText(publisher);
//        mISBNView.setText(isbn);
        Picasso.get().load(image).into(mImageView);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        itemView.startAnimation(animation);

    }
    private ViewHolder.ClickListener mClicklistener;

    public interface ClickListener {
        void onItemclick(View view,int position);
        void onItemLongclick(View view,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClicklistener = clickListener;
    }
}
