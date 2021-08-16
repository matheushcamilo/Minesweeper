package com.matheushcamilo.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha, coluna;

    private boolean minado;
    private boolean marcado;
    private boolean aberto;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    public boolean adicionarVizinho(Campo vizinho){
        //Descobrindo se o candidato a vizinho está na diagonal
        boolean linhaDiferente = vizinho.linha != linha;
        boolean colunaDiferente = vizinho.coluna != coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;
        //Calculando a distância geral do Campo atual
        //Valores permitidos para novo vizinho
        //  1 - Para o caso de não estar na diagonal
        //  2 - Para o caso de estar na diagonal
        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        var deltaGeral = deltaColuna + deltaLinha;

        if(!diagonal && deltaGeral == 1){
            vizinhos.add(vizinho);
            return true;
        }
        else if(diagonal && deltaGeral == 2){
            vizinhos.add(vizinho);
            return true;
        }
        else{
            return false;
        }
    }
}
