package com.kiran.chess.model;

import java.util.List;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public class VerticalMoveStrategy implements BoardMoveStrategy {

    private final int boardHeight;


    public VerticalMoveStrategy(int boardHeight) {
        this.boardHeight = boardHeight;

    }

    @Override
    public List<BoardPosition> getPossibleMoves(BoardPosition currentPosition, BoardInfoProvider boardInfoProvider) {
        return null;
    }
}
