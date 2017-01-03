package com.kiran.chess.view;

import com.kiran.chess.model.BoardPosition;
import com.kiran.chess.model.Player;

import java.util.List;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public interface BoardPosHoverListener {

    List<BoardPosition> onHover(BoardPosition boardPosition, Player player);
}
