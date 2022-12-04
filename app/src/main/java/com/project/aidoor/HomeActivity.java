package com.project.aidoor;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //this line hides Action bar
        //getSupportActionBar().hide();

        //this line hides Status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment fragment=null;
                switch(item.getItemId()){

                    case R.id.nav_home:
                        fragment=new HomeFragment();
                        break;

                    case R.id.nav_health:
                        fragment=new HealthFragment();
                        break;

                    case R.id.nav_users:
                        fragment=new UsersFragment();
                        break;

                    case R.id.nav_history:
                        fragment=new HistoryFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();

                return true;
            }
        });
    }
}