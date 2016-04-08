package com.example.asus.androidscruminftel;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.androidscruminftel.connection.PostHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import model.Project;

public class NewProjectActivity extends AppCompatActivity {

    //String url = "http://192.168.1.136:8080/AppInftelScrum/webresources/entity.proyectoscrum";
    String url = "http://192.168.1.147:443/newProject";

    private Project project;
    private Spinner spinner1;
    private TextView textView;
    private Button btnSubmit;
    private int nestados = 1;
    private String[] arrText;
    private String[] arrTemp;
    private ArrayList<String> arrText2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        arrText = new String[nestados];
        for(int i=0; i<nestados;i++){
            arrText[i]= "Estado 1";
        }

        arrTemp = new String[arrText.length];
        MyListAdapter myListAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myListAdapter);


        project = new Project();

        addListenerOnSpinnerItemSelection();
        addListenerOnButton();

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                System.out.println("seleccionado" + spinner1.getSelectedItem());
                Toast.makeText(NewProjectActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();



                TextInputEditText textName = (TextInputEditText) findViewById(R.id.input_nameProject);
                project.setName(textName.getText().toString());

                TextInputEditText textDescriptionProduct = (TextInputEditText) findViewById(R.id.input_descriptionProject);
                project.setDescription(textDescriptionProduct.getText().toString());

                //project.setEstados(arrTemp);//arrTemp



                for(int i=0;i<nestados;i++){
                    arrText2.add(arrTemp[i]);
                }
                project.setEstados(arrText2);

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nombre", project.getName());
                    jsonObject.put("descripcion", project.getDescription());
                    jsonObject.put("id_admin", "jjaldoasenjo@gmail.com");

                    JSONObject jsonObject1 = new JSONObject();
                    JSONArray state = new JSONArray();
                    for(int i=0;i<nestados;i++) {
                        jsonObject1.put("nombre",arrTemp[i]);
                        jsonObject1.put("posicion", i);
                        state.put(i,jsonObject1);

                    }

                    jsonObject.put("estados", state);
                    jsonObject.put("chat", "");
                    //jsonObject.put("fecha_inicio")
                    jsonObject.put("id_proyecto", 1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new PostHttp(getContext()).execute(url,jsonObject.toString());
            }

        });
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
            nestados=Integer.parseInt(parent.getItemAtPosition(pos).toString());
            System.out.println("ESTADO"+nestados);
//            TextView textEmpty = (TextView) findViewById(R.id.input_status2);
//            if(estados.equals("uno")){
//                textEmpty.setVisibility(View.INVISIBLE);
//
//            }else if(estados.equals("dos")){
//                textEmpty.setVisibility(View.VISIBLE);
//                textEmpty.setHint("Estado 2");
//            }
            //arrText = new String[]{"Estado 1"};
            arrText = new String[nestados];
            for(int i=0; i<nestados;i++){
                arrText[i]= "Estado " + (i+1);
                //arrText2.add(i, "Estado " + i);

            }


            arrTemp = new String[arrText.length];
            MyListAdapter myListAdapter = new MyListAdapter();
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(myListAdapter);




        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if(arrText != null && arrText.length != 0){
                return arrText.length;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arrText[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //ViewHolder holder = null;
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                LayoutInflater inflater = NewProjectActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.listview_list, null);
                holder.editText1 = (EditText) convertView.findViewById(R.id.editText1);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.ref = position;

            holder.editText1.setText(arrTemp[position]);
            holder.editText1.setHint(arrText[position]);
            holder.editText1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    arrTemp[holder.ref] = arg0.toString();
                }
            });


            return convertView;
        }

        private class ViewHolder {
            TextView textView1;
            EditText editText1;
            int ref;
        }
    }

    public Context getContext(){
        return getApplicationContext();
    }

}

