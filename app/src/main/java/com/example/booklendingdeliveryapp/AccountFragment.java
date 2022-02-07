package com.example.booklendingdeliveryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {

    TextView FullnameTv, UsernameTv, EmailTv, PhoneNoTv;
    Button logoutbt;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser user;
    String UserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("users");
        UserId = user.getUid();

        FullnameTv = view.findViewById(R.id.pfFullname);
        UsernameTv = view.findViewById(R.id.pfUsername);
        EmailTv = view.findViewById(R.id.pfEmail);
        PhoneNoTv = view.findViewById(R.id.pfPhone);


        reference.child(UserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass userProfile = snapshot.getValue(UserHelperClass.class);

                if (userProfile != null){
                    String  FullName = userProfile.name;
                    String  UserName = userProfile.username;
                    String  Email = userProfile.email;
                    String  PhoneNo = userProfile.phoneNo;

                    FullnameTv.setText(FullName);
                    UsernameTv.setText("@"+UserName);
                    EmailTv.setText(Email);
                    PhoneNoTv.setText(PhoneNo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something wrong happend!",Toast.LENGTH_SHORT).show();
            }
        });

        logoutbt = view.findViewById(R.id.cirLogoutButton);

        logoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });


        return view;
    }
}