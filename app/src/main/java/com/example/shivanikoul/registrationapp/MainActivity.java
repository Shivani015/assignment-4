package com.example.shivanikoul.registrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name,pass,email,city;
        final Spinner spinner;
        Button btn;

            name =findViewById(R.id.name);
            email=findViewById(R.id.email);
            pass=findViewById(R.id.pass);
            city=findViewById(R.id.city);
            spinner=findViewById(R.id.spinner);
            btn=findViewById(R.id.btn);


            final String[] State ={"Assam","Goa","Kerala"};

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,State);
            spinner.setAdapter(adapter);

         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(MainActivity.this,secondActivity.class);

                    String userName= name.getText().toString();
                    String userEmail = email.getText().toString();
                    String userPassword = pass.getText().toString();
                    String userCity = city.getText().toString();
                    String spinnerData = spinner.getSelectedItem().toString();

                    if (TextUtils.isEmpty(name.getText())) {
                        name.setError("required");
                        return;
                    }
                    if (TextUtils.isEmpty(email.getText())) {
                        email.setError("required");
                        return;

                    }
                    if (TextUtils.isEmpty(pass.getText())) {
                        pass.setError("required");
                        return;
                    }
                    if (TextUtils.isEmpty(city.getText())) {
                        city.setError("required");
                        return;
                    }

                    //write data


                    String totalData = "Name :" + userName + "\n" + "Email :" + userEmail+ "\n" + "Password :" + userPassword + "\n" + "State :" + spinnerData + "\n" + "City :" + userCity ;
                    FileOutputStream fos;

                    try {
                        fos = openFileOutput("userData", MODE_PRIVATE);
                        fos.write(totalData.getBytes());
                        fos.close();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    startActivity(intent);
                }
            });
    }
}
