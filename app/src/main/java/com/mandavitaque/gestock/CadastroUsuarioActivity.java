package com.mandavitaque.gestock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUsuarioActivity extends Activity {

    private FirebaseAuth firebaseAuth;

    private Button btnCadastro;
    private Button btnCancelar;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtSobrenome;
    private String txtSexo;
    private RadioGroup radioGroup;
    private RadioButton radioEscolhido;
    private EditText txtIdade;
    private AlertDialog.Builder confirmacao;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        firebaseAuth = FirebaseAuth.getInstance();

        btnCadastro = findViewById(R.id.btnCadastrarID);
        btnCancelar = findViewById(R.id.btnCancelarID);
        txtEmail = findViewById(R.id.txtEmailID);
        txtSenha = findViewById(R.id.txtSenhaID);
        txtNome = findViewById(R.id.txtNomeID);
        txtIdade = findViewById(R.id.txtIdadeID);
        radioGroup = findViewById(R.id.radioGroupSexoID);
        txtSobrenome = findViewById(R.id.txtSobrenomeID);
        txtTelefone = findViewById(R.id.txtTelefoneID);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pegando texto digitado pelo cliente
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();
                if (idRadioButtonEscolhido >0)
                {
                    radioEscolhido = findViewById(idRadioButtonEscolhido);
                    txtSexo = (radioEscolhido.getText().toString());

                }

                usuario = new Usuario();
                usuario.setNome(txtNome.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());
                usuario.setIdade(Integer.parseInt(txtIdade.getText().toString()));
                usuario.setSobrenome(txtSobrenome.getText().toString());
                usuario.setTelefone(txtTelefone.getText().toString());
                usuario.setSexo(txtSexo);
                cadastrarUsuario();

            }
        });
    }
             private void cadastrarUsuario()
        {
            firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
            firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                    .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(CadastroUsuarioActivity.this, "Cadastro Criado com Sucesso!", Toast.LENGTH_LONG).show();
                            FirebaseUser usuarioFirebase = task.getResult().getUser();
                            usuario.setId( usuarioFirebase.getUid());
                            usuario.salvar();

                            firebaseAuth.signOut();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(CadastroUsuarioActivity.this, "Cadastro não foi realizado", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmacao = new AlertDialog.Builder(CadastroUsuarioActivity.this);
                confirmacao.setTitle("Confirmar a ação");
                confirmacao.setMessage("Deseja realmente cancelar?");
                confirmacao.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                confirmacao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                confirmacao.create();
                confirmacao.show();

            }
        });
    }




                }




