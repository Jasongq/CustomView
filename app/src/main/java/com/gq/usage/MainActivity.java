package com.gq.usage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gq.usage.adapter.HomePageAdapter;
import com.gq.usage.fragment.FourFragment;
import com.gq.usage.fragment.OneFragment;
import com.gq.usage.fragment.SecondFragment;
import com.gq.usage.fragment.ThreeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private MenuItem menuItem;
    private HomePageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        viewpager.addOnPageChangeListener(OnPageChangeListener);
        //禁止ViewPager滑动
        /*viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/
        setupViewPager(viewpager);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.item_news:
                    viewpager.setCurrentItem(0,false);
                    break;
                case R.id.item_contact:
                    viewpager.setCurrentItem(1,false);
                    break;
                case R.id.item_found:
                    viewpager.setCurrentItem(2,false);
                    break;
                case R.id.item_more:
                    viewpager.setCurrentItem(3,false);
                    break;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener OnPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (menuItem!=null){
                menuItem.setChecked(false);
            }else {
                bottomNavigation.getMenu().getItem(0).setChecked(false);
            }
            menuItem=bottomNavigation.getMenu().getItem(position);
            menuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void setupViewPager(ViewPager viewPager) {
        adapter = new HomePageAdapter(getSupportFragmentManager());

        adapter.addFragment(OneFragment.newInstance("消息"));
        adapter.addFragment(SecondFragment.newInstance("联系人"));
        adapter.addFragment(ThreeFragment.newInstance("发现"));
        adapter.addFragment(FourFragment.newInstance("更多"));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
