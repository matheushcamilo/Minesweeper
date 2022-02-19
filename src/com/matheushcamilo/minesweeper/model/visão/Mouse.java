package com.matheushcamilo.minesweeper.model.visão;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface Mouse extends MouseListener {
    @Override
    default void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    default void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    default void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    default void mouseExited(MouseEvent e) {
        return;
    }


}
