package com.matheushcamilo.minesweeper.model;

@FunctionalInterface
public interface CampoObservador {
    void eventoOcorreu(Campo campo, CampoEvento campoEvento);
}
