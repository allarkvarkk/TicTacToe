package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
    private boolean isTileEmpty;
    private int xPos, yPos;
    private static Color tileColor, hoveringColor;
    private Rectangle box;
    private Engine eng;
    private static short turn;
    private char pieceType;
    private Piece piece;


    public Tile(int xPos, int yPos, Engine eng) {
        this.eng = eng;
        this.xPos = xPos;
        this.yPos = yPos;
        initialize();

    }

    public void update() {
        placePiece();
    }


    public void initialize() {
        pieceType = 'e';
        turn = 1;
        piece = new Piece();
        createBox();
        tileColor = new Color(1, 1, 1, 1);
        hoveringColor = new Color(77 / 255f, 247 / 255f, 10 / 255f, 0.25f);

    }

    public void placePiece() {
        if (box.contains(eng.inputHandler.getPressedPos()) && pieceType == 'e' && !ScoreHandler.isGameOver()) {
            if (turn % 2 != 0) {
                pieceType = 'X';
                piece.createXPiece(box);

            } else {
                pieceType = 'O';
                piece.createOPiece(box);
            }
            turn++;
        }
    }

    public char getPieceType() {
        return pieceType;
    }

    public Piece getPiece() {
        return piece;
    }


    public void createBox() {
        box = new Rectangle(xPos, yPos, Engine.GRID_OFFSET, Engine.GRID_OFFSET);
    }

    public static Color getHoveringColor() {
        return hoveringColor;
    }

    public static Color getTileColor() {
        return tileColor;
    }

    public boolean isHovering() {
        return box.contains(eng.inputHandler.getXPos(), eng.inputHandler.getYPos());
    }

    public Rectangle getBox() {
        return box;
    }


}
