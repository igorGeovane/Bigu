package com.oxi.bigu.controller;

import android.app.Activity;
import android.content.Intent;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.oxi.bigu.R;

import util.ValidacaoUtil;

public class LoginActivity extends Activity implements View.OnClickListener {

    private final int REQ_SIGNUP = 1;

    public final static String PARAM_USER_PASS = "USER_PASS";

    private String authTokenType;

    private Button buttonLogin;
    private EditText editTextUsuario;
    private EditText editTextSenha;
    private TextView textViewPrimeiroAcesso;
    private LoginButton buttonLoginFacebook;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFields();
        initCallbacks();
        initFacebook();
    }

    private void initFacebook() {
        callbackManager = CallbackManager.Factory.create();

        buttonLoginFacebook = (LoginButton) findViewById(R.id.login_button);
        buttonLoginFacebook.setReadPermissions("user_friends", "public_profile");

        buttonLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getIntent().putExtra(MainActivity.ARG_TOKEN, loginResult.getAccessToken());
                setResult(Activity.RESULT_OK);
                finish();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }

    private void initCallbacks() {
        textViewPrimeiroAcesso.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    private void initFields() {
        buttonLogin = (Button) findViewById(R.id.buttonCriar);
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        textViewPrimeiroAcesso = (TextView) findViewById(R.id.textViewPrimeiroAcesso);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.buttonCriar):
                entrar();
                break;
            case (R.id.textViewPrimeiroAcesso):
                primeiroAcesso();
                break;
        }
    }

    private void primeiroAcesso() {
        Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
        startActivityForResult(intent, REQ_SIGNUP);
    }

    private void entrar() {
        if (!ValidacaoUtil.validarCamposVazios(editTextUsuario, editTextSenha)) {
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up buttonLogin, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
