package com.example.asus.androidscruminftel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProjectsScrum extends AppCompatActivity {
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_scrum);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewTask.class);
                startActivity(intent);
            }
        });

        DesignDemoPagerAdapter adapter = new DesignDemoPagerAdapter(getSupportFragmentManager(), getActivityProject());
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.addTab(tabLayout.newTab().setText("to done"));
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());

                        switch (tab.getPosition()) {
                            case 0:
                                showToast("One");
                                break;
                            case 1:
                                showToast("Two");
                                break;
                            case 2:
                                showToast("Three");
                                break;
                        }

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );
    }

    private void showToast(String cadena) {
        Toast toast = Toast.makeText(this, cadena, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    //----------------------- subClase ---------------------------------
    public static class DesignDemoFragment extends Fragment {
        private static final String TAB_POSITION = "tab_position";
        private ProjectsScrum p;

        public DesignDemoFragment(){

        }

        public DesignDemoFragment(ProjectsScrum p) {
            this.p = p;
        }

        public DesignDemoFragment newInstance(int tabPosition) {
            DesignDemoFragment fragment = new DesignDemoFragment(p);
            Bundle args = new Bundle();
            args.putInt(TAB_POSITION, tabPosition);
            fragment.setArguments(args);

            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();
            int position = args.getInt(TAB_POSITION);

            CharSequence status = "";
            if(position==0){
                status = "To do";
            }else if (position ==1){
                status = "Doing";
            }else if (position == 2){
                status = "Done";
            }

            /*ArrayList<String> items = new ArrayList<String>();
            String[] it = new String[10];
            for (int i = 0; i < 10; i++) {
                it[i] =status + " TÍTULO, item #" + i;
                //items.add("Tab #" + tabPosition + " item #" + i);
            }

            String[] it2 = new String[10];
            for (int i = 0; i < 10; i++) {
                it2[i] ="Descripcion, item #" + i;
                //items.add("Tab #" + tabPosition + " item #" + i);
            }*/
            String texto = "Había una vez en un lugar de la mancha de cuyo nombre no quiero acordarme...Había una vez en un lugar de la mancha de cuyo nombre no quiero acordarme...Había una vez en un lugar de la mancha de cuyo nombre no quiero acordarme...Había una vez en un lugar de la mancha de cuyo nombre no quiero acordarme...";

            List<ExpandableListAdapter.Item> data = new ArrayList<>();
            for (int i = 0; i<6;i++){
                ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Tarea del estado "+status + i);
                places.invisibleChildren = new ArrayList<>();
                places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, i + "Descripción:" + texto));
                places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tiempo Estimado: 2:31:1"));
                data.add(places);
            }

            View rootView = inflater.inflate(R.layout.fragment_project_view, container, false);

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
            rv.setHasFixedSize(true);
            //MyAdapter adapter = new MyAdapter(it, it2);
            //adapter.setMyProject(p);
            ExpandableListAdapter exp = new ExpandableListAdapter(data);
            exp.setMyProject(p);

            rv.setAdapter(exp);


            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);


            return rootView;
        }
    }

    class DesignDemoPagerAdapter extends FragmentPagerAdapter {
        ProjectsScrum p;

        public DesignDemoPagerAdapter(FragmentManager fm,ProjectsScrum p) {
            super(fm);
            this.p = p;
        }

        @Override
        public Fragment getItem(int position) {
            DesignDemoFragment d = new DesignDemoFragment(p);
            return d.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence status = "";
            if(position==0){
                status = "to do";
            }else if (position ==1){
                status = "doing";
            }else if (position == 2){
                status = "done";
            }else{
                status = "Chat";
            }
            return status;
        }
    }

    public Context getContext(){
        return getApplicationContext();
    }

    public ProjectsScrum getActivityProject(){
        return this;
    }
}
