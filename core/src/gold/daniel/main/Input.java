/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author wrksttnpc
 */
public class Input
{
    private GameStateManager engine;
    
    private static int KEY_UP       = Keys.W;
    private static int KEY_DOWN     = Keys.S;
    private static int KEY_LEFT     = Keys.A;
    private static int KEY_RIGHT    = Keys.D;
    
    private static int KEY_SHOOT     = Keys.SPACE;
    private static int KEY_SLOWMOW   = Keys.SHIFT_LEFT;
    
    
    private boolean isLeftPressed;
    private boolean isRightPressed;
    
    private boolean isUpPressed;
    private boolean isDownPressed;
    
    
    //I dont like these being implemented here
    private boolean isShootPressed;
    private boolean isSlowMowPressed;
    
    public Input(GameStateManager engine)
    {
        this.engine = engine;
    }
    
    public void update()
    {
        isLeftPressed   = engine.isKeyPressed(KEY_LEFT);
        isRightPressed  = engine.isKeyPressed(KEY_RIGHT);
        isUpPressed     = engine.isKeyPressed(KEY_UP);
        isDownPressed   = engine.isKeyPressed(KEY_DOWN);
        
        if(engine.isKeyJustPressed(KEY_SHOOT))
        {
            isShootPressed = !isShootPressed;
        }
        isSlowMowPressed = engine.isKeyPressed(KEY_SLOWMOW);
    }
    
    public boolean isShootPressed()
    {
        return isShootPressed;
    }
    
    public boolean isDownPressed()
    {
        return isDownPressed;
    }
    
    public boolean isUpPressed()
    {
        return isUpPressed;
    }
    
    public boolean isSlowMowPressed()
    {
        return isSlowMowPressed;
    }
    
    /**
     * 
     * gets direction based on input keys.
     * 
     * -1 if left, 0 if none, +1 if right
     * 
     * @return 
     */
    public Vector2 getDirection()
    {
        Vector2 result = new Vector2();
        
        if(isLeftPressed)  result.x -= 1;
        if(isRightPressed) result.x += 1;
        
        if(isUpPressed)    result.y += 1;
        if(isDownPressed)  result.y -= 1;
        
        return result.nor();
    }
}
