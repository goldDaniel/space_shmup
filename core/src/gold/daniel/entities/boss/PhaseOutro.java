/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import gold.daniel.entities.Boss;
import gold.daniel.main.Assets;
import gold.daniel.main.Particle;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class PhaseOutro 
{
    static float stateTime = 0;
    static boolean sideToggle = false;
    static int resets = 0;
    
    public static void update(Boss boss, World world, float delta)
    {
        boss.invulnerable = true;
        boss.health = 1;
        
        stateTime += delta + (delta * 12 * (float)resets/20f);
        
        if(stateTime >= 2)
        {
            resets++;
            stateTime = 0;
            Assets.enemySmallExplosion.play(0.4f);
            
            float x = boss.x;
            float y = boss.y;
            if(sideToggle)
            {   
                x += MathUtils.random(
                        boss.getWidth()/6f, 
                        boss.getWidth()/2f); 
            }
            else
            {
                x += MathUtils.random(
                        boss.getWidth()/2f, 
                        boss.getWidth());
            }
            
            y += MathUtils.random(boss.getHeight()/6f, boss.getHeight()-Assets.particleExplosionSmall.get(0).getHeight());
            
            world.addParticle(
                    new Particle(
                            x, y, 
                            Particle.ParticleType.EXPLOSION_SMALL));
            sideToggle = !sideToggle;
        }
        if(resets > 20)
        {
            boss.kill(world);
        }
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
