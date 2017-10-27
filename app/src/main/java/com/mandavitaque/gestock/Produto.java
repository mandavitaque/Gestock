package com.mandavitaque.gestock;

import com.google.firebase.database.DatabaseReference;

import java.sql.Date;

/**
 * Created by vinic on 22/10/2017.
 */

public class Produto {

    private String nome;
    private String tipo;
    private int valorCompra;
    private int valorVenda;
    private String validade;
    private String codBarras;
    private int quantidade;
    private int qtdMinima;
    private double peso;
    private String ID;


    public Produto(){

    }

    public void salvar()
    {
        DatabaseReference usuarioFirebase = ConfiguracaoFirebase.getFirebase();
        usuarioFirebase.child("produtos").child(getID()).setValue( this );
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(int valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(int valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
