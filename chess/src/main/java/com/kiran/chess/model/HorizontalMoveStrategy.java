package com.kiran.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public class HorizontalMoveStrategy implements BoardMoveStrategy {

    private final int boardWidth;


    public HorizontalMoveStrategy(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    @Override
    public List<BoardPosition> getPossibleMoves(BoardPosition currentPosition, BoardInfoProvider boardInfoProvider) {
        List<BoardPosition> possibleMoves = new ArrayList<>();
        BoardPosition currPos = currentPosition.moveRight();
        while (currPos.getX() <= boardWidth) {
            possibleMoves.add(currPos);
            if (isCurrentPositionOccupied(currPos, boardInfoProvider)) {
                break;
            }
            currPos = currPos.moveRight();
        }

        currPos = currentPosition.moveLeft();
        while (currPos.getX() >= 1) {
            possibleMoves.add(currentPosition);
            if (isCurrentPositionOccupied(currPos, boardInfoProvider)) {
                break;
            }
            currPos = currPos.moveLeft();
        }

        return possibleMoves;
    }

    private boolean isCurrentPositionOccupied(BoardPosition currentPosition, BoardInfoProvider boardInfoProvider) {
        return boardInfoProvider.isBoardPositionOccupiedByOpponent(currentPosition);
    }
}
