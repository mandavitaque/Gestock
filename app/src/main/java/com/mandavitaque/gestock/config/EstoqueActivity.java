package com.mandavitaque.gestock.config;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mandavitaque.gestock.R;

public class EstoqueActivity extends Activity {

    private ListView listViewProdutos;
    private DatabaseReference produtoReferencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        listViewProdutos = findViewById(R.id.listviewEstoqueID);

    }
}
