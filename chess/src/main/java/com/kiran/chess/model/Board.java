package com.kiran.chess.model;

import com.kiran.chess.view.BoardPosHoverListener;

import java.util.*;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public class Board implements BoardPosHoverListener {

    private final Map<Player, Map<BoardPosition, ChessPiece>> playerPositionMap = new HashMap<>();
    private final Map<BoardPosition, Tuple<ChessPiece, Player>> boardPositionMap = new HashMap<>();

    public Board() {
        //populate board here
    }

    @Override
    public List<BoardPosition> onHover(BoardPosition boardPosition, Player player) {
        if (!playerPositionMap.get(player).containsKey(boardPosition)) {
            return Collections.emptyList();
        }

        List<BoardPosition> possibleMoves = new ArrayList<>();

        for (BoardMoveStrategy boardMoveStrategy :
                playerPositionMap.get(player).get(boardPosition).getMoveStrategies()) {
            possibleMoves.addAll(
                    boardMoveStrategy.getPossibleMoves(boardPosition, boardPosition1 ->
                            isBoardPositionOccupiedByOpponent(player, boardPosition1)));
        }

        return possibleMoves;
    }

    private boolean isBoardPositionOccupiedByOpponent(Player player, BoardPosition boardPosition1) {
        return boardPositionMap.containsKey(boardPosition1) && !Objects.equals(boardPositionMap.get(boardPosition1), player);
    }
}
