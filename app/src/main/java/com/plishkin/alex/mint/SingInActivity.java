package com.plishkin.alex.mint;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.plishkin.alex.mint.Adapters.MyRecyclerViewAdapter;
import com.plishkin.alex.mint.Helpers.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingInActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);


        WelcomeFragment welcomeFragment = new WelcomeFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, welcomeFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                navigationView.removeOnLayoutChangeListener(this);

                TextView textView = (TextView) navigationView.findViewById(R.id.login_text);

                textView.setText(SingInActivity.this.getIntent().getStringExtra("login"));
            }
        });

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sign_in_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter instanceof MyRecyclerViewAdapter)
                    ((MyRecyclerViewAdapter) adapter).filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id_item = item.getItemId();

        switch (id_item){
            case R.id.nav_home:{
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, welcomeFragment).commit();
                break;
            }
            case R.id.nav_contacts:{
                ContactsFragment contactsFragment = new ContactsFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, contactsFragment).commit();
                break;
            }
            case R.id.nav_weather:{
                WeatherFragment weatherFragment = new WeatherFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, weatherFragment).commit();
                break;
            }
            case R.id.nav_map:{
                MapFragment mapFragment = new MapFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mapFragment).commit();
                break;
            }
            case R.id.nav_log_out:{
                (new UserSession((MyApplication) getApplicationContext())).sessionDestroy();
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
