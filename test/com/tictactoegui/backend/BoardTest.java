package com.tictactoegui.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BoardTest {
    @Test
    public void board3x3test() {
        Board board = new Board();

        assertNull(board.board);

        board.setBoard(3);

        assertEquals(board.board.length,3);
        assertEquals(board.board.length * board.board.length ,9);
    }
    @Test
    public void board10x10test() {
        Board board = new Board();

        assertEquals(board.board,null);

        board.setBoard(10);

        assertEquals(board.board.length,10);
        assertEquals(board.board.length * board.board.length ,100);
    }
}