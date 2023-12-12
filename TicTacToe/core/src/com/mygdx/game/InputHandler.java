package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {
    private static Vector2 mousePos, pressedMousePos;

    public static void initialize() {
        mousePos = new Vector2();
        pressedMousePos = new Vector2();
    }

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
        pressedMousePos.x = x;
        pressedMousePos.y = Engine.WINDOW_HEIGHT - y; //LibGDX using origin ata bottom left but for some reason inputs are top left
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        pressedMousePos.x = -1;
        pressedMousePos.y = -1;
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
        mousePos.x = xPos;
        mousePos.y = Engine.WINDOW_HEIGHT - yPos; //LibGDX using origin ata bottom left but for some reason inputs are top left
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public Vector2 getMousePos() {
        return mousePos;
    }

    public Vector2 getPressedPos() {
        return pressedMousePos;
    }
}
