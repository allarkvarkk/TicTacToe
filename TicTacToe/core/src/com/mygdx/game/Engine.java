package com.mygdx.game;// Engine.java

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Engine extends Game {
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;
    public static int WINDOW_HEIGHT, WINDOW_WIDTH;
    private InputHandler inputHandler;
    public static int GRID_OFFSET;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        WINDOW_HEIGHT = Gdx.graphics.getHeight();
        WINDOW_WIDTH = Gdx.graphics.getWidth();
        GRID_OFFSET = WINDOW_HEIGHT / 6;
        inputHandler = new InputHandler();
        Gdx.input.setInputProcessor(inputHandler);
        Board board = new Board(this);
        setScreen(board);
    }

    @Override
    public void render() {
        super.render();
    }

    public int getMonitorHeight() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension monitorRes = toolkit.getScreenSize();
        return (int) monitorRes.getHeight() * 2 - (int) monitorRes.getHeight() / 4;
    }

    public int getMonitorWidth() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension monitorRes = toolkit.getScreenSize();
        return (int) monitorRes.getWidth() * 2 - (int) monitorRes.getWidth() / 4;
    }


    public static int getCenterOfWindow_X() {
        return WINDOW_WIDTH / 2;
    }

    public static int getCenterOfWindow_y() {
        return WINDOW_HEIGHT / 2;
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
    public ShapeRenderer getShapeRenderer(){
        return shapeRenderer;
    }
    public BitmapFont getBitmapFont(){
        return bitmapFont;
    }
    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }
    public InputHandler getInputHandler(){
        return inputHandler;
    }

}
