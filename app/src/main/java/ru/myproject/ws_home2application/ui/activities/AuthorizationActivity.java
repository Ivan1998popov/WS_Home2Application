package ru.myproject.ws_home2application.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ru.myproject.ws_home2application.R;

public class AuthorizationActivity extends AppCompatActivity {


    TextView text_login,text_password;
    Button btn_nav_drawer,btn_nav_btn_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        text_login=findViewById(R.id.text_login);
        text_password=findViewById(R.id.text_password);
        btn_nav_drawer=findViewById(R.id.btn_nav_drawer);
        btn_nav_btn_drawer=findViewById(R.id.btn_nav_btn_drawer);
        btn_nav_btn_drawer.setEnabled(false);
        btn_nav_drawer.setEnabled(false);
        text_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_nav_btn_drawer.setEnabled(false);
                    btn_nav_drawer.setEnabled(false);
                } else {
                    btn_nav_btn_drawer.setEnabled(true);
                    btn_nav_drawer.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        text_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_nav_btn_drawer.setEnabled(false);
                    btn_nav_drawer.setEnabled(false);
                } else {
                    btn_nav_btn_drawer.setEnabled(true);
                    btn_nav_drawer.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_nav_btn_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAuthorization()){
                    Intent intent =new Intent(getApplication(),BottomNavigationActivity.class);
                    intent.putExtra("name_user",text_login.getText().toString());
                    startActivity(intent);
                }
            }
        });

        btn_nav_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAuthorization()){
                    Intent intent =new Intent(getApplication(),NavigationDrawerActivity.class);
                    intent.putExtra("name_user",text_login.getText().toString());
                    startActivity(intent);
                }
            }
        });


    }
    private boolean checkAuthorization(){
        if(text_password.getText().toString().equals(getResources()
                .getString(R.string.password))){
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Не правильный логин или пароль!"
                    ,Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
