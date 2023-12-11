package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Board implements Screen {
    private Engine eng;
    private Piece piece;
    public Rectangle[][] grid;
    private boolean isHovering;
    private Color player1Color, player2Color, gridColor;
    private ArrayList<Vector2[]> xPieces;
    private boolean[][] isWin;

    public Board(Engine eng) {
        this.eng = eng;
       piece = new Piece();
        isHovering = false;
        fillGrid();
        player1Color = new Color(77 / 255f, 247 / 255f, 10 / 255f, 0.25f);
        gridColor = new Color(1,1,1,1.0f);
        xPieces = new ArrayList<Vector2[]>();
        isWin = new boolean[3][3];
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        eng.sr.begin(ShapeRenderer.ShapeType.Line);
        eng.sr.setColor(gridColor);
        for (int i = 0; i < grid.length; i++) {
            for (int u = 0; u < grid[i].length; u++) {
                boolean collision = checkCollisionOnTile(grid[i][u]);
                float x = grid[i][u].getX(), y = grid[i][u].getY(), w_h = grid[i][u].getWidth();
                if (collision) {
                    eng.sr.end();
                    Gdx.gl.glEnable(GL20.GL_BLEND);
                    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    eng.sr.begin(ShapeRenderer.ShapeType.Filled);
                    eng.sr.setColor(player1Color);
                    eng.sr.rect(x - 5, y - 5, w_h + 10, w_h + 10);
                    eng.sr.end();
                    Gdx.gl.glDisable(GL20.GL_BLEND);
                    eng.sr.begin(ShapeRenderer.ShapeType.Line);
                    eng.sr.setColor(gridColor);
                }
                eng.sr.rect(x, y, w_h, w_h);
                if(grid[i][u].contains(eng.inputHandler.getPressedPos())){
                    updateShapes(grid[i][u]);
                }

            }
        }
        eng.sr.end();
        eng.sr.begin(ShapeRenderer.ShapeType.Filled);
        for (Vector2[] xPiece : xPieces) {
            for (int u = 0; u < xPiece.length / 2; u++) {
                eng.sr.rectLine(xPiece[u], xPiece[xPiece.length - u - 1], 10);
            }
        }

        eng.sr.end();


    }

    public void updateShapes(Rectangle rect){
        xPieces.add(piece.createXPiece(rect));
    }


    public void fillGrid(){
        grid = new Rectangle[3][3];
        int xPos = eng.getCenterOfWindow_X() - (int)(Engine.GRID_OFFSET * (3.0/2)), yPos = eng.getCenterOfWindow_y() - (int)(Engine.GRID_OFFSET * (3.0/2));
        for (int i = 0; i < grid.length; i ++) {
            for (int u = 0; u < grid[i].length; u++) {
                grid[i][u] = new Rectangle(xPos, yPos, Engine.GRID_OFFSET, Engine.GRID_OFFSET);
                yPos += Engine.GRID_OFFSET;
            }
            yPos = eng.getCenterOfWindow_y() - (int)(Engine.GRID_OFFSET * (3.0/2));
            xPos += Engine.GRID_OFFSET;
        }
    }


    public boolean checkCollisionOnTile(Rectangle rect) {
        return rect.contains(eng.inputHandler.getMousePos());
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        eng.sr.dispose();
    }
}
