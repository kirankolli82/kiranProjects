package com.kiran.chess.model;

import java.util.List;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public interface BoardMoveStrategy {

    List<BoardPosition> getPossibleMoves(BoardPosition currentPosition, BoardInfoProvider boardInfoProvider);
}
