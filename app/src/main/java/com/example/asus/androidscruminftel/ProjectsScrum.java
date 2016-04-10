package com.example.asus.androidscruminftel;

import android.annotation.SuppressLint;
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

import com.example.asus.androidscruminftel.model.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectsScrum extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<String> state;
    ArrayList<Task> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_scrum);

        state = new ArrayList<>();
        state.add(0,"to");
        state.add(1,"do");
        state.add(2,"done");
        state.add(3,"doing");
        state.add(4,"finish");

        tasks = new ArrayList<>();
        Task t1 = new Task("1", "Prueba","Esto solo es una prueba","01:20","01/10/16", "to");
        Task t2 = new Task("2", "Café","Comprar café","02:30","03/12/15", "do");
        Task t3 = new Task("3", "Scrum","Hay que hacer la metodologia scrum en todos los proyectos","1:30","01/10/16", "finish");
        Task t4 = new Task("4", "Master","Ya estamos a punto de acabar el máster","10:30","03/12/15", "to");
        Task t5 = new Task("5", "Final","es el final del proyecto","01:20","01/10/16", "done");
        Task t6 = new Task("6", "Clase","La clase es una mierda","02:30","03/12/15", "doing");
        Task t7 = new Task("6", "Juanje","Juanje me pide ayuda","01:15","11/04/16", "doing");

        tasks.add(0,t1);
        tasks.add(1,t2);
        tasks.add(2,t3);
        tasks.add(3,t4);
        tasks.add(4,t5);
        tasks.add(5,t6);
        tasks.add(6,t7);


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
    @SuppressLint("ValidFragment")
    public class DesignDemoFragment extends Fragment {
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();
            int position = args.getInt(TAB_POSITION);

            String s = state.get(position);
            List<ExpandableListAdapter.Item> data = new ArrayList<>();
            for (Task t: tasks) {
                if (s.equals(t.getState())){
                    ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, t.getTittle());
                    places.invisibleChildren = new ArrayList<>();
                    places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, t.getDescription()));
                    places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tiempo Estimado: " + t.getTime()));
                    places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Fecha de Inicio: " + t.getDate()));
                    data.add(places);
                }
            }

            View rootView = inflater.inflate(R.layout.fragment_project_view, container, false);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            ExpandableListAdapter exp = new ExpandableListAdapter(data);
            exp.setMyProject(p);

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
            rv.setHasFixedSize(true);
            rv.setAdapter(exp);
            rv.setLayoutManager(llm);


            return rootView;
        }
    }

    //-----------SubClase--------------------

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
            return state.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return state.get(position);
        }
    }

    public Context getContext(){
        return getApplicationContext();
    }

    public ProjectsScrum getActivityProject(){
        return this;
    }
}
