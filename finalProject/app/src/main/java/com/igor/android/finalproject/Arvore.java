package com.igor.android.finalproject;



import java.text.Normalizer;

/**
 * Created by igor on 13/04/16.
 */
public class Arvore {

    private Elemento primeiro ;
    private Elemento apontando;


    public Arvore(){

        primeiro = new Elemento("NutrInfo");
        apontando = primeiro;
        int index = 1;

        primeiro.setPai(primeiro);

        Elemento tabela = new Elemento("Tabela Nutricional");

        primeiro.incluirProximo(tabela);
            Elemento carboidrato = new Elemento("Carboidratos", index++);
            Elemento proteina = new Elemento("Proteínas", index++);
            Elemento gorduras = new Elemento("Gorduras");
                Elemento gorduraTotal = new Elemento("Gorduras Totais", index++);
                Elemento gorduraTrans = new Elemento("Gorduras Trans", index++);
                Elemento gordurasSat = new Elemento("Gorduras Saturadas", index++);
                gorduras.incluirProximo(gorduraTotal);
                gorduras.incluirProximo(gorduraTrans);
                gorduras.incluirProximo(gordurasSat);
            Elemento fibras = new Elemento("Fibras", index++);
            Elemento sodio = new Elemento("Sódio", index++);
            Elemento vd = new Elemento("%VD", index++);
            Elemento medidaCaseira = new Elemento("Medida Caseira", index++);
            Elemento porcao = new Elemento("Porção", index++);
            tabela.incluirProximo(carboidrato);
            tabela.incluirProximo(proteina);
            tabela.incluirProximo(gorduras);
            tabela.incluirProximo(fibras);
            tabela.incluirProximo(sodio);
            tabela.incluirProximo(vd);
            tabela.incluirProximo(medidaCaseira);
            tabela.incluirProximo(porcao);

        Elemento ingredientes = new Elemento("Ingredientes" );
        primeiro.incluirProximo(ingredientes);

        Elemento acidulantes = new Elemento("Acidulantes", index++);
        Elemento antiespumantes = new Elemento("Antiespumantes", index++);
        Elemento antioxidantes = new Elemento("Antioxidantes", index++);
        Elemento aromas = new Elemento("Aromas", index++);
        Elemento antiumectantes = new Elemento("Antiumectantes", index++);
        Elemento corantes = new Elemento("Corantes", index++);
        Elemento conservadores = new Elemento("Conservadores", index++);
        Elemento edulcorantes = new Elemento("Edulcorantes", index++);
        Elemento espessantes  = new Elemento("Espessantes", index++);
        Elemento estabilizantes = new Elemento ("Estabilizantes", index++);
        Elemento umectantes = new Elemento ("Umectantes", index++);
        Elemento algumasDicas = new Elemento("Algumas Dicas", index++);
        ingredientes.incluirProximo(acidulantes);
        ingredientes.incluirProximo(antiespumantes);
        ingredientes.incluirProximo(antioxidantes);
        ingredientes.incluirProximo(aromas);
        ingredientes.incluirProximo(antiumectantes);
        ingredientes.incluirProximo(corantes);
        ingredientes.incluirProximo(conservadores);
        ingredientes.incluirProximo(edulcorantes);
        ingredientes.incluirProximo(espessantes);
        ingredientes.incluirProximo(estabilizantes);
        ingredientes.incluirProximo(umectantes);
        ingredientes.incluirProximo(algumasDicas);
    }

    public Elemento getApontando() {
        return apontando;
    }

    public Elemento getElemento(int pos){
        return apontando.getProximos().get(pos);
    }

    public Elemento getPai() {
        return apontando.getPai();
    }

    public void setApontando(Elemento apontar){
        this.apontando = apontar;
    }

    public boolean eRaiz() {
        if(primeiro == apontando){
            return true;
        }
        return false;
    }

    public void apontarPara(String nome){
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
        nome = nome.replaceAll("[^\\p{ASCII}]", "");
        nome = nome.toUpperCase();

        Elemento aux = buscarElemento(primeiro, nome);
        if(aux != null){
            apontando = aux;
        }
    }

    public Elemento buscarElemento(Elemento apontando, String nome){ //dfs
        Elemento saida = null;

        String nome2 = Normalizer.normalize(apontando.getNome(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        //se tiver encontrado o elemento que esta procurando
        if(nome2.toUpperCase().contains(nome) ){
            return apontando;
        }

        //se tiver chegado na folha e nao tiver encontrado retorna null:
        if(apontando.eFolha()){
            return null;
        }

        int i = 0;
        while(i < apontando.getProximos().size()){
            Elemento proximoApontando = apontando.getProximos().get(i);
            saida = buscarElemento(proximoApontando, nome);
            if(saida != null) return saida;
            i++;
        }

        return saida;
    }
}
