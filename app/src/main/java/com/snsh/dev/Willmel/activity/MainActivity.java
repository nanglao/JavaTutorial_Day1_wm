package com.snsh.dev.Willmel.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.frag.CartFrag;
import com.snsh.dev.Willmel.frag.HomeFrag;
import com.snsh.dev.Willmel.frag.MenuFrag;
import com.snsh.dev.Willmel.frag.UserFrag;
import com.snsh.dev.Willmel.utl.AppCons;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFrag(AppCons.FRAG_HOME);
                    return true;
                case R.id.navigation_menu:
                    replaceFrag(AppCons.FRAG_MENU);
                    return true;
                case R.id.navigation_cart:
                    replaceFrag(AppCons.FRAG_CART);
                    return true;
                case R.id.navigation_profile:
                    replaceFrag(AppCons.FRAG_USER);
                    return true;
            }
            return false;
        }

    };

    private void replaceFrag(int flag) {
        switch (flag) {
            case AppCons.FRAG_HOME:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_body, HomeFrag.newInstance("pass 1", "pass 2"), "HomeFrag")
                        .commit();
                break;
            case AppCons.FRAG_MENU:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_body, MenuFrag.newInstance("pass 1", "pass 2"), "MenuFrag")
                        .commit();
                break;
            case AppCons.FRAG_CART:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_body, CartFrag.newInstance("pass 1", "pass 2"), "CartFrag")
                        .commit();
                break;
            case AppCons.FRAG_USER:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_body, UserFrag.newInstance("pass 1", "pass 2"), "UserFrag")
                        .commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        replaceFrag(AppCons.FRAG_HOME);
    }

}
