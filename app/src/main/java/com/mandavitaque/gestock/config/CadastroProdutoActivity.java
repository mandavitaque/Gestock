package com.mandavitaque.gestock.config;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mandavitaque.gestock.CadastroUsuarioActivity;
import com.mandavitaque.gestock.Produto;
import com.mandavitaque.gestock.R;

public class CadastroProdutoActivity extends Activity {

    private Button btnSalvar;
    private Button btnCancelar;
    private EditText txtNome;
    private EditText txtTipo;
    private EditText txtValorCompra;
    private EditText txtValorVenda;
    private EditText txtQuantidade;
    private EditText txtQtdMinima;
    private AlertDialog.Builder confirmacao;
    private EditText txtValidade;
    private DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference produtosReferencia = databaseReferencia.child("produtos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        txtNome = findViewById(R.id.txtNomeID);
        txtQtdMinima = findViewById(R.id.txtQtdMinimaID);
        txtQuantidade = findViewById(R.id.txtQuantidadeID);
        txtValidade = findViewById(R.id.txtValidadeID);
        txtValorCompra = findViewById(R.id.txtValorCompraID);
        txtValorVenda = findViewById((R.id.txtValorVendaID));
        //txtTipo = findViewById(R.id.txtTipoID);
        btnCancelar = findViewById(R.id.btnCancelarID);
        btnSalvar = findViewById(R.id.btnSalvar);



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmacao = new AlertDialog.Builder(CadastroProdutoActivity.this);
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

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpNome = txtNome.getText().toString();
                int tmpQtdMinima = Integer.parseInt(txtQtdMinima.getText().toString());
                int tmpQuantidade = Integer.parseInt(txtQuantidade.getText().toString());
                String tmpValidade = txtValidade.getText().toString();
                int tmpValorCompra = Integer.parseInt(txtValorCompra.getText().toString());
                int tmpValorVenda = Integer.parseInt(txtValorVenda.getText().toString());
                //String tmpTipo = txtTipo.getText().toString();

                Produto produto = new Produto();
                {
                    produto.setNome(tmpNome);
                    produto.setQtdMinima(tmpQtdMinima);
                    produto.setQuantidade(tmpQuantidade);
                    //produto.setTipo(tmpTipo);
                    produto.setValidade((tmpValidade));
                    produto.setValorCompra(tmpValorCompra);
                    produto.setValorVenda(tmpValorVenda);
                }

                produtosReferencia.child("001").setValue(produto);

                txtNome.setText("");
                txtValorVenda.setText("");
                txtQtdMinima.setText("");
                txtQuantidade.setText("");
                //txtTipo.setText("");
                txtValidade.setText("");
                txtValorCompra.setText("");

            }
        });




    }
}
