package com.nezspencer.signin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import dmax.dialog.SpotsDialog;


/**
 * Created by AGBOMA franklyn on 5/25/17.
 */

public class RegisterAds extends AppCompatActivity {

    private EditText regId,name,date,email, mobile;
    private Button regBtn;
    public static String NAME = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        init();
    }

    private void init() {

        regId = (EditText) findViewById(R.id.ref_id);
        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        regBtn = (Button) findViewById(R.id.regBttn);
        regBtn.setEnabled(false);
        regBtn.setBackgroundColor(getResources().getColor(R.color.button));

        regId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty()){
                    regBtn.setEnabled(true);
                    regBtn.setBackgroundColor(getResources().getColor(R.color.app_color));
                }
                else {
                    regBtn.setEnabled(false);
                    regBtn.setBackgroundColor(getResources().getColor(R.color.button));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid;
                boolean number;
                String getId = regId.getText().toString().trim();
                final String getName = name.getText().toString().trim();
                String getDate = date.getText().toString().trim();
                String getEmail = email.getText().toString().trim();
                String getMobile = mobile.getText().toString().trim();

                if(getId.isEmpty())
                    regId.setError("Invalid Id");
                if(!getMobile.startsWith("+234")) {
                    number = false;
                    mobile.setError("Add +234");
                }
                else
                    number = true;

                if(!getEmail.contains("@") || !getEmail.contains(".com")) {
                    valid = false;
                    email.setError("Invalid email");
                }
                else
                    valid = true;

                if(!getId.isEmpty() && !getName.isEmpty() && !getDate.isEmpty()
                        && valid  && number){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(RegisterAds.this, "Registered successfully",
                                    Toast.LENGTH_SHORT).show();
                            NAME = getName;
                        }
                    }, 2000);


                    startActivity(new Intent(RegisterAds.this, Event.class));

                }
                else
                    Toast.makeText(RegisterAds.this, "Complete the form",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}
