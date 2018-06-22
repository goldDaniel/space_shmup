/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author goldd
 */
public class Globals
{
    public static float TIMESCALE = 1f;
    
    public static final int GAME_WIDTH  = 810;
    public static final int GAME_HEIGHT = 1080;
    
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 800;
    
    public static final int UI_WIDTH = GAME_WIDTH / 2;
    public static final int UI_HEIGHT = GAME_HEIGHT / 2;
    
    public static final Vector2 DIR_000DEG = new Vector2(MathUtils.cosDeg(0),   MathUtils.sinDeg(0));
    public static final Vector2 DIR_030DEG = new Vector2(MathUtils.cosDeg(30),  MathUtils.sinDeg(30));
    public static final Vector2 DIR_060DEG = new Vector2(MathUtils.cosDeg(60),  MathUtils.sinDeg(60));
    public static final Vector2 DIR_090DEG = new Vector2(MathUtils.cosDeg(90),  MathUtils.sinDeg(90));
    public static final Vector2 DIR_120DEG = new Vector2(MathUtils.cosDeg(120), MathUtils.sinDeg(120));
    public static final Vector2 DIR_150DEG = new Vector2(MathUtils.cosDeg(150), MathUtils.sinDeg(150));
    public static final Vector2 DIR_180DEG = new Vector2(MathUtils.cosDeg(180), MathUtils.sinDeg(180));
    public static final Vector2 DIR_210DEG = new Vector2(MathUtils.cosDeg(210), MathUtils.sinDeg(210));
    public static final Vector2 DIR_240DEG = new Vector2(MathUtils.cosDeg(240), MathUtils.sinDeg(240));
    public static final Vector2 DIR_270DEG = new Vector2(MathUtils.cosDeg(270), MathUtils.sinDeg(270));
    public static final Vector2 DIR_300DEG = new Vector2(MathUtils.cosDeg(300), MathUtils.sinDeg(300));
    public static final Vector2 DIR_330DEG = new Vector2(MathUtils.cosDeg(330), MathUtils.sinDeg(330));
}
