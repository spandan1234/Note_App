package com.example.spandan.notemakingintent;

import android.app.ListActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    EditText addTask, subTask, searchBar;
    ListView subTaskView, navListView;
    TextView done_option, cancel_option;
    ArrayList<String> tasks, drawerTools;
    LayoutInflater inflater;
    Button ADD_SUB_TASK;
    int BOOKMARK_FLAG=0;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    FloatingActionButton addTools,menu1,menu2,menu3,menu4,menu5,menu6;
    ConstraintLayout addTaskSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        drawerTools = new ArrayList<>();
        drawerTools.add("Settings");
        drawerTools.add("Calender");
        drawerTools.add("Notification");
        drawerTools.add("Connections");
        navListView = (ListView) findViewById(R.id.drawerListView);
        final DrawerAdapter drawerAdapter = new DrawerAdapter(this,R.layout.nav_listview_layout,drawerTools);
        navListView.setAdapter(drawerAdapter);

        Toolbar mytoolbar = (Toolbar)findViewById(R.id.customtoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Add Task");

        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,mytoolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(mToggle);
        mToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.toolNavDraw);
        navigationView.setNavigationItemSelectedListener(this);

        addTaskSection = (ConstraintLayout) findViewById(R.id.addTaskSection);
        addTools = (FloatingActionButton) findViewById(R.id.toolsButton);
        menu1 = (FloatingActionButton) findViewById(R.id.menu1);
        menu2 = (FloatingActionButton) findViewById(R.id.menu2);
        menu3 = (FloatingActionButton) findViewById(R.id.menu3);
        menu4 = (FloatingActionButton) findViewById(R.id.menu4);
        menu5 = (FloatingActionButton) findViewById(R.id.menu5);
        menu6 = (FloatingActionButton) findViewById(R.id.menu6);

        final Animation transition = AnimationUtils.loadAnimation(this,R.anim.fab_animation);
        final Animation transition2 = AnimationUtils.loadAnimation(this,R.anim.fab_animation2);
        final Animation transition3 = AnimationUtils.loadAnimation(this,R.anim.fab_animation_hr1);
        final Animation transition4 = AnimationUtils.loadAnimation(this,R.anim.fab_animation_hr2);
        final Animation transition5 = AnimationUtils.loadAnimation(this,R.anim.fab_animation_diag);
        final Animation transition6 = AnimationUtils.loadAnimation(this,R.anim.fab_animation_diag2);
        final Animation transition7 = AnimationUtils.loadAnimation(this,R.anim.fab_animation_rotate);

        addTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addTaskSection.setAlpha(.5f);
                menu1.startAnimation(transition);
                menu2.startAnimation(transition2);
                menu3.startAnimation(transition4);
                menu4.startAnimation(transition3);
                menu5.startAnimation(transition5);
                menu6.startAnimation(transition6);
                addTools.setAnimation(transition7);
                menu1.setVisibility(View.VISIBLE);
                menu2.setVisibility(View.VISIBLE);
                menu3.setVisibility(View.VISIBLE);
                menu4.setVisibility(View.VISIBLE);
                menu5.setVisibility(View.VISIBLE);
                menu6.setVisibility(View.VISIBLE);
            }
        });
        addTask = (EditText) findViewById(R.id.addTask);
        subTask = (EditText) findViewById(R.id.addsubTask);
        searchBar = (EditText) findViewById(R.id.searchBar);
        subTaskView = (ListView) findViewById(R.id.listView);
        done_option = (TextView) findViewById(R.id.done_action);
        cancel_option = (TextView) findViewById(R.id.cancel_action);
        tasks = new ArrayList<>();
        ADD_SUB_TASK = (Button) findViewById(R.id.addSubTask);
        //tasks.add("Groceries");

        final MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.task_list,tasks);
        subTaskView.setAdapter(adapter);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask.setText("");
            }
        });
        subTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subTask.setText("");
            }
        });
        addTask.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                addTask.setText("");
            }
        });
        subTask.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                subTask.setText("");
            }
        });
        done_option.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    done_option.setTextColor(getResources().getColor(R.color.Translucent));
            }
        });
        adapter.notifyDataSetChanged();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.to_do_slider,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemID =item.getItemId();
        if(itemID == R.id.bookmark) {
            if(BOOKMARK_FLAG==0) {
                item.setIcon(R.drawable.bookmark_selected);
                BOOKMARK_FLAG=1;
            }
            else {
                item.setIcon(R.drawable.bookmark_notselected);
                BOOKMARK_FLAG=0;
            }
        }

        return true;
    }
    public void inflateTasks(View v){
        String temp;
        temp = subTask.getText().toString();
        tasks.add(temp);
        subTask.setText("");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.tagOption){}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(Gravity.START);
        return true;
    }
}
