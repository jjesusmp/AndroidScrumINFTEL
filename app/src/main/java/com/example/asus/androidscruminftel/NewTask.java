package com.example.asus.androidscruminftel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    String tittle;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            tittle = bundle.getString("tittle");
            content = bundle.getString("content");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        //Mediante getItem se obtiene el vlaor del botn pulsado
        switch (id){
            case R.id.action_save:
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
}
