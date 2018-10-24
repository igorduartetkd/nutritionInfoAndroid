package com.igor.android.finalproject;

import java.util.ArrayList;

/**
 * Created by igor on 13/04/16.
 */
public class Elemento {
    private int qntDeFilhos = 0;
    private String nome;
    private boolean folha;
    private ArrayList<Elemento> proximos = new ArrayList<>();
    private Elemento pai = null;
    private int indexDescricao;

    Elemento(String nome){
        this.nome = nome;
        this.folha = false;
    }

    Elemento(String nome, int indexDescricao){
        this.nome = nome;
        this.folha = true;
        this.indexDescricao = indexDescricao;
    }


    public int getQntDeFilhos() {
        return qntDeFilhos;
    }

    public ArrayList<Elemento> getProximos() {
        return proximos;
    }

    public Elemento getPai() {
        return pai;
    }

    public String getNome() {
        return nome;
    }

    public boolean eFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPai(Elemento pai) {
        this.pai = pai;
    }

    public void setProximos(ArrayList<Elemento> proximos) {
        this.proximos = proximos;
    }

    public void setQntDeFilhos(int qntDeFilhos) {
        this.qntDeFilhos = qntDeFilhos;
    }

    public void incluirProximo(Elemento proximo){
        proximos.add(proximo);
        proximo.setPai(this);
        qntDeFilhos++;
    }

    public int getIndexDescricao() {
        return indexDescricao;
    }

    public void setIndexDescricao(int indexDescricao) {
        this.indexDescricao = indexDescricao;
    }
}
