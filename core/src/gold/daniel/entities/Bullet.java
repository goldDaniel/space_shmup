
package gold.daniel.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import gold.daniel.main.Assets;
import gold.daniel.main.Globals;
import gold.daniel.main.World;
import gold.daniel.main.Particle;
import gold.daniel.main.Particle.ParticleType;

/**
 *
 * @author wrksttn
 */
public class Bullet extends Entity implements Poolable
{   

    @Override
    public void reset()
    {
        x = -100;
        y = -100;
        isAlive = false;
        speed = 0;
        angle = 0;
        damage = 0;
    }
    
    public static enum BulletType
    {
        PLAYER_00,
        PLAYER_01,
        PLAYER_02,
        PLAYER_03,
        
        SMALL_PURPLE,
        SMALL_ORANGE,
        SMALL_BLUE,
    }
 
    public BulletType type;
    
    Array<ParticleType> particleTypes;
    
    TextureRegion texture;
    
    float speed;
    
    float angle;
    
    float damage;
    
    public Bullet(float x, float y, BulletType type)
    {
        super(0);
        isAlive = true;

        particleTypes = new Array<ParticleType>();
        this.type = type;
        setBulletType(type);
        
        width = texture.getRegionWidth() / 1.5f;
        height = texture.getRegionHeight() / 1.5f;
        
        this.x = x - width / 2;
        this.y = y;    
    }
    
    public Bullet(float x, float y, float angle, BulletType type)
    {
        this(x, y, type);
        this.angle = angle;
    }
    
    public Bullet(float x, float y, float angle, float bulletSpeed, BulletType type)
    {
        this(x, y, type);
        this.angle = angle;
        this.speed = bulletSpeed;
    }
    
    private void setBulletType(BulletType type)
    {
        switch(type)
        {
            case PLAYER_00: 
                
                angle = Globals.DIR_090DEG.angle();
                
                texture = Assets.playerBullet0;
                
                particleTypes.add(ParticleType.ADDITIONAL_BLUE_01);
                particleTypes.add(ParticleType.ADDITIONAL_BLUE_02);
                particleTypes.add(ParticleType.ADDITIONAL_BLUE_03);
                
                speed = Globals.GAME_HEIGHT;
              
                damage = 0.85f;
                
                break;
            case PLAYER_01: 
                
                angle = Globals.DIR_090DEG.angle();
                
                texture = Assets.playerBullet1;

                particleTypes.add(ParticleType.ADDITIONAL_BLUE_01);
                particleTypes.add(ParticleType.ADDITIONAL_BLUE_02);
                particleTypes.add(ParticleType.ADDITIONAL_BLUE_03);
                
                speed = Globals.GAME_HEIGHT * 1.2f;
                
                damage = 0.85f;
                
                break;
            case PLAYER_02: 
                
                angle = Globals.DIR_090DEG.angle();
                
                texture = Assets.playerBullet2;
                
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_01);
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_02);
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_03);
                
                speed = Globals.GAME_HEIGHT * 1.5f;
                
                damage = 0.85f;
                
                break;
            case PLAYER_03: 
                
                angle = Globals.DIR_090DEG.angle();
                
                texture = Assets.playerBullet3;
                
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_01);
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_02);
                particleTypes.add(ParticleType.ADDITIONAL_ORANGE_03);
                
                speed = Globals.GAME_HEIGHT * 2;
                
                damage = 0.85f;
                
                break;
            case SMALL_PURPLE:
                
                    angle = Globals.DIR_090DEG.angle();
                
                    texture = Assets.smallBulletPurple;
                    
                    speed = Globals.GAME_HEIGHT;
                    
                    break;
            case SMALL_ORANGE:
                
                    angle = Globals.DIR_090DEG.angle();
                
                    texture = Assets.smallBulletOrange;
                    
                    speed = Globals.GAME_HEIGHT;
                    
                    break;
            case SMALL_BLUE:
                
                    angle = Globals.DIR_090DEG.angle();
                
                    texture = Assets.smallBulletBlue;
                    
                    speed = Globals.GAME_HEIGHT;
                    
                    break;
        }
    }
    
    @Override
    public void kill(World level)
    {
        isAlive = false;
        if(particleTypes.size != 0)
        {
            level.addParticle(new Particle(
                        x + width / 2, y + height * 2,
                        particleTypes.get(MathUtils.random(particleTypes.size - 1))));
        }
    }
    
    @Override
    public void update(World level, float delta)
    {
        
        x += MathUtils.cosDeg(angle) * speed * delta;
        y += MathUtils.sinDeg(angle) * speed * delta;
        
        if(x < -width || x > Globals.GAME_WIDTH ||
           y < -height || y > Globals.GAME_HEIGHT)
        {
            isAlive = false;
            
        }
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        if(isAlive)
        {
            batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, angle);
        }
    }

    @Override
    public void dispose()
    {
        
    }  
    
    public void init(float x, float y, BulletType type)
    {
        isAlive = true;

        particleTypes = new Array<ParticleType>();
        this.type = type;
        setBulletType(type);
        
        width = texture.getRegionWidth() / 1.5f;
        height = texture.getRegionHeight() / 1.5f;
        
        this.x = x - width / 2;
        this.y = y;    
    }
    
    public void init(float x, float y, float angle, float speed, BulletType type)
    {
        isAlive = true;

        particleTypes = new Array<ParticleType>();
        this.type = type;
        setBulletType(type);
        
        width = texture.getRegionWidth() / 1.5f;
        height = texture.getRegionHeight() / 1.5f;
        
        this.x = x - width / 2;
        this.y = y;
        
        this.angle = angle;
        this.speed = speed;
    }
    
    
    
    
    public static Bullet createBullet(Pool<Bullet> bulletPool, float x, float y,
            float angle, float speed, BulletType type)
    {
        Bullet bullet = bulletPool.obtain();
        bullet.init(x, y, angle, speed, type);
        
        return bullet;
    }
}
