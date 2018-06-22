/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author wrksttn
 */
public class Particle
{
    public static enum ParticleType
    {
        ADDITIONAL_BLUE_01,
        ADDITIONAL_BLUE_02,
        ADDITIONAL_BLUE_03,
        
        ADDITIONAL_ORANGE_01,
        ADDITIONAL_ORANGE_02,
        ADDITIONAL_ORANGE_03,
        
        EXPLOSION_SMALL,
        EXPLOSION_LARGE,
    }
    
    Animation<Texture> animation;
    
    float stateTime;
    
    float x;
    float y;
    
    boolean isAlive;
    
    boolean flip;
    
    public Particle(float x, float y, ParticleType type)
    {
        isAlive = true;
        
        setAnimation(type);
        
        this.x = x - animation.getKeyFrame(0).getWidth() / 2;
        this.y = y - animation.getKeyFrame(0).getHeight() / 2;
        
        flip = MathUtils.randomBoolean();
    }
    
    private void setAnimation(ParticleType type)
    {
        switch(type)
        {
            case ADDITIONAL_BLUE_01:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional01);
                break;
            case ADDITIONAL_BLUE_02:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional02);
                break;
            case ADDITIONAL_BLUE_03:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional03);
                break;
            case ADDITIONAL_ORANGE_01:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional04);
                break;
            case ADDITIONAL_ORANGE_02:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional05);
                break;
            case ADDITIONAL_ORANGE_03:
                animation = new Animation<Texture>(1/32f, Assets.particleAdditional06);
                break;
            case EXPLOSION_SMALL:
                animation = new Animation<Texture>(1/32f, Assets.particleExplosionSmall);
                break;
            case EXPLOSION_LARGE:
                animation = new Animation<Texture>(1/24f, Assets.particleExplosionLarge);
                break;
        }
    }
    
    public boolean isAlive()
    {
        return isAlive;
    }
    
    public void update(float delta)
    {
        if(animation.isAnimationFinished(stateTime)) isAlive = false;
        
        stateTime += delta;
    }
    
    public void draw(SpriteBatch s)
    {
        if(isAlive)
        {
            s.draw(animation.getKeyFrame(stateTime), 
                x, y, 
                animation.getKeyFrame(stateTime).getWidth(), animation.getKeyFrame(stateTime).getHeight(), 
                0, 0, 
                animation.getKeyFrame(stateTime).getWidth(), animation.getKeyFrame(stateTime).getHeight(),  
                flip, false);
        }
    }
}
