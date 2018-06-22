/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;


import gold.daniel.screens.MenuScreen;
import gold.daniel.screens.SplashScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import gold.daniel.screens.ControlsScreen;
import gold.daniel.screens.PlayScreen;

/**
 *
 * @author wrksttnpc
 */
public class GameStateManager
{
    private Array<Input> inputs;
    
    FitViewport viewport;
    OrthographicCamera camera;
    
    FitViewport hudViewport;
    OrthographicCamera hudCamera;

    SpriteBatch hudBatch;

    public ShapeRenderer sh;

    int sleepTime = 0;
    boolean sleep = false;
    
    int shake = 0;
    boolean shakeDir = false;
    
    
    ArrayMap<Screens, Screen> screens;
    
    Screens currentScreen;

    public void initScreens()
    {
        screens.put(Screens.MAIN_MENU, new MenuScreen(this));
        screens.put(Screens.HOW_TO_PLAY, new ControlsScreen(this));
        screens.put(Screens.PLAY, new PlayScreen(this));
    }

    public Input getNewInput()
    {
        Input input = new Input(this);
        inputs.add(input);
        return input;
    }
    
    public enum Screens
    {
        SPLASH,
        MAIN_MENU,
        HOW_TO_PLAY,
        PLAY,
    }
    
    public GameStateManager()
    {
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        
        viewport = new FitViewport(Globals.GAME_WIDTH, Globals.GAME_HEIGHT, camera);
        viewport.apply();
        
        
        hudViewport = new FitViewport(Globals.UI_WIDTH, Globals.UI_HEIGHT, hudCamera);
        hudViewport.apply();
        
        camera.zoom = 1f;
        
        camera.position.x = Globals.GAME_WIDTH / 2 * camera.zoom;
        camera.position.y = Globals.GAME_HEIGHT / 2 * camera.zoom;
        
        hudCamera.position.x = Globals.GAME_WIDTH / 2;
        hudCamera.position.y = Globals.GAME_HEIGHT / 2;
        
        hudBatch = new SpriteBatch();
        hudBatch.enableBlending();
        
        
        screens = new ArrayMap();
        
        screens.put(Screens.SPLASH, new SplashScreen(this));
        currentScreen = Screens.SPLASH;
        
        inputs = new Array<Input>();
        
        
        sh = new ShapeRenderer();
    }
    
    /**
     * do engine things. updating camera. where engine calculations
     * should be done.
     */
    public void updateEngine()
    {
        if(isKeyPressed(Keys.ALT_LEFT) && isKeyJustPressed(Keys.ENTER))
        {
            if (Gdx.graphics.isFullscreen())
            {
                Gdx.graphics.setWindowedMode(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
            }
            else
            {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }
        }
        
        if(!sleep)
        {
            hudCamera.update();
            
            camera.update();
            if(shake > 0)
            {
                MathUtils.clamp(shake, 0, 20);
                shake--;
                shakeDir = !shakeDir;
            }
        }
        else
        {
            sleepTime--;
        }
        if(sleepTime <= 0)
        {
            sleep = false;
            sleepTime = 0;
        }
    
        
        camera.update();
    }

    /**
     * Like engine, but for when entities interact. What I want to do, is that
     * any time I write the same thing in 2 different scenes, it must be
     * re-factored into updateEngine()x
     * 
     * @param deltaTime 
     */
    public void updateGame(float deltaTime)
    {
        screens.get(currentScreen).render(deltaTime);
    }

    /**
     * CHECK IF MULTIPLE KEYS ARE PRESSED AT THE SAME TIME.
     * 
     * @param keycode keys wanting to check
     * @return 
     */
    public boolean areKeysPressed(int... keycode)
    {
        for(int code : keycode)
        {
            if(!Gdx.input.isKeyPressed(code))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * THIS METHOD IS NOT FOR DETECTING MULTIPLE KEYPRESSES AT THE SAME TIME.
     * This is to detect keys that do the same thing, i.e W and U+P for navigating
     * a menu. 
     * 
     * if(THIS KEY OR THIS KEY) 
     * 
     * @param keycode keys wanting to check
     * @return 
     */
    public boolean isKeyJustPressed(int... keycode)
    {
        for (int code : keycode)
        {
            if (Gdx.input.isKeyJustPressed(code))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * THIS METHOD IS NOT FOR DETECTING MULTIPLE KEYPRESSES AT THE SAME TIME.
     * This is to detect keys that do the same thing, i.e W and UP for navigating
     * a menu. 
     * 
     * if(THIS KEY OR THIS KEY) 
     * 
     * @param keycode keys wanting to check
     * @return 
     */
    public boolean isKeyPressed(int... keycode)
    {
        for (int code : keycode)
        {
            if (Gdx.input.isKeyPressed(code))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isMouseButtonPressed(int... buttoncode)
    {
        boolean result = false;
        for(int code : buttoncode)
        {
            if(Gdx.input.isButtonPressed(code))
            {
                result = true;
            }
            
        }
        return result;
    }
    
    public Vector2 getMouse()
    {
        Vector3 values = new Vector3(getMouseCoords(), 0);
        values = viewport.unproject(values);
        return new Vector2(values.x, values.y);
    }
    
    public void exit()
    {
        Gdx.app.exit();
    }
    
    public void resize(int width, int height)
    {
        viewport.update(width, height);
        viewport.apply();
        hudViewport.update(width, height);
        hudViewport.apply();
    }
    
    /**
     * USED TO PAUSE GAME FOR GIVEN AMOUNT OF FRAMES.
     * gives that "umph" effect
     * @param time 
     */
    public void sleep(int time)
    {
        sleep = true;
        sleepTime = time;
    }
    
    public void shake(int intensity)
    {
        shake += intensity;
    }
    
    /**
     * Makes the camera jitter around at the current intensity.
     * This really starts to make the game feel alive
     */
    public void doCameraShake()
    {
        if(shake > 0)
        {
            camera.position.x += shakeDir ? -shake : shake;
            camera.position.y += shakeDir ? shake : -shake;
        }
    }


    private Vector2 getMouseCoords()
    {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }
    
    public OrthographicCamera getCamera()
    {
        return camera;
    }
    
    public void setScreen(Screens screen)
    {
        this.currentScreen = screen;
    }
    
    public Viewport getViewport()
    {
        return viewport;
    }
    
    public Viewport getHUDViewport()
    {
        return hudViewport;
    }
    
    public SpriteBatch getHUDSpriteBatch()
    {
        return hudBatch;
    }
}