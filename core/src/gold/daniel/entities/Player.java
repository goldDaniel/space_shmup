package gold.daniel.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import gold.daniel.entities.Bullet.BulletType;
import gold.daniel.main.Assets;
import gold.daniel.main.Globals;
import gold.daniel.main.Input;
import gold.daniel.main.World;
import gold.daniel.main.Particle;

/**
 *
 * @author wrksttn
 */
public class Player extends Entity
{
    public boolean respawning = false;
    
    public  float lives = 5;
    
    //for animation timing, remains private
    private float stateTime;
    
    Input input;
    
    //speed is pixels per second
    //means this will take /N seconds to move across the screen
    private float speed = Globals.GAME_WIDTH / 2.f;
    
    Animation<Texture> idle;
    Animation<Texture> transition;
    Animation<Texture> banking;
    
    Animation<Texture> currentAnimation;
    
    Texture currentTexture;
    
    boolean movingRight;
    
    public BulletType bulletType;
    
    //can shoot every N frames
    final int FIRERATE = 4;
    float fireCount;
    
    
    float slowMotionTimer = 100;
    
    public Player(Input input)
    {
        super(0);
        
        isAlive = true;
        
        this.input = input;

        bulletType = BulletType.PLAYER_00;
        
        idle = new Animation<Texture>(
                1f/16f,
                Assets.playerIdleFrames,
                Animation.PlayMode.LOOP_PINGPONG);
        
        transition = new Animation<Texture>(
                1f/32f,
                Assets.playerTransitionFrames, 
                Animation.PlayMode.NORMAL);
        
        banking = new Animation<Texture>(
                1f/16f,
                Assets.playerLeanFrames, 
                Animation.PlayMode.LOOP_PINGPONG);
        
        currentAnimation = idle;
        currentTexture = currentAnimation.getKeyFrame(stateTime);
        
        width = currentTexture.getWidth();
        height = currentTexture.getHeight();
        collisionRadius = 4;
        
        respawning = true;
    }
    
    @Override
    public void update(World level, float delta)
    {
        
        stateTime += delta;
        input.update();
        
        currentTexture = getCurrentAnimationTexture();
        
        width = currentTexture.getWidth();
        height = currentTexture.getHeight();
        
        float speedFinal = speed;
        
        if(input.isSlowMowPressed())
        {
            if(slowMotionTimer <= 0)
            {
                Globals.TIMESCALE = 1f;
                slowMotionTimer = 0;
            }
            else
            {
                Globals.TIMESCALE = 0.5f;
                slowMotionTimer -= 0.25f;
            }
        }
        else
        {
            Globals.TIMESCALE = 1f;
            slowMotionTimer += 0.125f;
        }
        if(slowMotionTimer >= 100)
        {
            slowMotionTimer = 100;
        }   
        
        x += input.getDirection().x * speedFinal * delta;
        y += input.getDirection().y * speedFinal * delta;
        
        
        x = MathUtils.clamp(x, -width / 3, Globals.GAME_WIDTH - width * 2f / 3f);
        y = MathUtils.clamp(y, -width / 2, Globals.GAME_HEIGHT - height / 2);
    
        if(canFire(delta) && input.isShootPressed())
        {
            Assets.playerLaser.play(0.05f);
            
            level.createBullet(x + width / 2, y + height, bulletType);
            
            fireCount = FIRERATE;
        }
        
        Array<Bullet> bl = level.getBullets();
        for(Bullet bullet : bl)
        {
            if(bullet.isAlive)
            {
                if(bullet.type == BulletType.SMALL_BLUE   ||
                   bullet.type == BulletType.SMALL_PURPLE ||
                   bullet.type == BulletType.SMALL_ORANGE)
                {
                   if(isColliding(bullet))
                    {
                        kill(level);
                    }
                }
            }
        }
    }
    
    private boolean canFire(float delta)
    {
        boolean result = false;
        if(fireCount <= 0)
        {
            result = true;
        }
        else
        {
            fireCount -= 60f * delta;
        }
        
        return result;
    }
    
    public void setBulletType(BulletType type)
    {
        bulletType = type;
    }
    
