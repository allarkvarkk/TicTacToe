package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Piece{
  //  private int[] xPiece, oPiece;
    private Engine eng;
    public Piece() {

    }

    public Vector2[] createXPiece(Rectangle rect){
        Vector2[] xPiece = new Vector2[4];
        int offset = 50;
        int rectX = (int) rect.getX() + offset, rectY = (int) rect.getY() + offset;
        xPiece[0] = new Vector2(rectX, rectY); //bottom left
        xPiece[1] = new Vector2(rectX, rectY + rect.getWidth() - (2 * offset)); //top left
        xPiece[2] = new Vector2(rectX + rect.getWidth() - (2 * offset), rectY); //bottom right
        xPiece[3] = new Vector2(rectX + rect.getWidth() - (2 * offset), rectY + rect.getWidth() - (2 * offset)); //top right
        return xPiece;

    }


}
