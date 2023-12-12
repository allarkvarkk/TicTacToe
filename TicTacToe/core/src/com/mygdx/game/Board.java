package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Board implements Screen {
    private static Engine eng;
    private static Tile[][] grid;

    public Board(Engine eng) {
        this.eng = eng;
        initialize();
    }

    public static void initialize() {
        fillGrid();
        ScoreHandler.initialize();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        eng.sr.begin(ShapeRenderer.ShapeType.Line);
        eng.sr.setColor(Tile.getTileColor());
        for (Tile[] value : grid) {
            for (Tile tile : value) {
                boolean collision = tile.isHovering();
                float x = tile.getBox().getX(), y = tile.getBox().getY(), w_h = tile.getBox().getWidth();
                if (collision) {
                    eng.sr.end();
                    Gdx.gl.glEnable(GL20.GL_BLEND);
                    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    eng.sr.begin(ShapeRenderer.ShapeType.Filled);
                    eng.sr.setColor(Tile.getHoveringColor());
                    eng.sr.rect(x - 5, y - 5, w_h + 10, w_h + 10);
                    eng.sr.end();
                    Gdx.gl.glDisable(GL20.GL_BLEND);
                    eng.sr.begin(ShapeRenderer.ShapeType.Line);
                    eng.sr.setColor(Tile.getTileColor());
                }
                eng.sr.rect(x, y, w_h, w_h);
                tile.update();
                if (tile.getPieceType() != 'e') {
                    if (tile.getPieceType() == 'X') {
                        for (int a = 0; a < tile.getPiece().getXPiece().length / 2; a++) {

                            eng.sr.end();
                            eng.sr.begin(ShapeRenderer.ShapeType.Filled);
                            eng.sr.setColor(Piece.getxColor());
                            eng.sr.rectLine(tile.getPiece().getXPiece()[a], tile.getPiece().getXPiece()[tile.getPiece().getXPiece().length - 1 - a], Piece.getXThickness());
                            eng.sr.end();
                            eng.sr.begin(ShapeRenderer.ShapeType.Line);
                            eng.sr.setColor(Tile.getTileColor());
                        }
                    }
                    eng.sr.end();
                    eng.sr.begin(ShapeRenderer.ShapeType.Line);
                    eng.sr.setColor(Piece.getoColor());
                    eng.sr.circle(tile.getPiece().getOPiece()[0], tile.getPiece().getOPiece()[1], tile.getPiece().getOPiece()[2]);
                    eng.sr.setColor(Tile.getTileColor());
                }

            }
        }
        ScoreHandler.update();
        ScoreHandler.checkColumn();
        ScoreHandler.checkRow();
        ScoreHandler.checkDiagonal();

        if (ScoreHandler.isGameOver()) {
            eng.b.begin();
            eng.f.getData().setScale(3);
            eng.f.draw(eng.b, ScoreHandler.getWinningString(), Engine.getCenterOfWindow_X(), Engine.getWindowHeight() - 40);
            eng.f.draw(eng.b, "Press 'R' to restart", 300, Engine.getWindowHeight() - 50);
            eng.b.end();
        }
        eng.sr.end();

    }


    public static void fillGrid() {
        grid = new Tile[3][3];
        int xPos = eng.getCenterOfWindow_X() - (int) (Engine.GRID_OFFSET * (3.0 / 2)), yPos = eng.getCenterOfWindow_y() - (int) (Engine.GRID_OFFSET * (3.0 / 2));
        for (int i = 0; i < grid.length; i++) {
            for (int u = 0; u < grid[i].length; u++) {
                grid[i][u] = new Tile(xPos, yPos, eng);
                yPos += Engine.GRID_OFFSET;
            }
            yPos = eng.getCenterOfWindow_y() - (int) (Engine.GRID_OFFSET * (3.0 / 2));
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
        eng.sr.dispose();
    }
}
