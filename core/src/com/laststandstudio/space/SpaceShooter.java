/******************************************************************************
 * Space Shooter Software License
 * Version 0.0.2-alpha
 *
 * Copyright (C) 2015 Last Stand Studio
 *
 *  SpaceShooter is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  SpaceShooter is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SpaceShooter.  If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.laststandstudio.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.laststandstudio.space.Addons.SpaceShooterAddon;
import com.laststandstudio.space.Levels.SplashScreen;
import com.laststandstudio.space.engine.Utils.Logger;
import com.laststandstudio.space.engine.Utils.MusicManager;

import java.util.List;

public class SpaceShooter extends Game {

    public static final String BUILD_VERSION = "v0.0.2-alpha";

    public static AssetManager assetManager;
    public static boolean debug;
    public static MusicManager musicManager;
    public static Logger logger;
    public static List<SpaceShooterAddon> addons;
    public static GameMode mode;
    public static StartupOptions startupOptions;

    public enum GameMode {
        INITIALIZATION,
        MENU_MAIN,
        MENU_PAUSE,
        RUNNING_SINGLEPLAYER,
        RUNNING_MULTIPLAYER,
        CLOSING,
        HALTED,
    }

    private String[] mix = {
            "music/TSLASH_Mixtape/rain.ogg",
            "music/TSLASH_Mixtape/dawn.ogg",
            "music/TSLASH_Mixtape/wanted.ogg",
    };


    public SpaceShooter(StartupOptions startupOptions) {
        SpaceShooter.startupOptions = startupOptions;
        debug = startupOptions.debug;
    }


    @Override
    public void create() {
        mode = GameMode.INITIALIZATION;
        logger = new Logger();
        SpaceShooter.logger.logDebug("DEBUGGING MODE ACTIVATED");
        SpaceShooter.logger.logDebug("Current Version = " + BUILD_VERSION);
        logger.logDebug("Building Game Instance");
        assetManager = new AssetManager();
        setCursor("menus/slick_arrow-delta.png");
        musicManager = new MusicManager(mix);
        logger.logDebug("Setting Screen to 'Splash Screen'");
        setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        super.render();
        musicManager.update();
    }

    @Override
    public void dispose() {
        logger.logDebug("Destroying Space Game Instance");
        musicManager.dispose();
        super.dispose();
    }

    private void setCursor(String fileLoc) {
        Pixmap cursor = new Pixmap(Gdx.files.internal(fileLoc));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursor, 0, 0));
        cursor.dispose();
    }
}
