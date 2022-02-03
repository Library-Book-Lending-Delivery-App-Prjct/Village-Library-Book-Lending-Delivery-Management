package com.example.booklendingdeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class DashBoard extends AppCompatActivity {

    //initialize variable
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_favorite));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_qr_code));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_account));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;

                //Check condition
                switch (item.getId()){

                    case 1:
                        //When id is 1 initialize home fragment
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        //When id is 2 initialize favourite fragment
                        fragment = new FavouritesFragment();
                        break;
                    case 3:
                        //When id is 3 initialize ISBN_Scan fragment
                        fragment = new ISBN_ScanFragment();
                        break;
                    case 4:
                        //When id is 4 initialize Cart fragment
                        fragment = new CartFragment();
                        break;
                    case 5:
                        //When id is 5 initialize Account fragment
                        fragment = new AccountFragment();
                        break;
                }

                //load fragment
                loadFragment(fragment);

            }
        });

        //set cart item count
        bottomNavigation.setCount(4, "3");
        //set home fragment initially selected
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext()
                ,"You Clicked"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext()
                        ,"You Reselected"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadFragment(Fragment fragment) {

        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}