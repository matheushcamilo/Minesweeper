package com.matheushcamilo.minesweeper.model.visão;

import com.matheushcamilo.minesweeper.model.Campo;
import com.matheushcamilo.minesweeper.model.CampoEvento;
import com.matheushcamilo.minesweeper.model.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton implements CampoObservador, Mouse {
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCADO = new Color(8, 179, 247);
    private final Color BG_EXPLOSAO = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    private Campo campo;
    public BotaoCampo(Campo campo){
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        campo.registrarObservadores(this);
    };

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento campoEvento) {
        switch (campoEvento){
            case ABRIR:
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
                break;
        }
    }

    private void aplicarEstiloMarcar() {
    }
    private void aplicarEstiloExplodir() {
    }
    private void aplicarEstiloPadrao() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1){
            System.out.println("Botão Esquerdo!");
        }else{
            System.out.println("Botão Direito!");
        }
    }

}
