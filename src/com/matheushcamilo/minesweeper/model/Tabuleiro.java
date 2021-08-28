package com.matheushcamilo.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> CAMPOS = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                CAMPOS.add(new Campo(linha,coluna));
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

        }catch(ExplosionException e){
            CAMPOS.forEach(c -> c.setAberto(true));
            throw e;
        }
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("  ");
        for (int c = 0; c < colunas; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");
        for (int l = 0; l < linhas; l++) {
            sb.append(l);
            sb.append(" ");
            for (int c = 0; c < colunas; c++) {
                sb.append(" ");
                sb.append(CAMPOS.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

