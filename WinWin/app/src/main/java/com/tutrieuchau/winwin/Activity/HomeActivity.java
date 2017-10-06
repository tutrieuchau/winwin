package com.tutrieuchau.winwin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.tutrieuchau.winwin.Fragment.AnalyseFragment;
import com.tutrieuchau.winwin.Fragment.HelpFragment;
import com.tutrieuchau.winwin.Fragment.HistoryFragment;
import com.tutrieuchau.winwin.Fragment.LoveFragment;
import com.tutrieuchau.winwin.Fragment.MissionFragment;
import com.tutrieuchau.winwin.Fragment.ReminderFragment;
import com.tutrieuchau.winwin.Fragment.RewardFragment;
import com.tutrieuchau.winwin.Fragment.SettingFragment;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Utils;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private int navItemIndex;
    private String CURRENT_TAG;
    // tags used to attach the fragments
    private static final String TAG_MISSION = "mission";
    private static final String TAG_REMINDER = "reminder";
    private static final String TAG_ANALYSE = "analyse";
    private static final String TAG_REWARD = "reward";
    private static final String TAG_HISTORY = "history";
    private static final String TAG_SETTING = "setting";
    private static final String TAG_LOVE = "love";
    private static final String TAG_HELP = "help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                switch (navItemIndex){
                    case 0:
                        break;
                    case 1:
                        Intent intent = new Intent(HomeActivity.this,AddReminderActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            navItemIndex = 0;
            CURRENT_TAG = TAG_MISSION;
            loadHomeFragment();
        }

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
        getMenuInflater().inflate(R.menu.home, menu);
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
        switch(id){
            case R.id.nav_mission:
                navItemIndex = 0;
                CURRENT_TAG = TAG_MISSION;
                break;
            case R.id.nav_reminder:
                navItemIndex = 1;
                CURRENT_TAG = TAG_REMINDER;
                break;
            case R.id.nav_analyse:
                navItemIndex = 2;
                CURRENT_TAG = TAG_ANALYSE;
                break;
            case R.id.nav_reward:
                navItemIndex = 3;
                CURRENT_TAG = TAG_REWARD;
                break;
            case R.id.nav_history:
                navItemIndex = 4;
                CURRENT_TAG = TAG_HISTORY;
                break;
            case R.id.nav_setting:
                navItemIndex = 5;
                CURRENT_TAG = TAG_SETTING;
                break;
            case R.id.nav_love:
                navItemIndex = 6;
                CURRENT_TAG = TAG_LOVE;
                break;
            case R.id.nav_help:
                navItemIndex = 7;
                CURRENT_TAG = TAG_HELP;
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        loadHomeFragment();
        return true;
    }

    private Fragment getHomeFragment(){
        switch (navItemIndex) {
            case 0:
                MissionFragment missionFragment = new MissionFragment();
                return missionFragment;
            case 1:
                ReminderFragment reminderFragment = new ReminderFragment();
                return reminderFragment;
            case 2:
                AnalyseFragment analyseFragment = new AnalyseFragment();
                return analyseFragment;
            case 3:
                RewardFragment rewardFragment = new RewardFragment();
                return rewardFragment;
            case 4:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;
            case 5:
                SettingFragment settingFragment = new SettingFragment();
                return settingFragment;
            case 6:
                LoveFragment loveFragment = new LoveFragment();
                return loveFragment;
            case 7:
                HelpFragment helpFragment = new HelpFragment();
                return helpFragment;
            default:
                return new MissionFragment();

        }
    }

    /**
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment(){
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // show Fragment with fade Animation
        Handler handler = new Handler();
        handler.post(mPendingRunnable);

        // refresh toolbar menu
        invalidateOptionsMenu();
        setToolbarTitle();

    }
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(Utils.TOOL_BAR_TITLE[navItemIndex]);
    }

    @Override
    public void onClick(View v) {

    }

}
