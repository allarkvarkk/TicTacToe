package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Board implements Screen {
    private static Engine eng;
    private static Tile[][] grid;
    private static ShapeRenderer sr;
    private static BitmapFont f;
    private static SpriteBatch b;

    public Board(Engine eng) {
        Board.eng = eng;
        initialize();
    }

    public static void initialize() {
        sr = eng.getShapeRenderer();
        f = eng.getBitmapFont();
        b = eng.getSpriteBatch();
        fillGrid();
        ScoreHandler.initialize();
        InputHandler.initialize();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Tile.getTileColor());
        for (Tile[] value : grid) {
            for (Tile tile : value) {
                boolean collision = tile.isHovering();
                float x = tile.getBox().getX(), y = tile.getBox().getY(), w_h = tile.getBox().getWidth();

                if (collision) {
                    highlightHoveringTile(x, y, w_h);
                }
                sr.rect(x, y, w_h, w_h);
                tile.update();
                drawXPieces(tile);
                drawOPieces(tile);
            }
        }
        ScoreHandler.update();
        sr.end();
        displayEnd(sr);

    }

    public void drawOPieces(Tile tile) {
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Piece.getoColor());
        sr.circle(tile.getPiece().getOPiece()[0], tile.getPiece().getOPiece()[1], tile.getPiece().getOPiece()[2]);
        sr.setColor(Tile.getTileColor());
    }

    public void drawXPieces(Tile tile) {
        if (tile.getPieceType() == 'X') {
            for (int a = 0; a < tile.getPiece().getXPiece().length / 2; a++) {
                sr.end();
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(Piece.getxColor());
                sr.rectLine(tile.getPiece().getXPiece()[a], tile.getPiece().getXPiece()[tile.getPiece().getXPiece().length - 1 - a], Piece.getXThickness());
                sr.end();
                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.setColor(Tile.getTileColor());
            }
        }
    }

    public void highlightHoveringTile(float x, float y, float w_h) {
        sr.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Tile.getHoveringColor());
        sr.rect(x - 5, y - 5, w_h + 10, w_h + 10);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Tile.getTileColor());
    }

    public void displayEnd(ShapeRenderer sr) {
        if (ScoreHandler.isGameOver()) {
            b.begin();
            f.getData().setScale(3);
            f.draw(b, ScoreHandler.getWinningString(), Engine.getCenterOfWindow_X(), Engine.getWindowHeight() - 40);
            f.draw(b, "Press 'R' to restart", 300, Engine.getWindowHeight() - 50);
            b.end();
        }
    }


    public static void fillGrid() {
        grid = new Tile[3][3];
        int xPos = Engine.getCenterOfWindow_X() - (int) (Engine.GRID_OFFSET * (3.0 / 2)), yPos = Engine.getCenterOfWindow_y() - (int) (Engine.GRID_OFFSET * (3.0 / 2));
        for (int i = 0; i < grid.length; i++) {
            for (int u = 0; u < grid[i].length; u++) {
                grid[i][u] = new Tile(xPos, yPos, eng);
                yPos += Engine.GRID_OFFSET;
            }
            yPos = Engine.getCenterOfWindow_y() - (int) (Engine.GRID_OFFSET * (3.0 / 2));
            xPos += Engine.GRID_OFFSET;
        }
    }


    public static Tile[][] getGrid() {
        return grid;
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
        sr.dispose();
        b.dispose();
        f.dispose();
    }
}
