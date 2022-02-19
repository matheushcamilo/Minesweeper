package com.matheushcamilo.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador{
    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> CAMPOS = new ArrayList<>();
    private List<Consumer<Boolean>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void registrarObservadores(Consumer<Boolean> observador){
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado){
        observadores.stream()
                .forEach(o -> o.accept(resultado));
    }
    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Campo campo = new Campo(linha, coluna);
                campo.registrarObservadores(this);
                CAMPOS.add(campo);
            }
        }
    }

    private void associarVizinhos() {
        for (Campo campo1: CAMPOS){
            for (Campo campo2: CAMPOS) {
                campo1.adicionarVizinho(campo2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = Campo::isMinado;
        do {
            int aleatorio = (int) (Math.random() * CAMPOS.size());
            CAMPOS.get(aleatorio).minar();
            minasArmadas = CAMPOS.stream().filter(minado).count();
        }while (minasArmadas < minas);

    }
    public void abrir(int linha, int coluna){
        try{
            CAMPOS.parallelStream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());

        }catch(Exception e){ //FIXME Modificar método
            CAMPOS.forEach(c -> c.setAberto(true));
            throw e;
        }
    }
    private void mostrarMinas(){
        CAMPOS.stream()
                .filter(c -> c.isMinado())
                .forEach(c -> c.setAberto(true));
    }

    public void marcar(int linha, int coluna){
        CAMPOS.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    public boolean objetivoAlcancado() {
        return CAMPOS.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reinicar(){
        CAMPOS.stream().forEach(c -> c.reiniciar());
        sortearMinas();
        }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento campoEvento) {
        if(campoEvento == CampoEvento.EXPLODIR){
            mostrarMinas();
            notificarObservadores(false);
        }else if(objetivoAlcancado()){
            notificarObservadores(true);
        }
    }

    public int getLinhas() {
        return this.linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void paraCadaCampo(Consumer<Campo> funcao){
        CAMPOS.forEach(funcao);
    }
}

