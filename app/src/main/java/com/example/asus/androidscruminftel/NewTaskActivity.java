package com.example.asus.androidscruminftel;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.androidscruminftel.model.Tarea;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    //private String url = "http://secureuma.no-ip.org:8080/editTask/newTask/";
    private String urlnew = "http://192.168.1.148:8080/newTask/";
    private String urledit = "http://192.168.1.148:8080/editTask/";
    Tarea tarea;

    private Spinner spinner;
    String tittle;
    String content;
    String id_task;


    TextInputEditText textName;
    TextInputEditText textDescript;
    TextInputEditText fechaInicio;
    TextInputEditText totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        android.support.v7.app.ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

        tarea = new Tarea();

        textName = (TextInputEditText) findViewById(R.id.input_tittleTask);
        textDescript =  (TextInputEditText) findViewById(R.id.input_descriptionTask);
        fechaInicio =  (TextInputEditText) findViewById(R.id.input_dateIni);
        totalTime =  (TextInputEditText) findViewById(R.id.input_time);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            tittle = bundle.getString("tittle");
            content = bundle.getString("content");
            id_task = bundle.getString("idTask");
            EditText input_tittle = (EditText) findViewById(R.id.input_tittleTask);
            EditText input_content = (EditText) findViewById(R.id.input_descriptionTask);

            input_tittle.setText(tittle);
            input_content.setText(content);
        }


        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> state;
        state = new ArrayList<>();
        state.add(0,"to");
        state.add(1,"do");
        state.add(2,"done");
        state.add(3,"doing");
        state.add(4,"finish");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    private void saveTask() throws JSONException {
        Bundle bundle = this.getIntent().getExtras();
        String url;
        if (bundle == null){
            url = urlnew + AndroidScrumINFTELActivity.getInstance().getProject().getId();
        }else{
            //url = urledit + bundle.getString("idTask");
            url = urledit + id_task;
        }
        tarea.setNombre_tarea(textName.getText().toString());
        tarea.setDescripcion(textDescript.getText().toString());
        tarea.setFecha_inicio(fechaInicio.getText().toString());
        tarea.setTiempo_estimado(totalTime.getText().toString());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("nombre_tarea", tarea.getNombre_tarea());
        jsonObject.put("descripcion", tarea.getDescripcion());
        jsonObject.put("tiempo_estimado", tarea.getTiempo_estimado());
        jsonObject.put("nombre_usuario", AndroidScrumINFTELActivity.getInstance().getEmail());
        jsonObject.put("fecha_inicio", tarea.getFecha_inicio());
        jsonObject.put("fichero","eo");
        jsonObject.put("nombre_fichero","nombre._fich");
        jsonObject.put("estado", "CUIDAO");

//ENVIO EL JSON
        //NECESITAMOS UNA VARIABLE SELECTED_PROJECT CON LA ID DEL PROYECTO SELECCIONADO
       new PostHttp(this).execute(url, jsonObject.toString());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch (id){
            case R.id.action_save:

                try {
                    saveTask();
                    Toast.makeText(this,"HA ENTRAO EZUUU",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner.setSelection(position);
        String selState = (String) spinner.getSelectedItem();
        showToast(selState);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void showToast(String cadena) {
        Toast toast = Toast.makeText(this, cadena, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void savetaskonclick(View view){
        try {
            Toast.makeText(getApplicationContext(),"EZU",Toast.LENGTH_SHORT).show();
            saveTask();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
