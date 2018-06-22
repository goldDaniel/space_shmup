/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author wrksttn
 * @param <T>
 */
public class HitboxAnimation<T> extends Animation<T>
{
    
    Array<Rectangle> hitboxes;
    
    public HitboxAnimation(float frameDuration, Array<T> keyFrames, PlayMode mode)
    {
        super(frameDuration, keyFrames, mode);
        
        
        for (int i = 0; i < keyFrames.size; i++)
        {
            Rectangle hb = new Rectangle();
            
            TextureRegion tex = (TextureRegion)keyFrames.get(i);
            
            hb.x = tex.getRegionX();
            hb.y = tex.getRegionY();
            hb.width = tex.getRegionWidth();
            hb.height = tex.getRegionHeight();
            
            hitboxes.add(hb);
        }
    }
    
    public HitboxAnimation(float frameDuration, Array<T> keyFrames, PlayMode mode, 
            Array<Rectangle> hb) throws Exception
    {
        super(frameDuration, keyFrames, mode);
        
        if(keyFrames.size != hb.size)
        {
            throw new Exception("Animation frame count does not match hitbox count");
        }
        
        this.hitboxes = hb;
    }
    
    /**
     * gets X, Y, WIDTH, HEIGHT inside of texture.
     * 
     * 
     * @param stateTime
     * @return 
     */
    public Rectangle getFrameRectangle(float stateTime)
    {
        return hitboxes.get(this.getKeyFrameIndex(stateTime));
    }
}
