package com.behague.benjamin.mynews.controllers.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.views.PageAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call each method for UI configuring
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureViewPagerAndTabs();
        this.configureNavigationView();

        viewPager = findViewById(R.id.activity_main_viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        //Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    //Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureViewPagerAndTabs(){
        // Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // Get TabLayout from layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void configureNavigationView(){
        NavigationView navigationView ;

        navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_search:
                launchSearchActivity();
                return true;

            case R.id.menu_notifs:
                launchNotifsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int newItem ;
        int id = item.getItemId();

        //Show fragment after user clicked on a menu item
        switch (id){
            case R.id.activity_main_drawer_topstories:
                newItem = 0;
                break;

            case R.id.activity_main_drawer_mostpopular:
                newItem = 1;
                break;

            case R.id.activity_main_drawer_sports:
                newItem = 2;
                break;

            default:
                newItem = 0;
                break;
        }

        viewPager.setCurrentItem(newItem);

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void launchSearchActivity(){
        Intent mSearchIntent = new Intent(MainActivity.this, SearchActivity.class);
        this.startActivity(mSearchIntent);
    }

    private void launchNotifsActivity(){
        Intent mNotifsIntent = new Intent(MainActivity.this, NotificationActivity.class);
        this.startActivity(mNotifsIntent);
    }
}