    /**
     * 
     * State machine that decides which animation is currently in use.
     * 
     * @return final texture for current frame
     */
    private Texture getCurrentAnimationTexture()
    {
        //when we are idle, we can set our movingRight variable to true or false
        //then set the animation to transition
        if(currentAnimation == idle)
        {
            //moving right
            if(input.getDirection().x > 0)
            {
                movingRight = true;
                currentAnimation = transition;
                transition.setPlayMode(Animation.PlayMode.NORMAL);
                stateTime = 0;
            }
            //moving left
            else if(input.getDirection().x < 0)
            {
                movingRight = false;
                currentAnimation = transition;
                transition.setPlayMode(Animation.PlayMode.NORMAL);
                stateTime = 0;
            }
        }
        else if(currentAnimation == transition)
        {
            //if we are currently moving right
            if(input.getDirection().x > 0)
            {
                if(transition.isAnimationFinished(stateTime))
                {
                    //if we were previously moving right
                    if(movingRight)
                    {
                        currentAnimation = banking;
                    }
                    //if we were previously moving left
                    else
                    {
                        currentAnimation = idle;
                    }
                }
            }
            //if we are currently moving right
            else if(input.getDirection().x < 0)
            {
                if(transition.isAnimationFinished(stateTime))
                {
                    //if we were previously moving left
                    if(!movingRight)
                    {
                        currentAnimation = banking;
                    }
                    //if we were previously moving right
                    else
                    {
                        currentAnimation = idle;
                    }
                }
            }
            //not moving horizontal
            else
            {
                //we reverse the animation if we are not moving horizontally
                //allowing us to smoothly return to the idle animation
                transition.setPlayMode(Animation.PlayMode.REVERSED);
                if(transition.isAnimationFinished(stateTime))
                {
                    stateTime = 0;
                    currentAnimation = idle;
                }
            }
        }
        else if(currentAnimation == banking)
        {
            //I know it can be done with one big if(&&) statement, but 
            //this is for clarity
            
            //if we are not moving horizontally
            //we switch to the transition state and play it in reverse
            //allowing us to smoothly switch
            if(input.getDirection().x == 0)
            {
                transition.setPlayMode(Animation.PlayMode.REVERSED);
                stateTime = 0;
                currentAnimation = transition;
            }
            //if we were prevously moving right, and we are now moving left
            //we switch to the transition state and play it in reverse
            //allowing us to smoothly switch
            else if(movingRight && input.getDirection().x < 0)
            {
                transition.setPlayMode(Animation.PlayMode.REVERSED);
                stateTime = 0;
                currentAnimation = transition;
            }
            //if we were previously moving left and we are now moving right
            //we switch to the transition state, and play it in reverse
            //allowing us to smoothly switch 
            else if(!movingRight && input.getDirection().x > 0)
            {
                transition.setPlayMode(Animation.PlayMode.REVERSED);
                stateTime = 0;
                currentAnimation = transition;
            }  
        }
        return currentAnimation.getKeyFrame(stateTime);
    }

    @Override
    public Circle getCollisionCircle()
    {
        return new Circle(x + width / 2, y + height * 3.f / 4.f + 4, collisionRadius);
    }


    @Override
    public void draw(SpriteBatch s, float parentAlpha)
    {
        s.draw(currentTexture, 
                x, y, 
                width, height, 
                0, 0, 
                (int)width, (int)height, 
                movingRight, false);      
        
        
        s.setColor(Color.MAGENTA);
        s.draw(Assets.texture, 4, 4, 20, slowMotionTimer / 100f * 300f);
        
        Color c = Color.MAGENTA.cpy();
        c.a = 0.2f;
        s.setColor(c);
        s.draw(Assets.texture, 4, 4, 20, 300f);
        
        s.setColor(Color.WHITE);
        s.draw(Assets.texture, 4, 4, 4, 300);
        s.draw(Assets.texture, 24, 4, 4, 300);
        s.draw(Assets.texture, 4, 4, 20, 4);
        s.draw(Assets.texture, 4, 304, 24, 4);
        
        
        s.setColor(Color.WHITE);   
        
        
        for (int i = 0; i < lives; i++)
        {
            s.draw(
                idle.getKeyFrame(0), 
                32, 
                4 + (idle.getKeyFrame(0).getHeight() / 2 * i), 
                idle.getKeyFrame(0).getWidth() / 2,
                idle.getKeyFrame(0).getHeight() / 2);
        }
    }

    @Override
    public void dispose()
    {
       
    } 

    @Override
    public void kill(World level)
    {
        if(!invulnerable)
        {
            Assets.playerExplosion.play();
            invulnerable = true;
            respawning = true;
            slowMotionTimer = 100;
            Globals.TIMESCALE = 1f;
            level.addParticle(new Particle(x + width / 2, y + height / 2, 
                    Particle.ParticleType.EXPLOSION_LARGE));
            lives--;
            currentAnimation = idle;
            currentTexture = idle.getKeyFrame(0);
            if(lives > 0)
            {
                level.respawnPlayer();
            }
            else
            {
                level.gameOver();
            }
        }
    }
}
