package com.zdl.mymvptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.zdl.mymvptest.login.view.loginView;

public class MainActivity extends AppCompatActivity implements loginView {

    private EditText et_login_username,editPass;
    private  Button btnLogin, btnClear;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_login_username = (EditText) findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        btnClear = (Button) this.findViewById(R.id.btn_login_clear);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);


    }

    @Override
    public void cleartext() {

    }

    @Override
    public void onLoginresult() {

    }

    @Override
    public void progressbarvisi() {

    }
}
