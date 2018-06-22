/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gold.daniel.entities.boss;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import gold.daniel.entities.Boss;
import gold.daniel.entities.Bullet;
import gold.daniel.entities.BulletSpawner;
import gold.daniel.main.World;

/**
 *
 * @author wrksttn
 */
public class Phase4
{   
    
    private static float cycle = MathUtils.PI/2f;
    
    static BulletSpawner leftContainment = new BulletSpawner(
            0, 0, 2, 55, 
            -90, 0, 1, 250, 
            Bullet.BulletType.SMALL_ORANGE);
    
    static BulletSpawner rightContainment = new BulletSpawner(
            0, 0, 2, 55,
            -90f-55f, 0, 1, 250, 
            Bullet.BulletType.SMALL_ORANGE);
    
    
    static BulletSpawner spinSpawner = new BulletSpawner(
            0, 0, 4, 360,
            0, 270f, 3, 75, 
            Bullet.BulletType.SMALL_PURPLE);
    
    static BulletSpawner spraySpawner = new BulletSpawner(
            0, 0, 5, 120,
            -90f-60f, 0, 26, 95, 
            Bullet.BulletType.SMALL_BLUE);
    
    public static void update(Boss boss, World world, float delta)
    {
        if(boss.getHealth() <= 1)
        {
            boss.health = 1;
            boss.setPhase(Boss.Phase.PHASE_OUTRO);
            boss.inTransition = true;
            return;
        }
        boss.x += MathUtils.cosDeg(cycle) * 105f * delta;
        boss.y += MathUtils.sinDeg(cycle) * 25f  * delta;
        
        cycle += delta*60f;
        
        leftContainment.x = boss.x;
        leftContainment.y = boss.y + boss.getHeight() / 2f;
        
        rightContainment.x = boss.x + boss.getWidth();
        rightContainment.y = boss.y + boss.getHeight() / 2f;
        
        spraySpawner.x = spinSpawner.x = boss.x + boss.getWidth() / 2f;
        spraySpawner.y = spinSpawner.y = boss.y + boss.getHeight() / 2f;
        
        leftContainment.update(world, delta);
        rightContainment.update(world, delta);
        spinSpawner.update(world, delta);
        spraySpawner.update(world, delta);
    }
    
    public static void draw(SpriteBatch s)
    {
        
    }
}
