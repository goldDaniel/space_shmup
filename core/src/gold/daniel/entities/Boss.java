
package gold.daniel.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import gold.daniel.entities.boss.PhaseOutro;
import gold.daniel.entities.boss.Phase1;
import gold.daniel.entities.boss.Phase2;
import gold.daniel.entities.boss.Phase3;
import gold.daniel.entities.boss.Phase4;
import gold.daniel.entities.boss.PhaseEntrance;
import gold.daniel.main.Assets;
import gold.daniel.main.Globals;
import gold.daniel.main.World;
import gold.daniel.main.Particle;

/**
 *
 * @author wrksttn
 */
public class Boss extends Entity
{
    
    static final String VERTEX = Gdx.files.internal("misc/vertex.glsl").readString();
    static final String FRAGMENT = Gdx.files.internal("misc/fragment.glsl").readString();
    
    static ShaderProgram hitShader = new ShaderProgram(VERTEX, FRAGMENT);
    
    boolean drawHitFrame;
    
    public static enum Phase 
    {
          PHASE_ENTRANCE,
          PHASE_1,
          PHASE_2,
          PHASE_3,
          PHASE_4,
          PHASE_OUTRO,
    }
    
    private Phase currentPhase; 
    
    Texture tex = Assets.boss;
    
    public float health;
    
    public boolean inTransition;
    
    private float transitionTimer = 0;
    
    private final float TRANSITON_TIME = 8f;
    
    public Boss(float startTime)
    {
        super(0);
        
        drawHitFrame = false;
        inTransition = false;
        
        isAlive = true;
        
        width = tex.getWidth();
        height = tex.getHeight();
        
        x = Globals.GAME_WIDTH / 2 - width / 2;
        y = Globals.GAME_HEIGHT + 32;
        
        invulnerable = false;
        
        health = 400f;

        currentPhase = Phase.PHASE_ENTRANCE;
    }
    
    @Override
    public void update(World level, float delta)
    {
        drawHitFrame = false;
        if(inTransition)
        {
            invulnerable = true;
            
            
            x = MathUtils.lerp(
                    x, 
                    Globals.GAME_WIDTH / 2 - width / 2, 
                    0.03f);
         
            transitionTimer += delta;
            
            if(transitionTimer >= TRANSITON_TIME)
            {
                inTransition = false;
                transitionTimer = 0;
            }
            return;
        }
        else
        {
            invulnerable = false;
        }

        switch(currentPhase)
        {
            case PHASE_ENTRANCE: 
                PhaseEntrance.update(this, level, delta);
                level.getPlayer().setBulletType(Bullet.BulletType.PLAYER_00);
                return;
            case PHASE_1: 
                Phase1.update(this, level, delta);
                level.getPlayer().setBulletType(Bullet.BulletType.PLAYER_00);
                break;
            case PHASE_2: 
                Phase2.update(this, level, delta);    
                level.getPlayer().setBulletType(Bullet.BulletType.PLAYER_01);
                break;
            case PHASE_3: 
                Phase3.update(this, level, delta); 
                level.getPlayer().setBulletType(Bullet.BulletType.PLAYER_02);
                break;
            case PHASE_4: 
                Phase4.update(this, level, delta);
                level.getPlayer().setBulletType(Bullet.BulletType.PLAYER_03);
                break;
            case PHASE_OUTRO: 
                PhaseOutro.update(this, level, delta);
                break;
        }
             
        if(!invulnerable)
        {
            Array<Bullet> bullets = level.getBullets();

            for(Bullet bullet : bullets)
            {
                if(bullet.isAlive)
                {
                    if(bullet.type == Bullet.BulletType.PLAYER_00 ||
                       bullet.type == Bullet.BulletType.PLAYER_01 ||
                       bullet.type == Bullet.BulletType.PLAYER_02 ||
                       bullet.type == Bullet.BulletType.PLAYER_03)
                    {
                        if(bullet.isBoundingBoxColliding(this))
                        {
                            bullet.kill(level);
                            health -= bullet.damage;
                            drawHitFrame = true;
                        }
                    }
                }
            }
        }
       
        if(level.getPlayer().isBoundingBoxColliding(this))
        {
            level.getPlayer().kill(level);
        }
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        if(drawHitFrame)
        {
            batch.setShader(hitShader);
        }
      
        batch.draw(tex, x, y, width, height);
        batch.setShader(null);
        
        //healthbar
        batch.setColor(Color.RED);
        batch.draw(Assets.texture, 
                4, 
                Globals.GAME_HEIGHT - 4, 
                Globals.GAME_WIDTH * health / 400f - 8,
                -20);
        
        
        Color c = Color.RED.cpy();
        c.a = 0.2f;
        batch.setColor(c);
        batch.draw(Assets.texture, 
                4, 
                Globals.GAME_HEIGHT - 4, 
                Globals.GAME_WIDTH - 8,
                -20);
        
        batch.setColor(Color.WHITE);
        batch.draw(Assets.texture, 
                4, 
                Globals.GAME_HEIGHT - 4,  
                Globals.GAME_WIDTH - 8, 
                -4);
        batch.draw(Assets.texture, 
                4, 
                Globals.GAME_HEIGHT - 24, 
                Globals.GAME_WIDTH - 8, 
                -4);
        batch.draw(Assets.texture, 
                4, 
                Globals.GAME_HEIGHT - 4, 
                4, 
                -20);
        batch.draw(Assets.texture, 
                Globals.GAME_WIDTH - 8, 
                Globals.GAME_HEIGHT - 4, 
                4, 
                -20);  
    }

    @Override
    public void kill(World level)
    {
        isAlive = false;
        
        Assets.bossExplosion.play();
        
        level.addParticle(new Particle(x + width / 4, y + height / 2, 
                Particle.ParticleType.EXPLOSION_LARGE));
        level.addParticle(new Particle(x + width * 3 / 4, y + height / 2, 
                Particle.ParticleType.EXPLOSION_LARGE));
    }

    @Override
    public void dispose()
    {
    }
    
    public void setPhase(Phase phase)
    {
        this.currentPhase = phase;
    }
    
    public float getHealth()
    {
        return health;
    }
}
