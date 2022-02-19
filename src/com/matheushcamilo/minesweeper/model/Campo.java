package com.matheushcamilo.minesweeper.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Campo {
    private final int linha, coluna;

    private boolean minado;
    private boolean marcado;
    private boolean aberto;

    private List<Campo> vizinhos = new ArrayList<>();
    private Set<CampoObservador> observadores = new HashSet<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservadores(CampoObservador observador){
        observadores.add(observador);
    }

    public void notificarObservadores(CampoEvento evento){
        observadores.stream()
                .forEach(o -> o.eventoOcorreu(this, evento));
    }


    void setAberto(boolean aberto) {
        this.aberto = aberto;
        if(aberto) notificarObservadores(CampoEvento.ABRIR);
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
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

    public void alternarMarcacao(){
        if(!aberto){
            marcado = ! marcado;
            if(marcado){
                notificarObservadores(CampoEvento.MARCAR);
            }else{
                notificarObservadores(CampoEvento.DESMARCAR);
            }
        }
    }

    public boolean abrir(){
        if(!aberto && !marcado){
            if(minado){
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }
            setAberto(true);

            if (vizinhancaSegura()){
                vizinhos.forEach(Campo::abrir);
            }
            return true;
        }
        //Caso o campo esteja marcado, não poderá ser aberto
        else{
            return false;
        }
    }

    boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
    }

    public void minar(){
       minado = true;
    }

    public boolean objetivoAlcancado(){
        boolean objetivo1 = minado && marcado;
        boolean objetivo2 = !minado && aberto;
        return objetivo1 || objetivo2;
    }

    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        minado = false;
        aberto = false;
        marcado = false;
    }

    public String toString(){
        if(minado && aberto){
            return "*";
        }
        else if(marcado){
            return "X";
        }
        else if(aberto && (minasNaVizinhanca() > 0)){
            return Long.toString(minasNaVizinhanca());
        }
        else if(aberto){
            return " ";
        }
        else{
            return "?";
        }

    }


}
