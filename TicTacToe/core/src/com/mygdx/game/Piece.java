package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Piece {
    private static Color xColor, oColor;
    private static int xThickness;
    private final int boxSize = Engine.GRID_OFFSET;
    private int offset;
    Vector2[] xPiece;
    int[] oPiece;

    public Piece() {
        initialize();
    }

    public void initialize() {
        xPiece = new Vector2[4];
        oPiece = new int[3];
        xColor = Color.RED;
        oColor = Color.GREEN;
        xThickness = 10;
        offset = 50;

    }

    public void createXPiece(Rectangle rect) {
        int rectX = (int) rect.getX() + offset, rectY = (int) rect.getY() + offset;
        xPiece[0] = new Vector2(rectX, rectY); //bottom left
        xPiece[1] = new Vector2(rectX, rectY + rect.getWidth() - (2 * offset)); //top left
        xPiece[2] = new Vector2(rectX + rect.getWidth() - (2 * offset), rectY); //bottom right
        xPiece[3] = new Vector2(rectX + rect.getWidth() - (2 * offset), rectY + rect.getWidth() - (2 * offset)); //top right
    }

    public void createOPiece(Rectangle rect) {
        int rectX = (int) rect.getX(), rectY = (int) rect.getY();
        oPiece[0] = (int) (rectX + (boxSize / 2));
        oPiece[1] = (int) (rectY + (boxSize / 2));
        oPiece[2] = (int) (boxSize / 2);
    }

    public int[] getOPiece() {
        return oPiece;
    }

    public Vector2[] getXPiece() {
        return xPiece;
    }

    public static int getXThickness() {
        return xThickness;
    }

    public static Color getxColor() {
        return xColor;
    }

    public static Color getoColor() {
        return oColor;
    }


}
