package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        Engine eng = new Engine();
        config.setForegroundFPS(60);
        config.setTitle("Tic Tac Toe");
        config.setWindowedMode(eng.getMonitorWidth(), eng.getMonitorHeight());

        new Lwjgl3Application(eng, config);
    }


}
