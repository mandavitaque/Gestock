package com.mandavitaque.gestock;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by vinic on 22/10/2017.
 */

public class Usuario {

    private String nome;
    private String sobrenome;
    private String sexo;
    private int idade;
    private String telefone;
    private String id;
    private String email;
    private String senha;


    public Usuario() {
    }

    public void salvar()
    {
        DatabaseReference usuarioFirebase = ConfiguracaoFirebase.getFirebase();
        usuarioFirebase.child("usuarios").child( getId() ).setValue( this );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
