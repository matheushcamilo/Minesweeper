package com.matheushcamilo.minesweeper.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while(continuar){
                cicloDoJogo();
                System.out.println("Outra partida? (S/n)");
                String resposta = entrada.nextLine();
                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                }
                else{
                    tabuleiro.reinicar();
                }
            }
        }catch (SairException e){
            System.out.println("Até mais!!!");
        }finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while(!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);
                String digitado = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();
                int x = xy.next();
                int y = xy.next();
                digitado = capturarValorDigitado("1 - Abrir; 2 - Marcar(Desmarcar): ");

                if("1".equalsIgnoreCase(digitado)){
                    tabuleiro.abrir(x, y);
                }else if("2".equalsIgnoreCase(digitado)){
                    tabuleiro.marcar(x, y);
                }
            }
            System.out.println("Você ganhou!!!");
        }catch(ExplosionException e){
            System.out.println(tabuleiro);
            System.out.println("Você perdeu");
        }
    }
    private String capturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = entrada.nextLine();
        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
