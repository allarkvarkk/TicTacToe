package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {
    private float xPos, yPos;
    private int pressedX, pressedY;

    @Override
    public boolean keyDown(int key) {
        if (ScoreHandler.isGameOver() && key == Input.Keys.R) {
            Board.initialize();
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int i2, int i3) {
        pressedX = x;
        pressedY = Engine.WINDOW_HEIGHT - y; //LibGDX using origin ata bottom left but for some reason inputs are top left
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        pressedX = 0;
        pressedY = 0;
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = Engine.WINDOW_HEIGHT - yPos; //LibGDX using origin ata bottom left but for some reason inputs are top left
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public Vector2 getMousePos() {
        return new Vector2(xPos, yPos);
    }

    public Vector2 getPressedPos() {
        return new Vector2(pressedX, pressedY);
    }
}
