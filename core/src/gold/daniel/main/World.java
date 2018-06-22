
package gold.daniel.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.Bullet.BulletType;
import gold.daniel.entities.Entity;
import gold.daniel.entities.Player;

/**
 *
 * @author wrksttn
 */
public class World
{
    boolean isUpdating;
    
    float stateTime;  
    
    Array<Particle> particlesToAdd;
    Array<Particle> particles;
    
    Array<Entity> entitiesToAdd;
    Array<Entity> entities;
    
    Player player;
    
    Pool<Bullet> bulletPool;
    Array<Bullet> bullets;
    
    //hacked in to world for final release
    boolean drawFlash;
    float flashTimer;
    
    public World()
    {
        isUpdating = true;

        drawFlash = false;
        flashTimer = 0;
        
        entitiesToAdd = new Array<Entity>();
        entities = new Array<Entity>();
        
        particles = new Array<Particle>();
        particlesToAdd = new Array<Particle>();

        bullets = new Array<Bullet>(2500);
        bulletPool = new Pool<Bullet>(2500)
        {
            @Override
            protected Bullet newObject()
            {
                return new Bullet(-100, -100, 0, 0, Bullet.BulletType.SMALL_BLUE);
            }
        };
        
        isUpdating = false;
        
    }
    public void createBullet(float x, float y, float angle, float speed, BulletType type)
    {
        Bullet bullet = Bullet.createBullet(bulletPool, x, y, angle, speed, type);
        addEntity(bullet);
    }
    
    public void createBullet(float x, float y, BulletType type)
    {
        Bullet bullet = bulletPool.obtain();
        bullet.init(x, y, type);
        addEntity(bullet);
    }
    
    public void addEntity(Bullet bullet)
    {
        bullets.add(bullet);
    }
    
    public void addEntity(Entity entity)
    {
        if(entity instanceof Player)
        {
            player = (Player)entity;
        }
        
        if(isUpdating)
        {
            entitiesToAdd.add(entity);
        }
        else
        {
            entities.add(entity);
        }
    }
    
    public void addParticle(Particle particle)
    {
        if(isUpdating)
        {
            particlesToAdd.add(particle);
        }
        else
        {
            particles.add(particle);
        }
    }
    
    public void update(float deltaTime)
    {
        
        stateTime += deltaTime;
        flashTimer += deltaTime;
        
        if(flashTimer > 0.07f)
        {
            drawFlash = false;
        }
        
        isUpdating = true;
       
        for(Entity entity : entities)
        {
            if((entity == player))
            {
                if(player.respawning)
                {
                    bullets.clear();
                    //the total distance moved is the player height,
                    //when respawning, height is set to -player.height
                    //%f seconds until respwan complete
                    player.setY(player.getY() + (player.getHeight() / 3f * deltaTime));
                    if(player.getY() >= 0.5f)
                    {
                        player.respawning = false;
                        player.invulnerable = false;
                    }
                }
                else
                {
                    player.update(this, deltaTime);
                }
            }
            else if(entity instanceof Boss)
            {
                entity.update(this, deltaTime);
            }
            else if(stateTime > entity.START_TIME)
            {
                entity.update(this, deltaTime);
            }
        }
        
        for(Particle particle : particles)
        {
            particle.update(deltaTime);
        }
        
        
        for(Bullet bullet : bullets)
        {
            bullet.update(this, deltaTime);
        }
        
        isUpdating = false;

        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear();
        
        particles.addAll(particlesToAdd);
        particlesToAdd.clear();
        
        for(Bullet bullet : bullets)
        {
            if(!bullet.isAlive())
            {
                bulletPool.free(bullet);
                bullets.removeValue(bullet, true);
            }
        }
        //here we reuse the toAdd methods to clear entitities no longer used
        for(Entity entity : entities)
        {
            if( !(entity == player) /* && Boss? */ )
            {
                //because even if the player is no longer alive, we need it 
                //NOTE: may have to do with boss as well?
                if(!entity.isAlive())
                {
                    entitiesToAdd.add(entity);
                }
            }
        }
        
        entities.removeAll(entitiesToAdd, true);
        for(Particle particle : particles)
        {
            if(!particle.isAlive())
            {
                particlesToAdd.add(particle);
            }
        }
        particles.removeAll(particlesToAdd, true);
    }
    
    public void draw(SpriteBatch s)
    {
        if(drawFlash)
        {
            s.draw(Assets.texture, 0, 0, Globals.GAME_WIDTH, Globals.GAME_HEIGHT);
            return;
        }
        for(Entity entity : entities)
        {
            entity.draw(s, 1f);
        }
        for(Particle particle : particles)
        {
            particle.draw(s);
        }
        for(Bullet bullet : bullets)
        {
            bullet.draw(s, stateTime);
        }
    }
    
    public Array<Bullet> getBullets()
    {
        return bullets;
    }
    
    public Array<Particle> getParticles()
    {
        return particles;
    }
    
    public <T> Array<T> getEntityType(Class<T> type)
    {
        Array<T> result = new Array<T>();
        
        for(int i = 0; i < entities.size; i++)
        {
            T obj = (T)entities.get(i);
            if(obj.getClass() == type)
            {
                result.add(obj);
            }
        }
        return result;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    // == a > b ? a : b;
    //why is there no max fun
    public int max(int a, int b)
    {
        if(a > b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }
    
    public void respawnPlayer()
    {
        player.invulnerable = true;
        player.respawning = true;
        player.setX(Globals.GAME_WIDTH / 2 - player.getWidth() / 2);
        player.setY(-player.getHeight());
        drawFlash = true;
        flashTimer = 0;
    }
    
    public void gameOver()
    {
        //DO GAMEOVER STUFF
        entities.removeValue(player, true);
    }
}
