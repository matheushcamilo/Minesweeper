package com.matheushcamilo.minesweeper.model.visão;

import com.matheushcamilo.minesweeper.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro) {

        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

        tabuleiro.registrarObservadores(e -> {
            //TODO mostrar se usuário ganhou ou perdeu
        });

    }
}
