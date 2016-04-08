package com.example.asus.androidscruminftel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asus.androidscruminftel.fragment.ListChatFragment;
import com.example.asus.androidscruminftel.fragment.LoadingFragment;
import com.example.asus.androidscruminftel.model.ProjectChat;

import java.util.ArrayList;


public class ListChatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<ProjectChat> chatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listchat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatList = new ArrayList<>();
        chargeDefaultChatList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.coordinatorLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Fragmento de carga
        LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_listchat, loadingFragment).commit();

        showListChatFragment(chatList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

        }
        return;

            // Otros permisos que requiera la aplicación
    }

    private void showListChatFragment(ArrayList<ProjectChat> chatList) {
        this.chatList = chatList;
        ListChatFragment placeListFragment = new ListChatFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("chatList", chatList);
        placeListFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_listchat, placeListFragment).commit();
    }

    private void chargeDefaultChatList() {
        String idp1 = "198";
        String idp2 = "199";
        String namep1 = "Mi Proyecto";
        String namep2 = "Mi Otro Proyecto";

        ProjectChat pc = new ProjectChat();
        ProjectChat pc2 = new ProjectChat();
        pc.setProjectId(idp1);
        pc.setProjectName(namep1);
        chatList.add(pc);
        pc2.setProjectName(namep2);
        pc2.setProjectId(idp2);
        chatList.add(pc2);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.new_project) {
            Intent intent = new Intent(this,NewProjectActivity.class);
            startActivity(intent);
        } else if (id == R.id.projects) {
            Intent intent = new Intent(this,ProjectsScrum.class);
            startActivity(intent);

        }else if (id == R.id.exit) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.coordinatorLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
