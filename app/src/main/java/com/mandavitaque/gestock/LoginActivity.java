package com.mandavitaque.gestock;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends Activity {


    private static final String TAG = "EmailPassword";

    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnRegistrar;
    private TextView txtStatus;
    private FirebaseAuth firebaseAuth;
    private Usuario usuario;
    private DatabaseReference referenciaDatabase;


    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btnLogin = findViewById(R.id.btnLoginID);
        txtEmail = findViewById(R.id.txtEmailID);
        txtSenha = findViewById(R.id.txtSenhaID);
        btnRegistrar = findViewById(R.id.btnRegistrarID);


        // [START initialize_auth]
        firebaseAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        referenciaDatabase = ConfiguracaoFirebase.getFirebase();



        btnRegistrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
           public void onClick(View v)
            {
                String tmpEmail = txtEmail.getText().toString();
                String tmpSenha = txtSenha.getText().toString();
                usuario = new Usuario();
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());
                if (TextUtils.isEmpty(tmpEmail))
                {
                    txtEmail.setError("Insira um e-mail!");
                }
                else if(TextUtils.isEmpty(tmpSenha))
                {
                    txtSenha.setError("Insira uma senha!");
                }
                else
                validarLogin();
            }
        });

    }

    private void verificarUsuarioLogado()
    {
        if (firebaseAuth.getCurrentUser() != null)
        {
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void validarLogin()
    {
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Entrando...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()

                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {//logou com sucesso
                                    progressDialog.dismiss();
                                    abrirTelaPrincipal();
                                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login!", Toast.LENGTH_LONG).show();
                                } else //erro ao logar
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Erro ao fazer login ", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


    }


        }



